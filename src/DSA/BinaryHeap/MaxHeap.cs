using System;

namespace DSA.BinaryHeap
{
    public class MaxHeap : Heap, IMaxHeap
    {
        public MaxHeap(int[] arr) : base(arr)
        {

        }

        public MaxHeap(int[] arr,int cap) : base(arr,cap)
        {

        }

        public void Add(int item)
        {
            if (size == capacity)
            {
                return;
            }

            int i = size;
            arr[i] = item;
            size++;

            while (i > 0 && arr[Parent(i)] < arr[i])
            {
                Util.Swap(arr, Parent(i), i);
                i = Parent(i);
            }

            // Time: O(size)
            // Aux Space: O(1)
        }

        // Given a binary heap, with one possible voilation of min heap property, Fix the Heap
        public override void Heapify(int i, int heapSize)
        {
            int l = Left(i);
            int r = Right(i);

            int largest = i;
            if (l < heapSize && arr[l] > arr[largest])
            {
                largest = l;
            }

            if (r < heapSize && arr[r] > arr[largest])
            {
                largest = r;
            }

            if (largest != i)
            {
                Util.Swap(arr, i, largest);
                Heapify(largest, heapSize);
            }
        }

        public override int Poll()// O(Log n)
        {
            if (size == 0)
            {
                return Int32.MinValue;
            }

            return base.Poll();
        }

        public void IncreaseKey(int i, int x)
        {
            arr[i] = x;
            while (i > 0 && arr[Parent(i)] < arr[i])
            {
                Util.Swap(arr, Parent(i), i);
                i = Parent(i);
            }
            // Time: O(size)
            // Aux Space: O(1)
        }

        public void Delete(int i)
        {
            IncreaseKey(i, Int32.MaxValue);
            base.Poll();
        }
    }
}
