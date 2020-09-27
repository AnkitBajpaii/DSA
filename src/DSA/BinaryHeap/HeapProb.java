package HeapProblems;

import java.util.*;

public class HeapProb {
	public void KSorted(int[] arr, int k) {

		PriorityQueue<Integer> q = new PriorityQueue<Integer>();

		for (int i = 0; i <= k; i++) {
			q.offer(arr[i]);
		}

		int index = 0;
		for (int i = k + 1; i < arr.length; i++) {
			arr[index++] = q.poll();
			q.offer(arr[i]);
		}

		while (!q.isEmpty()) {
			arr[index++] = q.poll();
		}
	}

	public int BuyMaxItemsWithGivenSum(int[] arr, int sum) {
		int n = arr.length;
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for (int i = 0; i < n; i++) {
			q.offer(arr[i]);
		}

		int count = 0;
		while (!q.isEmpty() && q.peek() <= sum) {
			sum = sum - q.poll();
			count++;
		}

		return count;
	}

	public ArrayList<Integer> FindKLargestElements(int[] arr, int k) {
		int n = arr.length;

		PriorityQueue<Integer> q = new PriorityQueue<Integer>();

		// create min heap of K elements
		for (int i = 0; i < k; i++) { // O(K)
			q.offer(arr[i]);
		}

		for (int i = k; i < n; i++) { // O((n-K) * LogK)
			if (arr[i] > q.peek()) {
				q.poll();
				q.offer(arr[i]);
			}
		}

		ArrayList<Integer> res = new ArrayList<Integer>();
		while (!q.isEmpty()) { // O(KLogK)
			res.add(q.poll());
		}

		Collections.reverse(res);
		return res; // O(nLogK)
	}

	public int FindKthLargestElement(int[] arr, int k) {
		int n = arr.length;

		PriorityQueue<Integer> q = new PriorityQueue<Integer>();

		// create min heap of K elements
		for (int i = 0; i < k; i++) { // O(K)
			q.offer(arr[i]);
		}

		for (int i = k; i < n; i++) { // O((n-K) * LogK)
			if (arr[i] > q.peek()) {
				q.poll();
				q.offer(arr[i]);
			}
		}

		return q.peek();
	}

	public int[] FindKSmallestElements(int[] arr, int k) {
		int n = arr.length;
		int[] res = new int[k];

		PriorityQueue<Integer> q = new PriorityQueue<Integer>(Collections.reverseOrder());

		// create min heap of K elements
		for (int i = 0; i < k; i++) { // O(K)
			q.offer(arr[i]);
		}

		for (int i = k + 1; i < n; i++) { // O((n-K) * LogK)
			if (arr[i] < q.peek()) {
				q.poll();
				q.offer(arr[i]);
			}
		}

		int i = 0;
		while (!q.isEmpty()) { // O(KLogK)
			res[i++] = q.poll();
		}
		return res; // O(nLogK)
	}

	public int FindKthSmallestElement(int[] arr, int k) {
		int n = arr.length;

		PriorityQueue<Integer> q = new PriorityQueue<Integer>(Collections.reverseOrder());

		// create max heap of K elements
		for (int i = 0; i < k; i++) { // O(K)
			q.offer(arr[i]);
		}

		for (int i = k; i < n; i++) { // O((n-K) * LogK)
			if (arr[i] < q.peek()) {
				q.poll();
				q.offer(arr[i]);
			}
		}

		return q.peek();
	}

	public void PrintMedianInStream(int[] arr) {
		int n = arr.length;
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); // max heap to store smaller
																							// half
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(); // min heap to store greater half.

		maxHeap.offer(arr[0]);
		
		System.out.println(arr[0]);

		for (int i = 1; i < n; i++) {

			int x = arr[i];
			if (maxHeap.size() > minHeap.size()) {
				if (x < maxHeap.peek()) {
					maxHeap.offer(x);
					minHeap.offer(maxHeap.poll());

				} else {
					minHeap.offer(x);
				}

				System.out.println(((maxHeap.peek() + minHeap.peek()) / 2));
			} else {
				if (x <= maxHeap.peek()) {
					maxHeap.offer(x);
				} else {
					minHeap.offer(x);
					maxHeap.offer(minHeap.poll());
				}

				System.out.println(maxHeap.peek());
			}
		}
	}

	public void PrintKthLargestForEachElementInStream(int[] arr, int k) {
		int n = arr.length;

		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		String res = "";

		// create min heap of K elements
		for (int i = 0; i < k; i++) { // O(K)
			q.offer(arr[i]);
			if (i != k - 1) {
				res = res + -1 + " ";
			}
		}

		res = res + q.peek() + " ";

		for (int i = k; i < n; i++) { // O((n-K) * LogK)
			if (arr[i] > q.peek()) {
				q.poll();
				q.offer(arr[i]);
			}

			res = res + q.peek() + " ";
		}

		res = res.trim();
		System.out.print(res);
	}

	public void kMostFrequent(int arr[], int n, int k) {

		class Pair implements Comparable<Pair> {
			int p1;
			int p2;

			public Pair(int _p1, int _p2) {
				p1 = _p1;
				p2 = _p2;
			}

			@Override
			public int compareTo(Pair pair) {
				if (this.p2 > pair.p2) {
					return 1;
				} else if (this.p2 < pair.p2) {
					return -1;
				} else {
					return this.p1 > pair.p1 ? 1 : -1;
				}
			}
		}

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				int count = map.get(arr[i]);
				map.put(arr[i], count + 1);
			} else {
				map.put(arr[i], 1);
			}
		}

		ArrayList<Pair> pairs = new ArrayList<Pair>();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			pairs.add(new Pair(entry.getKey(), entry.getValue()));
		}

		PriorityQueue<Pair> q = new PriorityQueue<Pair>(Collections.reverseOrder());
		pairs.forEach((p) -> {
			q.offer(p);
		});

		pairs = new ArrayList<Pair>();
		for (int i = 0; i < k && !q.isEmpty(); i++) {
			pairs.add(q.poll());
		}

		int count = 0;
		for (Pair pair : pairs) {
			count += pair.p2;
		}

		System.out.print(count);
	}

	public long MinCostOfRopes(long arr[], int n) {
		PriorityQueue<Long> q = new PriorityQueue<Long>();

		long res = 0;
		for (int i = 0; i < arr.length; i++) {
			q.offer(arr[i]);
		}

		while (q.size() > 1) {

			long x = q.poll();
			long y = q.poll();
			long cost = x + y;
			res = res + cost;
			q.offer(cost);
		}
		return res;
	}

	public ArrayList<Integer> MergeKSortedArrays(int[][] arrays, int k) {

		class Triplet implements Comparable<Triplet> {

			int val, ap, vp;

			public Triplet(int v, int ap, int vp) {
				this.val = v;
				this.ap = ap;
				this.vp = vp;
			}

			@Override
			public int compareTo(Triplet o) {

				return (this.val <= o.val) ? -1 : 1;
			}
		}

		ArrayList<Integer> res = new ArrayList<Integer>();

		PriorityQueue<Triplet> q = new PriorityQueue<Triplet>();
		for (int i = 0; i < arrays.length; i++) {
			q.offer(new Triplet(arrays[i][0], i, 0));
		}

		while (!q.isEmpty()) {
			Triplet t = q.poll();
			res.add(t.val);

			int ap = t.ap, vp = t.vp;

			if (vp + 1 < arrays[ap].length) {
				q.offer(new Triplet(arrays[ap][vp + 1], ap, vp + 1));
			}
		}

		return res;
	}

}
