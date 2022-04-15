package linkedList;
import java.util.*;

public class linkedListProblems {
    /*
     * Middle element of linked list
     * Given a linked list of integers, find and return the middle element of the
     * linked list.
     * 
     * NOTE: If there are N nodes in the linked list and N is even then return the
     * (N/2 + 1)th element.
     * 
     * 
     * 
     * Problem Constraints
     * 1 <= length of the linked list <= 100000
     * 
     * 1 <= Node value <= 109
     * 
     * 
     * 
     * Input Format
     * The only argument given head pointer of linked list.
     * 
     * 
     * 
     * Output Format
     * Return the middle element of the linked list.
     * 
     * 
     * 
     * Example Input
     * Input 1:
     * 
     * 1 -> 2 -> 3 -> 4 -> 5
     * Input 2:
     * 
     * 1 -> 5 -> 6 -> 2 -> 3 -> 4
     * 
     * 
     * Example Output
     * Output 1:
     * 
     * 3
     * Output 2:
     * 
     * 2
     * 
     * 
     * Example Explanation
     * Explanation 1:
     * 
     * The middle element is 3.
     * Explanation 2:
     * 
     * The middle element in even length linked list of length N is ((N/2) + 1)th
     * element which is 2.
     */
    public ListNode FindMiddleElement(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public ListNode FindMiddleElement_2_findingFirstMid(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /* Delete middle node of a Linked List
    */
    public ListNode DeleteMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = slow.next;
        slow.next = null;

        return head;
    }

    /* Reverse Linked List
    */
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode curr = head, prev = null;
        while(curr != null)
        {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
    /* K reverse linked list
        Given a singly linked list head and an integer K, reverse the nodes of the list B at a time and return the modified linked list.
    */
    public ListNode reverseInGroupOfK(ListNode head, int K) {

        if (head == null || head.next == null)
            return head;

        ListNode curr = head, prev = null;
        int count = K;
        while (curr != null && count > 0) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count--;
        }

        head.next = reverseInGroupOfK(curr, K);

        return prev;
    }
    /* Reverse Link List II
    Reverse a linked list A from position B to C.

    NOTE: Do it in-place and in one-pass.
    Problem Description
    Reverse a linked list A from position B to C.

    NOTE: Do it in-place and in one-pass.



    Problem Constraints
    1 <= |A| <= 106

    1 <= B <= C <= |A|



    Input Format
    The first argument contains a pointer to the head of the given linked list, A.

    The second arugment contains an integer, B.

    The third argument contains an integer C.



    Output Format
    Return a pointer to the head of the modified linked list.



    Example Input
    Input 1:

    A = 1 -> 2 -> 3 -> 4 -> 5
    B = 2
    C = 4

    Input 2:

    A = 1 -> 2 -> 3 -> 4 -> 5
    B = 1
    C = 5


    Example Output
    Output 1:

    1 -> 4 -> 3 -> 2 -> 5
    Output 2:

    5 -> 4 -> 3 -> 2 -> 1


    Example Explanation
    Explanation 1:

    In the first example, we want to reverse the highlighted part of the given linked list : 1 -> 2 -> 3 -> 4 -> 5 
    Thus, the output is 1 -> 4 -> 3 -> 2 -> 5 
    Explanation 2:

    In the second example, we want to reverse the highlighted part of the given linked list : 1 -> 4 -> 3 -> 2 -> 5  
    Thus, the output is 5 -> 4 -> 3 -> 2 -> 1 
    */
    public ListNode reverseBetween(ListNode A, int B, int C) {
        int count = C - B + 1;
        if (A == null || count <= 1)
            return A;

        ListNode curr = A, prev = null;
        int pos = 1;
        while (curr != null) {

            if (pos == B) {
                ListNode p = null, h = curr;
                while (curr != null & count > 0) {
                    ListNode next = curr.next;
                    curr.next = p;
                    p = curr;
                    curr = next;
                    count--;
                }

                if (prev != null) {
                    prev.next = p;
                }

                h.next = curr;
                if (B == 1)
                    return p;

                break;
            }

            prev = curr;
            curr = curr.next;
            pos++;
        }

        return A;
    }

    /* Insert node at a given position
    */
    public ListNode insert_node(ListNode head, int position, int value) {
        ListNode n = new ListNode(value);

        if (head == null || position == 1) {
            n.next = head;
            head = n;
            return head;
        }

        ListNode curr = head;
        int pos = 1;
        while (curr != null && pos < position - 1) {
            curr = curr.next;
            pos++;
        }

        if (curr == null)
            return head;

        n.next = curr.next;
        curr.next = n;

        return head;
    }

    /* Delete node at a given position
    */
    public ListNode delete_node(ListNode head, int position) {
        if (head == null)
            return null;

        if (position == 1) {
            head = head.next;
            return head;
        }

        ListNode curr = head;
        int pos = 1;
        while (curr != null && pos < position - 1) {
            curr = curr.next;
            pos++;
        }

        if (curr == null)
            return head;

        ListNode next = curr.next;
        if (next == null)
            return head;

        curr.next = next.next;
        next.next = null;

        return head;
    }

    /* Remove Nth Node from List End
    Problem Description
    Given a linked list A, remove the B-th node from the end of the list and return its head.

    For example, Given linked list: 1->2->3->4->5, and B = 2. After removing the second node from the end, the linked list becomes 1->2->3->5.

    NOTE: If B is greater than the size of the list, remove the first node of the list.

    NOTE: Try doing it using constant additional space.



    Problem Constraints
    1 <= |A| <= 106



    Input Format
    The first argument of input contains a pointer to the head of the linked list.

    The second argument of input contains the integer B.



    Output Format
    Return the head of the linked list after deleting the B-th element from the end.



    Example Input
    Input 1:

    A = [1, 2, 3, 4, 5]
    B = 2
    Input 2:

    A = [1]
    B = 1


    Example Output
    Output 1:

    [1, 2, 3, 5]
    Output 2:

    [] 


    Example Explanation
    Explanation 1:

    In the first example, 4 is the second last element.
    Explanation 2:

    In the second example, 1 is the first and the last element.
    */
    public ListNode removeNthFromEnd(ListNode head, int B) {
        if(head == null || head.next == null) return null;

        ListNode p1 = head, p2 = head;
        while(p1 != null && B > 0)
        {
            p1 = p1.next;
            B--;
        }

        if(p1 == null){
            return head.next;
        }

        while(p1.next != null)
        {
            p1 = p1.next;
            p2 = p2.next;
        }        
        
        p2.next = p2.next.next;
        return head;
    }

    /* Remove Duplicates from Sorted List
    Problem Description
    Given a sorted linked list, delete all duplicates such that each element appears only once.



    Problem Constraints
    0 <= length of linked list <= 106



    Input Format
    First argument is the head pointer of the linked list.



    Output Format
    Return the head pointer of the linked list after removing all duplicates.



    Example Input
    Input 1:

    1->1->2
    Input 2:

    1->1->2->3->3


    Example Output
    Output 1:

    1->2
    Output 2:

    1->2->3


    Example Explanation
    Explanation 1:

    Each element appear only once in 1->2.
        */
    public ListNode deleteDuplicates(ListNode A) {
        if (A == null || A.next == null)
            return A;

        ListNode curr = A;
        while (curr != null) {
            while (curr != null && curr.next != null && curr.val == curr.next.val) {
                curr.next = curr.next.next;
            }

            curr = curr.next;
        }

        return A;
    }

    /* Merge Two Sorted Lists
    Problem Description
    Merge two sorted linked lists, A and B, and return it as a new list.

    The new list should be made by splicing together the nodes of the first two lists and should also be sorted.



    Problem Constraints
    0 <= |A|, |B| <= 105



    Input Format
    The first argument of input contains a pointer to the head of linked list A.

    The second argument of input contains a pointer to the head of linked list B.



    Output Format
    Return a pointer to the head of the merged linked list.



    Example Input
    Input 1:

    A = 5 -> 8 -> 20
    B = 4 -> 11 -> 15
    Input 2:

    A = 1 -> 2 -> 3
    B = Null


    Example Output
    Output 1:

    4 -> 5 -> 8 -> 11 -> 15 -> 20
    Output 2:

    1 -> 2 -> 3


    Example Explanation
    Explanation 1:

    Merging A and B will result in 4 -> 5 -> 8 -> 11 -> 15 -> 20 
    Explanation 2:

    We don't need to merge as B is empty. 
    */
    public ListNode mergeTwoLists(ListNode h1, ListNode h2) {
        if (h1 == null)
            return h2;

        if (h2 == null)
            return h1;

        ListNode h;

        if (h1.val < h2.val) {
            h = h1;
            h1 = h1.next;
        } else {
            h = h2;
            h2 = h2.next;
        }

        ListNode t = h;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                t.next = h1;
                h1 = h1.next;
            } else {
                t.next = h2;
                h2 = h2.next;
            }

            t = t.next;
        }

