package ITEC2150.Week1;

import java.util.Scanner;

/**
 * Get and print a user entered number
 *
 * @author Ajitesh Sandhu created this on 2025-05-29
 */
public class Num {
    /**
     * The entry point of application.
     */
    public static void main(String[] args) {
        int no = getNumber();
        System.out.printf("The entered number is %d.\n", no);
        System.out.println("Ajitesh Sandhu");
    }

    /**
     * Get number
     *
     * @return int
     */
    static int getNumber(){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number: ");
        return input.nextInt();
    }

}
