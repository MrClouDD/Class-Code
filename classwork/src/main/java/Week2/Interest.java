/*
Name: Ajitesh Sandhu
Date: 5/27/2025
Description: Get user input and calculate interest*/

package Week2;

import java.util.Scanner;

public class Interest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double[] vars = getUserInput(input);
        double interest = calculateTotalInterest(vars[0], (int) vars[1], vars[2]);

        System.out.printf("Total interest after %d years = $%.2f\n", (int) vars[1], interest);
        System.out.println("Ajitesh Sandhu");
    }

    public static double[] getUserInput(Scanner scanner){
        System.out.print("Enter your APR as a percentage (e.g., 5.5): ");
        double APR = scanner.nextDouble();
        System.out.print("Enter number of years: ");
        double year = scanner.nextDouble();
        System.out.print("Enter principle amount: ");
        double principle = scanner.nextDouble();
        return new double[] {APR, year, principle};
    }

    public static double calculateTotalInterest(double apr, int years, double amount){
        return (apr * years * amount) / 100.0;
    }
}
