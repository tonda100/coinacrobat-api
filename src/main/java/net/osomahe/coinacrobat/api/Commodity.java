package net.osomahe.coinacrobat.api;

public class Commodity extends Currency {


    public static Commodity of(String code) {
        return null;
    }

    @Override
    public Catalog getType() {
        return Catalog.COMMODITY;
    }
}
