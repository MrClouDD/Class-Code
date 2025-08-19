package ITEC2150.Week2;

import java.util.Scanner;

/**
 * Sum between a custom start and end
 *
 * @author Ajitesh Sandhu created this on 2025-05-29
 */
public class Sum2 {
    /**
     * The entry point of application.
     */
    public static void main(String[] args) {
        int start = getNumberWithMessage("Enter a start number: ");
        int end = getNumberWithMessage("Enter an end number: ");
        int sum = getTotal(start, end);
        System.out.printf("The sum of all numbers from %d to %d is %d!\n", start, end, sum);
        System.out.println("Ajitesh Sandhu");
    }

    /**
     * Get number with message
     *
     * @param m of type String
     * @return int
     */
    static int getNumberWithMessage(String m){
        Scanner input = new Scanner(System.in);
        System.out.print(m);
        return input.nextInt();
    }

    /**
     * Get total
     *
     * @param s of type int
     * @param e of type int
     * @return int
     */
    static int getTotal(int s, int e){
        int sum = 0;

        if (s > e){
            int temp = e;
            s = e;
            e = temp;
        }
        for (int i = s; i < e; i++) {
            sum += i;
        }
        return sum;
    }
}
