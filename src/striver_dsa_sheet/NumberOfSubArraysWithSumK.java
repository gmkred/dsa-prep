package striver_dsa_sheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfSubArraysWithSumK {

	public static void main(String[] args) {
		int pos[] = { 3, 1, 2, 3, 1, 0, 0, 1, 1, 0, 2, 3 };
		int neg[] = { 3, 1, -1, 2, 2, 1, 1, 1, 0, 2, 3 };
		System.out.println(numberOfSubarraysWithSumK(pos, 3));
		System.out.println(numberOfSubarraysWithSumK_better_for_Positives_and_optimal_for_negatives(neg, 3));
		System.out.println(numberOfSubarraysWithSumK_optimal_for_Positives(pos, 3));
	}

	/**
	 * <pre>
	 * two pointers
	 * 
	 * Note: Two pinter will ot work in case of negatives, because we will require more than 2 poitners, 
	 * even after that it will be difficult to move the pointer because at one point 
	 * the next addition  might be > k in this case if we move our left pointer it will be difficult to
	 * track the sums.
	 * So use Hashmap which will store all the prefix sums and we can find SUM - K is present in 
	 * map, if present the length will be (i - (SUM - K)th index)
	 * </pre>
	 */
	private static int numberOfSubarraysWithSumK_optimal_for_Positives(int[] arr, int k) {

		int count = 0;
		int sum = arr[0];

		int i = 0, j = 0;
		while (j < arr.length) {
			// first check for the current sum.
			while (i <= j && sum > k) {
				sum -= arr[i];
				i++;
			}
			if (sum == k) {
				count++;
			}
			j++;
			// later move to next sum
			if (j < arr.length) {
				sum += arr[j];
			}
		}

		return count;

	}

//prefix sum
	private static int numberOfSubarraysWithSumK_better_for_Positives_and_optimal_for_negatives(int arr[], int k) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			// if map is empty and the 0th index value is equivalent to k, then increase the
			// count and 0th index will be put into map.
			if (map.size() < 1) {
				count++;
			} else if (map.containsKey(sum - k)) {
				count++;
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return count;

	}

	/**
	 * <pre>
	 * TC : O(N^2) 
	 * SC : O(1)
	 * </pre>
	 */
	private static int numberOfSubarraysWithSumK(int arr[], int k) {
		List<int[]> list = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = i; j < arr.length; j++) {
				sum += arr[j];
				int[] sub = new int[2];
				if (sum == k) {
					sub[0] = i;
					sub[1] = j;
					list.add(sub);
					count++;
				}
			}
		}
//		for (int[] i : list) {
//			System.out.println(i[0] + " " + i[1]);
//		}
		return count;
	}
}
