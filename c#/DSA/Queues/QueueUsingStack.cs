using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Queues
{
    public class QueueUsingStack
    {
        Stack<int> s1 = new Stack<int>();
        Stack<int> s2 = new Stack<int>();

        public void Push(int x)
        {
            s1.Push(x);
        }

        public int Pop()
        {
            if (s1.Count == 0 && s2.Count == 0)
            {
                return -1;
            }

            if (s2.Count == 0)
            {
                while (s1.Count > 0)
                {
                    s2.Push(s1.Pop());
                }
            }
            return s2.Pop();
        }
    }
}
