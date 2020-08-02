package net.osomahe.coinacrobat.smartapi.price;

import net.osomahe.coinacrobat.smartapi.catalog.ExchangePair;

import java.time.ZonedDateTime;

public class Price {

    private ExchangePair exchangePair;

    private Double ask;

    private Double bid;

    private ZonedDateTime timestamp;

    public Double getAverage() {
        return (ask + bid) / 2;
    }

    public ExchangePair getExchangePair() {
        return exchangePair;
    }

    public void setExchangePair(ExchangePair exchangePair) {
        this.exchangePair = exchangePair;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
