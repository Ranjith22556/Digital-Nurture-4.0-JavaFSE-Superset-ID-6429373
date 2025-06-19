package com.example.factory;

/**
 * ExcelDocumentFactory - Concrete factory for creating Excel documents.
 * This class implements the Factory Method Pattern for Excel document creation.
 */
public class ExcelDocumentFactory extends DocumentFactory {
    
    @Override
    public Document createDocument() {
        System.out.println("ExcelDocumentFactory: Creating Excel document...");
        return new ExcelDocument();
    }
    
    @Override
    public Document createDocument(String fileName) {
        System.out.println("ExcelDocumentFactory: Creating Excel document with filename: " + fileName);
        return new ExcelDocument(fileName);
    }
    
    @Override
    public String getFactoryType() {
        return "Excel Document Factory";
    }
    
    /**
     * Excel-specific factory method for creating workbooks with multiple sheets
     * @param fileName The name of the file
     * @param sheetNames Array of sheet names to create
     * @return ExcelDocument with multiple worksheets
     */
    public ExcelDocument createWorkbookWithSheets(String fileName, String[] sheetNames) {
        System.out.println("ExcelDocumentFactory: Creating Excel workbook with multiple sheets");
        ExcelDocument doc = new ExcelDocument(fileName);
        doc.open();
        
        // Add additional worksheets
        for (String sheetName : sheetNames) {
            doc.addWorksheet(sheetName);
        }
        
        return doc;
    }
    
    /**
     * Excel-specific factory method for creating documents with charts
     * @param fileName The name of the file
     * @param chartType The type of chart to create
     * @return ExcelDocument with a chart
     */
    public ExcelDocument createDocumentWithChart(String fileName, String chartType) {
        System.out.println("ExcelDocumentFactory: Creating Excel document with chart");
        ExcelDocument doc = new ExcelDocument(fileName);
        doc.open();
        doc.createChart(chartType);
        
        return doc;
    }
    
    /**
     * Excel-specific factory method for creating calculation-heavy documents
     * @param fileName The name of the file
     * @return ExcelDocument optimized for calculations
     */
    public ExcelDocument createCalculationDocument(String fileName) {
        System.out.println("ExcelDocumentFactory: Creating Excel document optimized for calculations");
        ExcelDocument doc = new ExcelDocument(fileName);
        doc.open();
        
        // Simulate adding formulas and running calculations
        System.out.println("Adding complex formulas and data tables...");
        doc.runCalculations();
        
        return doc;
    }
} 