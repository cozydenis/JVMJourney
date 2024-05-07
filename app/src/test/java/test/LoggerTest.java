package test;

import ch.zhaw.it.pm2.jvmjourney.Logger.LOGGINGLEVEL;
import ch.zhaw.it.pm2.jvmjourney.Logger.Logger;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoggerTest {

    @Test
    void logInfo() {
        Logger.log(LOGGINGLEVEL.INFO, "This is an Info message");
        String logContent = readLogFile();
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        assertTrue(logContent.contains(formattedDateTime + ":  -  INFO: This is an Info message"));
    }
    @Test
    void logDebug() {
        Logger.log(LOGGINGLEVEL.DEBUG, "This is a debug message");
        String logContent = readLogFile();
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        assertTrue(logContent.contains(formattedDateTime + ":  -  DEBUG: This is a debug message"));
    }

    @Test
    void logError() {
        Logger.log(LOGGINGLEVEL.ERROR, "This is an error message");
        String logContent = readLogFile();
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        assertTrue(logContent.contains(formattedDateTime + ":  -  ERROR: This is an error message"));
    }

    @Test
    void logCritical() {
        Logger.log(LOGGINGLEVEL.CRITICAL, "This is a critical message");
        String logContent = readLogFile();
        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        assertTrue(logContent.contains(formattedDateTime + ":  -  CRITICAL: This is a critical message"));
    }

    // Helper method to read the contents of the log file
    private String readLogFile() {
        StringBuilder content = new StringBuilder();
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();
        // Format the date and time into a string
        String dateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // Format the date and time into a filename
        String filename = "log_" + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH")) + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }catch (IOException e) {
            System.out.println("Critical error: Could not read from log file.");
        }
        return content.toString();
    }
}
