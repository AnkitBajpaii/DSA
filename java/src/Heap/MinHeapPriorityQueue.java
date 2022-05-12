package Heap;

public class MinHeapPriorityQueue {
    private int[] arr;
    private int capacity;
    private int size;

    public MinHeapPriorityQueue(int capacity) {
        this.arr = new int[capacity];

        this.capacity = capacity;

        this.size = 0;
    }

    public MinHeapPriorityQueue(int[] A) {
        this.arr = A;

        this.capacity = A.length;

        this.size = A.length;

        this.BuildHeap();
    }

    private int Left(int i) {
        return 2 * i + 1;
    }

    private int Right(int i) {
        return 2 * i + 2;
    }

    private int Parent(int i) {
        return (i - 1) / 2;
    }

    public void Offer(int item) {

        if (size == capacity)
            return;

        this.arr[size] = item;
        size++;

        int i = size - 1;
        while (i > 0 && arr[i] < arr[Parent(i)]) {
            int t = arr[i];
            arr[i] = arr[Parent(i)];
            arr[Parent(i)] = t;

            i = Parent(i);
        }
    }

    public int Poll() throws Exception {

        if (size == 0)
            throw new Exception();

        int res = this.arr[0];

        int t = arr[0];

        this.arr[0] = arr[size - 1];

        arr[size - 1] = t;

        size--;

        Heapify(0, size);

        return res;
    }

    public int Peek() throws Exception {

        if (size == 0)
            throw new Exception();

        return this.arr[0];
    }

    public void Heapify(int index, int hSize) {
        int l = Left(index);

        int r = Right(index);

        int minIndex = index;

        if (l < hSize && arr[l] < arr[minIndex]) {
            minIndex = l;
        }

        if (r < hSize && arr[r] < arr[minIndex]) {
            minIndex = r;
        }

        if (minIndex != index) {
            Heapify(minIndex, hSize);
        }
    }

    public void Heapify_Iterative(int index, int hSize) {

        while (index < hSize) {
            int l = Left(index);

            int r = Right(index);

            int minIndex = index;

            if (l < hSize && arr[l] < arr[minIndex]) {
                minIndex = l;
            }

            if (r < hSize && arr[r] < arr[minIndex]) {
                minIndex = r;
            }

            if (minIndex == index) {
                break;
            }

            int t = arr[index];
            arr[index] = arr[minIndex];
            arr[minIndex] = t;

            index = minIndex;
        }

    }

    public void BuildHeap() {
        // O(N)
        int n = this.arr.length;

        for (int i = this.Parent(n - 1); i >= 0; i--) {
            Heapify(i, n);
        }
    }

}
