using System;
using System.Collections.Generic;
using System.Text;
using System.Xml;

namespace DSA.Tree
{
    public class TreeProblems
    {
        public void InorderTraversal(Node root)
        {
            if (root == null)
            {
                return;
            }

            InorderTraversal(root.Left);

            Console.Write(root.Key + " ");

            InorderTraversal(root.Right);
        }

        public void PreOrderTraversal(Node root)
        {
            if (root == null)
            {
                return;
            }

            Console.Write(root.Key + " ");

            PreOrderTraversal(root.Left);

            PreOrderTraversal(root.Right);
        }

        public void PostOrderTraversal(Node root)
        {
            if (root == null)
            {
                return;
            }

            PostOrderTraversal(root.Left);

            PostOrderTraversal(root.Right);

            Console.Write(root.Key + " ");
        }

        public int HeighOfBinaryTree(Node root)
        {
            if (root == null)
            {
                return 0;
            }

            int lh = HeighOfBinaryTree(root.Left);
            int rh = HeighOfBinaryTree(root.Right);

            return 1 + Math.Max(lh, rh);
        }

        // Nodes at distance k from the root are basically the nodes at (k+1)th level of the Binary Tree.
        public void PrintNodesAtDistanceK(Node root, int k)
        {
            if (root == null)
            {
                return;
            }

            if (k == 0)
            {
                Console.Write(root.Key + " ");
                return;
            }

            PrintNodesAtDistanceK(root.Left, k - 1);
            PrintNodesAtDistanceK(root.Right, k - 1);
        }

        public void LevelOrderTraversal(Node root)
        {
            if (root == null)
            {
                return;
            }

            Queue<Node> q = new Queue<Node>();
            q.Enqueue(root);
            while (q.Count > 0)
            {
                Node curr = q.Dequeue();
                Console.Write(curr.Key + " ");
                if (curr.Left != null)
                {
                    q.Enqueue(curr.Left);
                }

                if (curr.Right != null)
                {
                    q.Enqueue(curr.Right);
                }
            }
        }

        public void LevelOrderTraversal_LineByLine_Method1(Node root)
        {
            if (root == null)
            {
                return;
            }

            Queue<Node> q = new Queue<Node>();
            q.Enqueue(root);
            q.Enqueue(null);
            while (q.Count > 1)
            {
                Node curr = q.Dequeue();
                if (curr == null)
                {
                    Console.WriteLine();
                    q.Enqueue(null);
                }
                else
                {
                    Console.Write(curr.Key + " ");
                    if (curr.Left != null)
                    {
                        q.Enqueue(curr.Left);
                    }

                    if (curr.Right != null)
                    {
                        q.Enqueue(curr.Right);
                    }
                }
            }
        }

        public void LevelOrderTraversal_LineByLine_Method2(Node root)
        {
            if (root == null)
            {
                return;
            }

            Queue<Node> q = new Queue<Node>();
            q.Enqueue(root);
            while (q.Count > 0)
            {
                int size = q.Count;
                for (int i = 0; i < size; i++)
                {
                    Node curr = q.Dequeue();
                    Console.Write(curr.Key + " ");
                    if (curr.Left != null)
                    {
                        q.Enqueue(curr.Left);
                    }

                    if (curr.Right != null)
                    {
                        q.Enqueue(curr.Right);
                    }
                }

                Console.WriteLine();
            }
        }

        public int SizeOfBinaryTree(Node root)
        {
            if (root == null)
            {
                return 0;
            }

            return 1 + SizeOfBinaryTree(root.Left) + SizeOfBinaryTree(root.Right);
        }

        public int MaximumInBinaryTree(Node root)
        {
            if (root == null)
            {
                return Int32.MinValue;
            }

            int lmax = MaximumInBinaryTree(root.Left);
            int rmax = MaximumInBinaryTree(root.Right);

            return Math.Max(root.Key, Math.Max(lmax, rmax));
        }

        // Print left view of binary tree
        int maxLevel = 0;
        public void PrintLeftView_Recursive(Node root, int level) // initially level will be passed as 1 from outside
        {
            if (root == null)
            {
                return;
            }

            if (maxLevel < level)
            {
                maxLevel = level;
                Console.Write(root.Key + " ");
            }

            PrintLeftView_Recursive(root.Left, level + 1);
            PrintLeftView_Recursive(root.Right, level + 1);
        }

