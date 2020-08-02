package net.osomahe.coinacrobat.smartapi;

import net.osomahe.coinacrobat.smartapi.catalog.CatalogService;
import net.osomahe.coinacrobat.smartapi.catalog.Catalogable;
import net.osomahe.coinacrobat.smartapi.catalog.Currency;
import net.osomahe.coinacrobat.smartapi.catalog.ExchangePair;
import net.osomahe.coinacrobat.smartapi.price.Price;
import net.osomahe.coinacrobat.smartapi.price.PriceRequest;
import net.osomahe.coinacrobat.smartapi.price.PriceService;
import net.osomahe.coinacrobat.smartapi.price.TechnicalAnalysis;
import net.osomahe.coinacrobat.smartapi.trade.TradeBuilder;
import net.osomahe.coinacrobat.smartapi.trade.TradeRequest;
import net.osomahe.coinacrobat.smartapi.trade.Transaction;

import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class SmartStrategyScript {

    protected ZonedDateTime now;

    // frequency of strategy ticks
    protected StrategyFrequency frequency;

    // storage which survives the ticks
    protected Map<String, String> storage;

    // account balances
    protected Wallet wallet;

    private PriceService servicePrice;

    private CatalogService serviceCatalog;

    private boolean caching;

    private List<LogRecord> resultLogs = new ArrayList<>();

    private List<GraphRecord> resultGraphs = new ArrayList<>();

    private List<Transaction> resultTransactions = new ArrayList<>();

    protected SmartStrategyScript() {
    }

    protected abstract void tick();

    public final TickResult proceedTick() {
        tick();
        return new TickResult.Builder()
                .withNow(now)
                .withFrequency(frequency)
                .withStorage(storage)
                .withWallet(wallet)
                .withLogs(resultLogs)
                .withGraphs(resultGraphs)
                .withTransactions(resultTransactions.stream().filter(Transaction::isProcessed).collect(Collectors.toList()))
                .build();
    }

    protected final TradeBuilder.Sell sell(Amount amount) {
        TradeRequest tradeRequest = new TradeRequest(now, wallet, caching);
        return new TradeBuilder.Sell(amount, servicePrice, tradeRequest, resultTransactions);
    }

    protected final TradeBuilder.Receive receive(Amount amount) {
        return new TradeBuilder.Receive(amount);
    }


    protected <T extends Catalogable> List<T> getCatalogValues(Class<T> tClass) {
        return serviceCatalog.getCatalogValues(tClass);
    }

    /**
     * Method for logging message from a strategy.
     *
     * @param level  level of severity of the message
     * @param msg    message
     * @param params parameters of the message
     */
    protected final void log(LogLevel level, String msg, Object... params) {
        resultLogs.add(new LogRecord(level, String.format(msg, params)));
    }

    /**
     * By default, X-axis is a timeline, so the value will be plotted as Y-axis value on the current time
     *
     * @param graphName defines the name of the graph. All values with the same name will be plotted to the same graph
     * @param value     to be plotted
     */
    public final void plot(String graphName, Double value) {
        resultGraphs.add(new GraphRecord(graphName, value));
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
        return getPrice(exchangePair.getCode());
    }

    /**
     * Method for the most recent price of given exchange pair.
     *
     * @param exchangePairCode exchange pair which price you need to receive
     * @return current price of given exchange pair
     */
    protected final Price getPrice(String exchangePairCode) {
        return servicePrice.getPrice(
                new PriceRequest.Builder()
                        .withExchangePair(exchangePairCode)
                        .withTimestamp(now)
                        .withCaching(caching)
                        .withWallet(wallet)
                        .build()
        );
    }

    protected final Price getMaximum(ExchangePair exchangePair, TemporalAmount temporalAmount) {
        return getMaximum(exchangePair.getCode(), temporalAmount);
    }

    protected final Price getMaximum(String exchangePairCode, TemporalAmount temporalAmount) {
        return servicePrice.getMaximum(
                new PriceRequest.Builder()
                        .withExchangePair(exchangePairCode)
                        .withTimestamp(now)
                        .withCaching(caching)
                        .withWallet(wallet)
                        .build(),
                temporalAmount
        );
    }

    protected final Price getMinimum(ExchangePair exchangePair, TemporalAmount temporalAmount) {
        return getMinimum(exchangePair.getCode(), temporalAmount);
    }

    protected final Price getMinimum(String exchangePairCode, TemporalAmount temporalAmount) {
        return servicePrice.getMinimum(
                new PriceRequest.Builder()
                        .withExchangePair(exchangePairCode)
                        .withTimestamp(now)
                        .withCaching(caching)
                        .withWallet(wallet)
                        .build(),
                temporalAmount
        );
    }

    protected final Price getAverage(ExchangePair exchangePair, TemporalAmount temporalAmount) {
        return getAverage(exchangePair.getCode(), temporalAmount);
    }

    protected final Price getAverage(String exchangePairCode, TemporalAmount temporalAmount) {
        return servicePrice.getAverage(
                new PriceRequest.Builder()
                        .withExchangePair(exchangePairCode)
                        .withTimestamp(now)
                        .withCaching(caching)
                        .withWallet(wallet)
                        .build(),
                temporalAmount
        );
    }

    protected final Price getStandardDeviation(ExchangePair exchangePair, TemporalAmount temporalAmount) {
        return getStandardDeviation(exchangePair.getCode(), temporalAmount);
    }

    protected final Price getStandardDeviation(String exchangePairCode, TemporalAmount temporalAmount) {
        return servicePrice.getStandardDeviation(
                new PriceRequest.Builder()
                        .withExchangePair(exchangePairCode)
                        .withTimestamp(now)
                        .withCaching(caching)
                        .withWallet(wallet)
                        .build(),
                temporalAmount
        );
    }

    /**
     * Method for receiving technical analysis data for given exchange pair and time and with give precision count.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param count        how many previous data is take to computation
     * @return object of technical analysis
     */
    protected final TechnicalAnalysis getTechnicalAnalysis(ExchangePair exchangePair, Integer count) {
        return getTechnicalAnalysis(exchangePair.getCode(), count);
    }

    /**
     * Method for receiving technical analysis data for given exchange pair and time and with give precision count.
     *
     * @param exchangePairCode exchange pair which prices you need to receive
     * @param count            how many previous data is take to computation
     * @return object of technical analysis
     */
    protected final TechnicalAnalysis getTechnicalAnalysis(String exchangePairCode, Integer count) {
        return servicePrice.getTechnicalAnalysis(
                new PriceRequest.Builder()
                        .withExchangePair(exchangePairCode)
                        .withTimestamp(now)
                        .withCaching(caching)
                        .withWallet(wallet)
                        .build(),
                count
        );
    }


    protected final Price getPercentile(ExchangePair exchangePair, TemporalAmount temporalAmount, Double percentile) {
        return null;
    }


    protected final Price getPercentile(String exchangePairCode, TemporalAmount temporalAmount, Double percentile) {
        return null;
    }
}
