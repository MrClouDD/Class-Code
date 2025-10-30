package ITEC3150;

import customLibrary.ITEC3150.WeightedGraphKruskal;

public class TestMinimumSpanningTree {
    public static void main(String[] args) {
        String[] vertices = { "Seattle", "San Francisco", "Los Angeles",
                "Denver", "Kansas City", "Chicago", "Boston", "New York",
                "Atlanta", "Miami", "Dallas", "Houston" };

        int[][] edges = {
                { 0, 1, 807 }, { 0, 3, 1331 }, { 0, 5, 2097 },
                { 1, 0, 807 }, { 1, 2, 381 }, { 1, 3, 1267 },
                { 2, 1, 381 }, { 2, 3, 1015 }, { 2, 4, 1663 }, { 2, 10, 1435 },
                { 3, 0, 1331 }, { 3, 1, 1267 }, { 3, 2, 1015 }, { 3, 4, 599 },
                { 3, 5, 1003 },
                { 4, 2, 1663 }, { 4, 3, 599 }, { 4, 5, 533 }, { 4, 7, 1260 },
                { 4, 8, 864 }, { 4, 10, 496 },
                { 5, 0, 2097 }, { 5, 3, 1003 }, { 5, 4, 533 },
                { 5, 6, 983 }, { 5, 7, 787 },
                { 6, 5, 983 }, { 6, 7, 214 },
                { 7, 4, 1260 }, { 7, 5, 787 }, { 7, 6, 214 }, { 7, 8, 888 },
                { 8, 4, 864 }, { 8, 7, 888 }, { 8, 9, 661 },
                { 8, 10, 781 }, { 8, 11, 810 },
                { 9, 8, 661 }, { 9, 11, 1187 },
                { 10, 2, 1435 }, { 10, 4, 496 }, { 10, 8, 781 }, { 10, 11, 239 },
                { 11, 8, 810 }, { 11, 9, 1187 }, { 11, 10, 239 }
        };

        WeightedGraphKruskal<String> graph1 = new WeightedGraphKruskal<>(vertices, edges);
        WeightedGraphKruskal<String>.MST tree1 = graph1.getMinimumSpanningTree();
        System.out.println("tree1: Total weight is "
                + tree1.getTotalWeight());
        tree1.printTree();

        edges = new int[][] {
                { 0, 1, 2 }, { 0, 3, 8 },
                { 1, 0, 2 }, { 1, 2, 7 }, { 1, 3, 3 },
                { 2, 1, 7 }, { 2, 3, 4 }, { 2, 4, 5 },
                { 3, 0, 8 }, { 3, 1, 3 }, { 3, 2, 4 }, { 3, 4, 6 },
                { 4, 2, 5 }, { 4, 3, 6 }
        };

        WeightedGraphKruskal<Integer> graph2 = new WeightedGraphKruskal<>(edges, 5);
        WeightedGraphKruskal<Integer>.MST tree2 = graph2.getMinimumSpanningTree(1);
        System.out.println("\ntree2: Total weight is "
                + tree2.getTotalWeight());
        tree2.printTree();

        System.out.println("\nShow the search order for tree1:");
        for (int i : tree1.getSearchOrder())
            System.out.print(graph1.getVertex(i) + " ");

        // Test with adjacency matrix - same US cities example
        System.out.println("\n\n=== Testing with Adjacency Matrix ===");
        double[][] adjacencyMatrix = new double[12][12];
        // Initialize all to 0 (no edge)
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
        // Add edges from the original edge list
        adjacencyMatrix[0][1] = 807;
        adjacencyMatrix[0][3] = 1331;
        adjacencyMatrix[0][5] = 2097;
        adjacencyMatrix[1][0] = 807;
        adjacencyMatrix[1][2] = 381;
        adjacencyMatrix[1][3] = 1267;
        adjacencyMatrix[2][1] = 381;
        adjacencyMatrix[2][3] = 1015;
        adjacencyMatrix[2][4] = 1663;
        adjacencyMatrix[2][10] = 1435;
        adjacencyMatrix[3][0] = 1331;
        adjacencyMatrix[3][1] = 1267;
        adjacencyMatrix[3][2] = 1015;
        adjacencyMatrix[3][4] = 599;
        adjacencyMatrix[3][5] = 1003;
        adjacencyMatrix[4][2] = 1663;
        adjacencyMatrix[4][3] = 599;
        adjacencyMatrix[4][5] = 533;
        adjacencyMatrix[4][7] = 1260;
        adjacencyMatrix[4][8] = 864;
        adjacencyMatrix[4][10] = 496;
        adjacencyMatrix[5][0] = 2097;
        adjacencyMatrix[5][3] = 1003;
        adjacencyMatrix[5][4] = 533;
        adjacencyMatrix[5][6] = 983;
        adjacencyMatrix[5][7] = 787;
        adjacencyMatrix[6][5] = 983;
        adjacencyMatrix[6][7] = 214;
        adjacencyMatrix[7][4] = 1260;
        adjacencyMatrix[7][5] = 787;
        adjacencyMatrix[7][6] = 214;
        adjacencyMatrix[7][8] = 888;
        adjacencyMatrix[8][4] = 864;
        adjacencyMatrix[8][7] = 888;
        adjacencyMatrix[8][9] = 661;
        adjacencyMatrix[8][10] = 781;
        adjacencyMatrix[8][11] = 810;
        adjacencyMatrix[9][8] = 661;
        adjacencyMatrix[9][11] = 1187;
        adjacencyMatrix[10][2] = 1435;
        adjacencyMatrix[10][4] = 496;
        adjacencyMatrix[10][8] = 781;
        adjacencyMatrix[10][11] = 239;
        adjacencyMatrix[11][8] = 810;
        adjacencyMatrix[11][9] = 1187;
        adjacencyMatrix[11][10] = 239;

        // Test with vertices and adjacency matrix
        WeightedGraphKruskal<String> graph4 = new WeightedGraphKruskal<>(vertices, adjacencyMatrix);
        System.out.println("\nThe edges for graph4:");
        graph4.printWeightedEdges();
        WeightedGraphKruskal<String>.MST tree4 = graph4.getMinimumSpanningTree();
        System.out.println("\ntree4 (from adjacency matrix with vertices): Total weight is "
                + tree4.getTotalWeight());
        tree4.printTree();
    }
}
