package net.osomahe.coinacrobat.api.ticker.entity;

import java.time.ZonedDateTime;


/**
 * Object for storing price with timestamp information.
 *
 * @author Antonin Stoklasek
 */
public class TimestampPrice extends Price implements Comparable<TimestampPrice> {

    public static final long serialVersionUID = -1L;

    private ZonedDateTime timestamp;

    public TimestampPrice() {

    }

    public TimestampPrice(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TimestampPrice(ZonedDateTime timestamp, Price price) {
        super(price.getAsk(), price.getBid());
        this.timestamp = timestamp;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TimestampPrice{" +
                "timestamp=" + timestamp +
                "} " + super.toString();
    }

    @Override
    public int compareTo(TimestampPrice tp) {
        return timestamp.compareTo(tp.timestamp);
    }
}
