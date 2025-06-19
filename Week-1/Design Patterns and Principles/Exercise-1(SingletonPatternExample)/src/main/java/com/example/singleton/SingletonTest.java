package com.example.singleton;

/**
 * Test class to verify the Singleton implementation of Logger class.
 * This class demonstrates that only one instance of Logger is created
 * and used across the application.
 */
public class SingletonTest {
    
    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Test for Logger Class ===\n");
        
        // Test 1: Get multiple instances and verify they are the same
        System.out.println("Test 1: Verifying Singleton Behavior");
        System.out.println("-----------------------------------");
        
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        Logger logger3 = Logger.getInstance();
        
        // Check if all references point to the same instance
        System.out.println("logger1 hash code: " + logger1.getInstanceHashCode());
        System.out.println("logger2 hash code: " + logger2.getInstanceHashCode());
        System.out.println("logger3 hash code: " + logger3.getInstanceHashCode());
        
        // Verify using == operator
        boolean sameInstance1 = (logger1 == logger2);
        boolean sameInstance2 = (logger2 == logger3);
        boolean sameInstance3 = (logger1 == logger3);
        
        System.out.println("\nSingleton Verification Results:");
        System.out.println("logger1 == logger2: " + sameInstance1);
        System.out.println("logger2 == logger3: " + sameInstance2);
        System.out.println("logger1 == logger3: " + sameInstance3);
        
        if (sameInstance1 && sameInstance2 && sameInstance3) {
            System.out.println("✓ SUCCESS: All logger instances are the same object!");
        } else {
            System.out.println("✗ FAILURE: Multiple instances detected!");
        }
        
        System.out.println("\n==================================================\n");
        
        // Test 2: Demonstrate logging functionality
        System.out.println("Test 2: Demonstrating Logging Functionality");
        System.out.println("------------------------------------------");
        
        logger1.logInfo("Application started successfully");
        logger2.logWarning("This is a warning message from logger2");
        logger3.logError("This is an error message from logger3");
        logger1.logInfo("All logging operations completed");
        
        System.out.println("\n==================================================\n");
        
        // Test 3: Thread safety demonstration (simple test)
        System.out.println("Test 3: Thread Safety Test");
        System.out.println("--------------------------");
        
        // Create multiple threads that try to get Logger instance
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                Logger threadLogger1 = Logger.getInstance();
                threadLogger1.logInfo("Message from Thread 1 - Hash: " + threadLogger1.getInstanceHashCode());
            }
        });
        
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                Logger threadLogger2 = Logger.getInstance();
                threadLogger2.logInfo("Message from Thread 2 - Hash: " + threadLogger2.getInstanceHashCode());
            }
        });
        
        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                Logger threadLogger3 = Logger.getInstance();
                threadLogger3.logInfo("Message from Thread 3 - Hash: " + threadLogger3.getInstanceHashCode());
            }
        });
        
        // Start all threads
        thread1.start();
        thread2.start();
        thread3.start();
        
        // Wait for all threads to complete
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            logger1.logError("Thread interrupted: " + e.getMessage());
        }
        
        System.out.println("\n=== Test Completed Successfully ===");
    }
} 