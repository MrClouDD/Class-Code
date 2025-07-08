package CA.filewriting.printwriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Simple File and PrintWriter.
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-08
 */

public class TestMain{
    public static void main(String[] args) throws IOException {
        // Get the current working directory
        String fileName = "testfile4printwriter.txt";
        String filePath = System.getProperty("user.dir");
        File newFile = new File(filePath, fileName);

        // Ensure the directory exists and has write permissions
        File directory = newFile.getParentFile();
        if (!directory.exists() && !directory.mkdirs()) {
            System.err.println("Failed to create directory: " + directory.getAbsolutePath());
            return;
        }

        // Create printwriter with error handling and creation of files
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(newFile);
            // Write to file
            printWriter.write("Hello Ajitesh Sandhu!\n");
            printWriter.write("This is ITEC 2150-01 class on T,Tr 2:00-5:30pm.\n");
            printWriter.write("It works!\n");
        } catch (FileNotFoundException fne) {
            System.err.println("File not found: " + newFile.getName() + "\nAttempting to create");
            try {
                if (newFile.createNewFile()) {
                    printWriter = new PrintWriter(newFile);
                    printWriter.println("File created successfully.");

                    // Write to file
                    printWriter.write("Hello Ajitesh Sandhu!\n");
                    printWriter.write("This is ITEC 2150-01 class on T,Tr 2:00-5:30pm.\n");
                    printWriter.write("It works!\n");
                } else {
                    System.err.println("Failed to create the file.");
                }
            } catch (IOException ioe) {
                System.err.println("Error while creating the file: " + ioe.getMessage());
            }
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }

        // File path for the file created in the previous assignment
        filePath = System.getProperty("user.dir") + File.separator + fileName;

        // Use FileReader wrapped with BufferedReader to read the file
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            // Read the file line by line
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line); // Output the content of the file
            }

        } catch (FileNotFoundException fne) {
            // Handle case where the file is not found
            System.err.println("File not found: " + fileName);
        } catch (IOException ioe) {
            // Handle case where there is an error reading the file
            System.err.println("Error reading the file: " + ioe.getMessage());
        }
    }
}
