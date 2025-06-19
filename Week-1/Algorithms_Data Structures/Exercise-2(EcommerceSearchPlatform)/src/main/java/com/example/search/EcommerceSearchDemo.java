package com.example.search;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

/**
 * Interactive demo application for E-commerce Search Platform
 * Demonstrates linear and binary search algorithms with real-time performance comparison
 */
public class EcommerceSearchDemo {
    
    private SearchAlgorithms searchAlgorithms;
    private ProductDataGenerator dataGenerator;
    private Product[] currentDataset;
    private Scanner scanner;
    private String currentDatasetName;
    
    public EcommerceSearchDemo() {
        this.dataGenerator = new ProductDataGenerator();
        this.currentDataset = dataGenerator.generateSampleProducts();
        this.currentDatasetName = "Sample Products";
        this.searchAlgorithms = new SearchAlgorithms(currentDataset);
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Helper method to repeat a string (Java 8 compatible)
     */
    private String repeatString(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    /**
     * Main demo method
     */
    public void runDemo() {
        displayWelcome();
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    demonstrateLinearSearch();
                    break;
                case 2:
                    demonstrateBinarySearch();
                    break;
                case 3:
                    compareSearchPerformance();
                    break;
                case 4:
                    searchByProductName();
                    break;
                case 5:
                    searchByCategory();
                    break;
                case 6:
                    performanceTestingMenu();
                    break;
                case 7:
                    displayCurrentDataset();
                    break;
                case 8:
                    changeDataset();
                    break;
                case 9:
                    runComprehensiveTests();
                    break;
                case 0:
                    running = false;
                    displayGoodbye();
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }
    
    /**
     * Display welcome message
     */
    private void displayWelcome() {
        System.out.println(repeatString('=', 80));
        System.out.println("🛒 WELCOME TO E-COMMERCE SEARCH PLATFORM DEMO");
        System.out.println(repeatString('=', 80));
        System.out.println("This interactive demo showcases search algorithms used in e-commerce platforms.");
        System.out.println("Compare Linear Search vs Binary Search performance with real-time analysis!");
        System.out.println();
        System.out.println("Current Dataset: " + currentDatasetName + " (" + currentDataset.length + " products)");
        System.out.println(repeatString('-', 80));
    }
    
    /**
     * Display main menu
     */
    private void displayMenu() {
        System.out.println("\n📋 MAIN MENU:");
        System.out.println("1. 🔍 Demonstrate Linear Search (Product ID)");
        System.out.println("2. 🎯 Demonstrate Binary Search (Product ID)");
        System.out.println("3. ⚡ Compare Search Performance");
        System.out.println("4. 📝 Search by Product Name");
        System.out.println("5. 🏷️  Search by Category");
        System.out.println("6. 📊 Performance Testing Menu");
        System.out.println("7. 📦 Display Current Dataset");
        System.out.println("8. 🔄 Change Dataset");
        System.out.println("9. 🧪 Run Comprehensive Tests");
        System.out.println("0. 🚪 Exit");
        System.out.print("\nEnter your choice: ");
    }
    
    /**
     * Get user choice with input validation
     */
    private int getChoice() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    /**
     * Demonstrate linear search with user input
     */
    private void demonstrateLinearSearch() {
        System.out.println("\n" + repeatString('=', 60));
        System.out.println("🔍 LINEAR SEARCH DEMONSTRATION");
        System.out.println(repeatString('=', 60));
        
        System.out.print("Enter Product ID to search: ");
        try {
            int productId = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.println("\n🔄 Performing Linear Search...");
            long startTime = System.nanoTime();
            Product result = searchAlgorithms.linearSearchById(productId);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            
            System.out.println("\n📈 SEARCH RESULTS:");
            System.out.println("Algorithm: Linear Search");
            System.out.printf("Execution Time: %.2f microseconds\n", duration / 1000.0);
            System.out.println("Comparisons Made: " + searchAlgorithms.getLastOperationCount());
            
            if (result != null) {
                System.out.println("✅ Product Found:");
                System.out.println(result.toDetailedString());
            } else {
                System.out.println("❌ Product not found in the dataset.");
            }
            
            // Show theoretical analysis
            System.out.println("\n📚 THEORETICAL ANALYSIS:");
            System.out.println("• Time Complexity: O(n) - Linear time");
            System.out.println("• Space Complexity: O(1) - Constant space");
            System.out.println("• Best Case: O(1) - Element at first position");
            System.out.println("• Average Case: O(n/2) - Element in middle");
            System.out.println("• Worst Case: O(n) - Element at last position or not found");
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Please enter a valid Product ID.");
        }
    }
    
    /**
     * Demonstrate binary search with user input
     */
    private void demonstrateBinarySearch() {
        System.out.println("\n" + repeatString('=', 60));
        System.out.println("🎯 BINARY SEARCH DEMONSTRATION");
        System.out.println(repeatString('=', 60));
        
        System.out.print("Enter Product ID to search: ");
        try {
            int productId = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.println("\n🔄 Performing Binary Search...");
            long startTime = System.nanoTime();
            Product result = searchAlgorithms.binarySearchById(productId);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            
            System.out.println("\n📈 SEARCH RESULTS:");
            System.out.println("Algorithm: Binary Search");
            System.out.printf("Execution Time: %.2f microseconds\n", duration / 1000.0);
            System.out.println("Comparisons Made: " + searchAlgorithms.getLastOperationCount());
            
            if (result != null) {
                System.out.println("✅ Product Found:");
                System.out.println(result.toDetailedString());
            } else {
                System.out.println("❌ Product not found in the dataset.");
            }
            
            // Show theoretical analysis
            System.out.println("\n📚 THEORETICAL ANALYSIS:");
            System.out.println("• Time Complexity: O(log n) - Logarithmic time");
            System.out.println("• Space Complexity: O(1) - Constant space (iterative)");
            System.out.println("• Best Case: O(1) - Element at middle position");
            System.out.println("• Average Case: O(log n) - Divides search space in half");
            System.out.println("• Worst Case: O(log n) - Element at leaf position or not found");
            System.out.println("• Prerequisite: Array must be sorted by Product ID");
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Please enter a valid Product ID.");
        }
    }
    
    /**
     * Compare performance of both search algorithms
     */
    private void compareSearchPerformance() {
        System.out.println("\n" + repeatString('=', 70));
        System.out.println("⚡ SEARCH PERFORMANCE COMPARISON");
        System.out.println(repeatString('=', 70));
        
        System.out.print("Enter Product ID to search: ");
        try {
            int productId = Integer.parseInt(scanner.nextLine().trim());
            
            searchAlgorithms.compareSearchPerformance(productId);
            
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Please enter a valid Product ID.");
        }
    }
    
    /**
     * Search by product name demonstration
     */
    private void searchByProductName() {
        System.out.println("\n" + repeatString('=', 60));
        System.out.println("📝 SEARCH BY PRODUCT NAME");
        System.out.println(repeatString('=', 60));
        
        System.out.print("Enter product name (or part of name) to search: ");
        String searchTerm = scanner.nextLine().trim();
        
        if (searchTerm.isEmpty()) {
            System.out.println("❌ Search term cannot be empty.");
            return;
        }
        
        System.out.println("\n🔄 Searching for products containing: \"" + searchTerm + "\"...");
        
        long startTime = System.nanoTime();
        Product[] results = searchAlgorithms.linearSearchByName(searchTerm);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        
        System.out.println("\n📈 SEARCH RESULTS:");
        System.out.printf("Execution Time: %.2f microseconds\n", duration / 1000.0);
        System.out.println("Products Checked: " + searchAlgorithms.getLastOperationCount());
        System.out.println("Matches Found: " + results.length);
        
        if (results.length > 0) {
            System.out.println("\n✅ Products Found:");
            System.out.println(repeatString('-', 60));
            for (int i = 0; i < results.length; i++) {
                System.out.println((i + 1) + ". " + results[i].toString());
            }
        } else {
            System.out.println("❌ No products found matching: \"" + searchTerm + "\"");
        }
        
        System.out.println("\n📚 ALGORITHM INFO:");
        System.out.println("• Uses Linear Search (O(n) time complexity)");
        System.out.println("• Case-insensitive substring matching");
        System.out.println("• Returns all matching products");
    }
    
    /**
     * Search by category demonstration
     */
    private void searchByCategory() {
        System.out.println("\n" + repeatString('=', 60));
        System.out.println("🏷️  SEARCH BY CATEGORY");
        System.out.println(repeatString('=', 60));
        
        // Show available categories
        Set<String> categories = new HashSet<String>();
        for (Product product : currentDataset) {
            categories.add(product.getCategory());
        }
        
        System.out.println("Available categories:");
        int index = 1;
        for (String category : categories) {
            System.out.println(index++ + ". " + category);
        }
        
        System.out.print("\nEnter category name: ");
        String category = scanner.nextLine().trim();
        
        if (category.isEmpty()) {
            System.out.println("❌ Category name cannot be empty.");
            return;
        }
        
        System.out.println("\n🔄 Searching for products in category: \"" + category + "\"...");
        
        long startTime = System.nanoTime();
        Product[] results = searchAlgorithms.linearSearchByCategory(category);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        
        System.out.println("\n📈 SEARCH RESULTS:");
        System.out.printf("Execution Time: %.2f microseconds\n", duration / 1000.0);
        System.out.println("Products Checked: " + searchAlgorithms.getLastOperationCount());
        System.out.println("Matches Found: " + results.length);
        
        if (results.length > 0) {
            System.out.println("\n✅ Products Found:");
            System.out.println(repeatString('-', 60));
            for (int i = 0; i < results.length; i++) {
                System.out.println((i + 1) + ". " + results[i].toString());
            }
        } else {
            System.out.println("❌ No products found in category: \"" + category + "\"");
        }
    }
    
    /**
     * Performance testing submenu
     */
    private void performanceTestingMenu() {
        System.out.println("\n" + repeatString('=', 60));
        System.out.println("📊 PERFORMANCE TESTING MENU");
        System.out.println(repeatString('=', 60));
        
        System.out.println("1. Test with Best Case Scenario (First element)");
        System.out.println("2. Test with Worst Case Scenario (Last element)");
        System.out.println("3. Test with Average Case Scenario (Middle element)");
        System.out.println("4. Test with Non-existent Element");
        System.out.println("5. Bulk Performance Test (Multiple searches)");
        System.out.println("0. Return to Main Menu");
        
        System.out.print("\nEnter your choice: ");
        int choice = getChoice();
        
        switch (choice) {
            case 1:
                testBestCase();
                break;
            case 2:
                testWorstCase();
                break;
            case 3:
                testAverageCase();
                break;
            case 4:
                testNonExistentElement();
                break;
            case 5:
                bulkPerformanceTest();
                break;
            case 0:
                return;
            default:
                System.out.println("❌ Invalid choice.");
        }
    }
    
    /**
     * Test best case scenario
     */
    private void testBestCase() {
        System.out.println("\n🎯 TESTING BEST CASE SCENARIO");
        System.out.println("Searching for first element in the dataset...");
        
        if (currentDataset.length > 0) {
            int firstProductId = currentDataset[0].getProductId();
            searchAlgorithms.compareSearchPerformance(firstProductId);
        } else {
            System.out.println("❌ Dataset is empty.");
        }
    }
    
    /**
     * Test worst case scenario
     */
    private void testWorstCase() {
        System.out.println("\n🎯 TESTING WORST CASE SCENARIO");
        System.out.println("Searching for last element in the dataset...");
        
        if (currentDataset.length > 0) {
            // For worst case in binary search, we need the last element in sorted order
            Product[] sortedDataset = new Product[currentDataset.length];
            System.arraycopy(currentDataset, 0, sortedDataset, 0, currentDataset.length);
            java.util.Arrays.sort(sortedDataset, new java.util.Comparator<Product>() {
                public int compare(Product p1, Product p2) {
                    return Integer.compare(p1.getProductId(), p2.getProductId());
                }
            });
            
            int lastProductId = sortedDataset[sortedDataset.length - 1].getProductId();
            searchAlgorithms.compareSearchPerformance(lastProductId);
        } else {
            System.out.println("❌ Dataset is empty.");
        }
    }
    
    /**
     * Test average case scenario
     */
    private void testAverageCase() {
        System.out.println("\n🎯 TESTING AVERAGE CASE SCENARIO");
        System.out.println("Searching for middle element in the dataset...");
        
        if (currentDataset.length > 0) {
            int middleIndex = currentDataset.length / 2;
            int middleProductId = currentDataset[middleIndex].getProductId();
            searchAlgorithms.compareSearchPerformance(middleProductId);
        } else {
            System.out.println("❌ Dataset is empty.");
        }
    }
    
    /**
     * Test with non-existent element
     */
    private void testNonExistentElement() {
        System.out.println("\n🎯 TESTING NON-EXISTENT ELEMENT");
        System.out.println("Searching for element that doesn't exist...");
        
        // Find a product ID that doesn't exist
        int nonExistentId = -1;
        for (int i = 1; i <= 10000; i++) {
            boolean exists = false;
            for (Product product : currentDataset) {
                if (product.getProductId() == i) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                nonExistentId = i;
                break;
            }
        }
        
        if (nonExistentId != -1) {
            System.out.println("Testing with Product ID: " + nonExistentId + " (does not exist)");
            searchAlgorithms.compareSearchPerformance(nonExistentId);
        } else {
            System.out.println("❌ Could not find a non-existent ID to test with.");
        }
    }
    
    /**
     * Bulk performance test
     */
    private void bulkPerformanceTest() {
        System.out.println("\n🎯 BULK PERFORMANCE TEST");
        System.out.println("Performing multiple searches to get average performance...");
        
        int numTests = Math.min(10, currentDataset.length);
        long totalLinearTime = 0;
        long totalBinaryTime = 0;
        int totalLinearComparisons = 0;
        int totalBinaryComparisons = 0;
        
        System.out.println("\nPerforming " + numTests + " tests...");
        
        for (int i = 0; i < numTests; i++) {
            int testIndex = (i * currentDataset.length) / numTests;
            int productId = currentDataset[testIndex].getProductId();
            
            // Test Linear Search
            long startTime = System.nanoTime();
            searchAlgorithms.linearSearchById(productId);
            long endTime = System.nanoTime();
            totalLinearTime += (endTime - startTime);
            totalLinearComparisons += searchAlgorithms.getLastOperationCount();
            
            // Test Binary Search
            startTime = System.nanoTime();
            searchAlgorithms.binarySearchById(productId);
            endTime = System.nanoTime();
            totalBinaryTime += (endTime - startTime);
            totalBinaryComparisons += searchAlgorithms.getLastOperationCount();
        }
        
        System.out.println("\n📊 BULK TEST RESULTS:");
        System.out.println(repeatString('-', 50));
        System.out.printf("Linear Search - Avg Time: %.2f μs, Avg Comparisons: %.1f\n", 
                         (totalLinearTime / numTests) / 1000.0, 
                         (double) totalLinearComparisons / numTests);
        System.out.printf("Binary Search - Avg Time: %.2f μs, Avg Comparisons: %.1f\n", 
                         (totalBinaryTime / numTests) / 1000.0, 
                         (double) totalBinaryComparisons / numTests);
        
        double speedup = (double) totalLinearTime / totalBinaryTime;
        System.out.printf("Binary Search is %.2fx faster on average\n", speedup);
    }
    
    /**
     * Display current dataset information
     */
    private void displayCurrentDataset() {
        System.out.println("\n" + repeatString('=', 60));
        System.out.println("📦 CURRENT DATASET: " + currentDatasetName);
        System.out.println(repeatString('=', 60));
        
        dataGenerator.displayDatasetInfo(currentDataset);
        
        System.out.println("\n📋 PRODUCT LIST:");
        System.out.println(repeatString('-', 60));
        for (int i = 0; i < Math.min(currentDataset.length, 10); i++) {
            System.out.println((i + 1) + ". " + currentDataset[i].toString());
        }
        
        if (currentDataset.length > 10) {
            System.out.println("... and " + (currentDataset.length - 10) + " more products");
            System.out.println("\n💡 Tip: Use search functions to find specific products!");
        }
    }
    
    /**
     * Change dataset menu
     */
    private void changeDataset() {
        System.out.println("\n" + repeatString('=', 60));
        System.out.println("🔄 CHANGE DATASET");
        System.out.println(repeatString('=', 60));
        
        System.out.println("Available datasets:");
        System.out.println("1. Sample Products (15 items) - Default mixed products");
        System.out.println("2. Test Case Products (12 items) - Sequential IDs for testing");
        System.out.println("3. Best Selling Products (10 items) - Popular items");
        System.out.println("4. Electronics Products (8 items) - Electronics category only");
        System.out.println("5. Budget Products (6 items) - Affordable options");
        System.out.println("6. Premium Products (5 items) - High-end products");
        System.out.println("7. Random Products (Custom size) - Generated randomly");
        System.out.println("0. Cancel");
        
        System.out.print("\nSelect dataset: ");
        int choice = getChoice();
        
        Product[] newDataset = null;
        String newDatasetName = "";
        
        switch (choice) {
            case 1:
                newDataset = dataGenerator.generateSampleProducts();
                newDatasetName = "Sample Products";
                break;
            case 2:
                newDataset = dataGenerator.generateTestCaseProducts();
                newDatasetName = "Test Case Products";
                break;
            case 3:
                newDataset = dataGenerator.generateScenarioProducts("bestselling");
                newDatasetName = "Best Selling Products";
                break;
            case 4:
                newDataset = dataGenerator.generateScenarioProducts("electronics");
                newDatasetName = "Electronics Products";
                break;
            case 5:
                newDataset = dataGenerator.generateScenarioProducts("budget");
                newDatasetName = "Budget Products";
                break;
            case 6:
                newDataset = dataGenerator.generateScenarioProducts("premium");
                newDatasetName = "Premium Products";
                break;
            case 7:
                System.out.print("Enter number of products to generate (1-1000): ");
                try {
                    int count = Integer.parseInt(scanner.nextLine().trim());
                    if (count >= 1 && count <= 1000) {
                        newDataset = dataGenerator.generateRandomProducts(count);
                        newDatasetName = "Random Products (" + count + " items)";
                    } else {
                        System.out.println("❌ Invalid count. Must be between 1 and 1000.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("❌ Invalid input. Please enter a number.");
                    return;
                }
                break;
            case 0:
                return;
            default:
                System.out.println("❌ Invalid choice.");
                return;
        }
        
        if (newDataset != null) {
            currentDataset = newDataset;
            currentDatasetName = newDatasetName;
            searchAlgorithms = new SearchAlgorithms(currentDataset);
            
            System.out.println("✅ Dataset changed successfully!");
            System.out.println("New dataset: " + currentDatasetName + " (" + currentDataset.length + " products)");
        }
    }
    
    /**
     * Run comprehensive tests
     */
    private void runComprehensiveTests() {
        System.out.println("\n" + repeatString('=', 70));
        System.out.println("🧪 COMPREHENSIVE ALGORITHM TESTING");
        System.out.println(repeatString('=', 70));
        
        System.out.println("This will run a series of tests to demonstrate algorithm behavior...\n");
        
        // Test 1: Algorithm comparison with current dataset
        System.out.println("📊 Test 1: Performance comparison with current dataset");
        System.out.println(repeatString('-', 50));
        if (currentDataset.length > 0) {
            int testId = currentDataset[currentDataset.length / 2].getProductId();
            searchAlgorithms.compareSearchPerformance(testId);
        }
        
        // Test 2: Different dataset sizes
        System.out.println("\n📊 Test 2: Performance with different dataset sizes");
        System.out.println(repeatString('-', 50));
        int[] testSizes = {10, 50, 100, 500};
        
        for (int size : testSizes) {
            Product[] testDataset = dataGenerator.generateRandomProducts(size);
            SearchAlgorithms testAlgorithms = new SearchAlgorithms(testDataset);
            
            // Test with middle element
            int testId = testDataset[size / 2].getProductId();
            
            long linearStart = System.nanoTime();
            testAlgorithms.linearSearchById(testId);
            long linearTime = System.nanoTime() - linearStart;
            int linearComparisons = testAlgorithms.getLastOperationCount();
            
            long binaryStart = System.nanoTime();
            testAlgorithms.binarySearchById(testId);
            long binaryTime = System.nanoTime() - binaryStart;
            int binaryComparisons = testAlgorithms.getLastOperationCount();
            
            System.out.printf("Size %d: Linear(%d ops, %.1fμs) vs Binary(%d ops, %.1fμs)\n",
                            size, linearComparisons, linearTime/1000.0, 
                            binaryComparisons, binaryTime/1000.0);
        }
        
        // Test 3: Time complexity analysis
        System.out.println("\n📊 Test 3: Time complexity analysis");
        System.out.println(repeatString('-', 50));
        searchAlgorithms.displayTimeComplexityAnalysis();
        
        System.out.println("\n✅ Comprehensive testing completed!");
    }
    
    /**
     * Display goodbye message
     */
    private void displayGoodbye() {
        System.out.println("\n" + repeatString('=', 60));
        System.out.println("👋 THANK YOU FOR USING E-COMMERCE SEARCH DEMO!");
        System.out.println(repeatString('=', 60));
        System.out.println("Key Takeaways:");
        System.out.println("• Linear Search: O(n) - Simple but slower for large datasets");
        System.out.println("• Binary Search: O(log n) - Fast but requires sorted data");
        System.out.println("• Choose the right algorithm based on your use case!");
        System.out.println("\n🎓 Happy Learning!");
        System.out.println(repeatString('=', 60));
    }
    
    /**
     * Main method to run the demo
     */
    public static void main(String[] args) {
        EcommerceSearchDemo demo = new EcommerceSearchDemo();
        demo.runDemo();
    }
} 