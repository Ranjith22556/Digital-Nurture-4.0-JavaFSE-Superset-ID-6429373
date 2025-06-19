package com.example.singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logger class implementing the Singleton design pattern.
 * This ensures only one instance of the logger exists throughout the application lifecycle.
 */
public class Logger {
    
    // Private static instance of the Logger class (lazy initialization)
    private static Logger instance;
    
    // DateTimeFormatter for consistent timestamp formatting
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    // Private constructor to prevent instantiation from outside the class
    private Logger() {
        // Initialize the logger
        System.out.println("Logger instance created at: " + getCurrentTimestamp());
    }
    
    /**
     * Public static method to get the single instance of Logger.
     * Thread-safe implementation using synchronized method.
     * @return The single instance of Logger
     */
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    /**
     * Log an informational message
     * @param message The message to log
     */
    public void logInfo(String message) {
        System.out.println("[INFO] " + getCurrentTimestamp() + " - " + message);
    }
    
    /**
     * Log an error message
     * @param message The error message to log
     */
    public void logError(String message) {
        System.err.println("[ERROR] " + getCurrentTimestamp() + " - " + message);
    }
    
    /**
     * Log a warning message
     * @param message The warning message to log
     */
    public void logWarning(String message) {
        System.out.println("[WARNING] " + getCurrentTimestamp() + " - " + message);
    }
    
    /**
     * Get current timestamp as formatted string
     * @return Current timestamp
     */
    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(formatter);
    }
    
    /**
     * Get the hash code of this instance for testing purposes
     * @return Hash code of the instance
     */
    public int getInstanceHashCode() {
        return this.hashCode();
    }
} 