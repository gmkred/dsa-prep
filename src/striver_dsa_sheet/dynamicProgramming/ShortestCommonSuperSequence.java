package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * s1 = "abcde"
 * 
 * s2 = "bdgek"
 * 
 * 
 * make a string such that both the strings should present in them.
 * 
 * "abcdebdgek" len = 10
 * 
 * but the shortest one can be
 * with order consideration.
 * 
 * "abcgdek" len = 7 shortest one.
 * 
 * find the LCS len = "bde" = 3
 * shortest length = s1+s2 - 2= 10-3= 7
 * 
 * But how to print the shortest super sequence.
 * {@link PrintLongestCommonSubstring#longestDPTabulation(String, String)}
 * 
 * 
 * create DP table first.
 * 
 * 1 based indexes
 *     0  1  2  3  4  5
 *        b  d  g  e  k
 *0   [0, 0, 0, 0, 0, 0] 
 *1 a [0, 0, 0, 0, 0, 0]
 *2 b [0, 1, 1, 1, 1, 1]
 *3 c [0, 1, 1, 1, 1, 1]
 *4 d [0, 1, 2, 2, 2, 2]
 *5 e [0, 1, 2, 2, 3, 3]
 * 
 * 
 * 
 * 
 * </pre>
 */
public class ShortestCommonSuperSequence {

	public static void main(String[] args) {
		String s1 = "abcde";
		String s2 = "bdgek";
		System.out.println(tabulation(s1, s2));
	}

	private static String tabulation(String s1, String s2) {
		int dp[][] = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					int moveS1 = 0 + dp[i - 1][j];
					int moveS2 = 0 + dp[i][j - 1];
					dp[i][j] = Math.max(moveS1, moveS2);
				}

			}

		}
		int i = s1.length();
		int j = s2.length();
		String ans = "";
		while (i > 0 && j > 0) {
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				ans = s1.charAt(i - 1) + ans;
				i--;
				j--;
			} else if (dp[i][j - 1] > dp[i - 1][j]) {
				ans = s2.charAt(j - 1) + ans;
				j--;
			} else {
				ans = s1.charAt(i - 1) + ans;
				i--;
			}
		}
		while (i > 0) {
			ans = s1.charAt(i - 1) + ans;
			i--;
		}
		while (j > 0) {
			ans = s2.charAt(j - 1) + ans;
			j--;
		}
		return ans;
	}
}
