package net.osomahe.coinacrobat.api.strategy.entity;

/**
 * Pissible strategy frequency values with information about minutes duration.
 *
 * @author Antonin Stoklasek
 */
public enum StrategyFrequency {

    MINUTE(1), HOUR(60), DAY(60 * 24);

    private long mins;

    StrategyFrequency(long mins) {
        this.mins = mins;
    }

    public long getMins() {
        return mins;
    }
}
