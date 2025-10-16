package striver_dsa_sheet;

import java.util.HashMap;
import java.util.Map;

public class LongestSubStrinWithAtmostKElements {

	public static void main(String[] args) {
		String str = "aaabbccd";
		System.out.println(longestSubstringWithAtmostKElements(str, 3));
		System.out.println(longestSubstringWithAtmostKElements(str, 2));
	}

	/**
	 * same as longest sub array with k unique elements
	 */
	private static int longestSubstringWithAtmostKElements(String str, int k) {
		int max = 0, l = 0, r = 0;
		Map<Character, Integer> map = new HashMap<>();
		while (r < str.length()) {
			char _r = str.charAt(r);
			if (map.containsKey(str.charAt(r))) {
				map.put(_r, map.get(_r) + 1);
			} else {
				map.put(_r, 1);
			}
			if (map.size() == k) {
				char _l = str.charAt(l);
				map.put(_l, map.get(_l) - 1);
				if (map.get(_l) == 0) {
					map.remove(_l);
				}
				l++;
			}
			max = Math.max(max, r - l + 1);
			r++;
		}

		return max;
	}
}
