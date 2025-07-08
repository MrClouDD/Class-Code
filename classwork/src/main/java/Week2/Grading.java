/*
Name: Ajitesh Sandhu
Date: 5/27/2025
Description: */

package Week2;

import java.util.Scanner;

public class Grading {
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

    public double calculateGrade(double[] grades){
        double average = 0;

        for (double g: grades){
            average += g;
        }
        average /= grades.length;
        return average;
    }

    public double highestGrade(double[] grades){
        double temp = grades[0];

        for (int i = 0; i < grades.length; i++) {
            if (grades[i] > temp){
                temp = grades[i];
            }
        }

        return temp;
    }

    public double lowestGrade(double[] grades){
        double temp = grades[0];

        for (int i = 0; i < grades.length; i++) {
            if (grades[i] < temp){
                temp = grades[i];
            }
        }

        return temp;
    }

    public void calculateAverage(double[] grades) {
        System.out.println("Average grade: " + calculateGrade(grades));
    }
}
