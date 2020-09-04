using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Stacks
{
    public class StackUsingQueue
    {
        Queue<int> q1;
        Queue<int> q2;

        int size;
        public StackUsingQueue()
        {

        }
        public void Push(int x)
        {
            q2.Enqueue(x);
            while (q1.Count > 0)
            {
                q2.Enqueue(q1.Dequeue());
            }

            Queue<int> temp = q1;
            q1 = q2;
            q2 = temp;
            size++;
        }

        public int Pop()
        {
            if (q1.Count == 0)
            {
                throw new Exception("Stack is empty");
            }

            size--;
            return q1.Dequeue();
        }

        public int Peek()
        {
            if (q1.Count == 0)
            {
                throw new Exception("Stack is empty");
            }

            return q1.Peek();
        }
    }
}
