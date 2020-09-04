using System;
using System.Collections.Generic;
using System.Text;

namespace DSA.Queues
{
    // Efficient Implementation
    public class Queue
    {
        int cap, size, front;
        int[] arr;

        public Queue(int cap)
        {
            this.cap = cap;
            size = 0;
            front = 0;
            arr = new int[cap];
        }

        public bool IsFull()
        {
            return size == cap;
        }

        public bool IsEmpty()
        {
            return size == 0;
        }

        int GetRear()
        {
            if (IsEmpty())
            {
                return -1;
            }

            return front + size - 1;
        }

        public void Enqueue(int x)
        {
            if (IsFull())
            {
                return;
            }

            int rear = GetRear();
            rear = (rear + 1) % cap;
            arr[rear] = x;
            size++;
        }

        public int Dequeue()
        {
            if (IsEmpty())
            {
                throw new Exception("Queue is Empty");
            }

            int x = arr[front];
            front = (front + 1) % cap;
            size--;
            return x;
        }
    }
}
