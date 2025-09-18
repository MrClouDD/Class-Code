package ITEC3150;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * File Decompression using Huffman Coding
 * This program reads a compressed file and decompresses it
 */
public class Decompress {

    public static void main(String[] args) {
        try {
            // Decompress the file
            String decompressedText = decompressFile("doi_compressed.bin");

            // Save the decompressed text
            saveToFile(decompressedText, "doi_decompressed.txt");

            System.out.println("Decompression complete!");
            System.out.println("Decompressed text saved as: doi_decompressed.txt");
            System.out.println("Decompressed size: " + decompressedText.length() + " characters");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Decompress a file created by the Compress program
    public static String decompressFile(String filename) throws IOException {
        FileInputStream input = new FileInputStream(filename);

        // Step 1: Read the codes table
        Map<String, Character> codeToChar = readCodesTable(input);

        // Step 2: Read the compressed bits
        String compressedBits = readBits(input);

        input.close();

        // Step 3: Decode the bits using the codes table
        return decodeBits(compressedBits, codeToChar);
    }

    // Read the codes table from the beginning of the file
    private static Map<String, Character> readCodesTable(FileInputStream input) throws IOException {
        Map<String, Character> codeToChar = new HashMap<>();
        String line = "";

        // Read character by character until we find the "END" marker
        int byteValue;
        while ((byteValue = input.read()) != -1) {
            char c = (char) byteValue;

            if (c == '\n') {
                if (line.equals("END")) {
                    break; // End of codes table
                }

                // Parse the line: "ascii:code"
                if (line.contains(":")) {
                    String[] parts = line.split(":", 2);
                    int ascii = Integer.parseInt(parts[0]);
                    String code = parts[1];
                    codeToChar.put(code, (char) ascii);
                }

                line = ""; // Reset for next line
            } else {
                line += c;
            }
        }

        return codeToChar;
    }

    // Read the remaining bytes as bits
    private static String readBits(FileInputStream input) throws IOException {
        StringBuilder bits = new StringBuilder();

        int byteValue;
        while ((byteValue = input.read()) != -1) {
            // Convert byte to 8-bit binary string
            String binaryString = String.format("%8s", Integer.toBinaryString(byteValue)).replace(' ', '0');
            bits.append(binaryString);
        }

        return bits.toString();
    }

    // Decode the bit string using the codes table
    private static String decodeBits(String bits, Map<String, Character> codeToChar) {
        StringBuilder result = new StringBuilder();
        String currentCode = "";

        for (int i = 0; i < bits.length(); i++) {
            currentCode += bits.charAt(i);

            // Check if this code matches a character
            if (codeToChar.containsKey(currentCode)) {
                result.append(codeToChar.get(currentCode));
                currentCode = ""; // Reset for next character
            }
        }

        return result.toString();
    }

    // Save text to a file
    private static void saveToFile(String text, String filename) throws IOException {
        FileOutputStream output = new FileOutputStream(filename);
        output.write(text.getBytes());
        output.close();
    }
}
