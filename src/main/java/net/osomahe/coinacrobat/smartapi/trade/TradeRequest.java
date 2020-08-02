package net.osomahe.coinacrobat.smartapi.trade;

import net.osomahe.coinacrobat.smartapi.Wallet;

import java.time.ZonedDateTime;
import java.util.StringJoiner;

public class TradeRequest {

    private final ZonedDateTime timestamp;

    private final Wallet wallet;

    private final Boolean caching;

    public TradeRequest(ZonedDateTime timestamp, Wallet wallet, Boolean caching) {
        this.timestamp = timestamp;
        this.wallet = wallet;
        this.caching = caching;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Boolean getCaching() {
        return caching;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TradeRequest.class.getSimpleName() + "[", "]")
                .add("timestamp=" + timestamp)
                .add("wallet=" + wallet)
                .toString();
    }
}
