package Graph;

import Clients.IClient;
import Graph.Unweighted.DirectedGraph;

public class GraphClient implements IClient {

    @Override
    public void run() {
        int v = 5;

        DirectedGraph g = new DirectedGraph(v);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);

        Graph.Problems problems = new Graph.Problems();
        problems.PrintTopologiCalSort(g);
    }
}
