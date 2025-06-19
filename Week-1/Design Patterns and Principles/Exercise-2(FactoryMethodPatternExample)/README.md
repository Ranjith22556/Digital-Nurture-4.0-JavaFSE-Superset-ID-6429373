# Factory Method Pattern Example - Document Management System

This project demonstrates the implementation of the **Factory Method Design Pattern** using a document management system that creates different types of documents (Word, PDF, Excel).

## Project Structure

```
FactoryMethodPatternExample/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   └── factory/
│                       ├── Document.java                  # Document interface
│                       ├── WordDocument.java              # Word document implementation
│                       ├── PdfDocument.java               # PDF document implementation
│                       ├── ExcelDocument.java             # Excel document implementation
│                       ├── DocumentFactory.java           # Abstract factory class
│                       ├── WordDocumentFactory.java       # Word document factory
│                       ├── PdfDocumentFactory.java        # PDF document factory
│                       ├── ExcelDocumentFactory.java      # Excel document factory
│                       ├── FactoryMethodTest.java         # Comprehensive test class
│                       └── Main.java                      # Main application entry point
└── README.md
```

## Factory Method Pattern Components

### 1. **Product Interface/Abstract Class**
- `Document.java` - Defines the common interface for all document types

### 2. **Concrete Products** 
- `WordDocument.java` - Microsoft Word document implementation
- `PdfDocument.java` - PDF document implementation  
- `ExcelDocument.java` - Excel spreadsheet implementation

### 3. **Creator (Abstract Factory)**
- `DocumentFactory.java` - Abstract class defining the factory method

### 4. **Concrete Creators (Concrete Factories)**
- `WordDocumentFactory.java` - Creates Word documents
- `PdfDocumentFactory.java` - Creates PDF documents
- `ExcelDocumentFactory.java` - Creates Excel documents

## Key Features

### Document Interface
- **Common Operations**: `open()`, `save()`, `close()`, `print()`
- **Type Identification**: `getDocumentType()`
- **Polymorphic Behavior**: All documents implement the same interface

### Document-Specific Features

#### Word Documents
- Spell checking functionality
- Template-based creation (letter, resume, report)
- Rich text formatting support

#### PDF Documents
- Password protection capabilities
- Digital signature support
- Fixed layout preservation

#### Excel Documents
- Multiple worksheet management
- Chart creation functionality
- Formula calculations

### Factory Method Benefits Demonstrated
1. **Encapsulation**: Object creation logic is encapsulated in factories
2. **Extensibility**: Easy to add new document types
3. **Polymorphism**: Same interface for creating different document types
4. **Single Responsibility**: Each factory handles one document type
5. **Open/Closed Principle**: Open for extension, closed for modification

## How to Run

### Option 1: Using Batch Files (Windows)

1. **Navigate to project directory**:
   ```bash
   cd FactoryMethodPatternExample
   ```

2. **Run complete application**:
   ```bash
   .\compile-and-run.bat
   ```

3. **Run tests only**:
   ```bash
   .\run-tests.bat
   ```

### Option 2: Using Command Line

1. **Compile the project**:
   ```bash
   javac -source 8 -target 8 -d bin src/main/java/com/example/factory/*.java
   ```

2. **Run main application**:
   ```bash
   java -cp bin com.example.factory.Main
   ```

3. **Run tests only**:
   ```bash
   java -cp bin com.example.factory.FactoryMethodTest
   ```

### Option 3: Using IDE
1. Import the project into your Java IDE
2. Run `Main.java` for complete demonstration
3. Run `FactoryMethodTest.java` for pattern verification

## What the Application Demonstrates

### 1. **Basic Factory Method Usage**
- Creating documents using different factories
- Demonstrating polymorphic behavior
- Basic document operations

### 2. **Real-World Scenario**
- Business project document creation workflow
- Different document types for different purposes
- Document management operations

### 3. **Pattern Verification Tests**
- Factory method implementation correctness
- Template method usage
- Specific factory features
- Polymorphic behavior verification

### 4. **Advanced Features**
- Document-specific functionality (spell check, digital signatures, charts)
- Template-based document creation
- Security features (password protection)

## Design Pattern Benefits

### **Flexibility**
- Easy to add new document types without modifying existing code
- Different creation strategies for each document type

### **Maintainability** 
- Each factory responsible for one document type
- Clear separation of concerns
- Easy to test and debug

### **Extensibility**
- New document types can be added by creating new factories
- Existing code remains unchanged

### **Polymorphism**
- Client code works with Document interface
- Runtime decision on which concrete document to create
- Same interface for all document operations

## Expected Output

The application demonstrates:

1. **Document Creation Process**: Shows how different factories create their respective document types
2. **Document Operations**: Open, save, close, print operations for each document type
3. **Specific Features**: Document-type specific functionality (spell check, charts, signatures)
4. **Polymorphic Behavior**: Same interface used for different document types
5. **Template Method Usage**: How abstract factory coordinates document creation

## Key Learning Points

1. **Factory Method Pattern**: Delegates object creation to subclasses
2. **Template Method Pattern**: Abstract class provides workflow, subclasses implement details
3. **Polymorphism**: Same interface, different implementations
4. **Encapsulation**: Creation logic hidden within factories
5. **SOLID Principles**: Single responsibility, open/closed principle demonstrated

## Real-World Applications

This pattern is commonly used in:
- **GUI Frameworks**: Creating different UI components
- **Database Connections**: Creating connections for different database types
- **File Parsers**: Creating parsers for different file formats
- **Game Development**: Creating different types of game objects
- **Plugin Systems**: Loading different plugin implementations 