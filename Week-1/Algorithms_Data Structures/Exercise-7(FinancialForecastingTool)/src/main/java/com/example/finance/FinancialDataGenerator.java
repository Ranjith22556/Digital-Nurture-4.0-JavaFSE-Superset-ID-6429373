package com.example.finance;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

/**
 * FinancialDataGenerator generates sample financial data for testing
 * Includes various types of financial time series data with realistic patterns
 */
public class FinancialDataGenerator {
    
    private static final Random random = new Random();
    
    /**
     * Generate sample quarterly revenue data
     * @param startingValue Initial revenue value
     * @param periods Number of quarters
     * @param baseGrowthRate Base quarterly growth rate
     * @param volatility Volatility factor (0.0 to 1.0)
     * @return FinancialData object with quarterly revenue
     */
    public static FinancialData generateQuarterlyRevenue(double startingValue, int periods, 
                                                       double baseGrowthRate, double volatility) {
        double[] values = new double[periods];
        String[] quarters = new String[periods];
        
        values[0] = startingValue;
        quarters[0] = "2020-Q1";
        
        for (int i = 1; i < periods; i++) {
            // Apply base growth with random volatility
            double growthVariation = (random.nextGaussian() * volatility);
            double actualGrowthRate = baseGrowthRate + growthVariation;
            
            values[i] = values[i - 1] * (1 + actualGrowthRate);
            
            // Generate quarter string
            int year = 2020 + (i / 4);
            int quarter = (i % 4) + 1;
            quarters[i] = year + "-Q" + quarter;
        }
        
        return new FinancialData(values, quarters, "Quarterly Revenue", "USD");
    }
    
    /**
     * Generate sample monthly stock price data
     * @param startingPrice Initial stock price
     * @param months Number of months
     * @param annualReturn Expected annual return
     * @param volatility Annual volatility
     * @return FinancialData object with monthly stock prices
     */
    public static FinancialData generateMonthlyStockPrices(double startingPrice, int months, 
                                                         double annualReturn, double volatility) {
        double[] prices = new double[months];
        String[] periods = new String[months];
        
        double monthlyReturn = annualReturn / 12.0;
        double monthlyVolatility = volatility / Math.sqrt(12.0);
        
        prices[0] = startingPrice;
        periods[0] = "2020-01";
        
        for (int i = 1; i < months; i++) {
            // Geometric Brownian Motion model for stock prices
            double randomShock = random.nextGaussian() * monthlyVolatility;
            double actualReturn = monthlyReturn + randomShock;
            
            prices[i] = prices[i - 1] * Math.exp(actualReturn);
            
            // Generate period string (YYYY-MM)
            int year = 2020 + ((i - 1) / 12);
            int month = ((i - 1) % 12) + 1;
            periods[i] = String.format("%d-%02d", year, month);
        }
        
        return new FinancialData(prices, periods, "Monthly Stock Price", "USD");
    }
    
    /**
     * Generate sample annual company expenses
     * @param startingExpenses Initial annual expenses
     * @param years Number of years
     * @param inflationRate Annual inflation rate
     * @param businessGrowthRate Business growth impact on expenses
     * @return FinancialData object with annual expenses
     */
    public static FinancialData generateAnnualExpenses(double startingExpenses, int years, 
                                                     double inflationRate, double businessGrowthRate) {
        double[] expenses = new double[years];
        String[] periods = new String[years];
        
        expenses[0] = startingExpenses;
        periods[0] = "2020";
        
        for (int i = 1; i < years; i++) {
            // Expenses grow with inflation and business expansion
            double totalGrowthRate = inflationRate + businessGrowthRate + 
                                   (random.nextGaussian() * 0.05); // 5% volatility
            
            expenses[i] = expenses[i - 1] * (1 + totalGrowthRate);
            
            periods[i] = String.valueOf(2020 + i);
        }
        
        return new FinancialData(expenses, periods, "Annual Expenses", "USD");
    }
    
    /**
     * Generate cyclical sales data (seasonal patterns)
     * @param baseValue Base sales value
     * @param periods Number of periods
     * @param seasonalityStrength Strength of seasonal pattern (0.0 to 1.0)
     * @param trendGrowth Overall trend growth rate
     * @return FinancialData object with cyclical sales
     */
    public static FinancialData generateCyclicalSales(double baseValue, int periods, 
                                                    double seasonalityStrength, double trendGrowth) {
        double[] sales = new double[periods];
        String[] periodNames = new String[periods];
        
        for (int i = 0; i < periods; i++) {
            // Base trend growth
            double trendValue = baseValue * Math.pow(1 + trendGrowth, i);
            
            // Seasonal component (sine wave for quarterly cycles)
            double seasonalMultiplier = 1 + seasonalityStrength * Math.sin(2 * Math.PI * i / 4.0);
            
            // Random noise
            double noise = 1 + (random.nextGaussian() * 0.1);
            
            sales[i] = trendValue * seasonalMultiplier * noise;
            
            // Generate quarter period
            int year = 2020 + (i / 4);
            int quarter = (i % 4) + 1;
            periodNames[i] = year + "-Q" + quarter;
        }
        
        return new FinancialData(sales, periodNames, "Cyclical Sales", "USD");
    }
    
