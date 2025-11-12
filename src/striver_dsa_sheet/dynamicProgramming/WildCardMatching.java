package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * ? = single character
 * * = 0 or more than 0
 * 
 * 
 * s1 = "?ay"
 * s2 = "ray"
 * 
 * we can match ? with r
 * 
 * s1 = "abc*cd"
 * s2 = "abcdefcd"
 * 
 * we cna macth * with def
 * 
 * But how do we do it?
 * 
 * if it is ?, then just match and move.
 * But if it is *, then we have to try all the possibilities until we find a match,
 * 
 * to match * with def, first we need to match * with '' and 'f' and 'e' and 'd'
 * 
 * 
 * </pre>
 */
public class WildCardMatching {
	public static void main(String[] args) {
		String s1 = "abc*cd";
		String s2 = "abcdefcd";
		int i = s1.length() - 1;
		int j = s2.length() - 1;
		int dp[][] = new int[s1.length()][s2.length()];
		for (int m = 0; m < s1.length(); m++) {
			for (int n = 0; n < s2.length(); n++) {
				dp[m][n] = -1;
			}
		}
		System.out.println(rec(s1, s2, i, j));
		System.out.println(recDp(s1, s2, i, j, dp));
		System.out.println(recDpTabulation(s1, s2));
		System.out.println(recNoDpTabulation(s1, s2));

	}

	// O(2^M*2^N) exponential. SC : O(N*M) + O(N+M) auxiliary.
	private static boolean rec(String s1, String s2, int i, int j) {
		// base
		if (i < 0 && j < 0) {
			return true;
		}
		// if j reach < 0, that means tere is nothing to compare j with i, so we need to
		// check i, if i is * then we can return true or else return false;
		if (j < 0) {
			while (i >= 0) {
				if (s1.charAt(i) != '*') {
					return false;
				}
				i--;
			}
			return true;
		}
		// if reaches <0 first, then
		if (i < 0 && j >= 0) {
			return false;
		}
		if (s1.charAt(i) == s2.charAt(j) || s1.charAt(i) == '?') {
			return rec(s1, s2, i - 1, j - 1);
		} else if (s1.charAt(i) == '*') {
			// firstly we are matching * with empty '' and also considering it for one char
			// this recursion happens until
			return rec(s1, s2, i - 1, j) || rec(s1, s2, i, j - 1);
		} else {
			return false;
		}
	}

	// TC : O(N*M) SC : O(M*N)
	private static boolean recDp(String s1, String s2, int i, int j, int dp[][]) {
		if (i < 0 && j < 0) {
			return true;
		}
		if (j < 0) {
			while (i >= 0) {
				if (s1.charAt(i) != '*') {
					return false;
				}
				i--;
			}
			return true;
		}
		// if reaches <0 first, then
		if (i < 0 && j >= 0) {
			return false;
		}
		if (dp[i][j] == 0) {
			return false;
		}
		if (s1.charAt(i) == s2.charAt(j) || s1.charAt(i) == '?') {
			boolean match = recDp(s1, s2, i - 1, j - 1, dp);
			dp[i][j] = match ? 1 : 0;
			return match;
		} else if (s1.charAt(i) == '*') {
			boolean astrisk = recDp(s1, s2, i - 1, j, dp) || recDp(s1, s2, i, j - 1, dp);
			dp[i][j] = astrisk ? 1 : 0;
			return astrisk;
		} else {
			return false;
		}
	}

	private static boolean recDpTabulation(String s1, String s2) {
		boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
		dp[0][0] = true;

		for (int i = 1; i <= s1.length(); i++) {
			boolean flag = true;
			for (int j = 1; j <= i; j++) {
				if (s1.charAt(j - 1) != '*') {
					flag = false;
				}
			}
			dp[i][0] = flag;
		}

		for (int j = 1; j <= s2.length(); j++) {
			dp[0][j] = false;
		}
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1) || s1.charAt(i - 1) == '?') {
					dp[i][j] = dp[i - 1][j - 1];
				} else if (s1.charAt(i - 1) == '*') {
					dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
				} else {
					dp[i][j] = false;
					;
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}

	private static boolean recNoDpTabulation(String s1, String s2) {
		boolean prev[] = new boolean[s2.length() + 1];
		prev[0] = true;

		for (int i = 1; i <= s1.length(); i++) {
			boolean cur[] = new boolean[s2.length() + 1];
			// before executing each i, we need to set cur 0th index with true, if it is '*'
			// else false.
			boolean flag = true;
			for (int j = 1; j <= i; j++) {
				if (s1.charAt(j - 1) != '*') {
					flag = false;
				}
			}
			cur[0] = flag;
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1) || s1.charAt(i - 1) == '?') {
					cur[j] = prev[j - 1];
				} else if (s1.charAt(i - 1) == '*') {
					cur[j] = prev[j] || cur[j - 1];
				} else {
					cur[j] = false;
					;
				}
			}
			prev = cur;
		}
		return prev[s2.length()];
	}
}
