import java.util.Scanner;

/**
 * Reverse a String Using Iteration, StringBuffer, and Recursion
 * 
 * This program reverses a string using three different methods:
 * 1. Iterative method using loops
 * 2. StringBuffer's built-in reverse method
 * 3. Recursive method
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-14
 */
public class StringReverser {
    
    public static void main(String[] args) {
        // Print full name at the beginning
        System.out.println("Program created by: Ajitesh Sandhu");
        System.out.println("=".repeat(50));
        
        // Create scanner for user input using try-with-resources
        try (Scanner scanner = new Scanner(System.in)) {
            // Prompt user for input
            System.out.print("Enter a string to reverse: ");
            String inputString = scanner.nextLine();
            
            // Display the original string
            System.out.println("\nOriginal string: " + inputString);
            System.out.println("=".repeat(50));
            
            // Method 1: Iterative approach
            String iterativeResult = reverseIterative(inputString);
            System.out.println("Iterative method result: " + iterativeResult);
            
            // Method 2: StringBuffer approach
            String stringBufferResult = reverseStringBuffer(inputString);
            System.out.println("StringBuffer method result: " + stringBufferResult);
            
            // Method 3: Recursive approach
            String recursiveResult = reverseRecursive(inputString);
            System.out.println("Recursive method result: " + recursiveResult);
            
            // Verify all methods produce the same result
            System.out.println("\n" + "=".repeat(50));
            if (iterativeResult.equals(stringBufferResult) && stringBufferResult.equals(recursiveResult)) {
                System.out.println("✓ All three methods produced the same result!");
            } else {
                System.out.println("✗ Methods produced different results - check implementation!");
            }
        }
    }
    
    /**
     * Method 1: Reverse string using iterative approach
     * Logic: Loop through the input string from end to start and build the result
     * 
     * @param str The input string to reverse
     * @return The reversed string
     */
    public static String reverseIterative(String str) {
        // Initialize an empty result string
        String result = "";
        
        // Loop through the input string from end to start
        for (int i = str.length() - 1; i >= 0; i--) {
            // Append each character to the result string
            result += str.charAt(i);
        }
        
        // Return the reversed result
        return result;
    }
    
    /**
     * Method 2: Reverse string using StringBuffer's built-in reverse method
     * Logic: Create StringBuffer object and use its reverse() method
     * Note: Using StringBuffer as required by assignment (StringBuilder would be more efficient)
     * 
     * @param str The input string to reverse
     * @return The reversed string
     */
    public static String reverseStringBuffer(String str) {
        // Create a StringBuffer object with the input string
        @SuppressWarnings("StringBufferMayBeStringBuilder")
        StringBuffer stringBuffer = new StringBuffer(str);
        
        // Call the reverse() method on the StringBuffer
        stringBuffer.reverse();
        
        // Return the result as a string
        return stringBuffer.toString();
    }
    
    /**
     * Method 3: Reverse string using recursive approach
     * Logic: Base case - if string is empty or length 1, return it
     *        Recursive case - return reverse(substring(1)) + first character
     * 
     * @param str The input string to reverse
     * @return The reversed string
     */
    public static String reverseRecursive(String str) {
        // Base case: if the string is empty or has length 1, return it as is
        if (str == null || str.length() <= 1) {
            return str;
        }
        
        // Recursive case: return reverse of substring(1) + first character
        // This breaks down the string and builds it back in reverse order
        return reverseRecursive(str.substring(1)) + str.charAt(0);
    }
}
