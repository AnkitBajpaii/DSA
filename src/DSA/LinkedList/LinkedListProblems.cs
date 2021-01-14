using System.Collections.Generic;

namespace DSA.LinkedList
{
    public class LinkedListProblems
    {
        // Given a linked list of size N and a key. The task is to insert the key in the middle of the linked list.
        public static Node InsertInMid(Node head, int data)
        {
            if (head == null)
            {
                head = new Node(data);
                return head;
            }

            Node slow = head, fast = head;
            while (fast.Next != null && fast.Next.Next != null)
            {
                slow = slow.Next;
                fast = fast.Next.Next;
            }

            Node node = new Node(data);
            node.Next = slow.Next;
            slow.Next = node;
            return head;
        }

        // Find the middle of a linked list of a given linked list
        public static Node FindMiddle(Node head)
        {
            if (head == null || head.Next == null)
            {
                return head;
            }

            Node slow = head, fast = head;
            while (fast.Next != null && fast.Next.Next != null)
            {
                slow = slow.Next;
                fast = fast.Next.Next;
            }

            return slow;
        }

        // Find the n-th node from the end of a given linked list.
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

        // Reverse Linked List
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

        // Detect Loop In LinkedList - Floyd Cycle Detection
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

        // Detect Loop and Find the Length of Cycle
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
            if (head == null || head.Next == null)
            {
                return;
            }

            if (head.Next == head)
            {
                head.Next = null;
                return;
            }

            if (head.Next.Next == head)
            {
                head.Next.Next = null;
                return;
            }

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
                return;
            }

