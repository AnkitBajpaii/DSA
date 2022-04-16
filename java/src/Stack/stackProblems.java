package Stack;

import java.util.*;

public class stackProblems {

    /* Double Character Trouble
    Problem Description
    You are given a string A.

    An operation on the string is defined as follows:

    Remove the first occurrence of the same consecutive characters. eg for a string "abbcd", the first occurrence of same consecutive characters is "bb".

    Therefore the string after this operation will be "acd".

    Keep performing this operation on the string until there are no more occurrences of the same consecutive characters and return the final string.



    Problem Constraints
    1 <= |A| <= 100000



    Input Format
    First and only argument is string A.



    Output Format
    Return the final string.



    Example Input
    Input 1:

    A = abccbc
    Input 2:

    A = ab


    Example Output
    Output 1:

    "ac"
    Output 2:

    "ab"


    Example Explanation
    Explanation 1:

    Remove the first occurrence of same consecutive characters. eg for a string "abbc", 
    the first occurrence of same consecutive characters is "bb".
    Therefore the string after this operation will be "ac".
    Explanation 2:

    No removals are to be done.
    i/p: acbbck o/p: ak
    */
    public String removeConsecutiveDuplicates(String A) {
        Stack<Character> st = new Stack<Character>();

        for (int i = A.length() - 1; i>=0; i--) {
            if (st.size() > 0 && A.charAt(i) == st.peek()) {
                st.pop();
            } else {
                st.push(A.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();

        while (st.size() > 0) {
            sb.append(st.pop());
        }

        return sb.toString();
    }

    // i/p: baaabbc o/p: c
    public String removeAllDuplicates(String S) {
        Stack<Character> st = new Stack<Character>();

        int i = 0;

        while (i < S.length()) {
            if (st.size() > 0 && S.charAt(i) == st.peek()) {
                st.pop();

                while (i < S.length() && S.charAt(i) == S.charAt(i - 1)) {
                    i++;
                }
            } else {
                st.push(S.charAt(i));
                i++;
            }
        }

        String res = "";

        while (st.size() > 0) {
            res = st.pop() + res;
        }

        return res;
    }

    public Stack<Integer> reverse(Stack<Integer> S) {
        Stack<Integer> revSt = new Stack<Integer>();

        while (S.size() > 0) {
            revSt.push(S.pop());
        }

        return revSt;
    }

    public void reverse_recursive(Stack<Integer> S) {
        if (S.size() == 0)
            return;

        int x = S.pop();
        reverse_recursive(S);

        insert_at_bottom(S, x);
    }

    public void insert_at_bottom(Stack<Integer> S, int x) {
        if (S.size() == 0) {
            S.push(x);
            return;
        }

        int y = S.pop();
        insert_at_bottom(S, x);
        S.push(y);
    }

    public void insert_item_in_sorted_stack(Stack<Integer> S, int x) {
        if (S.size() == 0 || x > S.peek()) {
            S.push(x);
            return;
        }

        int y = S.pop();
        insert_item_in_sorted_stack(S, x);

        S.push(y);
    }

    // merge two sorted stacks and return a sorted stack. Sorted stack means lowest is at bottom and highest is at top
    public Stack<Integer> merge(Stack<Integer> S1, Stack<Integer> S2) {
        if(S1.size() == 0) return S2;

        if(S2.size() == 0) return S1;

        Stack<Integer> S = new Stack<Integer>();

        while (S1.size() > 0 && S2.size() > 0) {
            if (S1.peek() > S2.peek()) {
                S.push(S1.pop());

            } else {
                S.push(S2.pop());
            }
        }

        while (S1.size() > 0) {
            S.push(S1.pop());
        }

        while (S2.size() > 0) {
            S.push(S2.pop());
        }

        S = reverse(S);

        return S;
    }

    // sort stack. Lowest should be at botton and highest should be at top
    public Stack<Integer> mergeSort(Stack<Integer> S) {
        int n = S.size();

        // base condition: input of size 1 is considered already sorted
        if (n <= 1)
            return S;

        // divide input in two half's
        Stack<Integer> S2 = new Stack<Integer>();

        for (int i = 0; i < n / 2; i++) {
            S2.push(S.pop());
        }

        // sort first half
        S = mergeSort(S);

        // sort secnnd half
        S2 = mergeSort(S2);

        return merge(S, S2);
    }

    public String convertInfixToPostfix(String infix) {

        HashMap<Character, Integer> precedenceMap = new HashMap<Character, Integer>();
        precedenceMap.put('^', 3);
        precedenceMap.put('/', 2);
        precedenceMap.put('*', 2);
        precedenceMap.put('+', 1);
        precedenceMap.put('-', 1);

        Stack<Character> st = new Stack<Character>();

        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char str = infix.charAt(i);

            if (str == '(') {
                st.push(str);

            } else if (str == ')') {
                while (st.size() > 0) {
                    char t = st.pop();
                    if (t == '(') {
                        break;
                    }

                    postfix = postfix.append(t);
                }

            } else if (str == '+' || str == '-' || str == '*' || str == '/' || str == '^') {
                // while stack has higher precedence operators, keep poping them out.
                while (st.size() > 0 && (st.peek() != '(' && precedenceMap.get(str) <= precedenceMap.get(st.peek()))) {
                    postfix = postfix.append(st.pop());
                }

                st.push(str);

            } else {

                postfix = postfix.append(str);
            }
        }

        while (st.size() > 0) {
            postfix = postfix.append(st.pop());
        }

        return postfix.toString();
    }

    public int evaluatePostfixExpression(String[] A) {

        Stack<Integer> st = new Stack<Integer>();

        for (int i = 0; i < A.length; i++) {
            String str = A[i];           

            if (str == "+" || str == "-" || str == "*" || str == "/") {

                int x = st.pop();
                int y = st.pop();

                if (str == "+") {
                    st.push(x + y);
                } else if (str == "-") {
                    st.push(x - y);
                } else if (str == "*") {
                    st.push(x * y);
                } else // if (ch == "/")
                {
                    st.push(x / y);
                }

            } else {

                st.push(Integer.parseInt(str));
            }
        }

        return st.pop();
    }

    /* Balanced Paranthesis
    Problem Description
    Given an expression string A, examine whether the pairs and the orders of “{“,”}”, ”(“,”)”, ”[“,”]” are correct in A.

    Refer to the examples for more clarity.



    Problem Constraints
    1 <= |A| <= 100



    Input Format
    The first and the only argument of input contains the string A having the parenthesis sequence.



    Output Format
    Return 0 if the parenthesis sequence is not balanced.

    Return 1 if the parenthesis sequence is balanced.



    Example Input
    Input 1:

    A = {([])}
    Input 2:

    A = (){
    Input 3:

    A = ()[] 


    Example Output
    Output 1:

    1 
    Output 2:

    0 
    Output 3:

    1 


    Example Explanation
    You can clearly see that the first and third case contain valid paranthesis.

    In the second case, there is no closing bracket for {, thus the paranthesis sequence is invalid.
    */
    public int balancedParanthesis(String A) {
        Stack<Character> st = new Stack<Character>();

        for (int i = 0; i < A.length(); i++) {
            char ch = A.charAt(i);

            if (ch == '{' || ch == '(' || ch == '[') {
                st.push(ch);
            } else {
                if (st.size() == 0)
                    return 0;

                if ((ch == '}' && st.peek() == '{')
                        || ch == ')' && st.peek() == '('
                        || ch == ']' && st.peek() == '[') {
                    st.pop();
                } else {
                    return 0;
                }
            }
        }

        return st.size() == 0 ? 1 : 0;
    }

    /* Nearest smaller element (Previous smaller element)
    Given an array of +ive integers, for every i, find the nearest element on the left side of i, which is smaller than A[i]
    Elements for which there is no previous smaller element, output -1 for those elements
    i/p:  4 2  5 10 8 2
    o/p: -1 -1 2 5 5 -1
    */
    public int[] nearestSmallerElementOnLeftSide(int[] A) {
        int n = A.length;
        Stack<Integer> S = new Stack<Integer>();

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            while (S.size() > 0 && A[i] <= A[S.peek()]) {
                S.pop();
            }

            res[i] = S.size() == 0 ? -1 : S.peek();

            S.push(i);
        }

        return res;
    }

    // Nearest greater element (Next greater element)
    // Elements for which there is no next greater element, output -1 for those elements
    public int[] nearestGreaterElementOnLeftSide(int[] A) {
        int n = A.length;

        Stack<Integer> S = new Stack<Integer>();

        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            while (S.size() > 0 && A[i] >= A[S.peek()]) {
                S.pop();
            }

            res[i] = S.size() == 0 ? -1 : S.peek();

            S.push(i);
        }

        return res;
    }

