using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Stacks
{
    // Implement Two Stacks in an array
    public class TwoStacks
    {
        int cap;
        int[] arr;
        int top1, top2;
        public TwoStacks(int cap)
        {
            this.cap = cap;
            arr = new int[cap];
            top1 = -1;
            top2 = cap;
        }

        public void Push1(int x)
        {
            if (top1 == top2 - 1)
            {
                throw new Exception("Stack Overflow");
            }

            top1++;
            arr[top1] = x;
        }

        public void Push2(int x)
        {
            if (top1 == top2 - 1)
            {
                throw new Exception("Stack Overflow");
            }

            top2--;
            arr[top2] = x;
        }

        public int Pop1()
        {
            if (top1 == -1)
            {
                throw new Exception("Stack Underflow");
            }
            int res = arr[top1];
            top1--;
            return res;
        }

        public int Pop2()
        {
            if (top2 == cap)
            {
                throw new Exception("Stack Underflow");
            }
            int res = arr[top2];
            top2++;
            return res;
        }
    }
}
