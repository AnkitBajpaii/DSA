using System.Collections.Generic;

namespace DSA.LinkedList
{
    public class LinkedListProblems
    {
        //Find the middle of a linked list of a given linked list
        public static Node FindMiddle(Node head)
        {
            if (head == null || head.Next == null)
            {
                return head;
            }

            Node slow = head, fast = head;
            while (fast != null && fast.Next != null)
            {
                slow = slow.Next;
                fast = fast.Next.Next;
            }

            return slow;
        }

        //Find the n-th node from the end of a given linked list.
        public static Node FindNthNodeFromEnd(Node head, int n)
        {
            if (head == null)
            {
                return null;
            }

            Node first = head;
            for (int i = 0; i < n; i++)
            {
                if (first == null)
                {
                    return null;
                }

                first = first.Next;
            }

            Node second = head;
            while (first != null)
            {
                first = first.Next;
                second = second.Next;
            }

            return second;
        }

        //Reverse Linked List
        public static Node ReverseLinkedList_Iterative(Node head)
        {
            Node curr = head, prev = null;
            while (curr != null)
            {
                Node next = curr.Next;
                curr.Next = prev;

                prev = curr;

                curr = next;
            }

            return prev;
        }

        //Reverse Linked List - method 1 - initially curr = head, and prev = null
        // in this method we first reverse first i - 1 nodes and then link ith Node
        public static Node ReverseLinkedList_Recursive(Node curr, Node prev)
        {
            if (curr == null)
            {
                return prev;
            }

            Node next = curr.Next;
            curr.Next = prev;
            return ReverseLinkedList_Recursive(next, curr);
        }

        //Reverse Linked List - method 2
        // in this method when we are at ith node, we might have already reversed all the other nodes after that
        public static Node ReverseLinkedList_Recursive(Node head)
        {
            if (head == null || head.Next == null)
            {
                return head;
            }

            Node restHead = ReverseLinkedList_Recursive(head.Next);
            Node restTail = head.Next;
            restTail.Next = head;
            return restHead;
        }

        // Reverse LinkedList in groups of size K - Recursive Solution
        public static Node ReverseInGroup_Recursive(Node head, int k)
        {
            Node curr = head, prev = null, next = null;

            int counter = k;
            while (curr != null && counter > 0)
            {
                next = curr.Next;
                curr.Next = prev;
                prev = curr;
                curr = next;
                counter--;
            }

            if (next != null)
            {
                Node restHead = ReverseInGroup_Recursive(curr, k);
                head.Next = restHead;
            }

            return prev;
        }

        // Reverse LinkedList in groups of size K - Iterative Solution
        public static Node ReverseInGroup_Iterative(Node head, int k)
        {
            Node curr = head, prevFirst = null;
            bool firstPass = true;
            while (curr != null)
            {
                Node first = curr, prev = null;
                int counter = k;
                while (curr != null && counter > 0)
                {
                    Node next = curr.Next;

                    curr.Next = prev;

                    prev = curr;

                    curr = next;
                    counter--;
                }

                if (firstPass)
                {
                    head = prev;
                    firstPass = false;
                }
                else
                {
                    prevFirst.Next = prev;
                }

                prevFirst = first;
            }

            return head;
        }

        //Detect Loop In LinkedList - Floyd Cycle Detection
        public static bool DetectLoop(Node head)
        {
            Node slow = head, fast = head;

            while (fast != null && fast.Next != null)
            {
                slow = slow.Next;
                fast = fast.Next.Next;

                if (slow == fast)
                {
                    return true;
                }
            }

            return false;
        }

        //Detect Loop and Find the Length of Cycle
        public static int DetectLoopAndFindLengthOfCycle(Node head)
        {
            Node slow = head, fast = head;

            while (fast != null && fast.Next != null)
            {
                slow = slow.Next;
                fast = fast.Next.Next;

                if (slow == fast)
                {
                    break;
                }
            }

            if (fast == null || fast.Next == null || slow != fast)
            {
                return 0;
            }

            int length = 1;
            slow = fast.Next;

            while (slow != fast)
            {
                length++;
                slow = slow.Next;
            }

            return length;
        }

