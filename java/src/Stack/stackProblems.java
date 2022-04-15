package Stack;

import java.util.*;

public class stackProblems {

    public Stack<Integer> reverse(Stack<Integer> S) {
        Stack<Integer> revSt = new Stack<Integer>();

        while (S.size() > 0) {
            revSt.push(S.pop());
        }

        return revSt;
    }

    public Stack<Integer> merge(Stack<Integer> S1, Stack<Integer> S2) {
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

    public int evaluatePostfixExpression(String S) {
        String[] letters = S.split(" ");

        Stack<Integer> st = new Stack<Integer>();

        for (int i = 0; i < letters.length; i++) {
            String str = letters[i];
            if (str == " ")
                continue;

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

                st.push(Integer.parseInt(String.valueOf(str)));
            }
        }

        return st.pop();
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

}
