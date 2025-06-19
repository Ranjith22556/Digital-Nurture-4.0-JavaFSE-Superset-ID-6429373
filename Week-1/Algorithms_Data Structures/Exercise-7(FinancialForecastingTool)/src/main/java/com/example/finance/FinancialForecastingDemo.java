package com.example.finance;

import java.util.Scanner;
import java.util.List;

/**
 * FinancialForecastingDemo provides an interactive demonstration of recursive forecasting algorithms
 * Includes menu-driven interface for testing various financial calculations
 */
public class FinancialForecastingDemo {
    
    private static Scanner scanner = new Scanner(System.in);
    private static FinancialData currentDataset;
    
    /**
     * Main method - entry point for the demonstration
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        displayWelcome();
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getUserChoice(1, 9);
            
            switch (choice) {
                case 1:
                    demonstrateFutureValueCalculation();
                    break;
                case 2:
                    demonstratePresentValueCalculation();
                    break;
                case 3:
                    demonstrateGrowthProjections();
                    break;
                case 4:
                    demonstrateAnnuityCalculations();
                    break;
                case 5:
                    demonstrateLoanPaymentCalculation();
                    break;
                case 6:
                    analyzeHistoricalData();
                    break;
                case 7:
                    compareRecursiveVsIterative();
                    break;
                case 8:
                    displayAlgorithmComplexity();
                    break;
                case 9:
                    running = false;
                    break;
            }
            
            if (running) {
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            }
        }
        
        displayGoodbye();
    }
    
    /**
     * Display welcome message
     */
    private static void displayWelcome() {
        System.out.println(repeatString("=", 60));
        System.out.println("     FINANCIAL FORECASTING TOOL - RECURSIVE ALGORITHMS");
        System.out.println(repeatString("=", 60));
        System.out.println("This tool demonstrates recursive algorithms for financial calculations");
        System.out.println("including future value, present value, and growth projections.");
        System.out.println();
    }
    
    /**
     * Display main menu options
     */
    private static void displayMainMenu() {
        System.out.println(repeatString("-", 50));
        System.out.println("                MAIN MENU");
        System.out.println(repeatString("-", 50));
        System.out.println("1. Future Value Calculation (Compound Interest)");
        System.out.println("2. Present Value Calculation (Discounting)");
        System.out.println("3. Growth Projections (Multi-period)");
        System.out.println("4. Annuity Calculations");
        System.out.println("5. Loan Payment Calculator");
        System.out.println("6. Analyze Historical Financial Data");
        System.out.println("7. Compare Recursive vs Iterative Performance");
        System.out.println("8. Display Algorithm Time Complexity");
        System.out.println("9. Exit");
        System.out.println(repeatString("-", 50));
        System.out.print("Enter your choice (1-9): ");
    }
    
