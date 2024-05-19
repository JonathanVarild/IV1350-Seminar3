package utilities;

public class LoggedRuntimeException extends RuntimeException {
    public LoggedRuntimeException(String message) {
        super(message);
        ErrorLogger.log(message);
    }
}
