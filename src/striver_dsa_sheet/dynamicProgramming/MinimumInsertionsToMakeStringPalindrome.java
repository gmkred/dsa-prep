package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * S = "abcaa"
 * insert any character anywhere
 * 
 * The maximum operations can be len of string if we 
 * reverse and attach same string
 * 
 * abcaa + rev("abcaa")
 * 
 * abcaa aacba is a palindrome. this takes 5 operations
 * 
 * abcaa
 * 
 * a a bc b aa
 * min operations can be 2 in this problem.
 * 
 * 
 * How to pproach.
 * 
 * 1. Keep the longest palindrome portion intact.
 * ex : codingninjas
 * 
 *  to make this as palindrome first keep the existing palindrome in the sequnce intact.
 *  
 *  ingni is palinrome
 *  
 *  cod ingni jas
 * 	cod saj ingni jas doc
 * 
 * so the minimum operation required is 6
 * 
 * Total length - longest palindromic sequence = minimum number of operations.
 * </pre>
 * 
 */
public class MinimumInsertionsToMakeStringPalindrome {
	public static void main(String[] args) {
		String s = "codingnijas";
//		String s2 = new StringBuilder(s1).reverse().toString();
		int dp[][] = new int[s.length() + 1][s.length() + 1];
		for (int i = 0; i <= s.length(); i++) {
			for (int j = 0; j <= s.length(); j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(s.length() - rec(s, s.length(), -1, dp));
		String s2 = new StringBuilder(s).reverse().toString();
		System.out.println(s.length() - tabulation(s, s2));
		System.out.println(s.length() - NoDptabulation(s, s2));
	}

	private static int rec(String s, int i, int j, int dp[][]) {
		// ****If we are traversing same string both ways in 1 based indexing stopd it
		// at s.length()-1, if we go till s.length() then j+1 will result in out of
		// bound exception.
		if (i == 0 || j == s.length() - 1) {
			return 0;
		}
		if (dp[i - 1][j + 1] != -1) {
			return dp[i - 1][j + 1];
		}
		if (s.charAt(i - 1) == s.charAt(j + 1)) {
			dp[i - 1][j + 1] = 1 + rec(s, i - 1, j + 1, dp);
			return dp[i - 1][j + 1];
		}
		int moveLeft = 0 + rec(s, i, j + 1, dp);
		int moveRight = 0 + rec(s, i - 1, j, dp);
		dp[i - 1][j + 1] = Math.max(moveLeft, moveRight);
		return dp[i - 1][j + 1];
	}

	private static int tabulation(String s, String s2) {
		int dp[][] = new int[s.length() + 1][s2.length() + 1];
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					int moveLeft = 0 + dp[i][j - 1];
					int moveRight = 0 + dp[i - 1][j];
					dp[i][j] = Math.max(moveLeft, moveRight);
				}
			}
		}
		return dp[s.length()][s2.length()];
	}

	private static int NoDptabulation(String s, String s2) {
		int previous[] = new int[s2.length() + 1];
		for (int i = 1; i <= s.length(); i++) {
			int cur[] = new int[s2.length() + 1];
			for (int j = 1; j <= s2.length(); j++) {
				if (s.charAt(i - 1) == s2.charAt(j - 1)) {
					cur[j] = 1 + previous[j - 1];
				} else {
					int moveLeft = 0 + cur[j - 1];
					int moveRight = 0 + previous[j];
					cur[j] = Math.max(moveLeft, moveRight);
				}
			}
			previous = cur;
		}
		return previous[s2.length()];
	}
}
