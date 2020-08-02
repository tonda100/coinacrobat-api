package net.osomahe.coinacrobat.smartapi.trade;

import net.osomahe.coinacrobat.smartapi.Amount;
import net.osomahe.coinacrobat.smartapi.catalog.ExchangePair;
import net.osomahe.coinacrobat.smartapi.price.Price;

import java.util.StringJoiner;

public class Transaction {

    private Amount sell;

    private Amount receive;

    private Amount fee;

    private ExchangePair exchangePair;

    private Double tradePrice;

    private Price price;

    // flag whether transaction pass all validation
    private boolean success = false;

    // flag whether transaction was processed or not
    private boolean processed = false;

    private String note;

    private Transaction() {

    }

    private Transaction(String note, Transaction transaction) {

    }

    public Transaction withNote(String note) {
        return new Transaction(note, this);
    }

    public Transaction proceed() {
        if (!success) {
            throw new IllegalStateException("Cannot process transaction: " + this);
        }
        processed = true;
        return this;
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

    public ExchangePair getExchangePair() {
        return exchangePair;
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

    public String getNote() {
        return note;
    }

    public boolean isProcessed() {
        return processed;
    }

    public static final class Builder {
        private Amount sell;
        private Amount receive;
        private Amount fee;
        private ExchangePair exchangePair;
        private Double tradePrice;
        private Price price;
        // flag whether transaction pass all validation
        private boolean success;

        public Builder withSell(Amount sell) {
            this.sell = sell;
            return this;
        }

        public Builder withReceive(Amount receive) {
            this.receive = receive;
            return this;
        }

        public Builder withFee(Amount fee) {
            this.fee = fee;
            return this;
        }

        public Builder withExchangePair(ExchangePair exchangePair) {
            this.exchangePair = exchangePair;
            return this;
        }

        public Builder withTradePrice(Double tradePrice) {
            this.tradePrice = tradePrice;
            return this;
        }

        public Builder withPrice(Price price) {
            this.price = price;
            return this;
        }

        public Builder withSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public Transaction build() {
            Transaction transaction = new Transaction();
            transaction.exchangePair = this.exchangePair;
            transaction.tradePrice = this.tradePrice;
            transaction.sell = this.sell;
            transaction.fee = this.fee;
            transaction.success = this.success;
            transaction.price = this.price;
            transaction.receive = this.receive;
            return transaction;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Transaction.class.getSimpleName() + "[", "]")
                .add("sell=" + sell)
                .add("receive=" + receive)
                .add("fee=" + fee)
                .add("exchangePair=" + exchangePair)
                .add("tradePrice=" + tradePrice)
                .add("price=" + price)
                .add("success=" + success)
                .add("processed=" + processed)
                .add("note='" + note + "'")
                .toString();
    }
}
