package net.osomahe.coinacrobat.api;


/**
 * @author Antonin Stoklasek
 */
public class LogRecord {

    private final LogLevel level;

    private final String message;

    public LogRecord(LogLevel level, String message) {
        this.level = level;
        this.message = message;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "LogRecord{" +
                "level=" + level +
                ", message='" + message + '\'' +
                '}';
    }
}
