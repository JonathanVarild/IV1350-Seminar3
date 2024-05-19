package utilities;

/**
 * RuntimeException that logs itself each time it is thrown.
 */
public class LoggedRuntimeException extends RuntimeException {

    /**
     * Constructor of the LoggedRuntimeException class.
     *
     * @param message The error message.
     */
    public LoggedRuntimeException(String message) {
        super(message);
        ErrorLogger.log(message);
    }
}
