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

    /* Redundant Braces
    Problem Description
    Given a string A denoting an expression. It contains the following operators '+', '-', '*', '/'.

    Check whether A has redundant braces or not.

    NOTE: A will be always a valid expression and will not contain any white spaces.



    Problem Constraints
    1 <= |A| <= 105



    Input Format
    The only argument given is string A.



    Output Format
    Return 1 if A has redundant braces else, return 0.



    Example Input
    Input 1:

    A = "((a+b))"
    Input 2:

    A = "(a+(a+b))"


    Example Output
    Output 1:

    1
    Output 2:

    0


    Example Explanation
    Explanation 1:

    ((a+b)) has redundant braces so answer will be 1.
    Explanation 2:

    (a+(a+b)) doesn't have have any redundant braces so answer will be 0.
    */
    public int checkForRedundantBraces(String A) {        
        
        Stack<Character> st = new Stack<Character>();

        for (int i = 0; i < A.length(); i++) {
            char ch = A.charAt(i);

            if (ch == '(') {
                st.push(ch);
            } else if (ch == ')') {
                if (st.size() > 0 && st.peek() == '(') {
                    return 1;
                }

                while (st.size() > 0) {
                    char c = st.pop();
                    if (c == '(')
                        break;
                }
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                st.push(ch);
            }
        }

        return 0;
    }

    /* Nearest smaller element (Previous smaller element)
    Given an array of +ive integers, for every i, find the nearest element on the left side of i, which is smaller than A[i]
    Elements for which there is no previous smaller element, output -1 for those elements
    i/p:  4 2  5 10 8 2
    o/p: -1 -1 2 5 5 -1

    Given an array A, find the nearest smaller element G[i] for every element A[i] in the array such that the element has an index smaller than i.

    More formally,

    G[i] for an element A[i] = an element A[j] such that

    j is maximum possible AND

    j < i AND

    A[j] < A[i]

    Elements for which no smaller element exist, consider the next smaller element as -1.



    Problem Constraints
    1 <= |A| <= 100000

    -109 <= A[i] <= 109



    Input Format
    The only argument given is integer array A.



    Output Format
    Return the integar array G such that G[i] contains the nearest smaller number than A[i]. If no such element occurs G[i] should be -1.



    Example Input
    Input 1:

    A = [4, 5, 2, 10, 8]
    Input 2:

    A = [3, 2, 1]


    Example Output
    Output 1:

    [-1, 4, -1, 2, 2]
    Output 2:

    [-1, -1, -1]


    Example Explanation
    Explanation 1:

    index 1: No element less than 4 in left of 4, G[1] = -1
    index 2: A[1] is only element less than A[2], G[2] = A[1]
    index 3: No element less than 2 in left of 2, G[3] = -1
    index 4: A[3] is nearest element which is less than A[4], G[4] = A[3]
    index 4: A[3] is nearest element which is less than A[5], G[5] = A[3]
    Explanation 2:

    index 1: No element less than 3 in left of 3, G[1] = -1
    index 2: No element less than 2 in left of 2, G[2] = -1
    index 3: No element less than 1 in left of 1, G[3] = -1
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

    public int[] nearestSmallerElementOnRightSide(int[] A) {
        int n = A.length;
        Stack<Integer> S = new Stack<Integer>();

        int[] res = new int[n];
        for (int i = n-1; i >= 0; i--) {
            while (S.size() > 0 && A[i] <= A[S.peek()]) {
                S.pop();
            }

            res[i] = S.size() == 0 ? n : S.peek();

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

    public int[] nearestGreaterElementOnRightSide(int[] A) {
        int n = A.length;

        Stack<Integer> S = new Stack<Integer>();

        int[] res = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            while (S.size() > 0 && A[i] >= A[S.peek()]) {
                S.pop();
            }

            res[i] = S.size() == 0 ? n : S.peek();

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

            // no of sub arrays where A[i] is max = count no of sub arrays between s to e (both index excluded) where A[i] is present
            // = (i - ( index of nearest greater on left +1)+1) * ((index of nearest greater on right -1) - i + 1) 
            //==> (i - index of nearest greater on left) * (index of nearest greater on right - i)

            ans += A[i] * (i - ngl[i]) * (ngr[i] - i);

            // no of sub arrays where A[i] is min
            ans -= A[i] * (i - nsl[i]) * (nsr[i] - i);
        }

        return ans;
    }

    /* Check two bracket expressions
    Problem Description
    Given two strings A and B. Each string represents an expression consisting of lowercase English alphabets, '+', '-', '(' and ')'.

    The task is to compare them and check if they are similar. If they are identical, return 1 else, return 0.

    NOTE: It may be assumed that there are at most 26 operands from ‘a’ to ‘z’, and every operand appears only once.



    Problem Constraints
    1 <= length of the each String <= 100



    Input Format
    The given arguments are string A and string B.



    Output Format
    Return 1 if they represent the same expression else return 0.



    Example Input
    Input 1:

    A = "-(a+b+c)"
    B = "-a-b-c"
    Input 2:

    A = "a-b-(c-d)"
    B = "a-b-c-d"


    Example Output
    Output 1:

    1
    Output 2:

    0


    Example Explanation
    Explanation 1:

    The expression "-(a+b+c)" can be written as "-a-b-c" which is equal as B. 
    Explanation 2:

    Both the expression are different.

    */
    boolean adjSign(String S, int i) {
        if (i == 0)
            return true;

        return S.charAt(i - 1) == '-' ? false : true;
    }

    boolean[] signMap(String S) {
        boolean[] map = new boolean[26];
        Stack<Boolean> st = new Stack<Boolean>();
        st.push(true);
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);

            if (ch == '+' || ch == '-')
                continue;

            if (ch == '(') {
                if (adjSign(S, i))
                    st.push(st.peek());
                else
                    st.push(!st.peek());

            } else if (ch == ')') {
                st.pop();
            } else {
                if (st.peek()) {
                    map[ch - 'a'] = adjSign(S, i);
                } else {
                    map[ch - 'a'] = !adjSign(S, i);
                }
            }
        }

        return map;
    }

    public int compareExpressionsIfTheyAreSimilar(String A, String B) {
        boolean[] b1 = signMap(A);
        boolean[] b2 = signMap(B);

        for (int i = 0; i < 26; i++) {
            if (b1[i] != b2[i])
                return 0;
        }

        return 1;
    }

    /* Passing game
    Problem Description
    There is a football event going on in your city. In this event, you are given A passes and players having ids between 1 and 106.

    Initially, some player with a given id had the ball in his possession. You have to make a program to display the id of the player who possessed the ball after exactly A passes.

    There are two kinds of passes:

    1) ID

    2) 0

    For the first kind of pass, the player in possession of the ball passes the ball "forward" to the player with id = ID.

    For the second kind of pass, the player in possession of the ball passes the ball back to the player who had forwarded the ball to him.

    In the second kind of pass "0" just means Back Pass.

    Return the ID of the player who currently possesses the ball.



    Problem Constraints
    1 <= A <= 100000

    1 <= B <= 100000

    |C| = A



    Input Format
    The first argument of the input contains the number A.

    The second argument of the input contains the number B ( id of the player possessing the ball in the very beginning).

    The third argument is an array C of size A having (ID/0).



    Output Format
    Return the "ID" of the player who possesses the ball after A passes.



    Example Input
    Input 1:

    A = 10
    B = 23
    C = [86, 63, 60, 0, 47, 0, 99, 9, 0, 0]
    Input 2:

    A = 1
    B = 1
    C = [2]


    Example Output
    Output 1:

    63
    Output 2:

    2


    Example Explanation
    Explanation 1:

    Initially, Player having  id = 23  posses ball. 
    After pass  1,  Player having  id = 86  posses ball. 
    After pass  2,  Player having  id = 63  posses ball. 
    After pass  3,  Player having  id = 60  posses ball. 
    After pass  4,  Player having  id = 63  posses ball. 
    After pass  5,  Player having  id = 47  posses ball. 
    After pass  6,  Player having  id = 63  posses ball. 
    After pass  7,  Player having  id = 99  posses ball. 
    After pass  8,  Player having  id = 9   posses ball. 
    After pass  9,  Player having  id = 99  posses ball. 
    After pass  10, Player having  id = 63   posses ball.
    Explanation 2:

    Ball is passed to 2.
    */
    public int passingGame(int A, int B, int[] C) {
        Stack<Integer> st = new Stack<Integer>();
        st.push(B);

        for (int i = 0; i < A; i++) {
            if (C[i] == 0) {
                st.pop();
            } else {
                st.push(C[i]);
            }
        }

        return st.peek();
    }
}
