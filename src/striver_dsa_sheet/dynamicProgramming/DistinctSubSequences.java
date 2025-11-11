package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * s1 = "babgbag"
 * s2 = "bag"
 * 
 * find how many distinct 'bag' are present in s1
 * 
 * 013
 * bag
 * 017
 * bag
 * 067
 * bag
 * 267
 * bag
 * 567
 * bag
 * Total 5 distinct bag's are present;
 * 
 * 
 * different methods of comparing.
 * Trying all ways.
 * 
 * Steps of recurrence:
 * 1. express everything in terms of indexes i and j
 * 2. explore all possibilities 
 * 3. return summation of all possibilities
 * 4. Base case
 * </pre>
 * 
 */
public class DistinctSubSequences {

	public static void main(String[] args) {
		String s1 = "babgbag";
		String s2 = "bag";
		int i = s1.length() - 1;
		int j = s2.length() - 1;
		System.out.println(rec(s1, s2, i, j));
		int dp[][] = new int[s1.length()][s2.length()];
		for (int m = 0; m < s1.length(); m++) {
			for (int n = 0; n < s2.length(); n++) {
				dp[m][n] = -1;
			}
		}
		System.out.println(recDP(s1, s2, i, j, dp));
		System.out.println(recDPTabulation(s1, s2));
		System.out.println(recNoDPTabulation(s1, s2));
	}

	private static int rec(String s1, String s2, int i, int j) {

		if (j < 0) {
			return 1;
		}
		if (i < 0) {
			return 0;
		}

		int match = 0;
		if (s1.charAt(i) == s2.charAt(j)) {
			// if there is a match call f(i-1,j-1) and f(i-1,j), becasue there will be
			// multiple matches which starts with g from right side, so we need to consider
			// them so keep the j at sam posisiton until it find next matching char.

			return rec(s1, s2, i - 1, j - 1) + rec(s1, s2, i - 1, j);
		}
		return rec(s1, s2, i - 1, j);
	}

//TC : O(N*M) SC : O(N*M) + O(N+M) auxiliary
	private static int recDP(String s1, String s2, int i, int j, int dp[][]) {

		if (j < 0) {
			return 1;
		}
		if (i < 0) {
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		if (s1.charAt(i) == s2.charAt(j)) {
			// if there is a match call f(i-1,j-1) and f(i-1,j), becasue there will be
			// multiple matches which starts with g from right side, so we need to consider
			// them.
			dp[i][j] = recDP(s1, s2, i - 1, j - 1, dp) + recDP(s1, s2, i - 1, j, dp);
			return dp[i][j];
		}
		dp[i][j] = recDP(s1, s2, i - 1, j, dp);
		return dp[i][j];
	}

//TC : O(N*M) SC : O(N*M) no auxiliary
	private static int recDPTabulation(String s1, String s2) {
		// 1 based indexing
		int dp[][] = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					// if there is a match call f(i-1,j-1) and f(i-1,j), becasue there will be
					// multiple matches which starts with g from right side, so we need to consider
					// them.
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}

//TC : O(N*M) SC : O(2*M)
	private static int recNoDPTabulation(String s1, String s2) {
		int previous[] = new int[s2.length() + 1];

		previous[0] = 1;

		for (int i = 1; i <= s1.length(); i++) {
			int cur[] = new int[s2.length() + 1];
			cur[0] = 1;
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					cur[j] = previous[j - 1] + previous[j];
				} else {
					cur[j] = previous[j];
				}
			}
			previous = cur;
		}
		return previous[s2.length()];
	}
}