        public void PrintLeftView_Iterative(Node root)
        {
            if (root == null)
            {
                return;
            }

            Queue<Node> q = new Queue<Node>();
            q.Enqueue(root);
            while (q.Count > 0)
            {
                int size = q.Count;
                for (int i = 0; i < size; i++)
                {
                    Node curr = q.Dequeue();
                    if (i == 0) // print first node in queue
                    {
                        Console.Write(curr.Key + " ");
                    }

                    if (curr.Left != null)
                    {
                        q.Enqueue(curr.Left);
                    }

                    if (curr.Right != null)
                    {
                        q.Enqueue(curr.Right);
                    }
                }
            }
        }

        public void PrintRightView_Recursive(Node root, int level) // initially level will be passed as 1 from outside and maxlevel will be 0
        {
            if (root == null)
            {
                return;
            }

            if (maxLevel < level)
            {
                maxLevel = level;
                Console.Write(root.Key + " ");
            }

            PrintRightView_Recursive(root.Right, level + 1);
            PrintRightView_Recursive(root.Left, level + 1);
        }

        public void PrintRightView_Iterative(Node root)
        {
            if (root == null)
            {
                return;
            }

            Queue<Node> q = new Queue<Node>();
            q.Enqueue(root);
            while (q.Count > 0)
            {
                int size = q.Count;
                for (int i = 0; i < size; i++)
                {
                    Node curr = q.Dequeue();
                    if (i == size - 1) // print last node in queue
                    {
                        Console.Write(curr.Key + " ");
                    }

                    if (curr.Left != null)
                    {
                        q.Enqueue(curr.Left);
                    }

                    if (curr.Right != null)
                    {
                        q.Enqueue(curr.Right);
                    }
                }
            }
        }

        // Children Sum Property is a property in which the sum of values of the left child and right child should be equal to the value of their parent node if both children are present. Else if only one child is present then the value of the child should be equal to its parent node value.
        public bool IsChildSum(Node root)
        {
            if (root == null)
            {
                return true;
            }

            if (root.Left == null && root.Right == null)
            {
                return true;
            }

            int sum = 0;
            if (root.Left != null)
            {
                sum = sum + root.Left.Key;
            }

            if (root.Right != null)
            {
                sum = sum + root.Right.Key;
            }

            return (root.Key == sum) && IsChildSum(root.Left) && IsChildSum(root.Right);
        }

        // In a Balanced Binary Tree for every node, the difference between heights of left subtree and right subtree should not be more than one.
        public int IsBalanced(Node root)
        {
            if (root == null)
            {
                return 0;
            }

            int lh = IsBalanced(root.Left);
            if (lh == -1)
            {
                return -1;
            }

            int rh = IsBalanced(root.Right);
            if (rh == -1)
            {
                return -1;
            }

            if (Math.Abs(lh - rh) > 1)
            {
                return -1;
            }

            return 1 + Math.Max(lh, rh);
        }

        public int MaxWidthOfBinaryTree(Node root)
        {
            if (root == null)
            {
                return 0;
            }

            Queue<Node> q = new Queue<Node>();
            q.Enqueue(root);
            int res = 0;
            while (q.Count > 0)
            {
                int count = q.Count;
                res = Math.Max(res, count);
                for (int i = 0; i < count; i++)
                {
                    Node curr = q.Dequeue();
                    if (curr.Left != null)
                    {
                        q.Enqueue(curr.Left);
                    }

                    if (curr.Right != null)
                    {
                        q.Enqueue(curr.Right);
                    }
                }
            }

            return res;
        }

        public Node ConvertTreeToDLL(Node root, ref Node prev)
        {
            if (root == null)
            {
                return null;
            }

            Node head = ConvertTreeToDLL(root.Left, ref prev);
            if (prev == null)
            {
                head = root;
            }
            else
            {
                prev.Right = root;
                root.Left = prev;
            }

            prev = root;
            ConvertTreeToDLL(root.Right, ref prev);
            return head;
        }

