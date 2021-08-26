package Graph.Unweighted;

import Graph.Graph;
import java.util.*;

public abstract class UnWeightedGraph extends Graph {

    protected ArrayList<ArrayList<Integer>> adj;

    public UnWeightedGraph(int v) {
        super(v);

        this.adj = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < v; i++) {
            this.adj.add(new ArrayList<Integer>());
        }
    }

    public abstract void addEdge(int u, int v);

    public ArrayList<ArrayList<Integer>> adjacencyList() {
        return this.adj;
    }

    public void PrintGraph() {
        for (int i = 0; i < this.adj.size(); i++) {
            System.out.print(i + " -> ");
            for (int j = 0; j < this.adj.get(i).size(); j++) {
                if (j == this.adj.get(i).size() - 1) {
                    System.out.print(this.adj.get(i).get(j));
                } else {
                    System.out.print(this.adj.get(i).get(j) + " - ");
                }

            }

            System.out.println();
        }
    }

}
