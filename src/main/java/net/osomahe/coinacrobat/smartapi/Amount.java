package net.osomahe.coinacrobat.smartapi;

import net.osomahe.coinacrobat.smartapi.catalog.Currency;

import java.util.StringJoiner;

public class Amount {

    private final Double value;

    private final String currencyCode;

    public Amount(Double value, String currencyCode) {
        this.value = value;
        this.currencyCode = currencyCode;
    }

    public Amount(Integer value, String currencyCode) {
        this(value.doubleValue(), currencyCode);
    }

    public Amount(Double value, Currency currency) {
        this(value, currency.getCode());
    }

    public Amount(Integer value, Currency currency) {
        this(value.doubleValue(), currency.getCode());
    }

    public Double getValue() {
        return value;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Amount.class.getSimpleName() + "[", "]")
                .add("amount=" + value)
                .add("currencyCode='" + currencyCode + "'")
                .toString();
    }
}
