package utilities;

/**
 * Exception that logs itself each time it is thrown.
 */
public class LoggedException extends Exception {

    /**
     * Constructor for the LoggedException class.
     *
     * @param message The error message.
     */
    public LoggedException(String message) {
        super(message);
        ErrorLogger.log(message);
    }

}
