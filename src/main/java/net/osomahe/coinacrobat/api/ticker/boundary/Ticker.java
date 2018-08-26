package net.osomahe.coinacrobat.api.ticker.boundary;

import java.time.ZonedDateTime;
import java.util.TreeSet;

import net.osomahe.coinacrobat.api.exchange.entity.ExchangePair;
import net.osomahe.coinacrobat.api.ticker.entity.Price;
import net.osomahe.coinacrobat.api.ticker.entity.TimestampPrice;


/**
 * Interface for retrieving price information.
 *
 * @author Antonin Stoklasek
 */
public interface Ticker {

    /**
     * Method for the most recent price of given exchange pair.
     *
     * @param exchangePair exchange pair which price you need to receive
     * @return current price of given exchange pair
     */
    Price getLatest(ExchangePair exchangePair);

    /**
     * Method for price at given time and specific exchange pair.
     *
     * @param exchangePair exchange pair which price you need to receive
     * @param dateTime time of the price
     * @return price of exchange pair for given time
     */
    Price getPrice(ExchangePair exchangePair, ZonedDateTime dateTime);

    /**
     * Method for receiving set of all price between given from time and now.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of price's set
     * @return set of prices
     */
    TreeSet<TimestampPrice> getLast(ExchangePair exchangePair, ZonedDateTime from);

    /**
     * Method for receiving set of all price between given "from" time and and "to" time.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of price's set
     * @param to the to border of price's set
     * @return set of prices
     */
    TreeSet<TimestampPrice> getAll(ExchangePair exchangePair, ZonedDateTime from, ZonedDateTime to);

    /**
     * Method for receiving maximum ask price for given exchange pair between given from and now.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @return TimestampPrice with maximum ask value
     */
    TimestampPrice getMaximumAsk(ExchangePair exchangePair, ZonedDateTime from);

    /**
     * Method for receiving maximum ask price for given exchange pair between given from and to.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @param to the to border of search
     * @return TimestampPrice with maximum ask value
     */
    TimestampPrice getMaximumAsk(ExchangePair exchangePair, ZonedDateTime from, ZonedDateTime to);

    /**
     * Method for receiving maximum bid price for given exchange pair between given from and now.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @return TimestampPrice with maximum bid value
     */
    TimestampPrice getMaximumBid(ExchangePair exchangePair, ZonedDateTime from);

    /**
     * Method for receiving maximum bid price for given exchange pair between given from and to.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @param to the to border of search
     * @return TimestampPrice with maximum bid value
     */
    TimestampPrice getMaximumBid(ExchangePair exchangePair, ZonedDateTime from, ZonedDateTime to);

    /**
     * Method for receiving minimum ask price for given exchange pair between given from and now.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @return TimestampPrice with minimum ask value
     */
    TimestampPrice getMinimumAsk(ExchangePair exchangePair, ZonedDateTime from);

    /**
     * Method for receiving minimum ask price for given exchange pair between given from and to.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @param to the to border of search
     * @return TimestampPrice with minimum ask value
     */
    TimestampPrice getMinimumAsk(ExchangePair exchangePair, ZonedDateTime from, ZonedDateTime to);

    /**
     * Method for receiving minimum bid price for given exchange pair between given from and now.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @return TimestampPrice with minimum bid value
     */
    TimestampPrice getMinimumBid(ExchangePair exchangePair, ZonedDateTime from);

    /**
     * Method for receiving minimum bid price for given exchange pair between given from and to.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @param to the to border of search
     * @return TimestampPrice with minimum bid value
     */
    TimestampPrice getMinimumBid(ExchangePair exchangePair, ZonedDateTime from, ZonedDateTime to);

    /**
     * Method for receiving average ask price for given exchange pair between given from and now.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @return average ask price for period
     */
    Double getAverageAsk(ExchangePair exchangePair, ZonedDateTime from);

    /**
     * Method for receiving average ask price for given exchange pair between given from and to.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @param to the to border of search
     * @return average ask price for period
     */
    Double getAverageAsk(ExchangePair exchangePair, ZonedDateTime from, ZonedDateTime to);

    /**
     * Method for receiving average bid price for given exchange pair between given from and now.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @return average bid price for period
     */
    Double getAverageBid(ExchangePair exchangePair, ZonedDateTime from);

    /**
     * Method for receiving average bid price for given exchange pair between given from and to.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @param to the to border of search
     * @return average bid price for period
     */
    Double getAverageBid(ExchangePair exchangePair, ZonedDateTime from, ZonedDateTime to);

    /**
     * Method for receiving standard deviation of ask price for given exchange pair between given from and now.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @return average bid price for period
     */
    Double getStandardDeviationAsk(ExchangePair exchangePair, ZonedDateTime from);

    /**
     * Method for receiving standard deviation of ask price for given exchange pair between given from and to.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @param to the to border of search
     * @return average bid price for period
     */
    Double getStandardDeviationAsk(ExchangePair exchangePair, ZonedDateTime from, ZonedDateTime to);

    /**
     * Method for receiving standard deviation of bid price for given exchange pair between given from and now.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @return average bid price for period
     */
    Double getStandardDeviationBid(ExchangePair exchangePair, ZonedDateTime from);

    /**
     * Method for receiving standard deviation of bid price for given exchange pair between given from and to.
     *
     * @param exchangePair exchange pair which prices you need to receive
     * @param from the from border of search
     * @param to the to border of search
     * @return average bid price for period
     */
    Double getStandardDeviationBid(ExchangePair exchangePair, ZonedDateTime from, ZonedDateTime to);
}
