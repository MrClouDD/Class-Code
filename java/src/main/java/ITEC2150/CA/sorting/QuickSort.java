package ITEC2150.CA.sorting;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Implementation of the Quick Sort algorithm.
 * Quick sort is an efficient divide-and-conquer algorithm that chooses a pivot,
 * partitions the array into items smaller and larger than the pivot,
 * and recursively sorts the sub arrays.
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-13
 */
public class QuickSort {
    
    /**
     * Main quick sort method that sorts an array using quick sort algorithm
     * @param arr the array to be sorted
     * @param lo the starting index of the array portion to sort
     * @param hi the ending index of the array portion to sort
     */
    public static void quickSort(int[] arr, int lo, int hi) {
        // Base case: if lo >= hi or lo < 0, return (array is sorted or invalid)
        if (lo >= hi || lo < 0) {
            return;
        }
        
        // Partition the array and get the pivot index
        // All elements before pivot are smaller, all after are larger
        int p = partition(arr, lo, hi);
        
        // Recursively sort the left subarray (elements smaller than pivot)
        quickSort(arr, lo, p - 1);
        
        // Recursively sort the right subarray (elements larger than pivot)
        quickSort(arr, p + 1, hi);
    }
    
    /**
     * Partition method that rearranges the array so that:
     * - Elements smaller than or equal to pivot are on the left
     * - Elements greater than pivot are on the right
     * - Returns the final position of the pivot
     * 
     * @param arr the array to partition
     * @param lo the starting index
     * @param hi the ending index
     * @return the final index of the pivot element
     */
    private static int partition(int[] arr, int lo, int hi) {
        // Choose the last element as pivot
        int pv = arr[hi];
        
        // Index of smaller element, indicates right position of pivot
        int i = lo - 1;
        
        // Traverse through all elements
        // Compare each element with pivot
        for (int j = lo; j <= hi - 1; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pv) {
                i++; // Increment index of smaller element
                swap(arr, i, j); // Swap elements
            }
        }
        
        // Place pivot in its correct position
        i++;
        swap(arr, i, hi);
        
        // Return the position of the pivot
        return i;
    }
    
    /**
     * Swap two elements in the array
     * @param arr the array
     * @param i first index
     * @param j second index
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
     * Main method to demonstrate quick sort functionality
     */
    public static void main(String[] args) {
        // Print full name at the beginning of the output (before any other output)
        System.out.println("Ajitesh Sandhu");
        System.out.println("Quick Sort Implementation");
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
            
            // Display original array (before sorting)
            printArray(arr, "Original array: ");
            
            // Perform quick sort
            quickSort(arr, 0, arr.length - 1);
            
            // Display sorted array (after sorting)
            printArray(arr, "Sorted array: ");
            
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter valid integers.");
        }
    }
}
