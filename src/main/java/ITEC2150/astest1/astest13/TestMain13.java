package ITEC2150.astest1.astest13;
// modify code below so that the program runs as shown in the example
// hint: you do not need to change the existing lines, 
//       but adding an exception handling codes for non-integer input. 

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestMain13 {
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        int no = 7;
        int guess_no = 0;

        do {
            System.out.print("Enter a number: ");
            try {
                guess_no = kbd.nextInt();
                if (no > guess_no) {
                    System.out.printf("The number is higher than %d.\n", guess_no);
                } else {
                    System.out.printf("The number is lower than %d.\n", guess_no);
                }
            } catch (InputMismatchException e){
                System.out.println("Please enter an integer number.");
                kbd.nextLine();
            }
        }
        while (no != guess_no);

        System.out.println("That is the number.");
        kbd.close();

        System.out.println("\nCreated by: Ajitesh Sandhu");
        System.out.println("Created on: 6/15/2025");
    }
    
}
