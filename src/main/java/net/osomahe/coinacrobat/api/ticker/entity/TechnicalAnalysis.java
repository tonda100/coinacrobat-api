package net.osomahe.coinacrobat.api.ticker.entity;

import java.util.StringJoiner;


/**
 * Technical analysis indicators "Relative Strength Index", "Chande Momentum Oscillator" and "Triple Exponential Average"
 * for bid and ask prices.
 *
 * @author Antonin Stoklasek
 */
public class TechnicalAnalysis {

    /**
     * Relative Strength Index ASK
     */
    private Double rsiAsk;

    /**
     * Relative Strength Index BID
     */
    private Double rsiBid;

    /**
     * Chande Momentum Oscillator ASK
     */
    private Double cmoAsk;

    /**
     * Chande Momentum Oscillator BID
     */
    private Double cmoBid;

    /**
     * Triple Exponential Average ASK
     */
    private Double trixAsk;

    /**
     * Triple Exponential Average BID
     */
    private Double trixBid;

    public Double getRsiAsk() {
        return rsiAsk;
    }

    public void setRsiAsk(Double rsiAsk) {
        this.rsiAsk = rsiAsk;
    }

    public Double getRsiBid() {
        return rsiBid;
    }

    public void setRsiBid(Double rsiBid) {
        this.rsiBid = rsiBid;
    }

    public Double getCmoAsk() {
        return cmoAsk;
    }

    public void setCmoAsk(Double cmoAsk) {
        this.cmoAsk = cmoAsk;
    }

    public Double getCmoBid() {
        return cmoBid;
    }

    public void setCmoBid(Double cmoBid) {
        this.cmoBid = cmoBid;
    }

    public Double getTrixAsk() {
        return trixAsk;
    }

    public void setTrixAsk(Double trixAsk) {
        this.trixAsk = trixAsk;
    }

    public Double getTrixBid() {
        return trixBid;
    }

    public void setTrixBid(Double trixBid) {
        this.trixBid = trixBid;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TechnicalAnalysis.class.getSimpleName() + "[", "]")
                .add("rsiAsk=" + rsiAsk)
                .add("rsiBid=" + rsiBid)
                .add("cmoAsk=" + cmoAsk)
                .add("cmoBid=" + cmoBid)
                .add("trixAsk=" + trixAsk)
                .add("trixBid=" + trixBid)
                .toString();
    }
}
