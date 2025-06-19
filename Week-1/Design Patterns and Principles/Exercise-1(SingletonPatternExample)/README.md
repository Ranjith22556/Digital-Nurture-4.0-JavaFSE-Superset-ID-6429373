# Singleton Pattern Example - Logger Implementation

This project demonstrates the implementation of the **Singleton Design Pattern** using a Logger utility class in Java.

## Project Structure

```
SingletonPatternExample/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   └── singleton/
│                       ├── Logger.java          # Singleton Logger class
│                       ├── SingletonTest.java   # Test class for Singleton verification
│                       └── Main.java           # Main application entry point
└── README.md
```

## Features

### Logger Class (Singleton Implementation)
- **Private Constructor**: Prevents external instantiation
- **Static Instance**: Single instance maintained throughout application lifecycle
- **Thread-Safe**: Synchronized getInstance() method ensures thread safety
- **Logging Methods**: Supports INFO, WARNING, and ERROR level logging
- **Timestamp Support**: All log messages include formatted timestamps

### Key Singleton Pattern Elements
1. **Private static instance** of the Logger class
2. **Private constructor** to prevent external instantiation
3. **Public static getInstance()** method to provide global access point
4. **Thread-safe implementation** using synchronized method

## How to Run

### Option 1: Using Command Line

1. **Navigate to the project directory**:
   ```bash
   cd SingletonPatternExample
   ```

2. **Compile the Java files**:
   ```bash
   javac -d . src/main/java/com/example/singleton/*.java
   ```

3. **Run the main application**:
   ```bash
   java com.example.singleton.Main
   ```

4. **Run only the singleton tests**:
   ```bash
   java com.example.singleton.SingletonTest
   ```
### Option 2: Using Command Line
1. **Navigate to the project directory**:
   ```bash
   cd SingletonPatternExample
   ```
2. **Compile the Java files**:
   ```bash
   java -cp bin com.example.singleton.SingletonTest
   ```


## What the Application Demonstrates

### 1. Singleton Behavior Verification
- Creates multiple Logger instances and verifies they are the same object
- Compares hash codes and object references
- Confirms thread-safe singleton implementation

### 2. Practical Logging Usage
- Demonstrates real-world usage of the Logger singleton
- Shows consistent logging across different parts of the application
- Simulates typical application scenarios (login, data processing, file operations)

### 3. Thread Safety
- Tests singleton behavior in multi-threaded environment
- Ensures only one instance is created even with concurrent access

## Expected Output

The application will show:
1. **Application logs** demonstrating practical usage
2. **Singleton verification tests** confirming pattern implementation
3. **Thread safety tests** ensuring proper behavior in concurrent scenarios

## Key Learning Points

1. **Single Instance**: Only one Logger instance exists throughout the application
2. **Global Access**: The Logger can be accessed from anywhere in the application
3. **Thread Safety**: Multiple threads can safely access the singleton instance
4. **Memory Efficiency**: Reduces memory footprint by having only one instance
5. **Consistent State**: All parts of the application use the same logger configuration

## Singleton Pattern Benefits Demonstrated

- **Controlled Access**: Ensures only one instance exists
- **Global Point of Access**: Available throughout the application
- **Lazy Initialization**: Instance created only when first requested
- **Thread Safety**: Safe to use in multi-threaded applications
- **Memory Efficiency**: Single instance reduces memory usage 