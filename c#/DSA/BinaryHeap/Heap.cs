using System;

namespace DSA.BinaryHeap
{
    public abstract class Heap
    {
        const int DEFAULT_CAP = 1000;

        protected int[] arr;
        protected int size, capacity;

        public Heap() : this(DEFAULT_CAP)
        {

        }
        public Heap(int cap)
        {
            size = 0;
            capacity = cap;
            this.arr = new int[cap];
        }

        public void BuildHeap(int[] arr)
        {
            int size = arr.Length;
            // right most bottom most non leaf node will be the parent of last element which is ((size - 1) - 1)/2
            for (int i = (size - 2) / 2; i >= 0; i--)
            {
                Heapify(arr, i, this.size);
            }
        }

        public abstract void Heapify(int[] arr, int i, int heapSize);

        public int Peek()
        {
            return arr[0];
        }

        protected int Parent(int i)
        {
            return (i - 1) / 2;
        }

        protected int Left(int i)
        {
            return 2 * i + 1;
        }

        protected int Right(int i)
        {
            return 2 * i + 2;
        }

        public virtual int Poll()
        {
            if (size == 1)
            {
                size--;
                return arr[0];
            }

            int res = arr[0];
            Util.Swap(arr, 0, size - 1);
            size--;

            Heapify(arr, 0, size);
            return res;
        }

        public bool IsEmpty()
        {
            return size <= 0;
        }
    }
}