    /**
     * Demonstrate future value calculation using recursive algorithm
     */
    private static void demonstrateFutureValueCalculation() {
        System.out.println("\n" + repeatString("=", 50));
        System.out.println("        FUTURE VALUE CALCULATION");
        System.out.println(repeatString("=", 50));
        System.out.println("Formula: FV = PV * (1 + r)^n");
        System.out.println();
        
        System.out.print("Enter present value (e.g., 1000): $");
        double presentValue = scanner.nextDouble();
        
        System.out.print("Enter annual interest rate (e.g., 0.05 for 5%): ");
        double interestRate = scanner.nextDouble();
        
        System.out.print("Enter number of periods: ");
        int periods = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        try {
            long startTime = System.nanoTime();
            double futureValue = RecursiveForecasting.calculateFutureValue(presentValue, interestRate, periods);
            long endTime = System.nanoTime();
            
            System.out.println("\n" + repeatString("-", 40));
            System.out.println("RESULTS:");
            System.out.println("Present Value: " + RecursiveForecasting.formatCurrency(presentValue, "$"));
            System.out.println("Interest Rate: " + RecursiveForecasting.formatPercentage(interestRate));
            System.out.println("Periods: " + periods);
            System.out.println("Future Value: " + RecursiveForecasting.formatCurrency(futureValue, "$"));
            System.out.println("Total Growth: " + RecursiveForecasting.formatCurrency(futureValue - presentValue, "$"));
            System.out.println("Growth Factor: " + String.format("%.2fx", futureValue / presentValue));
            
            System.out.println("\nPerformance Metrics:");
            System.out.println("Execution Time: " + (endTime - startTime) / 1_000_000.0 + " ms");
            RecursiveForecasting.displayPerformanceMetrics();
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrate present value calculation using recursive algorithm
     */
    private static void demonstratePresentValueCalculation() {
        System.out.println("\n" + repeatString("=", 50));
        System.out.println("        PRESENT VALUE CALCULATION");
        System.out.println(repeatString("=", 50));
        System.out.println("Formula: PV = FV / (1 + r)^n");
        System.out.println();
        
        System.out.print("Enter future value target (e.g., 10000): $");
        double futureValue = scanner.nextDouble();
        
        System.out.print("Enter discount rate (e.g., 0.08 for 8%): ");
        double discountRate = scanner.nextDouble();
        
        System.out.print("Enter number of periods: ");
        int periods = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        try {
            long startTime = System.nanoTime();
            double presentValue = RecursiveForecasting.calculatePresentValue(futureValue, discountRate, periods);
            long endTime = System.nanoTime();
            
            System.out.println("\n" + repeatString("-", 40));
            System.out.println("RESULTS:");
            System.out.println("Future Value Target: " + RecursiveForecasting.formatCurrency(futureValue, "$"));
            System.out.println("Discount Rate: " + RecursiveForecasting.formatPercentage(discountRate));
            System.out.println("Periods: " + periods);
            System.out.println("Required Present Value: " + RecursiveForecasting.formatCurrency(presentValue, "$"));
            System.out.println("Discount Amount: " + RecursiveForecasting.formatCurrency(futureValue - presentValue, "$"));
            
            System.out.println("\nPerformance Metrics:");
            System.out.println("Execution Time: " + (endTime - startTime) / 1_000_000.0 + " ms");
            RecursiveForecasting.displayPerformanceMetrics();
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrate growth projections using recursive algorithm
     */
    private static void demonstrateGrowthProjections() {
        System.out.println("\n" + repeatString("=", 50));
        System.out.println("          GROWTH PROJECTIONS");
        System.out.println(repeatString("=", 50));
        System.out.println("Project future values with compound growth");
        System.out.println();
        
        System.out.print("Enter initial value (e.g., 50000): $");
        double initialValue = scanner.nextDouble();
        
        System.out.print("Enter growth rate per period (e.g., 0.03 for 3%): ");
        double growthRate = scanner.nextDouble();
        
        System.out.print("Enter number of periods to project: ");
        int periods = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        try {
            long startTime = System.nanoTime();
            double[] projections = RecursiveForecasting.projectGrowth(initialValue, growthRate, periods);
            long endTime = System.nanoTime();
            
            System.out.println("\n" + repeatString("-", 60));
            System.out.println("GROWTH PROJECTIONS:");
            System.out.println(repeatString("-", 60));
            System.out.printf("%-8s %-15s %-15s %-15s%n", "Period", "Value", "Growth", "Cumulative %");
            System.out.println(repeatString("-", 60));
            
            for (int i = 0; i <= Math.min(periods, 10); i++) {
                double growth = i > 0 ? projections[i] - projections[i-1] : 0;
                double cumulativePercent = ((projections[i] - initialValue) / initialValue) * 100;
                
                System.out.printf("%-8d %-15s %-15s %-15s%n", 
                    i, 
                    RecursiveForecasting.formatCurrency(projections[i], "$"),
                    RecursiveForecasting.formatCurrency(growth, "$"),
                    String.format("%.2f%%", cumulativePercent));
            }
            
            if (periods > 10) {
                System.out.println("... (showing first 10 periods)");
                System.out.printf("%-8d %-15s %-15s %-15s%n", 
                    periods, 
                    RecursiveForecasting.formatCurrency(projections[periods], "$"),
                    RecursiveForecasting.formatCurrency(projections[periods] - projections[periods-1], "$"),
                    String.format("%.2f%%", ((projections[periods] - initialValue) / initialValue) * 100));
            }
            
            System.out.println("\nSummary:");
            System.out.println("Final Value: " + RecursiveForecasting.formatCurrency(projections[periods], "$"));
            System.out.println("Total Growth: " + RecursiveForecasting.formatCurrency(projections[periods] - initialValue, "$"));
            System.out.println("Execution Time: " + (endTime - startTime) / 1_000_000.0 + " ms");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrate annuity calculations
     */
    private static void demonstrateAnnuityCalculations() {
        System.out.println("\n" + repeatString("=", 50));
        System.out.println("         ANNUITY CALCULATIONS");
        System.out.println(repeatString("=", 50));
        System.out.println("Calculate future value of regular payments");
        System.out.println();
        
        System.out.print("Enter payment amount per period (e.g., 1000): $");
        double payment = scanner.nextDouble();
        
        System.out.print("Enter interest rate per period (e.g., 0.06 for 6%): ");
        double interestRate = scanner.nextDouble();
        
        System.out.print("Enter number of payments: ");
        int periods = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        try {
            long startTime = System.nanoTime();
            double annuityFV = RecursiveForecasting.calculateAnnuityFutureValue(payment, interestRate, periods);
            long endTime = System.nanoTime();
            
            double totalPayments = payment * periods;
            double interestEarned = annuityFV - totalPayments;
            
            System.out.println("\n" + repeatString("-", 40));
            System.out.println("ANNUITY RESULTS:");
            System.out.println("Payment per Period: " + RecursiveForecasting.formatCurrency(payment, "$"));
            System.out.println("Interest Rate: " + RecursiveForecasting.formatPercentage(interestRate));
            System.out.println("Number of Payments: " + periods);
            System.out.println("Total Payments Made: " + RecursiveForecasting.formatCurrency(totalPayments, "$"));
            System.out.println("Interest Earned: " + RecursiveForecasting.formatCurrency(interestEarned, "$"));
            System.out.println("Future Value: " + RecursiveForecasting.formatCurrency(annuityFV, "$"));
            System.out.println("Execution Time: " + (endTime - startTime) / 1_000_000.0 + " ms");
            
            RecursiveForecasting.displayPerformanceMetrics();
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrate loan payment calculation
     */
    private static void demonstrateLoanPaymentCalculation() {
        System.out.println("\n" + repeatString("=", 50));
        System.out.println("       LOAN PAYMENT CALCULATOR");
        System.out.println(repeatString("=", 50));
        System.out.println("Calculate monthly payment for a loan");
        System.out.println();
        
        System.out.print("Enter loan amount (e.g., 200000): $");
        double loanAmount = scanner.nextDouble();
        
        System.out.print("Enter annual interest rate (e.g., 0.045 for 4.5%): ");
        double annualRate = scanner.nextDouble();
        
        System.out.print("Enter loan term in years (e.g., 30): ");
        int years = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        try {
            double monthlyRate = annualRate / 12.0;
            int numberOfPayments = years * 12;
            
            long startTime = System.nanoTime();
            double monthlyPayment = RecursiveForecasting.calculateLoanPayment(loanAmount, monthlyRate, numberOfPayments);
            long endTime = System.nanoTime();
            
            double totalPaid = monthlyPayment * numberOfPayments;
            double totalInterest = totalPaid - loanAmount;
            
            System.out.println("\n" + repeatString("-", 40));
            System.out.println("LOAN PAYMENT RESULTS:");
            System.out.println("Loan Amount: " + RecursiveForecasting.formatCurrency(loanAmount, "$"));
            System.out.println("Annual Interest Rate: " + RecursiveForecasting.formatPercentage(annualRate));
            System.out.println("Loan Term: " + years + " years (" + numberOfPayments + " payments)");
            System.out.println("Monthly Payment: " + RecursiveForecasting.formatCurrency(monthlyPayment, "$"));
            System.out.println("Total Amount Paid: " + RecursiveForecasting.formatCurrency(totalPaid, "$"));
            System.out.println("Total Interest: " + RecursiveForecasting.formatCurrency(totalInterest, "$"));
            System.out.println("Interest as % of Loan: " + String.format("%.2f%%", (totalInterest / loanAmount) * 100));
            System.out.println("Execution Time: " + (endTime - startTime) / 1_000_000.0 + " ms");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Analyze historical financial data
     */
    private static void analyzeHistoricalData() {
        System.out.println("\n" + repeatString("=", 50));
        System.out.println("     HISTORICAL DATA ANALYSIS");
        System.out.println(repeatString("=", 50));
        
        // Display available datasets
        FinancialDataGenerator.displaySampleDataInfo();
        
        System.out.print("Select dataset (1-8): ");
        int choice = getUserChoice(1, 8);
        
        List<FinancialData> datasets = FinancialDataGenerator.generateSampleDatasets();
        currentDataset = datasets.get(choice - 1);
        
        System.out.println("\nAnalyzing: " + currentDataset.getDataType());
        currentDataset.displaySummary();
        
        // Calculate CAGR if applicable
        if (currentDataset.size() >= 2) {
            double yearsSpan = currentDataset.size() / 4.0; // Assuming quarterly data
            if (currentDataset.getDataType().contains("Monthly")) {
                yearsSpan = currentDataset.size() / 12.0;
            } else if (currentDataset.getDataType().contains("Annual")) {
                yearsSpan = currentDataset.size();
            }
            
            try {
                double cagr = RecursiveForecasting.calculateCAGR(
                    currentDataset.getValue(0), 
                    currentDataset.getLatestValue(), 
                    yearsSpan);
                
                System.out.println("Compound Annual Growth Rate (CAGR): " + 
                    RecursiveForecasting.formatPercentage(cagr));
                
                // Project future values based on historical CAGR
                System.out.println("\nProjecting next 5 periods with historical CAGR:");
                double[] projections = RecursiveForecasting.projectGrowth(
                    currentDataset.getLatestValue(), cagr / 4.0, 5); // Quarterly projections
                
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Period +" + i + ": " + 
                        RecursiveForecasting.formatCurrency(projections[i], currentDataset.getCurrency()));
                }
                
            } catch (IllegalArgumentException e) {
                System.out.println("Cannot calculate CAGR: " + e.getMessage());
            }
        }
    }
    
    /**
     * Compare recursive vs iterative performance
     */
    private static void compareRecursiveVsIterative() {
        System.out.println("\n" + repeatString("=", 50));
        System.out.println("   RECURSIVE VS ITERATIVE PERFORMANCE");
        System.out.println(repeatString("=", 50));
        
        double presentValue = 10000;
        double interestRate = 0.05;
        
        System.out.println("Testing with PV=$10,000, Rate=5%");
        System.out.println(repeatString("-", 50));
        System.out.printf("%-10s %-15s %-15s %-15s%n", "Periods", "Recursive (ms)", "Iterative (ms)", "Difference");
        System.out.println(repeatString("-", 50));
        
        int[] testPeriods = {10, 50, 100, 250, 500};
        
        for (int periods : testPeriods) {
            // Test recursive approach
            long startRecursive = System.nanoTime();
            double recursiveResult = RecursiveForecasting.calculateFutureValue(presentValue, interestRate, periods);
            long endRecursive = System.nanoTime();
            double recursiveTime = (endRecursive - startRecursive) / 1_000_000.0;
            
            // Test iterative approach
            long startIterative = System.nanoTime();
            double iterativeResult = presentValue * Math.pow(1 + interestRate, periods);
            long endIterative = System.nanoTime();
            double iterativeTime = (endIterative - startIterative) / 1_000_000.0;
            
            double difference = recursiveTime - iterativeTime;
            
            System.out.printf("%-10d %-15.3f %-15.3f %-15.3f%n", 
                periods, recursiveTime, iterativeTime, difference);
            
            // Verify results are approximately equal
            if (Math.abs(recursiveResult - iterativeResult) > 0.01) {
                System.out.println("Warning: Results differ significantly!");
            }
        }
        
        System.out.println("\nObservations:");
        System.out.println("- Recursive approach has O(n) time complexity");
        System.out.println("- Iterative approach has O(1) time complexity");
        System.out.println("- Recursive approach uses O(n) space for call stack");
        System.out.println("- For large periods, iterative is more efficient");
        System.out.println("- Recursive approach is more educational and readable");
    }
    
    /**
     * Display algorithm complexity analysis
     */
    private static void displayAlgorithmComplexity() {
        System.out.println("\n" + repeatString("=", 60));
        System.out.println("             ALGORITHM COMPLEXITY ANALYSIS");
        System.out.println(repeatString("=", 60));
        
        System.out.println("RECURSIVE ALGORITHMS IN THIS TOOL:");
        System.out.println();
        
        System.out.println("1. Future Value Calculation:");
        System.out.println("   - Time Complexity: O(n) where n = periods");
        System.out.println("   - Space Complexity: O(n) due to recursion stack");
        System.out.println("   - Recurrence: T(n) = T(n-1) + O(1)");
        System.out.println();
        
        System.out.println("2. Present Value Calculation:");
        System.out.println("   - Time Complexity: O(n) where n = periods");
        System.out.println("   - Space Complexity: O(n) due to recursion stack");
        System.out.println("   - Recurrence: T(n) = T(n-1) + O(1)");
        System.out.println();
        
        System.out.println("3. Growth Projections:");
        System.out.println("   - Time Complexity: O(n) where n = periods");
        System.out.println("   - Space Complexity: O(n) for both recursion and array");
        System.out.println("   - Recurrence: T(n) = T(n-1) + O(1)");
        System.out.println();
        
        System.out.println("4. Power Calculation (for loan payments):");
        System.out.println("   - Time Complexity: O(log n) using divide-and-conquer");
        System.out.println("   - Space Complexity: O(log n) due to recursion stack");
        System.out.println("   - Recurrence: T(n) = T(n/2) + O(1)");
        System.out.println();
        
        System.out.println("5. CAGR Calculation (Newton's method):");
        System.out.println("   - Time Complexity: O(log precision) - converges quickly");
        System.out.println("   - Space Complexity: O(log precision)");
        System.out.println("   - Converges quadratically");
        System.out.println();
        
        System.out.println("COMPARISON WITH ITERATIVE APPROACHES:");
        System.out.println("- Most iterative versions have O(1) space complexity");
        System.out.println("- Time complexity often similar (O(n) vs O(n))");
        System.out.println("- Recursive versions are more intuitive for financial formulas");
        System.out.println("- Stack overflow risk for very large periods (>1000)");
        System.out.println();
        
        System.out.println("PRACTICAL CONSIDERATIONS:");
        System.out.println("- Recursion limit set to 1000 periods for safety");
        System.out.println("- Performance tracking included for analysis");
        System.out.println("- Input validation prevents common errors");
        System.out.println("- Suitable for typical financial planning horizons");
    }
    
    /**
     * Get user choice within specified range
     * @param min Minimum valid choice
     * @param max Maximum valid choice
     * @return Valid user choice
     */
    private static int getUserChoice(int min, int max) {
        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (Exception e) {
                System.out.print("Please enter a valid number: ");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
    
    /**
     * Helper method to repeat a string
     * @param str String to repeat
     * @param count Number of times to repeat
     * @return Repeated string
     */
    private static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    /**
     * Display goodbye message
     */
    private static void displayGoodbye() {
        System.out.println("\n" + repeatString("=", 60));
        System.out.println("    Thank you for using the Financial Forecasting Tool!");
        System.out.println("          Recursive algorithms in action for finance.");
        System.out.println(repeatString("=", 60));
        System.out.println();
    }
} 