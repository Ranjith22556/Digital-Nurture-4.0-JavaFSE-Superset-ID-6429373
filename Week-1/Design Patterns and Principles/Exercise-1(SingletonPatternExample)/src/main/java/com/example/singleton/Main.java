package com.example.singleton;

/**
 * Main class to demonstrate the practical usage of Logger Singleton
 * in a real application scenario.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== SingletonPatternExample Application ===\n");
        
        // Get the logger instance
        Logger logger = Logger.getInstance();
        
        // Simulate application startup
        logger.logInfo("Application initializing...");
        
        // Simulate some application operations
        simulateUserLogin(logger);
        simulateDataProcessing(logger);
        simulateFileOperation(logger);
        
        // Application shutdown
        logger.logInfo("Application shutting down...");
        
        System.out.println("\n=== Application Finished ===");
        
        // Run the singleton test
        System.out.println("\nRunning Singleton Pattern Tests...\n");
        SingletonTest.main(args);
    }
    
    /**
     * Simulate user login process
     */
    private static void simulateUserLogin(Logger logger) {
        logger.logInfo("User login process started");
        
        // Simulate some processing time
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            logger.logError("Login process interrupted");
        }
        
        logger.logInfo("User 'john_doe' logged in successfully");
    }
    
    /**
     * Simulate data processing
     */
    private static void simulateDataProcessing(Logger logger) {
        logger.logInfo("Starting data processing...");
        
        // Get another instance of logger to demonstrate singleton behavior
        Logger anotherLogger = Logger.getInstance();
        anotherLogger.logInfo("Processing data batch 1...");
        
        // Simulate some warning
        logger.logWarning("Low memory warning during data processing");
        
        anotherLogger.logInfo("Data processing completed successfully");
        
        // Verify both loggers are the same instance
        if (logger == anotherLogger) {
            logger.logInfo("Confirmed: Both logger references point to the same singleton instance");
        }
    }
    
    /**
     * Simulate file operation with error
     */
    private static void simulateFileOperation(Logger logger) {
        logger.logInfo("Attempting to read configuration file...");
        
        // Simulate a file not found error
        logger.logError("Configuration file not found: config.properties");
        logger.logWarning("Using default configuration settings");
        logger.logInfo("Application configured with default settings");
    }
} 