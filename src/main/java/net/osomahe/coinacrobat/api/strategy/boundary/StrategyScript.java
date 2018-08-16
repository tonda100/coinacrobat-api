package net.osomahe.coinacrobat.api.strategy.boundary;

import java.time.ZonedDateTime;
import java.util.Map;

import net.osomahe.coinacrobat.api.exchange.boundary.Trader;
import net.osomahe.coinacrobat.api.exchange.boundary.Wallet;
import net.osomahe.coinacrobat.api.graph.boundary.Graphs;
import net.osomahe.coinacrobat.api.logger.boundary.Logger;
import net.osomahe.coinacrobat.api.strategy.entity.StrategyFrequency;
import net.osomahe.coinacrobat.api.ticker.boundary.Ticker;


/**
 * Common parent of Salary Robot strategies.
 *
 * @author m.tkadlec
 */
public abstract class StrategyScript {

    // frequency of strategy ticks
    protected StrategyFrequency frequency;

    // storage which survives the ticks
    protected Map storage;

    // buy and selling commodity
    protected Trader trader;

    // account balances
    protected Wallet wallet;

    // provide ticker of commodity
    protected Ticker ticker;

    // logging the run
    protected Logger log;

    // graph drawing
    protected Graphs graphs;

    protected ZonedDateTime now = ZonedDateTime.now();

    /**
     * method called when the strategy is starting for the first time within the
     * plan
     */
    public abstract void init();

    /**
     * method called regularly based on the strategy plan frequency
     */
    public abstract void tick();

    /**
     * method called on strategy plan finish
     */
    public abstract void stop();

    public void setNow(ZonedDateTime now) {
        this.now = now;
    }

    public void setStorage(Map storage) {
        this.storage = storage;
    }

    public void setLog(Logger log) {
        this.log = log;
    }

    public void setGraphs(Graphs graphs) {
        this.graphs = graphs;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public void setFrequency(StrategyFrequency frequency) {
        this.frequency = frequency;
    }
}
