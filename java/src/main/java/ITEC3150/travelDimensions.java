package ITEC3150;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import customLibrary.ITEC3150.Edge;

/**
 * Solution to the IT Travel Department warp gate problem.
 * 1. Sort all warpgates by cost (cheapest first)
 * 2. Add warpgates one by one if they don't create a cycle
 * 3. Use BFS to check if adding an edge creates a cycle
 * 4. Stop when all dimensions are connected (n-1 edges)
 */
public class travelDimensions {

    public static void main(String[] args) {
        // Predefined Example 1: Sample from problem statement
        // 5 dimensions, 6 warpgates - Expected output: 11
        int[][] example1 = {
                { 5, 6 },
                { 0, 1, 2 },
                { 0, 2, 3 },
                { 0, 3, 5 },
                { 1, 4, 4 },
                { 2, 3, 6 },
                { 2, 4, 1 }
        };

        // Predefined Example 2: Smaller test case
        // 4 dimensions, 5 warpgates - Expected output: 6
        int[][] example2 = {
                { 4, 5 },
                { 0, 1, 1 },
                { 0, 2, 4 },
                { 1, 2, 2 },
                { 1, 3, 5 },
                { 2, 3, 3 }
        };

        // Process Example 1
        System.out.println("=== Example 1 ===");
        processExample(example1);
        System.out.println();

        // Process Example 2
        System.out.println("=== Example 2 ===");
        processExample(example2);
    }

    /**
     * Process a given example and compute the minimum spanning tree cost
     * 
     * @param example 2D array containing dimension/warpgate data
     */
    public static void processExample(int[][] example) {
        int n = example[0][0];
        int m = example[0][1];

        System.out.println("Dimensions: " + n + ", Warpgates: " + m);

        // Create list to store all edges with their costs
        ArrayList<Edge> edges = new ArrayList<>();

        // Load edges from the example
        for (int i = 1; i < example.length; i++) {
            int u = example[i][0];
            int v = example[i][1];
            int cost = example[i][2];
            edges.add(new Edge(u, v, cost));
            System.out.println("  Warpgate: " + u + " <-> " + v + ", Cost: " + cost);
        }
        System.out.println();

        // Sort edges by cost (weight) - using Collections.sort with Comparator
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.getWeight() - e2.getWeight();
            }
        });

        // Build graph using adjacency list (standard graph representation in 3150)
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        // Build minimum spanning tree using Kruskal's algorithm
        int totalCost = 0;
        int edgesAdded = 0;

        for (Edge edge : edges) {
            int u = edge.u;
            int v = edge.v;

            // Check if adding this edge would create a cycle
            // If u and v are already connected, adding this edge creates a cycle
            if (!isConnected(graph, u, v, n)) {
                // Add edge to graph (bidirectional)
                graph.get(u).add(v);
                graph.get(v).add(u);

                totalCost += edge.getWeight();
                edgesAdded++;

                // MST complete when we have n-1 edges
                if (edgesAdded == n - 1) {
                    break;
                }
            }
        }

        // Output the minimum cost
        System.out.println("Minimum Cost: " + totalCost);
    }

    /**
     * Performs BFS to check if two vertices are already connected in the graph
     * 
     * @param graph adjacency list representation of the graph
     * @param start starting vertex
     * @param end   target vertex
     * @param n     number of vertices
     * @return true if there's a path from start to end
     */
    public static boolean isConnected(ArrayList<ArrayList<Integer>> graph, int start, int end, int n) {
        if (start == end) {
            return true;
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // Check all neighbors
            for (int neighbor : graph.get(current)) {
                if (neighbor == end) {
                    return true; // Found path to end
                }

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return false; // No path found
    }

}