        // Construct Binary tree from in order and pre order
        public Node ConstructBinaryTreeFromInOrderAndPreOrder(int[] inOrder, int[] preOrder, int inStart, int inEnd, ref int preIndex) // preIndex = 0 initially
        {
            if (inStart > inEnd)
            {
                return null;
            }

            int key = preOrder[preIndex];
            preIndex++;

            Node root = new Node(key);
            int inIndex = 0;
            for (int i = inStart; i <= inEnd; i++)
            {
                if (inOrder[i] == key)
                {
                    inIndex = i;
                    break;
                }
            }

            root.Left = ConstructBinaryTreeFromInOrderAndPreOrder(inOrder, preOrder, inStart, inIndex - 1, ref preIndex);
            root.Right = ConstructBinaryTreeFromInOrderAndPreOrder(inOrder, preOrder, inIndex + 1, inEnd, ref preIndex);

            return root;
        }

        // Construct Binary tree from in order and post order
        public Node ConstructBinaryTreeFromInOrderAndPostOrder(int[] inOrder, int[] postOrder, int inStart, int inEnd, ref int postIndex) // postIndex = postOrder.Length - 1 initially
        {
            if (inStart > inEnd)
            {
                return null;
            }

            int key = postOrder[postIndex];
            postIndex--;

            Node root = new Node(key);
            int inIndex = 0;
            for (int i = inStart; i <= inEnd; i++)
            {
                if (inOrder[i] == key)
                {
                    inIndex = i;
                    break;
                }
            }

            root.Right = ConstructBinaryTreeFromInOrderAndPostOrder(inOrder, postOrder, inIndex + 1, inEnd, ref postIndex);
            root.Left = ConstructBinaryTreeFromInOrderAndPostOrder(inOrder, postOrder, inStart, inIndex - 1, ref postIndex);

            return root;
        }

        public void TreeTraversalInSpialForm(Node root)
        {
            if (root == null)
            {
                return;
            }

            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();

            s1.Push(root);

            while (s1.Count > 0 || s2.Count > 0)
            {
                while (s1.Count > 0)
                {
                    Node curr = s1.Pop();
                    Console.Write(curr.Key + " ");

                    if (curr.Left != null)
                    {
                        s2.Push(curr.Left);
                    }

                    if (curr.Right != null)
                    {
                        s2.Push(curr.Right);
                    }
                }

                Console.WriteLine();

                while (s2.Count > 0)
                {
                    Node curr = s2.Pop();
                    Console.Write(curr.Key + " ");

                    if (curr.Right != null)
                    {
                        s1.Push(curr.Right);
                    }

                    if (curr.Left != null)
                    {
                        s1.Push(curr.Left);
                    }
                }

                Console.WriteLine();
            }
        }

        public int DiameterOfBinaryTree(Node root, ref int res) // initially res = 0
        {
            if (root == null)
            {
                return 0;
            }

            int lh = DiameterOfBinaryTree(root.Left, ref res);
            int rh = DiameterOfBinaryTree(root.Right, ref res);

            res = Math.Max(res, 1 + lh + rh);

            return Math.Max(lh, rh) + 1;
        }
        // Find LCA (Lowest common ancestor) of two nodes in a binary tree
        public Node FindLCA_Method1(Node root, int n1, int n2)
        {
            List<Node> path1 = new List<Node>();
            List<Node> path2 = new List<Node>();

            if (FindPath(root, n1, path1) == false || FindPath(root, n2, path2) == false)
            {
                return null;
            }

            for (int i = 0; i < path1.Count && i < path2.Count; i++)
            {
                if (path1[i + 1] != path2[i + 1])
                {
                    return path1[i];
                }
            }

            return null;
        }

        bool FindPath(Node root, int n, List<Node> path)
        {
            if (root == null)
            {
                return false;
            }

            path.Add(root);

            if (root.Key == n)
            {
                return true;
            }

            if (FindPath(root.Left, n, path) || FindPath(root.Right, n, path))
            {
                return true;
            }

            path.RemoveAt(path.Count - 1);
            return false;
        }

