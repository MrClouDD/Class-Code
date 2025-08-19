package ITEC2150.CA.sorting;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Implementation of the Selection Sort algorithm.
 * Selection sort divides the input array into two parts: a sorted subarray (left side)
 * and an unsorted subarray (right side). It repeatedly finds the minimum element
 * from the unsorted subarray and swaps it with the leftmost unsorted element.
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-13
 */
public class SelectionSort {
    
    /**
     * Sorts an array using the selection sort algorithm
     * @param arr the array to be sorted
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in remaining unsorted array
            int minIndex = i;
            
            // Search for the minimum element in the unsorted portion
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;  // Update minimum index
                }
            }
            
            // Swap the found minimum element with the first element of unsorted subarray
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
        }
    }
    
    /**
     * Helper method to print an array
     * @param arr the array to be printed
     * @param message descriptive message for the array
     */
    public static void printArray(int[] arr, String message) {
        System.out.println(message + Arrays.toString(arr));
    }
    
    /**
     * Main method to demonstrate selection sort functionality
     */
    public static void main(String[] args) {
        // Print full name at the beginning of the output (before any other output)
        System.out.println("Ajitesh Sandhu");
        System.out.println("Selection Sort Implementation");
        System.out.println("================================");
        
        // Use try-with-resources to properly manage Scanner
        try (Scanner scanner = new Scanner(System.in)) {
            // Get array input from user
            System.out.print("\nEnter the number of elements: ");
            int n = scanner.nextInt();
            
            int[] arr = new int[n];
            System.out.print("Enter " + n + " integers: ");
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            
            // Display original array
            printArray(arr, "Original array: ");
            
            // Perform selection sort
            selectionSort(arr);
            
            // Display sorted array
            printArray(arr, "Sorted array: ");
        }
    }
}
