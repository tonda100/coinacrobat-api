package net.osomahe.coinacrobat.smartapi.price;

import net.osomahe.coinacrobat.smartapi.Amount;
import net.osomahe.coinacrobat.smartapi.catalog.ExchangePair;
import net.osomahe.coinacrobat.smartapi.trade.TradeRequest;
import net.osomahe.coinacrobat.smartapi.trade.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.TemporalAmount;

public abstract class PriceService {

    public abstract Price getPrice(PriceRequest priceRequest);

    public abstract Price getMaximum(PriceRequest priceRequest, TemporalAmount temporalAmount);

    public abstract Price getMinimum(PriceRequest priceRequest, TemporalAmount temporalAmount);

    public abstract Price getAverage(PriceRequest priceRequest, TemporalAmount temporalAmount);

    public abstract Price getStandardDeviation(PriceRequest priceRequest, TemporalAmount temporalAmount);

    public abstract TechnicalAnalysis getTechnicalAnalysis(PriceRequest priceRequest, Integer count);

    public abstract ExchangePair findExchangePair(String currencyA, String currencyB);

    public final Transaction sell(Amount amountSell, String targetCurrencyCode, TradeRequest tradeRequest) {
        ExchangePair exchangePair = findExchangePair(amountSell.getCurrencyCode(), targetCurrencyCode);
        Price price = getPrice(new PriceRequest.Builder()
                .withExchangePair(exchangePair.getCode())
                .withTimestamp(tradeRequest.getTimestamp())
                .withWallet(tradeRequest.getWallet())
                .withCaching(tradeRequest.getCaching())
                .build()
        );
        Double valueSell;
        Double valueReceive;
        Double tradePrice;
        Amount fee;
        if (isCommodity(amountSell, exchangePair)) {
            valueSell = round(amountSell.getValue(), exchangePair.getDecimalsCommodity());
            tradePrice = price.getBid();
            valueReceive = round(tradePrice * valueSell, exchangePair.getDecimalsPaymentCurrency());
            fee = new Amount(
                    valueReceive * tradeRequest.getWallet().getFeePercentage(exchangePair),
                    exchangePair.getPaymentCurrency()
            );
        } else {
            tradePrice = price.getAsk();
            valueReceive = round(amountSell.getValue() / tradePrice, exchangePair.getDecimalsCommodity());
            valueSell = round(valueReceive * tradePrice, exchangePair.getDecimalsPaymentCurrency());
            fee = new Amount(
                    valueSell * tradeRequest.getWallet().getFeePercentage(exchangePair),
                    exchangePair.getPaymentCurrency()
            );
        }

        amountSell = new Amount(valueSell, amountSell.getCurrencyCode());
        Amount amountReceive = new Amount(valueReceive, targetCurrencyCode);


        return new Transaction.Builder()
                .withSell(amountSell)
                .withReceive(amountReceive)
                .withFee(fee)
                .withExchangePair(exchangePair)
                .withPrice(price)
                .withTradePrice(tradePrice)
                .withSuccess(exchangePair.isGreaterThanMinimum(amountSell, amountReceive))
                .build();
    }

    private boolean isCommodity(Amount amount, ExchangePair exchangePair) {
        return amount.getCurrencyCode().equals(exchangePair.getCommodity().getCode());
    }

    private Double round(Double number, int scale) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(number));
        BigDecimal bigDecimalRounded = bigDecimal.setScale(scale, RoundingMode.DOWN);
        return bigDecimalRounded.doubleValue();
    }
}