        // this method assumes both n1 and n2 are present in tree. If any of n1 or n2 is not present this method fails.
        public Node FindLCA_Method2(Node root, int n1, int n2)
        {
            if (root == null)
            {
                return null;
            }

            if (root.Key == n1 || root.Key == n2)
            {
                return root;
            }

            Node lca1 = FindLCA_Method2(root.Left, n1, n2);
            Node lca2 = FindLCA_Method2(root.Right, n1, n2);
            if (lca1 != null && lca2 != null)
            {
                return root;
            }
            if (lca1 != null)
            {
                return lca1;
            }
            else
            {
                return lca2;
            }
        }

        // given a binary tree and a leaf node, we need to find time to burn the Binary Tree if we burn the given leaf at 0-th second. 
        public int BurnTreeFromLeaf(Node root, int leaf, ref int dist, ref int res)
        {
            if (root == null)
            {
                return 0;
            }

            if (root.Key == leaf)
            {
                dist = 0;
                return 1;
            }
            int ldist = -1, rdist = -1;

            int lh = BurnTreeFromLeaf(root.Left, leaf, ref ldist, ref res);
            int rh = BurnTreeFromLeaf(root.Right, leaf, ref rdist, ref res);
            if (ldist != -1)
            {
                dist = ldist + 1;
                res = Math.Max(res, rh + dist);

            }
            else if (rdist != -1)
            {
                dist = rdist + 1;
                res = Math.Max(res, lh + dist);
            }

            return Math.Max(lh, rh) + 1;
        }

        // Count nodes in a complete binary tree - O(Log n * Log n)
        public double CountNodesInCompleteBinaryTree(Node root)
        {
            int lh = 0, rh = 0;
            Node curr = root;
            while (curr != null)
            {
                lh++;
                curr = curr.Left;
            }

            curr = root;
            while (curr != null)
            {
                rh++;
                curr = curr.Right;
            }

            if (lh == rh)
            {
                return Math.Pow(2, lh) - 1;
            }

            return 1 + CountNodesInCompleteBinaryTree(root.Left) + CountNodesInCompleteBinaryTree(root.Right);
        }

        public void SerializeTree(Node root, List<int> res)
        {
            if (root == null)
            {
                res.Add(-1);
                return;
            }

            res.Add(root.Key);
            SerializeTree(root.Left, res);
            SerializeTree(root.Right, res);
        }

        public Node DerializeTree(List<int> arr, ref int index)
        {
            if (index == arr.Count)
            {
                return null;
            }

            int val = arr[index];
            index++;

            if (val == -1)
            {
                return null;
            }

            Node root = new Node(val);
            root.Left = DerializeTree(arr, ref index);
            root.Right = DerializeTree(arr, ref index);
            return root;
        }

        // Given a Binary Tree and a Key. The task is to insert the key into the binary tree at first position available in level order.
        public void Insert(Node root, int key)
        {
            if (root == null)
            {
                root = new Node(key);
                return;
            }

            Queue<Node> q = new Queue<Node>();
            q.Enqueue(root);

            while (q.Count > 0)
            {
                Node curr = q.Dequeue();
                if (curr.Left == null)
                {
                    curr.Left = new Node(key);
                    return;
                }

                if (curr.Right == null)
                {
                    curr.Right = new Node(key);
                    return;
                }

                q.Enqueue(curr.Left);
                q.Enqueue(curr.Right);
            }
        }

        // Delete a node in binary tree
        public void Delete(Node root, int key)
        {
            Queue<Node> q = new Queue<Node>();
            q.Enqueue(root);

            Node tmp = null; // Deepest Node
            Node keyNode = null; // node with matching key
            while (q.Count > 0)
            {
                tmp = q.Dequeue();
                if (tmp.Key == key)
                {
                    keyNode = tmp;
                }

                if (tmp.Left != null)
                {
                    q.Enqueue(tmp.Left);
                }

                if (tmp.Right != null)
                {
                    q.Enqueue(tmp.Right);
                }
            }

            int x = tmp.Key;
            DeleteLeafNode(root, tmp);
            keyNode.Key = x;
        }

        public void DeleteLeafNode(Node root, Node leafNode)
        {
            Queue<Node> q = new Queue<Node>();
            q.Enqueue(root);

            while (q.Count > 0)
            {
                Node curr = q.Dequeue();

                if (curr.Left != null)
                {
                    if (curr.Left == leafNode)
                    {
                        curr.Left = null;
                        return;
                    }
                    else
                    {
                        q.Enqueue(curr.Left);
                    }
                }

                if (curr.Right != null)
                {
                    if (curr.Right == leafNode)
                    {
                        curr.Right = null;
                        return;
                    }
                    else
                    {
                        q.Enqueue(curr.Right);
                    }
                }
            }
        }

