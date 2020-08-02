package net.osomahe.coinacrobat.smartapi.price;

import net.osomahe.coinacrobat.smartapi.Wallet;

import java.time.ZonedDateTime;
import java.util.StringJoiner;

public class PriceRequest {

    private String exchangePair;

    private ZonedDateTime timestamp;

    private Boolean caching;

    private Wallet wallet;

    private PriceRequest() {
    }

    public String getExchangePair() {
        return exchangePair;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public Boolean getCaching() {
        return caching;
    }

    public Wallet getWallet() {
        return wallet;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PriceRequest.class.getSimpleName() + "[", "]")
                .add("exchangePair=" + exchangePair)
                .add("timestamp=" + timestamp)
                .add("caching=" + caching)
                .add("wallet=" + wallet)
                .toString();
    }


    public static final class Builder {
        private String exchangePair;
        private ZonedDateTime timestamp;
        private Boolean caching;
        private Wallet wallet;

        public Builder() {
        }

        public Builder withExchangePair(String exchangePair) {
            this.exchangePair = exchangePair;
            return this;
        }

        public Builder withTimestamp(ZonedDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withCaching(Boolean caching) {
            this.caching = caching;
            return this;
        }

        public Builder withWallet(Wallet wallet) {
            this.wallet = wallet;
            return this;
        }

        public PriceRequest build() {
            PriceRequest priceRequest = new PriceRequest();
            priceRequest.exchangePair = this.exchangePair;
            priceRequest.timestamp = this.timestamp;
            priceRequest.wallet = this.wallet;
            priceRequest.caching = this.caching;
            return priceRequest;
        }
    }
}
