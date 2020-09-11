using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Tree
{
    public class Node
    {
        public int Key { get; set; }
        public Node Left { get; set; }
        public Node Right { get; set; }

        public bool RightThread { get; set; } // for threaded binary trees only
        public Node NextRight { get; set; } // to connect nodes at same level
        public Node(int key)
        {
            this.Key = key;
        }
    }

    public class NodeWithHd
    {
        public Node node { get; set; }
        public int hd { get; set; }
    }
}
