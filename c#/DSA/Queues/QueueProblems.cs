using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Queues
{
    public class QueueProblems
    {
        // Reverse items of Queue
        public static void ReverseQueue_Iterative(Queue<int> q)
        {
            Stack<int> s = new Stack<int>();

            while (q.Count > 0)
            {
                s.Push(q.Dequeue());
            }

            while (s.Count > 0)
            {
                q.Enqueue(s.Pop());
            }
        }

        public static void ReverseQueue_Recursive(Queue<int> q)
        {
            if (q.Count == 0)
            {
                return;
            }

            int x = q.Dequeue();
            ReverseQueue_Recursive(q);
            q.Enqueue(x);
        }

        // Given a number n, generate first n number(in increasing order) such that all these numbers have digits in set {5, 6}
        public static string[] GenerateFirstN(int n)
        {
            Queue<string> q = new Queue<string>();
            q.Enqueue("5");
            q.Enqueue("6");

            string[] res = new string[n];
            for (int i = 0; i < n; i++)
            {
                string str = q.Dequeue();
                res[i] = str;

                q.Enqueue(res + "5");
                q.Enqueue(res + "6");
            }

            return res;
        }

        // First Circular Tour
        // Consider an arrangement where n petrol pumps are arranged in a circular manner. We need to find the first petrol pump from where we can visit all petrol pumps and come back to the petrol pump without ever going out of the petrol.
        // Suppose there is a circle. There are N petrol pumps on that circle. You will be given two sets of data.
        // 1. The amount of petrol that every petrol pump has.
        // 2. Distance from that petrol pump to the next petrol pump.
        // Find a starting point where the truck can start to get through the complete circle without exhausting its petrol in between.
        // Note :  Assume for 1 litre petrol, the truck can go 1 unit of distance.
        public static int FirstPetrolPump(int[] petrol, int[] dist, int n)
        {
            int currPetrol = 0, prevPetrol = 0, start = 0;
            for (int i = 0; i < n; i++)
            {
                currPetrol = currPetrol + (petrol[i] - dist[i]);

                if (currPetrol < 0)
                {
                    prevPetrol = prevPetrol + currPetrol;
                    currPetrol = 0;
                    start = i + 1;
                }
            }

            return currPetrol + prevPetrol >= 0 ? start + 1 : -1;
        }

        // Implement Queue using Stacks.
        // See QueueUSingStack.cs file

        // Generate Binary Numbers 
        // Given a number N. The task is to generate and print all binary numbers with decimal values from 1 to N.
        public static List<string> Generate(long n)
        {
            List<string> res = new List<string>();

            Queue<string> q = new Queue<string>();
            q.Enqueue("1");
            for (int i = 0; i < n; i++)
            {
                string x = q.Dequeue();
                q.Enqueue(x + "0");
                q.Enqueue(x + "1");
                res.Add(x);
            }

            return res;
        }

        // Reverse First K elements of Queue
        // Given an integer K and a queue of integers, we need to reverse the order of the first K elements of the queue, leaving the other elements in the same relative order.
        public Queue<int> ModifyQueue(Queue<int> q, int k)
        {
            Stack<int> s = new Stack<int>();
            int count = 0, size = q.Count;
            while (q.Count > 0 && count < k)
            {
                s.Push(q.Dequeue());
                count++;
            }

            while (s.Count > 0)
            {
                q.Enqueue(s.Pop());
            }

            count = 0;
            while (q.Count > 0 && count < size - k)
            {
                q.Enqueue(q.Dequeue());
                count++;
            }

            return q;
        }
    }
}
