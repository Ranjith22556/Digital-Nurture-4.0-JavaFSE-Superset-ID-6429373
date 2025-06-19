package com.example.search;

import java.util.Random;

/**
 * ProductDataGenerator class for creating test data sets
 * Used to generate various product collections for search algorithm testing
 */
public class ProductDataGenerator {
    
    private Random random;
    
    // Sample data arrays for realistic product generation
    private static final String[] PRODUCT_NAMES = {
        "iPhone 15 Pro", "Samsung Galaxy S24", "MacBook Pro", "Dell XPS 13",
        "iPad Air", "Microsoft Surface", "AirPods Pro", "Sony WH-1000XM5",
        "Nintendo Switch", "PlayStation 5", "Xbox Series X", "Steam Deck",
        "Canon EOS R5", "Sony A7 IV", "GoPro Hero 12", "DJI Mini 4 Pro",
        "Apple Watch Ultra", "Garmin Fenix 7", "Fitbit Charge 6", "Samsung Watch",
        "Kindle Oasis", "iPad Pro", "Surface Book", "ThinkPad X1 Carbon"
    };
    
    private static final String[] CATEGORIES = {
        "Electronics", "Computers", "Audio", "Gaming", "Photography",
        "Wearables", "Tablets", "Smartphones", "Laptops", "Accessories"
    };
    
    private static final String[] DESCRIPTIONS = {
        "Latest technology with premium features",
        "High-performance device for professionals",
        "Affordable option with great value",
        "Premium quality with exceptional design",
        "Best-in-class performance and reliability",
        "Innovative features for modern users",
        "Compact and portable design",
        "Professional-grade quality and durability"
    };
    
    public ProductDataGenerator() {
        this.random = new Random();
    }
    
    /**
     * Generate a sample dataset with predefined products
     * @return Array of sample products
     */
    public Product[] generateSampleProducts() {
        Product[] products = new Product[15];
        
        products[0] = new Product(1001, "iPhone 15 Pro", "Smartphones", 999.99, 
                                "Latest iPhone with A17 Pro chip", 25, 4.8);
        products[1] = new Product(1002, "Samsung Galaxy S24", "Smartphones", 899.99, 
                                "Android flagship with AI features", 30, 4.7);
        products[2] = new Product(1003, "MacBook Pro 14", "Laptops", 1999.99, 
                                "Professional laptop with M3 chip", 15, 4.9);
        products[3] = new Product(1004, "Dell XPS 13", "Laptops", 1299.99, 
                                "Ultrabook with InfinityEdge display", 20, 4.6);
        products[4] = new Product(1005, "iPad Air", "Tablets", 599.99, 
                                "Versatile tablet for work and play", 40, 4.5);
        products[5] = new Product(1006, "AirPods Pro", "Audio", 249.99, 
                                "Premium wireless earbuds with ANC", 60, 4.7);
        products[6] = new Product(1007, "Sony WH-1000XM5", "Audio", 399.99, 
                                "Industry-leading noise canceling headphones", 35, 4.8);
        products[7] = new Product(1008, "Nintendo Switch", "Gaming", 299.99, 
                                "Hybrid gaming console", 50, 4.6);
        products[8] = new Product(1009, "PlayStation 5", "Gaming", 499.99, 
                                "Next-gen gaming console", 10, 4.9);
        products[9] = new Product(1010, "Canon EOS R5", "Photography", 3899.99, 
                                "Professional mirrorless camera", 8, 4.8);
        products[10] = new Product(1011, "Apple Watch Ultra", "Wearables", 799.99, 
                                 "Rugged smartwatch for athletes", 22, 4.7);
        products[11] = new Product(1012, "Microsoft Surface Pro", "Tablets", 1099.99, 
                                 "2-in-1 laptop tablet hybrid", 18, 4.5);
        products[12] = new Product(1013, "Kindle Oasis", "Electronics", 279.99, 
                                 "Premium e-reader with warm light", 45, 4.4);
        products[13] = new Product(1014, "ThinkPad X1 Carbon", "Laptops", 1599.99, 
                                 "Business laptop with carbon fiber", 12, 4.6);
        products[14] = new Product(1015, "GoPro Hero 12", "Photography", 399.99, 
                                 "Action camera with 5.3K video", 28, 4.5);
        
        return products;
    }
    
