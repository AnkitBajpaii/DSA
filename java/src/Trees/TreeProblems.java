package Trees;

import java.util.HashMap;

public class TreeProblems {

    /*
     * Post Order Traversal: Bottom to Top, When you want to visit left subtree
     * first and then right sub tree
     * Pre Order Traversal: Top to Bottom, used in Fail-Fast approach
     */
    public int Height(TreeNode root) {
        // we will do Post Order Traversal
        if (root == null)
            return -1;

        return Math.max(Height(root.left), Height(root.right)) + 1;
    }

    /* You are given the root node of a binary tree A. You have to find the number of nodes in this tree.
    */
    public int CountNodes(TreeNode root) // Post order Traversal
    {
        // assumption: countNodes will return number of nodes in tree or sub-tree, whose root node is root.
        if(root == null) return 0;

        return CountNodes(root.left) + CountNodes(root.right) + 1;
    }

    /* You are given the root node of a binary tree A. You have to find the sum of node values of this tree.
    */
    public int NodesSum(TreeNode root)
    {
        // assumption: NodesSum function will return sum of value of all the nodes in tree or sub-tree whose root node is root

        if(root == null) return 0;

        return NodesSum(root.left) + NodesSum(root.right) + root.val;
    }

    /* You are given the root node of a binary tree A. You have to find the max value of all node values of this tree.
    */
    public int nodesMax(TreeNode root)
    {
        if(root == null) return Integer.MIN_VALUE;

        //assumption: nodesMax function will return max value of all nodes in this tree or sub-tree
        return Math.max(root.val, Math.max(nodesMax(root.left), nodesMax(root.right)));
    }

    public boolean Search(TreeNode root, int k) {
        // we will do Pre Order Traversal
        if (root == null)
            return false;

        if (root.val == k)
            return true;

        return Search(root.left, k) || Search(root.right, k);
    }

    /* Binary Tree From Inorder And Preorder
    Given preorder and inorder traversal of a tree, construct the binary tree.

NOTE: You may assume that duplicates do not exist in the tree.



Problem Constraints

1 <= number of nodes <= 105



Input Format

First argument is an integer array A denoting the preorder traversal of the tree.

Second argument is an integer array B denoting the inorder traversal of the tree.



Output Format

Return the root node of the binary tree.



Example Input

Input 1:

 A = [1, 2, 3]
 B = [2, 1, 3]
Input 2:

 A = [1, 6, 2, 3]
 B = [6, 1, 3, 2]


Example Output

Output 1:

   1
  / \
 2   3
Output 2:

   1  
  / \
 6   2
    /
   3


Example Explanation

Explanation 1:

 Create the binary tree and return the root node of the tree.
*/
    public TreeNode buildTree(int[] A, int[] B) {
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < B.length; i++) {
            indexMap.put(B[i], i);
        }

        return buildTreeUtil(A, B, 0, A.length - 1, 0, B.length - 1, indexMap);
    }

    TreeNode buildTreeUtil(int[] pre, int[] in, int spr, int epr, int sin, int ein,
            HashMap<Integer, Integer> indexMap) {

        // assumption: buildTreeUtil function will build the tree with given preorder
        // and inorder travelsal for given preOder and inOrder sub-array
        if (spr > epr)
            return null;

        TreeNode root = new TreeNode(pre[spr]);

        int idx = indexMap.get(pre[spr]);

        int x = idx - sin;

        root.left = buildTreeUtil(pre, in, spr+1, spr + x, sin, idx - 1, indexMap);

        root.right = buildTreeUtil(pre, in, spr + x + 1, epr, idx + 1, ein, indexMap);

        return root;
    }
}