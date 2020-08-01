package net.osomahe.coinacrobat.api;

import java.time.Period;
import java.util.List;

public class DailyStrategy extends StrategyScript {

    private ExchangePair exchangePair;


    @Override
    protected void tick() {
        sell(10, "usd").to("xrp").withNote("to se mi to povedlo");
        receive(1000, "xrp").from("usd").withNote("akoy");

        getMaximum(exchangePair, Period.ofYears(1));

        List<Currency> values = getCatalogValues(Currency.class);

        PaymentCurrency a = PaymentCurrency.of("abc");

    }

    @StrategyParam(
            name = @LocalizedText(language = Language.EN, text = "Trading Exchange Pair"),
            description = @LocalizedText(language = Language.EN, text = "Exchange Pair to be traded, e.g. BTC_USD"),
            index = 0)
    public ExchangePair defineExchangePair(ExchangePair exchangePair) {
        this.exchangePair = exchangePair;

        return exchangePair;
    }
}
