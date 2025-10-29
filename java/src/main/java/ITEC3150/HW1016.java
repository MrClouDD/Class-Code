package ITEC3150;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * ITEC3150 - Graph Problem: Reach Target Number
 * 
 * Problem: Starting with value 1, we can perform two operations:
 * - Multiply by 2 (*2)
 * - Divide by 3 (/3) with integer division (drop fraction)
 * 
 * Goal: Find the minimum number of operations to reach a target value.
 * 
 * Example: To reach 10
 * 1 *2 -> 2 *2 -> 4 *2 -> 8 *2 -> 16 /3 -> 5 *2 -> 10
 * Solution: 1*2*2*2*2/3*2 (6 operations)
 * 
 * This is a shortest path problem solved using graphs and BFS
 */
public class HW1016 {

    /**
     * Find the minimum sequence of operations to reach target from 1
     * Uses BFS to guarantee shortest path
     */
    public static String findPath(int target) {
        // Base case
        if (target == 1) {
            return "1";
        }

        // BFS: Queue stores current values to explore
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        // Track visited values to avoid cycles
        Set<Integer> visited = new HashSet<>();
        visited.add(1);

        // Parent map: tracks how we reached each value
        // Key: value, Value: parent value
        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(1, null);

        // Operation map: tracks which operation was used
        // Key: value, Value: operation used to get here ("*2" or "/3")
        Map<Integer, String> operation = new HashMap<>();
        operation.put(1, "");

        // Upper limit to avoid infinite search
        int maxValue = Math.max(target * 6, 1000);

        // BFS Main Loop
        while (!queue.isEmpty()) {
            int current = queue.poll();

            // Try both operations

            // Operation 1: Multiply by 2
            int next = current * 2;
            if (next <= maxValue && !visited.contains(next)) {
                visited.add(next);
                parent.put(next, current);
                operation.put(next, "*2");
                queue.offer(next);

                // Found target?
                if (next == target) {
                    return buildPath(target, parent, operation);
                }
            }

            // Operation 2: Divide by 3
            next = current / 3;
            if (next > 0 && !visited.contains(next)) {
                visited.add(next);
                parent.put(next, current);
                operation.put(next, "/3");
                queue.offer(next);

                // Found target?
                if (next == target) {
                    return buildPath(target, parent, operation);
                }
            }
        }

        return "No solution found";
    }

    /**
     * Build the path string by backtracking from target to 1
     */
    private static String buildPath(int target, Map<Integer, Integer> parent,
            Map<Integer, String> operation) {
        // Build path backwards from target to 1
        List<String> ops = new ArrayList<>();
        int current = target;

        while (parent.get(current) != null) {
            ops.add(operation.get(current));
            current = parent.get(current);
        }

        // Reverse to get path from 1 to target
        Collections.reverse(ops);

        // Build result string
        StringBuilder result = new StringBuilder("1");
        for (String op : ops) {
            result.append(op);
        }

        return result.toString();
    }

    /**
     * Verify a solution by evaluating the expression
     */
    public static int evaluate(String path) {
        String[] tokens = path.split("(?=[*/])|(?<=[*/])");
        int result = Integer.parseInt(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            String op = tokens[i];
            int operand = Integer.parseInt(tokens[i + 1]);

            if (op.equals("*")) {
                result *= operand;
            } else {
                result /= operand;
            }
        }

        return result;
    }

    /**
     * Count number of operations in a path
     */
    public static int countOps(String path) {
        int count = 0;
        for (char c : path.toCharArray()) {
            if (c == '*' || c == '/') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Reach Target Number ===");
        System.out.println("Starting value: 1");
        System.out.println("Operations: *2 and /3");
        System.out.println();

        System.out.print("Enter target value: ");
        int target = input.nextInt();

        System.out.println("\nSearching for minimum path...\n");

        String path = findPath(target);

        if (path.equals("No solution found")) {
            System.out.println("No solution found!");
        } else {
            System.out.println("Solution found!");
            System.out.println("Path: " + path);
            System.out.println("Number of operations: " + countOps(path));
            System.out.println("Verification: " + evaluate(path));

            // Show step-by-step
            System.out.println("\nStep-by-step:");
            showSteps(path);
        }

        input.close();
    }

    /**
     * Display step-by-step evaluation
     */
    private static void showSteps(String path) {
        String[] parts = path.split("(?<=[0-9])(?=[*/])|(?<=[*/])(?=[0-9])");
        int current = 1;
        System.out.println("  " + current);

        for (int i = 1; i < parts.length; i += 2) {
            String op = parts[i];
            int operand = Integer.parseInt(parts[i + 1]);

            if (op.equals("*")) {
                current *= operand;
            } else {
                current /= operand;
            }
            System.out.println("  " + op + operand + " = " + current);
        }
    }
}
