package net.osomahe.coinacrobat.api;

import java.util.StringJoiner;

public class Amount {

    private final Double amount;

    private final String currencyCode;

    public Amount(Double amount, String currencyCode) {
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public Amount(Integer amount, String currencyCode) {
        this(amount.doubleValue(), currencyCode);
    }

    public Amount(Double amount, Currency currency) {
        this(amount, currency.getCode());
    }

    public Amount(Integer amount, Currency currency) {
        this(amount.doubleValue(), currency.getCode());
    }

    public Double getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Amount.class.getSimpleName() + "[", "]")
                .add("amount=" + amount)
                .add("currencyCode='" + currencyCode + "'")
                .toString();
    }
}
