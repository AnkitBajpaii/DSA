package Trees;
import java.util.*;

class BSTTreeInfo {
    public boolean isBST;
    public int min;
    public int max;

    public BSTTreeInfo(boolean isBST, int min, int max)
    {
        this.isBST = isBST;
        this.min = min;
        this.max = max;
    }
}

class BSTTreeInfoWithSize extends BSTTreeInfo {
    public int size;
    public BSTTreeInfoWithSize(boolean isBST, int min, int max, int size)
    {
        super(isBST, min, max);
        this.size = size;
    }
}

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

        root.left = Delete(root.left, predecessor.val);

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

    /* Floor and Ceil in BST
    Given a Binary Search Tree rooted at A.

Given an integer array B of size N. Find the floor and ceil of every element of the array B.

Floor(X) is the highest element in the tree <= X, while the ceil(X) is the lowest element in the tree >= X.

NOTE: If floor or ceil of any element of B doesn't exists, output -1 for the value which doesn't exists.



Problem Constraints

0 <= Number of nodes in the tree <= 1000000
0 <= node values <= 109
0 <= N <= 100000
0 <= B[i] <= 109



Input Format

First argument represents the root of binary tree A.
Second argument is an integer array B.



Output Format

Return an integer array C of size N*2. C[i][0] denotes the floor value of B[i] and C[i][1] represents the ceil value of B[i] in the given tree.



Example Input

Input 1:

Given Tree A:
           10
         /    \
        4      15
       / \
      1   8
B = [4, 19]
Input 2:

Given Tree A:
            8
          /   \
         5     19
        / \     \
       4   7     100
B = [1, 11]       


Example Output

Output 1:

[
    [4, 4]
    [15, -1]
]
Output 2:

[
    [-1, 4]
    [8, 19]
]


Example Explanation

Explanation 1:

Take all elements of given tree in sorted form: [1, 4, 8, 10, 15].
4 is present in the tree. So, for B[0] = 4, output is [4, 4] as both floor and ceil value is 4.

For B[1] = 19,
Highest element <= 19 is 15. So the floor value of 19 is 15. 
19 is greater than all elements in the tree. So, the ceil value of 19 doesn't exists.
So, output is [15, -1].
Explanation 2:

All elements of tree in sorted form: [4, 5, 7, 8, 19, 100].

For B[0] = 1, 
There is no element in the tree <= 1. So, the floor value doesn't exists.
Lowest element >= 1 is 4. So, the ceil value is 4.
So, output is [-1, 4]

Similarily for B[1] = 11, output is [8, 19].
    */
    TreeNode floor(TreeNode root, int x) {
        if (root == null)
            return null;

        if (root.val == x)
            return root;

        if (root.val > x)
            return floor(root.left, x);

        TreeNode possibleAns = floor(root.right, x);
        if (possibleAns != null)
            return possibleAns;

        return root;
    }

    TreeNode ceil(TreeNode root, int x) {
        if (root == null)
            return null;

        if (root.val == x)
            return root;

        if (root.val < x)
            return ceil(root.right, x);

        TreeNode possibleAns = ceil(root.left, x);
        if (possibleAns != null)
            return possibleAns;

        return root;
    }

    public ArrayList<ArrayList<Integer>> FindFloorAndCeilForAllElements(TreeNode A, ArrayList<Integer> B) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        
        for (int x : B) {
            TreeNode f = floor(A, x);
            TreeNode c = ceil(A, x);

            ArrayList<Integer> al = new ArrayList<Integer>();
            al.add(f == null ? -1 : f.val);
            al.add(c == null ? -1 : c.val);

            res.add(al);
        }

        return res;
    }

    /* Valid Binary Search Tree
    Problem Description
You are given a binary tree represented by root A.

Assume a BST is defined as follows:

1) The left subtree of a node contains only nodes with keys less than the node's key.

2) The right subtree of a node contains only nodes with keys greater than the node's key.

3) Both the left and right subtrees must also be binary search trees.



Problem Constraints
1 <= Number of nodes in binary tree <= 105

0 <= node values <= 109



Input Format
First and only argument is head of the binary tree A.



Output Format
Return 0 if false and 1 if true.



Example Input
Input 1:

 
   1
  /  \
 2    3
Input 2:

 
  2
 / \
1   3


Example Output
Output 1:

 0
Output 2:

 1


Example Explanation
Explanation 1:

 2 is not less than 1 but is in left subtree of 1.
Explanation 2:

Satisfies all conditions.
    */
    public boolean isValidBST(TreeNode root, int l, int h) {
        if (root == null)
            return true;

        return root.val >= l && root.val <= h && isValidBST(root.left, l, root.val - 1)
                && isValidBST(root.right, root.val + 1, h);
    }

    public int isValidBST(TreeNode A) {
        return isValidBST(A, Integer.MIN_VALUE, Integer.MAX_VALUE) ? 1 : 0;
    }

    public boolean isValidBSTPostOrder(TreeNode root) {
        BSTTreeInfo treeInfo = isValidBSTPostOrderUtil(root);

        return treeInfo.isBST;
    }

    public BSTTreeInfo isValidBSTPostOrderUtil(TreeNode root) {
        if (root == null) {
            return new BSTTreeInfo(true, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        BSTTreeInfo left = isValidBSTPostOrderUtil(root.left);

        BSTTreeInfo right = isValidBSTPostOrderUtil(root.right);

        if (left.isBST && right.isBST && root.val > left.max && root.val < right.min) {
            return new BSTTreeInfo(true, Math.min(root.val, left.min), Math.max(root.val, right.max));
        }

        return new BSTTreeInfo(false, Math.min(root.val, left.min), Math.max(root.val, right.max));
    }

    // Given a Binary tree, find max size BST.
    public BSTTreeInfoWithSize maxSizeBSTUtil(TreeNode root) {
        if (root == null) {
            return new BSTTreeInfoWithSize(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        BSTTreeInfoWithSize left = maxSizeBSTUtil(root.left);

        BSTTreeInfoWithSize right = maxSizeBSTUtil(root.right);

        if (left.isBST && right.isBST && root.val > left.max && root.val < right.min) {
            return new BSTTreeInfoWithSize(true, Math.min(root.val, left.min), Math.max(root.val, right.max),
                    1 + left.size + right.size);
        }

        return new BSTTreeInfoWithSize(false, Math.min(Math.min(root.val, left.min), right.min),
                Math.max(Math.max(root.val, right.max), left.max), Math.max(left.size, right.size));
    }

    /* Recover Binary Search Tree
    Problem Description
Two elements of a binary search tree (BST), represented by root A are swapped by mistake. Tell us the 2 values swapping which the tree will be restored.

A solution using O(n) space is pretty straightforward. Could you devise a constant space solution?



Problem Constraints
1 <= size of tree <= 100000



Input Format
First and only argument is the head of the tree,A



Output Format
Return the 2 elements which need to be swapped.



Example Input
Input 1:

 
         1
        / \
       2   3
Input 2:

 
         2
        / \
       3   1



Example Output
Output 1:

 [2, 1]
Output 2:

 [3, 1]


Example Explanation
Explanation 1:

Swapping 1 and 2 will change the BST to be 
         2
        / \
       1   3
which is a valid BST 
Explanation 2:

Swapping 1 and 3 will change the BST to be 
         2
        / \
       1   3
which is a valid BST 
    */
}
class RecoverBSTSolution {
    TreeNode first;
    TreeNode second;
    TreeNode prev;

    public void fixBST(TreeNode root)
    {
        if(root == null) return;

        fixBST(root.left);

        if(prev != null)
        {
            if(root.val < prev.val)
            {
                if(first == null)
                {
                    first = prev;
                }

                second = root;
            }
        }

        prev = root;

        fixBST(root.right);
    }

    public ArrayList<Integer> recoverTree(TreeNode A) {
        fixBST(A);

        ArrayList<Integer> res = new ArrayList<Integer>();

        res.add(second.val);
        res.add(first.val);        

        return res;
    }

    /*  Kth Smallest Element In BST
    Given a binary search tree represented by root A, write a function to find the Bth smallest element in the tree.



Problem Constraints
1 <= Number of nodes in binary tree <= 100000

0 <= node values <= 10^9



Input Format
First and only argument is head of the binary tree A.



Output Format
Return an integer, representing the Bth element.



Example Input
Input 1:

 
            2
          /   \
         1    3
B = 2
Input 2:

 
            3
           /
          2
         /
        1
B = 1



Example Output
Output 1:

 2
Output 2:

 1


Example Explanation
Explanation 1:

2nd element is 2.
Explanation 2:

1st element is 1.
    */
    public class KthSmallestInBST {
        class Counter {
            public int count;
        }
    
        public Integer KthSmallestNode(TreeNode root, int B, Counter C)
        {        
            if(root == null) return null;
    
            Integer left = KthSmallestNode(root.left, B, C);
            if(left != null) return left;
    
            C.count++;
            if(C.count == B) return root.val;
    
            return KthSmallestNode(root.right, B, C);
        }
    
        public int kthsmallest(TreeNode A, int B) {
            
            return KthSmallestNode(A, B, new Counter());        
        }
    }

    /* Least Common Ancestor
    Find the lowest common ancestor in an unordered binary tree A, given two values, B and C, in the tree.

Lowest common ancestor: the lowest common ancestor (LCA) of two nodes and w in a tree or directed acyclic graph (DAG) is the lowest (i.e., deepest) node that has both v and w as descendants.



Problem Constraints
1 <= size of tree <= 100000

1 <= B, C <= 109



Input Format
First argument is head of tree A.

Second argument is integer B.

Third argument is integer C.



Output Format
Return the LCA.



Example Input
Input 1:

 
      1
     /  \
    2    3
B = 2
C = 3
Input 2:

      1
     /  \
    2    3
   / \
  4   5
B = 4
C = 5


Example Output
Output 1:

 1
Output 2:

 2


Example Explanation
Explanation 1:

 LCA is 1.
Explanation 2:

 LCA is 2.
    */
    public class LeastCommonAncestorSolution {
    
        public TreeNode LeastCommonAncestor(TreeNode root, int B, int C) {
           if(root == null) return null;
   
           if(root.val == B || root.val == C){            
                return root;
           }
           
           TreeNode left = LeastCommonAncestor(root.left, B, C);
           TreeNode right = LeastCommonAncestor(root.right, B, C);
   
           if(left != null && right != null){            
               return root;
           }
   
           if(left != null) return left;
   
           return right;
       }
   
       boolean find(TreeNode root, int val) {
           if (root == null)
               return false;
           if (root.val == val)
               return true;
           return (find(root.left, val) || find(root.right, val));
       }
   
       public int lca(TreeNode A, int B, int C) {
           if(find(A, B) == false || find(A, C) == false) return -1 ;
           
          TreeNode lca = LeastCommonAncestor(A, B, C);
   
          return lca != null ? lca.val : -1;
       }}

       /* Check for BST with One Child
       Problem Description

Given preorder traversal of a binary tree, check if it is possible that it is also a preorder traversal of a Binary Search Tree (BST), where each internal node (non-leaf nodes) have exactly one child.



Problem Constraints

1 <= number of nodes <= 100000



Input Format

First and only argument is an integer array denoting the preorder traversal of binary tree.



Output Format

Return a string "YES" if true else "NO".



Example Input

Input 1:

 A : [4, 10, 5, 8]
Input 2:

 A : [1, 5, 6, 4]


Example Output

Output 1:

 "YES"
Output 2:

 "NO"


Example Explanation

Explanation 1:

 The possible BST is:
            4
             \
             10
             /
             5
              \
              8
Explanation 2:

 There is no possible BST which have the above preorder traversal.
       */
      public boolean checkBSTWithOneChild(int[] pre)
      {
          int L = Integer.MIN_VALUE;
          int R = Integer.MAX_VALUE;
  
          int root = pre[0];
          for(int i=1; i<pre.length; i++)
          {
              if(pre[i] > root)
              {
                  L = root;
              } else {
                  R = root;
              }
  
              if(pre[i] < L || pre[i] > R) return false;
  
              root = pre[i];
          }
  
          return true;
      }

      /* Common Nodes in Two BST
      Problem Description

Given two BST's A and B, return the (sum of all common nodes in both A and B) % (109 +7) .

In case there is no common node, return 0.

NOTE:

Try to do it one pass through the trees.



Problem Constraints

1 <= Number of nodes in the tree A and B <= 105

1 <= Node values <= 106



Input Format

First argument represents the root of BST A.

Second argument represents the root of BST B.



Output Format

Return an integer denoting the (sum of all common nodes in both BST's A and B) % (109 +7) .



Example Input

Input 1:

 Tree A:
    5
   / \
  2   8
   \   \
    3   15
        /
        9

 Tree B:
    7
   / \
  1  10
   \   \
    2  15
       /
      11
Input 2:

  Tree A:
    7
   / \
  1   10
   \   \
    2   15
        /
       11

 Tree B:
    7
   / \
  1  10
   \   \
    2  15
       /
      11


Example Output

Output 1:

 17
Output 2:

 46


Example Explanation

Explanation 1:

 Common Nodes are : 2, 15
 So answer is 2 + 15 = 17
Explanation 2:

 Common Nodes are : 7, 2, 1, 10, 15, 11
 So answer is 7 + 2 + 1 + 10 + 15 + 11 = 46
      */
      class CommonNodesInTwoBSTSolution {
        void buildFreqMap(TreeNode root, HashMap<Integer, Integer> map)
        {
            if(root == null)
            {
                return;
            }
    
            map.put(root.val, map.getOrDefault(root.val, 0) + 1 );
    
            buildFreqMap(root.left, map);
    
            buildFreqMap(root.right, map);
        }
    
        long getSumOfCommon(TreeNode root, HashMap<Integer, Integer> map)
        {
            if(root == null) return 0;
    
            long leftSum = getSumOfCommon(root.left, map);
    
            long rightSum = getSumOfCommon(root.right, map);
    
            long sum = leftSum + rightSum;
    
            if(map.containsKey(root.val))
            {
                sum = sum + root.val;
                map.put(root.val, map.get(root.val) - 1);
    
                if(map.get(root.val) == 0)
                {
                    map.remove(root.val);
                }
            }
    
            return sum;
        }
    
        public int sumOfCommonNodesInBSTUsingHashMap(TreeNode A, TreeNode B) {
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    
            buildFreqMap(A, map);
    
            long sum = getSumOfCommon(B, map);
    
            int mod = 1000 * 1000 * 1000 + 7;
    
            return (int)(sum % mod);
        }
    }

    // Find all common nodes in Two BST
    ArrayList<Integer> commonNotesInTwoBST(TreeNode root1, TreeNode root2)
    {
        TreeNode curr1 = root1, curr2 = root2;
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();

        ArrayList<Integer> ans = new ArrayList<Integer>();

        while((curr1 != null || !s1.isEmpty()) && (curr2 != null || !s2.isEmpty()))
        {
            while(curr1 != null)
            {
                s1.push(curr1);
                curr1 = curr1.left;
            }

            while(curr2 != null)
            {
                s2.push(curr2);
                curr2 = curr2.left;
            }

            curr1 = s1.peek();
            curr2 = s2.peek();

            if(curr1.val == curr2.val)
            {
                ans.add(curr1.val);

                s1.pop();
                s2.pop();

                curr1 = curr1.right;
                curr2 = curr2.right;
            } else if(curr1.val < curr2.val)
            {
                curr1 = curr1.right;
                s1.pop();
                curr2 = null;

            } else // if(curr1.val > curr2.val)
            {
                curr2 = curr2.right;
                s2.pop();
                curr1 = null;
            }
        }

        return ans;
    }

    //Sum of all common nodes in BST Optimized solution using Preorder traversal iterative
    long sumOfCommonNodesInTwoBST(TreeNode root1, TreeNode root2)
    {
        TreeNode curr1 = root1, curr2 = root2;
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();

        long ans = 0;

        while((curr1 != null || !s1.isEmpty()) && (curr2 != null || !s2.isEmpty()))
        {
            while(curr1 != null)
            {
                s1.push(curr1);
                curr1 = curr1.left;
            }

            while(curr2 != null)
            {
                s2.push(curr2);
                curr2 = curr2.left;
            }

            curr1 = s1.peek();
            curr2 = s2.peek();

            if(curr1.val == curr2.val)
            {
                ans = ans + curr1.val;
                s1.pop();
                s2.pop();

                curr1 = curr1.right;
                curr2 = curr2.right;
            } else if(curr1.val < curr2.val)
            {
                curr1 = curr1.right;
                s1.pop();
                curr2 = null;

            } else // if(curr1.val > curr2.val)
            {
                curr2 = curr2.right;
                s2.pop();
                curr1 = null;
            }
        }

        return ans;
    }
}
