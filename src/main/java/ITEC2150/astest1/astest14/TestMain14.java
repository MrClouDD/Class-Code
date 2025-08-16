package ITEC2150.astest1.astest14;
// modify code below so that the program runs as shown in the example
// hint: you may want to use System.out.printf method with "%-20s" format
//       to align the output as shown in the example output
//       also, consider to use while loop with the hasNext() method in Scanner
//       to check there are any remaining lines in the input casts.txt file.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestMain14 {


    public static void main(String[] args) {
        File castfile;
        File rolefile;
        Scanner cs = null;
        Scanner rs = null;

        final String startingPath = "src/Week4/astest14/";

        try {
            castfile = new File(startingPath + "casts.txt");
            rolefile = new File(startingPath + "roles.txt");
            cs = new Scanner(castfile);
            rs = new Scanner(rolefile);
        } catch (FileNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        }

        if (cs == null || rs == null){
            throw new NullPointerException("Scanners cannot be null");
        } else {
            System.out.printf("%-20s %-10s\n", "Cast", "Roles");
            while (cs.hasNextLine()){
                System.out.printf("%-20s %-10s\n", cs.nextLine(), rs.nextLine());
            }
        }

        System.out.println("\nCreated by: Ajitesh Sandhu");
        System.out.println("Created on: 6/15/2025");
    }
}
