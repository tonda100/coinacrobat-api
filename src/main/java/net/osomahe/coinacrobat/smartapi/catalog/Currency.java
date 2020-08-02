package net.osomahe.coinacrobat.smartapi.catalog;

import java.util.Objects;

public class Currency implements Catalogable {

    private final String code;

    public Currency(String code) {
        this.code = code;
    }

    public static Currency of(String code) {
        return new Currency(code);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return code.equals(currency.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
