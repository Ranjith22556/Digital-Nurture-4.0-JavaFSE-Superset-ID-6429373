package com.example.factory;

/**
 * Main class to demonstrate the Factory Method Pattern in a Document Management System.
 * This class shows practical usage of the pattern in creating different document types.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Document Management System - Factory Method Pattern Demo ===\n");
        
        // Demonstrate practical usage scenario
        demonstrateDocumentManagementSystem();
        
        System.out.println("\n============================================================\n");
        
        // Run comprehensive tests
        System.out.println("Running Factory Method Pattern Tests...\n");
        FactoryMethodTest.main(args);
    }
    
    /**
     * Demonstrate a realistic document management system scenario
     */
    private static void demonstrateDocumentManagementSystem() {
        System.out.println("Scenario: Creating documents for a business project");
        System.out.println("==================================================");
        
        // Step 1: Create project proposal (Word document)
        System.out.println("\n1. Creating Project Proposal Document:");
        WordDocumentFactory wordFactory = new WordDocumentFactory();
        WordDocument proposal = wordFactory.createDocumentWithTemplate("Project_Proposal", "report");
        proposal.spellCheck();
        proposal.save();
        
        // Step 2: Create budget spreadsheet (Excel document)
        System.out.println("\n2. Creating Budget Spreadsheet:");
        ExcelDocumentFactory excelFactory = new ExcelDocumentFactory();
        String[] budgetSheets = {"Revenue", "Expenses", "Projections"};
        ExcelDocument budget = excelFactory.createWorkbookWithSheets("Project_Budget", budgetSheets);
        budget.createChart("Pie Chart");
        budget.runCalculations();
        budget.save();
        
        // Step 3: Create final report (PDF document)
        System.out.println("\n3. Creating Final Report PDF:");
        PdfDocumentFactory pdfFactory = new PdfDocumentFactory();
        PdfDocument report = pdfFactory.createSecureDocument("Final_Project_Report", true);
        report.addDigitalSignature();
        report.save();
        
        // Step 4: Demonstrate polymorphic usage
        System.out.println("\n4. Managing All Documents Polymorphically:");
        Document[] documents = {proposal, budget, report};
        
        for (Document doc : documents) {
            System.out.println("Document Type: " + doc.getDocumentType());
            doc.print();
            doc.close();
            System.out.println();
        }
        
        System.out.println("Document Management System demonstration completed!");
    }
} 