    public int[] nearestSmallerElementOnRightSide(int[] A) {
        int n = A.length;
        Stack<Integer> S = new Stack<Integer>();

        int[] res = new int[n];
        for (int i = n-1; i >= 0; i--) {
            while (S.size() > 0 && A[i] <= A[S.peek()]) {
                S.pop();
            }

            res[i] = S.size() == 0 ? -1 : S.peek();

            S.push(i);
        }

        return res;
    }

    public int[] nearestGreaterElementOnRightSide(int[] A) {
        int n = A.length;

        Stack<Integer> S = new Stack<Integer>();

        int[] res = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (S.size() > 0 && A[i] >= A[S.peek()]) {
                S.pop();
            }

            res[i] = S.size() == 0 ? -1 : S.peek();

            S.push(i);
        }

        return res;
    }

    /* Largest rectangle area in hostogram
    */
    public int largestRectangleAreaInHistogram_UsingExtraSpace(int[] A) {
        int[] nsl = nearestSmallerElementOnLeftSide(A);
        int[] nsr = nearestSmallerElementOnRightSide(A);

        int maxArea = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; i++) {
            maxArea = Math.max(maxArea, A[i] * (nsr[i] - nsl[i] - 1));
        }

        return maxArea;
        //TC: O(N), SC: O(N)
    }

    /* find sum of (max - min) of all possible sub-arrays.
    // We will use contribution technique for optimal solution.
    */
    public int sumOfMaxMinusMinForAllSbArrays(int[] A)
    {
        int[] ngl = nearestGreaterElementOnLeftSide(A);

        int[] ngr = nearestGreaterElementOnRightSide(A);

        int[] nsl = nearestSmallerElementOnLeftSide(A);

        int[] nsr = nearestSmallerElementOnRightSide(A);

        int ans = 0;

        for (int i = 0; i < A.length; i++) {
            
            int s = ngl[i]; // index of nearest greater on left
            int e = ngr[i]; // index of nearest greater on right

            // no of sub arrays where A[i] is max = count no of sub arrays between s to e (both index excluded) where A[i] is present
            // = (i - (s+1)+1) * ((e -1) - i + 1) ==> (i - s) * (e - i)

            ans += A[i] * (i - s) * (e - i);

            // no of sub arrays where A[i] is min
            s = nsl[i];
            e = nsr[i];

            ans -= A[i] * (i - s) * (e - i);
        }

        return ans;
    }
}
