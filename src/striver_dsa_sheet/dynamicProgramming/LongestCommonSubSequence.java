package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * Longest common sub sequence
 * 
 * s1 ="adebc"
 * a,d,e,b,c,ad,ae,ab,ac,ade,aeb,abc,adeb .....
 * 
 * 2^5 = 32 sub sequences
 * 
 * s2 = "dcadb"
 * 
 * 2^5 = 32 sub sequences.
 * 
 * 
 * what is the longest sub sequence in s1 and s2
 * 
 * 
 * "adb" is is longest sub sequence between s1 and s2.
 * 
 * 
 * Solution:
 * 
 * acd | ced
 * a     c
 * c     e
 * d     d
 * ac    ce
 * ad    cd
 * cd    ed 
 * acd   ced
 * 
 * 
 * Rules of recurrence:
 * 
 * 1. express everything in terms of indexes
 * 2. explore every possible thing on that index
 * 3. Take the best among them.
 * 
 * For strings a single index cannot represent both strings so use 2 indexes index1 and index2.
 * 
 * When exploring the strings, use match and not match similar to pick and not pick.
 * 
 * Matches are added to the answer and move to next.
 * 
 * </pre>
 */
public class LongestCommonSubSequence {

	public static void main(String[] args) {
		String s1 = "abcde";
		String s2 = "bdgek";
		int i1 = s1.length() - 1;
		int i2 = s2.length() - 1;
		System.out.println(longestCommonSubsequnecelength(s1, s2, i1, i2));
		int dp[][] = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(longestDP(s1, s2, i1 + 1, i2 + 1, dp));
		System.out.println(longestDPTabulation(s1, s2));
		System.out.println(longestNoDPTabulation(s1, s2));
	}

//TC : >> O(2^S1 * 2^S2) exponentially because same character is read more than once.
	private static int longestCommonSubsequnecelength(String s1, String s2, int i1, int i2) {

		// base case
		if (i1 < 0 || i2 < 0) {
			return 0;
		}
//At any given indexes of s1 and s2, if the characters are equal then add 1 and move i1 and i2 to next indexes of respective strings.

		if (s1.charAt(i1) == s2.charAt(i2)) {
			return 1 + longestCommonSubsequnecelength(s1, s2, i1 - 1, i2 - 1);
		}
		// if no match add 0
		int moveS1 = 0 + longestCommonSubsequnecelength(s1, s2, i1 - 1, i2);
		int moveS2 = 0 + longestCommonSubsequnecelength(s1, s2, i1, i2 - 1);
		return Math.max(moveS1, moveS2);
	}

	/**
	 * <pre>
	 * 
	 * 
	 * 1 based indexes
	 *             0   1   2   3   4
	 *             b   d   g   e   k
	 *         0   1   2   3   4   5
	 *     0 [ 0,  0,  0,  0,  0, -1]
	 * 0 a 1 [ 1,  1,  1,  1,  1, -1]
	 * 1 b 2 [ 1,  1,  1,  1,  1, -1]
	 * 2 c 3 [-1,  2,  2,  2,  2, -1]
	 * 3 d 4 [-1, -1, -1,  3,  3, -1]
	 * 4 e 5 [-1, -1, -1, -1, -1, -1]
	 * </pre>
	 */
// TC : (S1*S2) SC : O(S1*S2) + O(S1+S2)
	// think this as 1 based indexing.
	private static int longestDP(String s1, String s2, int i1, int i2, int dp[][]) {
		// -1 as 0
		if (i1 == 0 || i2 == 0) {
			return 0;
		}
		if (dp[i1 - 1][i2 - 1] != -1) {
			return dp[i1 - 1][i2 - 1];
		}
		if (s1.charAt(i1 - 1) == s2.charAt(i2 - 1)) {
			dp[i1 - 1][i2 - 1] = 1 + longestDP(s1, s2, i1 - 1, i2 - 1, dp);
			return dp[i1 - 1][i2 - 1];
		}
		int moveS1 = longestDP(s1, s2, i1 - 1, i2, dp);
		int moveS2 = longestDP(s1, s2, i1, i2 - 1, dp);
		dp[i1 - 1][i2 - 1] = Math.max(moveS1, moveS2);
		return dp[i1 - 1][i2 - 1];
	}

	/**
	 * <pre>
	 * 
	 * Since the base case here is at -1 index, it is not possible to have -1 index in dp tabulation, 
	 * So we use shifting of index to write tabulation.
	 * 
	 * Shifting of Index :
	 * N means N-1
	 * 1 means 0
	 * 2 means 1
	 * shifted 1 index right wards.
	 * 
	 * So every i1 should treat as i1-1
	 * every i2 should be treated as i2-1
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
	 * 
	 * TC : O(N*M) SC : O(N*M)
	 * 
	 * </pre>
	 */
	private static int longestDPTabulation(String s1, String s2) {
		int dp[][] = new int[s1.length() + 1][s2.length() + 1];
		// in java by default we have zeros in int array.
		// here 0 is -1 out of bound so it always returns 0;
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
		return dp[s1.length()][s2.length()];
	}

	private static int longestNoDPTabulation(String s1, String s2) {
		// s2 > s1
		int previous[] = new int[10];
		// even though the default values are 0's its just for practice.
		for (int i = 0; i <= s1.length(); i++) {
			previous[i] = 0;
		}
		for (int i1 = 1; i1 <= s1.length(); i1++) {
			int cur[] = new int[10];
			for (int i2 = 1; i2 <= s2.length(); i2++) {
				if (s1.charAt(i1 - 1) == s2.charAt(i2 - 1)) {
					cur[i2] = 1 + previous[i2 - 1];
				} else {
					// take the previous value of previous
					int moveS1 = previous[i2];
					// take the previous value of cur
					int moveS2 = cur[i2 - 1];
					cur[i2] = Math.max(moveS1, moveS2);
				}
			}
			previous = cur;
		}
		return previous[s2.length()];
	}
}
