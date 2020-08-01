package net.osomahe.coinacrobat.api;

public class ExchangePair implements Catalogable {

    private Currency commodity;

    private Currency paymentCurrency;

    @Override
    public Catalog getType() {
        return Catalog.EXCHANGE_PAIR;
    }

    @Override
    public String getCode() {
        return commodity.getCode() + paymentCurrency.getCode();
    }

    public Currency getCommodity() {
        return commodity;
    }

    public void setCommodity(Currency commodity) {
        this.commodity = commodity;
    }

    public Currency getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(Currency paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }
}
