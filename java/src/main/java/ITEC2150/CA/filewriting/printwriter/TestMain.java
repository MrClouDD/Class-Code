package ITEC2150.CA.filewriting.printwriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;

/**
 * Demonstrates proper exception handling with File and PrintWriter.
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-08
 */
public class TestMain {
    public static void main(String[] args) {
        System.out.println("=== FILE WRITING DEMONSTRATION ===");
        
        String fileName = "testfile4printwriter.txt";
        File newFile = new File(System.getProperty("user.dir"), fileName);

        // Test 1: File Creation and Writing
        System.out.println("\n--- TEST 1: Normal File Operations ---");
        testFileOperations(newFile);

        // Test 2: Read-Only File Test
        System.out.println("\n--- TEST 2: Read-Only File Handling ---");
        testReadOnlyFile(newFile);

        // Test 3: Invalid Path Test
        System.out.println("\n--- TEST 3: Invalid Path Handling ---");
        testInvalidPath();

        // Print signature
        System.out.println("Created by Ajitesh Sandhu");
    }

    private static void testFileOperations(File file) {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            System.out.println("Attempting to write to file...");
            printWriter.write("Hello Ajitesh Sandhu!\n");
            printWriter.write("This is ITEC 2150-01 class on T,Tr 2:00-5:30pm.\n");
            printWriter.write("Demonstrating proper exception handling.\n");
            System.out.println("Successfully wrote to file: " + file.getAbsolutePath());
            
            // Verify file creation
            if (file.exists()) {
                System.out.println("File verification: File exists with size: " + file.length() + " bytes");
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found - " + e.getMessage());
            attemptFileRecovery(file);
        } catch (SecurityException e) {
            System.err.println("SECURITY ERROR: Permission denied - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("UNEXPECTED ERROR: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        // Demonstrate successful read
        System.out.println("\nReading file contents:");
        readFileContents(file);
    }

    private static void attemptFileRecovery(File file) {
        System.out.println("Attempting file recovery...");
        try {
            if (file.createNewFile()) {
                System.out.println("Successfully created new file");
                testFileOperations(file); // Retry
            } else {
                System.out.println("Failed to create new file");
            }
        } catch (IOException e) {
            System.err.println("RECOVERY FAILED: " + e.getMessage());
        } catch (SecurityException e) {
            System.err.println("SECURITY ERROR DURING RECOVERY: " + e.getMessage());
        }
    }

    private static void testReadOnlyFile(File file) {
        // Make file read-only for this test
        if (file.exists() && file.setReadOnly()) {
            System.out.println("Set file to read-only for testing purposes");
            
            try (PrintWriter printWriter = new PrintWriter(new FileWriter(file))) {
                System.out.println("Attempting to write to read-only file...");
                printWriter.write("This line should not be written to the file.\n");
            } catch (AccessDeniedException ade) {
                System.err.println("ACCESS DENIED: Cannot write to read-only file - " + ade.getMessage());
            } catch (IOException e) {
                System.err.println("IO ERROR: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("UNEXPECTED ERROR: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
        } else {
            System.err.println("ERROR: Unable to set file as read-only. File may not exist.");
        }

        // Reset file permissions to allow writing again
        resetFilePermissions(file);
    }

    private static void resetFilePermissions(File file) {
        if (file.exists()) {
            // Remove read-only attribute
            if (!file.setWritable(true)) {
                System.err.println("WARNING: Unable to reset file permissions. Manual intervention may be required.");
            } else {
                System.out.println("File permissions reset. File is writable again.");
            }
        }
    }

    private static void testInvalidPath() {
        String invalidPath = "invalid_directory/testfile.txt";
        File file = new File(invalidPath);

        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.write("This should fail due to invalid path.\n");
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Invalid file path - " + e.getMessage());
        } catch (SecurityException e) {
            System.err.println("SECURITY ERROR: Permission denied - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("UNEXPECTED ERROR: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    private static void readFileContents(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("ERROR: File not found - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("UNEXPECTED ERROR: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
