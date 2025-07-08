/*
Name: Ajitesh Sandhu
Date: 5/27/2025
Description: Get user input and add all integers between a start and end*/

package Week1;

import java.util.Scanner;

public class Loop {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the first number:");
        int start = getUserInput(input);
        System.out.println("Enter the second number:");
        int end = getUserInput(input);

        System.out.printf("Sum of all integers from %d and %d is %d\n", start, end, sumTwoIntegers(start,end));
        System.out.println("Ajitesh Sandhu");

    }
    public static int getUserInput(Scanner s){
        return s.nextInt();
    }

    public static int sumTwoIntegers(int start, int end){
        int sum = 0;
        if (start > end){
            int temp = start;
            start = end;
            end = temp;
        }
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
}
