package ITEC3150;

import java.util.Arrays;
import java.util.Random;

/**
 * Program to generate and sort 100,000 numbers and print CPU time
 * @author Ajitesh Sandhu
 * @created 8/14/2025
 */
public class HW0814 {
    static int SIZE = 100000;
    
    public static void main(String[] args) {
        Random rand = new Random();

        // Create array for random numbers
        int[] randomNumbers = new int[SIZE];

        // Populate with random numbers from 1-100,000
        for (int i = 0; i < SIZE; i++){
            randomNumbers[i] = rand.nextInt(SIZE) + 1;
        }

        // Get starting time
        long s = System.currentTimeMillis();

        // Execute a simple sort O(n^2)
        simpleSort(randomNumbers);
        
        // Print time taken to sort
        long simpleSortTime = System.currentTimeMillis() - s;
        System.out.printf("\nSimple sort started at %d took %d milliseconds to complete", s, simpleSortTime);

        // Repopulate array
        for (int i = 0; i < SIZE; i++){
            randomNumbers[i] = rand.nextInt(SIZE) + 1;
        }

        // Set starting time
        s = System.currentTimeMillis();

        // Use Arrays.sort O(nlog(n))
        Arrays.sort(randomNumbers);

        // Print Arrays.sort sorting time
        long arraysSortTime = System.currentTimeMillis() - s;
        System.out.printf("\nJava's Arrays.sort started at %d took %d milliseconds to complete", s, arraysSortTime);

        // Blank line at the end
        System.out.println();
    }

    public static void simpleSort(int[] unsortedArray){
     
        for (int i = 0; i < unsortedArray.length; i++) {
            // Start at i
            int minIndex = i;

            // Loop through the array
            for (int j = i + 1; j < unsortedArray.length; j++) {
                // Check if the j is smaller than the current minIndex
                if (unsortedArray[j] < unsortedArray[minIndex]) {
                    // Then change minIndex to j
                    minIndex = j;
                }
            }

            // Swap j and minIndex values
            int temp = unsortedArray[minIndex];
            unsortedArray[minIndex] = unsortedArray[i];
            unsortedArray[i] = temp;

        }

    }

}
