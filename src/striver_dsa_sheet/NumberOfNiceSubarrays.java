package striver_dsa_sheet;

import java.util.HashMap;
import java.util.Map;

public class NumberOfNiceSubarrays {

	public static void main(String[] args) {
		int arr[] = { 1, 1, 2, 1, 1 };
		int k = 3;

//		System.out.println(numberOfNiceSubarrays_PrefixSum(arr, k));
		arr = new int[] { 2, 2, 2, 1, 2, 2, 1, 2, 2, 2 };
		k = 2;
		System.out.println(numberOfNiceSubarrays_PrefixSum(arr, k));
	}

	/**
	 * <pre>
	 *  
	 * 
	 * Problem statement : Find number of Nice subarrays.
	 * A Nice sub array is a array of ODD K numbers in it.
	 * 
	 * Using prefix sum with hashmap.
	 * 
	 *     {1, 1, 2, 1, 1}
	 * pfs {0, 1, 2, 4, 5, 6}
	 * 
	 * TC: O(N)
	 * SP O(N);
	 * 
	 * </pre>
	 */
	private static int numberOfNiceSubarrays_PrefixSum(int nums[], int k) {
		int c = 0, r = 0, sum = 0;
		Map<Integer, Integer> m = new HashMap();
		m.put(0, 1);
		while (r < nums.length) {
			sum += nums[r] % 2;// add either 1 or 0 to prefixsum based on odd number or even number
			if (m.containsKey(sum - k)) {
				c += m.get(sum - k);
			}
			if (m.containsKey(sum)) {
				m.put(sum, m.get(sum) + 1);
			} else {
				m.put(sum, 1);
			}
			r++;
		}
		return c;
	}
}
