package Graph;

import java.util.*;
import Arrays.arrayUtil;

import Graph.Unweighted.DirectedGraph;
import Graph.Unweighted.UnWeightedGraph;
import Graph.Unweighted.UndirectedGraph;
import Graph.Weighted.WeightedGraph;

public abstract class Graph {

    protected int _v;

    public Graph(int v) {

        this._v = v;
    }

    public int size() {
        return this._v;
    }

    public static class Problems {

        private void BFSUtil(UnWeightedGraph g, int s, boolean[] visited) {
            Queue<Integer> q = new ArrayDeque<Integer>();

            ArrayList<ArrayList<Integer>> adj = g.adjacencyList();

            q.offer(s);
            visited[s] = true;

            while (!q.isEmpty()) {
                int u = q.poll();
                System.out.print(u + " ");
                for (int v : adj.get(u)) {
                    if (!visited[v]) {
                        visited[v] = true;
                        q.offer(v);
                    }
                }
            }
        }

        public void BFS(UnWeightedGraph g) {
            System.out.println("Performing BFS on Graph");
            int v = g.size();

            boolean[] visited = new boolean[v];

            for (int s = 0; s < v; s++) {
                if (!visited[s]) {
                    BFSUtil(g, s, visited);
                    System.out.println();
                }
            }
        }

        // Variation Of BFS.
        // Count connected components in graph. Or Find no. of Islands in graph.
        public int CountConnectedComponents_BFS(UnWeightedGraph g) {
            System.out.println("Performing BFS on Graph");
            int v = g.size();

            boolean[] visited = new boolean[v];
            int count = 0;
            for (int s = 0; s < v; s++) {
                if (!visited[s]) {
                    BFSUtil(g, s, visited);
                    count++;
                    System.out.println();
                }
            }

            System.out.println("Count of connected components = " + count);
            return count;
        }

