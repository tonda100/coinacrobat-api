package net.osomahe.coinacrobat.api;


/**
 * This interface represents stock market wallet.
 * It provides information about balances of your account.
 *
 * @author Antonin Stoklasek
 */
public interface Wallet {

    /**
     * This method returns available balance of given currency.
     * How much can be used in next transaction.
     *
     * @param currency - commodity or payment currency which balance should be returned.
     * @return - available balance of given currency.
     */
    Double getAvailable(Currency currency);

    /**
     * This method returns reserved balance of given currency.
     * How much is stuck in open transactions.
     *
     * @param currency - commodity or payment currency which balance should be returned.
     * @return - reserved balance of given currency.
     */
    Double getReserved(Currency currency);

    /**
     * This method returns total balance of given currency.
     * available + reserved = total
     *
     * @param currency - commodity or payment currency which balance should be returned.
     * @return - total balance of given currency.
     */
    Double getTotal(Currency currency);


    /**
     * This method returns fee for given exchange pair in percent.
     * How much percent is the fee for transaction.
     *
     * @param exchangePair - exchange pair which fee percentage should be returned.
     * @return - fee percentage of given exchange pair.
     */
    Double getFeePercentage(ExchangePair exchangePair);
}