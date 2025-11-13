package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * words[] = {"a","b","ba","bca","bda","bdca"}
 * 
 * chains are
 * {"a","ba","bda","bdca"} in each word it should have an extra letter compared to previous.
 * {"b","ba","bda",bdca"}
 * {"a","ba","bca"}
 * 
 * FInd longest chain possible.
 * 
 * </pre>
 */
public class LongestStringChain {
	public static void main(String[] args) {

		String words[] = { "a", "b", "ba", "bca", "bda", "bdca" };
		System.out.println(lsc(words));
	}

	private static int lsc(String[] words) {

		int maxi = 1;
		int dp[] = new int[words.length];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = 1;
		}
		for (int i = 0; i < words.length; i++) {
			int max = 1;
			for (int j = 0; j < i; j++) {
				if (compare(words[i], words[j]) && 1 + dp[j] > dp[i]) {
					dp[i] = Math.max(max, 1 + dp[j]);
					max = Math.max(max, dp[i]);
				}
			}
			maxi = Math.max(max, maxi);
		}
		return maxi;
	}

	private static boolean compare(String s1, String s2) {

		if (s1.length() != s2.length() + 1) {
			return false;
		}
		// i is longest string
		int i = 0;
		int j = 0;
		while (i < s1.length() && j < s2.length()) {
			if (s1.charAt(i) == s2.charAt(j)) {
				i++;
				j++;
			} else {
				i++;
			}
		}
		if (j == s2.length() && i == s1.length()) {
			return true;
		}
		return false;
	}

}