            if (fast == head)
            {
                fast = fast.Next;
                while (fast.Next != head)
                {
                    fast = fast.Next;
                }

                fast.Next = null;
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
        public static Node IntersectionPoint(Node headA, Node headB)
        {
            Node curr1 = headA, curr2 = headB;

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
                Node t = curr1;
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

        // Clone LinkedList using random pointer - method 2 Tricky solution
        public static Node CloneWithRandomPointer_Method2(Node head)
        {
            if (head == null)
            {
                return null;
            }

            if (head.Next == null)
            {
                return new Node(head.Data);
            }

            for (Node curr = head; curr != null;)
            {
                Node next = curr.Next;
                curr.Next = new Node(curr.Data);
                curr.Next.Next = next;
                curr = next;
            }

            for (Node curr = head; curr != null;)
            {
                if (curr.Next != null)
                {
                    curr.Next.Random = curr.Random != null ? curr.Random.Next : null;
                }

                curr = (curr.Next != null) ? curr.Next.Next : curr.Next;
            }


            Node original = head, copy = head.Next;

            Node temp = copy;
            while (original != null && copy != null)
            {
                original.Next = (original.Next != null) ? original.Next.Next : original.Next;

                copy.Next = (copy.Next != null) ? copy.Next.Next : copy.Next;
                original = original.Next;
                copy = copy.Next;
            }
            return temp;
        }

        // LRU Cache Design
        public static void LRUCacheDesign()
        {
            LRUCache cache = new LRUCache(4);

            cache.Set(10, 10);
            cache.Print();
            cache.Set(20, 20);
            cache.Print();
            cache.Set(10, 10);
            cache.Print();
            cache.Set(30, 30);
            cache.Print();
            cache.Set(40, 40);
            cache.Print();
            cache.Set(50, 50);
            cache.Print();
            cache.Set(30, 30);
            cache.Print();
            cache.Set(40, 40);
            cache.Print();
            cache.Set(60, 60);
            cache.Print();
            cache.Set(30, 30);
            cache.Print();
        }

        // Merge Two Sorted Single Linked List
        public static Node MergeSortedSingleLinkedLists(Node headA, Node headB)
        {
            if (headA == null)
            {
                return headB;
            }

            if (headB == null)
            {
                return headA;
            }

            Node head, tail;
            if (headA.Data <= headB.Data)
            {
                head = headA;
                headA = headA.Next;
            }
            else
            {
                head = headB;
                headB = headB.Next;
            }

            tail = head;

            while (headA != null && headB != null)
            {
                if (headA.Data <= headB.Data)
                {
                    tail.Next = headA;
                    headA = headA.Next;
                }
                else
                {
                    tail.Next = headB;
                    headB = headB.Next;
                }

                tail = tail.Next;
            }

            if (headA == null)
            {
                tail.Next = headB;
            }
            else
            {
                tail.Next = headA;
            }

            return head;
        }

        // Sort the single linked list using Merge Sort
        public static Node MergeSort(Node head)
        {
            if (head == null || head.Next == null)
            {
                return head;
            }

            Node middle = FindMiddle(head);
            Node nextofmiddle = middle.Next;
            middle.Next = null;
            Node left = MergeSort(head);
            Node right = MergeSort(nextofmiddle);
            Node sortedList = MergeSortedSingleLinkedLists(left, right);
            return sortedList;
        }

        // Merge Two Sorted Double Linked List
        public static Node MergeDLL(Node headA, Node headB)
        {
            if (headA == null)
            {
                return headB;
            }

            if (headB == null)
            {
                return headA;
            }

            Node head, tail;
            if (headA.Data <= headB.Data)
            {
                head = headA;
                headA = headA.Next;
            }
            else
            {
                head = headB;
                headB = headB.Next;
            }

            tail = head;
            while (headA != null && headB != null)
            {
                if (headA.Data <= headB.Data)
                {
                    tail.Next = headA;
                    headA.Prev = tail;
                    headA = headA.Next;

                }
                else
                {
                    tail.Next = headB;
                    headB.Prev = tail;
                    headB = headB.Next;
                }

                tail = tail.Next;
            }

            if (headA == null)
            {
                tail.Next = headB;
                headB.Prev = tail;
            }
            else
            {
                tail.Next = headA;
                headA.Prev = tail;
            }

            return head;
        }

        // Merge Sort Double Linked List
        public static Node SortDoubly(Node head)
        {
            if (head == null || head.Next == null)
            {
                return head;
            }

            Node middle = FindMiddle(head);
            Node nextofmiddle = middle.Next;
            middle.Next = null;
            nextofmiddle.Prev = null;

            Node left = MergeSort(head);
            Node right = MergeSort(nextofmiddle);
            Node sortedList = MergeDLL(left, right);
            return sortedList;
        }

        // Check if linked list is Palindrome
        public static bool IsPalindrome(Node head)
        {
            if (head == null || head.Next == null || (head.Next.Next == null && head.Data == head.Next.Data))
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

        // Remove duplicates from sorted linked list
        public static Node RemoveDuplicatesFromSortedList(Node root)
        {
            if (root == null || root.Next == null)
            {
                return root;
            }

            Node curr = root;
            while (curr != null)
            {
                while (curr.Next != null && curr.Data == curr.Next.Data)
                {
                    Node next = curr.Next;
                    curr.Next = next.Next;
                    next.Next = null;
                }

                curr = curr.Next;
            }
            return root;
        }

        // Given an unsorted linked list of N nodes. The task is to remove duplicate elements from this unsorted Linked List. When a value appears in multiple nodes, the node which appeared first should be kept, all others duplicates are to be removed.
        public static Node RemoveDuplicatesFromUnsortedLinkedLIst(Node head)
        {
            if (head == null || head.Next == null)
            {
                return head;
            }

            HashSet<int> hashSet = new HashSet<int>();
            Node curr = head, prev = null;
            while (curr != null)
            {
                Node next = curr.Next;
                if (hashSet.Contains(curr.Data))
                {
                    prev.Next = next;
                    curr.Next = null;
                }
                else
                {
                    hashSet.Add(curr.Data);
                    prev = curr;
                }
                curr = next;
            }
            return head;
        }

        //Given a singly linked list of size N, and an integer K. You need to swap the Kth node from beginning and Kth node from the end in the linked list.
        //Note: You need to swap the nodes through the links and not changing the content of the nodes.
        public static Node Swapkthnode(Node head, int N, int K)
        {
            if (K > N)
                return head;
            if (2 * K - 1 == N)
                return head;

            Node x_prev = null;
            Node x = head;

            Node y_prev = null;
            Node y = head;

            int count = K - 1;
            while (count-- > 0)
            {
                x_prev = x;
                x = x.Next;
            }

            count = N - K;
            while (count-- > 0)
            {
                y_prev = y;
                y = y.Next;
            }

            if (x_prev != null)
                x_prev.Next = y;
            if (y_prev != null)
                y_prev.Next = x;

            Node temp = x.Next;
            x.Next = y.Next;
            y.Next = temp;

            if (K == 1)
                head = y;
            if (K == N)
                head = x;
            return head;
        }

        // Rotate Linked List
        // Given a singly linked list of size N.The task is to rotate the linked list counter-clockwise by k nodes, where k is a given positive integer smaller than or equal to length of the linked list.
        public Node RotateCounterClockwise(Node head, int k)
        {
            if (head == null || head.Next == null || k == 0)
            {
                return head;
            }

            Node tail1 = head;
            for (int i = 0; i < k - 1; i++)
            {
                if (tail1 == null)
                {
                    return head;
                }

                tail1 = tail1.Next;
            }

            if (tail1 == null || tail1.Next == null)
            {
                return head;
            }

            Node first = head;
            Node second = tail1.Next;
            tail1.Next = null;
            Node tail2 = second;
            while (tail2.Next != null)
            {
                tail2 = tail2.Next;
            }

            tail2.Next = first;
            return second;
        }

        // Add two numbers represented by linked lists
        // Given two numbers represented by two linked lists of size N and M. The task is to return a sum list. The sum list is a linked list representation of the addition of two input numbers.
        //Input:
        //N = 2
        //valueN[] = {4,5}
        //M = 3
        //valueM[] = {3,4,5}
        //Output: 3 9 0  
        public static Node AddLists(Node first, Node second)
        {
            if (first == null)
            {
                return second;
            }

            if (second == null)
            {
                return first;
            }

            first = ReverseLinkedList_Iterative(first);
            second = ReverseLinkedList_Iterative(second);
            Node head = SumTwoLists(first, second);
            head = ReverseLinkedList_Iterative(head);
            return head;
        }

        public static Node SumTwoLists(Node first, Node second)
        {
            if (first == null)
            {
                return second;
            }

            if (second == null)
            {
                return first;
            }

            Node head = new Node(0);
            Node curr = head;
            int carry = 0;
            while (first != null && second != null)
            {
                int sum = first.Data + second.Data + carry;
                carry = sum / 10;
                sum = sum % 10;
                curr.Next = new Node(sum);
                curr = curr.Next;
                first = first.Next;
                second = second.Next;
            }

            if (first != null)
            {
                if (carry != 0)
                {
                    curr.Next = SumTwoLists(first, new Node(carry));
                }
                else
                {
                    curr.Next = first;
                }
            }
            else if (second != null)
            {
                if (carry != 0)
                {
                    curr.Next = SumTwoLists(second, new Node(carry));
                }
                else
                {
                    curr.Next = second;
                }
            }
            else if (carry != 0)
            {
                curr.Next = new Node(carry);
            }

            return head.Next;
        }

        // Given a linked list of 0s, 1s and 2s, sort it.
        // Given a linked list of N nodes where nodes can contain values 0s, 1s, and 2s only. The task is to segregate 0s, 1s, and 2s linked list such that all zeros segregate to head side, 2s at the end of the linked list, and 1s in the mid of 0s and 2s.
        public static Node Segregate(Node head)
        {
            if (head == null || head.Next == null)
            {
                return head;
            }

            // dummy nodes
            Node zeroD = new Node(0);
            Node oneD = new Node(0);
            Node twoD = new Node(0);

            Node p0 = zeroD, p1 = oneD, p2 = twoD;
            Node curr = head;
            while (curr != null)
            {
                if (curr.Data == 0)
                {

                    p0.Next = curr;
                    p0 = p0.Next;

                }
                else if (curr.Data == 1)
                {

                    p1.Next = curr;
                    p1 = p1.Next;

                }
                else
                {

                    p2.Next = curr;
                    p2 = p2.Next;

                }

                curr = curr.Next;
            }

            p0.Next = oneD.Next != null ? oneD.Next : twoD.Next;
            p1.Next = twoD.Next;
            p2.Next = null;
            head = zeroD.Next;
            return head;
        }

        // Merge K sorted linked lists 
        // Given K sorted linked lists of different sizes. The task is to merge them in such a way that after merging they will be a single sorted linked list.
        public static Node MergeKList(Node[] a, int N)
        {
            int last = N - 1;

            while (last != 0)
            {
                int i = 0, j = last;
                while (i < j)
                {
                    a[i] = MergeSortedSingleLinkedLists(a[i], a[j]);
                    i++;
                    j--;

                    if (i >= j)
                    {
                        last = j;
                    }
                }
            }

            return a[0];
        }

        public static Node ReverseDoubleLinkedList(Node head)
        {
            if(head == null || head.Next == null)
            {
                return head;
            }

            Node curr = head, temp = null;
            while(curr != null)
            {
                temp = curr.Prev;
                curr.Prev = curr.Next;
                curr.Next = temp;

                curr = curr.Prev;
            }

            return temp.Next;// new head
        }
    }
}
