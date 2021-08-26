package Greedy;

import java.util.*;

class ActivityPair {
	int startTime;
	int finishTime;

	public ActivityPair(int st, int et) {
		startTime = st;
		finishTime = et;
	}
}

class ActivityPairComparator implements Comparator<ActivityPair> {

	@Override
	public int compare(ActivityPair p1, ActivityPair p2) {

		return p1.finishTime - p2.finishTime;
	}
}

class KnapsackPair {
	int weight;
	int value;

	public KnapsackPair(int w, int v) {
		weight = w;
		value = v;
	}
}

class KnapsackPairComparator implements Comparator<KnapsackPair> {

	@Override
	public int compare(KnapsackPair p1, KnapsackPair p2) {

		return p2.value * p1.weight - p1.value * p2.weight;
	}
}

class JobSequencePairComparator implements Comparator<JobSequencePair> {

	@Override
	public int compare(JobSequencePair p1, JobSequencePair p2) {

		return p2.profit - p1.profit;
	}
}

class JobSequencePair {
	int deadline;
	int profit;
}

public class GreedyProb {

	// [{1,3}, {2,4}, {3,8}, {10,11}]
	public int ActivitySelection(ArrayList<ActivityPair> activities) {

		Collections.sort(activities, new ActivityPairComparator());

		int res = 1;

		ActivityPair prev = activities.get(0);
		for (int curr = 1; curr < activities.size(); curr++) {
			// if not overlap
			if (activities.get(curr).startTime >= prev.finishTime) {
				res++;
				prev = activities.get(curr);
			}
		}

		return res;
	}

	public double FractionalKnapsack(ArrayList<KnapsackPair> pairs, int knapsackCapacity) {

		Collections.sort(pairs, new KnapsackPairComparator());

		double res = 0;

		int currCap = knapsackCapacity;
		for (KnapsackPair pair : pairs) {
			if (pair.weight <= currCap) {
				currCap = currCap - pair.weight;
				res = res + pair.value;
			} else {
				res = res + currCap * ((double) pair.value / (double) pair.weight);
				return res;
			}
		}

		return res;
	}

	public int[] JobSequencingProblem(ArrayList<JobSequencePair> pairs) {

		Collections.sort(pairs, new JobSequencePairComparator());

		int maxDeadline = Integer.MIN_VALUE;
		for (JobSequencePair pair : pairs) {
			if (pair.deadline > maxDeadline) {
				maxDeadline = pair.deadline;
			}
		}

		boolean[] slots = new boolean[maxDeadline];
		int res = 0;
		int count = 0;
		for (JobSequencePair pair : pairs) {
			int d = pair.deadline - 1;
			while (d >= 0) {
				if (slots[d] == false) {
					slots[d] = true;
					res = res + pair.profit;
					count++;
					break;
				}
				d--;
			}
		}
		return new int[] { count, res };
	}
}
