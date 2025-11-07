package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * Given an array. find the subsets with sum K
 * 
 * {1,2,2,3} T = 3
 * 
 * {1,2}
 * {1,2}
 * {3}
 * 
 * </pre>
 */
public class CountSubsetsWithSumK {
	public static void main(String[] args) {
		int arr[] = { 1, 2, 2, 3 };
		int t = 3;
		System.out.println(rec(arr, t, arr.length - 1));
		int dp[][] = new int[arr.length][t + 1];
		// t > all the elements of the array.
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(recDP(arr, t, arr.length - 1, dp));
		System.out.println(DPTabulation(arr, t));
		System.out.println(NoDPTabulation(arr, t));

	}

//TC : O(2^N) SC : O(N) auxiliary
	private static int rec(int arr[], int t, int index) {
		if (t == 0) {
			return 1;
		}
//		if (index == 0) {
//			if (t == arr[0]) {
//				return 1;
//			} else {
//				return 0;
//			}
//		}
		// UPDATE : IF ARRAY HAS 0 AT 0th index
		if (index == 0) {
			// when we have 0, we can include and exclude it and we still get same t=0, so
			// count will be 2
			if (t == 0 && arr[0] == 0)
				return 2;
			// if target is 0 or arr[0] == t.
			else if (t == 0 || arr[0] == t)
				return 1;
			else {
				return 0;
			}
		}
		int include = 0;
		if (arr[index] <= t) {
			// including the arr[index] so removing it from target.
			include += rec(arr, t - arr[index], index - 1);
		}
		int exclude = 0;
		exclude += rec(arr, t, index - 1);

		return include + exclude;
	}

//TC : O(N) SC : O(N*Target) + O(N)

	private static int recDP(int arr[], int t, int index, int dp[][]) {
		if (t == 0) {
			return 1;
		}
		if (index == 0) {
			if (t == arr[0]) {
				return 1;
			} else {
				return 0;
			}
		}
		if (dp[index][t] != -1) {
			return dp[index][t];
		}
		int include = 0;
		if (arr[index] <= t) {
			// including the arr[index] so removing it from target.
			include += recDP(arr, t - arr[index], index - 1, dp);
		}
		int exclude = 0;
		exclude += recDP(arr, t, index - 1, dp);
		dp[index][t] = include + exclude;

		return dp[index][t];
	}

//TC : O(N) SC : O(N*Target) 
	private static int DPTabulation(int arr[], int t) {
		boolean dp[][] = new boolean[arr.length][t + 1];
		// base cases
		for (int i = 0; i < arr.length; i++) {
			dp[i][0] = true;
		}

		dp[0][arr[0]] = true;
		for (int index = 1; index < arr.length; index++) {
			for (int target = 1; target <= t; target++) {
				boolean include = false;
				if (arr[index] <= target) {
					include = dp[index - 1][target - arr[index]];
				}
				dp[index][target] = include || dp[index - 1][target];
			}
		}
		int count = 0;
		for (int i = 1; i < t + 1; i++) {
			if (dp[arr.length - 1][i]) {
				count++;
			}
		}
		return count;
	}

	// TC : O(N) SC : O(2*Target)
	private static int NoDPTabulation(int arr[], int t) {
		boolean original[] = new boolean[t + 1];
		// base cases
		original[0] = true;
		original[arr[0]] = true;
		for (int index = 1; index < arr.length; index++) {
			boolean temp[] = new boolean[t + 1];
			temp[0] = true;
			for (int target = 1; target <= t; target++) {
				boolean include = false;
				if (arr[index] <= target) {
					include = original[target - arr[index]];
				}
				boolean exclude = original[target];
				temp[target] = include || exclude;
			}
			original = temp;
		}
		int count = 0;
		for (int i = 1; i < t + 1; i++) {
			if (original[i]) {
				count++;
			}
		}
		return count;
	}
}
