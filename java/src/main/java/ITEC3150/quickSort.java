package ITEC3150;

/**
 * @author Ajitesh Sandhu
 * @date 8/30/25
 */
class quickSort {

    // partition function
    static int partition(int[] arr, int low, int high) {

        // choose the pivot
        int pivot = arr[high];

        // left marker
        int i = low - 1;

        // traverse arr[low...high] and move all smaller
        // elements to the left side.
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        // Move pivot after smaller elements and return its position
        swap(arr, i + 1, high);
        return i + 1;
    }

    // swap function
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // the QuickSort function implementation
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {

            // partition return index
            int pi = partition(arr, low, high);

            // recursion calls for smaller elements
            // and greater or equals elements
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 12, 32, 13, 25, 33, 21, 10, 7, 8, 9, 1, 5 };
        int n = arr.length;

        quickSort(arr, 0, n - 1);

        System.out.println("Sorted array:");

        for (int val : arr) {
            System.out.print(val + " ");
        }

        System.out.println();
    }
}