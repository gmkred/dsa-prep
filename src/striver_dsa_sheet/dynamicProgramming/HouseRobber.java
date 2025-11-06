package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * 
 * Max sum with out taking adjacent's in the sub sequence.
 * 
 * 
 * {2,7,9,3,1}
 * 2+9+1 = 12
 * 7+3+1 = 11
 * 2+3 = 5
 * 7+1 = 8
 * 
 * 12 is the maximum.
 * 
 * To solve this we use include and exclude concept.
 * 
 * but instead of moving to i+1, we go to i+2 or i-2
 * and while excluding we just remove the current value from sum and go to next i+1 or i-1
 * 
 * 
 * This we we get the sums of sub sequences with out adjacent's
 * </pre>
 */
public class HouseRobber {

	public static void main(String[] args) {
		int arr[] = { 2, 7, 9, 3, 1 };
		int n = arr.length - 1;
		System.out.println(recursion(arr, n));
		int dp[] = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			dp[i] = -1;
		}
//		System.out.println(recursionDP(arr, n, dp));
//		System.out.println(recursionDPTabulation(arr, n, dp));
		System.out.println(recursionNoDPTabulation(arr, n));
	}

	private static int recursion(int[] arr, int index) {
		if (index == 0) {
			return arr[index];
		}
		if (index < 0) {
			return 0;
		}
		// similar to include, but we should not take adjacent value so skipping 1 thats
		// why its index-2
		int include = arr[index] + recursion(arr, index - 2);

		// similar to exclude, but only exclude no addition as include,
		// remember : this exclude again calls include when it moves to next or prev
		// index.
		int exclude = recursion(arr, index - 1);
		// when it started returning we need to take only the max value from al lthe
		// recursive calls.
		return Math.max(include, exclude);
	}

	private static int recursionDP(int[] arr, int index, int dp[]) {
		if (index == 0) {
			return arr[index];
		}
		if (index < 0) {
			return 0;
		}
		if (dp[index] != -1) {
			return dp[index];
		}
		int include = arr[index] + recursionDP(arr, index - 2, dp);

		int exclude = recursionDP(arr, index - 1, dp);

		dp[index] = Math.max(include, exclude);

		return dp[index];
	}

	private static int recursionDPTabulation(int[] arr, int index, int dp[]) {
		dp[0] = arr[0];
		int include = Integer.MIN_VALUE;
		int exclude = Integer.MIN_VALUE;
		for (int i = 1; i <= index; i++) {
			include = arr[i];
			if (i > 1) {
				include += dp[i - 2];
			}
			exclude = dp[i - 1];
			dp[i] = Math.max(include, exclude);
		}

		return dp[index];
	}

	private static int recursionNoDPTabulation(int[] arr, int index) {
		int include = 0;
		int exclude = 0;
		int prev1 = arr[0];
		int prev2 = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= index; i++) {
			include = arr[i];
			if (i > 1) {
				include += prev2;
			}
			exclude = prev1;
			max = Math.max(include, exclude);
			prev2 = prev1;
			prev1 = max;
		}
		return max;
	}

}