        // Detect Loop and find the starting point of loop
        public static Node DetectLoopAndFindStartingPointOfLoop(Node head)
        {
            Node slow = head, fast = head;

            while (fast != null && fast.Next != null)
            {
                slow = slow.Next;
                fast = fast.Next.Next;

                if (slow == fast)
                {
                    break;
                }
            }

            if (fast == null || fast.Next == null || slow != fast)
            {
                return null;
            }

            slow = head;

            while (slow != fast)
            {
                slow = slow.Next;
                fast = fast.Next;
            }

            return slow;
        }

        // Detect Loop and break the loop
        public static void DetectLoopAndBreakIt(Node head)
        {
            Node slow = head, fast = head;

            while (fast != null && fast.Next != null)
            {
                slow = slow.Next;
                fast = fast.Next.Next;

                if (slow == fast)
                {
                    break;
                }
            }

            if (slow != fast)
            {
                return;
            }

            // break the loop
            slow = head;

            while (slow.Next != fast.Next)
            {
                slow = slow.Next;
                fast = fast.Next;
            }

            fast.Next = null;
        }

        // Delete node with only one pointer given to it
        public static void DeleteNode(Node p)
        {
            if (p.Next == null)
            {
                return;
            }

            p.Data = p.Next.Data;
            Node temp = p.Next;
            p.Next = p.Next.Next;
            temp.Next = null;
        }

        // Seggregate even and odd nodes of linked list
        public static Node SeggregateEvenOdd(Node head)
        {
            Node evenStart = null, evenEnd = null, oddStart = null, oddEnd = null;
            for (Node curr = head; curr != null; curr = curr.Next)
            {
                int data = curr.Data;

                if (data % 2 == 0)
                {
                    if (evenStart == null)
                    {
                        evenStart = curr;
                        evenEnd = evenStart;
                    }
                    else
                    {
                        evenEnd.Next = curr;
                        evenEnd = evenEnd.Next;
                    }
                }
                else
                {

                    if (oddStart == null)
                    {
                        oddStart = curr;
                        oddEnd = oddStart;
                    }
                    else
                    {
                        oddEnd.Next = curr;
                        oddEnd = oddEnd.Next;
                    }
                }
            }

            if (evenStart == null || oddStart == null)
            {
                return head;
            }

            evenEnd.Next = oddStart;
            oddEnd.Next = null;

            return evenStart;
        }

        public static int Length(Node head)
        {
            int len = 0;
            Node curr = head;
            while (curr != null)
            {
                len++;
                curr = curr.Next;
            }

            return len;
        }

        // Find Intersection of two linked list
        public static Node IntersectionPoint(Node head1, Node head2)
        {
            Node curr1 = head1, curr2 = head2;

            int len1 = Length(curr1);
            int len2 = Length(curr2);

            int d;
            if (len1 > len2)
            {
                d = len1 - len2;
            }
            else
            {
                d = len2 - len1;
                Node t = curr2;
                curr1 = curr2;
                curr2 = t;
            }

            for (int i = 0; i < d; i++)
            {
                curr1 = curr1.Next;
            }

            while (curr1 != null && curr2 != null)
            {
                if (curr1 == curr2)
                {
                    return curr1;
                }

                curr1 = curr1.Next;
                curr2 = curr2.Next;
            }

            return null;
        }

        // Pairwise swap nodes of linked list
        // This method swaps the data, so it data is large complex object, swap might be costly
        public static void PairWiseSwap_Method1(Node head)
        {
            Node curr = head;
            while (curr != null && curr.Next != null)
            {
                int data = curr.Data;
                curr.Data = curr.Next.Data;
                curr.Next.Data = data;

                curr = curr.Next.Next;
            }
        }

