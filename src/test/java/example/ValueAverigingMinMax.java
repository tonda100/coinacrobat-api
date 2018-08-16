package com.salaryrobot.strategy.example;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.TreeSet;

import net.osomahe.coinacrobat.api.exchange.entity.ExchangePair;
import net.osomahe.coinacrobat.api.strategy.boundary.StrategyScript;
import net.osomahe.coinacrobat.api.strategy.entity.Language;
import net.osomahe.coinacrobat.api.strategy.entity.LocalizedText;
import net.osomahe.coinacrobat.api.strategy.entity.StrategyParam;
import net.osomahe.coinacrobat.api.ticker.entity.Price;
import net.osomahe.coinacrobat.api.ticker.entity.TimestampPrice;


/**
 * TODO write JavaDoc
 *
 * @author Antonin Stoklasek
 */
public class ValueAverigingMinMax extends StrategyScript {

    public enum Behaviour {SHORT_TERM, NORMAL, LONG_TERM}

    private static final String KEY_POSITION = "position";

    private static final String KEY_TRADE_EPOCH = "trade-epoch";

    private static final String KEY_TRADE_AMOUNT = "trade-amount";

    private static final String KEY_MIN_MAX_EPOCH = "min-max-epoch";

    private static final String KEY_BEHAVIOUR = "behaviour";

    private static final String KEY_MIN = "min";

    private static final String KEY_MAX = "max";

    private ExchangePair exchangePair;

    private Behaviour behaviour;


    @Override
    public void init() {
        if (!storage.containsKey(KEY_TRADE_EPOCH) || !storage.containsKey(KEY_TRADE_AMOUNT)) {
            storage.put(KEY_TRADE_EPOCH, ZonedDateTime.now().toEpochSecond() + "");
            storage.put(KEY_TRADE_AMOUNT, 8 + "");
        }
        setMinMax();
    }

    private void setMinMax() {
        Long epoch = Long.valueOf(storage.getOrDefault(KEY_MIN_MAX_EPOCH, ZonedDateTime.now().minusMonths(2).toEpochSecond()).toString());
        ZonedDateTime zdtMinMax = Instant.ofEpochSecond(epoch).atZone(ZoneOffset.UTC);
        ZonedDateTime zdtBorder = ZonedDateTime.now().minusMonths(1);
        if (zdtBorder.isAfter(zdtMinMax) || !storage.containsKey(KEY_MIN) || !storage.containsKey(KEY_MIN) || behaviourIsChanged()) {
            Duration duration = getDuration();
            TreeSet<TimestampPrice> period = new TreeSet<>(new PriceComparator());
            period.addAll(ticker.getLast(exchangePair, ZonedDateTime.now().minus(duration)));

            storage.put(KEY_MIN_MAX_EPOCH, ZonedDateTime.now().toEpochSecond() + "");
            storage.put(KEY_MIN, period.first().getBid().toString());
            storage.put(KEY_MAX, period.last().getAsk().toString());
            storage.put(KEY_BEHAVIOUR, behaviour.name());
        }
    }

    /**
     * Provides how long in the should robot search in past for min and max.
     *
     * @return
     */
    private Duration getDuration() {
        if (behaviour.equals(Behaviour.SHORT_TERM)) {
            return Duration.ofDays(90);
        } else if (behaviour.equals(Behaviour.NORMAL)) {
            return Duration.ofDays(200);
        } else if (behaviour.equals(Behaviour.LONG_TERM)) {
            return Duration.ofDays(400);
        } else {
            throw new IllegalStateException("Behaviour is not set or unknown value: " + behaviour);
        }
    }

    /**
     * Decides whether the behaviour is set or recently changed by the user.
     *
     * @return
     */
    private boolean behaviourIsChanged() {
        if (!storage.containsKey(KEY_BEHAVIOUR)) {
            return true;
        }
        Behaviour saved = Behaviour.valueOf(storage.get(KEY_BEHAVIOUR).toString());
        return !saved.equals(behaviour);
    }

