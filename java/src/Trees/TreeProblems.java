package Trees;

import java.util.*;

import javax.swing.ImageIcon;

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
    public TreeNode buildTreeFromPreAndIn(int[] A, int[] B) {
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < B.length; i++) {
            indexMap.put(B[i], i);
        }

        return buildTreeFromPreAndInUtil(A, B, 0, A.length - 1, 0, B.length - 1, indexMap);
    }

    TreeNode buildTreeFromPreAndInUtil(int[] pre, int[] in, int spr, int epr, int sin, int ein,
            HashMap<Integer, Integer> indexMap) {

        // assumption: buildTreeUtil function will return the root of the sub-tree which can be created using
        // inOrder from sin to ein AND prePorder from spr tp epr
        if (spr > epr)
            return null;

        TreeNode root = new TreeNode(pre[spr]);

        int idx = indexMap.get(pre[spr]);

        int x = idx - sin;

        root.left = buildTreeFromPreAndInUtil(pre, in, spr+1, spr + x, sin, idx - 1, indexMap);

        root.right = buildTreeFromPreAndInUtil(pre, in, spr + x + 1, epr, idx + 1, ein, indexMap);

        return root;
    }

    /* Binary Tree From Inorder And Postorder
    Given the inorder and postorder traversal of a tree, construct the binary tree.

NOTE: You may assume that duplicates do not exist in the tree.



Problem Constraints
1 <= number of nodes <= 105



Input Format
First argument is an integer array A denoting the inorder traversal of the tree.

Second argument is an integer array B denoting the postorder traversal of the tree.



Output Format
Return the root node of the binary tree.



Example Input
Input 1:

 A = [2, 1, 3]
 B = [2, 3, 1]
Input 2:

 A = [6, 1, 3, 2]
 B = [6, 3, 2, 1]


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
    public TreeNode buildTreeFromPostAndInUtil(int[] in, int[] post, int sin, int ein, int spo, int epo,
            HashMap<Integer, Integer> indexMap) {
        // assumption: buildTreeUtil function returns root of tree created by
        // inOrder array from sin to ein and
        // postOrder array from spo to epo

        if (spo > epo)
            return null;

        TreeNode root = new TreeNode(post[epo]);

        int idx = indexMap.get(post[epo]);

        int x = ein - idx;

        root.left = buildTreeFromPostAndInUtil(in, post, sin, idx - 1, spo, epo - x - 1, indexMap);
        root.right = buildTreeFromPostAndInUtil(in, post, idx + 1, ein, epo - x, epo - 1, indexMap);

        return root;
    }

    public TreeNode buildTreeFromPostAndIn(int[] A, int[] B) {
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            indexMap.put(A[i], i);
        }

        return buildTreeFromPostAndInUtil(A, B, 0, A.length - 1, 0, B.length - 1, indexMap);
    }

    /* Depth of Each Node
    You are given the root node of a binary tree A. Each node has a value val and depth depth.

Initially depth of each node is set to -1. You have to fill the depth of each node with its correct value.
Depth of a node T is the number of nodes along the longest path from the root node down to node T. Also, depth of root node is always equal to 1.



Problem Constraints

1 <= Number of nodes <= 105

0 <= Value of each node <= 109

Initially, Depth of each node(depth) is set to -1.



Input Format

First and only argument is a tree node A.



Output Format

There is no output required.



Example Input

Input 1:

 
 Values =  1        Depths = -1
          / \                / \
         4   3             -1  -1                    
Input 2:

 
 Values =  1        Depths = -1
          / \                / \
         4   3             -1  -1                   
        /                  /
       2                 -1                           


Example Output

Output 1:

 
 Values =  1        Depths =  1
          / \                / \
         4   3              2   2                    
Output 2:

 
 Values =  1        Depths =  1
          / \                / \
         4   3              2   2                   
        /                  /
       2                  3                           


Example Explanation

Explanation 1:

 Depth of node having value 1 = 1 (root node)
 Depth of node having value 4 = 2
 Depth of node having value 3 = 2
Explanation 2:

 Depth of node having value 1 = 1 (root node)
 Depth of node having value 4 = 2
 Depth of node having value 3 = 2
 Depth of node having value 2 = 3
    */
    public void fillDepthUtil(TreeNodeWithDepth root, TreeNodeWithDepth parent)
    {
        if(root == null) return;

        root.depth = parent == null ? 1 : parent.depth + 1;
        fillDepthUtil((TreeNodeWithDepth)root.left, root);
        fillDepthUtil((TreeNodeWithDepth)root.right, root);
    }
    public void fillDepth(TreeNodeWithDepth root)
    {
        fillDepthUtil(root, null);
    }

    /* InOrder Traversal Iterative
    Given a binary tree, return the inorder traversal of its nodes' values.

NOTE: Using recursion is not allowed.



Problem Constraints
1 <= number of nodes <= 105



Input Format
First and only argument is root node of the binary tree, A.



Output Format
Return an integer array denoting the inorder traversal of the given binary tree.



Example Input
Input 1:

   1
    \
     2
    /
   3
Input 2:

   1
  / \
 6   2
    /
   3


Example Output
Output 1:

 [1, 3, 2]
Output 2:

 [6, 1, 3, 2]


Example Explanation
Explanation 1:

 The Inorder Traversal of the given tree is [1, 3, 2].
Explanation 2:

 The Inorder Traversal of the given tree is [6, 1, 3, 2].
    */
    public ArrayList<Integer> inorderTraversal(TreeNode A) {
        TreeNode root = A;

        ArrayList<Integer> res = new ArrayList<Integer>();

        Stack<TreeNode> st = new Stack<TreeNode>();

        while (root != null || !st.isEmpty()) {
            while (root != null) {
                st.push(root);

                root = root.left;
            }

            root = st.pop();

            res.add(root.val);

            root = root.right;
        }

        return res;

    }

    /* Preorder Traversal Iterative
    */
    public ArrayList<Integer> preorderTraversal(TreeNode A) {

        ArrayList<Integer> res = new ArrayList<Integer>();

        Stack<TreeNode> st = new Stack<TreeNode>();
        st.push(A);

        while (!st.isEmpty()) {
            TreeNode node = st.pop();

            res.add(node.val);

            if (node.right != null) {
                st.push(node.right);
            }

            if (node.left != null) {
                st.push(node.left);
            }
        }

        return res;
    }

    /* Identical Binary Trees
    Given two binary trees, check if they are equal or not.

    Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
    */
    public boolean isIdentical(TreeNode A, TreeNode B)
    {
        if(A == null && B == null) return true;
        if(A == null || B == null) return false;

        return A.val == B.val && isIdentical(A.left, B.left) && isIdentical(A.right, B.right);
    }

    /* Odd and Even Levels
    Given a binary tree of integers. Find the difference between the sum of nodes at odd level and sum of nodes at even level.

NOTE: Consider the level of root node as 1.



Problem Constraints

1 <= Number of nodes in binary tree <= 100000

0 <= node values <= 109



Input Format

First and only argument is a root node of the binary tree, A



Output Format

Return an integer denoting the difference between the sum of nodes at odd level and sum of nodes at even level.



Example Input

Input 1:

        1
      /   \
     2     3
    / \   / \
   4   5 6   7
  /
 8 
Input 2:

        1
       / \
      2   10
       \
        4


Example Output

Output 1:

 10
Output 2:

 -7


Example Explanation

Explanation 1:

 Sum of nodes at odd level = 23
 Sum of ndoes at even level = 13
Explanation 2:

 Sum of nodes at odd level = 5
 Sum of ndoes at even level = 12


See Expected Output
    */
    public int solve(TreeNode A) {
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.offer(A);

        boolean isOddLevel = true;
        int sumOdd = 0, sumEven = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (isOddLevel) {
                    sumOdd += node.val;

                } else {
                    sumEven += node.val;
                }

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }
            }

            isOddLevel = !isOddLevel;
        }

        return sumOdd - sumEven;
    }
}