package customLibrary.ITEC3150;

public class EdgeWithCost<E> {
    int u;
    int v;
    int cost;

    public EdgeWithCost(int u, int v, int cost) {
        this.u = u;
        this.v = v;
        this.cost = cost;
    }

    @Override // Test if two edges are identical
    public boolean equals(Object o) {
        return u == ((Edge) o).u && v == ((Edge) o).v;
    }
}