package TreeProblems;

import java.lang.reflect.Constructor;
import java.util.*;

public class BSTProblems {

	public boolean Contains(Node root, int key) {

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

	public Node Insert_Recursive(Node root, int key) {
		if (root == null) {
			return new Node(key);
		}

		if (key < root.data) {
			root.left = Insert_Recursive(root.left, key);
		} else if (key > root.data) {
			root.right = Insert_Recursive(root.right, key);
		}

		return root;
	}

	public Node Insert_Iterative(Node root, int key) {
		if (root == null) {
			return new Node(key);
		}

		Node curr = root, parent = null;
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

		Node newNode = new Node(key);

		if (key < parent.data) {
			parent.left = newNode;
		} else if (key > parent.data) {
			parent.right = newNode;
		}

		return newNode;
	}

	public Node Delete(Node root, int key) {

		if (root == null) {
			return null;
		}

		if (key > root.data) {
			root.right = Delete(root.right, key);
		} else if (key < root.data) {
			root.left = Delete(root.left, key);
		} else {

			if (root.left == null) {
				return root.right;
			}

			if (root.right == null) {
				return root.left;
			}

			// find successor
			Node rightChild = root.nextRight;
			Node curr = rightChild;
			while (curr != null && curr.left != null) {
				curr = curr.left;
			}

			root.data = curr.data;
			root.right = Delete(root.right, curr.data);
		}

		return root;
	}

	public Node Floor(Node root, int key) {

		Node curr = root, res = null;
		while (curr != null) {
			if (curr.data == key) {
				return root;
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

	public Node Ceil(Node root, int key) {

		Node curr = root, res = null;
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

	public Node BuilTreeWithLCount(Node root, int key) {
		if (root == null) {
			return new Node(key);
		}

		if (key < root.data) {
			root.left = BuilTreeWithLCount(root.left, key);
			root.lCount = root.lCount + 1;
		} else if (key > root.data) {
			root.right = BuilTreeWithLCount(root.right, key);
		}

		return root;
	}

	public int FindKthSmallestUtil(Node root, int k) {

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
		Node root = null;
		for (int i = 0; i < n; i++) {
			root = BuilTreeWithLCount(root, arr[i]);
		}

		int res = FindKthSmallestUtil(root, k);
		return res;
	}

	public boolean CheckBSTUtil(Node root, int min, int max) {

		if (root == null) {
			return true;
		}

		return root.data > min && root.data < max && CheckBSTUtil(root.left, min, root.data)
				&& CheckBSTUtil(root.right, root.data, max);
	}

	public boolean CheckBST(Node root) {

		return CheckBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	Node prev = null, first = null, second = null;

	public void FixBSTWithTwoNodesSwappedUtil(Node root) {

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

		FixBSTWithTwoNodesSwappedUtil(root.right);
	}

	public void FixBSTWithTwoNodesSwapped(Node root) {

		FixBSTWithTwoNodesSwappedUtil(root);

		if (first != null && second != null) {
			int t = first.data;
			first.data = second.data;
			second.data = t;
		}
	}

	public boolean IsPairWithSumExistUtil(Node root, int sum, HashSet<Integer> s) {

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

	public boolean IsPairWithSumExist(Node root, int sum) {

		if (root == null) {
			return false;
		}

		return IsPairWithSumExistUtil(root, sum, new HashSet<Integer>());
	}

	public void PrintVerticalSumUtil(Node root, int hd, TreeMap<Integer, Integer> map) {

		if (root == null) {
			return;
		}
		PrintVerticalSumUtil(root.left, hd - 1, map);
		int sum = map.get(hd) == null ? 0 : map.get(hd);
		map.put(hd, sum + root.data);
		PrintVerticalSumUtil(root.right, hd + 1, map);
	}

	public void PrintVerticalSum(Node root) {
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		PrintVerticalSumUtil(root, 0, map);
		for (Map.Entry<Integer, Integer> sum : map.entrySet()) {
			System.out.print(sum + " ");
		}
	}

	public TreeMap<Integer, ArrayList<Integer>> VerticalOrderTraversal(Node root) {
		class Pair {
			Node node;
			int hd;

			public Pair(Node n, int hd) {
				this.hd = hd;
				node = n;
			}
		}

		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(root, 0));

		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
		while (!q.isEmpty()) {
			Pair p = q.poll();

			Node curr = p.node;
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

	public TreeMap<Integer, Integer> TopView(Node root) {
		class Pair {
			Node node;
			int hd;

			public Pair(Node n, int hd) {
				this.hd = hd;
				node = n;
			}
		}

		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(root, 0));

		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		while (!q.isEmpty()) {
			Pair p = q.poll();

			Node curr = p.node;
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

	public TreeMap<Integer, Integer> BottomView(Node root) {
		class Pair {
			Node node;
			int hd;

			public Pair(Node n, int hd) {
				this.hd = hd;
				node = n;
			}
		}

		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(root, 0));

		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		while (!q.isEmpty()) {
			Pair p = q.poll();

			Node curr = p.node;
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
}
