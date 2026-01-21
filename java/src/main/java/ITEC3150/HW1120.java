package ITEC3150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import customLibrary.ITEC3150.UnweightedGraph;

/**
 * Merging Nodes in a graph:
 * Given an initial tree and a final tree after merging operations,
 * determine the sequence of merge operations performed.
 * 
 * Rules:
 * - Only nodes with the same parent can be merged
 * - When merging, the smaller value merges into the larger value
 * - Children of the merged node transfer to the surviving node
 */
public class HW1120 {

    public static void main(String[] args) {
        UnweightedGraph<Integer> initGraph = new UnweightedGraph<>();
        UnweightedGraph<Integer> finalGraph = new UnweightedGraph<>();
        int initSize;
        int finalSize;

        // Test input hardcoded for testing
        String input = "1\n8\n7 5\n2 1\n4 2\n5 1\n3 2\n8 5\n6 2\n4\n8 5\n5 1\n6 5";
        System.out.println("Input");
        System.out.println(input);
        Scanner kbd = new Scanner(input);

        kbd.nextInt(); // testCaseNum (not used in this implementation)
        initSize = kbd.nextInt();

        // Build initial graph from N-1 edges (child parent pairs)
        for (int i = 0; i < initSize - 1; i++) {
            int value = kbd.nextInt(); // child node
            int parent = kbd.nextInt(); // parent node

            addEdgeValidated(initGraph, value, parent);
        }

        // Build final graph from M-1 edges (child parent pairs)
        finalSize = kbd.nextInt();

        for (int i = 0; i < finalSize - 1; i++) {
            int value = kbd.nextInt(); // child node
            int parent = kbd.nextInt(); // parent node

            addEdgeValidated(finalGraph, value, parent);
        }

        // Determine and output the merge sequence
        determineMergedTargets(initGraph, finalGraph);
        kbd.close();
    }

    /**
     * Adds a directed edge from child to parent in the graph.
     * Creates vertices if they don't already exist.
     */
    static void addEdgeValidated(UnweightedGraph<Integer> graph, int value, int parent) {
        // Ensure both vertices exist in the graph
        if (!graph.getVertices().contains(value)) {
            graph.addVertex(value);
        }
        if (!graph.getVertices().contains(parent)) {
            graph.addVertex(parent);
        }

        // Convert values to indices for graph operations
        int valueIndex = graph.getIndex(value);
        int parentIndex = graph.getIndex(parent);

        // Add directed edge from child to parent
        graph.addEdge(valueIndex, parentIndex);
    }

    /**
     * Finds which nodes were removed (merged) between initial and final graphs.
     */
    static Set<Integer> getNodesMerged(UnweightedGraph<Integer> initGraph, UnweightedGraph<Integer> finalGraph) {
        Set<Integer> initNodes = new HashSet<>(initGraph.getVertices());
        Set<Integer> finalNodes = new HashSet<>(finalGraph.getVertices());

        // Find nodes that disappeared (were merged into other nodes)
        Set<Integer> nodesToMerge = new HashSet<>(initNodes);
        nodesToMerge.removeAll(finalNodes);

        return nodesToMerge;
    }

    /**
     * Builds a map of each node to its children.
     */
    static Map<Integer, Set<Integer>> getChildrenMap(UnweightedGraph<Integer> graph) {
        Map<Integer, Set<Integer>> childrenMap = new HashMap<>();
        Map<Integer, Integer> parentMap = getParentMap(graph);

        // For each node, find all nodes that have it as a parent
        for (Integer node : graph.getVertices()) {
            Set<Integer> children = new HashSet<>();
            for (Integer potentialChild : graph.getVertices()) {
                // Check if this node is the parent of potentialChild
                if (parentMap.get(potentialChild) != null && parentMap.get(potentialChild).equals(node)) {
                    children.add(potentialChild);
                }
            }
            childrenMap.put(node, children);
        }

        return childrenMap;
    }

    /**
     * Builds a map of each node to its parent.
     */
    static Map<Integer, Integer> getParentMap(UnweightedGraph<Integer> graph) {
        Map<Integer, Integer> parentMap = new HashMap<>();

        for (Integer node : graph.getVertices()) {
            // Get all neighbors (edges were added from child to parent)
            List<Integer> neighborIndices = graph.getNeighbors(graph.getIndex(node));
            for (Integer neighborIndex : neighborIndices) {
                Integer neighbor = graph.getVertex(neighborIndex);
                // Since edges go from child to parent, neighbor is the parent
                parentMap.put(node, neighbor);
            }
        }

        return parentMap;
    }

