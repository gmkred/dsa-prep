package striver_dsa_sheet;

public class LongestRepeatingCharacterReplacement {

	public static void main(String[] args) {

		System.out.println(longestRepeatingCharacterReplacement("ABAB", 2));
		System.out.println(longestRepeatingCharacterReplacement("AABABBA", 1));
		System.out.println(longestRepeatingCharacterReplacement("ABCDE", 1));
		System.out.println(longestRepeatingCharacterReplacement("ABBB", 2));
		System.out.println(longestRepeatingCharacterReplacement("ABAA", 0));
	}

	/**
	 * <pre>
	 * ABAB k =2
	 * AABABBA k=1
	 * ABCDE k=1
	 * ABBB k=2
	 * ABAA k=0
	 * 
	 * problem statement : find the longest substring will same characters after the k replacement.
	 * It can have any type of alphabet ABCDE, ABA, ABCDA. The goal is to turn any K elements to the
	 * maximum frequency character to increase the length of longest sub string.
	 * Steps: 
	 * 1. init l,r,max,maxFre =0
	 * 2. init arr[26] for 26 alphabet frequencies.
	 * On each iteration:
	 * 1. Increase the frequency of current char.
	 * 2. check if char char freq is greater than > previously stored freq, if yes update the freq.
	 * <span style="color : red; font-weight:bold;">3. Check if current window length (r-l+1) - (current max freq) <= K
	 *    i.e. if the current char freq is highest in the current window/substring and after removing it from the entire substring, it should be <= k to be a valid substring.
	 *    if valid, then find the max = Math.max(max, (r-l-1).
	 *    if the difference is > K, that means the substring contains more chars even after turning K chars to max freq char so it is invalid substring.
	 *    In this case, slide the window l++, but remember to reduce the freq of l th index char.
	 * 
	 * 
	 * </span>
	 * 4. r++
	 * </pre>
	 * 
	 */
	private static int longestRepeatingCharacterReplacement(String s, int k) {
		int max = 0, l = 0, r = 0;
		int maxFre = 0;
		int[] chars = new int[26];// to store the frequencies of all the characters of a window/substring.
		while (r < s.length()) {
			chars[s.charAt(r) - 'A']++;
			maxFre = Math.max(maxFre, chars[s.charAt(r) - 'A']);
			if (((r - l + 1) - maxFre) <= k) {
				max = Math.max(max, (r - l + 1));
			} else {
				chars[s.charAt(l) - 'A']--;
				l++;
			}
			r++;
		}
		return max;
	}
}
