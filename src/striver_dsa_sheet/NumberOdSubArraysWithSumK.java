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
	 * Also Note : 
	 * {1, 2, 3, -3, 1, 1, 1}
	 * Hashmap
	 * {1, 3, 6, 3, 4, 5, 6} = prefix sum
	 * Sum = X
	 * K = 3
	 * if(sum == k) count++;
	 * 
	 * If there occurs same sum due to negatives just increase the freq of that sum number.
	 * It's because, at any point in array the X-K = some value, is present twice in prefix sum,
	 * that means, current subarray can form two subarays with sum 3
	 * Dry run:
	 * 
	 * {1, 2, 3, -3, 1, 1, 1}
	 * indexes
	 *     0  1  2   3  4  5  6
	 * {0, 1, 3, 6,  3, 4, 5, 6} = prefix sum
	 * 
	 * at 6th index the sum is 6, if we do 6-3 = 3, 
	 * if we observe 
	 * form 4th to 6th index sum is 3
	 * and
	 * from 2nd to 6th index sum is 3.
	 * so maintain the freq of the sums if occurred again.
	 * 
	 * Example 2 :
	 * 
	 * {2, -2, 2, 1, 1, 1, 3}
	 * 
	 * 	   0   1  2  3  4  5  6 
	 * {0, 2,  0, 2, 3, 4, 5, 8} = prefix sum
	 * 
	 * hash map {2=2,0=2,3=1,4=1,5=1,8=1}
	 * 
	 * K=3
	 * 
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
		// prefixSum , Freq
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();// to store prefix sums and their frequencies.
		map.put(0, 1);// if x -k becomes 0, that means the current sub array is sum of K which is a
						// valid one.
		while (r < nums.length) {
			sum = sum + nums[r];
			if (map.containsKey(sum - k)) {
				count = count + map.get(sum - k);
			}
			// Remember: add the current prefix sum to map after the count cal, if
			// added before count cal, it is possible that the newly added prefix sum freq
			// can be taken.
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
