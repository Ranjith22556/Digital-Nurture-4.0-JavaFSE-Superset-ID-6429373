package com.example.factory;

/**
 * WordDocumentFactory - Concrete factory for creating Word documents.
 * This class implements the Factory Method Pattern for Word document creation.
 */
public class WordDocumentFactory extends DocumentFactory {
    
    @Override
    public Document createDocument() {
        System.out.println("WordDocumentFactory: Creating Word document...");
        return new WordDocument();
    }
    
    @Override
    public Document createDocument(String fileName) {
        System.out.println("WordDocumentFactory: Creating Word document with filename: " + fileName);
        return new WordDocument(fileName);
    }
    
    @Override
    public String getFactoryType() {
        return "Word Document Factory";
    }
    
    /**
     * Word-specific factory method for creating documents with templates
     * @param fileName The name of the file
     * @param template The template to use (e.g., "letter", "resume", "report")
     * @return WordDocument with template applied
     */
    public WordDocument createDocumentWithTemplate(String fileName, String template) {
        System.out.println("WordDocumentFactory: Creating Word document with template '" + template + "'");
        WordDocument doc = new WordDocument(fileName);
        doc.open();
        
        // Simulate template application
        switch (template.toLowerCase()) {
            case "letter":
                System.out.println("Applied business letter template");
                break;
            case "resume":
                System.out.println("Applied professional resume template");
                break;
            case "report":
                System.out.println("Applied formal report template");
                break;
            default:
                System.out.println("Applied default template");
                break;
        }
        
        return doc;
    }
} 