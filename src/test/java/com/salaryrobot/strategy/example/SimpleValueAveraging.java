package com.salaryrobot.strategy.example;

import net.osomahe.coinacrobat.api.exchange.entity.ExchangePair;
import net.osomahe.coinacrobat.api.strategy.entity.Language;
import net.osomahe.coinacrobat.api.strategy.entity.LocalizedText;
import net.osomahe.coinacrobat.api.ticker.entity.Price;
import net.osomahe.coinacrobat.api.strategy.entity.StrategyParam;
import net.osomahe.coinacrobat.api.strategy.boundary.StrategyScript;

/**
 * @author m.tkadlec
 */
public class SimpleValueAveraging extends StrategyScript {

    private Double targetValue;

    private ExchangePair exchangePair;

    private Double step;

    private Double threshold;

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    public void tick() {
        double usd = wallet.getAvailable(exchangePair.getPaymentCurrency());
        double curr = wallet.getAvailable(exchangePair.getCommodity());
        Price p = ticker.getLatest(exchangePair);
        double bid = p.getBid();
        double ask = p.getAsk();
        double currInUSD = curr * (bid + ask) / 2;
        double totalUSD = usd + currInUSD;

        double currentTarget = Double.valueOf(storage.getOrDefault("currentTarget", currInUSD + "").toString());
        if (currentTarget < targetValue) {
            currentTarget += step;
            storage.put("currentTarget", currentTarget);
        }

        double borderBuy = currentTarget - threshold;
        if (borderBuy > currInUSD && usd > threshold) {
            double needCURR = (currentTarget - currInUSD) / ask;
            trader.buyCommodity(exchangePair, needCURR);
            log.info("byuing " + needCURR + " for ask: " + ask);
        }

        double borderSell = currentTarget + threshold;
        if (borderSell < currInUSD && currInUSD > threshold) {
            double needCURR = (currInUSD - currentTarget) / bid;
            trader.sellCommodity(exchangePair, needCURR);
            log.info("selling " + needCURR + " for bid: " + bid);
        }

        graphs.plot(exchangePair.getPaymentCurrency().getCode(), usd);
        graphs.plot(exchangePair.getCommodity().getCode(), curr);
        graphs.plot(exchangePair.getCommodity().getCode() + "_in_" + exchangePair.getPaymentCurrency().getCode(), currInUSD);
        graphs.plot("total_" + exchangePair.getPaymentCurrency().getCode(), totalUSD);
        graphs.plot("PRICE", (bid + ask) / 2);
        graphs.plot("CURRENT_TARGET", currentTarget);
        log.info("USD:\t" + usd);
        log.info(exchangePair.getCommodity().getCode() + ":\t" + curr);
        log.info(exchangePair.getCommodity().getCode() + "_in_" + exchangePair.getPaymentCurrency().getCode() + ":\t" + currInUSD);
        log.info("total_" + exchangePair.getPaymentCurrency().getCode() + ":\t" + totalUSD);
        log.info("ask:\t" + ask);
        log.info("bid:\t" + bid);
        log.info("Current Target:\t" + currentTarget);
        log.info("tick finished");

    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub

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
            name = @LocalizedText(language = Language.EN, text = "Target Value"),
            description = @LocalizedText(language = Language.EN, text = "Amount in payment currency (e.g. USD) the strategy will be targeting to keep in invested commodity (e.g. BTC)"),
            index = 1)
    public double defineTargetValue(Double value) {
        if (value == null) {
            value = 1_000.0;
        }
        this.targetValue = value;
        return value;
    }

    /**
     * @param step
     * @return the provided value. If value==null, returns default calculated value
     */
    @StrategyParam(
            name = @LocalizedText(language = Language.EN, text = "Onboarding step"),
            description = @LocalizedText(language = Language.EN, text = "Amount in USD the strategy will be increasing with every tick while onboarding the investment"),
            index = 2)
    public double defineOnboardingStep(Double step) {
        if (step == null) {
            step = 0.8 / 30 / 24 / 60;
        }
        this.step = step;
        return step;
    }

    /**
     * @param threshold
     * @return the provided value. If value==null, returns default calculated value
     */
    @StrategyParam(
            name = @LocalizedText(language = Language.EN, text = "Trading threshold"),
            description = @LocalizedText(language = Language.EN, text = "Amount in USD the strategy will buy or sell, depending on the commodity pair progression. Must me 6 USD or more."),
            index = 3)
    public double defineTradingThreshold(Double threshold) {
        if (threshold == null || threshold < 6) {
            threshold = 6d;
        }
        this.threshold = threshold;
        return threshold;
    }
}
