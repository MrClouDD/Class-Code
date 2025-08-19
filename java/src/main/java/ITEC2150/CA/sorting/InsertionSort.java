package ITEC2150.CA.sorting;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Implementation of the Insertion Sort algorithm.
 * Insertion sort builds the final sorted array one item at a time.
 * Each new element is compared with those already sorted and inserted into its correct position.
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-13
 */
public class InsertionSort {
    
    /**
     * Sorts an array using the insertion sort algorithm
     * @param arr the array to be sorted
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        // Start from the second element (index 1) since first element is considered sorted
        for (int i = 1; i < n; i++) {
            // Current element to be inserted into the sorted portion
            int key = arr[i];
            int j = i - 1;
            
            // Move elements of arr[0..i-1] that are greater than key
            // one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];  // Shift element to the right
                j--;  // Move to the previous element
            }
            
            // Insert the key at its correct position
            arr[j + 1] = key;
        }
    }
    
    /**
     * Prints the array elements
     * @param arr the array to be printed
     * @param message descriptive message for the array
     */
    public static void printArray(int[] arr, String message) {
        System.out.println(message + Arrays.toString(arr));
    }
    
    /**
     * Main method to demonstrate insertion sort functionality
     */
    public static void main(String[] args) {
        // Print full name at the beginning of the output (before any other output)
        System.out.println("Ajitesh Sandhu");
        System.out.println("Insertion Sort Implementation");
        System.out.println("=====================================");
        
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the number of elements: ");
            int n = scanner.nextInt();
            
            // Validate input
            if (n <= 0) {
                System.out.println("Please enter a positive number of elements.");
                return;
            }
            
            int[] arr = new int[n];
            
            System.out.println("Enter " + n + " integers:");
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            
            // Display original array
            printArray(arr, "Original array: ");
            
            // Perform insertion sort
            insertionSort(arr);
            
            // Display sorted array
            printArray(arr, "Sorted array: ");
            
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter valid integers.");
        }
    }
}
