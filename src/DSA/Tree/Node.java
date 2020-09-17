package TreeProblems;

public class Node {
	int data;
	public Node left;
	public Node right;
	public Node nextRight;
	public int hd;
	public int lCount;

	public Node(int d) {
		data = d;
		left = right = null;
		hd = 0;
		lCount = 0;
	}
}