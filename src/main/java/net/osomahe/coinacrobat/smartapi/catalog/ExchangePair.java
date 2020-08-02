package net.osomahe.coinacrobat.smartapi.catalog;

import net.osomahe.coinacrobat.smartapi.Amount;

import java.util.Objects;
import java.util.StringJoiner;

public class ExchangePair implements Catalogable {

    public static ExchangePair of(String code) {
        return null;
    }

    private Currency commodity;

    private Integer decimalsCommodity;

    private Currency paymentCurrency;

    private Integer decimalsPaymentCurrency;

    private String description;

    private Boolean enabled;

    private Amount minimumOrder;

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

    public Integer getDecimalsCommodity() {
        return decimalsCommodity;
    }

    public void setDecimalsCommodity(Integer decimalsCommodity) {
        this.decimalsCommodity = decimalsCommodity;
    }

    public Currency getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(Currency paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    public Integer getDecimalsPaymentCurrency() {
        return decimalsPaymentCurrency;
    }

    public void setDecimalsPaymentCurrency(Integer decimalsPaymentCurrency) {
        this.decimalsPaymentCurrency = decimalsPaymentCurrency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Amount getMinimumOrder() {
        return minimumOrder;
    }

    public void setMinimumOrder(Amount minimumOrder) {
        this.minimumOrder = minimumOrder;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", ExchangePair.class.getSimpleName() + "[", "]")
                .add("code=" + getCode())
                .add("commodity=" + commodity)
                .add("decimalsCommodity=" + decimalsCommodity)
                .add("paymentCurrency=" + paymentCurrency)
                .add("decimalsPaymentCurrency=" + decimalsPaymentCurrency)
                .add("description='" + description + "'")
                .add("enabled=" + enabled)
                .add("minimumOrder=" + minimumOrder)
                .toString();
    }

    public boolean isGreaterThanMinimum(Amount amountSell, Amount amountReceive) {
        if (amountSell.getCurrencyCode().equals(minimumOrder.getCurrencyCode())) {
            return minimumOrder.getValue() <= amountSell.getValue();
        } else if (amountReceive.getCurrencyCode().equals(minimumOrder.getCurrencyCode())) {
            return minimumOrder.getValue() <= amountReceive.getValue();
        }
        return false;
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
