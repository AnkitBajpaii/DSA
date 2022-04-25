package Queue;

import java.util.*;

public class queueProblems {

    /*
     * Reversing B Elements Of Queue
     * Problem Description
     * Given an array of integers A and an integer B, we need to reverse the order
     * of the first B elements of the array, leaving the other elements in the same
     * relative order.
     * 
     * NOTE: You are required to the first insert elements into an auxiliary queue
     * then perform Reversal of first B elements.
     * 
     * 
     * 
     * Problem Constraints
     * 1 <= B <= length of the array <= 500000
     * 1 <= A[i] <= 100000
     * 
     * 
     * 
     * Input Format
     * The argument given is the integer array A and an integer B.
     * 
     * 
     * 
     * Output Format
     * Return an array of integer after reversing the first B elements of A using
     * queue.
     * 
     * 
     * 
     * Example Input
     * Input 1:
     * 
     * A = [1, 2, 3, 4, 5]
     * B = 3
     * Input 2:
     * 
     * A = [5, 17, 100, 11]
     * B = 2
     * 
     * 
     * Example Output
     * Output 1:
     * 
     * [3, 2, 1, 4, 5]
     * Output 2:
     * 
     * [17, 5, 100, 11]
     * 
     * 
     * Example Explanation
     * Explanation 1:
     * 
     * Reverse first 3 elements so the array becomes [3, 2, 1, 4, 5]
     * Explanation 2:
     * 
     * Reverse first 2 elements so the array becomes [17, 5, 100, 11]
     * 
     * See Expected Output
     */
    public int[] reverseBElements(int[] A, int B) {
        if (B == 1)
            return A;

        Queue<Integer> q = new ArrayDeque<Integer>();

        for (int i = 0; i < A.length; i++) {
            q.offer(A[i]);
        }

        Stack<Integer> st = new Stack<Integer>();

        while (B > 0) {
            st.push(q.poll());
            B--;
        }

        while (st.size() > 0) {
            q.offer(st.pop());
        }

        int k = A.length - B;
        while (k > 0) {
            q.offer(q.poll());
            k--;
        }

        int[] res = new int[A.length];

        int i = 0;
        while (q.size() > 0) {
            res[i++] = q.poll();
        }

        return res;
    }

    /*
     * find nth number using 1, 2 or 3 as digits
     */
    public int[] NIntegersContaining1_2_3(int A) {
        int[] res = new int[A];

        Queue<Integer> q = new ArrayDeque<Integer>();

        q.offer(1);
        q.offer(2);
        q.offer(3);

        int count = 3;
        int i = 0;
        while (i < A) {
            int x = q.poll();

            res[i++] = x;

            if (count >= A)
                continue;

            q.offer(x * 10 + 1);
            q.offer(x * 10 + 2);
            q.offer(x * 10 + 3);

            count += 3;
        }

        return res;
    }

    String reverse(String S) {
        char[] arr = S.toCharArray();
        int s = 0, e = S.length() - 1;
        while (s < e) {
            char temp = arr[s];
            arr[s] = arr[e];
            arr[e] = temp;

            s++;
            e--;
        }

        return new String(arr);
    }

    /* Perfect Numbers
     * Given an integer A, you have to find the Ath Perfect Number.

    A Perfect Number has the following properties:

    It comprises only 1 and 2.

    The number of digits in a Perfect number is even.

    It is a palindrome number.

    For example, 11, 22, 112211 are Perfect numbers, where 123, 121, 782, 1 are not.



    Problem Constraints
    1 <= A <= 100000



    Input Format
    The only argument given is an integer A.



    Output Format
    Return a string that denotes the Ath Perfect Number.



    Example Input
    Input 1:

    A = 2
    Input 2:

    A = 3


    Example Output
    Output 1:

    22
    Output 2:

    1111


    Example Explanation
    Explanation 1:

    First four perfect numbers are:
    1. 11
    2. 22
    3. 1111
    4. 1221
    Explanation 2:

    First four perfect numbers are:
    1. 11
    2. 22
    3. 1111
    4. 1221
    *
*/
    public String findNthPerfectNumber(int n) {
        Queue<String> q = new ArrayDeque<String>();

        q.offer("1");
        q.offer("2");

        int count = 0;
        while (true) {
            String x = q.poll();
            count++;

            if (count == n) {
                return x + reverse(x);
            }

            q.offer(x + "1");
            q.offer(x + "2");
        }
    }
    
    // reverse a queue
    public void ReverseQueue(Queue<Integer> q) {
        Stack<Integer> st = new Stack<Integer>();

        while (!q.isEmpty()) {
            st.push(q.poll());
        }

        while (!st.isEmpty()) {
            q.offer(st.pop());
        }
    }

    /* Task Scheduling
    A CPU has N tasks to be performed. It is to be noted that the tasks have to be completed in a specific order to avoid deadlock. In every clock cycle, the CPU can either perform a task or move it to the back of the queue. You are given the current state of the scheduler queue in array A and the required order of the tasks in array B.

    Determine the minimum number of clock cycles to complete all the tasks.


    Problem Constraints
    1 <= N <= 1000
    1 <= A[i], B[i] <= N


    Input Format
    First argument consist of integer array A.
    Second argument consist of integer array B.


    Output Format
    Return an integer denoting the minimum number of clock cycles required to complete all the tasks.



    Example Input
    Input 1:

    A = [2, 3, 1, 5, 4]
    B = [1, 3, 5, 4, 2]
    Input 2:

    A = [1]
    B = [1]


    Example Output
    Output 1:

    10
    Output 2:

    1


    Example Explanation
    Explanation 1:

    According to the order array B task 1 has to be performed first, so the CPU will move tasks 2 and 3 to the end of the queue (in 2 clock cycles).
    Total clock cycles till now = 2
    Now CPU will perform task 1.
    Total clock cycles till now = 3
    Now, queue becomes [5, 4, 2, 3]
    Now, CPU has to perform task 3. So it moves tasks 5, 4, and 2 to the back one-by-one.
    Total clock cycles till now = 6
    Now all the tasks in the queue are as in the required order so the CPU will perform them one-by-one.
    Total clock cycles = 10
    Explanation 2:

    Directly task 1 is completed.
    */
    public int TaskScheduling(int[] A, int[] B) {

        Queue<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < A.length; i++) {
            q.offer(A[i]);
        }