    /**
     * Generate test case products for edge case testing
     * @return Array of test case products
     */
    public Product[] generateTestCaseProducts() {
        Product[] products = new Product[12];
        
        // Products with sequential IDs for predictable testing
        products[0] = new Product(1, "First Product", "Electronics", 100.00, 
                                "Test product at first position", 1, 1.0);
        products[1] = new Product(2, "Second Product", "Computers", 200.00, 
                                "Test product for basic search", 2, 2.0);
        products[2] = new Product(5, "Fifth Product", "Audio", 500.00, 
                                "Test product with gap in ID sequence", 5, 3.0);
        products[3] = new Product(10, "Tenth Product", "Gaming", 1000.00, 
                                "Test product for mid-range search", 10, 4.0);
        products[4] = new Product(15, "Fifteenth Product", "Photography", 1500.00, 
                                "Test product for middle position", 15, 5.0);
        products[5] = new Product(20, "Twentieth Product", "Wearables", 2000.00, 
                                "Test product for higher range", 20, 4.5);
        products[6] = new Product(25, "Samsung Phone", "Smartphones", 800.00, 
                                "Test product for name search", 25, 4.2);
        products[7] = new Product(27, "Samsung Tablet", "Tablets", 600.00, 
                                "Another Samsung product", 15, 4.1);
        products[8] = new Product(29, "Samsung Laptop", "Laptops", 1200.00, 
                                "Third Samsung product for multiple matches", 8, 4.3);
        products[9] = new Product(30, "Apple iPhone", "Smartphones", 999.00, 
                                "Test product for brand search", 30, 4.8);
        products[10] = new Product(31, "Test Laptop", "Laptops", 1100.00, 
                                 "Laptop for category search", 12, 4.0);
        products[11] = new Product(32, "Final Product", "Electronics", 3200.00, 
                                 "Test product at last position", 1, 5.0);
        
        return products;
    }
    
    /**
     * Generate random products for performance testing
     * @param count Number of products to generate
     * @return Array of random products
     */
    public Product[] generateRandomProducts(int count) {
        Product[] products = new Product[count];
        
        for (int i = 0; i < count; i++) {
            int id = i + 1;
            String name = PRODUCT_NAMES[random.nextInt(PRODUCT_NAMES.length)] + " " + (i + 1);
            String category = CATEGORIES[random.nextInt(CATEGORIES.length)];
            double price = 50.0 + (random.nextDouble() * 2950.0); // Price between $50-$3000
            String description = DESCRIPTIONS[random.nextInt(DESCRIPTIONS.length)];
            int stock = random.nextInt(100) + 1; // Stock between 1-100
            double rating = 1.0 + (random.nextDouble() * 4.0); // Rating between 1.0-5.0
            
            products[i] = new Product(id, name, category, price, description, stock, rating);
        }
        
        return products;
    }
    
    /**
     * Generate products for specific testing scenarios
     * @param scenario The test scenario to generate data for
     * @return Array of products for the scenario
     */
    public Product[] generateScenarioProducts(String scenario) {
        switch (scenario.toLowerCase()) {
            case "bestselling":
                return generateBestSellingProducts();
            case "electronics":
                return generateElectronicsProducts();
            case "budget":
                return generateBudgetProducts();
            case "premium":
                return generatePremiumProducts();
            default:
                return generateSampleProducts();
        }
    }
    
    /**
     * Generate best-selling products dataset
     */
    private Product[] generateBestSellingProducts() {
        Product[] products = new Product[10];
        
        products[0] = new Product(2001, "iPhone 15", "Smartphones", 799.99, "Best-selling smartphone", 100, 4.8);
        products[1] = new Product(2002, "AirPods", "Audio", 179.99, "Best-selling earbuds", 200, 4.6);
        products[2] = new Product(2003, "MacBook Air", "Laptops", 1099.99, "Best-selling laptop", 75, 4.7);
        products[3] = new Product(2004, "iPad", "Tablets", 449.99, "Best-selling tablet", 150, 4.5);
        products[4] = new Product(2005, "Nintendo Switch Lite", "Gaming", 199.99, "Popular handheld console", 120, 4.4);
        products[5] = new Product(2006, "Echo Dot", "Electronics", 49.99, "Best-selling smart speaker", 300, 4.3);
        products[6] = new Product(2007, "Fire TV Stick", "Electronics", 39.99, "Popular streaming device", 250, 4.2);
        products[7] = new Product(2008, "Kindle", "Electronics", 89.99, "Best-selling e-reader", 180, 4.4);
        products[8] = new Product(2009, "Apple Watch SE", "Wearables", 279.99, "Popular smartwatch", 90, 4.5);
        products[9] = new Product(2010, "Samsung Buds", "Audio", 149.99, "Popular wireless earbuds", 160, 4.3);
        
        return products;
    }
    
    /**
     * Generate electronics-focused dataset
     */
    private Product[] generateElectronicsProducts() {
        Product[] products = new Product[8];
        
        products[0] = new Product(3001, "Smart TV 55", "Electronics", 699.99, "4K Smart Television", 25, 4.5);
        products[1] = new Product(3002, "Bluetooth Speaker", "Electronics", 129.99, "Portable wireless speaker", 50, 4.3);
        products[2] = new Product(3003, "Wireless Charger", "Electronics", 39.99, "Fast wireless charging pad", 80, 4.2);
        products[3] = new Product(3004, "Smart Home Hub", "Electronics", 199.99, "Central smart home controller", 30, 4.4);
        products[4] = new Product(3005, "Security Camera", "Electronics", 159.99, "WiFi security camera", 40, 4.1);
        products[5] = new Product(3006, "Robot Vacuum", "Electronics", 399.99, "Smart robot vacuum cleaner", 20, 4.6);
        products[6] = new Product(3007, "Smart Doorbell", "Electronics", 249.99, "Video doorbell with app", 35, 4.3);
        products[7] = new Product(3008, "Streaming Device", "Electronics", 89.99, "4K streaming media player", 60, 4.4);
        
        return products;
    }
    
