package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * 
 * s1 = "abcjlp"
 * s2 = "acjkp"
 * 
 * ans = "cjk"
 * </pre>
 */
public class LongestCommonSubstring {
	public static void main(String[] args) {
		String s1 = "abcjkp";
		String s2 = "acjke";
		System.out.println(longestCommonSubstring(s1, s2));
	}

	private static int longestCommonSubstring(String s1, String s2) {
		int max = 0;
		int dp[][] = new int[s1.length() + 1][s2.length() + 1];

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[i].length; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = 1 + dp[i - 1][j - 1];
					max = Math.max(max, dp[i][j]);
				} else {
					dp[i][j] = 0;
				}
			}
		}
		return max;
	}
}