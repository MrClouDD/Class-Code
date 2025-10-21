package ITEC3150;

import java.util.List;

import customLibrary.ITEC3150.Edge;
import customLibrary.ITEC3150.UnweightedGraph;

public class NineTailModel<E> extends UnweightedGraph<E> {
    int NUMBER_OF_NODES = 512;
    int BOARD_SIZE = 2;

    UnweightedGraph<Integer>.SearchTree tree;

    public NineTailModel() {
        List<Edge> edges = getEdges();

        UnweightedGraph<Integer> graph = new UnweightedGraph<>(edges, NUMBER_OF_NODES);

        tree = graph.bfs(511);
    }

    List<Integer> getShortestPath(int nodeIndex) {

    }
}
