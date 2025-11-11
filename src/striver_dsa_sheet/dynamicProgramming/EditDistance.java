package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * S1 = "horse"
 * 
 * S2 = "ros"
 * 
 * 
 * 3 options :
 * 1. insert
 * 2. remove
 * 3. replace
 * 
 * 
 * minimum number of steps to convert s1 to s2.
 * 
 * 
 * Maximum number of operations
 * replace s1 with s2
 * 
 * delete all s1 and insert al1 s2
 * 
 * s1+s2 steps
 * 
 * Next way
 * 
 * repalce h with r = rorse
 * remove r = rose
 * remove e = ros
 * 
 * s1 = "intention"
 * s2 = "execution"
 * 
 * remove t inention
 * replace i -> e enention
 * replace n -> x exention
 * replace n -> c exection
 * insert u execution
 * 
 * 5 operations are the minimum operations.
 * 
 * </pre>
 */
public class EditDistance {
	public static void main(String[] args) {
		String s1 = "intention";
		String s2 = "execution";
		int i = s1.length() - 1;
		int j = s2.length() - 1;
		System.out.println(rec(s1, s2, i, j));
		int dp[][] = new int[i + 1][j + 1];
		for (int m = 0; m < s1.length(); m++) {
			for (int n = 0; n < s2.length(); n++) {
				dp[m][n] = -1;
			}
		}
		System.out.println(recDP(s1, s2, i, j, dp));
		System.out.println(tabulation(s1, s2));
		System.out.println(NoDPtabulation(s1, s2));
	}

	private static int rec(String s1, String s2, int i, int j) {

		if (j < 0) {
			// when j is exhausted, and i >0 that means there ar sill i+1 elements left so
			// we can send that much count as operations.
			return i + 1;
		}
		if (i < 0) {
			return j + 1;
		}

		if (s1.charAt(i) == s2.charAt(j)) {
			return 0 + rec(s1, s2, i - 1, j - 1);
		}
		int remove = 0;
		int replace = 0;
		int insert = 0;
		/**
		 * <pre>
		 * hypothetically we are inserting s2 jth element next to ith element 
		 * and moving j to j-1, and 
		 * we dont move i because we have already thought of insertion as hypothetical.
		 *     i
		 * horse
		 *   j
		 * ros
		 * 
		 * we hypothetically think like inserting 's' from s2 to the end of s1
		 *      i
		 *  horses
		 *    j
		 *  ros
		 * 
		 * but we dont move i to 's' becasue its only hypothetical.
		 * Now we know that its a match so move j-1 nad i should be at same place becasue we did not change i and it should work fine.
		 * </pre>
		 */
		insert = 1 + rec(s1, s2, i, j - 1);
		/**
		 * <pre>
		 * 
		 * Delete ith element by moving i to next element.
		 *     i
		 * horse
		 *   j
		 * ros
		 * 
		 * just move i alone to ignore 'e'
		 *    i
		 * horse
		 *   j
		 * ros
		 * 
		 * </pre>
		 */
		remove = 1 + rec(s1, s2, i - 1, j);
		/**
		 * <pre>
		 * 
		 * Hypothetically think that i and j are same as we assume that jth elmenet is replace wit hith element and after that they will be equal.
		 *     i
		 * horse
		 *   j
		 * ros
		 * 
		 * thin that we have replaced 's' with 'e' and since they are now same so we can move forward.
		 * 
		 * </pre>
		 */
		replace = 1 + rec(s1, s2, i - 1, j - 1);

		return Math.min(replace, Math.min(remove, insert));

	}

	private static int recDP(String s1, String s2, int i, int j, int dp[][]) {
		if (i < 0) {
			return j + 1;
		}
		if (j < 0) {
			return i + 1;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		if (s1.charAt(i) == s2.charAt(j)) {
			dp[i][j] = 0 + recDP(s1, s2, i - 1, j - 1, dp);
			return dp[i][j];
		}
		int replace = 0;
		int remove = 0;
		int insert = 0;
		replace = 1 + recDP(s1, s2, i - 1, j - 1, dp);
		remove = 1 + recDP(s1, s2, i - 1, j, dp);
		insert = 1 + recDP(s1, s2, i, j - 1, dp);
		dp[i][j] = Math.min(replace, Math.min(remove, insert));
		return dp[i][j];
	}

	private static int tabulation(String s1, String s2) {
		int dp[][] = new int[s1.length() + 1][s2.length() + 1];
		// base cases.
		for (int i = 0; i <= s1.length(); i++) {
			// since we are using 1 based index starting assigning as 0 to dp[0][0] is
			// completely fine.
			dp[i][0] = i;
		}
		for (int i = 0; i <= s2.length(); i++) {
			dp[0][i] = i;
		}
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = 0 + dp[i - 1][j - 1];
				} else {
					int replace = 0;
					int remove = 0;
					int insert = 0;
					replace = 1 + dp[i - 1][j - 1];
					remove = 1 + dp[i - 1][j];
					insert = 1 + dp[i][j - 1];
					dp[i][j] = Math.min(replace, Math.min(remove, insert));
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}

	private static int NoDPtabulation(String s1, String s2) {
		int s1l = s1.length();
		int s2l = s2.length();
		int previous[] = new int[s2l + 1];
		// base cases.
		for (int i = 0; i <= s2l; i++) {
			previous[i] = i;
		}
		for (int i = 1; i <= s1l; i++) {
			int cur[] = new int[s2l + 1];
			// important to initialize 0 th index with i.
			cur[0] = i;
			for (int j = 1; j <= s2l; j++) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					cur[j] = 0 + previous[j - 1];
				} else {
					int replace = 1 + previous[j - 1];
					int remove = 1 + previous[j];
					int insert = 1 + cur[j - 1];
					cur[j] = Math.min(replace, Math.min(remove, insert));
				}
			}
			previous = cur;
		}
		return previous[s2l];
	}
}
