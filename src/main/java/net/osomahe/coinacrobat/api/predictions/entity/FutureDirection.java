package net.osomahe.coinacrobat.api.predictions.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


/**
 * TODO write JavaDoc
 *
 * @author Antonin Stoklasek
 */
public class FutureDirection {

    private final double decline;

    private final double stable;

    private final double grow;

    public FutureDirection(double decline, double stable, double grow) {
        this.decline = decline;
        this.stable = stable;
        this.grow = grow;
    }

    public FutureDirection(double[] values) {
        this(values[0], values[1], values[2]);
    }

    public FutureDirection(List<BigDecimal> values) {
        this(values.get(0).doubleValue(), values.get(1).doubleValue(), values.get(2).doubleValue());
    }

    public double getDecline() {
        return decline;
    }

    public double getStable() {
        return stable;
    }

    public double getGrow() {
        return grow;
    }

    @Override
    public String toString() {
        return "FutureDirection{" +
                "decline=" + decline +
                ", stable=" + stable +
                ", grow=" + grow +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        FutureDirection that = (FutureDirection) o;
        return Double.compare(that.decline, decline) == 0 &&
                Double.compare(that.stable, stable) == 0 &&
                Double.compare(that.grow, grow) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(decline, stable, grow);
    }
}
