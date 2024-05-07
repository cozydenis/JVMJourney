package ch.zhaw.it.pm2.jvmjourney.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static  void log(LOGGINGLEVEL level, String message) {
        if(level == LOGGINGLEVEL.INFO) {
            writeToFile("INFO: " + message);
        } else if(level == LOGGINGLEVEL.DEBUG) {
            writeToFile("DEBUG: " + message);
        } else if(level == LOGGINGLEVEL.ERROR) {
            writeToFile("ERROR: " + message);
        } else if(level == LOGGINGLEVEL.CRITICAL) {
            writeToFile("CRITICAL: " + message);
            System.out.println("CRITICAL: " + message);
        }
    }

    private static void writeToFile(String message) {
    // Get the current date and time
    LocalDateTime now = LocalDateTime.now();
    // Format the date and time into a string
    String dateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    // Format the date and time into a filename
    String filename = "log_" + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH")) + ".txt";
    try(Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename, true), StandardCharsets.UTF_8))) {
        // Write the date, time, and message to the file
        writer.write(dateTime + ":  -  " + message + "\n");
    } catch (IOException e) {
        System.out.println("Critical error: Could not write to log file.");
    }
}
}
