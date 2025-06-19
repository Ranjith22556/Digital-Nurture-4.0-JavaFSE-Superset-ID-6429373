package com.example.finance;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * FinancialData class represents time series financial data
 * Used for storing historical values and calculating growth rates
 */
public class FinancialData {
    
    private double[] values;
    private String[] periods;
    private String dataType;
    private String currency;
    
    /**
     * Constructor with arrays
     * @param values Historical values
     * @param periods Time periods (e.g., "2020-Q1", "2020-Q2")
     * @param dataType Type of data (e.g., "Revenue", "Stock Price")
     * @param currency Currency symbol (e.g., "USD", "EUR")
     */
    public FinancialData(double[] values, String[] periods, String dataType, String currency) {
        if (values.length != periods.length) {
            throw new IllegalArgumentException("Values and periods arrays must have the same length");
        }
        this.values = Arrays.copyOf(values, values.length);
        this.periods = Arrays.copyOf(periods, periods.length);
        this.dataType = dataType;
        this.currency = currency;
    }
    
    /**
     * Constructor with lists
     * @param values List of historical values
     * @param periods List of time periods
     * @param dataType Type of data
     * @param currency Currency symbol
     */
    public FinancialData(List<Double> values, List<String> periods, String dataType, String currency) {
        if (values.size() != periods.size()) {
            throw new IllegalArgumentException("Values and periods lists must have the same size");
        }
        
        this.values = new double[values.size()];
        this.periods = new String[periods.size()];
        
        for (int i = 0; i < values.size(); i++) {
            this.values[i] = values.get(i);
            this.periods[i] = periods.get(i);
        }
        
        this.dataType = dataType;
        this.currency = currency;
    }
    
    /**
     * Get the number of data points
     * @return Number of data points
     */
    public int size() {
        return values.length;
    }
    
