package net.osomahe.coinacrobat.api;

public class PaymentCurrency extends Currency {

    public static PaymentCurrency of(String code) {
        return null;
    }

    @Override
    public Catalog getType() {
        return Catalog.PAYMENT_CURRENCY;
    }
}