        if (h1 != null) {
            t.next = h1;
        } else {
            t.next = h2;
        }

        return h;
    }

    /* Merge Two sorted list in descender order
       The order of final list should be sorted in descending order
    */
    public ListNode mergeTwoListsInDescendingOrder(ListNode h1, ListNode h2) {
        if (h1 == null)
            return reverseList(h2);

        if (h2 == null)
            return reverseList(h1);

        ListNode t;

        if (h1.val < h2.val) {
            t = h1;

            ListNode next = h1.next;
            h1.next = null;
            h1 = next;
        } else {
            t = h2;
            
            ListNode next = h2.next;
            h2.next = null;
            h2 = next;
        }
        
        while (h1 != null && h2 != null) {
            ListNode next;

            if (h1.val < h2.val) {

                next = h1.next;
                h1.next = t;
                t = h1;
                h1 = next;
            } else {
                
                next = h2.next;
                h2.next = t;
                t = h2;
                h2 = next;
            }
        }

        while(h1 != null)
        {
            ListNode next = h1.next;
            h1.next = t;
            t = h1;
            h1 = next;
        }

        while(h2 != null)
        {
            ListNode next = h2.next;
            h2.next = t;
            t = h2;
            h2 = next;            
        }

        return t;
    }

    public ListNode insertAtBegin(ListNode head, int val)
    {
        ListNode n = new ListNode(val);
        if (head == null) {
            return n;
        }

        n.next = head;
        return n;
    }

    public void print(ListNode head)
    {
        ListNode curr = head;
        while(curr != null)
        {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    /* Given a LL,sort it using MergeSort
        Sort a linked list, A in O(n log n) time using constant space complexity.
    */
    public ListNode sortList(ListNode A) {
        //asumption: sortList function when given a node sorts the entire list starting this node.

        //base condition: input of size 1 is considered already sorted
        if(A == null || A.next == null) return A;
        
        // divide input in two half's
        ListNode mid = FindMiddleElement_2_findingFirstMid(A);
        ListNode mid2 = mid.next;
        mid.next = null;

        // sort first half
        ListNode h1 = sortList(A);

        // sort second half
        ListNode h2 = sortList(mid2);

        return mergeTwoLists(h1, h2);
    }

    /* Reorder List
    Problem Description

    Given a singly linked list A

    A: A0 → A1 → … → An-1 → An 
    reorder it to:

    A0 → An → A1 → An-1 → A2 → An-2 → … 
    You must do this in-place without altering the nodes' values.


    Problem Constraints

    1 <= |A| <= 106



    Input Format

    The first and the only argument of input contains a pointer to the head of the linked list A.



    Output Format

    Return a pointer to the head of the modified linked list.



    Example Input

    Input 1:

    A = [1, 2, 3, 4, 5] 
    Input 2:

    A = [1, 2, 3, 4] 


    Example Output

    Output 1:

    [1, 5, 2, 4, 3] 
    Output 2:

    [1, 4, 2, 3] 


    Example Explanation

    Explanation 1:

    The array will be arranged to [A0, An, A1, An-1, A2].
    Explanation 2:

    The array will be arranged to [A0, An, A1, An-1, A2].
    */
    public ListNode reorderList(ListNode A) {
        if (A == null || A.next == null)
            return A;

        ListNode mid = FindMiddleElement_2_findingFirstMid(A);
        ListNode h2 = mid.next;
        mid.next = null;

        h2 = reverseList(h2);

        ListNode i = A, j = h2, t = A;
        boolean flag = true;
        while (i != null && j != null) {
            if (flag) {
                i = i.next;
                t.next = j;
            } else {
                j = j.next;
                t.next = i;
            }

            t = t.next;
            flag = !flag;
        }

        return A;
    }

    /* Given a 2D list where each list is sorted. Flatten it to a signly linked list(Sorted)
    */
    public ListNode Flatten2DList(ListNode2D head) {
        //assumption : Flatten2DList(A) merges all lists where heads are connected to node into a signly linked list

        if (head == null || head.down == null)
            return head;

        ListNode2D slow = head, fast = head;
        while (fast.down != null && fast.down.down != null) {
            slow = slow.down;
            fast = fast.down.down;
        }

        ListNode2D mid = slow;
        ListNode2D h2 = mid.down;
        mid.down = null;

        ListNode head1 = Flatten2DList(head);// sort first half
        ListNode head2 = Flatten2DList(h2);// sort second half

        return mergeTwoLists(head1, head2);//merge 
    }

    /* Remove Loop from Linked List
    Problem Description
    You are given a linked list that contains a loop.
    You need to find the node, which creates a loop and break it by making the node point to NULL.



    Problem Constraints
    1 <= number of nodes <= 1000



    Input Format
    Only argument is the head of the linked list.



    Output Format
    return the head of the updated linked list.



    Example Input
    Input 1:

    
    1 -> 2
    ^    |
    | - - 
    Input 2:

    3 -> 2 -> 4 -> 5 -> 6
            ^         |
            |         |    
            - - - - - -


    Example Output
    Output 1:

    1 -> 2 -> NULL
    Output 2:

    3 -> 2 -> 4 -> 5 -> 6 -> NULL


    Example Explanation
    Explanation 1:

    Chain of 1->2 is broken.
    Explanation 2:

    Chain of 4->6 is broken.
    */
    public ListNode solve(ListNode head) {
        
        if(head == null || head.next == null) return head;

        if(head.next == head)
        {
            head.next = null;
            return head;
        }

        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast)
            {
                break;
            }
        }

        if(slow != fast)
        {
            return head;
        }

        slow = head;
        ListNode prev = null;
        while(slow != fast)
        {
            slow = slow.next;
            prev = fast;
            fast = fast.next;
        }

        prev.next = null;
        return head;
    }

    /* Clone Copy List

    */
    class RandomListNode {
      
        int label;
      
        RandomListNode next, random;
      
        RandomListNode(int x) { this.label = x; }
    };

    public RandomListNode copyRandomList_UsingHashMap(RandomListNode head) {
        if (head == null)
            return null;

        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

        RandomListNode curr = head;
        while (curr != null) {
            map.put(curr, new RandomListNode(curr.label));

            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            RandomListNode n = map.get(curr);
            n.next = map.get(curr.next);
            n.random = curr.random == null ? null : map.get(curr.random);

            curr = curr.next;
        }

        return map.get(head);
    }

    /* Palindrome List
    */
    public int lPalin(ListNode A) {

        ListNode mid = FindMiddleElement_2_findingFirstMid(A);

        ListNode h2 = reverseList(mid.next);
        mid.next = null;

        ListNode h1 = A;
        while (h1 != null && h2 != null) {
            if (h1.val != h2.val)
                return 0;

            h1 = h1.next;
            h2 = h2.next;
        }

        return 1;
    }
    
}