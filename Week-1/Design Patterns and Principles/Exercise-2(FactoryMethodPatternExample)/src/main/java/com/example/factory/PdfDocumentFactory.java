package com.example.factory;

/**
 * PdfDocumentFactory - Concrete factory for creating PDF documents.
 * This class implements the Factory Method Pattern for PDF document creation.
 */
public class PdfDocumentFactory extends DocumentFactory {
    
    @Override
    public Document createDocument() {
        System.out.println("PdfDocumentFactory: Creating PDF document...");
        return new PdfDocument();
    }
    
    @Override
    public Document createDocument(String fileName) {
        System.out.println("PdfDocumentFactory: Creating PDF document with filename: " + fileName);
        return new PdfDocument(fileName);
    }
    
    @Override
    public String getFactoryType() {
        return "PDF Document Factory";
    }
    
    /**
     * PDF-specific factory method for creating password-protected documents
     * @param fileName The name of the file
     * @param enablePassword Whether to enable password protection
     * @return PdfDocument with optional password protection
     */
    public PdfDocument createSecureDocument(String fileName, boolean enablePassword) {
        System.out.println("PdfDocumentFactory: Creating secure PDF document");
        PdfDocument doc = new PdfDocument(fileName);
        
        if (enablePassword) {
            doc.setPasswordProtection(true);
        }
        
        doc.open();
        return doc;
    }
    
    /**
     * PDF-specific factory method for creating digitally signed documents
     * @param fileName The name of the file
     * @param addSignature Whether to add digital signature
     * @return PdfDocument with optional digital signature
     */
    public PdfDocument createSignedDocument(String fileName, boolean addSignature) {
        System.out.println("PdfDocumentFactory: Creating digitally signed PDF document");
        PdfDocument doc = new PdfDocument(fileName);
        doc.open();
        
        if (addSignature) {
            doc.addDigitalSignature();
        }
        
        return doc;
    }
} 