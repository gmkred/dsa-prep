package arrays;

import java.util.HashMap;
import java.util.Map;

public class PrefixSum_ContinuousSUbarraySum {

	public static void main(String[] args) {
		int arr[] = { 5, 0, 0, 0 };
		boolean result = continuousSubArraySum(arr, 6);
		System.out.println(result);
		result = continuousSubArraySum(new int[] { 23, 2, 4, 6, 7 }, 6);
		System.out.println(result);
		result = continuousSubArraySum(new int[] { 23, 2, 6, 4, 7 }, 6);
		System.out.println(result);
		result = continuousSubArraySum(new int[] { 23, 2, 6, 4, 7 }, 13);
		System.out.println(result);
		result = continuousSubArraySum(new int[] { 23, 6, 9 }, 6);
		System.out.println(result);
	}

	/**
	 * <pre>
	 *    0  1  2  3  4  5
	 *	{ 23, 2, 4, 6, 7, 15 };
	 *	  0   1   2   3   4   5   6
	 *	{ 0, 23, 25, 29, 35, 42, 57}
	 *	reminders
	 *	{ 0, 5,  1,  5,   5,  0,  3}
	 *<b>Example of Range:</b>
	 *	range 2 to 5 means after index 2 till index 6. so we have to subtract 6th index value with 2nd index value 
	 * <span style= "color:red"> 
	 *                          range
	 *                 |------------------|
	 * |----------------------------------|
	 * |---------------|                  Pb
	 *                 Pa	
	 *	</span>
	 *	Pb - Pa = range
	 *	Pb%k - Pa%k = range%k
	 *NOTE:	If range turn out to be divisible by k then range%k = 0.
	 *	that means Pb%k = Pa%k
	 *
	 *
	 * </pre>
	 */
	public static boolean continuousSubArraySum(int nums[], int k) {
		Map<Integer, Integer> prefixSum = new HashMap<>();
		int prefixArr[] = new int[nums.length + 1];
		prefixArr[0] = 0;
		for (int i = 0; i < nums.length; i++) {
			prefixArr[i + 1] = (prefixArr[i] + nums[i]) % k;
		}
		// key is reminder and value is index
		prefixSum.put(0, 0);
		for (int i = 1; i < prefixArr.length; i++) {
			if (prefixSum.containsKey(prefixArr[i])) {
				if (i - prefixSum.get(prefixArr[i]) > 1) {
					return true;
				}
			} else {
				prefixSum.put(prefixArr[i], i);
			}
		}
		return false;
	}

}
