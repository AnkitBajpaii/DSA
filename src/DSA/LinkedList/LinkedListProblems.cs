using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.LinkedList
{
    public class Node
    {
        public int Data;
        public Node Next;
        public Node(int data) : this(data, null)
        {
        }

        public Node(int data, Node next)
        {
            this.Data = data;
            this.Next = next;
        }
    }

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
        public static Node FindNtNodeFromEnd(Node head, int n)
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
            head.Next = null;
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
    }
}
