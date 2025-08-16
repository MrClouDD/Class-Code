package ITEC2150.Week3;

import java.util.Scanner;

/**
 * Grading
 *
 * @author Ajitesh Sandhu created on 06.23.2025
 */
public class Grading {
    /**
     * The entry point of application.
     */
    public static void main(String[] args) {
        Student[] stu;
        int no_of_students;

        Scanner kbd = new Scanner(System.in);

        displayBanner();

        no_of_students = getNumberOfStudent(kbd);

        stu = new Student[no_of_students];

        getStudentInfo(kbd, stu);

        int highest = findHighestStudent(stu);

        displayHighestStudent(stu, highest);
    }

    /**
     * Display banner
     */
    static void displayBanner(){
        System.out.println("This program shows the student with the highest score");
    }

    /**
     * Get number of student
     *
     * @param kbd of type Scanner
     * @return int
     */
    static int getNumberOfStudent(Scanner kbd){
        System.out.println("Enter the number of students in the class: ");
        return kbd.nextInt();
    }

    /**
     * Get student info
     *
     * @param kbd of type Scanner
     * @param stu of type Student[]
     */
    static void getStudentInfo(Scanner kbd, Student[] stu){
        kbd.nextLine(); // Consume the leftover newline character
        for (int i = 0; i < stu.length; i++) {
            System.out.printf("Enter student #%d's first name: ", i + 1);
            String fName = kbd.nextLine();
            System.out.printf("Enter %s's last name: ", fName);
            String lName = kbd.nextLine();
            System.out.printf("Enter %s's ID number (ID must start with 900): ", fName);
            int id = kbd.nextInt();
            kbd.nextLine(); // Consume the leftover newline character
            System.out.printf("Enter %s's score: ", fName);
            double score = kbd.nextDouble();
            kbd.nextLine(); // Consume the leftover newline character
            System.out.printf("Enter %s's street address in the format (Number Street Name City ST Zipcode, Apt/Suite Number): ", fName);
            String streetAddress = kbd.nextLine();
            stu[i] = new Student(id, fName, lName, score, streetAddress);
        }
    }

    /**
     * Find highest student
     *
     * @param stu of type Student[]
     * @return int
     */
    static int findHighestStudent(Student[] stu){
        int index = 0;
        double temp = 0.0;

        for (int i = 0; i < stu.length; i++) {
            if (stu[i].getScore() > temp){
                index = i;
                temp = stu[i].getScore();
            }
        }
        return index;
    }

    /**
     * Display highest student
     *
     * @param stu   of type Student[]
     * @param index of type int
     */
    static void displayHighestStudent(Student[] stu, int index){
        System.out.println("The student with the highest score is:");
        System.out.println(stu[index]);
    }
}
