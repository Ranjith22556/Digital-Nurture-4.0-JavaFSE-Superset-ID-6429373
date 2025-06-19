package com.example.finance;

/**
 * RecursiveForecasting class implements recursive algorithms for financial calculations
 * Includes future value calculations, compound interest, and growth projections
 */
public class RecursiveForecasting {
    
    // Constants for validation
    private static final double MAX_INTEREST_RATE = 1.0; // 100% max
    private static final int MAX_PERIODS = 1000; // Maximum periods to prevent stack overflow
    private static final double MIN_PRESENT_VALUE = 0.01; // Minimum starting value
    
    // Performance tracking
    private static long operationCount = 0;
    private static long maxRecursionDepth = 0;
    
    /**
     * Calculate future value using recursive compound interest formula
     * FV = PV * (1 + r)^n
     * 
     * @param presentValue Current value (PV)
     * @param interestRate Interest rate per period (as decimal, e.g., 0.05 for 5%)
     * @param periods Number of compounding periods
     * @return Future value after compound interest
     */
    public static double calculateFutureValue(double presentValue, double interestRate, int periods) {
        // Input validation
        validateInputs(presentValue, interestRate, periods);
        
        // Reset operation counter
        operationCount = 0;
        maxRecursionDepth = 0;
        
        // Start recursive calculation
        double result = futureValueRecursive(presentValue, interestRate, periods, 0);
        
        return result;
    }
    
    /**
     * Recursive helper method for future value calculation
     * Base case: periods = 0, return present value
     * Recursive case: apply interest and reduce periods by 1
     * 
     * @param currentValue Current accumulated value
     * @param interestRate Interest rate per period
     * @param remainingPeriods Remaining periods to calculate
     * @param depth Current recursion depth for tracking
     * @return Future value
     */
    private static double futureValueRecursive(double currentValue, double interestRate, 
                                             int remainingPeriods, int depth) {
        // Track performance metrics
        operationCount++;
        maxRecursionDepth = Math.max(maxRecursionDepth, depth);
        
        // Base case: no more periods to calculate
        if (remainingPeriods == 0) {
            return currentValue;
        }
        
        // Recursive case: apply compound interest for one period
        double newValue = currentValue * (1 + interestRate);
        
        // Recursively calculate for remaining periods
        return futureValueRecursive(newValue, interestRate, remainingPeriods - 1, depth + 1);
    }
    
    /**
     * Calculate present value using recursive approach
     * PV = FV / (1 + r)^n
     * 
     * @param futureValue Target future value
     * @param interestRate Interest rate per period
     * @param periods Number of periods
     * @return Present value required to achieve future value
     */
    public static double calculatePresentValue(double futureValue, double interestRate, int periods) {
        // Input validation
        if (futureValue <= 0) {
            throw new IllegalArgumentException("Future value must be positive");
        }
        if (interestRate < -1.0 || interestRate > MAX_INTEREST_RATE) {
            throw new IllegalArgumentException("Interest rate must be between -100% and 100%");
        }
        if (periods < 0 || periods > MAX_PERIODS) {
            throw new IllegalArgumentException("Periods must be between 0 and " + MAX_PERIODS);
        }
        
        // Reset counters
        operationCount = 0;
        maxRecursionDepth = 0;
        
        return presentValueRecursive(futureValue, interestRate, periods, 0);
    }
    
    /**
     * Recursive helper for present value calculation
     * 
     * @param targetValue Target value to discount
     * @param discountRate Discount rate per period
     * @param remainingPeriods Remaining periods to discount
     * @param depth Current recursion depth
     * @return Present value
     */
    private static double presentValueRecursive(double targetValue, double discountRate, 
                                              int remainingPeriods, int depth) {
        operationCount++;
        maxRecursionDepth = Math.max(maxRecursionDepth, depth);
        
        // Base case: no more periods to discount
        if (remainingPeriods == 0) {
            return targetValue;
        }
        
        // Recursive case: discount by one period
        double discountedValue = targetValue / (1 + discountRate);
        
        return presentValueRecursive(discountedValue, discountRate, remainingPeriods - 1, depth + 1);
    }
    
    /**
     * Calculate compound annual growth rate (CAGR) recursively
     * CAGR = (Ending Value / Beginning Value)^(1/n) - 1
     * 
     * @param beginningValue Starting value
     * @param endingValue Ending value
     * @param years Number of years
     * @return CAGR as decimal
     */
    public static double calculateCAGR(double beginningValue, double endingValue, double years) {
        if (beginningValue <= 0 || endingValue <= 0) {
            throw new IllegalArgumentException("Values must be positive");
        }
        if (years <= 0) {
            throw new IllegalArgumentException("Years must be positive");
        }
        
        operationCount = 0;
        return cagrRecursive(endingValue / beginningValue, 1.0 / years, 1.0, 100); // 100 iterations for precision
    }
    
