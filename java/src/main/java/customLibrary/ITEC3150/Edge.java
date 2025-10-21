package customLibrary.ITEC3150;

public class Edge<E> {
    public int u;
    public int v;
    int weight;

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
        this.weight = 0;
    }

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        return u == ((Edge) o).u && v == ((Edge) o).v;
    }
}