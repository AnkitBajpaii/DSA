package Trees;

public class BinarySearchTreeProblems {

    public TreeNode Insert(TreeNode root, int k) {
        if (root == null)
            return new TreeNode(k);

        if (k <= root.val) {
            root.left = Insert(root.left, k);
        } else {
            root.right = Insert(root.right, k);
        }

        return root;
    }

    public TreeNode Search(TreeNode root, int k) {
        if (root == null || k == root.val)
            return root;

        if (k < root.val) {
            return Search(root.left, k);
        }
        
        return Search(root.right, k);
    }

    public TreeNode Delete(TreeNode root, int k) {
        if (root == null)
            return null;
            
        if(k < root.val)
        {
            return Delete(root.left, k);
        }

        if(k > root.val)
        {
            return Delete(root.right, k);
        }

        if(root.left == null) return root.right;

        if(root.right == null) return root.left;

        /*find max on left or min on right . Lets find max on left*/
        TreeNode predecessor = root.left;
        while(predecessor.right != null)
        {
            predecessor = predecessor.right;
        }

        root.val = predecessor.val;

        root.left = Delete(root.left, k);

        return root;
    }

    // Trim Binary Tree. Delete nodes outside of given range
    public TreeNode TrimBST(TreeNode root, int l, int h) {
        if (root == null)
            return null;

        if (root.val < l)
            return TrimBST(root.right, l, h);

        if (root.val > h)
            return TrimBST(root.left, l, h);

        root.left = TrimBST(root.left, l, h);
        root.right = TrimBST(root.right, l, h);

        return root;
    }

    /* Sorted Array To Balanced BST
    Given an array where elements are sorted in ascending order, convert it to a height Balanced Binary Search Tree (BBST).

Balanced tree : a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.



Problem Constraints

1 <= length of array <= 100000



Input Format

First argument is an integer array A.



Output Format

Return a root node of the Binary Search Tree.



Example Input

Input 1:

 A : [1, 2, 3]
Input 2:

 A : [1, 2, 3, 5, 10]


Example Output

Output 1:

      2
    /   \
   1     3
Output 2:

      3
    /   \
   2     5
  /       \
 1         10


Example Explanation

Explanation 1:

 You need to return the root node of the Binary Tree.

    */
    public TreeNode sortedArrayToBSTUtil(int[] A, int start, int end) {
        // assumption: sortedArrayToBSTUtil function will return root of binary search
        // tree which
        // can be formed by sorted array A, from start to end

        if (start > end)
            return null;

        int mid = start + (end - start) / 2;

        TreeNode root = new TreeNode(A[mid]);

        root.left = sortedArrayToBSTUtil(A, start, mid - 1);
        root.right = sortedArrayToBSTUtil(A, mid + 1, end);

        return root;
    }

    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public TreeNode sortedArrayToBST(final int[] A) {
        return sortedArrayToBSTUtil(A, 0, A.length - 1);
    }

    /* BST nodes in a range
    Given a binary search tree of integers. You are given a range B and C.

Return the count of the number of nodes that lie in the given range.



Problem Constraints
1 <= Number of nodes in binary tree <= 100000

0 <= B < = C <= 109



Input Format
First argument is a root node of the binary tree, A.

Second argument is an integer B.

Third argument is an integer C.



Output Format
Return the count of the number of nodes that lies in the given range.



Example Input
Input 1:

            15
          /    \
        12      20
        / \    /  \
       10  14  16  27
      /
     8

     B = 12
     C = 20
Input 2:

            8
           / \
          6  21
         / \
        1   7

     B = 2
     C = 20


Example Output
Output 1:

 5
Output 2:

 3


Example Explanation
Explanation 1:

 Nodes which are in range [12, 20] are : [12, 14, 15, 20, 16]
Explanation 2:

 Nodes which are in range [2, 20] are : [8, 6, 7]
    */
    public int countNodesInGivenRange(TreeNode root, int l, int h) {
        // assumption: countNodesInGivenRange function will return number of nodes in
        // given range l to h for tree rooted at "root"

        if (root == null)
            return 0;

        if (root.val < l) {
            return countNodesInGivenRange(root.right, l, h);
        }

        if (root.val > h) {
            return countNodesInGivenRange(root.left, l, h);
        }

        return 1 + countNodesInGivenRange(root.left, l, h) + countNodesInGivenRange(root.right, l, h);

    }

}
