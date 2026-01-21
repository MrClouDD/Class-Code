package ITEC3150.Exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import customLibrary.ITEC3150.Edge;
import customLibrary.ITEC3150.Graph;
import customLibrary.ITEC3150.UnweightedGraph;

public class MaximumInducedSubgraph {

    public static <V> Graph<V> maximumInducedSubgraph(Graph<V> g, int k) {
        // Create a copy of vertices
        Set<V> remainingVertices = new HashSet<>(g.getVertices());

        // Remove vertices with < k degree
        boolean changed = true;
        while (changed) {
            changed = false;
            List<V> toRemove = new ArrayList<>();

            for (V vertex : remainingVertices) {
                int vertexIndex = g.getIndex(vertex);
                // Count neighbors that are still in remainingVertices
                int degree = 0;
                for (int neighborIndex : g.getNeighbors(vertexIndex)) {
                    V neighbor = g.getVertex(neighborIndex);
                    if (remainingVertices.contains(neighbor)) {
                        degree++;
                    }
                }

                // If degree < k, mark for removal
                if (degree < k) {
                    toRemove.add(vertex);
                    changed = true;
                }
            }

            // Remove
            remainingVertices.removeAll(toRemove);
        }

        // Build subgraph
        if (remainingVertices.isEmpty()) {
            return new UnweightedGraph<>();
        }

        // Create vertex list and index mapping
        List<V> subgraphVertices = new ArrayList<>(remainingVertices);
        Map<V, Integer> newIndexMap = new HashMap<>();
        for (int i = 0; i < subgraphVertices.size(); i++) {
            newIndexMap.put(subgraphVertices.get(i), i);
        }

        // Create edge list for induced subgraph
        List<Edge> subgraphEdges = new ArrayList<>();
        for (V u : remainingVertices) {
            int oldIndexU = g.getIndex(u);
            int newIndexU = newIndexMap.get(u);

            for (int oldIndexV : g.getNeighbors(oldIndexU)) {
                V v = g.getVertex(oldIndexV);
                if (remainingVertices.contains(v)) {
                    int newIndexV = newIndexMap.get(v);
                    // Add edge only once (avoid duplicates in undirected graph)
                    if (newIndexU < newIndexV) {
                        subgraphEdges.add(new Edge(newIndexU, newIndexV));
                        subgraphEdges.add(new Edge(newIndexV, newIndexU));
                    }
                }
            }
        }

        return new UnweightedGraph<>(subgraphVertices, subgraphEdges);
    }

    public static void main(String[] args) {
        // Test case 1: Simple graph with k=2
        String[] vertices = { "A", "B", "C", "D", "E", "F" };
        int[][] edges = {
                { 0, 1 }, { 1, 0 }, // A-B
                { 0, 2 }, { 2, 0 }, // A-C
                { 1, 2 }, { 2, 1 }, // B-C
                { 2, 3 }, { 3, 2 }, // C-D
                { 3, 4 }, { 4, 3 }, // D-E
                { 4, 5 }, { 5, 4 } // E-F
        };

        UnweightedGraph<String> graph1 = new UnweightedGraph<>(vertices, edges);

        System.out.println("Original Graph:");
        graph1.printEdges();
        System.out.println();

        Graph<String> result1 = maximumInducedSubgraph(graph1, 2);
        System.out.println("Maximum Induced Subgraph with k=2:");
        System.out.println("Size: " + result1.getSize());
        result1.printEdges();
        System.out.println();

        // Test case 2: k too large, no subgraph exists
        Graph<String> result2 = maximumInducedSubgraph(graph1, 5);
        System.out.println("Maximum Induced Subgraph with k=5:");
        System.out.println("Size: " + result2.getSize());
        if (result2.getSize() == 0) {
            System.out.println("No such subgraph exists");
        }
        System.out.println();
    }
}