    /**
     * Determines the sequence of merge operations that transforms initGraph into
     * finalGraph.
     * 
     * Algorithm:
     * 1. Identify which nodes need to be merged (exist in init but not final)
     * 2. Process merges level by level:
     * - Group nodes by their current parent
     * - For each parent's children, find the merge target (highest value that
     * survives in final)
     * - Merge all siblings into the target
     * - Update parent-child relationships (merged node's children become target's
     * children)
     * 3. Repeat until all merges are complete
     */
    static void determineMergedTargets(UnweightedGraph<Integer> initGraph, UnweightedGraph<Integer> finalGraph) {
        // Find all nodes that were merged (disappeared from final graph)
        Set<Integer> nodesToMerge = getNodesMerged(initGraph, finalGraph);

        // Create working copies of parent-child relationships that we'll update during
        // merges
        Map<Integer, Integer> currentParent = new HashMap<>(getParentMap(initGraph));
        Map<Integer, Set<Integer>> currentChildren = new HashMap<>(getChildrenMap(initGraph));

        List<String> mergeOps = new ArrayList<>();

        // Process merges level by level until all nodes are merged
        while (!nodesToMerge.isEmpty()) {
            // Group nodes to merge by their current parent
            Map<Integer, Set<Integer>> nodesByParent = new HashMap<>();

            for (Integer node : nodesToMerge) {
                Integer parent = currentParent.get(node);
                if (parent != null) {
                    nodesByParent.putIfAbsent(parent, new HashSet<>());
                    nodesByParent.get(parent).add(node);
                }
            }

            // Track which nodes we process in this iteration
            Set<Integer> processedThisRound = new HashSet<>();

            // Process parents in a consistent order for deterministic output
            List<Integer> sortedParents = new ArrayList<>(nodesByParent.keySet());
            java.util.Collections.sort(sortedParents);

            // For each parent, merge its children that need to be merged
            for (Integer parent : sortedParents) {
                Set<Integer> siblings = nodesByParent.get(parent);

                if (siblings.isEmpty())
                    continue;

                // Find the merge target: highest value child that exists in final graph
                Integer mergeTarget = null;

                // Check all current children of this parent
                if (currentChildren.containsKey(parent)) {
                    for (Integer child : currentChildren.get(parent)) {
                        if (finalGraph.getVertices().contains(child)) {
                            if (mergeTarget == null || child > mergeTarget) {
                                mergeTarget = child;
                            }
                        }
                    }
                }

                if (mergeTarget == null)
                    continue;

                // Merge each sibling into the target
                for (Integer nodeToMerge : siblings) {
                    if (nodeToMerge.equals(mergeTarget))
                        continue;

                    // Record this merge operation
                    mergeOps.add(nodeToMerge + " " + mergeTarget);
                    processedThisRound.add(nodeToMerge);

                    // Transfer children: all children of merged node become children of merge
                    // target
                    if (currentChildren.containsKey(nodeToMerge)) {
                        for (Integer child : currentChildren.get(nodeToMerge)) {
                            currentParent.put(child, mergeTarget);
                        }

                        // Update merge target's children list
                        currentChildren.putIfAbsent(mergeTarget, new HashSet<>());
                        currentChildren.get(mergeTarget).addAll(currentChildren.get(nodeToMerge));

                        // Remove merged node's children entry
                        currentChildren.remove(nodeToMerge);
                    }

                    // Remove merged node from parent's children list
                    if (currentChildren.containsKey(parent)) {
                        currentChildren.get(parent).remove(nodeToMerge);
                    }
                }
            }

            // Remove processed nodes from the set of nodes to merge
            nodesToMerge.removeAll(processedThisRound);

            // Safety check: break if no progress was made to avoid infinite loop
            if (processedThisRound.isEmpty()) {
                break;
            }
        }

        // Output the number of merge operations followed by each operation
        System.out.println();
        System.out.println("Output");
        System.out.println(mergeOps.size());
        for (String op : mergeOps) {
            System.out.println(op);
        }
    }
}
