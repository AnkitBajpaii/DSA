package Queue;

import java.util.*;

// Implement Queue using only Stack data structure.
public class QueueUsingStack {
    Stack<Integer> s1, s2;

    public void enqueue(int x) {
        s1.push(x);
        //TC : O(1)
    }

    public int dequeue() throws Exception {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }

        if (!s2.isEmpty()) {
            return s2.pop();
        }

        throw new Exception("Queue is empty");

        //TC : O(1) Amortized
    }

    public int peek() throws Exception {
        if (s2.isEmpty()) {
            throw new Exception("Queue is empty");
        }

        return s2.peek();
    }

}
