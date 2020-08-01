package net.osomahe.coinacrobat.api;

public class Currency implements Catalogable {

    public static Currency of(String code) {
        return null;
    }

    @Override
    public Catalog getType() {
        return Catalog.CURRENCY;
    }

    @Override
    public String getCode() {
        return null;
    }
}