    /**
     * Generate portfolio value data with market volatility
     * @param initialValue Initial portfolio value
     * @param months Number of months
     * @param expectedReturn Expected annual return
     * @param marketVolatility Market volatility factor
     * @return FinancialData object with portfolio values
     */
    public static FinancialData generatePortfolioValue(double initialValue, int months, 
                                                     double expectedReturn, double marketVolatility) {
        double[] values = new double[months];
        String[] periods = new String[months];
        
        double monthlyReturn = expectedReturn / 12.0;
        double monthlyVol = marketVolatility / Math.sqrt(12.0);
        
        values[0] = initialValue;
        periods[0] = "2020-01";
        
        for (int i = 1; i < months; i++) {
            // Portfolio return with market shocks
            double marketShock = random.nextGaussian() * monthlyVol;
            double actualReturn = monthlyReturn + marketShock;
            
            // Simulate some market crash scenarios (rare events)
            if (random.nextDouble() < 0.02) { // 2% chance of significant drop
                actualReturn -= 0.05 + random.nextDouble() * 0.10; // 5-15% drop
            }
            
            values[i] = values[i - 1] * (1 + actualReturn);
            
            // Ensure portfolio value doesn't go negative
            values[i] = Math.max(values[i], initialValue * 0.1);
            
            int year = 2020 + ((i - 1) / 12);
            int month = ((i - 1) % 12) + 1;
            periods[i] = String.format("%d-%02d", year, month);
        }
        
        return new FinancialData(values, periods, "Portfolio Value", "USD");
    }
    
    /**
     * Generate economic indicator data (like GDP, inflation)
     * @param startingValue Initial indicator value
     * @param periods Number of periods
     * @param averageGrowth Average growth rate
     * @param cyclicalPattern Cyclical pattern strength
     * @return FinancialData object with economic indicator
     */
    public static FinancialData generateEconomicIndicator(double startingValue, int periods, 
                                                        double averageGrowth, double cyclicalPattern) {
        double[] values = new double[periods];
        String[] periodNames = new String[periods];
        
        values[0] = startingValue;
        periodNames[0] = "2020-Q1";
        
        for (int i = 1; i < periods; i++) {
            // Base growth with economic cycles
            double cycleEffect = cyclicalPattern * Math.sin(2 * Math.PI * i / 16.0); // 4-year cycle
            double randomShock = random.nextGaussian() * 0.02; // 2% volatility
            
            double actualGrowth = averageGrowth + cycleEffect + randomShock;
            values[i] = values[i - 1] * (1 + actualGrowth);
            
            int year = 2020 + ((i - 1) / 4);
            int quarter = ((i - 1) % 4) + 1;
            periodNames[i] = year + "-Q" + quarter;
        }
        
        return new FinancialData(values, periodNames, "Economic Indicator", "Index");
    }
    
    /**
     * Generate bond yield data
     * @param initialYield Initial yield (as decimal)
     * @param months Number of months
     * @param meanReversion Mean reversion strength
     * @param longTermMean Long-term mean yield
     * @return FinancialData object with bond yields
     */
    public static FinancialData generateBondYields(double initialYield, int months, 
                                                 double meanReversion, double longTermMean) {
        double[] yields = new double[months];
        String[] periods = new String[months];
        
        yields[0] = initialYield;
        periods[0] = "2020-01";
        
        for (int i = 1; i < months; i++) {
            // Mean reverting process for interest rates
            double deviation = yields[i - 1] - longTermMean;
            double meanReversionForce = -meanReversion * deviation;
            double randomShock = random.nextGaussian() * 0.002; // 20 basis points volatility
            
            double change = meanReversionForce + randomShock;
            yields[i] = yields[i - 1] + change;
            
            // Keep yields positive
            yields[i] = Math.max(yields[i], 0.001); // Minimum 10 basis points
            
            int year = 2020 + ((i - 1) / 12);
            int month = ((i - 1) % 12) + 1;
            periods[i] = String.format("%d-%02d", year, month);
        }
        
        return new FinancialData(yields, periods, "Bond Yield", "%");
    }
    
