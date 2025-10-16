package arrays;

/**
 * Contiguous sub array with max sum.
 * 
 * <p style="color:red">
 * Problem Statement:
 * </p>
 * <ul>
 * <li>Given an integer array arr, find the contiguous subarray (containing at
 * least one number) which has the largest sum and returns its sum and prints
 * the subarray.
 * </ul>
 * Example :
 * 
 * <pre>
 * {-2,1,-3,4,-1,2,1,-5,4}
 * 
 * Here, the max sum subarray will be
 * {4,-1,2,1} = 6
 * </pre>
 */

public class Kadanes_algorithm {

	public static void main(String[] args) {
		int[] arr1 = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		int res = 6; // {4,-1,2,1} = 6
		int actRes = findMaxSumSubArray_better(arr1);
		System.out.println("result1 : " + actRes);
		actRes = findMaxSumSubArray_optimal(arr1);
		System.out.println("result2 : " + actRes);

	}

	/**
	 * better approach
	 * 
	 * <pre>
	 * <b>Time Complexity: </b>
	 * Using loop inside a loop  i.e. nested for loops.
	 * for outer loop, its runs N times so O(N)
	 * for inner loop, for every outer loop, inner loop runs (N-i-1)
	 * Iterations of array size 9
	 *	<b>OF	|	IF</b>
	 *			<b>N-i-1</b>
	 *i	0		9-0-1 = 8 times
	 *i	1		9-1-1 = 7 times
	 *i	2		9-2-1 = 6 times
	 *..
	 *..
	 *i	9		9-8-1 = 0 times
	 *
	 *<b>Summation formula</b> 
	 *	
		<b>     i=0
		T(n)=∑	(n−i−1)
		     n−1 </b>
		This is an arithmetic series:
		
		First term: n−0−1 = n−1
		Last term: n−(n−1)−1 = 0
		Number of terms: n
		
		Sum of arithmetic series:
		T(n)= n/2 ×(firstterm + lastterm) =n/2 × (n−1+0)= n(n−1)/2
			​So total inner loop executions = n(n-1)/2, which is 𝑂(n^2)
		O(n2).
	 * </pre>
	 * 
	 */
	public static int findMaxSumSubArray_better(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int sum = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				sum = sum + arr[j];
				max = Math.max(max, sum);
			}
		}
		return max;
	}

	/**
	 * <pre>
	 * Kadan's algorithm: <b>running sum and decision-making.</b>
	 * Traverse only once through the array to find the max sum of a sub array.
	 *  <b>There are only 2 possibilities:
	 * 	1. Extend the previous sub array
	 * 	2. start fresh sub array from a point</b>
	 * Step 1:
	 * Initialize 2 variables with arr[0]:
	 * 	<b>curSum
	 * 	maxSum</b>
	 * step 2:
	 * while looping: 
	 * step 3:
	 * 	Compare curSum and arr[i]. Assign max value to curSum
	 * step 4:
	 * 	Compare maxSum and curSum. Assign max value to the maxSum.
	 * step 5:
	 * 	retrun maxSum
	 * 
	 * Maintain previous sub array sum unless its less than the current arr[i] element.
	 * if current arr[i] value is greater than previous subarray just start a new sub array from arr[i]
	 * </pre>
	 * 
	 * { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
	 */
	public static int findMaxSumSubArray_optimal(int arr[]) {
		/*
		 * int st = 0; int end = 0;
		 */
		int maxSum = arr[0];
		int curSum = arr[0];
		for (int i = 1; i < arr.length; i++) {
			curSum = curSum + arr[i];
			/*
			 * if (curSum < arr[i]) { st = i; }
			 */
			curSum = Math.max(arr[i], curSum);
			/*
			 * if (maxSum < curSum) { end = i; }
			 */
			maxSum = Math.max(maxSum, curSum);
		}
//		System.out.println(st + " " + end);
		return maxSum;
	}

}
