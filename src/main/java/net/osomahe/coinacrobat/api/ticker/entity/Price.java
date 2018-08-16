package net.osomahe.coinacrobat.api.ticker.entity;

import java.io.Serializable;


/**
 * Object for storing ask and bid price.
 *
 * @author Antonin Stoklasek
 */
public class Price implements Serializable {

    public static final long serialVersionUID = -1L;

    private Double ask;

    private Double bid;

    public Price() {
    }

    public Price(Double ask, Double bid) {
        this.ask = ask;
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public Double getBid() {
        return bid;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "Price{" +
                "ask=" + ask +
                ", bid=" + bid +
                '}';
    }
}
