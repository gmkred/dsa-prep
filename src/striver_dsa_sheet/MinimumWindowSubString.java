package striver_dsa_sheet;

public class MinimumWindowSubString {

	public static void main(String[] args) {
		String s = "ADOBECODEBANC";
		String t = "ABC";
//		System.out.println(minWindow(s, t));
//		System.out.println(minWindow("cabwefgewcwaefgcf", "cae"));
		System.out.println(minWindow_Optimal(s, t));
		System.out.println(minWindow("a", "aa"));
	}

	public static String minWindow(String s, String t) {
		if (t.length() > s.length()) {
			return "";
		}
		int index = -1;
		int min = Integer.MAX_VALUE;
		int sindex = -1;
		for (int i = 0; i < s.length(); i++) {
			int count = 0;
			int[] hash = new int[256];
			for (int k = 0; k < t.length(); k++) {
				hash[t.charAt(k)]++;
			}
			in: for (int j = i; j < s.length(); j++) {
				if (hash[s.charAt(j)] > 0) {
					hash[s.charAt(j)]--;
					count++;
					if (count == t.length()) {
						if (min > j - i + 1) {
							min = j - i + 1;
							sindex = i;
						}
						break in;
					}
				}
			}
			if (count != t.length() && sindex == -1) {
				return "";
			}
		}
		return s.substring(sindex, sindex + min);
	}

	/**
	 * write explanation clearly.
	 */
	public static String minWindow_Optimal(String s, String t) {
		// edge case if t length > s length
		if (t.length() > s.length()) {
			return "";
		}
		int[] hash = new int[256];
		int count = 0, startingIndex = -1, min = Integer.MAX_VALUE;
		int l = 0, r = 0;
		for (int i = 0; i < t.length(); i++) {
			hash[t.charAt(i)]++;
		}
		while (r < s.length()) {
			char c = s.charAt(r);
			if (hash[c] > 0) {
				count++;
				hash[c]--;
			} else {
				hash[c]--;
			}
			while (count == t.length()) {
				if (min > r - l + 1) {
					startingIndex = l;
					min = r - l + 1;
				}
				hash[s.charAt(l)]++;
				if (hash[s.charAt(l)] > 0) {
					count--;
				}
				l++;

			}
			r++;
		}
		// Edge case : if startingIndex is not moved then there is no valid sub array.
		if (startingIndex == -1) {
			return "";
		}
		return s.substring(startingIndex, startingIndex + min);
	}

}
