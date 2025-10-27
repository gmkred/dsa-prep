package striver_dsa_sheet;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArryaWithGivenSum_Positives {
	public static void main(String[] args) {
		int[] arr = { 3, 1, 2, 3, 1, 0, 0, 1, 1, 0, 2, 3 };
		int k = 3;
		System.out.println(longestSubArryaWithGivenSum(arr, k));
		System.out.println(longestSubArryaWithGivenSum_Positives_hash_prefixsum(arr, k));
		System.out.println(longestSubArryaWithGivenSum_Positives_twoPoiners(arr, k));
	}

	/**
	 * <pre>
	 * Optimal for array that has only Positives and zeroes.
	 * {3, 1, 2, 3, 1, 0, 0, 1, 1, 0, 2, 3}
	 * 
	 * TC: O(2N)
	 * SC : O(1)
	 * 
	 * </pre>
	 */
	private static int longestSubArryaWithGivenSum_Positives_twoPoiners(int arr[], int k) {
		int max = Integer.MIN_VALUE;
		int sum = arr[0];
		int p1 = 0;
		int p2 = 0;
		while (p2 < arr.length) {
			// before adding the current element to sum, check if current sum is > k or
			// equals kthNode
			// if current sum is > k, then move the p1 toward p2 by removing the left side
			// values
			while (p1 <= p2 && sum > k) {
				sum -= arr[p1];
				p1++;
			}
			if (sum == k) {
				max = Math.max(max, p2 - p1 + 1);
			}
			// now, add the current value to the sum, which will be checked in the next
			// iteration
			p2++;
			// make sure p2 will throw out of bound exception when p2 is already on the last
			// index.
			if (p2 < arr.length) {
				sum += arr[p2];
			}
		}
		return max;
	}

	/**
	 * <pre>
	 * 
	 * This will work for an array contains positives, negative and zeroes.
	 * And it is <span style ="color:red">Optimal</span> 
	 * 
	 * Using prefix sum we can find the sub arrays with sum k in single iteration of array.
	 * 
	 * For this we need hashmap, store each prefix sum in hash map.
	 * before storing prefix sum, check if current (prefix sum - K) is already present in 
	 * hashmap, because SUM - K = X, if X is present in prefix sum somewhere then i - (X index) will
	 * give the sub array length of K, do it until it finds the max sub array.
	 * 
	
	 *  0  1   2  3  4   5   6  7   8
	 * {1, 2, -2, 3, 5, -1, -1, 4, -1}
	 * 
	 *  0  1   2  3  4   5   6   7  8
	 * {1, 3,  1, 4, 9,  8,  7, 11, 10}
	 * 
	 * Ignore updating the indexes of existing elements
	 *  0  1   3  4  5  6  7   8
	 * {1, 3,  4, 9, 8, 7, 11, 10 }
	 * 
	 * SUM - X + X = SUM
	 * 
	 * TC : O(N log N)
	 * SC : O(N) worst case to store all unique prefix sums.
	 * 
	 * </pre>
	 */
	private static int longestSubArryaWithGivenSum_Positives_hash_prefixsum(int[] arr, int k) {

		Map<Integer, Integer> map = new HashMap<>();
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];
			if (sum == k) {
				max = Math.max(max, i + 1);
			}
			if (map.containsKey(sum - k)) {
				max = Math.max(max, i - map.get(sum - k));
			}
			// if we have zeroes, we definitely, dont want to update the existing prefix
			// sums index, because we want the longest subarray.
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return max;
	}

	/**
	 * <pre>
	 * TC : O(N^2)
	 * SC : O(1)
	 * 
	 * </pre>
	 */
	private static int longestSubArryaWithGivenSum(int arr[], int k) {
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {
			int sum = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				sum = sum + arr[j];
				if (sum == k) {
					max = Math.max(max, j - i + 1);
				}
			}
		}

		return max;
	}
}
