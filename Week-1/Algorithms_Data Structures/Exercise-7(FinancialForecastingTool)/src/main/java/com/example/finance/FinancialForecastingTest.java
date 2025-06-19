package com.example.finance;

import java.util.List;

/**
 * FinancialForecastingTest class to test and validate recursive forecasting algorithms
 * Includes comprehensive tests for all financial calculation methods
 */
public class FinancialForecastingTest {
    
    private static int testsRun = 0;
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    
    /**
     * Main method to run all tests
     * @param args Command line arguments (can specify test type)
     */
    public static void main(String[] args) {
        System.out.println(repeatString("=", 70));
        System.out.println("      FINANCIAL FORECASTING TOOL - COMPREHENSIVE TESTING");
        System.out.println(repeatString("=", 70));
        System.out.println();
        
        if (args.length > 0) {
            String testType = args[0].toLowerCase();
            switch (testType) {
                case "basic":
                    runBasicTests();
                    break;
                case "performance":
                    runPerformanceTests();
                    break;
                case "data":
                    runDataTests();
                    break;
                case "edge":
                    runEdgeCaseTests();
                    break;
                default:
                    runAllTests();
                    break;
            }
        } else {
            runAllTests();
        }
        
        displayTestSummary();
    }
    
    /**
     * Run all test suites
     */
    private static void runAllTests() {
        System.out.println("Running comprehensive test suite...\n");
        
        runBasicTests();
        runPerformanceTests();
        runDataTests();
        runEdgeCaseTests();
        runAccuracyTests();
    }
    
    /**
     * Run basic functionality tests
     */
    private static void runBasicTests() {
        System.out.println(repeatString("-", 50));
        System.out.println("           BASIC FUNCTIONALITY TESTS");
        System.out.println(repeatString("-", 50));
        
        testFutureValueCalculation();
        testPresentValueCalculation();
        testGrowthProjections();
        testAnnuityCalculations();
        testLoanPaymentCalculation();
        testCAGRCalculation();
        
        System.out.println();
    }
    