        public void DFSUtil(UnWeightedGraph g, int u, boolean[] visited) {
            visited[u] = true;
            System.out.print(u + " ");
            var adj = g.adjacencyList();

            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    DFSUtil(g, v, visited);
                }
            }
        }

        public void DFS(UnWeightedGraph g) {

            int v = g.size();
            boolean[] visited = new boolean[v];

            System.out.println("Performing DFS on graph");

            for (int s = 0; s < v; s++) {
                if (!visited[s]) {
                    DFSUtil(g, s, visited);
                }
            }
        }

        // Variation Of DFS.
        // Count connected components in graph. Or Find no. of Islands in graph.
        public int CountConnectedComponents_DFS(UnWeightedGraph g) {
            System.out.println("Performing DFS on Graph");
            int v = g.size();

            boolean[] visited = new boolean[v];
            int count = 0;
            for (int s = 0; s < v; s++) {
                if (!visited[s]) {
                    DFSUtil(g, s, visited);
                    count++;
                    System.out.println();
                }
            }

            System.out.println("Count of connected components = " + count);
            return count;
        }

        public int[] ShortestPathInUnWeightedGraph(UndirectedGraph g, int s) {
            System.out.println("Shortedt path in unweighted graph");

            int V = g.size();

            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);

            dist[s] = 0;
            boolean[] visited = new boolean[V];
            visited[s] = true;

            Queue<Integer> q = new ArrayDeque<Integer>();
            q.offer(s);

            var adj = g.adjacencyList();

            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v : adj.get(u)) {
                    if (!visited[v]) {
                        dist[v] = dist[u] + 1;
                        visited[v] = true;
                        q.offer(v);
                    }
                }
            }

            arrayUtil.Print1DArray(dist);
            return dist;
        }

        private boolean DetectCycleInUndirectedGraph_DFS_Util(UndirectedGraph g, boolean[] visited, int u,
                Integer parent) {
            visited[u] = true;
            var adj = g.adjacencyList();

            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    if (DetectCycleInUndirectedGraph_DFS_Util(g, visited, v, u)) {
                        return true;
                    }

                } else if (v != parent) {
                    return true;
                }
            }

            return false;
        }

        public boolean DetectCycleInUndirectedGraph_DFS(UndirectedGraph g) {
            int V = g.size();

            boolean[] visited = new boolean[V];

            for (int u = 0; u < V; u++) {
                if (!visited[u]) {
                    if (DetectCycleInUndirectedGraph_DFS_Util(g, visited, u, -1)) {
                        System.out.println("Graph contains cycle");
                        return true;
                    }
                }
            }

            System.out.println("Graph does not contain cycle");
            return false;
        }

        public boolean DetectCycleInDirectedGraph_DFS_Util(DirectedGraph g, int u, boolean[] visited, boolean[] recSt) {
            visited[u] = true;
            recSt[u] = true;

            var adj = g.adjacencyList();

            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    if (DetectCycleInDirectedGraph_DFS_Util(g, v, visited, recSt)) {
                        return true;
                    }
                } else if (recSt[v] == true) {// if node is visited & currently active in recursion stack
                    return true;
                }
            }
            recSt[u] = false;
            return false;
        }

        public boolean DetectCycleInDirectedGraph_DFS(DirectedGraph g) {
            int V = g.size();

            boolean[] visited = new boolean[V];
            boolean[] recSt = new boolean[V]; // recusrion stack

            for (int i = 0; i < V; i++) {
                if (visited[i] == false) {
                    if (DetectCycleInDirectedGraph_DFS_Util(g, i, visited, recSt)) {
                        System.out.println("Graph contain cycle");
                        return true;
                    }
                }
            }

            System.out.println("Graph does not contain cycle");
            return false;
        }

        // Topological Sort of Directed graph using BFS, Also called Kahns Algorithm
        public void TopologicalSort_BFS(DirectedGraph g) {
            int V = g.size();

            int[] indegree = new int[V];
            var adj = g.adjacencyList();

            // store indegree of vertices
            for (int u = 0; u < V; u++) {
                for (int v : adj.get(u)) {
                    indegree[v]++;
                }
            }

            Queue<Integer> q = new ArrayDeque<Integer>();

            for (int u = 0; u < V; u++) {
                if (indegree[u] == 0) {
                    q.offer(u);
                }
            }

            System.out.println("Topological sort of directed graph using Kahns Algorithm");

            int count = 0;

            while (!q.isEmpty()) {
                int u = q.poll();
                System.out.print(u + " ");

                for (int v : adj.get(u)) {
                    if (--indegree[v] == 0) {
                        q.offer(v);
                    }
                }

                count++;
            }

            if (count != V) {
                System.out.println("Graph contains cycle");
            }
        }

        public void PrintTopologiCalSort(DirectedGraph g) {

            ArrayDeque<Integer> stack = this.TopologicalSort_DFS(g);

            System.out.println("Topological sort using DFS");

            while (!stack.isEmpty()) {
                System.out.print(stack.pop() + " ");
            }
        }

        // Topological sort using DFS
        public ArrayDeque<Integer> TopologicalSort_DFS(DirectedGraph g) {
            int V = g.size();

            boolean[] visited = new boolean[V];
            ArrayDeque<Integer> stack = new ArrayDeque<Integer>();

            var adj = g.adjacencyList();

            for (int u = 0; u < V; u++) {
                if (!visited[u]) {
                    this.TopologicalSort_DFS_Util(adj, u, visited, stack);
                }
            }

            return stack;
        }

        private void TopologicalSort_DFS_Util(ArrayList<ArrayList<Integer>> adj, int u, boolean[] visited,
                ArrayDeque<Integer> stack) {
            visited[u] = true;

            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    TopologicalSort_DFS_Util(adj, v, visited, stack);
                }
            }

            stack.push(u);
        }

        public int[] ShortestPathInDAG(WeightedGraph g, int s) {
            int V = g.size();

            var adj = g.adjacencyList();

            ArrayDeque<Integer> stack = this.TopologicalSort_DFS(g);

            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[s] = 0;

            while (!stack.isEmpty()) {
                int u = stack.pop();
                if (dist[u] != Integer.MAX_VALUE) {
                    for (WeightedGraph.Node n : adj[u]) {
                        if (dist[n.v] > dist[u] + n.weight) {
                            dist[n.v] = dist[u] + n.weight;
                        }
                    }
                }
            }

            return dist;
        }

        public ArrayDeque<Integer> TopologicalSort_DFS(WeightedGraph g) {

            ArrayDeque<Integer> stack = new ArrayDeque<Integer>();

            int V = g.size();

            boolean[] visited = new boolean[V];

            var adj = g.adjacencyList();

            for (int i = 0; i < V; i++) {

                if (!visited[i]) {
                    TopologicalSort_DFS_Util(adj, i, visited, stack);
                }
            }

            return stack;
        }

        public void TopologicalSort_DFS_Util(LinkedList<WeightedGraph.Node> adj[], int u, boolean[] visited,
                ArrayDeque<Integer> stack) {

            visited[u] = true;

            for (WeightedGraph.Node n : adj[u]) {
                if (!visited[n.v]) {
                    TopologicalSort_DFS_Util(adj, n.v, visited, stack);
                }
            }

            stack.add(u);
        }

        public int PrimMST(int[][] graph, int V) {
            int res = 0;

            boolean[] mSet = new boolean[V];

            int[] key = new int[V];
            Arrays.fill(key, Integer.MAX_VALUE);

            key[0] = 0;

            for (int count = 0; count < graph.length; count++) {

                int u = -1;

                for (int i = 0; i < V; i++) {
                    if (!mSet[i] && (u == -1 || key[i] < key[u])) {
                        u = i;
                    }
                }

                mSet[u] = true;
                res = res + key[u];

                for (int v = 0; v < V; v++) {
                    if (graph[u][v] != 0 && !mSet[v] && graph[u][v] < key[v]) {
                        key[v] = graph[u][v];
                    }
                }
            }

            return res;
        }

        public int[] DijkstraShortedPath(int[][] graph, int V, int s) {
            int[] dist = new int[V];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[s] = 0;

            boolean[] finalized = new boolean[V];

            for (int count = 0; count < V; count++) {
                int u = -1;
                for (int i = 0; i < V; i++) {
                    if (!finalized[i] && (u == -1 || dist[i] < dist[u])) {
                        u = i;
                    }
                }

                finalized[u] = true;

                for (int v = 0; v < V; v++) {
                    if (graph[u][v] != 0 && !finalized[v]) {
                        dist[v] = Math.min(dist[v], dist[u] + graph[u][v]);
                    }
                }
            }

            return dist;
        }
    }
}
