package net.osomahe.coinacrobat.api;

/**
 * Possible strategy frequency values with information about minutes duration.
 *
 * @author Antonin Stoklasek
 */
public enum StrategyFrequency {

    MINUTE(1), HOUR(60), DAY(60 * 24), HOURS12(60 * 12), HOURS8(60 * 8), HOURS6(60 * 6), HOURS4(60 * 4);

    private long mins;

    StrategyFrequency(long mins) {
        this.mins = mins;
    }

    public long getMins() {
        return mins;
    }
}
