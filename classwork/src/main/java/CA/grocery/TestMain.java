package CA.grocery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Enhanced TestMain class to read product categories from file and demonstrate
 * advanced features of the ProductCategory class.
 * Uses divide and conquer approach with OOP principles.
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-08
 */
public class TestMain {
    private static final String FILE_NAME = "prodcats.txt";
    private static final String[] CATEGORY_NAMES = {"Bakery", "Deli", "Grocery", "Meat", "Produce", "Seafood"};
    
    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        
        // Ensure file exists, create if needed
        testMain.ensureFileExists();
        
        // Read categories from file
        List<ProductCategory> categories = testMain.readCategoriesFromFile();
        
        if (!categories.isEmpty()) {
            // Display basic information
            testMain.displayCategories(categories);
            
            // Demonstrate enhanced features
            // testMain.demonstrateFeatures(categories);
            
            // Perform validation tests
            // testMain.performValidationTests();
        }

        // Print signature
        System.out.println("Created by Ajitesh Sandhu");
    }
    
    /**
     * Reads product categories from file using dynamic list instead of fixed array
     * @return List of ProductCategory objects
     */
    private List<ProductCategory> readCategoriesFromFile() {
        String filePath = getPackageDirectory() + File.separator + FILE_NAME;
        List<ProductCategory> categories = new ArrayList<>();
        
        // Use FileReader wrapped with BufferedReader to read the file
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            
            System.out.println("Reading categories from: " + filePath);
            
            // Read the file line by line
            String line;
            int lineNumber = 0;
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                try {
                    // Parse the line to extract ID and name
                    String[] parts = line.split(" ", 2); // Split into at most 2 parts
                    if (parts.length == 2) {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        
                        // Create and validate ProductCategory object
                        ProductCategory category = new ProductCategory(id, name);
                        if (category.isValid()) {
                            categories.add(category);
                        } else {
                            System.err.println("Warning: Invalid category at line " + lineNumber);
                        }
                    } else {
                        System.err.println("Warning: Invalid format at line " + lineNumber + ": " + line);
                    }
                } catch (NumberFormatException nfe) {
                    System.err.println("Error parsing ID at line " + lineNumber + ": " + nfe.getMessage());
                } catch (IllegalArgumentException iae) {
                    System.err.println("Error creating category at line " + lineNumber + ": " + iae.getMessage());
                }
            }
            
            System.out.println("Successfully read " + categories.size() + " categories from file.\n");
            
        } catch (FileNotFoundException fne) {
            System.err.println("File not found: " + FILE_NAME);
            System.err.println("Please run ProdCats class first to generate the file.");
        } catch (IOException ioe) {
            System.err.println("Error reading the file: " + ioe.getMessage());
        }
        
        return categories;
    }
    
    /**
     * Displays categories in the required format
     * @param categories List of ProductCategory objects
     */
    private void displayCategories(List<ProductCategory> categories) {
        System.out.println("=== PRODUCT CATEGORIES ===");
        System.out.println("ID Name");
        System.out.println("-".repeat(20));
        
        for (ProductCategory category : categories) {
            System.out.println(category.toString()); // Uses overridden toString()
        }
        System.out.println();
    }
    
    /**
     * Demonstrates enhanced features of the ProductCategory class
     * @param categories List of ProductCategory objects
     */
    private void demonstrateFeatures(List<ProductCategory> categories) {
        System.out.println("=== ENHANCED FEATURES DEMONSTRATION ===");
        
        // 1. Demonstrate sorting using Comparable interface
        System.out.println("1. Sorting categories by ID:");
        List<ProductCategory> sortedCategories = new ArrayList<>(categories);
        Collections.sort(sortedCategories); // Uses compareTo method
        sortedCategories.forEach(cat -> System.out.println("   " + cat));
        
        // 2. Demonstrate reverse sorting
        System.out.println("\n2. Reverse sorting:");
        Collections.reverse(sortedCategories);
        sortedCategories.forEach(cat -> System.out.println("   " + cat));
        
        // 3. Demonstrate HashSet functionality (eliminates duplicates)
        System.out.println("\n3. Using HashSet (demonstrates equals/hashCode):");
        Set<ProductCategory> uniqueCategories = new HashSet<>(categories);
        
        // Add a duplicate to show it won't be added
        if (!categories.isEmpty()) {
            ProductCategory duplicate = new ProductCategory(categories.get(0).getId(), categories.get(0).getName());
            uniqueCategories.add(duplicate);
            System.out.println("   Added duplicate category - Set size: " + uniqueCategories.size());
        }
        
        // 4. Demonstrate search functionality
        System.out.println("\n4. Search functionality:");
        if (!categories.isEmpty()) {
            ProductCategory searchTarget = categories.get(0);
            boolean found = uniqueCategories.contains(searchTarget);
            System.out.println("   Searching for: " + searchTarget + " - Found: " + found);
        }
        
        // 5. Demonstrate performance with hash code caching
        System.out.println("\n5. Hash code caching demonstration:");
        if (!categories.isEmpty()) {
            ProductCategory testCategory = categories.get(0);
            int hash1 = testCategory.hashCode();
            int hash2 = testCategory.hashCode();
            System.out.println("   First hash: " + hash1);
            System.out.println("   Second hash: " + hash2 + " (cached)");
            System.out.println("   Same hash codes: " + (hash1 == hash2));
        }
        
        System.out.println();
    }
    
    /**
     * Performs validation tests to demonstrate error handling
     */
    private void performValidationTests() {
        System.out.println("=== VALIDATION TESTS ===");
        
        // Test 1: Invalid ID
        System.out.println("1. Testing invalid ID:");
        try {
            new ProductCategory(-1, "Invalid");
            System.out.println("   ERROR: Should have thrown exception!");
        } catch (IllegalArgumentException e) {
            System.out.println("   ✓ Correctly caught: " + e.getMessage());
        }
        
        // Test 2: Null name
        System.out.println("\n2. Testing null name:");
        try {
            new ProductCategory(1, null);
            System.out.println("   ERROR: Should have thrown exception!");
        } catch (IllegalArgumentException e) {
            System.out.println("   ✓ Correctly caught: " + e.getMessage());
        }
        
        // Test 3: Empty name
        System.out.println("\n3. Testing empty name:");
        try {
            new ProductCategory(1, "   ");
            System.out.println("   ERROR: Should have thrown exception!");
        } catch (IllegalArgumentException e) {
            System.out.println("   ✓ Correctly caught: " + e.getMessage());
        }
        
        // Test 4: Valid category
        System.out.println("\n4. Testing valid category:");
        try {
            ProductCategory valid = new ProductCategory(1, "  Valid Category  ");
            System.out.println("   ✓ Successfully created: " + valid);
            System.out.println("   ✓ Name trimmed: '" + valid.getName() + "'");
            System.out.println("   ✓ Is valid: " + valid.isValid());
        } catch (Exception e) {
            System.out.println("   ERROR: " + e.getMessage());
        }
        
        // Test 5: Setter validation
        System.out.println("\n5. Testing setter validation:");
        try {
            ProductCategory test = new ProductCategory(1, "Test");
            test.setName(""); // Should throw exception
            System.out.println("   ERROR: Should have thrown exception!");
        } catch (IllegalArgumentException e) {
            System.out.println("   ✓ Correctly caught setter validation: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    /**
     * Ensures the product categories file exists, creates it if needed.
     * Follows divide and conquer approach by separating file existence check from creation.
     */
    private void ensureFileExists() {
        String filePath = getPackageDirectory() + File.separator + FILE_NAME;
        File file = new File(filePath);
        
        if (!file.exists()) {
            System.out.println("File " + FILE_NAME + " not found. Creating it...");
            createCategoriesFile();
        } else {
            System.out.println("File " + FILE_NAME + " found. Ready to read.");
        }
    }
    
    /**
     * Creates the product categories file with predefined categories.
     * Encapsulates file creation logic following OOP principles.
     */
    private void createCategoriesFile() {
        String packageDir = getPackageDirectory();
        File newFile = new File(packageDir, FILE_NAME);
        
        // Ensure the package directory exists and has write permissions
        File directory = newFile.getParentFile();
        if (!directory.exists() && !directory.mkdirs()) {
            System.err.println("Failed to create package directory: " + directory.getAbsolutePath());
            return;
        }
        
        // Create file with error handling using try-with-resources
        try (PrintWriter printWriter = new PrintWriter(newFile)) {
            
            // Write each category to the file with its ID
            for (int i = 0; i < CATEGORY_NAMES.length; i++) {
                printWriter.println((i + 1) + " " + CATEGORY_NAMES[i]);
            }
            
            System.out.println("✓ Product categories file created successfully in package: " + newFile.getAbsolutePath());
            
        } catch (FileNotFoundException fne) {
            System.err.println("Error creating file: " + fne.getMessage());
            // Attempt alternative file creation method
            createFileAlternative(newFile);
        }
    }
    
    /**
     * Alternative file creation method for error recovery.
     * Demonstrates defensive programming and error handling.
     */
    private void createFileAlternative(File newFile) {
        try {
            if (newFile.createNewFile()) {
                try (PrintWriter printWriter = new PrintWriter(newFile)) {
                    
                    // Write each category to the file with its ID
                    for (int i = 0; i < CATEGORY_NAMES.length; i++) {
                        printWriter.println((i + 1) + " " + CATEGORY_NAMES[i]);
                    }
                    
                    System.out.println("✓ File created successfully using alternative method.");
                }
            } else {
                System.err.println("✗ Failed to create the file using alternative method.");
            }
        } catch (IOException ioe) {
            System.err.println("✗ Error during alternative file creation: " + ioe.getMessage());
        }
    }
    
    /**
     * Gets the package directory path where the classes are located.
     * Follows Java package structure conventions.
     * 
     * @return String path to the package directory
     */
    private String getPackageDirectory() {
        // Get the base source directory
        String userDir = System.getProperty("user.dir");
        String packagePath = "src" + File.separator + "main" + File.separator + "java" + 
                           File.separator + "CA" + File.separator + "grocery";
        
        return userDir + File.separator + packagePath;
    }
}
