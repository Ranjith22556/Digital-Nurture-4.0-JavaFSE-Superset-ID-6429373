package com.example.factory;

/**
 * ExcelDocument class - Concrete implementation for Excel documents
 */
public class ExcelDocument implements Document {
    
    private String fileName;
    private boolean isOpen;
    private int numberOfSheets;
    
    public ExcelDocument() {
        this.fileName = "Untitled.xlsx";
        this.isOpen = false;
        this.numberOfSheets = 1;
        System.out.println("ExcelDocument created: " + fileName);
    }
    
    public ExcelDocument(String fileName) {
        this.fileName = fileName.endsWith(".xlsx") ? fileName : fileName + ".xlsx";
        this.isOpen = false;
        this.numberOfSheets = 1;
        System.out.println("ExcelDocument created: " + this.fileName);
    }
    
    @Override
    public void open() {
        if (!isOpen) {
            isOpen = true;
            System.out.println("Opening Excel document: " + fileName);
            System.out.println("Microsoft Excel interface loaded...");
            System.out.println("Loading " + numberOfSheets + " worksheet(s)...");
        } else {
            System.out.println("Excel document is already open: " + fileName);
        }
    }
    
    @Override
    public void save() {
        if (isOpen) {
            System.out.println("Saving Excel document: " + fileName);
            System.out.println("Document saved in .xlsx format");
            System.out.println("All " + numberOfSheets + " worksheet(s) saved");
        } else {
            System.out.println("Cannot save. Excel document is not open: " + fileName);
        }
    }
    
    @Override
    public void close() {
        if (isOpen) {
            isOpen = false;
            System.out.println("Closing Excel document: " + fileName);
            System.out.println("Microsoft Excel interface closed");
        } else {
            System.out.println("Excel document is already closed: " + fileName);
        }
    }
    
    @Override
    public String getDocumentType() {
        return "Microsoft Excel Spreadsheet (.xlsx)";
    }
    
    @Override
    public void print() {
        System.out.println("Printing Excel document: " + fileName);
        System.out.println("Content: Spreadsheet with " + numberOfSheets + " worksheet(s), formulas, and charts");
        System.out.println("Status: " + (isOpen ? "Open" : "Closed"));
    }
    
    /**
     * Excel-specific method for adding worksheets
     */
    public void addWorksheet(String sheetName) {
        if (isOpen) {
            numberOfSheets++;
            System.out.println("Added new worksheet '" + sheetName + "' to Excel document: " + fileName);
            System.out.println("Total worksheets: " + numberOfSheets);
        } else {
            System.out.println("Cannot add worksheet. Document is not open.");
        }
    }
    
    /**
     * Excel-specific method for creating charts
     */
    public void createChart(String chartType) {
        if (isOpen) {
            System.out.println("Creating " + chartType + " chart in Excel document: " + fileName);
            System.out.println("Chart created successfully");
        } else {
            System.out.println("Cannot create chart. Document is not open.");
        }
    }
    
    /**
     * Excel-specific method for running calculations
     */
    public void runCalculations() {
        if (isOpen) {
            System.out.println("Running calculations in Excel document: " + fileName);
            System.out.println("All formulas calculated successfully");
        } else {
            System.out.println("Cannot run calculations. Document is not open.");
        }
    }
} 