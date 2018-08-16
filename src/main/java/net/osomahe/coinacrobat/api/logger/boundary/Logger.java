package net.osomahe.coinacrobat.api.logger.boundary;

import net.osomahe.coinacrobat.api.logger.entity.LogLevel;


/**
 * This interface provides possibility of logging.
 *
 * @author Antonin Stoklasek
 */
public interface Logger {

    /**
     * Method for debug message.
     *
     * @param msg message
     * @param params parameters of the message
     */
    void debug(String msg, Object... params);

    /**
     * Method for info message.
     *
     * @param msg message
     * @param params parameters of the message
     */
    void info(String msg, Object... params);

    /**
     * Method for warn message.
     *
     * @param msg message
     * @param params parameters of the message
     */
    void warn(String msg, Object... params);

    /**
     * Method for error message.
     *
     * @param msg message
     * @param params parameters of the message
     */
    void error(String msg, Object... params);

    /**
     * Method for logging message from a strategy.
     *
     * @param level level of severity of the message
     * @param msg message
     * @param params parameters of the message
     */
    void log(LogLevel level, String msg, Object... params);
}
