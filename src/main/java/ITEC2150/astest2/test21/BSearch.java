package ITEC2150.astest2.test21;
// You may add your package information here

/**
 * Binary search implementation using recursion
 * @author Ajitesh Sandhu
 */
// Put your code in the search() method below
class BSearch {
    int[] data;
    BSearch(int[] d) {
        data = d;
    }

    public int search(int no) {
        return search(no, 0, data.length - 1);
    } // end search(int no)

    public int search(int no, int start, int end) {
        int center = (start + end) / 2;
        if (end < start) {
            return -1;
        }
        // put your code *** BELOW *** to complete
        // this binary search using RECURSION
        // *** hint ***
        // if no is the same as the data in the center, return the center
        // if no is less than the data in the center,
        //     return the result of the search() method with smaller range (from the start to before the center)
        // if no is greater than the data in the center,
        //     return the result of the search() method with smaller range (from the right after the center to the end)


        // Check if the target number equals the center element
        if (data[center] == no) {
            return center; // Found the target, return its index
        } 
        // If target is smaller than center element, search left half
        else if (no < data[center]) {
            return search(no, start, center - 1);
        } 
        // If target is larger than center element, search right half
        else {
            return search(no, center + 1, end);
        }

    } // end search(int no, int start, int end)
} // end class BSearch
