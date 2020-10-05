package Graphs;

import java.util.*;

public class GraphProb {

	public void PrintGraph(ArrayList<ArrayList<Integer>> adj, int V) {
		for (int i = 0; i < V; i++) // Traverse adjacency list for every vertex
		{
			System.out.print(i); // Print the vertex id
			for (int j = 0; j < adj.get(i).size(); j++) // Traverse through all the connected component of that vertex
				System.out.print("-> " + adj.get(i).get(j)); // Print the connected vertex id

			System.out.println();
		}
	}

	// BFS of connected/disconnected graph O(V+E)
	public ArrayList<Integer> BFS(ArrayList<ArrayList<Integer>> adj, int v) {

		boolean[] visited = new boolean[v];
		ArrayList<Integer> res = new ArrayList<Integer>();

		for (int i = 0; i < v; i++) {
			if (visited[i] == false) {
				ArrayList<Integer> list = BFSUtil(adj, i, visited);
				res.addAll(list);
			}
		}

		return res;
	}

	public ArrayList<Integer> BFSUtil(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited) {

		ArrayList<Integer> res = new ArrayList<Integer>();

		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(s);
		visited[s] = true;

		while (!q.isEmpty()) {
			int u = q.poll();
			res.add(u);

			for (int v : adj.get(u)) {
				if (visited[v] == false) {
					q.offer(v);
					visited[v] = true;
				}
			}
		}

		return res;
	}

	// no of islands in graph i.e count number of connected components in graph
	// O(V+E)
	public int NoOfIslandsInGraphUsingBFS(ArrayList<ArrayList<Integer>> adj, int V) {

		boolean[] visited = new boolean[V];

		int count = 0;
		for (int i = 0; i < V; i++) {
			if (visited[i] == false) {
				BFSUtil(adj, i, visited);
				count++;
			}
		}
		return count;
	}

	public int LevelOfNode(ArrayList<ArrayList<Integer>> adj, int src, int in) {
		int V = adj.size();
		boolean[] visited = new boolean[V];

		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(src);
		q.offer(null);

		int level = 0;
		boolean found = false;

		while (!q.isEmpty()) {
			Integer u = q.poll();

			if (u == null) {
				q.offer(null);
				level++;
			} else {
				if (u == in) {
					found = true;
					break;
				}

				for (int v : adj.get(u)) {
					if (visited[v] == false) {
						q.offer(v);
						visited[v] = true;
					}
				}
			}

		}

		return found ? level : -1;
	}

	// DFS of connected/disconnected graph O(V+E)
	public ArrayList<Integer> DFS(ArrayList<ArrayList<Integer>> adj, int V) {

		boolean[] visited = new boolean[V];
		ArrayList<Integer> res = new ArrayList<Integer>();

		for (int i = 0; i < V; i++) {
			if (visited[i] == false) {
				DFSRec(adj, i, visited, res);
			}
		}

		return res;
	}

	// adj - adjacency list of undirected and connected graph
	public void DFSRec(ArrayList<ArrayList<Integer>> adj, int v, boolean[] visited, ArrayList<Integer> res) {

		visited[v] = true;
		res.add(v);

		for (int u : adj.get(v)) {
			if (visited[u] == false) {
				DFSRec(adj, u, visited, res);
			}
		}
	}

	public int[] ShortestPathInUnDirectedGraph(ArrayList<ArrayList<Integer>> adj, int vertices, int s) {

		boolean[] visited = new boolean[vertices];
		int[] dist = new int[vertices];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(s);
		visited[s] = true;
		dist[s] = 0;

		while (!q.isEmpty()) {
			int u = q.poll();
			for (int v : adj.get(u)) {
				if (visited[v] == false) {
					dist[v] = dist[u] + 1;
					q.offer(v);
					visited[v] = true;
				}
			}
		}
		return dist;
	}

