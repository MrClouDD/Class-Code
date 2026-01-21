package ITEC3150.Exam;

import java.util.Arrays;
import java.util.Random;

public class QuickSortRandomPivot {

    private static Random random = new Random();

    private static int partition(int[] list, int first, int last) {
        // Choose pivot randomly
        int randomIndex = first + random.nextInt(last - first + 1);

        // Swap random with the last element
        int temp = list[randomIndex];
        list[randomIndex] = list[last];
        list[last] = temp;

        // Partition
        int pivot = list[last];
        int i = first - 1;

        for (int j = first; j < last; j++) {
            if (list[j] <= pivot) {
                i++;
                temp = list[i];
                list[i] = list[j];
                list[j] = temp;
            }
        }

        // Move pivot
        temp = list[i + 1];
        list[i + 1] = list[last];
        list[last] = temp;

        return i + 1;
    }

    private static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    public static void main(String[] args) {
        // Test with randomly generated array of 20
        int size = 20;
        int[] testArray = new int[size];

        for (int i = 0; i < size; i++) {
            testArray[i] = random.nextInt(101);
        }

        System.out.println("Original array:");
        System.out.println(Arrays.toString(testArray));

        quickSort(testArray);

        System.out.println("Sorted array:");
        System.out.println(Arrays.toString(testArray));
    }
}
