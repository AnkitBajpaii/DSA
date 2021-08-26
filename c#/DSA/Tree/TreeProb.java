package TreeProblems;

import java.util.*;

public class TreeProb {

	public void preOrderUtil(TreeNode root, ArrayList<Integer> res) {
		if (root == null) {
			return;
		}

		res.add(root.data);
		preOrderUtil(root.left, res);
		preOrderUtil(root.right, res);
	}

	public ArrayList<Integer> preorder(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		preOrderUtil(root, res);
		return res;
	}

	int height(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int lh = height(root.left);
		int rh = height(root.right);

		if (lh > rh) {
			return 1 + lh;
		}

		return 1 + rh;
	}

	boolean isIdentical(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}

		if (root1 == null || root2 == null) {
			return false;
		}

		if (root1.data != root2.data) {
			return false;
		}

		return isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right);
	}

	public int isSumProperty(TreeNode root) {
		if (root == null || (root.left == null && root.right == null)) {
			return 1;
		}

		int sum = 0;
		if (root.left != null) {
			sum += root.left.data;
		}

		if (root.right != null) {
			sum += root.right.data;
		}

		if ((sum == root.data) && isSumProperty(root.left) == 1 && isSumProperty(root.right) == 1) {
			return 1;
		}

		return 0;
	}

	public ArrayList<Integer> levelOrder(TreeNode node) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(node);
		while (!q.isEmpty()) {
			TreeNode curr = q.poll();
			res.add(curr.data);
			if (curr.left != null) {
				q.offer(curr.left);
			}

			if (curr.right != null) {
				q.offer(curr.right);
			}
		}

		return res;
	}

	public void levelOrderLineByLine(TreeNode node) {

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(node);
		String res = "";
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode curr = q.poll();
				res = res + curr.data + " ";
				if (curr.left != null) {
					q.offer(curr.left);
				}

				if (curr.right != null) {
					q.offer(curr.right);
				}
			}

			res = res + "$ ";
		}

		res = res.trim();
		System.out.print(res);
	}

	public void printSpiral(TreeNode node) {
		if (node == null) {
			return;
		}
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		s1.push(node);
		String res = " ";
		while (!s1.isEmpty() || !s2.isEmpty()) {
			while (!s1.isEmpty()) {
				TreeNode curr = s1.pop();
				res = res + curr.data + " ";

				if (curr.right != null) {
					s2.push(curr.right);
				}

				if (curr.left != null) {
					s2.push(curr.left);
				}
			}

			while (!s2.isEmpty()) {
				TreeNode curr = s2.pop();
				res = res + curr.data + " ";

				if (curr.left != null) {
					s1.push(curr.left);
				}

				if (curr.right != null) {
					s1.push(curr.right);
				}
			}
		}

		res = res.trim();
		System.out.print(res);
	}

	int getMaxWidth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);

		int res = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			if (size > res) {
				res = size;
			}

			for (int i = 0; i < size; i++) {
				TreeNode curr = q.poll();

				if (curr.left != null) {
					q.offer(curr.left);
				}

				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
		}

		return res;
	}

	int isBalancedUtil(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int lh = isBalancedUtil(root.left);
		if (lh == -1) {
			return -1;
		}
		int rh = isBalancedUtil(root.right);
		if (rh == -1) {
			return -1;
		}

		int diff = lh - rh;
		if (diff < 0) {
			diff = -1 * diff;
		}
		if (diff > 1) {
			return -1;
		}

		if (lh > rh) {
			return 1 + lh;
		}

		return 1 + rh;
	}

	boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;

		return isBalancedUtil(root) >= 0;
	}

	void leftView(TreeNode root) {
		if (root == null) {
			return;
		}

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		String res = "";
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode curr = q.poll();
				if (i == 0) {
					res = res + curr.data + " ";
				}

				if (curr.left != null) {
					q.offer(curr.left);
				}

				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
		}

		System.out.print(res.trim());
	}

	void rightView(TreeNode root) {
		if (root == null) {
			return;
		}

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		String res = "";
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode curr = q.poll();
				if (i == size - 1) {
					res = res + curr.data + " ";
				}

				if (curr.left != null) {
					q.offer(curr.left);
				}

				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
		}

		System.out.print(res.trim());
	}

	TreeNode lca(TreeNode root, int n1, int n2) {
		if (root == null) {
			return null;
		}

		if (root.data == n1 || root.data == n2) {
			return root;
		}

		TreeNode lca1 = lca(root.left, n1, n2);
		TreeNode lca2 = lca(root.right, n1, n2);
		if (lca1 != null && lca2 != null) {
			return root;
		}

		if (lca1 != null) {
			return lca1;
		}

		return lca2;
	}

	int res = 0;

	int diameterUtil(TreeNode root) {

		if (root == null) {
			return 0;
		}

		int lh = diameterUtil(root.left);
		int rh = diameterUtil(root.right);

		int d = 1 + lh + rh;
		if (d > res) {
			res = d;
		}
		return 1 + (lh > rh ? lh : rh);
	}

	int diameter(TreeNode root) {
		diameterUtil(root);
		int r = res;
		res = 0;
		return r;
	}

	public void fillSet(TreeNode root, HashSet<Integer> set, int hd) {
		if (root == null)
			return;

		fillSet(root.left, set, hd - 1);
		set.add(hd);
		fillSet(root.right, set, hd + 1);
	}

	public int verticalWidth(TreeNode root) {
		HashSet<Integer> set = new HashSet<Integer>();
		fillSet(root, set, 0);
		return set.size();
	}

	public boolean isSame(TreeNode T, TreeNode S) {
		if (T == null && S == null) {
			return true;
		}

		if (T == null || S == null) {
			return false;
		}

		return T.data == S.data && isSame(T.left, S.left) && isSame(T.right, S.right);

	}

	public boolean isSubtree(TreeNode T, TreeNode S) {
		if (T == null && S == null) {
			return true;
		}

		if (T == null || S == null) {
			return false;
		}

		return isSame(T, S) || isSame(T.left, S) || isSame(T.right, S) || isSubtree(T.left, S) || isSubtree(T.right, S);
	}

	TreeNode prev = null;

	TreeNode bToDLL(TreeNode root) {
		if (root == null) {
			return null;
		}

		TreeNode head = bToDLL(root.left);
		if (prev == null) {
			head = root;
		} else {
			prev.right = root;
			root.left = prev;
		}

		prev = root;
		bToDLL(root.right);
		return head;
	}

	TreeNode bTreeToClistUtil(TreeNode root) {
		if (root == null) {
			return null;
		}

		TreeNode head = bTreeToClistUtil(root.left);
		if (prev == null) {
			head = root;
		} else {
			prev.right = root;
			root.left = prev;
		}

		prev = root;
		bTreeToClistUtil(root.right);
		return head;
	}

	TreeNode bTreeToClist(TreeNode root) {
		prev = null;
		TreeNode head = bTreeToClistUtil(root);
		head.left = prev;
		prev.right = head;
		return head;
	}

	public void connect(TreeNode p) {
		if (p == null) {
			return;
		}

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(p);
		while (!q.isEmpty()) {
			int size = q.size();
			TreeNode prev = null;
			for (int i = 0; i < size; i++) {
				TreeNode curr = q.poll();

				if (curr.left != null) {
					q.offer(curr.left);
				}

				if (curr.right != null) {
					q.offer(curr.right);
				}

				if (prev != null) {
					prev.nextRight = curr;
				}

				prev = curr;
			}
		}
	}

	static TreeNode myRoot;

	public void createNode(int arr[], int i, TreeNode[] created) {

		if (created[i] != null) {
			return;
		}
		TreeNode node = new TreeNode(i);
		created[i] = node;
		if (arr[i] == -1) {
			myRoot = node;
			return;
		}

		if (created[arr[i]] == null) {
			createNode(arr, arr[i], created);
		}

		TreeNode p = created[arr[i]];

		if (p.left == null) {
			p.left = node;
		}

		else if (p.right == null) {
			p.right = node;
		}
	}

	public TreeNode createTree(int arr[], int n) {
		TreeNode[] created = new TreeNode[n];
		TreeNode root = null;
		for (int i = 0; i < n; i++) {
			createNode(arr, i, created);
		}
		return root;
	}

	static int postIndex = 0;

	TreeNode buildTreeUtil(int in[], int inStart, int inEnd, int post[]) {

		if (inStart > inEnd) {
			return null;
		}

		int key = post[postIndex];
		postIndex--;

		TreeNode root = new TreeNode(key);

		int inIndex = -1;
		for (int i = inStart; i <= inEnd; i++) {
			if (in[i] == key) {
				inIndex = i;
				break;
			}
		}

		root.right = buildTreeUtil(in, inIndex + 1, inEnd, post);
		root.left = buildTreeUtil(in, inStart, inIndex - 1, post);
		return root;
	}

	TreeNode buildTree(int in[], int post[], int n) {

		postIndex = n - 1;
		TreeNode root = buildTreeUtil(in, 0, in.length - 1, post);
		return root;
	}

	void mirror(TreeNode node) {
		if (node == null) {
			return;
		}

		mirror(node.left);
		mirror(node.right);

		TreeNode t = node.left;
		node.left = node.right;
		node.right = t;
	}

	boolean isStructureSame(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}

		if (root1 != null && root2 != null && isStructureSame(root1.left, root2.left)
				&& isStructureSame(root1.right, root2.right)) {
			return true;
		}

		return false;
	}

	boolean isFoldable(TreeNode root) {
		if (root == null) {
			return true;
		}

		boolean res = false;
		mirror(root.left);
		res = isStructureSame(root.left, root.right);
		mirror(root.left);
		return res;
	}

	int Res = Integer.MIN_VALUE;

	int findMaxSum(TreeNode node) {
		if (node == null) {
			return 0;
		}

		int l = findMaxSum(node.left);
		int r = findMaxSum(node.right);

		int x = Math.max(Math.max(l, r) + node.data, node.data);

		int y = Math.max(x, l + r + node.data);
		Res = Math.max(Res, y);
		return x;
	}

	int MaxDiffBetweenNodeAndItsAncestor(TreeNode root) {

		if (root == null) {
			return Integer.MAX_VALUE;
		}

		if (root.left == null && root.right == null) {
			return root.data;
		}

		int l = MaxDiffBetweenNodeAndItsAncestor(root.left);
		int r = MaxDiffBetweenNodeAndItsAncestor(root.right);

		int min = Math.min(l, r);
		int diff = root.data - min;
		Res = Math.max(Res, diff);
		return Math.min(min, root.data);
	}

	int count = 0;

	int countSubtreesWithSumX(TreeNode root, int X) {
		if (root == null) {
			return 0;
		}

		if (root.left == null && root.right == null) {
			return root.data;
		}

		int l = countSubtreesWithSumX(root.left, X);
		int r = countSubtreesWithSumX(root.right, X);

		int sum = l + r + root.data;
		if (sum == X) {
			count++;
		}

		return sum;
	}

	public void serialize(TreeNode root, ArrayList<Integer> A) {
		if (root == null) {
			A.add(-1);
			return;
		}

		A.add(root.data);
		serialize(root.left, A);
		serialize(root.right, A);
	}

	int preIndex = 0;

	public TreeNode deSerialize(ArrayList<Integer> A) {

		if (preIndex == A.size()) {
			return null;
		}

		int key = A.get(preIndex);
		preIndex++;
		if (key == -1) {
			return null;
		}

		TreeNode root = new TreeNode(key);
		root.left = deSerialize(A);
		root.right = deSerialize(A);
		return root;
	}

	class Distance {
		public int val;

		public Distance(int d) {
			this.val = d;
		}
	}

	ArrayList<Integer> zigZagTraversal(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root != null) {
			Stack<TreeNode> s1 = new Stack<TreeNode>();
			Stack<TreeNode> s2 = new Stack<TreeNode>();
			s1.push(root);

			while (!s1.isEmpty() || !s2.isEmpty()) {
				while (!s1.isEmpty()) {
					TreeNode curr = s1.pop();
					res.add(curr.data);
					if (curr.left != null) {
						s2.push(curr.left);
					}

					if (curr.right != null) {
						s2.push(curr.right);
					}
				}

				while (!s2.isEmpty()) {
					TreeNode curr = s2.pop();
					res.add(curr.data);
					if (curr.right != null) {
						s1.push(curr.right);
					}

					if (curr.left != null) {
						s1.push(curr.left);
					}
				}
			}
		}
		return res;

	}
}
