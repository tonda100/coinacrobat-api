package net.osomahe.coinacrobat.api.exchange.entity;


import java.util.HashSet;
import java.util.Set;


/**
 * Enumeration of all possible combinations of {@link Commodity} and {@link PaymentCurrency}.
 *
 * @author Antonin Stoklasek
 */
public enum ExchangePair {
    BTC_USD(Commodity.BTC, PaymentCurrency.USD),
    BTC_EUR(Commodity.BTC, PaymentCurrency.EUR),
    BTC_GBP(Commodity.BTC, PaymentCurrency.GBP),
    GBP_USD(Commodity.GBP, PaymentCurrency.USD),
    GBP_EUR(Commodity.GBP, PaymentCurrency.EUR),
    EUR_USD(Commodity.EUR, PaymentCurrency.USD),
    XRP_USD(Commodity.XRP, PaymentCurrency.USD),
    XRP_EUR(Commodity.XRP, PaymentCurrency.EUR),
    XRP_BTC(Commodity.XRP, PaymentCurrency.BTC),
    XRP_GBP(Commodity.XRP, PaymentCurrency.GBP),
    LTC_USD(Commodity.LTC, PaymentCurrency.USD),
    LTC_EUR(Commodity.LTC, PaymentCurrency.EUR),
    LTC_BTC(Commodity.LTC, PaymentCurrency.BTC),
    LTC_GBP(Commodity.LTC, PaymentCurrency.GBP),
    ETH_USD(Commodity.ETH, PaymentCurrency.USD),
    ETH_EUR(Commodity.ETH, PaymentCurrency.EUR),
    ETH_BTC(Commodity.ETH, PaymentCurrency.BTC),
    ETH_GBP(Commodity.ETH, PaymentCurrency.GBP),
    BCH_USD(Commodity.BCH, PaymentCurrency.USD),
    BCH_EUR(Commodity.BCH, PaymentCurrency.EUR),
    BCH_BTC(Commodity.BCH, PaymentCurrency.BTC),
    BCH_GBP(Commodity.BCH, PaymentCurrency.GBP);

    private Commodity commodity;

    private PaymentCurrency paymentCurrency;

    private String code;

    ExchangePair(Commodity commodity, PaymentCurrency paymentCurrency) {
        this.commodity = commodity;
        this.paymentCurrency = paymentCurrency;
        this.code = commodity.getCode() + paymentCurrency.getCode();
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
