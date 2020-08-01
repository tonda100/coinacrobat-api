package net.osomahe.coinacrobat.api;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Map;

public abstract class StrategyScript {

    private ZonedDateTime now;

    // frequency of strategy ticks
    protected StrategyFrequency frequency;

    // storage which survives the ticks
    protected Map<String, String> storage;

    // account balances
    protected Wallet wallet;

    protected abstract void tick();

    public final TickResult proceedTick(ZonedDateTime now) {
        this.now = now;
        tick();
        return new TickResult();
    }

    protected final TradeBuilder.Sell sell(Amount amount) {
        return null;
    }

    protected final TradeBuilder.Receive receive(Amount amount) {
        return null;
    }


    protected <T extends Catalogable> List<T> getCatalogValues(Class<T> tClass) {
        return null;
    }

    /**
     * Method for logging message from a strategy.
     *
     * @param level  level of severity of the message
     * @param msg    message
     * @param params parameters of the message
     */
    protected final void log(LogLevel level, String msg, Object... params) {

    }

    /**
     * By default, X-axis is a timeline, so the value will be plotted as Y-axis value on the current time
     *
     * @param graphName defines the name of the graph. All values with the same name will be plotted to the same graph
     * @param value     to be plotted
     */
    public final void plot(String graphName, Double value) {

    }

    protected final TradeBuilder.Sell sell(Integer amount, Currency currency) {
        return sell(new Amount(amount.doubleValue(), currency));
    }

    protected final TradeBuilder.Sell sell(Double amount, Currency currency) {
        return sell(new Amount(amount, currency));
    }

    protected final TradeBuilder.Sell sell(Integer amount, String currencyCode) {
        return sell(new Amount(amount.doubleValue(), currencyCode));
    }

    protected final TradeBuilder.Sell sell(Double amount, String currencyCode) {
        return sell(new Amount(amount, currencyCode));
    }

    protected final TradeBuilder.Receive receive(Integer amount, Currency currency) {
        return receive(new Amount(amount.doubleValue(), currency));
    }

    protected final TradeBuilder.Receive receive(Double amount, Currency currency) {
        return receive(new Amount(amount, currency));
    }

    protected final TradeBuilder.Receive receive(Integer amount, String currencyCode) {
        return receive(new Amount(amount.doubleValue(), currencyCode));
    }

    protected final TradeBuilder.Receive receive(Double amount, String currencyCode) {
        return receive(new Amount(amount, currencyCode));
    }

    /**
     * Method for debug message.
     *
     * @param msg    message
     * @param params parameters of the message
     */
    protected final void debug(String msg, Object... params) {
        log(LogLevel.DEBUG, msg, params);
    }

    /**
     * Method for info message.
     *
     * @param msg    message
     * @param params parameters of the message
     */
    protected final void info(String msg, Object... params) {
        log(LogLevel.INFO, msg, params);
    }

    /**
     * Method for warn message.
     *
     * @param msg    message
     * @param params parameters of the message
     */
    protected final void warn(String msg, Object... params) {
        log(LogLevel.WARN, msg, params);
    }

    /**
     * Method for error message.
     *
     * @param msg    message
     * @param params parameters of the message
     */
    protected final void error(String msg, Object... params) {
        log(LogLevel.ERROR, msg, params);
    }

    /**
     * Method for the most recent price of given exchange pair.
     *
     * @param exchangePair exchange pair which price you need to receive
     * @return current price of given exchange pair
     */
    protected final Price getPrice(ExchangePair exchangePair) {
        return null;
    }

    /**
     * Method for the most recent price of given exchange pair.
     *
     * @param exchangePairCode exchange pair which price you need to receive
     * @return current price of given exchange pair
     */
    protected final Price getPrice(String exchangePairCode) {
        return null;
    }

    protected final Price getMaximum(ExchangePair exchangePair, TemporalAmount temporalAmount) {
        return null;
    }

    protected final Price getMaximum(String exchangePairCode, TemporalAmount temporalAmount) {
        return null;
    }

    protected final Price getMinimum(ExchangePair exchangePair, TemporalAmount temporalAmount) {
        return null;
    }

    protected final Price getMinimum(String exchangePairCode, TemporalAmount temporalAmount) {
        return null;
    }

    protected final Double getAverage(ExchangePair exchangePair, TemporalAmount temporalAmount) {
        return null;
    }

    protected final Double getAverage(String exchangePairCode, TemporalAmount temporalAmount) {
        return null;
    }

    protected final Double getStandardDeviation(ExchangePair exchangePair, TemporalAmount temporalAmount) {
        return null;
    }

    protected final Double getStandardDeviation(String exchangePairCode, TemporalAmount temporalAmount) {
        return null;
    }

    /**
     * Method for receiving technical analysis data for given exchange pair and time and with give precision count.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param count        how many previous data is take to computation
     * @return object of technical analysis
     */
    protected final TechnicalAnalysis getTechnicalAnalysis(ExchangePair exchangePair, Integer count) {
        return null;
    }

    /**
     * Method for receiving technical analysis data for given exchange pair and time and with give precision count.
     *
     * @param exchangePairCode exchange pair which prices you need to receive
     * @param count            how many previous data is take to computation
     * @return object of technical analysis
     */
    protected final TechnicalAnalysis getTechnicalAnalysis(String exchangePairCode, Integer count) {
        return null;
    }


    protected final Double getPercentile(ExchangePair exchangePair, TemporalAmount temporalAmount, Double percentile) {
        return null;
    }


    protected final Double getPercentile(String exchangePairCode, TemporalAmount temporalAmount, Double percentile) {
        return null;
    }
}
