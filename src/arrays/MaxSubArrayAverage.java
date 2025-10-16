package arrays;

public class MaxSubArrayAverage {

	public static void main(String[] args) {
		int arr[] = { 1, 12, -5, -6, 50, 3 };
		double result = maxSubArrayAverage(arr, 4);
		System.out.println(result);
	}

	/**
	 * Its kind of Sliding window problem
	 * 
	 * <pre>
	 * <b>
	 *Input: nums = [1,12,-5,-6,50,3], k = 4
	 *Output: 12.75000
	 *Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
	 *</b>
	 *Steps :
	 * 1. First initialize curSum, maxSum with arr[0]
	 * 2. Divide the loop into 2 parts, 
	 * 	one to set the initial sum of first K elememts i < k
	 * 	second to go through subsequent K elements sums to find the max sub array of K elements.
	 * 3. Average the max sub array sum value with K.
	 * 
	 * </pre>
	 */
	public static double maxSubArrayAverage(int arr[], int k) {

		int curSum = arr[0];
		int maxSum = arr[0];
		int p = 0;// pointer for left side of the window
		for (int i = 1; i < arr.length; i++) {
			if (i < k) {
				curSum = curSum + arr[i];
				maxSum = curSum;
				continue;
			}
			curSum = curSum + arr[i] - arr[p];// only summing the subsequent K elements
			p++;
			maxSum = Math.max(maxSum, curSum);
		}
		return (double) maxSum / k;

	}
}
