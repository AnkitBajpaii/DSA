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

    // Morris Traversal - In order traversal of Binary tree without using Extra space
    public void MorrisTraversal(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                System.out.print(curr.val + " ");
                curr = curr.right;
            } else {
                TreeNode tmp = curr.left;
                while (tmp.right != null && tmp.right != curr) {
                    tmp = tmp.right;
                }

                if (tmp.right == null) {
                    tmp.right = curr;
                    curr = curr.left;
                } else {
                    tmp.right = null;

                    System.out.print(curr.val + " ");

                    curr = curr.right;
                }
            }
        }
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

    // Iterative Post Order Traversal
    public ArrayList<Integer> postorderTraversalIterativeUsingTwoStacks(TreeNode A) {
        ArrayList<Integer> res = new ArrayList<Integer>();

        Stack<TreeNode> S1 = new Stack<TreeNode>();
        Stack<TreeNode> S2 = new Stack<TreeNode>();
        TreeNode curr = A;

        S1.push(curr);
        while (!S1.isEmpty()) {
            TreeNode temp = S1.pop();
            S2.push(temp);

            if (temp.left != null) {
                S1.push(temp.left);
            }

            if (temp.right != null) {
                S1.push(temp.right);
            }
        }

        while (!S2.isEmpty()) {
            res.add(S2.pop().val);
        }

        return res;
    }

    public ArrayList<Integer> OptimizedPostorderTraversalIterativeUsingOneStack(TreeNode A) {
        //Post Order - Left Right Root
        // Lets reverse this, and you will get , Root Right Left
        // This is similar to Pre-Order algo - Root Left Right, except we need to reverse ordering of pushing left and right sub tree
        // in the end you get reverse of post order, so finally reverse the answer
        ArrayList<Integer> res = new ArrayList<Integer>();

        Stack<TreeNode> st = new Stack<TreeNode>();
        
        TreeNode curr = A;

        st.push(curr);

        while (!st.isEmpty()) {
            curr = st.pop();

            res.add(curr.val);

            if(curr.left != null)
            {
                st.push(curr.left);
            }

            if(curr.right != null)
            {
                st.push(curr.right);
            }
        }

        Collections.reverse(res);

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
    public int sumOfNodesAtOddAndEvenLevel(TreeNode A) {
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

    /* Level Order Traversal
        Given a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
    */
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();

        q.offer(A);

        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        while (!q.isEmpty()) {
            int size = q.size();

            ArrayList<Integer> al = new ArrayList<Integer>();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                al.add(node.val);

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }
            }

            res.add(al);
        }

        return res;
    }

    /* Vertical Order traversal
    Given a binary tree, return a 2-D array with vertical order traversal of it. Go through the example and image for more details.


NOTE: If 2 Tree Nodes shares the same vertical level then the one with lesser depth will come first.



Problem Constraints
0 <= number of nodes <= 105



Input Format
First and only arument is a pointer to the root node of binary tree, A.



Output Format
Return a 2D array denoting the vertical order traversal of tree as shown.



Example Input
Input 1:

      6
    /   \
   3     7
  / \     \
 2   5     9
Input 2:

      1
    /   \
   3     7
  /       \
 2         9


Example Output
Output 1:

 [
    [2],
    [3],
    [6, 5],
    [7],
    [9]
 ]
Output 2:

 [
    [2],
    [3],
    [1],
    [7],
    [9]
 ]


Example Explanation
Explanation 1:

 First row represent the verical line 1 and so on.



See Expected Output

    */

    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode A) {

        class TreeInfo {
            public int hd;
            public TreeNode node;

            public TreeInfo(TreeNode n, int hd) {
                this.node = n;
                this.hd = hd;
            }
        }

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();

        Queue<TreeInfo> q = new ArrayDeque<TreeInfo>();

        q.offer(new TreeInfo(A, 0));

        int minHd = 0, maxHd = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeInfo treeInfo = q.poll();
                minHd = Math.min(minHd, treeInfo.hd);
                maxHd = Math.max(maxHd, treeInfo.hd);

                ArrayList<Integer> al = map.get(treeInfo.hd);
                if (al == null) {
                    al = new ArrayList<Integer>();
                }

                al.add(treeInfo.node.val);

                map.put(treeInfo.hd, al);

                if (treeInfo.node.left != null) {
                    q.offer(new TreeInfo(treeInfo.node.left, treeInfo.hd - 1));
                }

                if (treeInfo.node.right != null) {
                    q.offer(new TreeInfo(treeInfo.node.right, treeInfo.hd + 1));
                }
            }
        }

        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        for (int i = minHd; i <= maxHd; i++) {
            res.add(map.get(i));
        }

        return res;
    }

    /*  Left View of Binary tree
    Given a binary tree of integers. Return an array of integers representing the left view of the Binary tree.

Left view of a Binary Tree is a set of nodes visible when the tree is visited from Left side

NOTE: The value comes first in the array which have lower level.



Problem Constraints
1 <= Number of nodes in binary tree <= 100000

0 <= node values <= 109



Input Format
First and only argument is a root node of the binary tree, A.



Output Format
Return an integer array denoting the left view of the Binary tree.



Example Input
Input 1:

            1
          /   \
         2    3
        / \  / \
       4   5 6  7
      /
     8 
Input 2:

            1
           /  \
          2    3
           \
            4
             \
              5


Example Output
Output 1:

 [1, 2, 4, 8]
Output 2:

 [1, 2, 4, 5]


Example Explanation
Explanation 1:

 The Left view of the binary tree is returned.
    */
    public ArrayList<Integer> leftViewOfBinaryTree(TreeNode A) {

        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.offer(A);

        ArrayList<Integer> res = new ArrayList<Integer>();

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i == 0) {
                    res.add(node.val);
                }

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }

        return res;
    }

    /* Right View of Binary tree
    Given a binary tree of integers denoted by root A. Return an array of integers representing the right view of the Binary tree.

Right view of a Binary Tree is a set of nodes visible when the tree is visited from Right side.



Problem Constraints
1 <= Number of nodes in binary tree <= 100000

0 <= node values <= 10^9



Input Format
First and only argument is head of the binary tree A.



Output Format
Return an array, representing the right view of the binary tree.



Example Input
Input 1:

 
            1
          /   \
         2    3
        / \  / \
       4   5 6  7
      /
     8 
Input 2:

 
            1
           /  \
          2    3
           \
            4
             \
              5


Example Output
Output 1:

 [1, 3, 7, 8]
Output 2:

 [1, 3, 4, 5]


Example Explanation
Explanation 1:

Right view is described.
Explanation 2:

Right view is described.
    */
    public ArrayList<Integer> rightViewOfBinaryTree(TreeNode A) {
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.offer(A);

        ArrayList<Integer> res = new ArrayList<Integer>();

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i == size - 1) {
                    res.add(node.val);
                }

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }

        return res;
    }

    /*  Reverse Level Order
    Given a binary tree, return the reverse level order traversal of its nodes values. (i.e, from left to right and from last level to starting level).



Problem Constraints

1 <= number of nodes <= 5 * 105

1 <= node value <= 105



Input Format

First and only argument is a pointer to the root node of the Binary Tree, A.



Output Format

Return an integer array denoting the reverse level order traversal from left to right.



Example Input

Input 1:

    3
   / \
  9  20
    /  \
   15   7
Input 2:

   1
  / \
 6   2
    /
   3


Example Output

Output 1:

 [15, 7, 9, 20, 3] 
Output 2:

 [3, 6, 2, 1]


Example Explanation

Explanation 1:

 Nodes as level 3 : [15, 7]
 Nodes at level 2: [9, 20]
 Nodes at level 1: [3]
 Reverse level order traversal will be: [15, 7, 9, 20, 3]
Explanation 2:

 Nodes as level 3 : [3]
 Nodes at level 2: [6, 2]
 Nodes at level 1: [1]
 Reverse level order traversal will be: [3, 6, 2, 1]
    */
    public ArrayList<Integer> ReverseLevelOrder(TreeNode A) {

        Stack<Integer> st = new Stack<Integer>();

        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.offer(A);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                st.push(node.val);

                if (node.right != null) {
                    q.offer(node.right);
                }

                if (node.left != null) {
                    q.offer(node.left);
                }
            }
        }

        ArrayList<Integer> ans = new ArrayList<Integer>();

        while (!st.isEmpty()) {
            ans.add(st.pop());
        }
        return ans;
    }

    /*  ZigZag Level Order Traversal BT
    Given a binary tree, return the zigzag level order traversal of its nodes values. (ie, from left to right, then right to left for the next level and alternate between).



Problem Constraints
1 <= number of nodes <= 105



Input Format
First and only argument is root node of the binary tree, A.



Output Format
Return a 2D integer array denoting the zigzag level order traversal of the given binary tree.



Example Input
Input 1:

    3
   / \
  9  20
    /  \
   15   7
Input 2:

   1
  / \
 6   2
    /
   3


Example Output
Output 1:

 [
   [3],
   [20, 9],
   [15, 7]
 ]
Output 2:

 [ 
   [1]
   [2, 6]
   [3]
 ]


Example Explanation
Explanation 1:

 Return the 2D array. Each row denotes the zigzag traversal of each level.
    */
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode A) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.offer(A);
        boolean isOddLevel = true;

        while (!q.isEmpty()) {
            int size = q.size();

            ArrayList<Integer> al = new ArrayList<Integer>();

            Stack<Integer> st = new Stack<Integer>();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (!isOddLevel) {
                    st.push(node.val);
                } else {
                    al.add(node.val);
                }

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }
            }

            if (!isOddLevel) {
                while (!st.isEmpty()) {
                    al.add(st.pop());
                }
            }

            res.add(al);

            isOddLevel = !isOddLevel;
        }

        return res;
    }

    /* Sum binary tree or not
    Given a binary tree. Check whether the given tree is a Sum-binary Tree or not.

Sum-binary Tree is a Binary Tree where the value of a every node is equal to sum of the nodes present in its left subtree and right subtree.

An empty tree is Sum-binary Tree and sum of an empty tree can be considered as 0. A leaf node is also considered as SumTree.

Return 1 if it sum-binary tree else return 0.



Problem Constraints
1 <= length of the array <= 100000

0 <= node values <= 50



Input Format
The only argument given is the root node of tree A.



Output Format
Return 1 if it is sum-binary tree else return 0.



Example Input
Input 1:

       26
     /    \
    10     3
   /  \     \
  4   6      3
Input 2:

       26
     /    \
    10     3
   /  \     \
  4   6      4


Example Output
Output 1:

 1
Output 2:

 0


Example Explanation
Explanation 1:

 All leaf nodes are considered as SumTree. 
 Value of Node 10 = 4 + 6.
 Value of Node 3 = 0 + 3 
 Value of Node 26 = 20 + 6 
Explanation 2:

 Sum of left subtree and right subtree is 27 which is not equal to the value of root node which is 26.
    */
    public int isSumBinaryTree(TreeNode root) {
        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return root.val;

        int l = isSumBinaryTree(root.left);
        if (l == -1) {
            return -1;
        }

        int r = isSumBinaryTree(root.right);
        if (r == -1) {
            return -1;
        }

        int sum = l + r;
        if (root.val == sum)
            return sum + root.val;

        return -1;
    }

    /* Given two binary trees with root, root1 and root2. Check if they are symmetric
    2 trees, T1 and T2, are symmetric if
    1) value of T1’s root is the same as T2’s root
    2) T1’s left and T2’s right are symmetric.
    3) T2’s left and T1’s right are symmetric.
    */
    public boolean isSymmetric(TreeNode root1, TreeNode root2)
    {
        if(root1 == null && root2 == null) return true;

        if(root1 == null || root2 == null) return false;

        return root1.val == root2.val && isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
    }

    /* Symmetric Binary Tree
    Given a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
    */
    public int isSymmetric(TreeNode A) {
        if(isSymmetric(A, A)) return 1;

        return 0;
    }

    /* Invert the Binary Tree
    Given a binary tree A, invert the binary tree and return it.

    Inverting refers to making the left child the right child and vice versa.
    Problem Constraints
1 <= size of tree <= 100000



Input Format
First and only argument is the head of the tree A.



Output Format
Return the head of the inverted tree.



Example Input
Input 1:

 
     1
   /   \
  2     3
Input 2:

 
     1
   /   \
  2     3
 / \   / \
4   5 6   7


Example Output
Output 1:

 
     1
   /   \
  3     2
Output 2:

 
     1
   /   \
  3     2
 / \   / \
7   6 5   4


Example Explanation
Explanation 1:

Tree has been inverted.
Explanation 2:

Tree has been inverted.
    */
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        TreeNode l = invertTree(root.left);
        TreeNode r = invertTree(root.right);

        root.left = r;
        root.right = l;

        return root;
    }

    // checks if a node with given value K , is present on tree rooted at root node and also fills the ancestors
    public boolean isNodePresent(TreeNode root, int k, ArrayList<Integer> al)
    {
        if(root == null) return false;

        al.add(root.val);

        if(root.val == k) return true;

        boolean found = isNodePresent(root.left, k, al) || isNodePresent(root.right, k, al);
        if(found) return true;

        al.remove(al.size() - 1);

        return false;
    }

    public ArrayList<Integer> FindAncestorsOfANodeUtil(TreeNode root, int k) {
        ArrayList<Integer> al = new ArrayList<Integer>();

        isNodePresent(root, k, al);

        return al;
    }

    /* Diameter of binary tree
    Problem Description
Given a Binary Tree A consisting of N integer nodes, you need to find the diameter of the tree.

The diameter of a tree is the number of edges on the longest path between two nodes in the tree.



Problem Constraints
0 <= N <= 105



Input Format
First and only Argument represents the root of binary tree A.



Output Format
Return an single integer denoting the diameter of the tree.



Example Input
Input 1:

           1
         /   \
        2     3
       / \
      4   5
Input 2:

            1
          /   \
         2     3
        / \     \
       4   5     6


Example Output
Output 1:

 3
Output 2:

 4


Example Explanation
Explanation 1:

 Longest Path in the tree is 4 -> 2 -> 1 -> 3 and the number of edges in this path is 3 so diameter is 3.
Explanation 2:

 Longest Path in the tree is 4 -> 2 -> 1 -> 3 -> 6 and the number of edges in this path is 4 so diameter is 4.
    */
    public class DiameterOfTreeSolution {
        class TreeInfo {
            public int height;
            public int diameter;

            public TreeInfo(int h, int d) {
                this.height = h;
                this.diameter = d;
            }
        }

        TreeInfo Diameter(TreeNode root) {
            if (root == null) {
                return new TreeInfo(-1, -1);
            }

            TreeInfo left = Diameter(root.left);

            TreeInfo right = Diameter(root.right);

            int currentNodeHeight = Math.max(left.height, right.height) + 1;
            int currentNodeDiameter = Math.max(Math.max(left.diameter, right.diameter), left.height + right.height + 2);

            return new TreeInfo(currentNodeHeight, currentNodeDiameter);
        }

        public int SolveDiameterOfTree(TreeNode A) {
            TreeInfo treeInfo = Diameter(A);

            return treeInfo.diameter;
        }
    }

    /* Max Sum Path in Binary Tree
    Problem Description
Given a binary tree T, find the maximum path sum.

The path may start and end at any node in the tree.



Problem Constraints
1 <= Number of Nodes <= 7e4

-1000 <= Value of Node in T <= 1000



Input Format
The first and the only argument contains a pointer to the root of T, A.



Output Format
Return an integer representing the maximum sum path.



Example Input
Input 1:

  
    1
   / \
  2   3
Input 2:

       20
      /  \
   -10   20
        /  \
      -10  -50


Example Output
Output 1:

 6
Output 2:

 40


Example Explanation
Explanation 1:

     The path with maximum sum is: 2 -> 1 -> 3
Explanation 2:

     The path with maximum sum is: 20 -> 20
    */
    public class MaxPathSumSolution {

        class TreeInfo {
            public int maxPathSum;
            public int ans;

            public TreeInfo(int maxPathSum, int ans) {
                this.maxPathSum = maxPathSum;
                this.ans = ans;
            }
        }

        public TreeInfo maxSumPathUtil(TreeNode root) {
            if (root == null)
                return new TreeInfo(Integer.MIN_VALUE, Integer.MIN_VALUE);

            TreeInfo left = maxSumPathUtil(root.left);

            TreeInfo right = maxSumPathUtil(root.right);

            left.maxPathSum = Math.max(0, left.maxPathSum);

            right.maxPathSum = Math.max(0, right.maxPathSum);

            int ans = Math.max(Math.max(left.ans, right.ans), left.maxPathSum + right.maxPathSum + root.val);

            return new TreeInfo(Math.max(left.maxPathSum, right.maxPathSum) + root.val, ans);
        }

        public int maxPathSum(TreeNode A) {
            TreeInfo info = maxSumPathUtil(A);
            return info.ans;
        }
    }

    /* Boundary Traversal Of Binary Tree
    Given a binary tree. Given a binary tree, return the values of its boundary in anti-clockwise direction starting from the root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.

Left boundary is defined as the path from the root to the left-most node. Right boundary is defined as the path from the root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Return an array of integers denoting the boundary values of tree in anti-clockwise order.

For Example

Input 1:
               _____1_____
              /           \
             2             3
            / \            / 
           4   5          6   
              / \        / \
             7   8      9  10  
Output 1:
    [1, 2, 4, 7, 8, 9, 10, 6, 3]
    Explanation 1:
        The left boundary are node 1,2,4. (4 is the left-most node according to definition)
        The leaves are node 4,7,8,9,10.
        The right boundary are node 1,3,6,10. (10 is the right-most node).
        So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].

Input 2:
                1
               / \
              2   3
             / \  / \
            4   5 6  7
Output 2:
     [1, 2, 4, 5, 6, 7, 3] 
    */
    void GetLeftMost(TreeNode root, ArrayList<Integer> al) {
        TreeNode curr = root;

        while (curr != null) {
            al.add(curr.val);

            if (curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
    }

    void GetRightMost(TreeNode root, ArrayList<Integer> al) {
        TreeNode curr = root;
        Stack<Integer> st = new Stack<Integer>();

        while (curr != null) {
            st.push(curr.val);

            if (curr.right != null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        while (!st.isEmpty()) {
            al.add(st.pop());
        }
    }

    void GetLeafs(TreeNode root, ArrayList<Integer> al) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            al.add(root.val);
            return;
        }

        GetLeafs(root.left, al);

        GetLeafs(root.right, al);
    }

    public ArrayList<Integer> BoundaryTraversalOfBinaryTree(TreeNode A) {
        ArrayList<Integer> al = new ArrayList<Integer>();

        GetLeftMost(A, al);
        GetLeafs(A, al);
        GetRightMost(A, al);

        HashSet<Integer> set = new HashSet<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();

        for (int x : al) {
            if (set.contains(x))
                continue;

            res.add(x);
            set.add(x);
        }

        return res;
    }

    /* Path Sum
    Problem Description
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.



Problem Constraints
1 <= number of nodes <= 105

-100000 <= B, value of nodes <= 100000



Input Format
First argument is a root node of the binary tree, A.

Second argument is an integer B denoting the sum.



Output Format
Return 1, if there exist root-to-leaf path such that adding up all the values along the path equals the given sum. Else, return 0.



Example Input
Input 1:

 Tree:    5
         / \
        4   8
       /   / \
      11  13  4
     /  \      \
    7    2      1

 B = 22
Input 2:

 Tree:    5
         / \
        4   8
       /   / \
     -11 -13  4

 B = -1


Example Output
Output 1:

 1
Output 2:

 0


Example Explanation
Explanation 1:

 There exist a root-to-leaf path 5 -> 4 -> 11 -> 2 which has sum 22. So, return 1.
Explanation 2:

 There is no path which has sum -1.
    */
    private boolean hasSumPathUtil(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return sum == root.val;
        }

        return hasSumPathUtil(root.left, sum - root.val) || hasSumPathUtil(root.right, sum - root.val);
    }

    public int hasPathSum(TreeNode A, int B) {

        boolean found = hasSumPathUtil(A, B);
        if (found)
            return 1;

        return 0;
    }

    /* Next Pointer Binary Tree
    Problem Description
Given a binary tree,

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Assume perfect binary tree and try to solve this in constant extra space.



Problem Constraints
1 <= Number of nodes in binary tree <= 100000

0 <= node values <= 10^9



Input Format
First and only argument is head of the binary tree A.



Output Format
Return the head of the binary tree after the changes are made.



Example Input
Input 1:

 
     1
    /  \
   2    3
Input 2:

 
        1
       /  \
      2    5
     / \  / \
    3  4  6  7


Example Output
Output 1:

 
        1 -> NULL
       /  \
      2 -> 3 -> NULL
Output 2:

 
         1 -> NULL
       /  \
      2 -> 5 -> NULL
     / \  / \
    3->4->6->7 -> NULL


Example Explanation
Explanation 1:

Next pointers are set as given in the output.
Explanation 2:

Next pointers are set as given in the output.
    */
public void connectUsingExtraSpace(TreeLinkNode root) {
    
        Queue<TreeLinkNode> q = new ArrayDeque<TreeLinkNode>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            TreeLinkNode prev = null;
            for (int i = 0; i < size; i++) {
                TreeLinkNode node = q.poll();

                if (prev != null) {
                    prev.next = node;
                }

                if (node.left != null) {
                    q.offer(node.left);
                }

                if (node.right != null) {
                    q.offer(node.right);
                }

                prev = node;
            }

        }
    }

    private TreeLinkNode GetNextChild(TreeLinkNode root) {
        if (root == null)
            return null;

        TreeLinkNode temp = root.next;
        while (temp != null) {
            if (temp.left != null) {
                return temp.left;
            }

            if (temp.right != null) {
                return temp.right;
            }

            temp = temp.next;
        }

        return null;
    }

    public void connectSiblingsInConstantSpace(TreeLinkNode root) {
        TreeLinkNode level = root;

        while (level != null) {
            TreeLinkNode curr = level;

            while (curr != null) {
                if (curr.left != null) {
                    if (curr.right != null) {
                        curr.left.next = curr.right;
                    } else {
                        curr.left.next = GetNextChild(curr);
                    }
                }

                if (curr.right != null) {
                    curr.right.next = GetNextChild(curr);
                }

                curr = curr.next;
            }

            if (level.left != null) {
                level = level.left;
            } else if (level.right != null) {
                level = level.right;
            } else {
                level = GetNextChild(level);
            }
        }
    }

    /* Deserialize Binary Tree
    Problem Description
You are given an integer array A denoting the Level Order Traversal of the Binary Tree.

You have to Deserialize the given Traversal in the Binary Tree and return the root of the Binary Tree.

NOTE:

In the array, the NULL/None child is denoted by -1.
For more clarification check the Example Input.


Problem Constraints
1 <= number of nodes <= 105

-1 <= A[i] <= 105



Input Format
Only argument is an integer array A denoting the Level Order Traversal of the Binary Tree.



Output Format
Return the root node of the Binary Tree.



Example Input
Input 1:

 A = [1, 2, 3, 4, 5, -1, -1, -1, -1, -1, -1]
Input 2:

 A = [1, 2, 3, 4, 5, -1, 6, -1, -1, -1, -1, -1, -1]


Example Output
Output 1:

           1
         /   \
        2     3
       / \
      4   5
Output 2:

            1
          /   \
         2     3
        / \ .   \
       4   5 .   6


Example Explanation
Explanation 1:

 Each element of the array denotes the value of the node. If the val is -1 then it is the NULL/None child.
 Since 3, 4 and 5 each has both NULL child we had represented that using -1.
Explanation 2:

 Each element of the array denotes the value of the node. If the val is -1 then it is the NULL/None child.
 Since 3 has left child as NULL while 4 and 5 each has both NULL child.
    */
    public TreeNode serializeTree(int[] A) {
        TreeNode root = new TreeNode(A[0]);

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);

        int i = 1;

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null)
                continue;

            int leftVal = A[i];
            int rightVal = A[i + 1];

            if (leftVal == -1) {
                node.left = null;
            } else {
                node.left = new TreeNode(leftVal);
            }

            if (rightVal == -1) {
                node.right = null;
            } else {
                node.right = new TreeNode(rightVal);
            }

            q.offer(node.left);
            q.offer(node.right);

            i = i + 2;
        }

        return root;
    }

    /* Count number of nodes in a complete binary tree */
    public int CountNumberOfNodesInCompleteBinaryTree(TreeNode root) {
        if (root == null)
            return 0;

        TreeNode tmp = root;
        int lh = 0;
        while (tmp != null) {
            tmp = tmp.left;
            lh++;
        }

        tmp = root;
        int rh = 0;
        while (tmp != null) {
            tmp = tmp.right;
            rh++;
        }

        if (lh == rh)
            return (int) (Math.pow(2, lh + 1) - 1);

        return 1 + CountNumberOfNodesInCompleteBinaryTree(root.left)
                + CountNumberOfNodesInCompleteBinaryTree(root.right);
    }
}
