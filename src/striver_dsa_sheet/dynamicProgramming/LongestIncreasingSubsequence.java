package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * arr[] = {10,9,2,5,3,7,101,18}
 * increasing subsequences
 * {2,35}
 * {2,3,7}
 * {2,3,7,18}
 * {2,3,7,101}
 * 
 * longest increasing subsequence length is 4
 * 
 * 
 * {8,8,8}
 * 
 * LIS = 1
 * </pre>
 */
public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		int arr[] = { 10, 9, 2, 5, 3, 7, 101, 18 };
		System.out.println(recursion(0, arr, -1));
		int dp[][] = new int[arr.length][arr.length + 1];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(recTabulation(arr));
		System.out.println(recTabulationSO(arr));
	}

	// generate all subsequences and check each sub sequence with increasing
	// numbers.
	private static void bruteForce() {
	}

	/**
	 * <pre>
	 * 1. express every thing as index
	 * 2. perform all operations on each index.
	 * 3. take max value
	 * 
	 *  p  c 
	 * -1  0  1  2  3  4  5   6   7
	 *   {10, 9, 2, 5, 3, 7, 101, 18}
	 *   
	 * initially previousIndex will be at -1 and index will be at 0.
	 * we will only consider if current index value is > previous index value.
	 * else move the current index to next index but previous index stays there itself.
	 * </pre>
	 */
	private static int recursion(int index, int arr[], int previousIndex) {
		if (index >= arr.length) {
			return 0;
		}

		int include = Integer.MIN_VALUE;
		if (previousIndex == -1 || arr[index] > arr[previousIndex]) {
			include = 1 + recursion(index + 1, arr, index);
		}
		int exclude = 0 + recursion(index + 1, arr, previousIndex);

		return Math.max(include, exclude);
	}

	private static int rec(int index, int prev, int arr[], int dp[][]) {
		if (index >= arr.length) {
			return 0;
		}
		// A coordinate change is done for previous storing -1nth index at 0th index
		// shifting towards right.
		if (dp[index][prev + 1] != -1) {
			return dp[index][prev + 1];
		}
		int in = Integer.MIN_VALUE;
		if (prev == -1 || arr[index] > arr[prev]) {
			in = 1 + rec(index + 1, index, arr, dp);
		}
		int ex = 0 + rec(index + 1, prev, arr, dp);
		dp[index][prev + 1] = Math.max(in, ex);
		return dp[index][prev + 1];
	}

	/**
	 * <pre>
	 * 	{10, 9, 2, 5, 3, 7, 101, 18}
	 * 
	 *   
	 * 	[4, 0, 0, 0, 0, 0, 0, 0, 0] 
	 * 	[4, 1, 0, 0, 0, 0, 0, 0, 0] 
	 * 	[4, 1, 1, 0, 0, 0, 0, 0, 0] 
	 * 	[3, 1, 1, 3, 0, 0, 0, 0, 0] 
	 * 	[3, 1, 1, 3, 2, 0, 0, 0, 0] 
	 * 	[2, 1, 1, 2, 2, 2, 0, 0, 0] 
	 * 	[1, 1, 1, 1, 1, 1, 1, 0, 0] 
	 * 	[1, 1, 1, 1, 1, 1, 1, 0, 0] 
	 * 	[0, 0, 0, 0, 0, 0, 0, 0, 0]
	 * 
	 * 
	 * </pre>
	 */
	private static int recTabulation(int arr[]) {
		int dp[][] = new int[arr.length + 1][arr.length + 1];
		// A coordinate change is done for previous storing -1nth index at 0th index
		// shifting towards right.
		for (int index = arr.length - 1; index >= 0; index--) {
			for (int prev = index - 1; prev >= -1; prev--) {
				int in = Integer.MIN_VALUE;
				int ex = Integer.MIN_VALUE;
				if (prev == -1 || arr[index] > arr[prev]) {
					// since we are doing index+1, so for rows also have length+1
					in = 1 + dp[index + 1][index + 1];
				}
				ex = 0 + dp[index + 1][prev + 1];
				dp[index][prev + 1] = Math.max(in, ex);
			}
		}
		return dp[0][-1 + 1];
	}

	private static int recTabulationSO(int arr[]) {
		int previous[] = new int[arr.length + 1];
		// A coordinate change is done for previous storing -1nth index at 0th index
		// shifting towards right.
		for (int index = arr.length - 1; index >= 0; index--) {
			int cur[] = new int[arr.length + 1];
			for (int prev = index - 1; prev >= -1; prev--) {
				int in = Integer.MIN_VALUE;
				int ex = Integer.MIN_VALUE;
				if (prev == -1 || arr[index] > arr[prev]) {
					// since we are doing index+1, so for rows also have length+1
					in = 1 + previous[index + 1];
				}
				ex = 0 + previous[prev + 1];
				cur[prev + 1] = Math.max(in, ex);
			}
			previous = cur;
		}
		return previous[-1 + 1];
	}
}