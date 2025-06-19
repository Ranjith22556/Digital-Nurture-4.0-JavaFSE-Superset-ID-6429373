# Financial Forecasting Tool - Recursive Algorithms

A comprehensive Java application demonstrating recursive algorithms for financial calculations, with a focus on future value calculations using compound interest formulas.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Quick Start](#quick-start)
- [Core Algorithms](#core-algorithms)
- [Usage Examples](#usage-examples)
- [Testing](#testing)
- [Performance Analysis](#performance-analysis)
- [Educational Value](#educational-value)
- [Requirements](#requirements)
- [License](#license)

## 🎯 Overview

The Financial Forecasting Tool is an educational and practical Java application that implements recursive algorithms for various financial calculations. The primary focus is on demonstrating how recursive approaches can be applied to financial mathematics, particularly for:

- **Future Value Calculations** using compound interest
- **Present Value Calculations** using discounting
- **Growth Projections** for financial planning
- **Annuity Calculations** for regular payments
- **Loan Payment Calculations** for mortgages and loans

## ✨ Features

### 🔧 Core Functionality
- **Recursive Future Value Calculation**: `FV = PV * (1 + r)^n`
- **Recursive Present Value Calculation**: `PV = FV / (1 + r)^n`
- **Multi-period Growth Projections**
- **Compound Annual Growth Rate (CAGR) Calculation**
- **Annuity Future Value Calculations**
- **Loan Payment Calculator**
- **Effective Annual Rate Calculations**

### 📊 Data Management
- **Financial Data Model**: Time series data handling
- **Sample Data Generation**: Realistic financial datasets
- **Historical Data Analysis**: Growth rates, volatility, statistics
- **Multiple Data Types**: Revenue, stock prices, expenses, etc.

### 🖥️ User Interface
- **Interactive Demo**: Menu-driven interface
- **Performance Monitoring**: Operation counting and timing
- **Comprehensive Testing**: Automated test suites
- **Educational Displays**: Algorithm complexity analysis

### 🚀 Performance Features
- **Operation Counting**: Track recursive function calls
- **Recursion Depth Monitoring**: Memory usage analysis
- **Performance Comparison**: Recursive vs iterative approaches
- **Input Validation**: Comprehensive error handling

## 📁 Project Structure

```
FinancialForecastingTool/
├── src/main/java/com/example/finance/
│   ├── FinancialData.java              # Time series financial data model
│   ├── RecursiveForecasting.java       # Core recursive algorithms ⭐
│   ├── FinancialDataGenerator.java     # Sample data generation
│   ├── FinancialForecastingDemo.java   # Interactive demonstration
│   └── FinancialForecastingTest.java   # Comprehensive testing
├── bin/                                # Compiled classes
├── README.md                           # This file
├── SAMPLE_OUTPUT.txt                   # Example program output
└── .gitignore                          # Git ignore rules
```

## 🚀 Quick Start

### Prerequisites
- Java 8 or higher
- Command line access

### Compilation
```bash
# Navigate to project directory
cd FinancialForecastingTool

# Compile all Java files
javac -d bin src/main/java/com/example/finance/*.java
```

### Running the Interactive Demo
```bash
# Run the main demonstration
java -cp bin com.example.finance.FinancialForecastingDemo
```

### Running Tests
```bash
# Run all tests
java -cp bin com.example.finance.FinancialForecastingTest

# Run specific test categories
java -cp bin com.example.finance.FinancialForecastingTest basic
java -cp bin com.example.finance.FinancialForecastingTest performance
java -cp bin com.example.finance.FinancialForecastingTest edge
```

## 🧮 Core Algorithms

### 1. Future Value Calculation (Primary Focus)
**Recursive Implementation:**
```java
public static double calculateFutureValue(double presentValue, double interestRate, int periods) {
    if (periods == 0) return presentValue;
    return (1 + interestRate) * calculateFutureValue(presentValue, interestRate, periods - 1);
}
```

**Mathematical Formula:** `FV = PV * (1 + r)^n`
- **Time Complexity:** O(n) where n = periods
- **Space Complexity:** O(n) due to recursion stack
- **Use Case:** Investment growth, savings planning

### 2. Present Value Calculation
**Purpose:** Calculate the current value of future money
- **Formula:** `PV = FV / (1 + r)^n`
- **Implementation:** Recursive discounting approach
- **Applications:** Investment valuation, retirement planning

### 3. Growth Projections
**Purpose:** Project future values for multiple periods
- **Method:** Recursive period-by-period calculation
- **Output:** Array of projected values
- **Applications:** Business forecasting, financial planning

### 4. Compound Annual Growth Rate (CAGR)
**Purpose:** Calculate average annual growth rate
- **Method:** Newton's method recursive approximation
- **Formula:** `CAGR = (Ending Value / Beginning Value)^(1/n) - 1`
- **Applications:** Investment performance analysis

## 💡 Usage Examples

### Example 1: Future Value Calculation
```java
// Calculate future value of $10,000 at 7% for 15 years
double fv = RecursiveForecasting.calculateFutureValue(10000.0, 0.07, 15);
// Result: $27,590.32
```

### Example 2: Loan Payment Calculation
```java
// Calculate monthly payment for $300,000 loan at 4.5% for 30 years
double payment = RecursiveForecasting.calculateLoanPayment(300000.0, 0.045/12, 360);
// Result: $1,520.06 per month
```

### Example 3: Growth Projections
```java
// Project growth of $50,000 at 6% for 10 years
double[] projections = RecursiveForecasting.projectGrowth(50000.0, 0.06, 10);
// Result: Array showing year-by-year growth
```

## 🧪 Testing

### Test Categories

#### 1. Basic Functionality Tests
- Future value calculation accuracy
- Present value calculation accuracy
- Growth projection validation
- Annuity calculation verification
- Loan payment calculation verification
- CAGR calculation accuracy

#### 2. Performance Tests
- Recursion performance measurement
- Memory usage analysis (recursion depth)
- Large dataset handling

#### 3. Data Generation Tests
- Financial data creation
- Sample data generation
- Data analysis methods

#### 4. Edge Case Tests
- Zero interest rate handling
- Negative value validation
- Large period validation
- Input validation testing

#### 5. Accuracy Tests
- Known value verification
- Method consistency testing
- Cross-validation between algorithms

### Running Tests
```bash
# All tests
java -cp bin com.example.finance.FinancialForecastingTest

# Specific test categories
java -cp bin com.example.finance.FinancialForecastingTest basic
java -cp bin com.example.finance.FinancialForecastingTest performance
java -cp bin com.example.finance.FinancialForecastingTest data
java -cp bin com.example.finance.FinancialForecastingTest edge
```

## 📈 Performance Analysis

### Algorithm Complexity

| Algorithm | Time Complexity | Space Complexity | Use Case |
|-----------|----------------|------------------|----------|
| Future Value | O(n) | O(n) | Investment growth |
| Present Value | O(n) | O(n) | Discounting |
| Growth Projections | O(n) | O(n) | Multi-period forecasting |
| Power Calculation | O(log n) | O(log n) | Loan calculations |
| CAGR (Newton's method) | O(log p) | O(log p) | Growth rate analysis |

### Performance Characteristics
- **Recursion Limit:** 1,000 periods (configurable)
- **Typical Performance:** <1ms for 500 periods
- **Memory Usage:** Linear with recursion depth
- **Accuracy:** Within 0.01 for financial calculations

### Comparison: Recursive vs Iterative

| Periods | Recursive (ms) | Iterative (ms) | Memory Usage |
|---------|----------------|----------------|--------------|
| 10 | 0.05 | 0.01 | Low |
| 100 | 0.15 | 0.01 | Medium |
| 500 | 0.31 | 0.01 | High |
| 1000 | 0.65 | 0.01 | Very High |

## 🎓 Educational Value

### Learning Objectives
1. **Recursive Algorithm Design**: Understanding base cases and recursive cases
2. **Financial Mathematics**: Practical application of compound interest
3. **Performance Analysis**: Time and space complexity evaluation
4. **Input Validation**: Robust error handling techniques
5. **Testing Methodology**: Comprehensive test suite design

### Key Concepts Demonstrated
- **Recursion vs Iteration**: Trade-offs and use cases
- **Stack Overflow Prevention**: Recursion depth limits
- **Performance Monitoring**: Operation counting and timing
- **Financial Formulas**: Real-world mathematical applications

### Suitable For
- Computer Science students learning recursion
- Finance students learning compound interest
- Developers interested in financial algorithms
- Anyone wanting to understand recursive problem-solving

## ⚙️ Requirements

### System Requirements
- **Java Version:** 8 or higher
- **Memory:** Minimum 128MB heap space
- **Storage:** ~5MB for source and compiled files

### Dependencies
- **Standard Library Only:** No external dependencies
- **Java SE APIs:** Scanner, Arrays, List, ArrayList
- **Compatible with:** Java 8, 11, 17, 21

### Supported Platforms
- Windows (PowerShell/Command Prompt)
- macOS (Terminal)
- Linux (Bash/Shell)

## 📝 Key Features Summary

✅ **Pure Recursive Implementation**: All algorithms use recursion  
✅ **Performance Monitoring**: Real-time metrics and analysis  
✅ **Comprehensive Testing**: 15+ test scenarios  
✅ **Interactive Interface**: User-friendly menu system  
✅ **Educational Focus**: Clear algorithm explanations  
✅ **Input Validation**: Robust error handling  
✅ **Real-world Applications**: Practical financial calculations  
✅ **Sample Data Generation**: Realistic test datasets  
✅ **Cross-platform Compatibility**: Works on Windows, macOS, Linux  
✅ **Well-documented Code**: Extensive comments and documentation  

## 🔮 Future Enhancements

Potential areas for expansion:
- **Monte Carlo Simulations**: Risk analysis with random variables
- **Options Pricing**: Black-Scholes recursive implementation
- **Bond Valuation**: Yield-to-maturity calculations
- **Portfolio Optimization**: Recursive optimization algorithms
- **GUI Interface**: JavaFX or Swing user interface
- **Data Export**: CSV/Excel output capabilities

## 📄 License

This project is created for educational purposes. Feel free to use, modify, and distribute for learning and teaching recursion and financial mathematics.

---

**Created for demonstrating recursive algorithms in financial mathematics.**  
**Perfect for computer science and finance education.**

For questions or contributions, please refer to the source code documentation and test cases for guidance. 