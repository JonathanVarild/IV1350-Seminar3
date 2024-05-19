package utilities;

public class LoggedException extends Exception {
    public LoggedException(String message) {
        super(message);
        ErrorLogger.log(message);
    }
}
