package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * arr[] = {1,2,3} denominations 
 * Target = 7.
 * 
 * Minimum number of coins to add upto target
 * 
 * {1,1,2,3}
 * {2,2,3]
 * {3,3,1}
 * 
 * SO 3 coins are minimum.
 * 
 * we can do 
 * 
 * 7/3 = 2
 * 
 * 7%3 = 1
 * 
 * find if there is 1 denomination.
 * but this greedy approach will not work because there is no uniformity.
 * 
 * </pre>
 */
public class MinimumCoins {
	public static void main(String[] args) {
		int coins[] = { 1, 2, 3 };
		int target = 7;
		int index = coins.length - 1;
		System.out.println(minimumCoinsRec(index, target, coins));
		int dp[][] = new int[coins.length][target + 1];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(minimumCoinsRecDP(index, target, coins, dp));
		System.out.println(minimumCoinsRecDPTabulation(target, coins));
		System.out.println(minimumCoinsRecNoDPTabulation(target, coins));
	}

	/**
	 * <pre>
	 * TC : >> O(2^N) exponential because recursion stands at same index till conditioan fails.
	 * SC : >> O(Target)
	 * 
	 * </pre>
	 */
	private static int minimumCoinsRec(int index, int target, int coins[]) {
		if (index == 0) {
			if (coins[0] == target) {
				return 1;
				// if target is 12 and the coins[0] is 4, then 12 is divisible by 4, 3 times so
				// we can return 12/4
			} else if (target % coins[0] == 0) {
				return target / coins[0];
			} else {
				// use 1e9 because if we use MAX_VALUE+1 will go over flow
				return (int) 1e9;
			}
		}
		int include = Integer.MAX_VALUE;
		if (coins[index] <= target) {
			include = 1 + minimumCoinsRec(index, target - coins[index], coins);
		}

		int exclude = minimumCoinsRec(index - 1, target, coins);
		return Math.min(include, exclude);
	}

	// TC : O(N*T)
	// SC : O(N*T) + O(T) auxiliary
	private static int minimumCoinsRecDP(int index, int target, int coins[], int dp[][]) {
		// base

		if (index == 0) {
			if (coins[0] == target) {
				dp[0][target] = 1;
				// if target is 12 and the coins[0] is 4, then 12 is divisible by 4, 3 times so
				// we can return 12/4
			} else if (target % coins[0] == 0) {
				dp[0][target] = target / coins[0];
			} else {
				// use 1e9 because if we use MAX_VALUE+1 will go over flow
				dp[0][target] = (int) 1e9;
			}
			return dp[0][target];
		}

		if (dp[index][target] != -1) {
			return dp[index][target];
		}
		int include = Integer.MAX_VALUE;
		if (coins[index] <= target) {
			include = 1 + minimumCoinsRecDP(index, target - coins[index], coins, dp);
		}

		int exclude = minimumCoinsRecDP(index - 1, target, coins, dp);
		dp[index][target] = Math.min(include, exclude);
		return dp[index][target];
	}

	// TC : O(N*T)
	// SC : O(N*T)
	private static int minimumCoinsRecDPTabulation(int target, int coins[]) {
		int dp[][] = new int[coins.length][target + 1];
		for (int t = 0; t <= target; t++) {
			if (coins[0] == t) {
				dp[0][t] = 1;
			} else if (t % coins[0] == 0) {
				dp[0][t] = t / coins[0];
			} else {
				dp[0][t] = (int) 1e6;
			}
		}
		// base
		for (int index = 1; index < coins.length; index++) {
			for (int t = 0; t <= target; t++) {
				int include = Integer.MAX_VALUE;
				if (coins[index] <= t) {
					// we want to take same denomination until target is less that the denomination
					include = 1 + dp[index][t - coins[index]];
				}
				int exclude = dp[index - 1][t];
				dp[index][t] = Math.min(include, exclude);
			}
		}
		return dp[coins.length - 1][target];
	}

	// TC : O(N*T)
	// SC : O(2*T)
	private static int minimumCoinsRecNoDPTabulation(int target, int coins[]) {
		int previous[] = new int[target + 1];
		for (int t = 0; t <= target; t++) {
			if (coins[0] == t) {
				previous[t] = 1;
			} else if (t % coins[0] == 0) {
				previous[t] = t / coins[0];
			} else {
				previous[t] = (int) 1e6;
			}
		}
		// base
		for (int index = 1; index < coins.length; index++) {
			int temp[] = new int[target + 1];
			for (int t = 0; t <= target; t++) {
				int include = Integer.MAX_VALUE;
				if (coins[index] <= t) {
					// we want to take same denomination until target is less that the denomination
					include = 1 + temp[t - coins[index]];
				}
				int exclude = previous[t];
				temp[t] = Math.min(include, exclude);
			}
			previous = temp;
		}
		return previous[target];
	}
}
