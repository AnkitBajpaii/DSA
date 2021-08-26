namespace DSA.BinaryHeap
{
    public class HeapSort
    {
        public static void Sort(int[] arr)
        {
            int n = arr.Length;
            IHeap maxHeap = new MaxHeap(n);

            maxHeap.BuildHeap(arr);

            for (int i = n - 1; i > 0; i--)
            {
                Util.Swap(arr, 0, i);
                maxHeap.Heapify(arr, 0, i);
            }
        }
    }
}
