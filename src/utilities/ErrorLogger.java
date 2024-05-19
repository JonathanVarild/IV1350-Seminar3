package utilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorLogger {
    private static PrintWriter printWriter;

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

    public static void log(String message) {
        printWriter.println(message);
    }
}
