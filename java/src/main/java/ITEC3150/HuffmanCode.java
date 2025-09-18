package ITEC3150;

import java.util.PriorityQueue;

/**
 * Basic Huffman Compression Tree Implementation
 * Contains the tree structure and basic methods for Huffman compression
 */
public class HuffmanCode {

    // Count how many times each character appears in text
    public static int[] countCharacters(String text) {
        int[] frequencies = new int[256]; // ASCII characters 0-255

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            frequencies[c]++;
        }

        return frequencies;
    }

    // Build the Huffman tree using a priority queue
    public static Tree buildHuffmanTree(int[] frequencies) {
        PriorityQueue<Tree> queue = new PriorityQueue<>();

        // Create a tree for each character that appears in the text
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                Tree tree = new Tree(frequencies[i], (char) i);
                queue.add(tree);
            }
        }

        // Build the tree by combining the two smallest trees
        while (queue.size() > 1) {
            Tree tree1 = queue.remove(); // Get smallest
            Tree tree2 = queue.remove(); // Get second smallest
            Tree combinedTree = new Tree(tree1, tree2); // Combine them
            queue.add(combinedTree); // Put back in queue
        }

        return queue.remove(); // Return the final tree
    }

    // Generate Huffman codes by walking through the tree
    public static void generateCodes(Tree.Node node, String code, String[] codes) {
        if (node.left == null && node.right == null) {
            // Leaf node
            codes[(int) node.element] = code;
        } else {
            if (node.left != null) {
                generateCodes(node.left, code + "0", codes);
            }
            if (node.right != null) {
                generateCodes(node.right, code + "1", codes);
            }
        }
    }

    // Print the codes in a nice table
    public static void printCodes(int[] frequencies, String[] codes) {
        System.out.println("Huffman Codes:");
        System.out.printf("%-10s %-10s %-10s %-15s\n", "ASCII", "Character", "Frequency", "Code");
        System.out.println("-----------------------------------------------");

        for (int i = 0; i < codes.length; i++) {
            if (frequencies[i] > 0) {
                String character;
                if (i == 32) {
                    character = "SPACE";
                } else if (i == 10) {
                    character = "\\n";
                } else if (i >= 32 && i <= 126) {
                    character = "" + (char) i;
                } else {
                    character = "?";
                }
                System.out.printf("%-10d %-10s %-10d %-15s\n",
                        i, character, frequencies[i], codes[i]);
            }
        }
    }

    // Simple Tree class for Huffman coding
    public static class Tree implements Comparable<Tree> {
        Node root;
        // deterministic tie-breaker for equal weights
        private static long nodeCounter = 0;

        // Constructor for leaf node (single character)
        public Tree(int weight, char character) {
            root = new Node(weight, character);
        }

        // Constructor for internal node (combining two trees)
        public Tree(Tree leftTree, Tree rightTree) {
            root = new Node();
            root.left = leftTree.root;
            root.right = rightTree.root;
            root.weight = leftTree.root.weight + rightTree.root.weight;
        }

        // Compare trees by weight (for priority queue)
        public int compareTo(Tree other) {
            int cmp = Integer.compare(this.root.weight, other.root.weight);
            if (cmp != 0)
                return cmp;
            // tie-break by creation order to make ordering deterministic
            return Long.compare(this.root.id, other.root.id);
        }

        // Simple Node class
        public static class Node {
            char element; // Character (for leaf nodes)
            int weight; // Frequency of this character/subtree
            long id; // creation sequence id for tie-breaking
            Node left; // Left child
            Node right; // Right child

            // Constructor for leaf node
            public Node(int weight, char element) {
                this.weight = weight;
                this.element = element;
                this.id = ++Tree.nodeCounter;
            }

            // Constructor for internal node
            public Node() {
                // Empty node
                this.id = ++Tree.nodeCounter;
            }
        }
    }
}
