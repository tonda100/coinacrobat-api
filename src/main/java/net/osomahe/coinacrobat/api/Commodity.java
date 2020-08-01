package net.osomahe.coinacrobat.api;

public class Commodity extends Currency {

    @Override
    public Catalog getType() {
        return Catalog.COMMODITY;
    }
}
