/*
Name: Ajitesh Sandhu
Date: 5/27/2025
Description: Gets user input and calculates the average grade*/

package Week2;

import java.util.Scanner;

public class Average {
    public double[] inputGrades(){
        Scanner input = new Scanner(System.in);

        System.out.print("How many grades do you want to enter? ");
        int count = input.nextInt();
        System.out.print("Enter the grades: ");
        double[] grades = new double[count];

        for (int i = 0; i < count; i++) {
            grades[i] = input.nextDouble();
        }

        return grades;
    }

    public double calculateGrade(){
        double[] grades = inputGrades();
        double average = 0;

        for (double g: grades){
            average += g;
        }
        average /= grades.length;
        return average;
    }

    public void calculateAverage() {
        System.out.println("Average grade: " + calculateGrade());
    }
}