    /**
     * Get value at specific index
     * @param index Index of the value
     * @return Value at the specified index
     */
    public double getValue(int index) {
        if (index < 0 || index >= values.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + values.length);
        }
        return values[index];
    }
    
    /**
     * Get period at specific index
     * @param index Index of the period
     * @return Period at the specified index
     */
    public String getPeriod(int index) {
        if (index < 0 || index >= periods.length) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + periods.length);
        }
        return periods[index];
    }
    
    /**
     * Get the latest value
     * @return Most recent value
     */
    public double getLatestValue() {
        if (values.length == 0) {
            throw new IllegalStateException("No data available");
        }
        return values[values.length - 1];
    }
    
    /**
     * Get the latest period
     * @return Most recent period
     */
    public String getLatestPeriod() {
        if (periods.length == 0) {
            throw new IllegalStateException("No data available");
        }
        return periods[periods.length - 1];
    }
    
    /**
     * Calculate simple growth rate between two consecutive periods
     * @param fromIndex Starting index
     * @param toIndex Ending index
     * @return Growth rate as decimal (e.g., 0.05 for 5% growth)
     */
    public double calculateGrowthRate(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= values.length || fromIndex >= toIndex) {
            throw new IllegalArgumentException("Invalid index range");
        }
        
        double fromValue = values[fromIndex];
        double toValue = values[toIndex];
        
        if (fromValue == 0) {
            throw new ArithmeticException("Cannot calculate growth rate with zero starting value");
        }
        
        return (toValue - fromValue) / fromValue;
    }
    
    /**
     * Calculate period-over-period growth rates
     * @return Array of growth rates between consecutive periods
     */
    public double[] calculatePeriodicGrowthRates() {
        if (values.length < 2) {
            return new double[0];
        }
        
        double[] growthRates = new double[values.length - 1];
        for (int i = 0; i < growthRates.length; i++) {
            try {
                growthRates[i] = calculateGrowthRate(i, i + 1);
            } catch (ArithmeticException e) {
                growthRates[i] = 0.0; // Handle zero values
            }
        }
        return growthRates;
    }
    
    /**
     * Calculate average growth rate
     * @return Average growth rate across all periods
     */
    public double calculateAverageGrowthRate() {
        double[] growthRates = calculatePeriodicGrowthRates();
        if (growthRates.length == 0) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (double rate : growthRates) {
            sum += rate;
        }
        return sum / growthRates.length;
    }
    
    /**
     * Calculate compound annual growth rate (CAGR)
     * @param yearsSpan Number of years the data spans
     * @return CAGR as decimal
     */
    public double calculateCAGR(double yearsSpan) {
        if (values.length < 2 || yearsSpan <= 0) {
            return 0.0;
        }
        
        double startValue = values[0];
        double endValue = values[values.length - 1];
        
        if (startValue <= 0) {
            return 0.0;
        }
        
        return Math.pow(endValue / startValue, 1.0 / yearsSpan) - 1.0;
    }
    
    /**
     * Get array copy of all values
     * @return Copy of values array
     */
    public double[] getValues() {
        return Arrays.copyOf(values, values.length);
    }
    
    /**
     * Get array copy of all periods
     * @return Copy of periods array
     */
    public String[] getPeriods() {
        return Arrays.copyOf(periods, periods.length);
    }
    
    /**
     * Get data type
     * @return Data type description
     */
    public String getDataType() {
        return dataType;
    }
    
    /**
     * Get currency
     * @return Currency symbol
     */
    public String getCurrency() {
        return currency;
    }
    
    /**
     * Add a new data point
     * @param value New value
     * @param period New period
     */
    public void addDataPoint(double value, String period) {
        double[] newValues = new double[values.length + 1];
        String[] newPeriods = new String[periods.length + 1];
        
        System.arraycopy(values, 0, newValues, 0, values.length);
        System.arraycopy(periods, 0, newPeriods, 0, periods.length);
        
        newValues[values.length] = value;
        newPeriods[periods.length] = period;
        
        this.values = newValues;
        this.periods = newPeriods;
    }
    
    /**
     * Get subset of data from start to end index
     * @param startIndex Starting index (inclusive)
     * @param endIndex Ending index (exclusive)
     * @return New FinancialData object with subset
     */
    public FinancialData getSubset(int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex > values.length || startIndex >= endIndex) {
            throw new IllegalArgumentException("Invalid index range");
        }
        
        int size = endIndex - startIndex;
        double[] subValues = new double[size];
        String[] subPeriods = new String[size];
        
        System.arraycopy(values, startIndex, subValues, 0, size);
        System.arraycopy(periods, startIndex, subPeriods, 0, size);
        
        return new FinancialData(subValues, subPeriods, dataType, currency);
    }
    
    /**
     * Calculate volatility (standard deviation of growth rates)
     * @return Volatility as decimal
     */
    public double calculateVolatility() {
        double[] growthRates = calculatePeriodicGrowthRates();
        if (growthRates.length < 2) {
            return 0.0;
        }
        
        double mean = calculateAverageGrowthRate();
        double sumSquaredDiffs = 0.0;
        
        for (double rate : growthRates) {
            double diff = rate - mean;
            sumSquaredDiffs += diff * diff;
        }
        
        return Math.sqrt(sumSquaredDiffs / (growthRates.length - 1));
    }
    
    /**
     * Format currency value
     * @param value Value to format
     * @return Formatted string
     */
    public String formatCurrency(double value) {
        return String.format("%s %.2f", currency, value);
    }
    
    /**
     * Format percentage
     * @param rate Rate as decimal
     * @return Formatted percentage string
     */
    public String formatPercentage(double rate) {
        return String.format("%.2f%%", rate * 100);
    }
    
    /**
     * Display summary statistics
     */
    public void displaySummary() {
        System.out.println("=== " + dataType + " Summary ===");
        System.out.println("Data Points: " + values.length);
        System.out.println("Period Range: " + periods[0] + " to " + getLatestPeriod());
        System.out.println("Latest Value: " + formatCurrency(getLatestValue()));
        
        if (values.length >= 2) {
            System.out.println("Average Growth Rate: " + formatPercentage(calculateAverageGrowthRate()));
            System.out.println("Volatility: " + formatPercentage(calculateVolatility()));
        }
        
        System.out.println();
    }
    
    /**
     * Convert to string representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dataType).append(" (").append(currency).append("):\n");
        
        for (int i = 0; i < Math.min(values.length, 5); i++) {
            sb.append(periods[i]).append(": ").append(formatCurrency(values[i])).append("\n");
        }
        
        if (values.length > 5) {
            sb.append("... and ").append(values.length - 5).append(" more data points\n");
        }
        
        return sb.toString();
    }
    
    /**
     * Validate data integrity
     * @return true if data is valid, false otherwise
     */
    public boolean isValid() {
        if (values.length == 0 || periods.length == 0) {
            return false;
        }
        
        for (double value : values) {
            if (Double.isNaN(value) || Double.isInfinite(value)) {
                return false;
            }
        }
        
        for (String period : periods) {
            if (period == null || period.trim().isEmpty()) {
                return false;
            }
        }
        
        return true;
    }
} 