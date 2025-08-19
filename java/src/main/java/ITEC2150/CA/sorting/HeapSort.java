package ITEC2150.CA.sorting;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Implementation of the Heap Sort algorithm.
 * Heap sort is a comparison-based sorting algorithm that uses a binary heap data structure.
 * It builds a max heap from the input data, then repeatedly extracts the maximum element
 * and places it at the end of the array.
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-13
 */
public class HeapSort {
    
    private static int heapSize; // Current size of the heap
    
    /**
     * Main heap sort method that sorts an array using heap sort algorithm
     * @param arr the array to be sorted
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;
        heapSize = n;
        
        // Build initial max heap
        // Start from the last non-leaf node and heapify each node
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, i);
        }
        
        // Extract elements from heap one by one
        for (int i = n - 1; i >= 1; i--) {
            // Move current root (maximum) to the end
            swap(arr, 0, i);
            
            // Reduce heap size
            heapSize--;
            
            // Heapify the root element to maintain max heap property
            heapify(arr, 0);
        }
    }
    
    /**
     * Heapify method to maintain max heap property
     * This method assumes that the binary trees rooted at left and right children
     * are max heaps, but arr[i] might be smaller than its children
     * @param arr the array representing the heap
     * @param i the index of the node to heapify
     */
    public static void heapify(int[] arr, int i) {
        int left = getLeftChild(i);     // Left child index
        int right = getRightChild(i);   // Right child index
        int largest = i;                // Initialize largest as root
        
        // Check if left child exists and is greater than root
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        
        // Check if right child exists and is greater than current largest
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        
        // If largest is not root, swap and recursively heapify
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest); // Recursively heapify the affected sub-tree
        }
    }
    
    /**
     * Get the index of the left child
     * @param i parent index
     * @return left child index
     */
    private static int getLeftChild(int i) {
        return 2 * i + 1;
    }
    
    /**
     * Get the index of the right child
     * @param i parent index
     * @return right child index
     */
    private static int getRightChild(int i) {
        return 2 * i + 2;
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
     * Main method to demonstrate heap sort functionality
     */
    public static void main(String[] args) {
        // Print full name at the beginning of the output (before any other output)
        System.out.println("Ajitesh Sandhu");
        System.out.println("Heap Sort Implementation");
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
            
            // Perform heap sort
            heapSort(arr);
            
            // Display sorted array
            printArray(arr, "Sorted array: ");
            
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter valid integers.");
        }
    }
}
