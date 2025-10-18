package striver_dsa_sheet;

public class MinimumWindowSubString {

	public static void main(String[] args) {
		String s = "ADOBECODEBANC";
		String t = "ABC";
//		System.out.println(minWindow(s, t));
//		System.out.println(minWindow("cabwefgewcwaefgcf", "cae"));
		System.out.println(minWindow_Optimal(s, t));
		System.out.println(minWindow("a", "aa"));
		String num ="24123";
		int ans = 48;
		for (int i = 0; i < num.length(); i++) {
			if (i % 2 == 0) {
				ans = ans + (num.charAt(i));

			} else {
				ans = ans - (num.charAt(i));
			}
			System.out.println(ans);
		}
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
	 *<pre>
	 *poroblem statement: find the minimum substring which contains all the characters of another string
	 *
	 *example :1 
	 *	String s = "ADOBECODEBANC"; String t = "ABC";
	 *
	 *OUT put = "BANC"
	 *
	 *example 2 : String s = "cabwefgewcwaefgcf", String t = "cae"
	 *	
	 *OUT put = "cwae"
	 *
	 *A character can be lower or upper case and it can be repeated many times in the t string.
	 *we need to find the minimum subarray that contains all the chars and same frequency of chars in t string.
	 *
	 *to solve this we need hash[256] each represent a char.
	 *
	 *Steps:
	 *
	 *1. initilize hash[256] with chars frequency from 0 i.e if 'A' is present in t ,then hash['A'] = 0
	 *	initialize all the other chars with -1
	 *2. int count =0, l=0, r=0, minLen = string.length, startingIndex;
	 *3. maintain t string's length. it is used to identify a substring whether it contains all the t string chars.
	 *4. on each iteration over string s
	 *	1. Take each char and decrease the hash[s.charAt(r)]--
	 *	   While decreasing, check if the hash[s.cahrAt(r)] is >0;
	 *	   and increase the count if hash[s.charAt(r)] is >0
	 *	2. when (count == t.length)  that means, all the chars in t string are present in the current substring.
	 *	3. Now, we need to find whether that is min substring or not, by shrinking the current substring.
	 *	4. while shrinking, do hash[s.chartAt(l)]++ and check if the value is >= 0 then decrease the count.
	 *	5. until the (count == t.length) it is a valid substring and update the minLen and also update the startingIndex
	 *	   with the latest 'l' value.
	 *	6. use s.substrin(startingIndex, startingIndex + min);
	 *</pre>
	 *
	 *
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
