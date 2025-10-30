package customLibrary.ITEC3150;

import java.util.ArrayList;
import java.util.List;

public class WeightedGraphKruskal<V> extends UnweightedGraph<V> {
    /** Construct an empty */
    public WeightedGraphKruskal() {
    }

    /** Construct a WeightedGraph from vertices and edged in arrays */
    public WeightedGraphKruskal(V[] vertices, int[][] edges) {
        createWeightedGraph(java.util.Arrays.asList(vertices), edges);
    }

    /** Construct a WeightedGraph from vertices and edges in list */
    public WeightedGraphKruskal(int[][] edges, int numberOfVertices) {
        List<V> vertices = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++)
            vertices.add((V) (Integer.valueOf(i)));

        createWeightedGraph(vertices, edges);
    }

    /** Construct a WeightedGraph for vertices 0, 1, 2 and edge list */
    public WeightedGraphKruskal(List<V> vertices, List<WeightedEdge> edges) {
        createWeightedGraph(vertices, edges);
    }

    /** Construct a WeightedGraph from vertices 0, 1, and edge array */
    public WeightedGraphKruskal(List<WeightedEdge> edges,
            int numberOfVertices) {
        List<V> vertices = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++)
            vertices.add((V) (Integer.valueOf(i)));

        createWeightedGraph(vertices, edges);
    }

    /** Construct a WeightedGraph from adjacency matrix */
    public WeightedGraphKruskal(double[][] adjacencyMatrix) {
        List<V> vertices = new ArrayList<>();
        for (int i = 0; i < adjacencyMatrix.length; i++)
            vertices.add((V) (Integer.valueOf(i)));

        createWeightedGraphFromMatrix(vertices, adjacencyMatrix);
    }

    /** Construct a WeightedGraph from vertices and adjacency matrix */
    public WeightedGraphKruskal(V[] vertices, double[][] adjacencyMatrix) {
        createWeightedGraphFromMatrix(java.util.Arrays.asList(vertices), adjacencyMatrix);
    }

    /** Create adjacency lists from edge arrays */
    private void createWeightedGraph(List<V> vertices, int[][] edges) {
        this.vertices = vertices;

        for (int i = 0; i < vertices.size(); i++) {
            neighbors.add(new ArrayList<Edge>()); // Create a list for vertices
        }

        for (int i = 0; i < edges.length; i++) {
            neighbors.get(edges[i][0]).add(
                    new WeightedEdge(edges[i][0], edges[i][1], edges[i][2]));
        }
    }

    /** Create adjacency lists from edge lists */
    private void createWeightedGraph(
            List<V> vertices, List<WeightedEdge> edges) {
        this.vertices = vertices;

        for (int i = 0; i < vertices.size(); i++) {
            neighbors.add(new ArrayList<Edge>()); // Create a list for vertices
        }

        for (WeightedEdge edge : edges) {
            neighbors.get(edge.u).add(edge); // Add an edge into the list
        }
    }

    /** Create adjacency lists from adjacency matrix */
    private void createWeightedGraphFromMatrix(
            List<V> vertices, double[][] adjacencyMatrix) {
        this.vertices = vertices;

        for (int i = 0; i < vertices.size(); i++) {
            neighbors.add(new ArrayList<Edge>()); // Create a list for vertices
        }

        // Convert adjacency matrix to edge list
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    neighbors.get(i).add(
                        new WeightedEdge(i, j, adjacencyMatrix[i][j]));
                }
            }
        }
    }

    /** Return the weight on the edge (u, v) */
    public double getWeight(int u, int v) throws Exception {
        for (Edge edge : neighbors.get(u)) {
            if (edge.v == v) {
                return ((WeightedEdge) edge).weight;
            }
        }

        throw new Exception("Edge does not exit");
    }

    /** Display edges with weights */
    public void printWeightedEdges() {
        for (int i = 0; i < getSize(); i++) {
            System.out.print(getVertex(i) + " (" + i + "): ");
            for (Edge edge : neighbors.get(i)) {
                System.out.print("(" + edge.u +
                        ", " + edge.v + ", " + ((WeightedEdge) edge).weight + ") ");
            }
            System.out.println();
        }
    }

    /** Add an edge (u, v, weight) to the graph */
    public boolean addEdge(int u, int v, double weight) {
        return addEdge(new WeightedEdge(u, v, weight));
    }

    /** Get a minimum spanning tree rooted at vertex 0 */
    public MST getMinimumSpanningTree() {
        return getMinimumSpanningTree(0);
    }

    /** Get a minimum spanning tree using Kruskal's algorithm */
    public MST getMinimumSpanningTree(int startingVertex) {
        // Collect all edges from the graph
        List<WeightedEdge> allEdges = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            for (Edge e : neighbors.get(i)) {
                allEdges.add((WeightedEdge) e);
            }
        }

        // Sort edges by weight in ascending order
        allEdges.sort((e1, e2) -> Double.compare(e1.weight, e2.weight));

        double totalWeight = 0;
        List<Integer> T = new ArrayList<>();
        List<WeightedEdge> mstEdges = new ArrayList<>();

        // Build MST adjacency list incrementally
        List<List<Integer>> mstAdjList = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            mstAdjList.add(new ArrayList<>());
        }

        // Process edges in sorted order
        for (WeightedEdge edge : allEdges) {
            if (mstEdges.size() == getSize() - 1) {
                break; // MST is complete
            }

            // Check if adding this edge creates a cycle using DFS
            if (!createsCycle(mstAdjList, edge.u, edge.v)) {
                mstEdges.add(edge);
                totalWeight += edge.weight;
                // Add edge to MST adjacency list (undirected)
                mstAdjList.get(edge.u).add(edge.v);
                mstAdjList.get(edge.v).add(edge.u);
            }
        }

        // Build the parent array for MST representation (rooted at startingVertex)
        int[] mstParent = new int[getSize()];
        for (int i = 0; i < getSize(); i++) {
            mstParent[i] = -1;
        }

        // Perform BFS to establish parent relationships rooted at startingVertex
        boolean[] visited = new boolean[getSize()];
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        queue.offer(startingVertex);
        visited[startingVertex] = true;
        T.add(startingVertex);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : mstAdjList.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    mstParent[v] = u;
                    T.add(v);
                    queue.offer(v);
                }
            }
        }

        return new MST(startingVertex, mstParent, T, totalWeight);
    }

    /** Check if adding edge (u, v) creates a cycle using DFS */
    private boolean createsCycle(List<List<Integer>> adjList, int u, int v) {
        // If there's already a path from u to v, adding edge (u,v) creates a cycle
        boolean[] visited = new boolean[getSize()];
        return dfsPathExists(adjList, u, v, visited, -1);
    }

    /** DFS to check if path exists from start to target */
    private boolean dfsPathExists(List<List<Integer>> adjList, int current,
            int target, boolean[] visited, int parent) {
        if (current == target) {
            return true;
        }

        visited[current] = true;

        for (int neighbor : adjList.get(current)) {
            if (!visited[neighbor]) {
                if (dfsPathExists(adjList, neighbor, target, visited, current)) {
                    return true;
                }
            }
        }

        return false;
    }

    /** MST is an inner class in WeightedGraph */
    public class MST extends SearchTree {
        private double totalWeight; // Total weight of all edges in the tree

        public MST(int root, int[] parent, List<Integer> searchOrder,
                double totalWeight) {
            super(root, parent, searchOrder);
            this.totalWeight = totalWeight;
        }

        public double getTotalWeight() {
            return totalWeight;
        }
    }

    /** Find single source shortest paths */
    public ShortestPathTree getShortestPath(int sourceVertex) {
        // cost[v] stores the cost of the path from v to the source
        double[] cost = new double[getSize()];
        for (int i = 0; i < cost.length; i++) {
            cost[i] = Double.POSITIVE_INFINITY; // Initial cost set to infinity
        }
        cost[sourceVertex] = 0; // Cost of source is 0

        // parent[v] stores the previous vertex of v in the path
        int[] parent = new int[getSize()];
        parent[sourceVertex] = -1; // The parent of source is set to -1

        // T stores the vertices whose path found so far
        List<Integer> T = new ArrayList<>();

        // Expand T
        while (T.size() < getSize()) {
            // Find smallest cost v in V - T
            int u = -1; // Vertex to be determined
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i = 0; i < getSize(); i++) {
                if (!T.contains(i) && cost[i] < currentMinCost) {
                    currentMinCost = cost[i];
                    u = i;
                }
            }

            if (u == -1)
                break;
            else
                T.add(u); // Add a new vertex to T

            // Adjust cost[v] for v that is adjacent to u and v in V - T
            for (Edge e : neighbors.get(u)) {
                if (!T.contains(e.v)
                        && cost[e.v] > cost[u] + ((WeightedEdge) e).weight) {
                    cost[e.v] = cost[u] + ((WeightedEdge) e).weight;
                    parent[e.v] = u;
                }
            }
        } // End of while

        // Create a ShortestPathTree
        return new ShortestPathTree(sourceVertex, parent, T, cost);
    }

    /** ShortestPathTree is an inner class in WeightedGraph */
    public class ShortestPathTree extends SearchTree {
        private double[] cost; // cost[v] is the cost from v to source

        /** Construct a path */
        public ShortestPathTree(int source, int[] parent,
                List<Integer> searchOrder, double[] cost) {
            super(source, parent, searchOrder);
            this.cost = cost;
        }

        /** Return the cost for a path from the root to vertex v */
        public double getCost(int v) {
            return cost[v];
        }

        /** Print paths from all vertices to the source */
        public void printAllPaths() {
            System.out.println("All shortest paths from " +
                    vertices.get(getRoot()) + " are:");
            for (int i = 0; i < cost.length; i++) {
                printPath(i); // Print a path from i to the source
                System.out.println("(cost: " + cost[i] + ")"); // Path cost
            }
        }
    }
}