        int count = 0;
        for (int i = 0; i < B.length; i++) {
            while (q.size() > 0) {
                int task = q.poll();
                count++;

                if (task != B[i]) {
                    q.offer(task);
                } else {
                    break;
                }
            }
        }

        return count;
        
    }

    /* Sliding Window Maximum

    Problem Description
    Given an array of integers A. There is a sliding window of size B, moving from the very left of the array to the very right. You can only see the B numbers in the window. Each time the sliding window moves rightwards by one position. You have to find the maximum for each window.

    Return an array C, where C[i] is the maximum value in the array from A[i] to A[i+B-1].

    Refer to the given example for clarity.

    NOTE: If B > length of the array, return 1 element with the max of the array.



    Problem Constraints
    1 <= |A|, B <= 106



    Input Format
    The first argument given is the integer array A.

    The second argument given is the integer B.



    Output Format
    Return an array C, where C[i] is the maximum value of from A[i] to A[i+B-1].



    Example Input
    Input 1:

    A = [1, 3, -1, -3, 5, 3, 6, 7]
    B = 3
    Input 2:

    A = [1, 2, 3, 4, 2, 7, 1, 3, 6]
    B = 6


    Example Output
    Output 1:

    [3, 3, 5, 5, 6, 7]
    Output 2:

    [7, 7, 7, 7]


    Example Explanation
    Explanation 1:

    Window position     | Max
    --------------------|-------
    [1 3 -1] -3 5 3 6 7 | 3
    1 [3 -1 -3] 5 3 6 7 | 3
    1 3 [-1 -3 5] 3 6 7 | 5
    1 3 -1 [-3 5 3] 6 7 | 5
    1 3 -1 -3 [5 3 6] 7 | 6
    1 3 -1 -3 5 [3 6 7] | 7
    Explanation 2:

    Window position     | Max
    --------------------|-------
    [1 2 3 4 2 7] 1 3 6 | 7
    1 [2 3 4 2 7 1] 3 6 | 7
    1 2 [3 4 2 7 1 3] 6 | 7
    1 2 3 [4 2 7 1 3 6] | 7
    */
    public int[] slidingMaximum(final int[] A, int B) {
        Deque<Integer> dq = new ArrayDeque<Integer>();

        for (int i = 0; i < B && i < A.length; i++) {
            while (!dq.isEmpty() && A[i] > dq.peekLast()) {
                dq.removeLast();
            }

            dq.addLast(A[i]);
        }

        if (B >= A.length) {
            return new int[] { dq.peekFirst() };
        }

        int[] res = new int[A.length - B + 1];
        res[0] = dq.peek();

        for (int i = B; i < A.length; i++) {
            if (!dq.isEmpty() && dq.peekFirst() == A[i - B]) {
                dq.removeFirst();
            }

            while (!dq.isEmpty() && A[i] > dq.peekLast()) {
                dq.removeLast();
            }

            dq.addLast(A[i]);

            res[i - B + 1] = dq.peekFirst();
        }

        return res;
    }

    /* First non-repeating character
    Problem Description
    Given a string A denoting a stream of lowercase alphabets, you have to make a new string B.
    B is formed such that we have to find the first non-repeating character each time a character is inserted to the stream and append it at the end to B. If no non-repeating character is found, append '#' at the end of B.



    Problem Constraints
    1 <= |A| <= 100000



    Input Format
    The only argument given is string A.



    Output Format
    Return a string B after processing the stream of lowercase alphabets A.



    Example Input
    Input 1:

    A = "abadbc"
    Input 2:

    A = "abcabc"


    Example Output
    Output 1:

    "aabbdd"
    Output 2:

    "aaabc#"


    Example Explanation
    Explanation 1:

    "a"      -   first non repeating character 'a'
    "ab"     -   first non repeating character 'a'
    "aba"    -   first non repeating character 'b'
    "abad"   -   first non repeating character 'b'
    "abadb"  -   first non repeating character 'd'
    "abadbc" -   first non repeating character 'd'
    Explanation 2:

    "a"      -   first non repeating character 'a'
    "ab"     -   first non repeating character 'a'
    "abc"    -   first non repeating character 'a'
    "abca"   -   first non repeating character 'b'
    "abcab"  -   first non repeating character 'c'
    "abcabc" -   no non repeating character so '#'
    */
    public String firstNonRepeatingCharacter(String A) {
        Queue<Character> q = new ArrayDeque<Character>();
        int[] freq = new int[26];

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < A.length(); i++) {
            freq[A.charAt(i) - 'a']++;

            while (!q.isEmpty() && freq[q.peek() - 'a'] > 1) {
                q.poll();
            }

            if (freq[A.charAt(i) - 'a'] == 1) {
                q.offer(A.charAt(i));
            }

            sb.append(q.isEmpty() ? '#' : q.peek());
        }

        return sb.toString();
    }
}
