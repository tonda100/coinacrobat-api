package example;

import java.time.ZonedDateTime;

import net.osomahe.coinacrobat.api.exchange.entity.ExchangePair;
import net.osomahe.coinacrobat.api.strategy.boundary.StrategyScript;
import net.osomahe.coinacrobat.api.strategy.entity.Language;
import net.osomahe.coinacrobat.api.strategy.entity.LocalizedText;
import net.osomahe.coinacrobat.api.strategy.entity.StrategyParam;
import net.osomahe.coinacrobat.api.ticker.entity.Price;


/**
 * TODO write JavaDoc
 *
 * @author Antonin Stoklasek
 */
public class ValueAverigingMinMax extends StrategyScript {

    private static final String KEY_POSITION = "position";

    private static final String KEY_TRADE_NEXT_EPOCH = "trade-next-epoch";

    private static final String KEY_TRADE_NEXT_AMOUNT = "trade-next-amount";

    private double tradeMin = 6;

    private int period = 512;

    private ExchangePair exchangePair;


    @Override
    public void init() {
    }

    @Override
    public void tick() {
        ZonedDateTime from = now.minusDays(period);
        double priceMax = ticker.getMaximumAsk(exchangePair, from).getAsk() * 1.2;
        double priceMin = ticker.getMinimumBid(exchangePair, from).getBid() * 0.8;
        Price price = ticker.getLatest(exchangePair);
        double priceNow = (price.getAsk() + price.getBid()) / 2;
        double percentageNow = (priceNow - priceMin) / (priceMax - priceMin);

        double usd = wallet.getAvailable(exchangePair.getPaymentCurrency());
        double btc = wallet.getAvailable(exchangePair.getCommodity());
        double btcInUsd = btc * price.getBid();
        double total = usd + btcInUsd;
        double percentageWallet = btcInUsd / total;

        double positionSaved = Double.valueOf(storage.getOrDefault(KEY_POSITION, String.valueOf(btcInUsd)));
        double positionStep = getPositionStep(total);
        if (percentageNow > percentageWallet) {
            storage.put(KEY_POSITION, String.valueOf(positionSaved + positionStep));
        } else {
            storage.put(KEY_POSITION, String.valueOf(positionSaved - positionStep));
        }

        double tradeSize = getTradeSize();
        if (positionSaved - btcInUsd > tradeSize && usd > tradeMin) {
            // buying btc
            tradeSize = positionSaved - btcInUsd;
            if (tradeSize > usd) {
                tradeSize = usd;
            }
            trader.buyCommodity(exchangePair, tradeSize / price.getAsk());
            storage.put(KEY_TRADE_NEXT_EPOCH, String.valueOf(now.plusDays(1).toEpochSecond()));
            storage.put(KEY_TRADE_NEXT_AMOUNT, String.valueOf(tradeSize * 2));
        }
        if (btcInUsd - positionSaved > tradeSize) {
            // selling btc
            tradeSize = btcInUsd - positionSaved;
            trader.sellCommodity(exchangePair, tradeSize / price.getBid());
            storage.put(KEY_TRADE_NEXT_EPOCH, String.valueOf(now.plusDays(1).toEpochSecond()));
            storage.put(KEY_TRADE_NEXT_AMOUNT, String.valueOf(tradeSize * 2));
        }

        graphs.plot("position", positionSaved);
        graphs.plot(String.format("total_in_%s", exchangePair.getPaymentCurrency().getCode()), total);

        log.debug("priceMin: %s", priceMin);
        log.debug("priceMax: %s", priceMax);
        log.debug("priceNow: %s", priceNow);

        log.debug("%s: %s", exchangePair.getPaymentCurrency().getCode(), usd);
        log.debug("%s: %s", exchangePair.getCommodity().getCode(), btc);
        log.debug("%s_in_%s: %s", exchangePair.getCommodity().getCode(), exchangePair.getPaymentCurrency().getCode(), btcInUsd);
        log.debug("total_in_%s: %s", exchangePair.getPaymentCurrency().getCode(), total);

        log.debug("percentageNow: %s", percentageNow);
        log.debug("percentageWallet: %s", percentageWallet);
        log.debug("positionSaved: %s", positionSaved);
        log.debug("positionStep: %s", positionStep);
        log.debug("tradeSize: %s", tradeSize);
    }

    private double getTradeSize() {
        long tradeNextEpoch = Long.parseLong(storage.getOrDefault(KEY_TRADE_NEXT_EPOCH, String.valueOf(now.toEpochSecond())));
        double tradeNextAmount = Double.parseDouble(storage.getOrDefault(KEY_TRADE_NEXT_AMOUNT, String.valueOf(tradeMin)));
        if (tradeNextEpoch < now.toEpochSecond()) {
            tradeNextAmount = tradeNextAmount / 2;
            storage.put(KEY_TRADE_NEXT_EPOCH, String.valueOf(now.plusDays(1).toEpochSecond()));
            storage.put(KEY_TRADE_NEXT_AMOUNT, String.valueOf(tradeNextAmount));
        }
        if (tradeNextAmount < tradeMin) {
            tradeNextAmount = tradeMin;
        }
        return tradeNextAmount;
    }

    private double getPositionStep(double total) {
        double numberOfTicksPerDay = 60 * 24 / frequency.getMins();
        return total / period / numberOfTicksPerDay;
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
}

