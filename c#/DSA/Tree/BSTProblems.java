package TreeProblems;

import java.util.*;

public class BSTProblems {

	public boolean Contains(TreeNode root, int key) {

		if (root == null) {
			return false;
		}

		if (root.data == key) {
			return true;
		}

		if (key < root.data) {
			return Contains(root.left, key);
		}

		return Contains(root.right, key);
	}

	public TreeNode Insert_Recursive(TreeNode root, int key) {
		if (root == null) {
			return new TreeNode(key);
		}

		if (key < root.data) {
			root.left = Insert_Recursive(root.left, key);
		} else if (key > root.data) {
			root.right = Insert_Recursive(root.right, key);
		}

		return root;
	}

	public TreeNode Insert_Iterative(TreeNode root, int key) {
		if (root == null) {
			return new TreeNode(key);
		}

		TreeNode curr = root, parent = null;
		while (curr != null) {
			parent = curr;
			if (key < curr.data) {
				curr = curr.left;
			} else if (key > curr.data) {
				curr = curr.right;
			} else {
				return curr;
			}
		}

		TreeNode newNode = new TreeNode(key);

		if (key < parent.data) {
			parent.left = newNode;
		} else if (key > parent.data) {
			parent.right = newNode;
		}

		return newNode;
	}

	public TreeNode deleteNode(TreeNode root, int key) {

		if (root == null) {
			return null;
		}

		if (key > root.data) {
			root.right = deleteNode(root.right, key);
		} else if (key < root.data) {
			root.left = deleteNode(root.left, key);
		} else {

			if (root.left == null) {
				return root.right;
			}

			if (root.right == null) {
				return root.left;
			}

			// find successor
			TreeNode curr = root.right;
			while (curr != null && curr.left != null) {
				curr = curr.left;
			}

			root.data = curr.data;
			root.right = deleteNode(root.right, curr.data);
		}

		return root;
	}

	public TreeNode Floor(TreeNode root, int key) {

		TreeNode curr = root, res = null;
		while (curr != null) {
			if (curr.data == key) {
				return curr;
			}

			if (curr.data < key) {
				res = curr;
				curr = curr.right;
			} else {
				curr = curr.left;
			}
		}

		return res;
	}

