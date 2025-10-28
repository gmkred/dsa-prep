package striver_dsa_sheet.algos;

/**
 * <pre>
 * To find the max sum sub array
 * 
 * 
 * </pre>
 */
public class Kadanes_Algo {

	public static void main(String[] args) {
		int arr[] = { -2, -3, 6, -1, -2, 1, 5, -3 };
		System.out.println(findMaxSumSubArray(arr));
		System.out.println(findMaxSumSubArray_Striver(arr));
		System.out.println(findMinSumSubArray(arr));
		System.out.println(findMinSumSubArray_2(arr));
	}

	/**
	 * DO not carry negatives as it only reduce the total sum;
	 * 
	 */
	private static int findMaxSumSubArray_Striver(int arr[]) {
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;
		int begin = 0, end = 0;

		for (int i = 0; i < arr.length; i++) {
			// if sum is zero, then it is starting off again.
			if (sum == 0) {
				begin = i;
			}
			sum += arr[i];
			if (sum < 0) {
				sum = 0;
			}
			if (sum > maxSum) {
				maxSum = sum;
				end = i;
			}
		}
		System.out.println(begin + " " + end);
		return maxSum;
	}

	/**
	 * 
	 * this will work if array does not have positives.
	 * 
	 */
	private static int findMaxSumSubArray(int arr[]) {
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			sum = Math.max(sum, arr[i]);
			maxSum = Math.max(maxSum, sum);
		}
		return maxSum;
	}

	// -2, -3, 6, -1, -2, 1, 5, -3
	private static int findMinSumSubArray_2(int arr[]) {
		int minSum = Integer.MAX_VALUE;
		int sum = 0;
		int begin = 0;
		int end = 0;
		for (int i = 0; i < arr.length; i++) {
			if (sum == 0) {
				if (i < end) {
					begin = i;
				}
			}
			sum += arr[i];
			if (sum > 0) {
				sum = 0;
			}
			if (minSum > sum) {
				minSum = sum;
				end = i;
			}
		}
		System.out.println("b : " + begin + " end " + end);
		return minSum;
	}

// -2, -3, 4, -1, -2, 1, 5, -3 
	private static int findMinSumSubArray(int arr[]) {
		int minSum = Integer.MAX_VALUE;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			sum = Math.min(sum, arr[i]);
			minSum = Math.min(minSum, sum);
		}
		return minSum;
	}
}
