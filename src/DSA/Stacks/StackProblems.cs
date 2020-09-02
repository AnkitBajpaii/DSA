using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;

namespace DSA.Stacks
{
    public class StackProblems
    {
        // Balanced Paranthesis
        // Given a string of parenthesis ({, }, (, ), [ and ]), we need to check if this string is balanced or not.
        public static bool IsBalanced(string str)
        {
            Stack<char> stack = new Stack<char>();
            for (int i = 0; i < str.Length; i++)
            {
                if (str[i] == '(' || str[i] == '{' || str[i] == '[')
                {
                    stack.Push(str[i]);
                }
                else
                {
                    // stack is empty
                    if (stack.Count == 0)
                    {
                        return false;
                    }

                    char top = stack.Peek();
                    bool isMatching = (top == '(' && str[i] == ')') || (top == '{' && str[i] == '}') || (top == '[' && str[i] == ']');
                    if (!isMatching)
                    {
                        return false;
                    }

                    stack.Pop();
                }
            }

            return stack.Count == 0;
        }

        // Implement Two Stacks in an array
        public static void ImplementTwoStacks()
        {
            TwoStacks s = new TwoStacks(4);
            s.Push1(10);
            s.Push2(20);
        }

        //  Implementation of K Stacks in an array

        // The stock span problem is a financial problem where we have a series of n daily price quotes for a stock and we need to calculate span of stock’s price for all n days.
        // The span Si of the stock’s price on a given day i is defined as the maximum number of consecutive days just before the given day, for which the price of the stock on the current day is less than or equal to its price on the given day.
        // For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
        public static int[] StockSpan(int[] price, int n)
        {
            int[] span = new int[n];
            Stack<int> s = new Stack<int>();
            s.Push(0);
            span[0] = 1;
            for (int i = 1; i < n; i++)
            {
                while (s.Count > 0 && price[i] >= price[s.Peek()])
                {
                    s.Pop();
                }

                span[i] = s.Count == 0 ? i + 1 : i - s.Peek();
                s.Push(i);
            }
            return span;
        }

        // Previous Greater Element
        // Given an array of distinct integers, find the closest (position-wise closest and on the left side) greater on left of every element. If there is no greater element on l;eft, then print -1
        public static int[] PreviousGreater(int[] arr, int n)
        {
            int[] res = new int[n];
            Stack<int> s = new Stack<int>();
            s.Push(0);
            res[0] = -1;
            for (int i = 1; i < n; i++)
            {
                while (s.Count > 0 && arr[i] >= arr[s.Peek()])
                {
                    s.Pop();
                }

                res[i] = s.Count == 0 ? -1 : arr[s.Peek()];
                s.Push(i);
            }
            return res;
        }

        // Next Greater Element
        // Given an array of distinct integers, find the NextGreater(position-wise closest and on the right side) of every array elements.
        // variation of previous prblem, instead of traversing left to right, traverse right to left. 
        public static int[] NextGreater(int[] arr, int n)
        {
            int[] res = new int[n];
            Stack<int> s = new Stack<int>();
            s.Push(n - 1);
            res[n - 1] = -1;
            for (int i = n - 2; i >= 0; i--)
            {
                while (s.Count > 0 && arr[i] >= arr[s.Peek()])
                {
                    s.Pop();
                }

                res[i] = s.Count == 0 ? -1 : arr[s.Peek()];
                s.Push(i);
            }
            return res;
        }

        // Previous Smaller
        // Given an array of distinct integers, find the closest (position-wise closest and on the left side) smaller on left of every element. If there is no smaller element on left, then print -1
        public static int[] PreviousSmaller(int[] arr, int n)
        {
            int[] res = new int[n];
            Stack<int> s = new Stack<int>();
            s.Push(0);
            res[0] = -1;
            for (int i = 1; i < n; i++)
            {
                while (s.Count > 0 && arr[i] <= arr[s.Peek()])
                {
                    s.Pop();
                }

                res[i] = s.Count == 0 ? -1 : arr[s.Peek()];
                s.Push(i);
            }
            return res;
        }

        // Next Smaller Element
        // Given an array of distinct integers, find the Next Smaller(position-wise closest and on the right side) of every array elements.
        //If there is no smaller element on right, then print length of array
        // variation of previous problem, instead of traversing left to right, traverse right to left. 
        public static int[] NextSmaller(int[] arr)
        {
            int n = arr.Length;

            int[] res = new int[n];
            Stack<int> s = new Stack<int>();
            s.Push(n - 1);
            res[n - 1] = n;
            for (int i = n - 2; i >= 0; i--)
            {
                while (s.Count > 0 && arr[i] <= arr[s.Peek()])
                {
                    s.Pop();
                }

                res[i] = s.Count == 0 ? n : arr[s.Peek()];
                s.Push(i);
            }
            return res;
        }

        // Calculate the Largest Rectangular Area in a Histogram.
        // Find the largest rectangular area possible in a given histogram where the largest rectangle can be made of a number of contiguous bars.For simplicity, assume that all bars have same width and the width is 1 unit.
        // For example, consider the following histogram with 7 bars of heights { 6, 2, 5, 4, 5, 1, 6}. The largest possible rectangle possible is 12.
        public static int GetMaxAreaUnderHistogram(int[] arr)
        {
            int n = arr.Length, res = 0;

            Stack<int> s = new Stack<int>();

            for (int i = 0; i < n; i++)
            {
                while (s.Count > 0 && arr[i] <= arr[s.Peek()])
                {
                    int top = s.Pop();
                    int curr = arr[top] * (s.Count == 0 ? i : (i - s.Peek() - 1));
                    res = Math.Max(res, curr);
                }

                s.Push(i);
            }

            while (s.Count > 0)
            {
                int top = s.Pop();
                int curr = arr[top] * (s.Count == 0 ? n : (n - s.Peek() - 1));
                res = Math.Max(res, curr);
            }

            return res;
        }

        // Stack with getMin in O(1)
        // Design a stack that supports normal stack operations in O(1) and also supports getMin() in O(1)
        // See StackForGetMinInConstantTime.cs file

        // Removing consecutive duplicates
        // Stacks can be used to remove duplicate characters from a string that are stacked next to each other. 
        // For example, we take the string aabbccccc and convert it into abc. We can push the first character into a stack and skip if the top of the stack is equal to the current character.
        public static string RemoveConsecutiveDuplicates(string str)
        {
            if (str == null || str.Length <= 1)
            {
                return str;
            }

            Stack<char> st = new Stack<char>();
            st.Push(str[0]);

            for (int i = 1; i < str.Length; i++)
            {

                if (st.Peek() == str[i])
                {
                    continue;
                }
                else
                {
                    st.Push(str[i]);
                }
            }

            string res = "";
            while (st.Count > 0)
            {
                res = st.Pop() + res;
            }
            return res;
        }

        // Delete middle element of a stack 
        // Given a stack with push(), pop(), empty() operations, delete the middle of it without using any additional data structure.
        // Middle: ceil(size_of_stack/2.0)
        public static void DeleteMid(Stack<int> s, int sizeOfStack, int current)
        {
            if (s.Count == 0)
            {
                return;
            }

            int middle = (int)Math.Ceiling(sizeOfStack / 2.0);
            int threshold = sizeOfStack - middle + 1;

            if (current < threshold)
            {
                int x = s.Pop();
                current = current + 1;
                DeleteMid(s, sizeOfStack, current);
                if (current < threshold)
                {
                    s.Push(x);
                }
            }
        }
    }
}
