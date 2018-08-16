package net.osomahe.coinacrobat.api.exchange.entity;


/**
 * Enumeration of all possible commodities.
 *
 * @author m.tkadlec
 */
public enum Commodity implements Asset {

    BTC("btc"),
    XRP("xrp"),
    LTC("ltc"),
    ETH("eth"),
    BCH("bch"),
    EUR("eur");

    private final String code;

    Commodity(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "code='" + code + '\'' +
                "} " + super.toString();
    }
}
