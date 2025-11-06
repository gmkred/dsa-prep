package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 0 1 1 2 3 = 5
 * 
 * TC : O(N)
 * SC : O(N) stack space + O(N) array
 * </pre>
 */
public class FibonacciNumber {

	public static void main(String[] args) {
		int n = 5;
		int dp[] = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			dp[i] = -1;
		}
		System.out.println(fibonnaci(n, dp));
		System.out.println(fibonacci_tabulation(n));
		System.out.println(fibonacci_tabulation2(n));
	}

//Memoization
	private static int fibonnaci(int n, int dp[]) {

		if (n <= 1) {
			return n;
		}
		if (dp[n] != -1) {
			return dp[n];
		}

		dp[n] = fibonnaci(n - 1, dp) + fibonnaci(n - 2, dp);
		return dp[n];
	}

	// Tabulation
	// without recursion, using Tabulation.
	private static int fibonacci_tabulation(int n) {

		int dp[] = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		return dp[5];
	}

	// Tabulation
	// some problems may require only few variables instead of DP
	private static int fibonacci_tabulation2(int n) {

		int prev2 = 0;
		int prev1 = 1;
		int cur = 0;
		for (int i = 2; i <= n; i++) {
			cur = prev1 + prev2;
			prev2 = prev1;
			prev1 = cur;
		}

		return cur;
	}
}
