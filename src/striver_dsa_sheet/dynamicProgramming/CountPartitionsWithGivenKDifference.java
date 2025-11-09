package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * { 5, 2, 6, 4 }
 * 
 * divide into 2 subsets which should have the diff D
 * 
 * D = 3
 * 
 * {5+2} - {6+4} = 3
 * 
 * 
 * assume 
 * S1 and S2
 * Total = 5+2+6+4 = 17
 * S1 = 10
 * S2 = 7
 * 
 * to find the difference we need to know either S1 or S2
 * S1 > S2
 * 
 * Total = 17
 * S1 = 10
 * 
 * D = (S1-s2) 
 * 
 * 	S1 + S2 = Total
 * 
 * 	s2 = Total - S1
 * D = S1 - Total + S1
 * D = 2S1 - Total
 * 
 * D + Total = 2S1
 * 
 * S1 = (Total + D)/2
 * 
 * 
 * </pre>
 */
public class CountPartitionsWithGivenKDifference {
	public static void main(String[] args) {
		int arr[] = { 5, 2, 6, 4, 2, 1, 3 };
		int D = 3;
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[i];
		}
		int target = (total + D) / 2;
		if ((total - target < 0) || (total - D) % 2 != 0) {
			return;
		}
		int dp[][] = new int[arr.length][target + 1];
		for (int i = 0; i < dp.length; i++) {
			for (int t = 0; t <= target; t++) {
				dp[i][t] = -1;
			}
		}
		System.out.println(rec(arr, D, arr.length - 1, target));
		System.out.println(recDP(arr, arr.length - 1, target, dp));
		System.out.println(DPTabulation(arr, target));
		System.out.println(NoDPTabulation(arr, target));

	}

	// TC : O(2^N) SC : O(N) auxiliary
	private static int rec(int arr[], int D, int index, int target) {
		if (index == 0) {
			if (target == 0 && arr[0] == 0) {
				return 2;
			} else if (target == 0 || target == arr[0]) {
				return 1;
			} else {
				return 0;
			}
		}
		if (target == 0) {
			return 1;
		}
		int include = 0;
		if (arr[index] <= target) {
			include = rec(arr, D, index - 1, target - arr[index]);
		}

		int exclude = 0;
		exclude = rec(arr, D, index - 1, target);

		return include + exclude;
	}

	// Recursive DP approach
	private static int recDP(int arr[], int index, int target, int dp[][]) {
		if (index == 0) {
			if (target == 0 && arr[0] == 0) {
				dp[index][target] = 2;
				return dp[index][target];
			}
			if (target == 0 || target == arr[index]) {
				dp[index][target] = 1;
				return dp[index][target];
			} else {
				dp[index][target] = 0;
				return dp[index][target];
			}
		}
		if (target == 0) {
			return dp[index][target] = 1;
		}

		if (dp[index][target] != -1) {
			return dp[index][target];
		}
		int include = 0;
		int exclude = 0;
		if (arr[index] <= target) {
			include = recDP(arr, index - 1, target - arr[index], dp);
		}
		exclude = recDP(arr, index - 1, target, dp);
		dp[index][target] = include + exclude;
		return dp[index][target];
	}

	// Tabulation DP approach
	private static int DPTabulation(int arr[], int target) {
		int[][] dp = new int[arr.length][target + 1];

		// base case: dp[i][0] = 1 for all i (since sum 0 is always achievable by not
		// including elements)
		for (int i = 0; i < arr.length; i++) {
			dp[i][0] = 1;
		}
		// initialize dp[0][arr[0]]
		dp[0][0] = 1;
		dp[0][arr[0]] = 1;

		for (int index = 1; index < arr.length; index++) {
			for (int t = 1; t <= target; t++) {
				int include = 0;
				if (arr[index] <= t) {
					include = dp[index - 1][t - arr[index]];
				}
				int exclude = dp[index - 1][t];
				dp[index][t] = include + exclude;
			}
		}
		return dp[arr.length - 1][target];
	}

	// Optimized Tabulation DP approach (Space Optimization)
	private static int NoDPTabulation(int arr[], int target) {
		int[] previous = new int[target + 1];
		previous[0] = 1; // base case: sum 0 can be achieved by excluding all elements

		previous[arr[0]] = 1;

		for (int index = 1; index < arr.length; index++) {
			int[] current = new int[target + 1];
			current[0] = 1; // sum 0 is always achievable

			for (int t = 1; t <= target; t++) {
				int include = 0;
				if (arr[index] <= t) {
					include = previous[t - arr[index]];
				}
				int exclude = previous[t];
				current[t] = include + exclude;
			}
			previous = current; // move to the next row
		}
		return previous[target];
	}
}
