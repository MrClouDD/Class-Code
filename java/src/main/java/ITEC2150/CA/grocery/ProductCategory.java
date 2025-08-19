package ITEC2150.CA.grocery;

import java.util.Objects;

/**
 * ProductCategory class to represent a product category with id and name.
 * Optimized for performance and includes validation checks.
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-08
 */
public class ProductCategory implements Comparable<ProductCategory> {
    private int id;
    private String name;
    
    // Cache hash code for better performance in collections
    private int hashCode;
    private boolean hashCodeCached = false;
    
    /**
     * Default constructor
     */
    public ProductCategory() {
        this.id = 0;
        this.name = "";
    }
    
    /**
     * Constructor with parameters
     * @param id the category id (must be positive)
     * @param name the category name (cannot be null or empty)
     * @throws IllegalArgumentException if id is negative or name is null/empty
     */
    public ProductCategory(int id, String name) {
        // Validate parameters directly in constructor to avoid overridable method calls
        if (id < 0) {
            throw new IllegalArgumentException("Category ID cannot be negative: " + id);
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        
        this.id = id;
        this.name = name.trim();
        this.hashCodeCached = false;
    }
    
    /**
     * Getter for id
     * @return the category id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Setter for id with validation
     * @param id the category id to set (must be positive)
     * @throws IllegalArgumentException if id is negative
     */
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Category ID cannot be negative: " + id);
        }
        this.id = id;
        this.hashCodeCached = false; // Reset cache when data changes
    }
    
    /**
     * Getter for name
     * @return the category name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Setter for name with validation
     * @param name the category name to set (cannot be null or empty)
     * @throws IllegalArgumentException if name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        this.name = name.trim(); // Remove leading/trailing whitespace
        this.hashCodeCached = false; // Reset cache when data changes
    }
    
    /**
     * Optimized equals method for O(1) performance in most cases
     * @param obj the object to compare with
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Reference equality check - O(1)
        if (obj == null || getClass() != obj.getClass()) return false; // Type check - O(1)
        
        ProductCategory that = (ProductCategory) obj;
        return id == that.id && Objects.equals(name, that.name); // O(1) for id, O(n) for name
    }
    
    /**
     * Cached hash code for better performance in hash-based collections
     * @return the hash code
     */
    @Override
    public int hashCode() {
        if (!hashCodeCached) {
            hashCode = Objects.hash(id, name);
            hashCodeCached = true;
        }
        return hashCode;
    }
    
    /**
     * Natural ordering by id for sorting - O(1)
     * @param other the other ProductCategory to compare to
     * @return negative if this < other, 0 if equal, positive if this > other
     */
    @Override
    public int compareTo(ProductCategory other) {
        return Integer.compare(this.id, other.id);
    }
    
    /**
     * String representation of the category
     * @return formatted string with id and name
     */
    @Override
    public String toString() {
        return id + " " + name;
    }
    
    /**
     * Validates if the category is properly initialized
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        return id > 0 && name != null && !name.trim().isEmpty();
    }
}
