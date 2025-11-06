package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * similar to frog jump 1 or 2 steps, but here frog can jump 1 to k steps at a jump.
 * 
 * Find the minimum cost from 1step to last step
 * 
 * </pre>
 */
public class FrogJumpsWithKDistance {
	public static void main(String[] args) {
		int arr[] = { 10, 20, 30, 20, 40, 10, 30, 10 };
		int k = 4;
		int n = arr.length;
		System.out.println(recursion(arr, k, n - 1));
		int[] dp = new int[arr.length];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = -1;
		}
//		System.out.println(recursionDP(arr, k, n - 1, dp));
//		System.out.println(recursionDP_Tabulation(arr, k, n - 1, dp));

	}

	private static int recursion(int arr[], int k, int n) {
		if (n == 0) {
			// distance from 0th to 0th will be 0
			return 0;
		}
		int minSteps = Integer.MAX_VALUE;
		/**
		 * <pre>
		 * for each index, we need to find minimum steps by jumping k steps. 
		 * like 1,2,3,4...,k
		 * And retrun that minimum steps which will be appended to next index min steps
		 * </pre>
		 */
		for (int i = 1; i <= k; i++) {
			if (n - i >= 0) {
				int jumpSteps = recursion(arr, k, n - i) + Math.abs(arr[n - i] - arr[n]);
				minSteps = Math.min(jumpSteps, minSteps);
			}
		}
		return minSteps;
	}

	private static int recursionDP(int arr[], int k, int n, int dp[]) {
		if (n == 0) {
			// distance from 0th to 0th will be 0
			dp[n] = 0;
			return dp[n];
		}
		int minSteps = Integer.MAX_VALUE;
		/**
		 * <pre>
		 * for each index, we need to find minimum steps by jumping k steps. 
		 * like 1,2,3,4...,k
		 * And retrun that minimum steps which will be appended to next index min steps
		 * 
		 * Since, there will be multiple similar subproblems so use DP to avoid solvining them again.
		 * </pre>
		 */
		if (dp[n] != -1) {
			return dp[n];
		}
		for (int i = 1; i <= k; i++) {
			if (n - i >= 0) {
				int jumpSteps = recursionDP(arr, k, n - i, dp) + Math.abs(arr[n - i] - arr[n]);
				minSteps = Math.min(jumpSteps, minSteps);
				dp[n] = minSteps;
			}
		}
		return dp[n];
	}

	private static int recursionDP_Tabulation(int arr[], int k, int n, int dp[]) {
		dp[0] = 0;

		for (int i = 1; i <= n; i++) {
			int minSteps = Integer.MAX_VALUE;
			int jumpSteps = 0;
			for (int j = 1; j <= k; j++) {
				if (i - j >= 0) {
					jumpSteps = dp[i - j] + Math.abs(arr[i - j] - arr[i]);
					minSteps = Math.min(jumpSteps, minSteps);
				}
			}

			dp[i] = minSteps;
		}
		return dp[n];
	}
}