	public boolean DetectCycleInUndirectedGraphDFS(ArrayList<ArrayList<Integer>> adj, int V) {

		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (visited[i] == false) {
				if (DetectCycleInUndirectedGraphDFSRecursive(adj, i, visited, -1) == true) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean DetectCycleInUndirectedGraphDFSRecursive(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited,
			int parent) {

		// parent means the node through which we came to s
		visited[s] = true;

		for (int u : adj.get(s)) {
			if (visited[u] == false) {
				if (DetectCycleInUndirectedGraphDFSRecursive(adj, u, visited, s) == true) {
					return true;
				}
			} else if (u != parent) {
				return true;
			}
		}

		return false;
	}

	public boolean DetectCycleInUndirectedGraphBFS(ArrayList<ArrayList<Integer>> adj, int V) {

		boolean[] visited = new boolean[V];
		for (int i = 0; i < V; i++) {
			if (visited[i] == false) {
				if (DetectCycleInUndirectedGraphBFSUtil(adj, V, i, visited) == true) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean DetectCycleInUndirectedGraphBFSUtil(ArrayList<ArrayList<Integer>> adj, int V, int s,
			boolean[] visited) {
		int[] parent = new int[V];
		Arrays.fill(parent, -1);

		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(s);
		visited[s] = true;

		while (!q.isEmpty()) {
			int u = q.poll();

			for (int v : adj.get(u)) {
				if (visited[v] == false) {
					q.offer(v);
					visited[v] = true;
					parent[v] = u;
				} else if (v != parent[u]) {
					return true;
				}
			}
		}

		return false;
	}

	// DFS based approach to find cycles in directed graph
	public boolean DetectCycleInDirectedGraph(ArrayList<ArrayList<Integer>> adj, int V) {
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];

		for (int i = 0; i < V; i++) {
			if (visited[i] == false) {
				if (DetectCycleInDirectedGraphRec(adj, i, visited, recStack) == true) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean DetectCycleInDirectedGraphRec(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited,
			boolean[] recStack) {

		visited[s] = true;
		recStack[s] = true;

		for (int u : adj.get(s)) {
			if (visited[u] == false && DetectCycleInDirectedGraphRec(adj, u, visited, recStack) == true) {
				return true;

			} else if (recStack[u] == true) {
				return true;
			}
		}

		recStack[s] = false;
		return false;
	}

	// Kahn's Algorithm
	// graph should be directed acyclic graph
	public int[] TopologicalSortingBFS(ArrayList<ArrayList<Integer>> adj, int V) {
		// store indegree of all vertices
		int[] inDegree = new int[V];
		Arrays.fill(inDegree, 0);

		for (int i = 0; i < V; i++) {
			for (int j = 0; j < adj.get(i).size(); j++) {
				inDegree[adj.get(i).get(j)]++;
			}
		}

		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}

		int[] res = new int[V];
		int index = 0;

		while (!q.isEmpty()) {
			int u = q.poll();
			res[index++] = u;

			for (int v : adj.get(u)) {
				inDegree[v]--;
				if (inDegree[v] == 0) {
					q.offer(v);
				}
			}
		}

		return res;
	}

	// DFS based approach for topological sorting
	public ArrayList<Integer> TopologicalSortDFS(ArrayList<ArrayList<Integer>> adj, int V) {
		boolean[] visited = new boolean[V];
		Stack<Integer> st = new Stack<Integer>();
		ArrayList<Integer> res = new ArrayList<Integer>();

		for (int i = 0; i < V; i++) {
			if (visited[i] == false) {
				TopologicalSortDFSRec(adj, i, visited, st);
			}
		}

		while (!st.isEmpty()) {
			res.add(st.pop());
		}

		return res;
	}

	public void TopologicalSortDFSRec(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited, Stack<Integer> st) {

		visited[s] = true;
		for (int v : adj.get(s)) {
			if (visited[v] == false) {
				TopologicalSortDFSRec(adj, v, visited, st);
			}
		}

		st.push(s);
	}

	// BFS based approach to find cycles in directed graph using Kahns Algo
	public boolean DetectCycleInDirectedGraphBFSUsingKahnsAlgo(ArrayList<ArrayList<Integer>> adj, int V) {
		// store indegree of all vertices
		int[] inDegree = new int[V];
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < adj.get(i).size(); j++) {
				inDegree[adj.get(i).get(j)]++;
			}
		}

		Queue<Integer> q = new LinkedList<Integer>();

		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}

		int count = 0;
		while (!q.isEmpty()) {
			int u = q.poll();

			for (int v : adj.get(u)) {
				inDegree[v]--;
				if (inDegree[v] == 0) {
					q.offer(v);
				}
			}

			count++;
		}

		return count != V;

	}

	public int PrimsMST(int[][] graph, int V) {
		int res = 0;
		int[] key = new int[V];

		Arrays.fill(key, Integer.MAX_VALUE);
		key[0] = 0;

		boolean[] mSet = new boolean[V];

		for (int count = 0; count < V; count++) {

			int u = -1;
			for (int i = 0; i < V; i++) {
				if (!mSet[i] && (u == -1 || key[i] < key[u])) {
					u = i;
				}
			}

			mSet[u] = true;

			res = res + key[u];

			for (int v = 0; v < V; v++) {
				if (!mSet[v] && graph[u][v] != 0 && graph[u][v] < key[v]) {
					key[v] = graph[u][v];
				}
			}
		}

		return res;
	}

	public int[] Dijkstra(int[][] graph, int V, int src) {

		int[] dist = new int[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;

		boolean[] fin = new boolean[V];

		for (int count = 0; count < V; count++) {
			int u = -1;

			for (int i = 0; i < V; i++) {
				if (!fin[i] && (u == -1 || dist[i] < dist[u])) {
					u = i;
				}
			}

			fin[u] = true;

			for (int v = 0; v < V; v++) {
				if (!fin[v] && graph[u][v] != 0) {
					dist[v] = Math.min(dist[v], dist[u] + graph[u][v]);
				}
			}
		}

		return dist;
	}

	public void KosarajuAlgoToFindStronglyConnectedComponentsInGraph(ArrayList<ArrayList<Integer>> adj, int V) {
		Stack<Integer> st = new Stack<Integer>();
		boolean[] visited = new boolean[V];

		// step 1 vertices in decreasing order of their finish time
		// reusing topological sort logic to fill stack i.e to get vertices in
		// decreasing order of their finish time
		for (int i = 0; i < V; i++) {
			TopologicalSortDFSRec(adj, i, visited, st);
		}

		// step 2 reverse the edges
		ArrayList<ArrayList<Integer>> revAdj = ReverseEdges(adj);

		// step 3 do DFS as per vertices in decreasing order of their finish time
		visited = new boolean[V];
		ArrayList<Integer> res = new ArrayList<Integer>();
		while (!st.isEmpty()) {
			DFSRec(revAdj, st.pop(), visited, res);
		}
	}

	ArrayList<ArrayList<Integer>> ReverseEdges(ArrayList<ArrayList<Integer>> adj) {
		ArrayList<ArrayList<Integer>> revAdj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < adj.size(); i++) {
			revAdj.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < adj.size(); i++) {
			for (int j = 0; j < adj.get(i).size(); j++) {
				revAdj.get(adj.get(i).get(j)).add(i);
			}
		}

		return revAdj;
	}	
	
	// Given a Directed Graph. Count the total number of ways or paths that exist between two vertices in the directed graph. These paths doesn’t contain any cycle.
	public int CountPaths(ArrayList<ArrayList<Integer>> g, int s, int d) {

		if(s == d) {
			return 1;
		}
		
		else {
			int pathCount = 0;
			for (int v : g.get(s)) {
				pathCount = pathCount + CountPaths(g, v, d);
			}
			return pathCount;
		}
	}
}
