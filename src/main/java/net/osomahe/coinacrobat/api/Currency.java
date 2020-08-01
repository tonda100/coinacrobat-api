package net.osomahe.coinacrobat.api;

public class Currency implements Catalogable {

    @Override
    public Catalog getType() {
        return Catalog.CURRENCY;
    }

    @Override
    public String getCode() {
        return null;
    }
}
