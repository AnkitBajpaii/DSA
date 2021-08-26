package Graph.Weighted;

import java.util.*;
import Graph.Graph;

public class WeightedGraph extends Graph {
    public class Node {
        public int v;
        public int weight;

        public Node(int v, int w) {
            this.v = v;
            this.weight = w;
        }

    }

    private LinkedList<Node> adj[];

    public WeightedGraph(int v) {
        super(v);

        adj = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Node>();
        }
    }

    public void addEdge(int u, int v, int w) {
        adj[u].add(new Node(v, w));
    }

    public LinkedList<Node>[] adjacencyList() {
        return this.adj;
    }
}