    /**
     * Recursive helper for CAGR calculation using Newton's method approximation
     * 
     * @param ratio Ending/Beginning value ratio
     * @param exponent 1/years
     * @param guess Current guess for the result
     * @param iterations Remaining iterations
     * @return CAGR approximation
     */
    private static double cagrRecursive(double ratio, double exponent, double guess, int iterations) {
        operationCount++;
        
        if (iterations == 0) {
            return guess - 1.0; // Convert to rate
        }
        
        // Newton's method for x^(1/n) = ratio
        double newGuess = ((1.0 / exponent - 1) * guess + ratio / Math.pow(guess, 1.0 / exponent - 1)) / (1.0 / exponent);
        
        // Check for convergence
        if (Math.abs(newGuess - guess) < 0.000001) {
            return newGuess - 1.0;
        }
        
        return cagrRecursive(ratio, exponent, newGuess, iterations - 1);
    }
    
    /**
     * Project future values for multiple periods using recursive growth model
     * Each period applies the growth rate to the previous period's value
     * 
     * @param initialValue Starting value
     * @param growthRate Growth rate per period (as decimal)
     * @param periods Number of periods to project
     * @return Array of projected values for each period
     */
    public static double[] projectGrowth(double initialValue, double growthRate, int periods) {
        validateInputs(initialValue, growthRate, periods);
        
        double[] projections = new double[periods + 1];
        projections[0] = initialValue;
        
        operationCount = 0;
        
        // Fill array recursively
        projectGrowthRecursive(projections, growthRate, 1, periods);
        
        return projections;
    }
    
    /**
     * Recursive helper for growth projection
     * 
     * @param projections Array to fill with projections
     * @param growthRate Growth rate per period
     * @param currentPeriod Current period being calculated
     * @param totalPeriods Total periods to calculate
     */
    private static void projectGrowthRecursive(double[] projections, double growthRate, 
                                             int currentPeriod, int totalPeriods) {
        operationCount++;
        
        // Base case: all periods calculated
        if (currentPeriod > totalPeriods) {
            return;
        }
        
        // Calculate current period value based on previous period
        projections[currentPeriod] = projections[currentPeriod - 1] * (1 + growthRate);
        
        // Recursive call for next period
        projectGrowthRecursive(projections, growthRate, currentPeriod + 1, totalPeriods);
    }
    
    /**
     * Calculate annuity future value recursively
     * FV = PMT * [((1 + r)^n - 1) / r]
     * 
     * @param payment Payment amount per period
     * @param interestRate Interest rate per period
     * @param periods Number of periods
     * @return Future value of annuity
     */
    public static double calculateAnnuityFutureValue(double payment, double interestRate, int periods) {
        if (payment <= 0) {
            throw new IllegalArgumentException("Payment must be positive");
        }
        validateInputs(MIN_PRESENT_VALUE, interestRate, periods);
        
        if (interestRate == 0) {
            return payment * periods; // Simple case with no interest
        }
        
        operationCount = 0;
        return annuityFVRecursive(payment, interestRate, periods, 0.0, 0);
    }
    
    /**
     * Recursive helper for annuity future value calculation
     * 
     * @param payment Payment amount
     * @param interestRate Interest rate
     * @param remainingPeriods Remaining periods
     * @param accumulatedValue Current accumulated value
     * @param depth Recursion depth
     * @return Future value of annuity
     */
    private static double annuityFVRecursive(double payment, double interestRate, int remainingPeriods, 
                                           double accumulatedValue, int depth) {
        operationCount++;
        maxRecursionDepth = Math.max(maxRecursionDepth, depth);
        
        // Base case: no more periods
        if (remainingPeriods == 0) {
            return accumulatedValue;
        }
        
        // Add current payment and apply compound interest to accumulated value
        double newAccumulatedValue = (accumulatedValue + payment) * (1 + interestRate);
        
        return annuityFVRecursive(payment, interestRate, remainingPeriods - 1, 
                                newAccumulatedValue, depth + 1);
    }
    
    /**
     * Calculate monthly payment for a loan using recursive approach
     * PMT = PV * [r(1 + r)^n] / [(1 + r)^n - 1]
     * 
     * @param loanAmount Principal loan amount
     * @param monthlyRate Monthly interest rate (annual rate / 12)
     * @param numberOfPayments Total number of payments
     * @return Monthly payment amount
     */
    public static double calculateLoanPayment(double loanAmount, double monthlyRate, int numberOfPayments) {
        if (loanAmount <= 0) {
            throw new IllegalArgumentException("Loan amount must be positive");
        }
        if (monthlyRate < 0 || monthlyRate > 0.1) { // 10% monthly max
            throw new IllegalArgumentException("Monthly rate must be between 0% and 10%");
        }
        if (numberOfPayments <= 0 || numberOfPayments > MAX_PERIODS) {
            throw new IllegalArgumentException("Number of payments must be between 1 and " + MAX_PERIODS);
        }
        
        if (monthlyRate == 0) {
            return loanAmount / numberOfPayments; // No interest case
        }
        
        operationCount = 0;
        
        // Calculate (1 + r)^n recursively
        double compoundFactor = calculatePowerRecursive(1 + monthlyRate, numberOfPayments, 0);
        
        // Calculate payment using the loan payment formula
        return loanAmount * (monthlyRate * compoundFactor) / (compoundFactor - 1);
    }
    
