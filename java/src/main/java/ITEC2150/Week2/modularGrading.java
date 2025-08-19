package ITEC2150.Week2;

import java.util.Scanner;

/**
 * Grading using modular programming
 *
 * @author Ajitesh Sandhu created this on 2025-05-29
 */
public class modularGrading {
    /**
     * The entry point of application.
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("How many students' grades do you need?");
        int numStudents = input.nextInt();

        String[] names = new String[numStudents];
        double[] scores = new double[numStudents];
        String[] streetAddresses = new String[numStudents];

        for (int i = 0; i < numStudents; i++) {
            System.out.printf("Enter the student %d's name: ", i);
            names[i] = input.next();
            System.out.printf("Enter the student %d's score: ", i);
            scores[i] = input.nextDouble();
            streetAddresses[i] = "";
            System.out.println();
        }

        double temp = scores[0];
        int highestIndex = 0;

        for (int i = 0; i < numStudents; i++) {
            if (scores[i] > temp) {
                temp = scores[i];
                highestIndex = i;
            }
        }

        System.out.printf("The highest scoring student is %s with a score of %.0f\n", names[highestIndex], scores[highestIndex]);
        System.out.printf("Their address is: %s", streetAddresses[highestIndex]);

    }
}
