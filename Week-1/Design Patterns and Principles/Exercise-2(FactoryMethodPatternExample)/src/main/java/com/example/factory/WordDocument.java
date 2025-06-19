package com.example.factory;

/**
 * WordDocument class - Concrete implementation for Word documents
 */
public class WordDocument implements Document {
    
    private String fileName;
    private boolean isOpen;
    
    public WordDocument() {
        this.fileName = "Untitled.docx";
        this.isOpen = false;
        System.out.println("WordDocument created: " + fileName);
    }
    
    public WordDocument(String fileName) {
        this.fileName = fileName.endsWith(".docx") ? fileName : fileName + ".docx";
        this.isOpen = false;
        System.out.println("WordDocument created: " + this.fileName);
    }
    
    @Override
    public void open() {
        if (!isOpen) {
            isOpen = true;
            System.out.println("Opening Word document: " + fileName);
            System.out.println("Microsoft Word interface loaded...");
        } else {
            System.out.println("Word document is already open: " + fileName);
        }
    }
    
    @Override
    public void save() {
        if (isOpen) {
            System.out.println("Saving Word document: " + fileName);
            System.out.println("Document saved in .docx format");
        } else {
            System.out.println("Cannot save. Word document is not open: " + fileName);
        }
    }
    
    @Override
    public void close() {
        if (isOpen) {
            isOpen = false;
            System.out.println("Closing Word document: " + fileName);
            System.out.println("Microsoft Word interface closed");
        } else {
            System.out.println("Word document is already closed: " + fileName);
        }
    }
    
    @Override
    public String getDocumentType() {
        return "Microsoft Word Document (.docx)";
    }
    
    @Override
    public void print() {
        System.out.println("Printing Word document: " + fileName);
        System.out.println("Content: Rich text document with formatting, images, and tables");
        System.out.println("Status: " + (isOpen ? "Open" : "Closed"));
    }
    
    /**
     * Word-specific method for spell check
     */
    public void spellCheck() {
        if (isOpen) {
            System.out.println("Running spell check on Word document: " + fileName);
            System.out.println("Spell check completed - No errors found");
        } else {
            System.out.println("Cannot run spell check. Document is not open.");
        }
    }
} 