    /**
     * Generate commodity price data
     * @param startingPrice Initial commodity price
     * @param months Number of months
     * @param volatility Price volatility
     * @param supplyShockProbability Probability of supply shock per month
     * @return FinancialData object with commodity prices
     */
    public static FinancialData generateCommodityPrices(double startingPrice, int months, 
                                                      double volatility, double supplyShockProbability) {
        double[] prices = new double[months];
        String[] periods = new String[months];
        
        prices[0] = startingPrice;
        periods[0] = "2020-01";
        
        for (int i = 1; i < months; i++) {
            // Base price movement
            double normalChange = random.nextGaussian() * volatility;
            
            // Supply shock events
            double shockEffect = 0.0;
            if (random.nextDouble() < supplyShockProbability) {
                // Random supply shock (positive or negative)
                shockEffect = (random.nextDouble() - 0.5) * 0.3; // Up to 15% shock
            }
            
            double totalChange = normalChange + shockEffect;
            prices[i] = prices[i - 1] * (1 + totalChange);
            
            // Prevent negative prices
            prices[i] = Math.max(prices[i], startingPrice * 0.1);
            
            int year = 2020 + ((i - 1) / 12);
            int month = ((i - 1) % 12) + 1;
            periods[i] = String.format("%d-%02d", year, month);
        }
        
        return new FinancialData(prices, periods, "Commodity Price", "USD");
    }
    
    /**
     * Generate sample datasets for testing
     * @return List of various FinancialData objects for demonstration
     */
    public static List<FinancialData> generateSampleDatasets() {
        List<FinancialData> datasets = new ArrayList<FinancialData>();
        
        // Various sample datasets
        datasets.add(generateQuarterlyRevenue(1000000, 20, 0.05, 0.02));
        datasets.add(generateMonthlyStockPrices(100.0, 36, 0.08, 0.20));
        datasets.add(generateAnnualExpenses(500000, 10, 0.03, 0.02));
        datasets.add(generateCyclicalSales(250000, 16, 0.15, 0.03));
        datasets.add(generatePortfolioValue(100000, 24, 0.07, 0.15));
        datasets.add(generateEconomicIndicator(100.0, 20, 0.025, 0.01));
        datasets.add(generateBondYields(0.025, 36, 0.1, 0.03));
        datasets.add(generateCommodityPrices(50.0, 24, 0.05, 0.05));
        
        return datasets;
    }
    
    /**
     * Generate trending data with specified characteristics
     * @param initialValue Starting value
     * @param periods Number of periods
     * @param trendStrength Strength of the trend
     * @param isUptrend True for uptrend, false for downtrend
     * @param dataType Description of data type
     * @return FinancialData object with trending pattern
     */
    public static FinancialData generateTrendingData(double initialValue, int periods, 
                                                   double trendStrength, boolean isUptrend, 
                                                   String dataType) {
        double[] values = new double[periods];
        String[] periodNames = new String[periods];
        
        values[0] = initialValue;
        
        double growthRate = isUptrend ? trendStrength : -trendStrength;
        
        for (int i = 0; i < periods; i++) {
            if (i > 0) {
                // Apply trend with some random variation
                double variation = random.nextGaussian() * 0.01; // 1% volatility
                values[i] = values[i - 1] * (1 + growthRate + variation);
            }
            
            // Generate monthly periods
            int year = 2020 + (i / 12);
            int month = (i % 12) + 1;
            periodNames[i] = String.format("%d-%02d", year, month);
        }
        
        return new FinancialData(values, periodNames, dataType, "USD");
    }
    
    /**
     * Generate volatile data with high fluctuations
     * @param baseValue Base value around which data fluctuates
     * @param periods Number of periods
     * @param volatilityLevel Volatility level (higher = more volatile)
     * @param dataType Description of data type
     * @return FinancialData object with volatile pattern
     */
    public static FinancialData generateVolatileData(double baseValue, int periods, 
                                                   double volatilityLevel, String dataType) {
        double[] values = new double[periods];
        String[] periodNames = new String[periods];
        
        for (int i = 0; i < periods; i++) {
            // High volatility around base value
            double volatileChange = random.nextGaussian() * volatilityLevel;
            values[i] = baseValue * (1 + volatileChange);
            
            // Ensure positive values
            values[i] = Math.max(values[i], baseValue * 0.1);
            
            int year = 2020 + (i / 12);
            int month = (i % 12) + 1;
            periodNames[i] = String.format("%d-%02d", year, month);
        }
        
        return new FinancialData(values, periodNames, dataType, "USD");
    }
    
    /**
     * Display information about all sample datasets
     */
    public static void displaySampleDataInfo() {
        System.out.println("=== Available Sample Financial Datasets ===");
        System.out.println("1. Quarterly Revenue - Corporate revenue growth over 5 years");
        System.out.println("2. Monthly Stock Prices - Stock price movements over 3 years");
        System.out.println("3. Annual Expenses - Company expense growth over 10 years");
        System.out.println("4. Cyclical Sales - Seasonal sales patterns over 4 years");
        System.out.println("5. Portfolio Value - Investment portfolio performance over 2 years");
        System.out.println("6. Economic Indicator - Economic index with business cycles");
        System.out.println("7. Bond Yields - Interest rate movements over 3 years");
        System.out.println("8. Commodity Prices - Commodity price volatility over 2 years");
        System.out.println();
        
        System.out.println("Each dataset includes:");
        System.out.println("- Historical time series data");
        System.out.println("- Realistic financial patterns");
        System.out.println("- Growth rate calculations");
        System.out.println("- Statistical measures");
        System.out.println();
    }
} 