package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * { 5, 2, 6, 4 }
 * 
 * divide into 2 subsets which should have the diff D
 * 
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
		int arr[] = { 5, 2, 6, 4 };
		int D = 3;
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[i];
		}
		int target = (total + D) / 2;
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

//TC : O(2^N) SC : O(N) auxiliary
	private static int rec(int arr[], int D, int index, int target) {
		if (index == 0) {
			if (target == arr[index] || target == 0) {
				return 1;
			} else {
				return 0;
			}
		}
		if (target == 0) {
			return 1;
		}
		int include = 0;
		include += rec(arr, D, index - 1, target - arr[index]);

		int exclude = 0;
		exclude += rec(arr, D, index - 1, target);

		return include + exclude;
	}

	private static int recDP(int arr[], int index, int target, int dp[][]) {
		if (index == 0) {
			if (target == 0) {
				dp[index][target] = 1;
				return dp[index][target];
			}
			if (target == arr[index]) {
				dp[index][arr[target]] = 1;
				return dp[index][target];
			} else {
				dp[index][target] = 1;
				return dp[index][target];
			}
		}
		if (target == 0) {
			return dp[index][target];
		}
		if (dp[index][target] != -1) {
			return dp[index][target];
		}
		int include = 0;
		include = recDP(arr, index - 1, target - arr[index], dp);
		int exclude = 0;
		exclude = recDP(arr, index - 1, target, dp);
		dp[index][target] = include > exclude ? include : exclude;
		return dp[index][target];
	}

	private static int DPTabulation(int arr[], int target) {
		boolean[][] dp = new boolean[arr.length][target + 1];
		// base case
		for (int i = 0; i < arr.length; i++) {
			dp[i][0] = true;
		}
		dp[0][arr[0]] = true;

		for (int index = 1; index < arr.length; index++) {
			for (int t = 1; t <= target; t++) {
				boolean include = false;
				if (arr[index] <= t) {
					include = dp[index - 1][t - arr[index]];
				}
				boolean exclude = dp[index - 1][t];
				dp[index][t] = include || exclude;
			}
		}
		return dp[arr.length - 1][target] ? 1 : 0;
	}

	private static int NoDPTabulation(int arr[], int target) {
		boolean[] previous = new boolean[target + 1];
		// base cases
		previous[0] = true;
		previous[arr[0]] = true;

		for (int index = 1; index < arr.length; index++) {
			boolean[] current = new boolean[target + 1];
			current[0] = true;
			for (int t = 1; t <= target; t++) {
				boolean include = false;
				if (arr[index] <= t) {
					include = previous[t - arr[index]];
				}
				boolean exclude = previous[t];
				current[t] = include || exclude;
			}
			previous = current;
		}
		return previous[target] ? 1 : 0;
	}
}
