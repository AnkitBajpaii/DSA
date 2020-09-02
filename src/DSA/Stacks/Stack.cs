using DSA.LinkedList;
using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Stacks
{
    public class Stack
    {
        readonly int cap;
        int top;
        int[] arr;
        public Stack(int cap)
        {
            this.cap = cap;
            this.top = -1;
            arr = new int[cap];
        }

        public void Push(int x)
        {
            if (top == cap - 1)
            {
                throw new Exception("Stack Overflow");
            }

            top++;
            arr[top] = x;
        }

        public int Pop()
        {
            if (IsEmpty())
            {
                throw new Exception("Stack Underflow");
            }

            int res = arr[top];
            top--;
            return res;
        }

        public int Peek()
        {
            if (IsEmpty())
            {
                throw new Exception("Stack is empty");
            }
            int res = arr[top];
            return res;
        }

        public int Size()
        {
            return top + 1;
        }

        public bool IsEmpty()
        {
            return top == -1;
        }
    }

    public class DynamicStack
    {
        int top;
        List<int> arr;

        public DynamicStack(int cap)
        {
            this.top = -1;
            arr = new List<int>(cap);
        }

        public void Push(int x)
        {
            top++;
            arr[top] = x;
        }

        public int Pop()
        {
            if (IsEmpty())
            {
                throw new Exception("Stack Underflow");
            }

            int res = arr[top];
            top--;
            return res;
        }

        public int Peek()
        {
            if (IsEmpty())
            {
                throw new Exception("Stack is empty");
            }
            int res = arr[top];
            return res;
        }

        public int Size()
        {
            return top + 1;
        }

        public bool IsEmpty()
        {
            return top == -1;
        }
    }

    public class StackLL
    {
        int len;
        Node head;
        public StackLL()
        {
            head = null;
            len = 0;
        }

        public void Push(int x)
        {
            Node temp = new Node(x);
            temp.Next = head;
            head = temp;
            len++;
        }

        public int Pop()
        {
            if (IsEmpty())
            {
                throw new Exception("Stack Underflow");
            }

            int res = head.Data;
            Node temp = head;
            head = head.Next;
            temp.Next = null;
            len--;
            return res;
        }

        public bool IsEmpty()
        {
            return head == null;
        }

        public int Size()
        {
            return len;
        }
    }
}
