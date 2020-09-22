namespace DSA.BinaryHeap
{
    public interface IHeap
    {
        void Add(int item);
        int Peek();
        int Poll();
        void Delete(int i);
        bool IsEmpty();
        void Heapify(int i, int heapSize);
        void BuildHeap();
    }
}
