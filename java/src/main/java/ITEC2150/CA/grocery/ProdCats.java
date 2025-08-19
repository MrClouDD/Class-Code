package ITEC2150.CA.grocery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ProdCats class to generate product categories and write them to a file.
 * 
 * @author Ajitesh Sandhu
 * @date 2025-07-08
 */
public class ProdCats {
    public static void main(String[] args) {
        // Define the file name and path
        String fileName = "prodcats.txt";
        String filePath = System.getProperty("user.dir");
        File newFile = new File(filePath, fileName);
        
        // Array of product category names
        String[] categoryNames = {"Bakery", "Deli", "Grocery", "Meat", "Produce", "Seafood"};
        
        // Ensure the directory exists and has write permissions
        File directory = newFile.getParentFile();
        if (!directory.exists() && !directory.mkdirs()) {
            System.err.println("Failed to create directory: " + directory.getAbsolutePath());
            return;
        }
        
        // Create PrintWriter with error handling using try-with-resources
        try (PrintWriter printWriter = new PrintWriter(newFile)) {
            
            // Write each category to the file with its ID
            for (int i = 0; i < categoryNames.length; i++) {
                printWriter.println((i + 1) + " " + categoryNames[i]);
            }
            
            System.out.println("Product categories have been written to " + fileName);
            
        } catch (FileNotFoundException fne) {
            System.err.println("File not found: " + newFile.getName() + "\nAttempting to create");
            try {
                if (newFile.createNewFile()) {
                    try (PrintWriter printWriter = new PrintWriter(newFile)) {
                        
                        // Write each category to the file with its ID
                        for (int i = 0; i < categoryNames.length; i++) {
                            printWriter.println((i + 1) + " " + categoryNames[i]);
                        }
                        
                        System.out.println("File created and product categories written successfully.");
                    }
                } else {
                    System.err.println("Failed to create the file.");
                }
            } catch (IOException ioe) {
                System.err.println("Error while creating the file: " + ioe.getMessage());
            }
        }
    }
}
