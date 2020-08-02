package net.osomahe.coinacrobat.smartapi.trade;

import net.osomahe.coinacrobat.smartapi.Amount;
import net.osomahe.coinacrobat.smartapi.catalog.Currency;
import net.osomahe.coinacrobat.smartapi.price.PriceService;

import java.util.List;

public class TradeBuilder {

    public static class Sell {
        private final Amount amountSell;
        private final PriceService servicePrice;
        private final TradeRequest tradeRequest;
        private final List<Transaction> bag;

        public Sell(Amount amountSell, PriceService servicePrice, TradeRequest tradeRequest, List<Transaction> bag) {
            this.amountSell = amountSell;
            this.servicePrice = servicePrice;
            this.tradeRequest = tradeRequest;
            this.bag = bag;
        }

        public Transaction to(Currency currency) {
            return to(currency.getCode());
        }

        public Transaction to(String currencyCode) {
            Transaction transaction = servicePrice.sell(amountSell, currencyCode, tradeRequest);
            bag.add(transaction);
            return transaction;
        }
    }

    public static class Receive {
        private final Amount amountReceive;

        public Receive(Amount amountReceive) {
            this.amountReceive = amountReceive;
        }

        public Transaction from(Currency currency) {
            return from(currency.getCode());
        }

        public Transaction from(String currencyCode) {
            return null;
        }
    }
}