    @Override
    public void tick() {
        init();

        double paymentCurrency = wallet.getAvailable(exchangePair.getPaymentCurrency());
        double commodity = wallet.getAvailable(exchangePair.getCommodity());
        Price price = ticker.getLatest(exchangePair);
        double middlePrice = (price.getBid() + price.getAsk()) / 2;
        double commodityInPaymentCurrency = commodity * middlePrice;
        double totalInPaymentCurrency = commodityInPaymentCurrency + paymentCurrency;

        double minPrice = Double.parseDouble(storage.get(KEY_MIN).toString());
        double maxPrice = Double.parseDouble(storage.get(KEY_MAX).toString());

        double position = Double.valueOf(storage.getOrDefault(KEY_POSITION, commodityInPaymentCurrency).toString());
        double trend = (middlePrice - minPrice) / (maxPrice - minPrice); // 0 - buy all, 1 - sell everything
        double target = totalInPaymentCurrency - (trend * totalInPaymentCurrency);
        double step = getStep(totalInPaymentCurrency);

        if (position < target) {
            position += step;
        } else {
            position -= step;
        }
        storage.put(KEY_POSITION, position + "");

        double tradeAmount = Double.parseDouble(storage.get(KEY_TRADE_AMOUNT).toString());
        if (commodityInPaymentCurrency + tradeAmount < position) {
            // buy
            tradeAmount = position - commodityInPaymentCurrency;
            if (tradeAmount > paymentCurrency) {
                tradeAmount = paymentCurrency - 6;
            }
            if (tradeAmount > 6) {
                trader.buyCommodity(exchangePair, tradeAmount / price.getAsk());

                storage.put(KEY_TRADE_EPOCH, ZonedDateTime.now().toEpochSecond() + "");
                storage.put(KEY_TRADE_AMOUNT, tradeAmount * 2 + "");
            }
        } else if (commodityInPaymentCurrency - tradeAmount > position) {
            // sell
            tradeAmount = commodityInPaymentCurrency - position;
            if (tradeAmount > commodityInPaymentCurrency) {
                tradeAmount = commodityInPaymentCurrency - 6;
            }
            if (tradeAmount > 6) {
                trader.sellCommodity(exchangePair, tradeAmount / price.getBid());

                storage.put(KEY_TRADE_EPOCH, ZonedDateTime.now().toEpochSecond() + "");
                storage.put(KEY_TRADE_AMOUNT, tradeAmount * 2 + "");
            }
        } else {
            ZonedDateTime zdt = Instant.ofEpochSecond(Long.parseLong(storage.get(KEY_TRADE_EPOCH).toString())).atZone(ZoneOffset.UTC);
            if (zdt.plusDays(1).isAfter(ZonedDateTime.now())) {
                // if there was no trade last 24 hours, lower the trade amount
                storage.put(KEY_TRADE_EPOCH, ZonedDateTime.now().toEpochSecond() + "");
                storage.put(KEY_TRADE_AMOUNT, (tradeAmount / 2 > 6 ? tradeAmount / 2 : 6) + "");
            }
        }

        log.info("total_in_%s: %s", exchangePair.getPaymentCurrency().getCode(), totalInPaymentCurrency);
        log.info("%s: %s", exchangePair.getPaymentCurrency().getCode(), paymentCurrency);
        log.info(exchangePair.getCommodity().getCode() + ": " + commodity);
        log.info("%s_in_%s: %s",
                exchangePair.getCommodity().getCode(),
                exchangePair.getPaymentCurrency().getCode(),
                commodityInPaymentCurrency);

        graphs.plot(String.format("total_in_%s", exchangePair.getPaymentCurrency().getCode()), totalInPaymentCurrency);
        graphs.plot(exchangePair.getPaymentCurrency().getCode(), paymentCurrency);
        graphs.plot(exchangePair.getCommodity().getCode(), commodity);
        graphs.plot(String.format("%s_in_%s", exchangePair.getCommodity().getCode(), exchangePair.getPaymentCurrency().getCode()),
                commodityInPaymentCurrency);

        log.debug("minPrice: %s", minPrice);
        log.debug("maxPrice: %s", maxPrice);
        log.debug("position: %s", position);
        log.debug("trend: %s", trend);
        log.debug("target: %s", target);
        log.debug("tradeAmount: %s", tradeAmount);
    }

    private double getStep(double totalInPaymentCurrency) {
        long durationSecs = getDuration().getSeconds();
        double tickPeriodSecs = frequency.getMins() * 60;
        double numberOfTicks = durationSecs / tickPeriodSecs;
        return totalInPaymentCurrency / numberOfTicks;
    }

    @Override
    public void stop() {

    }

    @StrategyParam(
            name = @LocalizedText(language = Language.EN, text = "Trading Exchange Pair"),
            description = @LocalizedText(language = Language.EN, text = "Exchange Pair to be traded, e.g. BTC_USD"),
            index = 0)
    public ExchangePair defineExchangePair(ExchangePair exchangePair) {
        if (exchangePair == null) {
            exchangePair = ExchangePair.BTC_USD;
        }
        this.exchangePair = exchangePair;

        return exchangePair;
    }

    @StrategyParam(
            name = @LocalizedText(language = Language.EN, text = "Behaviour"),
            description = @LocalizedText(language = Language.EN, text = "Behaviour time frame"),
            index = 1)
    public Behaviour defineBehaviour(Behaviour behaviour) {
        if (behaviour == null) {
            behaviour = Behaviour.NORMAL;
        }
        this.behaviour = behaviour;

        return behaviour;
    }

    public static class PriceComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            TimestampPrice a = (TimestampPrice) o1;
            TimestampPrice b = (TimestampPrice) o2;
            return a.getAsk().compareTo(b.getAsk());
        }
    }
}
