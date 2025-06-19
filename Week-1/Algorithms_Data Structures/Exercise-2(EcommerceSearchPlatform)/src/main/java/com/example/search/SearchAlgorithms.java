package com.example.search;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * SearchAlgorithms class implementing linear and binary search algorithms
 * for e-commerce product search functionality with performance analysis
 */
public class SearchAlgorithms {
    
    private Product[] products;
    private Product[] sortedProducts;
    private int lastOperationCount;
    
    /**
     * Constructor - initializes with product array
     */
    public SearchAlgorithms(Product[] products) {
        this.products = Arrays.copyOf(products, products.length);
        this.sortedProducts = Arrays.copyOf(products, products.length);
        this.lastOperationCount = 0;
        
        // Sort products by ID for binary search
        Arrays.sort(this.sortedProducts, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Integer.compare(p1.getProductId(), p2.getProductId());
            }
        });
    }
    
    /**
     * Helper method to repeat a string (Java 8 compatible)
     */
    private static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
    
    /**
     * LINEAR SEARCH IMPLEMENTATION
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    
    /**
     * Linear search by product ID
     * @param productId The ID to search for
     * @return Product if found, null otherwise
     */
    public Product linearSearchById(int productId) {
        lastOperationCount = 0;
        
        for (int i = 0; i < products.length; i++) {
            lastOperationCount++;
            if (products[i].getProductId() == productId) {
                return products[i];
            }
        }
        return null;
    }
    
    /**
     * Linear search by product name (partial matches)
     * @param searchTerm Term to search for in product names
     * @return Array of products whose names contain the search term
     */
    public Product[] linearSearchByName(String searchTerm) {
        lastOperationCount = 0;
        List<Product> results = new ArrayList<Product>();
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return new Product[0];
        }
        
        for (int i = 0; i < products.length; i++) {
            lastOperationCount++;
            if (products[i].containsInName(searchTerm)) {
                results.add(products[i]);
            }
        }
        return results.toArray(new Product[results.size()]);
    }
    
    /**
     * Linear search by category
     * @param category Category to search for
     * @return Array of products in the specified category
     */
    public Product[] linearSearchByCategory(String category) {
        lastOperationCount = 0;
        List<Product> results = new ArrayList<Product>();
        
        if (category == null || category.trim().isEmpty()) {
            return new Product[0];
        }
        
        for (int i = 0; i < products.length; i++) {
            lastOperationCount++;
            if (products[i].isInCategory(category)) {
                results.add(products[i]);
            }
        }
        return results.toArray(new Product[results.size()]);
    }
    
    /**
     * Linear search by price range
     * @param minPrice Minimum price
     * @param maxPrice Maximum price
     * @return Array of products within price range
     */
    public Product[] linearSearchByPriceRange(double minPrice, double maxPrice) {
        lastOperationCount = 0;
        List<Product> results = new ArrayList<Product>();
        
        for (int i = 0; i < products.length; i++) {
            lastOperationCount++;
            if (products[i].isPriceInRange(minPrice, maxPrice)) {
                results.add(products[i]);
            }
        }
        return results.toArray(new Product[results.size()]);
    }
    
    /**
     * BINARY SEARCH IMPLEMENTATION
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     * Note: Requires sorted array
     */
    
    /**
     * Binary search by product ID
     * @param productId The ID to search for
     * @return Product if found, null otherwise
     */
    public Product binarySearchById(int productId) {
        lastOperationCount = 0;
        
        int left = 0;
        int right = sortedProducts.length - 1;
        
        while (left <= right) {
            lastOperationCount++;
            int mid = left + (right - left) / 2;
            int midProductId = sortedProducts[mid].getProductId();
            
            if (midProductId == productId) {
                return sortedProducts[mid];
            } else if (midProductId < productId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
    
    /**
     * Recursive binary search implementation
     * @param productId The ID to search for
     * @return Product if found, null otherwise
     */
    public Product binarySearchRecursive(int productId) {
        lastOperationCount = 0;
        return binarySearchRecursiveHelper(productId, 0, sortedProducts.length - 1);
    }
    
    /**
     * Helper method for recursive binary search
     */
    private Product binarySearchRecursiveHelper(int productId, int left, int right) {
        if (left > right) {
            return null;
        }
        
        lastOperationCount++;
        int mid = left + (right - left) / 2;
        int midProductId = sortedProducts[mid].getProductId();
        
        if (midProductId == productId) {
            return sortedProducts[mid];
        } else if (midProductId < productId) {
            return binarySearchRecursiveHelper(productId, mid + 1, right);
        } else {
            return binarySearchRecursiveHelper(productId, left, mid - 1);
        }
    }
    
    /**
     * PERFORMANCE COMPARISON AND ANALYSIS
     */
    
    /**
     * Compare performance of linear vs binary search
     * @param productId The ID to search for
     */
    public void compareSearchPerformance(int productId) {
        System.out.println("\n" + repeatString("=", 60));
        System.out.println("🚀 SEARCH PERFORMANCE COMPARISON");
        System.out.println(repeatString("=", 60));
        System.out.println("Dataset size: " + products.length + " products");
        System.out.println("Target Product ID: " + productId);
        System.out.println(repeatString("-", 60));
        
        // Linear Search Performance
        long startTime = System.nanoTime();
        Product linearResult = linearSearchById(productId);
        long linearTime = System.nanoTime() - startTime;
        int linearOperations = lastOperationCount;
        
        // Binary Search Performance
        startTime = System.nanoTime();
        Product binaryResult = binarySearchById(productId);
        long binaryTime = System.nanoTime() - startTime;
        int binaryOperations = lastOperationCount;
        
        // Display results
        System.out.println("LINEAR SEARCH:");
        System.out.println("  Result: " + (linearResult != null ? "✅ Found" : "❌ Not Found"));
        if (linearResult != null) {
            System.out.println("  Product: " + linearResult.getProductName());
        }
        System.out.println("  Time: " + linearTime + " nanoseconds");
        System.out.println("  Comparisons: " + linearOperations);
        System.out.println("  Time Complexity: O(n)");
        
        System.out.println("\nBINARY SEARCH:");
        System.out.println("  Result: " + (binaryResult != null ? "✅ Found" : "❌ Not Found"));
        if (binaryResult != null) {
            System.out.println("  Product: " + binaryResult.getProductName());
        }
        System.out.println("  Time: " + binaryTime + " nanoseconds");
        System.out.println("  Comparisons: " + binaryOperations);
        System.out.println("  Time Complexity: O(log n)");
        
        // Performance Analysis
        System.out.println("\n📊 PERFORMANCE ANALYSIS:");
        if (linearOperations > 0 && binaryOperations > 0) {
            double efficiency = (double) linearOperations / binaryOperations;
            System.out.printf("  Efficiency Gain: %.1fx fewer comparisons with binary search\n", efficiency);
        }
        
        if (binaryTime > 0) {
            double speedRatio = (double) linearTime / binaryTime;
            System.out.printf("  Speed Improvement: %.1fx faster\n", speedRatio);
        }
        
        // Theoretical Analysis
        int n = products.length;
        int theoreticalLinear = n / 2; // Average case
        int theoreticalBinary = (int) Math.ceil(Math.log(n) / Math.log(2)); // Worst case
        
        System.out.println("\n🧮 THEORETICAL ANALYSIS:");
        System.out.println("  Linear Search Average: ~" + theoreticalLinear + " comparisons");
        System.out.println("  Binary Search Worst: ~" + theoreticalBinary + " comparisons");
        System.out.printf("  Theoretical Efficiency: %.1fx\n", (double) theoreticalLinear / theoreticalBinary);
    }
    
    /**
     * Analyze search scenarios (best, average, worst case)
     */
    public void analyzeSearchScenarios() {
        System.out.println("\n" + repeatString("=", 70));
        System.out.println("📈 SEARCH SCENARIO ANALYSIS");
        System.out.println(repeatString("=", 70));
        
        if (products.length == 0) {
            System.out.println("No products available for analysis.");
            return;
        }
        
        // Best Case Scenarios
        System.out.println("\n🎯 BEST CASE SCENARIOS:");
        System.out.println("Linear Search: Target at first position");
        System.out.println("Binary Search: Target at middle position");
        
        // Test first element (best case for linear)
        Product firstProduct = products[0];
        Product linearResult = linearSearchById(firstProduct.getProductId());
        int linearBestCase = lastOperationCount;
        
        // Test middle element (best case for binary in some cases)
        Product middleProduct = sortedProducts[sortedProducts.length / 2];
        Product binaryResult = binarySearchById(middleProduct.getProductId());
        int binaryBestCase = lastOperationCount;
        
        System.out.println("Actual results:");
        System.out.println("  Linear Search (first element): " + linearBestCase + " comparisons");
        System.out.println("  Binary Search (middle element): " + binaryBestCase + " comparisons");
        
        // Worst Case Scenarios
        System.out.println("\n💥 WORST CASE SCENARIOS:");
        System.out.println("Linear Search: Target at last position or not found");
        System.out.println("Binary Search: Maximum tree depth traversal");
        
        // Test last element (worst case for linear)
        Product lastProduct = products[products.length - 1];
        linearResult = linearSearchById(lastProduct.getProductId());
        int linearWorstCase = lastOperationCount;
        
        // Test non-existent element (worst case for both)
        binaryResult = binarySearchById(99999);
        int binaryWorstCase = lastOperationCount;
        
        System.out.println("Actual results:");
        System.out.println("  Linear Search (last element): " + linearWorstCase + " comparisons");
        System.out.println("  Binary Search (not found): " + binaryWorstCase + " comparisons");
        
        // Average Case Analysis
        System.out.println("\n📊 AVERAGE CASE ANALYSIS:");
        System.out.println("  Linear Search: O(n/2) ≈ " + (products.length / 2) + " comparisons");
        System.out.println("  Binary Search: O(log n) ≈ " + 
                         (int) Math.ceil(Math.log(products.length) / Math.log(2)) + " comparisons");
    }
    
    /**
     * Get the number of operations performed in the last search
     */
    public int getLastOperationCount() {
        return lastOperationCount;
    }
    
    /**
     * Get the original products array
     */
    public Product[] getProducts() {
        return Arrays.copyOf(products, products.length);
    }
    
    /**
     * Get the sorted products array
     */
    public Product[] getSortedProducts() {
        return Arrays.copyOf(sortedProducts, sortedProducts.length);
    }
    
    /**
     * Display time complexity analysis
     */
    public static void displayTimeComplexityAnalysis() {
        System.out.println("\n" + repeatString("=", 80));
        System.out.println("                    ASYMPTOTIC NOTATION & TIME COMPLEXITY ANALYSIS");
        System.out.println(repeatString("=", 80));
        
        System.out.println("\n🎓 BIG O NOTATION EXPLANATION:");
        System.out.println("Big O notation describes the upper bound of an algorithm's time complexity.");
        System.out.println("It represents the worst-case scenario for how the algorithm performs as the");
        System.out.println("input size (n) grows to infinity.");
        
        System.out.println("\n📊 COMMON TIME COMPLEXITIES (from best to worst):");
        System.out.println("  O(1)      - Constant time (best)");
        System.out.println("  O(log n)  - Logarithmic time (excellent)");
        System.out.println("  O(n)      - Linear time (good)");
        System.out.println("  O(n log n)- Linearithmic time (fair)");
        System.out.println("  O(n²)     - Quadratic time (poor)");
        System.out.println("  O(2ⁿ)     - Exponential time (terrible)");
        
        System.out.println("\n🔍 SEARCH ALGORITHM ANALYSIS:");
        
        System.out.println("\n1️⃣  LINEAR SEARCH - O(n)");
        System.out.println("   ┌─────────────────┬──────────────────────────────────────┐");
        System.out.println("   │ Best Case       │ O(1) - Element at first position     │");
        System.out.println("   │ Average Case    │ O(n/2) - Element in middle          │");
        System.out.println("   │ Worst Case      │ O(n) - Element at end or not found  │");
        System.out.println("   └─────────────────┴──────────────────────────────────────┘");
        System.out.println("   ✅ Advantages: Works on unsorted data, simple implementation");
        System.out.println("   ❌ Disadvantages: Slow for large datasets");
        
        System.out.println("\n2️⃣  BINARY SEARCH - O(log n)");
        System.out.println("   ┌─────────────────┬──────────────────────────────────────┐");
        System.out.println("   │ Best Case       │ O(1) - Element at middle position    │");
        System.out.println("   │ Average Case    │ O(log n) - Divide and conquer       │");
        System.out.println("   │ Worst Case      │ O(log n) - Maximum divisions needed │");
        System.out.println("   └─────────────────┴──────────────────────────────────────┘");
        System.out.println("   ✅ Advantages: Very fast for large datasets");
        System.out.println("   ❌ Disadvantages: Requires sorted data");
        
        System.out.println("\n📈 PERFORMANCE COMPARISON FOR DIFFERENT DATASET SIZES:");
        System.out.println("   ┌──────────────┬─────────────────┬─────────────────┐");
        System.out.println("   │ Dataset Size │ Linear Search   │ Binary Search   │");
        System.out.println("   │              │ (comparisons)   │ (comparisons)   │");
        System.out.println("   ├──────────────┼─────────────────┼─────────────────┤");
        System.out.println("   │ 100          │ ~50             │ ~7              │");
        System.out.println("   │ 1,000        │ ~500            │ ~10             │");
        System.out.println("   │ 10,000       │ ~5,000          │ ~13             │");
        System.out.println("   │ 100,000      │ ~50,000         │ ~17             │");
        System.out.println("   │ 1,000,000    │ ~500,000        │ ~20             │");
        System.out.println("   └──────────────┴─────────────────┴─────────────────┘");
        
        System.out.println("\n💡 RECOMMENDATION FOR E-COMMERCE PLATFORM:");
        System.out.println("   For Product ID searches: Use BINARY SEARCH (requires sorted data)");
        System.out.println("   For Name/Category searches: Use LINEAR SEARCH (data may not be sorted)");
        System.out.println("   For hybrid approach: Use indexing/hash tables for O(1) average case");
    }
    
    /**
     * Perform comprehensive performance benchmarking
     */
    public void performBenchmark() {
        System.out.println("\n" + repeatString("=", 70));
        System.out.println("🚀 COMPREHENSIVE PERFORMANCE BENCHMARK");
        System.out.println(repeatString("=", 70));
        
        int[] testCases = {1, products.length / 4, products.length / 2, 
                          3 * products.length / 4, products.length};
        
        System.out.println("Testing with " + products.length + " products:");
        System.out.println(repeatString("-", 70));
        
        for (int i = 0; i < testCases.length; i++) {
            if (testCases[i] <= products.length && testCases[i] > 0) {
                int targetId = products[testCases[i] - 1].getProductId();
                System.out.println("\nTest Case " + (i + 1) + ": Searching for position " + testCases[i]);
                compareSearchPerformance(targetId);
            }
        }
    }
} 