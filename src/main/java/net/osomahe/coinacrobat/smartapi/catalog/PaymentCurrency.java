package net.osomahe.coinacrobat.smartapi.catalog;

import java.util.Objects;

public class PaymentCurrency extends Currency {

    public PaymentCurrency(String code) {
        super(code);
    }

    public static PaymentCurrency of(String code) {
        return new PaymentCurrency(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return getCode().equals(currency.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }
}
