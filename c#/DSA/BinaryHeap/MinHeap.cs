using System;

namespace DSA.BinaryHeap
{
    public class MinHeap : Heap, IMinHeap
    {
        public MinHeap() : base()
        {

        }

        public MinHeap(int cap) : base(cap)
        {

        }

        public void InsertKey(int item)
        {
            if (size == capacity)
            {
                return;
            }

            size++;
            int i = size - 1;
            arr[i] = item;
            while (i > 0 && arr[Parent(i)] > arr[i])
            {
                Util.Swap(arr, Parent(i), i);
                i = Parent(i);
            }

            // Time: O(size)
            // Aux Space: O(1)
        }

        public override int Poll() // O(Log n)
        {
            if (size == 0)
            {
                return Int32.MinValue;
            }

            return base.Poll();
        }

        public void DecreaseKey(int i, int x)
        {
            if (i >= size) return;

            arr[i] = x;
            while (i > 0 && arr[Parent(i)] > arr[i])
            {
                Util.Swap(arr, Parent(i), i);
                i = Parent(i);
            }
            // Time: O(size)
            // Aux Space: O(1)
        }

        public void Delete(int i)
        {
            DecreaseKey(i, Int32.MinValue);
            base.Poll();
        }

        public override void Heapify(int[] arr, int i, int heapSize)
        {
            int l = Left(i);
            int r = Right(i);

            int smallest = i;
            if (l < heapSize && arr[l] < arr[smallest])
            {
                smallest = l;
            }

            if (r < heapSize && arr[r] < arr[smallest])
            {
                smallest = r;
            }

            if (smallest != i)
            {
                Util.Swap(arr, i, smallest);
                Heapify(arr, smallest, heapSize);
            }
        }
    }
}
