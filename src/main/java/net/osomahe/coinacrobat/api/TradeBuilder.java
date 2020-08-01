package net.osomahe.coinacrobat.api;

public class TradeBuilder {

    public static class Sell {

        public TransactionSummary to(Currency currency) {
            return to(currency.getCode());
        }

        public TransactionSummary to(String currencyCode) {
            return null;
        }
    }

    public static class Receive {

        public TransactionSummary from(Currency currency) {
            return from(currency.getCode());
        }

        public TransactionSummary from(String currencyCode) {
            return null;
        }
    }
}
