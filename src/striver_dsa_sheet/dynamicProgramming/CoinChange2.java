package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * {1,2,3}
 * 
 * T = 4
 * 
 * ANy element can be used any number of times.
 * 
 * {1,1,1,1} = 4
 * {1,1,2} =4
 * {2,2}=4
 * {1,3} = 4
 * 
 * </pre>
 */
public class CoinChange2 {
	public static void main(String[] args) {
		int arr[] = { 1, 2, 3 };
		int t = 4;
		int index = arr.length - 1;
		System.out.println(coinChangeRec(index, t, arr));
		int dp[][] = new int[arr.length][t + 1];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(coinChangeRecDp(index, t, arr, dp));
		System.out.println(coinChangeDpTabulation(t, arr));
		System.out.println(coinChangeNoDpTabulation(t, arr));
	}

	private static int coinChangeRec(int index, int target, int arr[]) {
		if (index == 0) {
			if (target == 0 && arr[0] == 0) {
				return 2;
			}
			if (target == 0 || arr[0] == target) {
				return 1;
			}
			// very important.
			if (target % arr[0] == 0) {
				return 1;
			} else {
				return 0;
			}
		}
		if (target == 0) {
			return 1;
		}
		int in = 0;
		int ex = 0;
		if (arr[index] <= target) {
			in = coinChangeRec(index, target - arr[index], arr);
		}
		ex = coinChangeRec(index - 1, target, arr);

		return in + ex;
	}

	private static int coinChangeRecDp(int index, int target, int arr[], int dp[][]) {

		if (index == 0) {
			if (target == 0 && arr[0] == 0) {
				dp[index][target] = 2;
				return dp[index][target];
			}
			if (target == 0 || arr[0] == target) {
				dp[index][target] = 1;
				return dp[index][target];
			}
			// very important.
			if (target % arr[0] == 0) {
				dp[index][target] = 1;
				return dp[index][target];
			} else {
				dp[index][target] = 0;
				return dp[index][target];
			}
		}
		if (target == 0) {
			dp[index][target] = 1;
			return dp[index][target];
		}
		if (dp[index][target] != -1) {
			return dp[index][target];
		}

		int in = 0;
		int ex = 0;
		if (arr[index] <= target) {
			in = coinChangeRecDp(index, target - arr[index], arr, dp);
		}
		ex = coinChangeRecDp(index - 1, target, arr, dp);
		return dp[index][target] = in + ex;
	}

	private static int coinChangeDpTabulation(int target, int arr[]) {
		int dp[][] = new int[arr.length][target + 1];

		for (int t = 0; t <= target; t++) {
			if (t % arr[0] == 0) {
				dp[0][t] = 1;
			}
		}
		for (int index = 1; index < arr.length; index++) {
			for (int t = 0; t <= target; t++) {
				int in = 0;
				int ex = 0;
				if (arr[index] <= t) {
					in = dp[index][t - arr[index]];
				}
				ex = dp[index - 1][t];
				dp[index][t] = in + ex;
			}
		}
		return dp[arr.length - 1][target];
	}

	private static int coinChangeNoDpTabulation(int target, int arr[]) {
		int previous[] = new int[target + 1];

		for (int t = 0; t <= target; t++) {
			if (t % arr[0] == 0) {
				previous[t] = 1;
			}
		}
		for (int index = 1; index < arr.length; index++) {
			int cur[] = new int[target + 1];
			for (int t = 0; t <= target; t++) {
				int in = 0;
				int ex = 0;
				if (arr[index] <= t) {
					in = cur[t - arr[index]];
				}
				ex = previous[t];
				cur[t] = in + ex;
			}
			previous = cur;
		}
		return previous[target];
	}

}