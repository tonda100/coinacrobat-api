package net.osomahe.coinacrobat.api.exchange.entity;


/**
 * Common interface to retrieve code from {@link Commodity} and {@link PaymentCurrency}.
 *
 * @author Antonin Stoklasek
 */
public interface Asset {

    /**
     * This method provides code of given asset.
     *
     * @return - asset's code
     */
    String getCode();
}
