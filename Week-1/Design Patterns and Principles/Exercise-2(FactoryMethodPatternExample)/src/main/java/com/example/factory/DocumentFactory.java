package com.example.factory;

/**
 * Abstract DocumentFactory class that defines the Factory Method Pattern.
 * This class provides the template for creating documents while allowing
 * subclasses to decide which specific document type to create.
 */
public abstract class DocumentFactory {
    
    /**
     * Abstract factory method that must be implemented by concrete factories.
     * This is the core of the Factory Method Pattern.
     * @return Document instance created by the concrete factory
     */
    public abstract Document createDocument();
    
    /**
     * Abstract factory method with filename parameter.
     * Allows creating documents with specific filenames.
     * @param fileName The name of the file to create
     * @return Document instance with the specified filename
     */
    public abstract Document createDocument(String fileName);
    
    /**
     * Template method that uses the factory method to create and setup a document.
     * This demonstrates how the factory method is used in a larger workflow.
     * @return Fully initialized and opened document
     */
    public Document createAndOpenDocument() {
        System.out.println("=== Document Creation Process Started ===");
        
        // Use the factory method (implemented by subclasses)
        Document document = createDocument();
        
        // Common operations that work with any document type
        System.out.println("Document type: " + document.getDocumentType());
        document.open();
        
        System.out.println("=== Document Creation Process Completed ===");
        return document;
    }
    
    /**
     * Template method that creates, opens, and prepares a document with a filename.
     * @param fileName The name of the file to create
     * @return Fully initialized and opened document
     */
    public Document createAndOpenDocument(String fileName) {
        System.out.println("=== Document Creation Process Started ===");
        
        // Use the factory method with filename (implemented by subclasses)
        Document document = createDocument(fileName);
        
        // Common operations that work with any document type
        System.out.println("Document type: " + document.getDocumentType());
        document.open();
        
        System.out.println("=== Document Creation Process Completed ===");
        return document;
    }
    
    /**
     * Utility method to get the factory type name
     * @return String representing the factory type
     */
    public abstract String getFactoryType();
} 