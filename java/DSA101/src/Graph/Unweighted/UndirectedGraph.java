package Graph.Unweighted;

public class UndirectedGraph extends UnWeightedGraph {

    public UndirectedGraph(int v) {
        super(v);
    }

    @Override
    public void addEdge(int u, int v) {
        this.adj.get(u).add(v);
        this.adj.get(v).add(u);
    }
}
