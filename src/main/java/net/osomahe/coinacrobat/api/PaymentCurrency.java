package net.osomahe.coinacrobat.api;

public class PaymentCurrency extends Currency {

    @Override
    public Catalog getType() {
        return Catalog.PAYMENT_CURRENCY;
    }
}
