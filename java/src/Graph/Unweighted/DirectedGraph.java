package Graph.Unweighted;

public class DirectedGraph extends UnWeightedGraph {

    public DirectedGraph(int v) {
        super(v);
    }

    @Override
    public void addEdge(int u, int v) {
        this.adj.get(u).add(v);
    }
}
