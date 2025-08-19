package ITEC2150.CA.gradebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Grading {
    
    static Map<Faculty, List<Student>> facultyMap = new HashMap<>();

    public static void main(String[] args) {
        try (Scanner keyboard = new Scanner(System.in)) {

            System.out.print("Enter faculty first name: ");
            String fname = keyboard.nextLine();
            
            System.out.print("Enter faculty last name: ");
            String lname = keyboard.nextLine();

            Faculty f = new Faculty(fname, lname);

            System.out.print("How many students are in the class: ");
            
            int count;
            try {
                count = keyboard.nextInt();
            } catch (InputMismatchException e){
                    System.out.println("Created by Ajitesh Sandhu");
                    throw new InputMismatchException("Number should be an int" + e.getMessage());
                }
            keyboard.nextLine();



            // Create and fill student list
            List<Student> students = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                System.out.print("Enter name: ");
                String name = keyboard.nextLine();
                
                System.out.print("Enter score: ");
                int score;

                score = keyboard.nextInt();
                
                keyboard.nextLine();
                
                System.out.print("Enter address: ");
                String address = keyboard.nextLine();
                
                students.add(new Student(name, score, address));
            }

            // Store faculty and their students into a Hashmap for quick searching
            facultyMap.put(f, students);

            // Prints the student with the highest score
            Student highestStudent = returnHighest(facultyMap, f);

            System.out.printf("Faculty: %s", f.getName());
            System.out.printf("%s has the highest score of %d", highestStudent.getName(), highestStudent.getScore());
            System.out.printf("Street Address: %s" + highestStudent.getStreetAddress());
        } // try-with-resources auto-closes Scanner
    }

    /**
     * Finds the student with the highest score for the given faculty.
     */
    static Student returnHighest(Map<Faculty, List<Student>> map, Faculty f){
        List<Student> students = map.get(f);
        Student highestStudent = null;
        int highestScore = Integer.MIN_VALUE;

        for(Student s: students){
            if (s.getScore() > highestScore){
                highestStudent = s;
                highestScore = s.getScore();
            }
        }
        return highestStudent;
    }


    /**
     * Predefined scenario main for Jane Smith example with 3 students.
     *
    public static void main(String[] args) {
        // Setup faculty and students list
        Faculty faculty = new Faculty("Jane", "Smith");
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice Johnson", 85, "123 Elm St"));
        students.add(new Student("Bob Brown", 92, "456 Oak St"));
        students.add(new Student("Charlie Davis", 78, "789 Pine St"));

        // Clear any existing entries and add example data
        facultyMap.clear();
        facultyMap.put(faculty, students);

        // Determine highest scoring student
        Student highestStudent = returnHighest(facultyMap, faculty);

        // Print example output
        System.out.println("Faculty name: " + faculty.getName());
        System.out.println(students.size() + " students");
        for (Student s : students) {
            System.out.printf("%s, %d, %s%n", s.getName(), s.getScore(), s.getStreetAddress());
        }
        System.out.println("Faculty: " + faculty.getName());
        System.out.println(highestStudent.getName() + " has the highest score of " + highestStudent.getScore());
        System.out.println("Street Address: " + highestStudent.getStreetAddress());
    }
        */
}