    /**
     * Test future value calculation
     */
    private static void testFutureValueCalculation() {
        System.out.print("Testing Future Value Calculation... ");
        
        try {
            // Test case: $1000 at 5% for 10 years
            double result = RecursiveForecasting.calculateFutureValue(1000.0, 0.05, 10);
            double expected = 1000.0 * Math.pow(1.05, 10); // Expected: ~1628.89
            
            if (Math.abs(result - expected) < 0.01) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED - Expected: " + expected + ", Got: " + result);
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Test present value calculation
     */
    private static void testPresentValueCalculation() {
        System.out.print("Testing Present Value Calculation... ");
        
        try {
            // Test case: $10000 future value at 8% for 5 years
            double result = RecursiveForecasting.calculatePresentValue(10000.0, 0.08, 5);
            double expected = 10000.0 / Math.pow(1.08, 5); // Expected: ~6805.83
            
            if (Math.abs(result - expected) < 0.01) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED - Expected: " + expected + ", Got: " + result);
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Test growth projections
     */
    private static void testGrowthProjections() {
        System.out.print("Testing Growth Projections... ");
        
        try {
            // Test case: $1000 at 10% for 3 periods
            double[] result = RecursiveForecasting.projectGrowth(1000.0, 0.10, 3);
            
            boolean passed = true;
            double[] expected = {1000.0, 1100.0, 1210.0, 1331.0};
            
            if (result.length != expected.length) {
                passed = false;
            } else {
                for (int i = 0; i < result.length; i++) {
                    if (Math.abs(result[i] - expected[i]) > 0.01) {
                        passed = false;
                        break;
                    }
                }
            }
            
            if (passed) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED - Projection values incorrect");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Test annuity calculations
     */
    private static void testAnnuityCalculations() {
        System.out.print("Testing Annuity Calculations... ");
        
        try {
            // Test case: $100 payments at 6% for 5 periods
            double result = RecursiveForecasting.calculateAnnuityFutureValue(100.0, 0.06, 5);
            
            // Expected calculation manually verified
            double expected = 563.71; // Approximately
            
            if (Math.abs(result - expected) < 1.0) { // Allow 1 dollar tolerance
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED - Expected: ~" + expected + ", Got: " + result);
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Test loan payment calculation
     */
    private static void testLoanPaymentCalculation() {
        System.out.print("Testing Loan Payment Calculation... ");
        
        try {
            // Test case: $100,000 loan at 6% annual (0.5% monthly) for 30 years
            double result = RecursiveForecasting.calculateLoanPayment(100000.0, 0.005, 360);
            
            // Expected monthly payment: ~$599.55
            double expected = 599.55;
            
            if (Math.abs(result - expected) < 5.0) { // Allow $5 tolerance
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED - Expected: ~" + expected + ", Got: " + result);
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Test CAGR calculation
     */
    private static void testCAGRCalculation() {
        System.out.print("Testing CAGR Calculation... ");
        
        try {
            // Test case: $1000 to $2000 over 10 years
            double result = RecursiveForecasting.calculateCAGR(1000.0, 2000.0, 10.0);
            
            // Expected CAGR: ~7.18%
            double expected = 0.0718;
            
            if (Math.abs(result - expected) < 0.001) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED - Expected: ~" + expected + ", Got: " + result);
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Run performance tests
     */
    private static void runPerformanceTests() {
        System.out.println(repeatString("-", 50));
        System.out.println("            PERFORMANCE TESTS");
        System.out.println(repeatString("-", 50));
        
        testRecursionPerformance();
        testMemoryUsage();
        
        System.out.println();
    }
    
    /**
     * Test recursion performance
     */
    private static void testRecursionPerformance() {
        System.out.print("Testing Recursion Performance... ");
        
        try {
            long startTime = System.nanoTime();
            RecursiveForecasting.calculateFutureValue(1000.0, 0.05, 500);
            long endTime = System.nanoTime();
            
            double executionTime = (endTime - startTime) / 1_000_000.0; // ms
            
            if (executionTime < 100.0) { // Should complete within 100ms
                System.out.println("PASSED (" + String.format("%.2f", executionTime) + " ms)");
                testsPassed++;
            } else {
                System.out.println("FAILED - Too slow: " + executionTime + " ms");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Test memory usage (recursion depth)
     */
    private static void testMemoryUsage() {
        System.out.print("Testing Memory Usage (Recursion Depth)... ");
        
        try {
            RecursiveForecasting.calculateFutureValue(1000.0, 0.05, 100);
            long maxDepth = RecursiveForecasting.getLastMaxRecursionDepth();
            
            if (maxDepth == 100) { // Should match the number of periods
                System.out.println("PASSED (Max depth: " + maxDepth + ")");
                testsPassed++;
            } else {
                System.out.println("FAILED - Unexpected depth: " + maxDepth);
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Run data generation and analysis tests
     */
    private static void runDataTests() {
        System.out.println(repeatString("-", 50));
        System.out.println("         DATA GENERATION TESTS");
        System.out.println(repeatString("-", 50));
        
        testFinancialDataCreation();
        testDataGeneration();
        testDataAnalysis();
        
        System.out.println();
    }
    
    /**
     * Test financial data creation
     */
    private static void testFinancialDataCreation() {
        System.out.print("Testing Financial Data Creation... ");
        
        try {
            double[] values = {1000.0, 1100.0, 1210.0, 1331.0};
            String[] periods = {"2020-Q1", "2020-Q2", "2020-Q3", "2020-Q4"};
            
            FinancialData data = new FinancialData(values, periods, "Test Revenue", "USD");
            
            if (data.size() == 4 && data.getLatestValue() == 1331.0) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED - Data not created correctly");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Test data generation
     */
    private static void testDataGeneration() {
        System.out.print("Testing Data Generation... ");
        
        try {
            FinancialData generated = FinancialDataGenerator.generateQuarterlyRevenue(100000, 8, 0.05, 0.02);
            
            if (generated.size() == 8 && generated.getValue(0) == 100000) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED - Generated data incorrect");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Test data analysis methods
     */
    private static void testDataAnalysis() {
        System.out.print("Testing Data Analysis... ");
        
        try {
            double[] values = {1000.0, 1100.0, 1210.0, 1331.0};
            String[] periods = {"2020-Q1", "2020-Q2", "2020-Q3", "2020-Q4"};
            
            FinancialData data = new FinancialData(values, periods, "Test Revenue", "USD");
            double avgGrowth = data.calculateAverageGrowthRate();
            
            // Expected average growth rate: 10%
            if (Math.abs(avgGrowth - 0.10) < 0.001) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED - Expected: 0.10, Got: " + avgGrowth);
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Run edge case tests
     */
    private static void runEdgeCaseTests() {
        System.out.println(repeatString("-", 50));
        System.out.println("             EDGE CASE TESTS");
        System.out.println(repeatString("-", 50));
        
        testZeroInterestRate();
        testNegativeValues();
        testLargePeriods();
        testInputValidation();
        
        System.out.println();
    }
    
    /**
     * Test zero interest rate
     */
    private static void testZeroInterestRate() {
        System.out.print("Testing Zero Interest Rate... ");
        
        try {
            double result = RecursiveForecasting.calculateFutureValue(1000.0, 0.0, 10);
            
            if (result == 1000.0) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED - Expected: 1000.0, Got: " + result);
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Test negative values handling
     */
    private static void testNegativeValues() {
        System.out.print("Testing Negative Values Validation... ");
        
        try {
            RecursiveForecasting.calculateFutureValue(-1000.0, 0.05, 10);
            System.out.println("FAILED - Should have thrown exception");
            testsFailed++;
        } catch (IllegalArgumentException e) {
            System.out.println("PASSED");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("FAILED - Wrong exception type: " + e.getClass().getSimpleName());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Test large periods
     */
    private static void testLargePeriods() {
        System.out.print("Testing Large Periods Validation... ");
        
        try {
            RecursiveForecasting.calculateFutureValue(1000.0, 0.05, 2000);
            System.out.println("FAILED - Should have thrown exception");
            testsFailed++;
        } catch (IllegalArgumentException e) {
            System.out.println("PASSED");
            testsPassed++;
        } catch (Exception e) {
            System.out.println("FAILED - Wrong exception type: " + e.getClass().getSimpleName());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Test input validation
     */
    private static void testInputValidation() {
        System.out.print("Testing Input Validation... ");
        
        int validationsPassed = 0;
        int validationsTotal = 0;
        
        // Test various invalid inputs
        try {
            RecursiveForecasting.calculateFutureValue(0.0, 0.05, 10);
        } catch (IllegalArgumentException e) {
            validationsPassed++;
        } catch (Exception e) {}
        validationsTotal++;
        
        try {
            RecursiveForecasting.calculateFutureValue(1000.0, 2.0, 10); // 200% rate
        } catch (IllegalArgumentException e) {
            validationsPassed++;
        } catch (Exception e) {}
        validationsTotal++;
        
        try {
            RecursiveForecasting.calculateFutureValue(1000.0, 0.05, -10);
        } catch (IllegalArgumentException e) {
            validationsPassed++;
        } catch (Exception e) {}
        validationsTotal++;
        
        if (validationsPassed == validationsTotal) {
            System.out.println("PASSED (" + validationsPassed + "/" + validationsTotal + ")");
            testsPassed++;
        } else {
            System.out.println("FAILED (" + validationsPassed + "/" + validationsTotal + ")");
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Run accuracy tests
     */
    private static void runAccuracyTests() {
        System.out.println(repeatString("-", 50));
        System.out.println("             ACCURACY TESTS");
        System.out.println(repeatString("-", 50));
        
        testCalculationAccuracy();
        testConsistency();
        
        System.out.println();
    }
    
    /**
     * Test calculation accuracy against known values
     */
    private static void testCalculationAccuracy() {
        System.out.print("Testing Calculation Accuracy... ");
        
        try {
            // Known test cases with verified results
            double result1 = RecursiveForecasting.calculateFutureValue(5000.0, 0.07, 15);
            double expected1 = 13795.45; // Known result
            
            double result2 = RecursiveForecasting.calculatePresentValue(25000.0, 0.09, 20);
            double expected2 = 4464.29; // Known result
            
            boolean accurate = Math.abs(result1 - expected1) < 1.0 && 
                             Math.abs(result2 - expected2) < 1.0;
            
            if (accurate) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED - Accuracy issues detected");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Test consistency between different methods
     */
    private static void testConsistency() {
        System.out.print("Testing Method Consistency... ");
        
        try {
            // Test that FV and PV are inverse operations
            double originalPV = 10000.0;
            double rate = 0.06;
            int periods = 8;
            
            double calculatedFV = RecursiveForecasting.calculateFutureValue(originalPV, rate, periods);
            double calculatedPV = RecursiveForecasting.calculatePresentValue(calculatedFV, rate, periods);
            
            if (Math.abs(originalPV - calculatedPV) < 0.01) {
                System.out.println("PASSED");
                testsPassed++;
            } else {
                System.out.println("FAILED - Inconsistency detected");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println("FAILED - Exception: " + e.getMessage());
            testsFailed++;
        }
        
        testsRun++;
    }
    
    /**
     * Display test summary
     */
    private static void displayTestSummary() {
        System.out.println(repeatString("=", 70));
        System.out.println("                         TEST SUMMARY");
        System.out.println(repeatString("=", 70));
        System.out.println("Tests Run: " + testsRun);
        System.out.println("Tests Passed: " + testsPassed);
        System.out.println("Tests Failed: " + testsFailed);
        System.out.println("Success Rate: " + String.format("%.1f%%", (double) testsPassed / testsRun * 100));
        
        if (testsFailed == 0) {
            System.out.println("\n🎉 ALL TESTS PASSED! The Financial Forecasting Tool is working correctly.");
        } else {
            System.out.println("\n⚠️  Some tests failed. Please review the implementation.");
        }
        
        System.out.println(repeatString("=", 70));
        System.out.println();
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
} 