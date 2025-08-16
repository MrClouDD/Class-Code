package ITEC3150;

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

        long s = System.currentTimeMillis();

        randomNumbers = divisionSort(randomNumbers);
    }

    public static int[] swapSort(int[] unsortedArray){
        int[] sortedArray = new int[SIZE];
        int minValue = unsortedArray[0];

        for (int i = 0; i < SIZE; i++) {
                for (int j = i; j < SIZE; j++) {
                    minValue = Math.min(minValue, unsortedArray[j]);
                }
            
            sortedArray[i] = minValue;
        }

        return sortedArray;
    }

    public static int[] divisionSort(int[] array){
        int[] sortedArray = new int[array.length];

        

        return sortedArray;
    }
}
