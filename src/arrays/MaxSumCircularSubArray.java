package arrays;

/**
 * 
 * This problesm has 2 cases
 * 
 * <pre>
 * <b>1. Non-Wrapping subarray : </b>
 * 	Max subarray exists inside the original arrya
 * 
 * <b>2. Wrapping subarray :</b>
 * 	Max subarrya exisits at the ends of the original subarray.
 * 	In case 2 solution we have to find the Min subarrya and 
 * 	deduct that value from the total sum of all elements in the array
 * 
 * </pre>
 * 
 * 
 */
public class MaxSumCircularSubArray {
	public static void main(String[] args) {
		int arr[] = { 1, -2, 3, -2 };
		int result = maxSubArraySumInCircularArray(arr);
		System.out.println(result);
	}
	/**
	 *<b>Edge cases:</b>
	 *<pre>
	 *If all the elements are -ve then return maxSum.
	 *
	 *Because Total minSum will becaome 0 which can be considered as max value but the max 
	 *value should be the lowest -ve value
	 *
	 *{-2,-6,-1,-8} maxSubarray sum is -1.
	 *
	 *<b>Steps:</b>
	 *1.Initialize curMaxSum, curMinSum, maxSum, minSum, total = arr[0]
	 *2.Use single for loop to iterate each element. while iterating
	 *  calculate the maxSum, minSum and total.
	 *3.	If total - minSum results in 0 that means all might be - ve's so 
	 *	retrun maxSum
	 *	else max(maxSum, total - minSum).
	 *</pre>
	 * */
	public static int maxSubArraySumInCircularArray(int arr[]) {
		int maxSum = arr[0];
		int minSum = arr[0];
		int curMaxSum = arr[0];
		int curMinSum = arr[0];
		int totSum = arr[0];
		for (int i = 1; i < arr.length; i++) {
			totSum = totSum + arr[i];
			curMaxSum = curMaxSum + arr[i];
			curMaxSum = Math.max(curMaxSum, arr[i]);
			curMinSum = curMinSum + arr[i];
			curMinSum = Math.min(curMinSum, arr[i]);
			maxSum = Math.max(curMaxSum, maxSum);
			minSum = Math.min(curMinSum, minSum);
		}
		if(maxSum < 0) {
			return maxSum;
		}
		return Math.max(maxSum, totSum - minSum);
	}
}
