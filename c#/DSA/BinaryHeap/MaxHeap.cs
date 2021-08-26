using System;

namespace DSA.BinaryHeap
{
    public class MaxHeap : Heap, IMaxHeap
    {
        public MaxHeap() : base()
        {

        }

        public MaxHeap(int cap) : base(cap)
        {

        }

        public void InsertKey(int item)
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

        public override int Poll()// O(Log n)
        {
            if (size == 0)
            {
                return Int32.MaxValue;
            }

            return base.Poll();
        }

        public void IncreaseKey(int i, int x)
        {
            if (i >= size) return;

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

        public override void Heapify(int[] arr, int i, int heapSize)
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
                Heapify(arr, largest, heapSize);
            }
        }
    }
}