    /**
     * Recursive power calculation for compound factor
     * 
     * @param base Base value
     * @param exponent Exponent
     * @param depth Recursion depth
     * @return base^exponent
     */
    private static double calculatePowerRecursive(double base, int exponent, int depth) {
        operationCount++;
        maxRecursionDepth = Math.max(maxRecursionDepth, depth);
        
        // Base case
        if (exponent == 0) {
            return 1.0;
        }
        if (exponent == 1) {
            return base;
        }
        
        // Recursive case using divide and conquer for efficiency
        if (exponent % 2 == 0) {
            double halfPower = calculatePowerRecursive(base, exponent / 2, depth + 1);
            return halfPower * halfPower;
        } else {
            return base * calculatePowerRecursive(base, exponent - 1, depth + 1);
        }
    }
    
    /**
     * Validate common inputs for financial calculations
     * 
     * @param presentValue Present value to validate
     * @param interestRate Interest rate to validate
     * @param periods Number of periods to validate
     */
    private static void validateInputs(double presentValue, double interestRate, int periods) {
        if (presentValue < 0) {
            throw new IllegalArgumentException("Present value cannot be negative");
        }
        if (presentValue == 0) {
            throw new IllegalArgumentException("Present value cannot be zero");
        }
        if (interestRate < -1.0 || interestRate > MAX_INTEREST_RATE) {
            throw new IllegalArgumentException("Interest rate must be between -100% and " + MAX_INTEREST_RATE * 100 + "%");
        }
        if (periods < 0) {
            throw new IllegalArgumentException("Periods cannot be negative");
        }
        if (periods > MAX_PERIODS) {
            throw new IllegalArgumentException("Periods cannot exceed " + MAX_PERIODS + " to prevent stack overflow");
        }
    }
    
    /**
     * Get the number of operations performed in the last calculation
     * 
     * @return Number of recursive operations
     */
    public static long getLastOperationCount() {
        return operationCount;
    }
    
    /**
     * Get the maximum recursion depth of the last calculation
     * 
     * @return Maximum recursion depth
     */
    public static long getLastMaxRecursionDepth() {
        return maxRecursionDepth;
    }
    
    /**
     * Reset performance counters
     */
    public static void resetCounters() {
        operationCount = 0;
        maxRecursionDepth = 0;
    }
    
    /**
     * Display performance metrics for the last calculation
     */
    public static void displayPerformanceMetrics() {
        System.out.println("=== Recursive Algorithm Performance ===");
        System.out.println("Operations performed: " + operationCount);
        System.out.println("Maximum recursion depth: " + maxRecursionDepth);
        System.out.println("Time complexity: O(n) where n = periods");
        System.out.println("Space complexity: O(n) due to recursion stack");
        System.out.println();
    }
    
    /**
     * Calculate effective annual rate from nominal rate
     * EAR = (1 + r/m)^m - 1
     * 
     * @param nominalRate Nominal annual rate
     * @param compoundingFrequency Number of compounding periods per year
     * @return Effective annual rate
     */
    public static double calculateEffectiveAnnualRate(double nominalRate, int compoundingFrequency) {
        if (nominalRate < 0 || nominalRate > MAX_INTEREST_RATE) {
            throw new IllegalArgumentException("Nominal rate must be between 0% and " + MAX_INTEREST_RATE * 100 + "%");
        }
        if (compoundingFrequency <= 0) {
            throw new IllegalArgumentException("Compounding frequency must be positive");
        }
        
        operationCount = 0;
        
        double periodicRate = nominalRate / compoundingFrequency;
        double compoundFactor = calculatePowerRecursive(1 + periodicRate, compoundingFrequency, 0);
        
        return compoundFactor - 1;
    }
    
    /**
     * Format financial results for display
     * 
     * @param value Value to format
     * @param currency Currency symbol
     * @return Formatted string
     */
    public static String formatCurrency(double value, String currency) {
        return String.format("%s %.2f", currency, value);
    }
    
    /**
     * Format percentage for display
     * 
     * @param rate Rate as decimal
     * @return Formatted percentage string
     */
    public static String formatPercentage(double rate) {
        return String.format("%.4f%%", rate * 100);
    }
} 