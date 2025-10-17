package striver_dsa_sheet;

import java.util.HashMap;
import java.util.Map;

/**
 * find the number of sub arrays with sum = k
 * 
 */
public class NumberOdSubArraysWithSumK {

	public static void main(String[] args) {
		System.out.println(numberOfSubArraysWithSumK(new int[] { 1, 1, 1 }, 2));

	}

	/**
	 * <pre>
	 * To find the number of sub arrays whose sum equals to K.
	 * First we need prefix sum 
	 * orig array = {1,1,1} K =2 
	 * prefixSum array {0, 1, 2, 3}
	 * 
	 * Now, assume we want to find the number of arrays present at a point x in the given array.
	 * x = 2nd index
	 * If we want a subarray whose sum is K. first we try to find if x-K is present in the prefix sum array.
	 * It is because, if we know how many sums that are = x-k, then we can easily find
	 * how many subarrays with sum = k.
	 * 
	 * steps:
	 * initialize map with (0,1) = this will be halpful, when x-k =0, that means the current sub array is a valid one
	 * on each iteration.
	 * <span style="color:red; font-weight:bold;">
	 * 1. add the current nums[r] to sum (prefixSum)
	 * 2. find if x-K is present in the prefix sum
	 * 	if yes, that means we have subarray with sum = K
	 * 	increase the count by the number of prefixSum's that have (x-K) value.
	 * 3. Add the new prefixSum value to map if already resent increase the freq.
	 * 
	 * 
	 * Simple to implement.
	 * TC: O(N)
	 * SC : O(N)
	 * </span>
	 * </pre>
	 */
	public static int numberOfSubArraysWithSumK(int nums[], int k) {
		int count = 0, r = 0;
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();// to store prefix sums and their frequencies.
		map.put(0, 1);// if x -k  becomes 0, that means the current sub array is sum of K which is a valid one.
		while (r < nums.length) {
			sum = sum + nums[r];
			if (map.containsKey(sum - k)) {
				count = count + map.get(sum - k);
			}
			//Remember: add the current prefix sum to map after the count cal, if
			// added before count cal, it is possible that the newly added prefix sum freq can be taken.
			if (map.containsKey(sum)) {
				map.put(sum, map.get(sum) + 1);
			} else {
				map.put(sum, 1);
			}
			r++;
		}
		return count;
	}

}