    /**
     * Generate budget-friendly products dataset
     */
    private Product[] generateBudgetProducts() {
        Product[] products = new Product[6];
        
        products[0] = new Product(4001, "Budget Phone", "Smartphones", 199.99, "Affordable smartphone", 100, 3.8);
        products[1] = new Product(4002, "Basic Laptop", "Laptops", 399.99, "Entry-level laptop", 50, 3.5);
        products[2] = new Product(4003, "Wired Earphones", "Audio", 19.99, "Basic wired earphones", 200, 3.9);
        products[3] = new Product(4004, "Budget Tablet", "Tablets", 149.99, "7-inch budget tablet", 75, 3.6);
        products[4] = new Product(4005, "Basic Webcam", "Electronics", 29.99, "HD webcam for video calls", 120, 3.7);
        products[5] = new Product(4006, "USB Hub", "Accessories", 24.99, "4-port USB hub", 150, 4.0);
        
        return products;
    }
    
    /**
     * Generate premium products dataset
     */
    private Product[] generatePremiumProducts() {
        Product[] products = new Product[5];
        
        products[0] = new Product(5001, "iPhone 15 Pro Max", "Smartphones", 1199.99, "Premium flagship smartphone", 20, 4.9);
        products[1] = new Product(5002, "MacBook Pro 16", "Laptops", 2499.99, "Professional laptop", 15, 4.8);
        products[2] = new Product(5003, "Sony A7R V", "Photography", 3899.99, "Professional camera", 5, 4.9);
        products[3] = new Product(5004, "Bose QuietComfort Ultra", "Audio", 429.99, "Premium noise-canceling headphones", 25, 4.7);
        products[4] = new Product(5005, "Surface Studio", "Computers", 3499.99, "All-in-one creative workstation", 8, 4.6);
        
        return products;
    }
    
    /**
     * Display information about a dataset
     * @param products The product array to analyze
     */
    public void displayDatasetInfo(Product[] products) {
        System.out.println("\n📊 DATASET INFORMATION:");
        System.out.println("Total Products: " + products.length);
        
        if (products.length == 0) {
            System.out.println("No products in dataset.");
            return;
        }
        
        // Calculate price statistics
        double minPrice = products[0].getPrice();
        double maxPrice = products[0].getPrice();
        double totalPrice = 0;
        
        // Count categories
        String[] categories = new String[products.length];
        int categoryCount = 0;
        
        for (Product product : products) {
            double price = product.getPrice();
            minPrice = Math.min(minPrice, price);
            maxPrice = Math.max(maxPrice, price);
            totalPrice += price;
            
            // Count unique categories
            String category = product.getCategory();
            boolean found = false;
            for (int i = 0; i < categoryCount; i++) {
                if (categories[i].equals(category)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                categories[categoryCount++] = category;
            }
        }
        
        double avgPrice = totalPrice / products.length;
        
        System.out.printf("Price Range: $%.2f - $%.2f\n", minPrice, maxPrice);
        System.out.printf("Average Price: $%.2f\n", avgPrice);
        System.out.println("Categories: " + categoryCount);
        
        // Display categories
        System.out.print("Category List: ");
        for (int i = 0; i < categoryCount; i++) {
            System.out.print(categories[i]);
            if (i < categoryCount - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        
        // ID range
        int minId = products[0].getProductId();
        int maxId = products[0].getProductId();
        for (Product product : products) {
            minId = Math.min(minId, product.getProductId());
            maxId = Math.max(maxId, product.getProductId());
        }
        System.out.println("Product ID Range: " + minId + " - " + maxId);
    }
    
    /**
     * Get a product by index (for testing purposes)
     * @param products The product array
     * @param index The index to retrieve
     * @return Product at the specified index, or null if invalid
     */
    public Product getProductByIndex(Product[] products, int index) {
        if (index >= 0 && index < products.length) {
            return products[index];
        }
        return null;
    }
    
    /**
     * Find product ID at specific position (1-based)
     * @param products The product array
     * @param position The position (1-based)
     * @return Product ID at the position, or -1 if invalid
     */
    public int getProductIdAtPosition(Product[] products, int position) {
        if (position >= 1 && position <= products.length) {
            return products[position - 1].getProductId();
        }
        return -1;
    }
    
    /**
     * Generate products with specific ID pattern for testing
     * @param count Number of products
     * @param startId Starting ID
     * @param increment ID increment between products
     * @return Array of products with specified ID pattern
     */
    public Product[] generateProductsWithIdPattern(int count, int startId, int increment) {
        Product[] products = new Product[count];
        
        for (int i = 0; i < count; i++) {
            int id = startId + (i * increment);
            String name = "Product " + id;
            String category = CATEGORIES[i % CATEGORIES.length];
            double price = 100.0 + (i * 50.0);
            String description = "Test product with ID " + id;
            int stock = 10 + i;
            double rating = 3.0 + (i % 3);
            
            products[i] = new Product(id, name, category, price, description, stock, rating);
        }
        
        return products;
    }
} 