	public TreeNode Ceil(TreeNode root, int key) {

		TreeNode curr = root, res = null;
		while (curr != null) {
			if (curr.data == key) {
				return root;
			}

			if (curr.data > key) {
				res = curr;
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}

		return res;
	}

	public void PrintCeilOnLeft(int[] arr, int n) {

		TreeSet<Integer> ts = new TreeSet<Integer>();
		ts.add(arr[0]);
		System.out.print(-1);
		for (int i = 1; i < n; i++) {

			int ceil = ts.ceiling(arr[i]);
			if (ts.contains(ceil)) {
				System.out.print(ceil);
			} else {
				System.out.print(-1);
			}
			ts.add(arr[i]);
		}
	}

	public TreeNode BuilTreeWithLCount(TreeNode root, int key) {
		if (root == null) {
			return new TreeNode(key);
		}

		if (key < root.data) {
			root.left = BuilTreeWithLCount(root.left, key);
			root.lCount = root.lCount + 1;
		} else if (key > root.data) {
			root.right = BuilTreeWithLCount(root.right, key);
		}

		return root;
	}

	public int FindKthSmallestUtil(TreeNode root, int k) {

		if (root == null) {
			return -1;
		}

		if (root.lCount + 1 == k) {
			return root.data;
		}

		if (root.lCount + 1 > k) {
			return FindKthSmallestUtil(root.left, k);
		}

		return FindKthSmallestUtil(root.right, k - root.lCount - 1);
	}

	public int FindKthSmallest(int[] arr, int n, int k) {
		TreeNode root = null;
		for (int i = 0; i < n; i++) {
			root = BuilTreeWithLCount(root, arr[i]);
		}

		int res = FindKthSmallestUtil(root, k);
		return res;
	}

	public boolean CheckBSTUtil(TreeNode root, int min, int max) {

		if (root == null) {
			return true;
		}

		return root.data > min && root.data < max && CheckBSTUtil(root.left, min, root.data)
				&& CheckBSTUtil(root.right, root.data, max);
	}

	public boolean CheckBST(TreeNode root) {

		return CheckBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	TreeNode prev = null, first = null, second = null;

	public void FixBSTWithTwoNodesSwappedUtil(TreeNode root) {

		if (root == null) {
			return;
		}

		FixBSTWithTwoNodesSwappedUtil(root.left);

		if (prev != null && root.data < prev.data) {
			if (first == null) {
				first = prev;
			}

			second = root;
		}

		prev = root;
		
		FixBSTWithTwoNodesSwappedUtil(root.right);
	}

	public void FixBSTWithTwoNodesSwapped(TreeNode root) {

		FixBSTWithTwoNodesSwappedUtil(root);

		if (first != null && second != null) {
			int t = first.data;
			first.data = second.data;
			second.data = t;
		}
	}

	public boolean IsPairWithSumExistUtil(TreeNode root, int sum, HashSet<Integer> s) {

		if (root == null) {
			return false;
		}
		if (IsPairWithSumExistUtil(root.left, sum, s)) {
			return true;
		}

		if (s.contains(sum - root.data)) {
			return true;
		}

		s.add(root.data);

		return IsPairWithSumExistUtil(root.right, sum, s);
	}

	public boolean IsPairWithSumExist(TreeNode root, int sum) {

		if (root == null) {
			return false;
		}

		return IsPairWithSumExistUtil(root, sum, new HashSet<Integer>());
	}

	public void PrintVerticalSumUtil(TreeNode root, int hd, TreeMap<Integer, Integer> map) {

		if (root == null) {
			return;
		}
		PrintVerticalSumUtil(root.left, hd - 1, map);
		int sum = map.get(hd) == null ? 0 : map.get(hd);
		map.put(hd, sum + root.data);
		PrintVerticalSumUtil(root.right, hd + 1, map);
	}

	public void PrintVerticalSum(TreeNode root) {
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		PrintVerticalSumUtil(root, 0, map);
		for (Map.Entry<Integer, Integer> sum : map.entrySet()) {
			System.out.print(sum + " ");
		}
	}

	ArrayList<Integer> verticalOrder(TreeNode root) {
		TreeMap<Integer, ArrayList<Integer>> map = VerticalOrderTraversal(root);
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
			ArrayList<Integer> al = entry.getValue();
			res.addAll(al);
		}

		return res;
	}

	public TreeMap<Integer, ArrayList<Integer>> VerticalOrderTraversal(TreeNode root) {
		class Pair {
			TreeNode node;
			int hd;

			public Pair(TreeNode n, int hd) {
				this.hd = hd;
				node = n;
			}
		}

		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(root, 0));

		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		while (!q.isEmpty()) {
			Pair p = q.poll();

			TreeNode curr = p.node;
			int hd = p.hd;

			if (map.containsKey(hd)) {
				map.get(hd).add(curr.data);
			} else {
				ArrayList<Integer> al = new ArrayList<Integer>();
				al.add(curr.data);
				map.put(hd, al);
			}

			if (curr.left != null) {
				q.offer(new Pair(curr.left, hd - 1));
			}

			if (curr.right != null) {
				q.offer(new Pair(curr.right, hd + 1));
			}
		}

		return map;
	}

	public TreeMap<Integer, Integer> TopView(TreeNode root) {
		class Pair {
			TreeNode node;
			int hd;

			public Pair(TreeNode n, int hd) {
				this.hd = hd;
				node = n;
			}
		}

		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(root, 0));

		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		while (!q.isEmpty()) {
			Pair p = q.poll();

			TreeNode curr = p.node;
			int hd = p.hd;

			if (!map.containsKey(hd)) {
				map.put(hd, curr.data);
			}

			if (curr.left != null) {
				q.offer(new Pair(curr.left, hd - 1));
			}

			if (curr.right != null) {
				q.offer(new Pair(curr.right, hd + 1));
			}
		}

