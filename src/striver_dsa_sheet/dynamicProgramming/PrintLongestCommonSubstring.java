package striver_dsa_sheet.dynamicProgramming;

public class PrintLongestCommonSubstring {
	public static void main(String[] args) {
		String s1 = "abcde";
		String s2 = "bdgek";
		int i1 = s1.length() - 1;
		int i2 = s2.length() - 1;
		System.out.println(lcss(s1, s2, i1, i2));
		System.out.println(longestDPTabulation(s1, s2));
	}

	private static String lcss(String s1, String s2, int i1, int i2) {
		if (i1 < 0 || i2 < 0) {
			return "";
		}
		if (s1.charAt(i1) == s2.charAt(i2)) {
			return s1.charAt(i1) + lcss(s1, s2, i1 - 1, i2 - 1);
		}

		String moveS1 = lcss(s1, s2, i1 - 1, i2);

		String moveS2 = lcss(s1, s2, i1, i2 - 1);

		String max = moveS1.length() > moveS2.length() ? moveS1 : moveS2;
		return max;
	}

	/**
	 * <pre>
	 * 1 based indexes
	 *       b  d  g  e  k
	 *   [0, 0, 0, 0, 0, 0] 
	 * a [0, 0, 0, 0, 0, 0]
	 * b [0, 1, 1, 1, 1, 1]
	 * c [0, 1, 1, 1, 1, 1]
	 * d [0, 1, 2, 2, 2, 2]
	 * e [0, 1, 2, 2, 3, 3]
	 * 
	 * 
	 * Observe that when ever there is a change in the value 
	 * that is the character common in both the strings.
	 * so start a loop from [s1.length+1][s2.length+1]
	 * 
	 * and add the common character to string ans.
	 * 
	 * </pre>
	 */
	public static String longestDPTabulation(String s1, String s2) {
		int dp[][] = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			dp[i][0] = 0;
		}
		for (int i = 0; i <= s2.length(); i++) {
			dp[0][i] = 0;
		}
		for (int i1 = 1; i1 <= s1.length(); i1++) {
			for (int i2 = 1; i2 <= s2.length(); i2++) {
				if (s1.charAt(i1 - 1) == s2.charAt(i2 - 1)) {
					dp[i1][i2] = 1 + dp[i1 - 1][i2 - 1];
				} else {
					int moveS1 = dp[i1 - 1][i2];
					int moveS2 = dp[i1][i2 - 1];
					dp[i1][i2] = Math.max(moveS1, moveS2);
				}

			}
		}
		/**
		 * <pre>
		 * 1 based indexes
		 *       b  d  g  e  k
		 *   [0, 0, 0, 0, 0, 0] 
		 * a [0, 0, 0, 0, 0, 0]
		 * b [0, 1, 1, 1, 1, 1]
		 * c [0, 1, 1, 1, 1, 1]
		 * d [0, 1, 2, 2, 2, 2]
		 * e [0, 1, 2, 2, 3, 3]
		 * 
		 * 
		 * Observe that when ever there is a change in the value 
		 * that is the character common in both the strings.
		 * so start a loop from [s1.length+1][s2.length+1]
		 * 
		 * and add the common character to string ans.
		 * 
		 * </pre>
		 */

		int i = s1.length();
		int j = s2.length();

		String ans = "";
		while (i > 0 && j > 0) {
			if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
				ans = s1.charAt(i - 1) + ans;
				// once match found move to upper row diagonally to find next matching element.
				i--;
				j--;
			} else if (dp[i][j - 1] > dp[i - 1][j]) {
				j--;
			} else {
				i--;
			}
		}
		return ans;
	}
}
