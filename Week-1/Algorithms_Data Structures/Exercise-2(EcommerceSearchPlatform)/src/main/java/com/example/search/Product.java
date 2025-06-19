package com.example.search;

/**
 * Product class representing an e-commerce product with searchable attributes
 * Used in linear and binary search algorithms demonstration
 */
public class Product {
    private int productId;
    private String productName;
    private String category;
    private double price;
    private String description;
    private int stockQuantity;
    private double rating;
    
    /**
     * Default constructor
     */
    public Product() {
        this.productId = 0;
        this.productName = "";
        this.category = "";
        this.price = 0.0;
        this.description = "";
        this.stockQuantity = 0;
        this.rating = 0.0;
    }
    
    /**
     * Constructor with essential parameters
     */
    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = 0.0;
        this.description = "";
        this.stockQuantity = 0;
        this.rating = 0.0;
    }
    
    /**
     * Full constructor
     */
    public Product(int productId, String productName, String category, 
                   double price, String description, int stockQuantity, double rating) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.rating = rating;
    }
    
    // Getters
    public int getProductId() {
        return productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public String getCategory() {
        return category;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getStockQuantity() {
        return stockQuantity;
    }
    
    public double getRating() {
        return rating;
    }
    
    // Setters
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    /**
     * Check if product name contains search term (case-insensitive)
     */
    public boolean containsInName(String searchTerm) {
        return productName.toLowerCase().contains(searchTerm.toLowerCase());
    }
    
    /**
     * Check if category matches (case-insensitive)
     */
    public boolean isInCategory(String categoryName) {
        return category.equalsIgnoreCase(categoryName);
    }
    
    /**
     * Check if product is available (in stock)
     */
    public boolean isAvailable() {
        return stockQuantity > 0;
    }
    
    /**
     * Check if product price is within range
     */
    public boolean isPriceInRange(double minPrice, double maxPrice) {
        return price >= minPrice && price <= maxPrice;
    }
    
    /**
     * String representation for basic display
     */
    @Override
    public String toString() {
        return String.format("Product[ID=%d, Name='%s', Category='%s', Price=$%.2f]", 
                           productId, productName, category, price);
    }
    
    /**
     * Detailed string representation for comprehensive display
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== PRODUCT DETAILS ===\n");
        sb.append("Product ID: ").append(productId).append("\n");
        sb.append("Name: ").append(productName).append("\n");
        sb.append("Category: ").append(category).append("\n");
        sb.append("Price: $").append(String.format("%.2f", price)).append("\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Stock: ").append(stockQuantity).append(" units\n");
        sb.append("Rating: ").append(String.format("%.1f", rating)).append("/5.0\n");
        sb.append("Status: ").append(isAvailable() ? "In Stock" : "Out of Stock");
        return sb.toString();
    }
    
    /**
     * Compare products by ID (for sorting)
     */
    public int compareById(Product other) {
        return Integer.compare(this.productId, other.productId);
    }
    
    /**
     * Compare products by name (for sorting)
     */
    public int compareByName(Product other) {
        return this.productName.compareToIgnoreCase(other.productName);
    }
    
    /**
     * Compare products by price (for sorting)
     */
    public int compareByPrice(Product other) {
        return Double.compare(this.price, other.price);
    }
    
    /**
     * Compare products by rating (for sorting)
     */
    public int compareByRating(Product other) {
        return Double.compare(other.rating, this.rating); // Higher rating first
    }
    
    /**
     * Override equals method for proper comparison
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return productId == product.productId;
    }
    
    /**
     * Override hashCode method
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(productId);
    }
    
    /**
     * Create a copy of the product
     */
    public Product copy() {
        return new Product(productId, productName, category, price, 
                         description, stockQuantity, rating);
    }
    
    /**
     * Update product information
     */
    public void updateProduct(String name, String category, double price, 
                            String description, int stock, double rating) {
        this.productName = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.stockQuantity = stock;
        this.rating = rating;
    }
    
    /**
     * Get formatted price string
     */
    public String getFormattedPrice() {
        return String.format("$%.2f", price);
    }
    
    /**
     * Get stock status string
     */
    public String getStockStatus() {
        if (stockQuantity == 0) {
            return "Out of Stock";
        } else if (stockQuantity < 10) {
            return "Low Stock (" + stockQuantity + " left)";
        } else {
            return "In Stock (" + stockQuantity + " available)";
        }
    }
    
    /**
     * Get rating stars representation
     */
    public String getRatingStars() {
        int fullStars = (int) rating;
        boolean hasHalfStar = (rating - fullStars) >= 0.5;
        
        StringBuilder stars = new StringBuilder();
        
        // Add full stars
        for (int i = 0; i < fullStars; i++) {
            stars.append("★");
        }
        
        // Add half star if needed
        if (hasHalfStar) {
            stars.append("☆");
        }
        
        // Add empty stars to make it 5 total
        int totalStars = fullStars + (hasHalfStar ? 1 : 0);
        for (int i = totalStars; i < 5; i++) {
            stars.append("☆");
        }
        
        return stars.toString() + " (" + String.format("%.1f", rating) + ")";
    }
} 