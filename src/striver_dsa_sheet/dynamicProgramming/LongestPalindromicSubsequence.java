package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * s = "bbbab";
 * 
 * bb
 * bbb
 * bab
 * b
 * bbbb
 * 
 * </pre>
 */
public class LongestPalindromicSubsequence {
	public static void main(String[] args) {
		String s = "bbbab";
		int dp[][] = new int[s.length() + 1][s.length() + 1];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				dp[i][j] = -1;
			}
		}
		int i = s.length();
		int j = -1;
		System.out.println(rec(s, i, j, dp));
	}

	private static int rec(String s, int i, int j, int dp[][]) {
		if (i == 0 || j == s.length()) {
			dp[i][j] = 0;
			return dp[i][j];
		}
		if (dp[i - 1][j + 1] != -1) {
			return dp[i - 1][j + 1];
		}
		if (s.charAt(i - 1) == s.charAt(j + 1)) {
			dp[i - 1][j + 1] = 1 + rec(s, i - 1, j + 1, dp);
			return dp[i - 1][j + 1];
		}
		int moveRight = 0 + rec(s, i, j + 1, dp);
		int moveLeft = 0 + rec(s, i - 1, j, dp);
		dp[i - 1][j + 1] = Math.max(moveRight, moveLeft);
		return dp[i - 1][j + 1];
	}
}
