package striver_dsa_sheet.dynamicProgramming;

public class PrintLongestIncreasingSubSequence {
	public static void main(String[] args) {
		int arr[] = { 5, 4, 11, 1, 16, 8 };
		int res[] = pringLIS(arr);
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	/**
	 * <pre>
	 * 
	 * To print LIS, first we need to find the LIS and while finding it we store the Previous index value in
	 * hash for current index for easy back tracing.
	 * </pre>
	 */
	private static int[] pringLIS(int arr[]) {
		int dp[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			dp[i] = 1;
		}
		int hash[] = new int[arr.length];
		int lis = 0;
		int lisIndex = 0;
		// For each ith index, we are finding the max increasing sequence.
		// { 5, 4, 11, 1, 16, 8 };
		// till 11 eith {5,11} or {4,11}
		// till 16, if 11 is < 16, that means till 16 we have 3 numbers {5,11,16} or
		// {4,11,16}
		for (int i = 0; i < arr.length; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					if (1 + dp[j] > dp[i]) {
						dp[i] = 1 + dp[j];
						hash[i] = j;
					}
				}
			}
			dp[i] = dp[i] + max;
			if (lis < dp[i]) {
				lis = dp[i];
				lisIndex = i;
			}
		}
		// dp [1, 1, 2, 1, 3, 2]
		// hash [0, 0, 0, 0, 2, 0]
		int res[] = new int[lis];
		int p = res.length - 1;
		while (lis >= 0 && p >= 0) {
			res[p] = arr[lisIndex];
			lisIndex = hash[lisIndex];
			p--;
		}
		return res;

	}
}
