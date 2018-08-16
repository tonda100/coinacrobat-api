/**
 *
 */
package net.osomahe.coinacrobat.api.exchange.boundary;

import net.osomahe.coinacrobat.api.exchange.entity.ExchangePair;


/**
 * The interface for buying and selling market stuff.
 *
 * @author m.tkadlec
 */
public interface Trader {


    /**
     * This method buys an amount of commodity units for market ticker.
     *
     * @param exchangePair exchange pair for buying commodity
     * @param commodityUnits - how much commodity want a user buy
     */
    void buyCommodity(ExchangePair exchangePair, Double commodityUnits);


    /**
     * This method sells an amount of commodity units for market ticker.
     *
     * @param exchangePair exchange pair for selling commodity
     * @param commodityUnits - how much commodity want a user sell
     */
    void sellCommodity(ExchangePair exchangePair, Double commodityUnits);

}
