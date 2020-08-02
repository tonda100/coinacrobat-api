package net.osomahe.coinacrobat.smartapi;

import net.osomahe.coinacrobat.smartapi.trade.Transaction;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class TickResult {

    private ZonedDateTime now;

    private StrategyFrequency frequency;

    private Map<String, String> storage;

    private Wallet wallet;

    private List<LogRecord> logs;

    private List<GraphRecord> graphs;

    private List<Transaction> transactions;


    private TickResult() {
    }

    public ZonedDateTime getNow() {
        return now;
    }

    public StrategyFrequency getFrequency() {
        return frequency;
    }

    public Map<String, String> getStorage() {
        return storage;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public List<LogRecord> getLogs() {
        return logs;
    }

    public List<GraphRecord> getGraphs() {
        return graphs;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TickResult.class.getSimpleName() + "[", "]")
                .add("now=" + now)
                .add("frequency=" + frequency)
                .add("storage=" + storage)
                .add("wallet=" + wallet)
                .add("logs=" + logs)
                .add("graphs=" + graphs)
                .add("transactions=" + transactions)
                .toString();
    }

    public static final class Builder {
        private ZonedDateTime now;
        private StrategyFrequency frequency;
        private Map<String, String> storage;
        private Wallet wallet;
        private List<LogRecord> logs;
        private List<GraphRecord> graphs;
        private List<Transaction> transactions;

        public Builder withNow(ZonedDateTime now) {
            this.now = now;
            return this;
        }

        public Builder withFrequency(StrategyFrequency frequency) {
            this.frequency = frequency;
            return this;
        }

        public Builder withStorage(Map<String, String> storage) {
            this.storage = storage;
            return this;
        }

        public Builder withWallet(Wallet wallet) {
            this.wallet = wallet;
            return this;
        }

        public Builder withLogs(List<LogRecord> logs) {
            this.logs = logs;
            return this;
        }

        public Builder withGraphs(List<GraphRecord> graphs) {
            this.graphs = graphs;
            return this;
        }

        public Builder withTransactions(List<Transaction> transactions) {
            this.transactions = transactions;
            return this;
        }

        public TickResult build() {
            TickResult tickResult = new TickResult();
            tickResult.frequency = this.frequency;
            tickResult.storage = this.storage;
            tickResult.now = this.now;
            tickResult.logs = this.logs;
            tickResult.wallet = this.wallet;
            tickResult.graphs = this.graphs;
            tickResult.transactions = this.transactions;
            return tickResult;
        }
    }
}