        public Node LeftMost(Node root)
        {
            if (root == null)
            {
                return null;
            }

            Node curr = root;
            while (curr.Left != null)
            {
                curr = curr.Left;
            }

            return curr;
        }

        public Node RightMost(Node root)
        {
            if (root == null)
            {
                return null;
            }

            Node curr = root;
            while (curr.Right != null)
            {
                curr = curr.Right;
            }

            return curr;
        }

        public void InorderTraversalInThreadedBinaryTree(Node root)
        {
            Node curr = LeftMost(root);

            while (curr != null)
            {
                Console.Write(curr.Key + " ");

                if (curr.RightThread)
                {
                    curr = curr.Right;
                }
                else
                {
                    curr = LeftMost(root.Right);
                }
            }
        }

        public void PrintTopView(Node root)
        {
            Queue<NodeWithHd> q = new Queue<NodeWithHd>();
            NodeWithHd rootNode = new NodeWithHd { hd = 0, node = root };
            SortedDictionary<int, Node> map = new SortedDictionary<int, Node>();

            while (q.Count > 0)
            {
                NodeWithHd curr = q.Dequeue();
                if (!map.ContainsKey(curr.hd))
                {
                    map.Add(curr.hd, curr.node);
                }

                if (curr.node.Left != null)
                {
                    q.Enqueue(new NodeWithHd { node = curr.node.Left, hd = curr.hd - 1 });
                }

                if (curr.node.Right != null)
                {
                    q.Enqueue(new NodeWithHd { node = curr.node.Right, hd = curr.hd + 1 });
                }
            }

            foreach (var kvp in map)
            {
                Console.Write(kvp.Value.Key + " ");
            }
        }

        public void PrintBottomView(Node root)
        {
            Queue<NodeWithHd> q = new Queue<NodeWithHd>();
            NodeWithHd rootNode = new NodeWithHd { hd = 0, node = root };
            SortedDictionary<int, Node> map = new SortedDictionary<int, Node>();

            while (q.Count > 0)
            {
                NodeWithHd curr = q.Dequeue();
                if (!map.ContainsKey(curr.hd))
                {
                    map.Add(curr.hd, curr.node);
                }
                else
                {
                    map[curr.hd] = curr.node;
                }

                if (curr.node.Left != null)
                {
                    q.Enqueue(new NodeWithHd { node = curr.node.Left, hd = curr.hd - 1 });
                }

                if (curr.node.Right != null)
                {
                    q.Enqueue(new NodeWithHd { node = curr.node.Right, hd = curr.hd + 1 });
                }
            }

            foreach (var kvp in map)
            {
                Console.Write(kvp.Value.Key + " ");
            }
        }

        public void VerticalOrderTraversal(Node root, int hd, SortedDictionary<int, List<int>> map)
        {
            if (root == null)
            {
                return;
            }

            if (map.ContainsKey(hd))
            {
                var list = map[hd];
                list.Add(root.Key);
            }
            else
            {
                map.Add(hd, new List<int> { root.Key });
            }

            VerticalOrderTraversal(root.Left, hd - 1, map);
            VerticalOrderTraversal(root.Right, hd + 1, map);
        }

        public void MirrorOfBinaryTree(Node root)
        {
            if (root == null)
            {
                return;
            }

            MirrorOfBinaryTree(root.Left);
            MirrorOfBinaryTree(root.Right);

            Node t = root.Left;
            root.Left = root.Right;
            root.Right = t;
        }

        public bool IsIdentical(Node root1, Node root2)
        {
            if (root1 == null && root2 == null)
            {
                return true;
            }

            if (root1 == null || root2 == null)
            {
                return false;
            }

            if (root1.Key != root2.Key)
            {
                return false;
            }

            return IsIdentical(root1.Left, root2.Left) && IsIdentical(root1.Right, root2.Right);
        }

