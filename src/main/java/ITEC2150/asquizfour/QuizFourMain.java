package ITEC2150.asquizfour;

import java.io.FilterOutputStream;
import java.util.Arrays;

/**
 * @author Ajitesh Sandhu
 * Created on: 6/24/2025
 */
class QuizFourMain {
    /**
     * The entry point of application.
     */
    public static void main(String[] args) {
        String[] strArray = {"one", "two", "three"};
        printReverseOrder(strArray);
    }

    /**
     * Print reverse order
     *
     * @param ary of type String[]
     */
    public static void printReverseOrder(String[] ary) {
        // complete here to print the array element in reverse order
        // *** This printReverseOrder() MUST use recursion to print in reverse order ***
        // hint use Arrays.copyOfRange() to make a smaller array

        if (ary.length == 1) {
            System.out.print(ary[0]);
        } else {
            System.out.print(ary[ary.length - 1] + " ");
            printReverseOrder(Arrays.copyOfRange(ary, 0, ary.length - 1));
        }
    }
}