        // Pairwise swap nodes of linked list
        public static Node PairWiseSwap(Node head)
        {
            if (head == null || head.Next == null)
            {
                return head;
            }

            Node curr = head.Next.Next;

            Node prev = head;
            head = head.Next;
            head.Next = prev;

            while (curr != null && curr.Next != null)
            {
                prev.Next = curr.Next;

                Node next = curr.Next.Next;
                curr.Next.Next = curr;

                prev = curr;
                curr = next;
            }

            prev.Next = curr;
            return head;
        }

        // Clone LinkedList using random pointer - method 1 using HashTable
        public static Node CloneWithRandomPointer_Method1(Node head)
        {
            Dictionary<int, Node> hashMap = new Dictionary<int, Node>();
            for (Node curr = head; curr != null; curr = curr.Next)
            {
                hashMap.Add(curr.Data, new Node { Data = curr.Data });
            }

            for (Node curr = head; curr != null; curr = curr.Next)
            {
                Node cloned = hashMap[curr.Data];
                cloned.Next = hashMap[curr.Next.Data];
                cloned.Random = hashMap[curr.Random.Data];
            }

            return hashMap[head.Data];
        }

        //  Clone LinkedList using random pointer - method 2 Tricky solution
        public static Node CloneWithRandomPointer_Method2(Node head)
        {
            // step 1 insert new nodes in between
            for (Node curr = head; curr != null;)
            {
                Node next = curr.Next;
                curr.Next = new Node { Data = curr.Data };
                curr.Next.Next = next;

                curr = next;
            }

            // step 2 update random pointer for new nodes
            for (Node curr = head; curr != null; curr = curr.Next.Next)
            {
                curr.Next.Random = curr.Random != null ? curr.Random.Next : null;
            }

            // step3 extract out new nodes
            Node dummy = new Node { Data = -1 };
            Node currClone = dummy;

            for (Node curr = head; curr != null && curr.Next != null; curr = curr.Next.Next)
            {
                currClone.Next = curr.Next;

                currClone = currClone.Next;
            }

            Node clonedHead = dummy.Next;
            dummy.Next = null;

            return clonedHead;
        }

        // LRU Cache Design
        public static void LRUCacheDesign()
        {
            LRUCache cache = new LRUCache(4);

            cache.Refer(10);
            cache.Refer(20);
            cache.Refer(10);
            cache.Refer(30);
            cache.Refer(40);
            cache.Refer(50);
            cache.Refer(30);
            cache.Refer(40);
            cache.Refer(60);
            cache.Refer(30);
        }

        //Merge Two Sorted Linked List
        public static Node Merge(Node a, Node b)
        {
            if (a == null)
            {
                return b;
            }

            if (b == null)
            {
                return a;
            }

            Node head, tail;
            if (a.Data <= b.Data)
            {
                head = a;
                tail = a;
                a = a.Next;
            }
            else
            {
                head = b;
                tail = b;
                b = b.Next;
            }

            while (a != null && b != null)
            {
                if (a.Data <= b.Data)
                {
                    tail.Next = a;
                    tail = a;
                    a = a.Next;
                }
                else
                {
                    tail.Next = b;
                    tail = b;
                    b = b.Next;
                }
            }

            if (a != null)
            {
                tail.Next = a;
            }

            if (b != null)
            {
                tail.Next = b;
            }

            return head;
        }

        // Check if linked list is Palindrome
        public static bool IsPalindrome(Node head)
        {
            if (head == null || head.Next == null)
            {
                return true;
            }

            Node slow = head, fast = head;
            while (fast.Next != null && fast.Next.Next != null)
            {
                slow = slow.Next;
                fast = fast.Next.Next;
            }

            Node rev = ReverseLinkedList_Iterative(slow.Next);
            Node curr = head;
            while (rev != null)
            {
                if (rev.Data != curr.Data)
                {
                    return false;
                }

                rev = rev.Next;
                curr = curr.Next;
            }

            return true;
        }
    }
}