        void fillSet(Node root, HashSet<int> set, int hd)
        {
            if (root == null) return;

            fillSet(root.Left, set, hd - 1);
            set.Add(hd);
            fillSet(root.Right, set, hd + 1);
        }

        public int VerticalWidth(Node root)
        {
            HashSet<int> set = new HashSet<int>();
            fillSet(root, set, 0);
            return set.Count;
        }

        // Connect Nodes at Same Level
        public void ConnectNodesAtSameLevel(Node p)
        {
            if (p == null)
            {
                return;
            }

            Queue<Node> q = new Queue<Node>();
            q.Enqueue(p);
            while (q.Count > 0)
            {
                int size = q.Count;
                Node prev = null;
                for (int i = 0; i < size; i++)
                {
                    Node curr = q.Dequeue();

                    if (curr.Left != null)
                    {
                        q.Enqueue(curr.Left);
                    }

                    if (curr.Right != null)
                    {
                        q.Enqueue(curr.Right);
                    }

                    if (prev != null)
                    {
                        prev.NextRight = curr;
                    }

                    prev = curr;
                }
            }
        }

        // Construct Binary Tree from Parent Array
        // Given an array of size N that represents a Tree in such a way that array indexes are values in tree nodes and array values give the parent node of that particular index (or node). The value of the root node index would always be -1 as there is no parent for root. Construct the standard linked representation of Binary Tree from this array representation.

        void createNode(int[] arr, int i, Node[] created, ref Node root)
        {
            if (created[i] != null)
            {
                return;
            }
            Node node = new Node(i);
            created[i] = node;
            if (arr[i] == -1)
            {
                root = node;
                return;
            }

            if (created[arr[i]] == null)
            {
                createNode(arr, arr[i], created, ref root);
            }

            Node p = created[arr[i]];

            if (p.Left == null)
            {
                p.Left = node;
            }

            else if (p.Right == null)
            {
                p.Right = node;
            }
        }

        public Node ConstructTreeFromParentArray(int[] arr, int n)
        {
            Node[] created = new Node[n];
            Node root = null;
            for (int i = 0; i < n; i++)
            {
                createNode(arr, i, created, ref root);
            }
            return root;
        }

        //Max Path Sum in Binary Tree
        //Given a binary tree, find the maximum path sum.The path may start and end at any node in the tree.
        public int FindMaxSum(Node node, ref int res) // initially res will be Int.Min
        {
            if (node == null)
            {
                return 0;
            }

            int l = FindMaxSum(node.Left, ref res);
            int r = FindMaxSum(node.Right, ref res);

            int x = Math.Max(Math.Max(l, r) + node.Key, node.Key);

            int y = Math.Max(x, l + r + node.Key);

            res = Math.Max(res, y);
            return x;
        }


        public int MaxDiffBetweenNodeAndItsAncestor(Node root, ref int res)
        {
            // Initialising result with minimum int value  
            /* Returning Maximum int value if node is not  
                there (one child case)  */
            if (root == null)
            {
                return int.MaxValue;
            }

            /* If leaf node then just return node's value  */
            if (root.Left == null && root.Right == null)
            {
                return root.Key;
            }

            int l = MaxDiffBetweenNodeAndItsAncestor(root.Left, ref res);
            int r = MaxDiffBetweenNodeAndItsAncestor(root.Right, ref res);

            int min = Math.Min(l, r);
            int diff = root.Key - min;
            res = Math.Max(res, diff);
            return Math.Min(min, root.Key);
        }

        // Given a binary tree and an integer X. Your task is to complete the function countSubtreesWithSumX() that returns the count of the number of subtress having total node’s data sum equal to the value X.
        public int CountSubtreesWithSumX(Node root,int x, ref int count) // initially count = 0
        {
            // if tree is empty  
            if (root == null)
            {
                return 0;
            }

            // sum of nodes in the left subtree  
            int ls = CountSubtreesWithSumX(root.Left, x, ref count);

            // sum of nodes in the right subtree  
            int rs = CountSubtreesWithSumX(root.Right, x, ref count);

            // sum of nodes in the subtree  
            // rooted with 'root.data'  
            int sum = ls + rs + root.Key;

            // if true  
            if (sum == x)
            {
                count++;
            }

            // return subtree's nodes sum  
            return sum;
        }
    }
}
