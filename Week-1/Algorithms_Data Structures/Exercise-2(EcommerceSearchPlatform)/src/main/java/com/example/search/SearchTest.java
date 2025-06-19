package com.example.search;

import java.util.List;
import java.util.Arrays;

/**
 * Comprehensive test class for search algorithms
 * Includes theory explanations, performance tests, and algorithm comparisons
 */
public class SearchTest {
    
    private ProductDataGenerator dataGenerator;
    
    public SearchTest() {
        this.dataGenerator = new ProductDataGenerator();
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
     * Main test method
     */
    public static void main(String[] args) {
        SearchTest tester = new SearchTest();
        
        if (args.length > 0) {
            String testType = args[0].toLowerCase();
            switch (testType) {
                case "theory":
                    tester.explainTheory();
                    break;
                case "basic":
                    tester.runBasicTests();
                    break;
                case "performance":
                    tester.runPerformanceTests();
                    break;
                case "edge":
                    tester.runEdgeCaseTests();
                    break;
                case "all":
                    tester.runAllTests();
                    break;
                default:
                    tester.runAllTests();
            }
        } else {
            tester.runAllTests();
        }
    }
    
    /**
     * Run all comprehensive tests
     */
    public void runAllTests() {
        System.out.println(repeatString('=', 80));
        System.out.println("🧪 COMPREHENSIVE SEARCH ALGORITHM TEST SUITE");
        System.out.println(repeatString('=', 80));
        System.out.println("Running complete test suite for Linear and Binary Search algorithms\n");
        
        runBasicTests();
        runPerformanceTests();
        runEdgeCaseTests();
        explainTheory();
        
        System.out.println("\n" + repeatString('=', 80));
        System.out.println("✅ ALL TESTS COMPLETED SUCCESSFULLY!");
        System.out.println(repeatString('=', 80));
    }
    
    /**
     * Run basic functionality tests
     */
    public void runBasicTests() {
        System.out.println("\n" + repeatString('-', 60));
        System.out.println("🔧 BASIC FUNCTIONALITY TESTS");
        System.out.println(repeatString('-', 60));
        
        // Test with sample dataset
        Product[] products = dataGenerator.generateSampleProducts();
        SearchAlgorithms searchAlgorithms = new SearchAlgorithms(products);
        
        System.out.println("Dataset: Sample Products (" + products.length + " items)");
        
        // Test 1: Search for existing product (first)
        System.out.println("\n📋 Test 1: Search for first product");
        int firstId = products[0].getProductId();
        testSearch(searchAlgorithms, firstId, "First Product");
        
        // Test 2: Search for existing product (middle)
        System.out.println("\n📋 Test 2: Search for middle product");
        int middleId = products[products.length / 2].getProductId();
        testSearch(searchAlgorithms, middleId, "Middle Product");
        
        // Test 3: Search for existing product (last)
        System.out.println("\n📋 Test 3: Search for last product");
        int lastId = products[products.length - 1].getProductId();
        testSearch(searchAlgorithms, lastId, "Last Product");
        
        // Test 4: Search for non-existing product
        System.out.println("\n📋 Test 4: Search for non-existing product");
        testSearch(searchAlgorithms, 99999, "Non-existing Product");
        
        // Test 5: Name search
        System.out.println("\n📋 Test 5: Search by product name");
        Product[] nameResults = searchAlgorithms.linearSearchByName("iPhone");
        System.out.println("Search term: 'iPhone'");
        System.out.println("Results found: " + nameResults.length);
        for (Product product : nameResults) {
            System.out.println("- " + product.getProductName() + " (ID: " + product.getProductId() + ")");
        }
        
        // Test 6: Category search
        System.out.println("\n📋 Test 6: Search by category");
        Product[] categoryResults = searchAlgorithms.linearSearchByCategory("Smartphones");
        System.out.println("Category: 'Smartphones'");
        System.out.println("Results found: " + categoryResults.length);
        for (Product product : categoryResults) {
            System.out.println("- " + product.getProductName() + " (ID: " + product.getProductId() + ")");
        }
    }
    
    /**
     * Helper method to test both search algorithms
     */
    private void testSearch(SearchAlgorithms searchAlgorithms, int productId, String testName) {
        System.out.println("Searching for Product ID: " + productId + " (" + testName + ")");
        
        // Linear Search
        long startTime = System.nanoTime();
        Product linearResult = searchAlgorithms.linearSearchById(productId);
        long linearTime = System.nanoTime() - startTime;
        int linearComparisons = searchAlgorithms.getLastOperationCount();
        
        // Binary Search
        startTime = System.nanoTime();
        Product binaryResult = searchAlgorithms.binarySearchById(productId);
        long binaryTime = System.nanoTime() - startTime;
        int binaryComparisons = searchAlgorithms.getLastOperationCount();
        
        // Results
        boolean linearFound = linearResult != null;
        boolean binaryFound = binaryResult != null;
        
        System.out.printf("Linear Search:  %s | %d comparisons | %.2f μs\n", 
                         linearFound ? "FOUND" : "NOT FOUND", linearComparisons, linearTime / 1000.0);
        System.out.printf("Binary Search:  %s | %d comparisons | %.2f μs\n", 
                         binaryFound ? "FOUND" : "NOT FOUND", binaryComparisons, binaryTime / 1000.0);
        
        if (linearFound && binaryFound) {
            System.out.println("Product: " + linearResult.getProductName());
        }
        
        // Verify consistency
        if (linearFound == binaryFound) {
            System.out.println("✅ Results consistent between algorithms");
        } else {
            System.out.println("❌ Results inconsistent between algorithms");
        }
    }
    
    /**
     * Run performance tests with different dataset sizes
     */
    public void runPerformanceTests() {
        System.out.println("\n" + repeatString('-', 60));
        System.out.println("⚡ PERFORMANCE TESTS");
        System.out.println(repeatString('-', 60));
        
        int[] testSizes = {10, 50, 100, 500, 1000};
        
        System.out.println("Testing performance with different dataset sizes...\n");
        System.out.printf("%-8s %-20s %-20s %-15s\n", "Size", "Linear (μs)", "Binary (μs)", "Speedup");
        System.out.println(repeatString('-', 65));
        
        for (int size : testSizes) {
            performanceTestWithSize(size);
        }
        
        System.out.println("\n📊 Performance Analysis:");
        System.out.println("• Linear Search performance decreases linearly with dataset size");
        System.out.println("• Binary Search performance increases logarithmically with dataset size");
        System.out.println("• Speedup factor increases with larger datasets");
        System.out.println("• Binary Search becomes more advantageous with larger datasets");
    }
    
    /**
     * Performance test for specific dataset size
     */
    private void performanceTestWithSize(int size) {
        Product[] products = dataGenerator.generateRandomProducts(size);
        SearchAlgorithms searchAlgorithms = new SearchAlgorithms(products);
        
        // Test multiple searches and take average
        int numTests = Math.min(10, size);
        long totalLinearTime = 0;
        long totalBinaryTime = 0;
        
        for (int i = 0; i < numTests; i++) {
            int testIndex = (i * size) / numTests;
            int productId = products[testIndex].getProductId();
            
            // Linear Search
            long startTime = System.nanoTime();
            searchAlgorithms.linearSearchById(productId);
            totalLinearTime += System.nanoTime() - startTime;
            
            // Binary Search
            startTime = System.nanoTime();
            searchAlgorithms.binarySearchById(productId);
            totalBinaryTime += System.nanoTime() - startTime;
        }
        
        double avgLinearTime = (totalLinearTime / numTests) / 1000.0;
        double avgBinaryTime = (totalBinaryTime / numTests) / 1000.0;
        double speedup = avgLinearTime / avgBinaryTime;
        
        System.out.printf("%-8d %-20.2f %-20.2f %.2fx\n", 
                         size, avgLinearTime, avgBinaryTime, speedup);
    }
    
    /**
     * Run edge case tests
     */
    public void runEdgeCaseTests() {
        System.out.println("\n" + repeatString('-', 60));
        System.out.println("🎯 EDGE CASE TESTS");
        System.out.println(repeatString('-', 60));
        
        // Test 1: Empty dataset
        System.out.println("\n📋 Test 1: Empty dataset");
        Product[] emptyProducts = new Product[0];
        SearchAlgorithms emptySearch = new SearchAlgorithms(emptyProducts);
        
        Product result1 = emptySearch.linearSearchById(1);
        Product result2 = emptySearch.binarySearchById(1);
        System.out.println("Linear Search on empty dataset: " + (result1 == null ? "NULL (correct)" : "ERROR"));
        System.out.println("Binary Search on empty dataset: " + (result2 == null ? "NULL (correct)" : "ERROR"));
        
        // Test 2: Single element dataset
        System.out.println("\n📋 Test 2: Single element dataset");
        Product[] singleProduct = {new Product(100, "Single Product", "Test", 50.0, "Test product", 1, 4.0)};
        SearchAlgorithms singleSearch = new SearchAlgorithms(singleProduct);
        
        // Search for existing element
        Product result3 = singleSearch.linearSearchById(100);
        Product result4 = singleSearch.binarySearchById(100);
        System.out.println("Search for existing element (100):");
        System.out.println("Linear Search: " + (result3 != null ? "FOUND" : "NOT FOUND"));
        System.out.println("Binary Search: " + (result4 != null ? "FOUND" : "NOT FOUND"));
        
        // Search for non-existing element
        Product result5 = singleSearch.linearSearchById(200);
        Product result6 = singleSearch.binarySearchById(200);
        System.out.println("Search for non-existing element (200):");
        System.out.println("Linear Search: " + (result5 == null ? "NOT FOUND (correct)" : "ERROR"));
        System.out.println("Binary Search: " + (result6 == null ? "NOT FOUND (correct)" : "ERROR"));
        
        // Test 3: Duplicate IDs (edge case)
        System.out.println("\n📋 Test 3: Large dataset stress test");
        Product[] largeDataset = dataGenerator.generateRandomProducts(1000);
        SearchAlgorithms largeSearch = new SearchAlgorithms(largeDataset);
        
        int testId = largeDataset[500].getProductId();
        long startTime = System.nanoTime();
        Product result7 = largeSearch.linearSearchById(testId);
        long linearTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        Product result8 = largeSearch.binarySearchById(testId);
        long binaryTime = System.nanoTime() - startTime;
        
        System.out.println("Large dataset (1000 elements) search for middle element:");
        System.out.printf("Linear Search: %.2f μs\n", linearTime / 1000.0);
        System.out.printf("Binary Search: %.2f μs\n", binaryTime / 1000.0);
        System.out.printf("Performance difference: %.2fx faster\n", (double) linearTime / binaryTime);
        
        // Test 4: Sequential ID testing
        System.out.println("\n📋 Test 4: Sequential ID pattern testing");
        Product[] sequentialProducts = dataGenerator.generateProductsWithIdPattern(20, 1, 1);
        SearchAlgorithms sequentialSearch = new SearchAlgorithms(sequentialProducts);
        
        // Test best case (first element)
        sequentialSearch.compareSearchPerformance(1);
    }
    
    /**
     * Explain search algorithm theory and complexity analysis
     */
    public void explainTheory() {
        System.out.println("\n" + repeatString('=', 80));
        System.out.println("📚 SEARCH ALGORITHM THEORY & COMPLEXITY ANALYSIS");
        System.out.println(repeatString('=', 80));
        
        // Big O Notation explanation
        System.out.println("\n🎯 BIG O NOTATION FUNDAMENTALS");
        System.out.println(repeatString('-', 50));
        System.out.println("Big O notation describes the upper bound of algorithm complexity.");
        System.out.println("It helps us understand how algorithms scale with input size.\n");
        
        System.out.println("Common Time Complexities (from best to worst):");
        System.out.println("• O(1)      - Constant time (best possible)");
        System.out.println("• O(log n)  - Logarithmic time (very good)");
        System.out.println("• O(n)      - Linear time (acceptable)");
        System.out.println("• O(n log n)- Linearithmic time (reasonable)");
        System.out.println("• O(n²)     - Quadratic time (poor for large n)");
        System.out.println("• O(2ⁿ)     - Exponential time (terrible)");
        
        // Linear Search Analysis
        System.out.println("\n🔍 LINEAR SEARCH ANALYSIS");
        System.out.println(repeatString('-', 50));
        System.out.println("Algorithm: Sequential search through array elements");
        System.out.println("Time Complexity: O(n) where n = number of elements");
        System.out.println("Space Complexity: O(1) - constant space usage\n");
        
        System.out.println("Scenario Analysis:");
        System.out.println("• Best Case:    O(1) - Element found at first position");
        System.out.println("• Average Case: O(n/2) - Element found in middle");
        System.out.println("• Worst Case:   O(n) - Element at end or not found\n");
        
        System.out.println("Advantages:");
        System.out.println("✅ Works on unsorted data");
        System.out.println("✅ Simple to implement");
        System.out.println("✅ No preprocessing required");
        System.out.println("✅ Memory efficient\n");
        
        System.out.println("Disadvantages:");
        System.out.println("❌ Slow for large datasets");
        System.out.println("❌ Performance degrades linearly");
        System.out.println("❌ Not suitable for frequent searches");
        
        // Binary Search Analysis
        System.out.println("\n🎯 BINARY SEARCH ANALYSIS");
        System.out.println(repeatString('-', 50));
        System.out.println("Algorithm: Divide-and-conquer search on sorted array");
        System.out.println("Time Complexity: O(log n) where n = number of elements");
        System.out.println("Space Complexity: O(1) - iterative implementation\n");
        
        System.out.println("Scenario Analysis:");
        System.out.println("• Best Case:    O(1) - Element found at middle position");
        System.out.println("• Average Case: O(log n) - Typical binary tree traversal");
        System.out.println("• Worst Case:   O(log n) - Element at leaf or not found\n");
        
        System.out.println("Advantages:");
        System.out.println("✅ Very fast for large datasets");
        System.out.println("✅ Logarithmic performance scaling");
        System.out.println("✅ Optimal for frequent searches");
        System.out.println("✅ Predictable performance\n");
        
        System.out.println("Disadvantages:");
        System.out.println("❌ Requires sorted data");
        System.out.println("❌ Preprocessing overhead (sorting)");
        System.out.println("❌ More complex implementation");
        System.out.println("❌ Not suitable for frequently changing data");
        
        // Performance Comparison
        System.out.println("\n⚡ PERFORMANCE COMPARISON");
        System.out.println(repeatString('-', 50));
        System.out.println("Dataset Size vs. Maximum Comparisons Required:\n");
        
        System.out.printf("%-15s %-15s %-15s %-15s\n", "Dataset Size", "Linear Search", "Binary Search", "Improvement");
        System.out.println(repeatString('-', 65));
        
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
        for (int size : sizes) {
            int linearComparisons = size;
            int binaryComparisons = (int) Math.ceil(Math.log(size) / Math.log(2));
            double improvement = (double) linearComparisons / binaryComparisons;
            
            System.out.printf("%-15s %-15d %-15d %.1fx\n", 
                             formatNumber(size), linearComparisons, binaryComparisons, improvement);
        }
        
        // Real-world Applications
        System.out.println("\n🌍 REAL-WORLD E-COMMERCE APPLICATIONS");
        System.out.println(repeatString('-', 50));
        System.out.println("Linear Search Use Cases:");
        System.out.println("• Product name/description search (text matching)");
        System.out.println("• Category-based filtering");
        System.out.println("• Price range queries");
        System.out.println("• Recent searches/browsing history");
        System.out.println("• Small inventory systems (<100 items)\n");
        
        System.out.println("Binary Search Use Cases:");
        System.out.println("• Product ID lookups (primary key searches)");
        System.out.println("• Price-sorted product lists");
        System.out.println("• Inventory management systems");
        System.out.println("• Large catalog searches (>1000 items)");
        System.out.println("• Auto-complete suggestions\n");
        
        System.out.println("📊 RECOMMENDATION FOR E-COMMERCE PLATFORM:");
        System.out.println(repeatString('-', 50));
        System.out.println("Hybrid Approach for Optimal Performance:\n");
        System.out.println("1. Product ID Search:");
        System.out.println("   → Use Binary Search (data sorted by ID)");
        System.out.println("   → Ideal for: Cart operations, order management\n");
        
        System.out.println("2. Name/Category Search:");
        System.out.println("   → Use Linear Search with optimizations");
        System.out.println("   → Ideal for: User browsing, filter operations\n");
        
        System.out.println("3. Large Scale Solutions:");
        System.out.println("   → Database indexing (B-trees)");
        System.out.println("   → Caching strategies (Redis/Memcached)");
        System.out.println("   → Search engines (Elasticsearch)\n");
        
        System.out.println("🎯 PRACTICAL TIPS:");
        System.out.println("• Use Binary Search when data is pre-sorted");
        System.out.println("• Consider sorting cost vs. search frequency");
        System.out.println("• Implement caching for frequently accessed data");
        System.out.println("• Profile your application to identify bottlenecks");
        System.out.println("• Choose algorithm based on data characteristics");
    }
    
    /**
     * Format large numbers with commas
     */
    private String formatNumber(int number) {
        if (number >= 1000000) {
            return String.format("%.1fM", number / 1000000.0);
        } else if (number >= 1000) {
            return String.format("%.1fK", number / 1000.0);
        } else {
            return String.valueOf(number);
        }
    }
} 