package com.example.factory;

/**
 * Document interface that defines the common operations
 * that all document types must implement.
 */
public interface Document {
    
    /**
     * Open the document
     */
    void open();
    
    /**
     * Save the document
     */
    void save();
    
    /**
     * Close the document
     */
    void close();
    
    /**
     * Get the document type
     * @return String representing the document type
     */
    String getDocumentType();
    
    /**
     * Print document content or summary
     */
    void print();
} 