		return map;
	}

	public TreeMap<Integer, Integer> BottomView(TreeNode root) {
		class Pair {
			TreeNode node;
			int hd;

			public Pair(TreeNode n, int hd) {
				this.hd = hd;
				node = n;
			}
		}

		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(root, 0));

		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		while (!q.isEmpty()) {
			Pair p = q.poll();

			TreeNode curr = p.node;
			int hd = p.hd;

			map.put(hd, curr.data);

			if (curr.left != null) {
				q.offer(new Pair(curr.left, hd - 1));
			}

			if (curr.right != null) {
				q.offer(new Pair(curr.right, hd + 1));
			}
		}

		return map;
	}

	public ArrayList<Integer> FindCommonNodesInBST(TreeNode root1, TreeNode root2) {

		ArrayList<Integer> al = new ArrayList<Integer>();

		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();

		while ((root1 != null || !s1.isEmpty()) && (root2 != null || !s2.isEmpty())) {

			while (root1 != null) {
				s1.push(root1);
				root1 = root1.left;
			}

			while (root2 != null) {
				s2.push(root2);
				root2 = root2.left;
			}

			root1 = s1.peek();
			root2 = s2.peek();

			if (root1.data == root2.data) {

				s1.pop();
				s2.pop();

				al.add(root1.data);

				root1 = root1.right;
				root2 = root2.right;
			} else if (root1.data < root2.data) {
				root1 = root1.right;
				root2 = null;
				s1.pop();

			} else {
				root2 = root2.right;
				root1 = null;
				s2.pop();
			}
		}
		return al;
	}

	public TreeNode LowestCommonAncestorInBST(TreeNode root, int n1, int n2) {
		if (root == null)
			return null;

		if (n1 < root.data && n2 < root.data) {
			return LowestCommonAncestorInBST(root.left, n1, n2);
		} else if (n1 > root.data && n2 > root.data) {
			return LowestCommonAncestorInBST(root.right, n1, n2);
		}

		return root;
	}

	public void printNearNodesUtil(TreeNode root, int low, int high, ArrayList<Integer> al) {

		if (root == null)
			return;

		if (root.data > low) {
			printNearNodesUtil(root.left, low, high, al);
		}

		if (root.data >= low && root.data <= high) {
			al.add(root.data);
		}

		if (root.data < high) {
			printNearNodesUtil(root.right, low, high, al);
		}
	}

	public ArrayList<Integer> printNearNodes(TreeNode root, int low, int high) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		printNearNodesUtil(root, low, high, al);
		return al;
	}

	class ClosestValue {
		int minDiffKey;
		int minDiff;

		public ClosestValue() {
			minDiffKey = Integer.MIN_VALUE;
			minDiff = Integer.MAX_VALUE;
		}
	}

	public void FindClosestValueUtil(TreeNode root, int K, ClosestValue c) {

		if (root.data == K) {
			c.minDiff = 0;
			c.minDiffKey = K;
			return;
		}

		int absValue = Math.abs(root.data - K);

		if (absValue < c.minDiff) {
			c.minDiff = absValue;
			c.minDiffKey = root.data;
		}

		if (root.data < K) {
			FindClosestValueUtil(root.right, K, c);
		} else {
			FindClosestValueUtil(root.left, K, c);
		}
	}

	public int FindClosestNodeForGivenValueK(TreeNode root, int K) {
		ClosestValue c = new ClosestValue();

		FindClosestValueUtil(root, K, c);
		return c.minDiff;
	}

	public TreeNode ConstructBSTFromLevelOrder(int[] arr) {
		class NodeExtension {
			TreeNode node;
			int min;
			int max;
		}

		int i = 0, n = arr.length;
		Queue<NodeExtension> q = new LinkedList<NodeExtension>();
		NodeExtension nodeDetail = new NodeExtension();
		nodeDetail.node = new TreeNode(arr[i++]);
		nodeDetail.min = Integer.MIN_VALUE;
		nodeDetail.max = Integer.MAX_VALUE;
		q.offer(nodeDetail);

		TreeNode root = nodeDetail.node;

		while (i != n) {
			NodeExtension tmp = q.poll();

			if (i < n && arr[i] > tmp.min && arr[i] < tmp.node.data) {

				nodeDetail = new NodeExtension();
				nodeDetail.node = new TreeNode(arr[i++]);
				nodeDetail.min = tmp.min;
				nodeDetail.max = tmp.node.data;
				q.offer(nodeDetail);

				tmp.node.left = nodeDetail.node;
			}

			if (i < n && arr[i] > tmp.node.data && arr[i] < tmp.max) {
				nodeDetail = new NodeExtension();
				nodeDetail.node = new TreeNode(arr[i++]);
				nodeDetail.min = tmp.node.data;
				nodeDetail.max = tmp.max;
				q.offer(nodeDetail);

				tmp.node.right = nodeDetail.node;
			}
		}

		return root;
	}

	class Index {
		int index;

		public Index() {
			index = 0;
		}
	}

	public TreeNode ConstructBSTFromPreOrderUtil(int[] pre, int n, int min, int max, Index preIndex) {

		if (preIndex.index >= n) {
			return null;
		}

		int key = pre[preIndex.index];

		if (key > min && key < max) {
			TreeNode root = new TreeNode(key);
			preIndex.index++;

			root.left = ConstructBSTFromPreOrderUtil(pre, n, min, key, preIndex);
			root.right = ConstructBSTFromPreOrderUtil(pre, n, key, max, preIndex);

			return root;

		} else {
			return null;
		}
	}

	public TreeNode ConstructBSTFromPreOrder(int[] pre) {
		Index preIndex = new Index();

		return ConstructBSTFromPreOrderUtil(pre, pre.length, Integer.MIN_VALUE, Integer.MAX_VALUE, preIndex);
	}

	public void PrintPostOrderTraversalFromGivenPreOrderUtil(int pre[], int n, int min, int max, Index preIndex) {
		if (preIndex.index == n) {
			return;
		}

		int key = pre[preIndex.index];
		if (key < min || key > max) {
			return;
		}

		preIndex.index++;
		PrintPostOrderTraversalFromGivenPreOrderUtil(pre, n, min, key, preIndex);
		PrintPostOrderTraversalFromGivenPreOrderUtil(pre, n, key, max, preIndex);

		System.out.print(key + " ");
	}

	public void PrintPostOrderTraversalFromGivenPreOrder(int pre[], int size) {
		Index preIndex = new Index();
		PrintPostOrderTraversalFromGivenPreOrderUtil(pre, size, Integer.MIN_VALUE, Integer.MAX_VALUE, preIndex);
	}

	public int getCountOfNode(TreeNode node, int low, int high) {
		// Base Case
		if (node == null)
			return 0;

		// If current node is in range, then
		// include it in count and recur for
		// left and right children of it
		if (node.data >= low && node.data <= high)
			return 1 + getCountOfNode(node.left, low, high) + getCountOfNode(node.right, low, high);

		// If current node is smaller than low,
		// then recur for right child
		else if (node.data < low)
			return getCountOfNode(node.right, low, high);

		// Else recur for left child
		else
			return getCountOfNode(node.left, low, high);
	}

	public List<Integer> Merge(TreeNode root1, TreeNode root2) {
		List<Integer> res = new ArrayList<Integer>();

		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();

		while ((root1 != null || !s1.isEmpty()) && (root2 != null || !s2.isEmpty())) {

			while (root1 != null) {
				s1.push(root1);
				root1 = root1.left;
			}

			while (root2 != null) {
				s2.push(root2);
				root2 = root2.left;
			}

			root1 = s1.peek();
			root2 = s2.peek();

			if (root1.data < root2.data) {
				s1.pop();
				res.add(root1.data);
				root1 = root1.right;
				root2 = null;
			} else if (root2.data < root1.data) {
				s2.pop();
				res.add(root2.data);
				root2 = root2.right;
				root1 = null;
			} else {
				s1.pop();
				s2.pop();
				res.add(root1.data);
				res.add(root2.data);
				root1 = root1.right;
				root2 = root2.right;
			}
		}

		while ((root1 != null || !s1.isEmpty())) {
			while (root1 != null) {
				s1.push(root1);
				root1 = root1.left;
			}

			root1 = s1.pop();
			res.add(root1.data);

			root1 = root1.right;
		}

		while ((root2 != null || !s2.isEmpty())) {
			while (root2 != null) {
				s2.push(root2);
				root2 = root2.left;
			}

			root2 = s2.pop();
			res.add(root2.data);

			root2 = root2.right;
		}

		return res;
	}
}
