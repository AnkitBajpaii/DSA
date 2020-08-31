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
        readonly Dictionary<int, Node> hashTable;
        private readonly int capacity;
        private int size;

        public LRUCache() : this(LRUCache.DefaultCap)
        {

        }

        public LRUCache(int cap)
        {
            capacity = cap;
            size = 0;
            tail = null;
            head = null;
            hashTable = new Dictionary<int, Node>();
        }

        private void DeleteTail()
        {
            if (tail == null || size == 0)
            {
                return;
            }

            Node prev = tail.Prev;
            tail.Prev = null;
            prev.Next = null;
            tail = prev;
            size--;
        }

        public void Refer(int item)
        {
            Node node;

            if (hashTable.ContainsKey(item))
            {
                node = hashTable[item];

                if (node == head)
                {
                    return;
                }

                Node prev = node.Prev;
                Node next = node.Next;

                node.Prev = null;
                prev.Next = next;

                if (node == tail)
                {
                    tail = prev;
                }
                else
                {
                    next.Prev = prev;
                }

                node.Next = head;
                head.Prev = node;
                head = node;
            }
            else
            {
                if (size == capacity)
                {
                    DeleteTail();
                }

                node = new Node() { Data = item };

                if (head == null)
                {
                    head = node;
                    tail = head;
                }
                else
                {
                    node.Next = head;
                    head.Prev = node;
                    head = node;
                }

                hashTable.Add(item, node);
                size++;
            }
        }

        public void Clear()
        {
            head = null;
            tail = null;
            size = 0;
            hashTable.Clear();
        }

        public string Print()
        {
            string res = string.Empty;
            if (head == null)
            {
                res = "Cache is Empty";
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

                return res;
            }

            return res;
        }
    }
}
