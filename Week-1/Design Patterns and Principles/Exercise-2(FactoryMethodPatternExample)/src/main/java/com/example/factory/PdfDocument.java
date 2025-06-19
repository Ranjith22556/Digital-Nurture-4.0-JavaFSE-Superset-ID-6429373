package com.example.factory;

/**
 * PdfDocument class - Concrete implementation for PDF documents
 */
public class PdfDocument implements Document {
    
    private String fileName;
    private boolean isOpen;
    private boolean isPasswordProtected;
    
    public PdfDocument() {
        this.fileName = "Untitled.pdf";
        this.isOpen = false;
        this.isPasswordProtected = false;
        System.out.println("PdfDocument created: " + fileName);
    }
    
    public PdfDocument(String fileName) {
        this.fileName = fileName.endsWith(".pdf") ? fileName : fileName + ".pdf";
        this.isOpen = false;
        this.isPasswordProtected = false;
        System.out.println("PdfDocument created: " + this.fileName);
    }
    
    @Override
    public void open() {
        if (!isOpen) {
            isOpen = true;
            System.out.println("Opening PDF document: " + fileName);
            System.out.println("Adobe PDF Reader interface loaded...");
            if (isPasswordProtected) {
                System.out.println("Password protection detected - Please enter password");
            }
        } else {
            System.out.println("PDF document is already open: " + fileName);
        }
    }
    
    @Override
    public void save() {
        if (isOpen) {
            System.out.println("Saving PDF document: " + fileName);
            System.out.println("Document saved in .pdf format");
            System.out.println("PDF optimization applied");
        } else {
            System.out.println("Cannot save. PDF document is not open: " + fileName);
        }
    }
    
    @Override
    public void close() {
        if (isOpen) {
            isOpen = false;
            System.out.println("Closing PDF document: " + fileName);
            System.out.println("Adobe PDF Reader interface closed");
        } else {
            System.out.println("PDF document is already closed: " + fileName);
        }
    }
    
    @Override
    public String getDocumentType() {
        return "Portable Document Format (.pdf)";
    }
    
    @Override
    public void print() {
        System.out.println("Printing PDF document: " + fileName);
        System.out.println("Content: Formatted document with fixed layout, images, and fonts");
        System.out.println("Status: " + (isOpen ? "Open" : "Closed"));
        System.out.println("Security: " + (isPasswordProtected ? "Password Protected" : "No Protection"));
    }
    
    /**
     * PDF-specific method for password protection
     */
    public void setPasswordProtection(boolean enable) {
        this.isPasswordProtected = enable;
        if (enable) {
            System.out.println("Password protection enabled for PDF: " + fileName);
        } else {
            System.out.println("Password protection disabled for PDF: " + fileName);
        }
    }
    
    /**
     * PDF-specific method for digital signing
     */
    public void addDigitalSignature() {
        if (isOpen) {
            System.out.println("Adding digital signature to PDF: " + fileName);
            System.out.println("Digital signature applied successfully");
        } else {
            System.out.println("Cannot add signature. Document is not open.");
        }
    }
} 