package TreeProblems;

public class TreeNode {
	int data;
	public TreeNode left;
	public TreeNode right;
	public TreeNode nextRight;
	public int hd;
	public int lCount;

	public TreeNode(int d) {
		data = d;
		left = right = null;
		hd = 0;
		lCount = 0;
	}
}