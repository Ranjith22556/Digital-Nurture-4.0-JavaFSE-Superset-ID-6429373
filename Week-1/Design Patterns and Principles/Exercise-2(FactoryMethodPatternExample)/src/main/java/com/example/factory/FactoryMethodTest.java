package com.example.factory;

/**
 * Test class to demonstrate the Factory Method Pattern implementation.
 * This class shows how different document types are created using their respective factories.
 */
public class FactoryMethodTest {
    
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Test for Document Management System ===\n");
        
        // Test 1: Basic factory method usage
        testBasicFactoryMethods();
        
        System.out.println("\n======================================================================\n");
        
        // Test 2: Factory method with filenames
        testFactoryMethodsWithFilenames();
        
        System.out.println("\n======================================================================\n");
        
        // Test 3: Template method usage
        testTemplateMethod();
        
        System.out.println("\n======================================================================\n");
        
        // Test 4: Specific factory features
        testSpecificFactoryFeatures();
        
        System.out.println("\n======================================================================\n");
        
        // Test 5: Polymorphic behavior
        testPolymorphicBehavior();
        
        System.out.println("\n=== All Factory Method Tests Completed Successfully ===");
    }
    
    /**
     * Test basic factory method creation without parameters
     */
    private static void testBasicFactoryMethods() {
        System.out.println("Test 1: Basic Factory Method Usage");
        System.out.println("----------------------------------");
        
        // Create factories
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        
        // Use factory methods to create documents
        System.out.println("Creating documents using factory methods:");
        
        Document wordDoc = wordFactory.createDocument();
        Document pdfDoc = pdfFactory.createDocument();
        Document excelDoc = excelFactory.createDocument();
        
        // Test basic operations
        System.out.println("\nTesting basic document operations:");
        wordDoc.open();
        wordDoc.save();
        wordDoc.close();
        
        pdfDoc.print();
        excelDoc.print();
    }
    
    /**
     * Test factory methods with filename parameters
     */
    private static void testFactoryMethodsWithFilenames() {
        System.out.println("Test 2: Factory Methods with Filenames");
        System.out.println("---------------------------------------");
        
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        
        // Create documents with specific filenames
        Document contract = wordFactory.createDocument("Business_Contract");
        Document manual = pdfFactory.createDocument("User_Manual");
        Document budget = excelFactory.createDocument("Annual_Budget");
        
        System.out.println("\nDocument types created:");
        System.out.println("- " + contract.getDocumentType());
        System.out.println("- " + manual.getDocumentType());
        System.out.println("- " + budget.getDocumentType());
    }
    
    /**
     * Test template method that uses factory methods
     */
    private static void testTemplateMethod() {
        System.out.println("Test 3: Template Method Usage");
        System.out.println("-----------------------------");
        
        DocumentFactory[] factories = {
            new WordDocumentFactory(),
            new PdfDocumentFactory(),
            new ExcelDocumentFactory()
        };
        
        System.out.println("Using template method to create and open documents:");
        
        for (DocumentFactory factory : factories) {
            System.out.println("\nUsing " + factory.getFactoryType() + ":");
            Document doc = factory.createAndOpenDocument("Template_Test_File");
            doc.close();
        }
    }
    
    /**
     * Test specific features of each factory
     */
    private static void testSpecificFactoryFeatures() {
        System.out.println("Test 4: Specific Factory Features");
        System.out.println("---------------------------------");
        
        // Test Word-specific features
        System.out.println("Testing Word-specific features:");
        WordDocumentFactory wordFactory = new WordDocumentFactory();
        WordDocument resumeDoc = wordFactory.createDocumentWithTemplate("MyResume", "resume");
        resumeDoc.spellCheck();
        resumeDoc.close();
        
        System.out.println("\nTesting PDF-specific features:");
        PdfDocumentFactory pdfFactory = new PdfDocumentFactory();
        PdfDocument secureDoc = pdfFactory.createSecureDocument("Confidential_Report", true);
        PdfDocument signedDoc = pdfFactory.createSignedDocument("Legal_Document", true);
        secureDoc.close();
        signedDoc.close();
        
        System.out.println("\nTesting Excel-specific features:");
        ExcelDocumentFactory excelFactory = new ExcelDocumentFactory();
        String[] sheetNames = {"Sales", "Marketing", "Finance"};
        ExcelDocument workbook = excelFactory.createWorkbookWithSheets("Company_Data", sheetNames);
        ExcelDocument chartDoc = excelFactory.createDocumentWithChart("Sales_Analysis", "Bar Chart");
        workbook.close();
        chartDoc.close();
    }
    
    /**
     * Test polymorphic behavior of the factory pattern
     */
    private static void testPolymorphicBehavior() {
        System.out.println("Test 5: Polymorphic Behavior");
        System.out.println("----------------------------");
        
        // Array of different factories treated polymorphically
        DocumentFactory[] factories = {
            new WordDocumentFactory(),
            new PdfDocumentFactory(),
            new ExcelDocumentFactory()
        };
        
        String[] fileNames = {"Report", "Manual", "Spreadsheet"};
        
        System.out.println("Creating different document types polymorphically:");
        
        for (int i = 0; i < factories.length; i++) {
            DocumentFactory factory = factories[i];
            String fileName = fileNames[i];
            
            System.out.println("\nFactory Type: " + factory.getFactoryType());
            
            // Polymorphic call - same method, different implementations
            Document document = factory.createDocument(fileName);
            document.open();
            document.print();
            document.save();
            document.close();
        }
        
        System.out.println("\nPolymorphic behavior demonstrated successfully!");
        System.out.println("All factories implement the same interface but create different document types.");
    }
} 