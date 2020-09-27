namespace DSA.BinaryHeap
{
    public interface IHeap
    {
        void InsertKey(int item);
        int Peek();
        int Poll();
        void Delete(int i);
        bool IsEmpty();
        void BuildHeap(int[] arr);
        void Heapify(int[] arr, int i, int heapSize);
    }
}
