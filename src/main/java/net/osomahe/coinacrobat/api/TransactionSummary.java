package net.osomahe.coinacrobat.api;

import java.util.StringJoiner;

public class TransactionSummary {

    private Amount sell;

    private Amount receive;

    private Amount fee;

    private Amount minimum;

    private Double tradePrice;

    private Price price;

    private boolean success = false;

    private String note;

    public TransactionSummary() {

    }

    public TransactionSummary(String note, TransactionSummary transactionSummary) {

    }

    protected TransactionSummary withNote(String note) {
        return new TransactionSummary(note, this);
    }

    public TransactionSummary proceed() throws TradeException {
        return null;
    }

    public Amount getSell() {
        return sell;
    }

    public Amount getReceive() {
        return receive;
    }

    public Amount getFee() {
        return fee;
    }

    public Amount getMinimum() {
        return minimum;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public Price getPrice() {
        return price;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TransactionSummary.class.getSimpleName() + "[", "]")
                .add("sell=" + sell)
                .add("receive=" + receive)
                .add("fee=" + fee)
                .add("minimum=" + minimum)
                .add("tradePrice=" + tradePrice)
                .add("price=" + price)
                .add("success=" + success)
                .add("note='" + note + "'")
                .toString();
    }
}
