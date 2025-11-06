package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. 
 * In how many distinct ways can you climb to the top?
 * 
 * n = 3
 * 
 * 1 step + 1 step + 1 step
 * 1 step + 2 steps
 * 2 steps + 1 step
 * </pre>
 */
public class ClimbingStairs {
	public static void main(String[] args) {
		int n = 5;
		System.out.println(recursion(n));
		int dp[] = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			dp[i] = -1;
		}
		System.out.println(memoization(n, dp));
		System.out.println(tabulation(n));
	}

	private static int recursion(int n) {
		if (n == 0) {
			return 1;

		} else if (n < 0) {
			// because n-2 may result in -1
			return 0;
		}
		int count = 0;
		// like include and exclude here we can do n-1 and n-2
		// one step from current step
		count += recursion(n - 1);
		// two steps from current step
		count += recursion(n - 2);

		return count;
	}

	/**
	 * <pre>
	 * its similar to fibonacci
	 * 
	 * to find number of ways,
	 * suppose when we are on 3rd step, from here we can go to 3-1 = 2nd
	 * or 3-2 = 1st step, but at the end we have 2 ways.
	 * 
	 * when ever we revisit 3rd step, that means this will 3rd step will return 1 
	 * because in previous step also it returned 1
	 * 
	 * so, store each step value and in dp[n] and re use it instead of solving again.
	 * </pre>
	 */
	private static int memoization(int n, int dp[]) {
		if (n == 0) {
			return 1;
		} else if (n < 0) {
			return 0;
		}
		if (dp[n] != -1) {
			return dp[n];
		}

		int left = memoization(n - 1, dp);
		int right = memoization(n - 2, dp);
		dp[n] = left + right;
		return dp[n];
	}

	private static int tabulation(int n) {
		int step1 = 0;
		int step2 = 1;
		int ways = 0;
		for (int i = 0; i < n; i++) {
			ways = step2 + step1;
			step1 = step2;
			step2 = ways;
		}
		return ways;
	}
}
