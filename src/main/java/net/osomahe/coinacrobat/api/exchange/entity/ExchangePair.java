package net.osomahe.coinacrobat.api.exchange.entity;


import java.util.HashSet;
import java.util.Set;


/**
 * Enumeration of all possible combinations of {@link Commodity} and {@link PaymentCurrency}.
 *
 * @author Antonin Stoklasek
 */
public enum ExchangePair {
    BTC_USD(Commodity.BTC, 8, PaymentCurrency.USD, 2),
    BTC_EUR(Commodity.BTC, 8, PaymentCurrency.EUR, 2),
    BTC_GBP(Commodity.BTC, 8, PaymentCurrency.GBP, 2),
    BTC_PAX(Commodity.BTC, 8, PaymentCurrency.GBP, 2),

    GBP_USD(Commodity.GBP, 5, PaymentCurrency.USD, 5),
    GBP_EUR(Commodity.GBP, 5, PaymentCurrency.EUR, 5),
    EUR_USD(Commodity.EUR, 5, PaymentCurrency.USD, 5),

    XRP_USD(Commodity.XRP, 8, PaymentCurrency.USD, 5),
    XRP_EUR(Commodity.XRP, 8, PaymentCurrency.EUR, 5),
    XRP_BTC(Commodity.XRP, 8, PaymentCurrency.BTC, 8),
    XRP_GBP(Commodity.XRP, 8, PaymentCurrency.GBP, 5),
    XRP_PAX(Commodity.XRP, 8, PaymentCurrency.PAX, 5),

    LTC_USD(Commodity.LTC, 8, PaymentCurrency.USD, 2),
    LTC_EUR(Commodity.LTC, 8, PaymentCurrency.EUR, 2),
    LTC_BTC(Commodity.LTC, 8, PaymentCurrency.BTC, 8),
    LTC_GBP(Commodity.LTC, 8, PaymentCurrency.GBP, 2),

    ETH_USD(Commodity.ETH, 8, PaymentCurrency.USD, 2),
    ETH_EUR(Commodity.ETH, 8, PaymentCurrency.EUR, 2),
    ETH_BTC(Commodity.ETH, 8, PaymentCurrency.BTC, 8),
    ETH_GBP(Commodity.ETH, 8, PaymentCurrency.GBP, 2),
    ETH_PAX(Commodity.ETH, 8, PaymentCurrency.PAX, 2),

    BCH_USD(Commodity.BCH, 8, PaymentCurrency.USD, 2),
    BCH_EUR(Commodity.BCH, 8, PaymentCurrency.EUR, 2),
    BCH_BTC(Commodity.BCH, 8, PaymentCurrency.BTC, 8),
    BCH_GBP(Commodity.BCH, 8, PaymentCurrency.GBP, 2),

    PAX_USD(Commodity.PAX, 5, PaymentCurrency.USD, 5),
    PAX_EUR(Commodity.PAX, 5, PaymentCurrency.EUR, 5),
    PAX_GBP(Commodity.PAX, 5, PaymentCurrency.GBP, 5),

    XLM_BTC(Commodity.XLM, 8, PaymentCurrency.BTC, 8),
    XLM_USD(Commodity.XLM, 8, PaymentCurrency.USD, 5),
    XLM_EUR(Commodity.XLM, 8, PaymentCurrency.EUR, 5),
    XLM_GBP(Commodity.XLM, 8, PaymentCurrency.GBP, 5);

    private Commodity commodity;

    private int commodityPrecision;

    private PaymentCurrency paymentCurrency;

    private int paymentPrecision;

    private String code;

    ExchangePair(Commodity commodity, int commodityPrecision, PaymentCurrency paymentCurrency, int paymentPrecision) {
        this.commodity = commodity;
        this.paymentCurrency = paymentCurrency;
        this.code = commodity.getCode() + paymentCurrency.getCode();
        this.commodityPrecision = commodityPrecision;
        this.paymentPrecision = paymentPrecision;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public PaymentCurrency getPaymentCurrency() {
        return paymentCurrency;
    }

    public String getCode() {
        return code;
    }

    public int getCommodityPrecision() {
        return commodityPrecision;
    }

    public int getPaymentPrecision() {
        return paymentPrecision;
    }

    public static boolean exists(String code) {
        for (ExchangePair ep : ExchangePair.values()) {
            if (code.equals(ep.getCode())) {
                return true;
            }
        }
        return false;
    }

    public static ExchangePair valueFromCode(String code) {
        for (ExchangePair ep : ExchangePair.values()) {
            if (code.equals(ep.getCode())) {
                return ep;
            }
        }
        throw new IllegalArgumentException("Cannot find ExchangePair for code: " + code);
    }

    public static Set<ExchangePair> valuesForPaymentCurrency(PaymentCurrency paymentCurrency) {
        Set<ExchangePair> results = new HashSet<>();
        for (ExchangePair ep : ExchangePair.values()) {
            if (paymentCurrency == ep.paymentCurrency) {
                results.add(ep);
            }
        }
        return results;
    }
}
