namespace DSA.Stacks
{
    public class StackWithGetMinInConstantTime
    {
        Stack main;
        Stack aux;

        public StackWithGetMinInConstantTime(int cap)
        {
            main = new Stack(cap);
            aux = new Stack(cap);
        }

        public void Push(int x)
        {
            main.Push(x);

            if (aux.IsEmpty() || main.Peek() <= aux.Peek())
            {
                aux.Push(x);
            }
        }

        public int Pop()
        {
            int res = main.Pop();

            if (!aux.IsEmpty() && aux.Peek() == res)
            {
                aux.Pop();
            }

            return res;
        }

        public int GetMin()
        {
            return aux.Peek();
        }
    }
}
