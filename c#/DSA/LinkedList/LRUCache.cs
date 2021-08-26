using System.Collections.Generic;

namespace DSA.LinkedList
{
    // Basic LRU Cache Design
    // Most recently used item will be at front and least recently used item will be at tail
    public class LRUCache
    {
        private static readonly int DefaultCap = 100;
        private Node tail;
        private Node head;
        readonly Dictionary<int, Node> hashMap;
        private readonly int capacity;
        private int size;

        public LRUCache() : this(LRUCache.DefaultCap)
        {

        }

        public LRUCache(int cap)
        {
            capacity = cap;
            size = 0;
            head = tail = null;
            hashMap = new Dictionary<int, Node>();
        }

        private void DeleteTail()
        {
            if (tail == null || size == 0)
            {
                return;
            }

            hashMap.Remove(tail.Data);

            Node prev = tail.Prev;
            tail.Prev = null;
            tail.Next = null;
            prev.Next = null;
            tail = prev;
            size--;
        }

        void DeleteNode(Node node)
        {
            if (node == null)
                return;

            if (node.Prev != null)
            {
                node.Prev.Next = node.Next;
            }

            if (node.Next != null)
            {
                node.Next.Prev = node.Prev;
            }
            else
            {
                tail = node.Prev;
            }

            node.Prev = null;
            node.Next = null;
        }

        void AddNodeToHead(Node node)
        {
            if (node == null)
                return;

            if (head == null)
            {
                head = tail = node;
                return;
            }

            node.Next = head;
            head.Prev = node;
            head = node;
        }

        public int Get(int key)
        {
            if (hashMap.ContainsKey(key))
            {
                Node node = hashMap[key];

                if (node == head)
                {
                    return head.Data;
                }

                DeleteNode(node);

                AddNodeToHead(node);

                return node.Data;
            }

            return -1;
        }

        public void Set(int key, int value)
        {
            Node node;

            if (hashMap.ContainsKey(key))
            {
                node = hashMap[key];
                node.Data = value;
                if (node == head)
                {
                    return;
                }

                DeleteNode(node);

                AddNodeToHead(node);
            }
            else
            {
                if (size >= capacity)
                {
                    DeleteTail();
                }

                node = new Node() { Data = value };

                AddNodeToHead(node);
                hashMap.Add(key, node);
                size++;
            }
        }

        public void Clear()
        {
            head = null;
            tail = null;
            size = 0;
            hashMap.Clear();
        }

        public string Print()
        {
            string res = string.Empty;
            if (head == null)
            {
                res = "Cache is Empty";
                System.Console.WriteLine(res);
                return res;
            }
            else
            {
                Node curr = head;
                while (curr != null)
                {
                    res = res + curr.Data + " => ";
                    if (curr.Next == null)
                    {
                        res = res + "NULL";
                    }

                    curr = curr.Next;
                }
                System.Console.WriteLine(res);
                return res;
            }
        }
    }
}
