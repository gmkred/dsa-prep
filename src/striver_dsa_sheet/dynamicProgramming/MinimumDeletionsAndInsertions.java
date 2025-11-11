package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * s1 = "abcd"
 * s2 = "anc"
 * 
 * 
 * the maximum operations
 * delete every thing in s1 and replace with s2
 * 
 * total operations are 4
 * 
 * 
 * but if we delete b and d in abcd and insert n from anc
 * it takes only 3 operations which are the minimum.
 * 
 * 
 * Just find the longest common sequence and 
 * find the left over characters as the count of deletions and insertions.
 * 
 * deletetions = s1.length - len(lcs)
 * insertions = s2.length - len(lcs)
 * 
 * Total ins and del = 
 * s1.length + s2.length - 2*len(lcs);
 * 
 * </pre>
 * 
 */
public class MinimumDeletionsAndInsertions {
	public static void main(String[] args) {
		String s1 = "abcd";
		String s2 = "anc";
		int i = s1.length() - 1;
		int j = s2.length() - 1;
		System.out.println(s1.length() + s2.length() - 2 * rec(s1, s2, i, j));
	}

	private static int rec(String s1, String s2, int i, int j) {
		if (i < 0 || j < 0) {
			return 0;
		}
		if (s1.charAt(i) == s2.charAt(j)) {
			return 1 + rec(s1, s2, i - 1, j - 1);
		}
		int moveS1 = 0 + rec(s1, s2, i - 1, j);
		int moveS2 = 0 + rec(s1, s2, i, j - 1);

		return Math.max(moveS1, moveS2);
	}
}
