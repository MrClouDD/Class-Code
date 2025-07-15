import java.util.Scanner;

/**
 * Factorial Using Iteration and Recursion
 * 
 * This program calculates the factorial of a non-negative integer using two different methods:
 * 1. Iterative method using loops
 * 2. Recursive method
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-15
 */
public class FactorialCalculator {
    
    public static void main(String[] args) {
        // Print full name at the beginning
        System.out.println("Program created by: Ajitesh Sandhu");
        System.out.println("=".repeat(50));
        
        // Create scanner for user input using try-with-resources
        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt user for input
            System.out.print("Enter a non-negative integer: ");
            int inputNumber = scanner.nextInt();
            
            // Validate input - ensure it's non-negative
            if (inputNumber < 0) {
                System.out.println("Error: Please enter a non-negative integer.");
                return;
            }
            
            // Display the input number
            System.out.println("\nInput number: " + inputNumber);
            System.out.println("=".repeat(50));
            
            // Method 1: Iterative approach
            long iterativeResult = factorialIterative(inputNumber);
            System.out.println("Iterative method result: " + inputNumber + "! = " + iterativeResult);
            
            // Method 2: Recursive approach
            long recursiveResult = factorialRecursive(inputNumber);
            System.out.println("Recursive method result: " + inputNumber + "! = " + recursiveResult);
            
            // Verify both methods produce the same result
            System.out.println("\n" + "=".repeat(50));
            if (iterativeResult == recursiveResult) {
                System.out.println("✓ Both methods produced the same result!");
            } else {
                System.out.println("✗ Methods produced different results - check implementation!");
            }
        }
    }
    
    /**
     * Method 1: Calculate factorial using iterative approach
     * Logic: Start with result = 1, multiply by each number from 1 to n
     * 
     * @param n The number to calculate factorial for
     * @return The factorial of n
     */
    public static long factorialIterative(int n) {
        // Handle base case: 0! = 1
        if (n == 0) {
            return 1;
        }
        
        // Initialize result to 1
        long result = 1;
        
        // Multiply result by each number from 1 to n
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        
        // Return the calculated factorial
        return result;
    }
    
    /**
     * Method 2: Calculate factorial using recursive approach
     * Logic: Base case - if n is 0 or 1, return 1
     *        Recursive case - return n * factorial(n-1)
     * 
     * @param n The number to calculate factorial for
     * @return The factorial of n
     */
    public static long factorialRecursive(int n) {
        // Base case: 0! = 1 and 1! = 1
        if (n <= 1) {
            return 1;
        }
        
        // Recursive case: n! = n * (n-1)!
        // This breaks down the problem into smaller subproblems
        return n * factorialRecursive(n - 1);
    }
}
