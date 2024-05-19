package utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorLogger {
    private static PrintWriter printWriter;

    /**
     * Static method to set up the logging system.
     *
     * @param fileName The filename that the logs should be exported to.
     */
    public static void setupLoggingSystem(String fileName) {
        if (printWriter != null) {
            return;
        }

        try {
            printWriter = new PrintWriter(new FileWriter(fileName), true);
        }
        catch (IOException e) {
            System.out.println("Failed to create setup logging environment.");
        }
    }

    /**
     * Method used to add a new log to the logging file.
     *
     * @param message The message to be logged.
     */
    public static void log(String message) {
        printWriter.println(message);
    }
}
