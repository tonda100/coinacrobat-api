package net.osomahe.coinacrobat.smartapi.catalog;

import java.util.Objects;

public class Commodity extends Currency {


    public Commodity(String code) {
        super(code);
    }

    public static Commodity of(String code) {
        return new Commodity(code);
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
