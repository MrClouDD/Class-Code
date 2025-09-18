package ITEC3150;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Ajitesh Sandhu
 *         File Compression using Huffman Compression
 *         This program reads doi.txt and creates a compressed file
 */
public class Compress {

    public static void main(String[] args) {
        try {
            // Step 1: Read the input file
            String text = readFile("doi.txt");
            System.out.println("File read successfully. Length: " + text.length() + " characters");

            // Step 2: Count character frequencies
            int[] frequencies = HuffmanCode.countCharacters(text);

            // Step 3: Build Huffman tree
            HuffmanCode.Tree huffmanTree = HuffmanCode.buildHuffmanTree(frequencies);

            // Step 4: Generate codes from the tree
            String[] codes = new String[256];
            if (huffmanTree.root != null) {
                HuffmanCode.generateCodes(huffmanTree.root, "", codes);
            }

            // Step 5: Display the codes
            HuffmanCode.printCodes(frequencies, codes);

            // Step 6: Compress and save to file
            compressFile(text, codes, "doi_compressed.bin");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Read a text file
    public static String readFile(String filename) throws IOException {
        FileInputStream inputStream = new FileInputStream(filename);

        // Read all bytes and convert to string
        byte[] bytes = inputStream.readAllBytes();
        inputStream.close();

        return new String(bytes);
    }

    // Compress the file and save it to working directory
    public static void compressFile(String text, String[] codes, String outputFileName) throws IOException {
        // Create the compressed data as a string of 0s and 1s
        StringBuilder compressedBits = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            compressedBits.append(codes[c]);
        }

        // Save to working directory
        FileOutputStream output = new FileOutputStream(outputFileName);

        // Write the codes first (so we can decompress later)
        writeCodesTable(output, codes);

        // Write the compressed data
        writeBits(output, compressedBits.toString());

        output.close();

        System.out.println("\nCompression complete!");
        System.out.println("Compressed file saved as: " + outputFileName);
        System.out.println("Original size: " + text.length() + " bytes");
        System.out.println("Compressed size: " + (compressedBits.length() / 8 + 1) + " bytes (approximately)");
    }

    // Helper method to write the codes table to file
    private static void writeCodesTable(FileOutputStream output, String[] codes) throws IOException {
        for (int i = 0; i < codes.length; i++) {
            if (codes[i] != null) {
                String line = i + ":" + codes[i] + "\n";
                output.write(line.getBytes());
            }
        }
        output.write("END\n".getBytes()); // Mark end of codes table
    }

    // Helper method to convert bit string to bytes
    private static void writeBits(FileOutputStream output, String bits) throws IOException {
        // Pad with zeros if needed
        while (bits.length() % 8 != 0) {
            bits += "0";
        }

        // Convert every 8 bits to a byte
        for (int i = 0; i < bits.length(); i += 8) {
            String byteString = bits.substring(i, i + 8);
            int byteValue = Integer.parseInt(byteString, 2);
            output.write(byteValue);
        }
    }
}
