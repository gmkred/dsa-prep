package striver_dsa_sheet;

import java.util.HashMap;
import java.util.Map;

public class NumberOfSubstringsContainingAll3Charachters {

	public static void main(String[] args) {
		String s = "abcabc";
		System.out.println(numberOfSubstringsContainingAll3Charachtersbetter(s));
		System.out.println(numberOfSubstringsContainingAll3CharachtersOptimal(s));
	}

	private static int numberOfSubstringsContainingAll3Charachtersbetter(String s) {
		int count = 0;
		int l = 0, r = 0;
		Map<Byte, Integer> map = new HashMap<>();

		while (r < s.length()) {
			byte _r = (byte) s.charAt(r);
			if (map.containsKey(_r)) {
				map.put(_r, map.get(_r) + 1);
			} else {
				map.put(_r, 1);
			}
			while (map.size() == 3) {
				count = count + s.length() - r;
				byte _l = (byte) s.charAt(l);
				int i = map.put(_l, map.get(_l) - 1);
				if (i == 1) {
					map.remove(_l);
				}
				l++;
			}
			r++;
		}
		return count;

	}

	// optimal
	/**
	 * <pre>
	 * init l,r, sum=0;
	 * maintain an array of size 3, for a,b,c and init them with -1 
	 * arr[0] for a, arr[1] for b, and arr[2] = c
	 * 
	 * on each iteration:
	 * 1. Take string.charAt(r) and assign the index of it to respective arr position.
	 * 2. Check if it is a valid substring. i.e. all 3 characters are present in the substring.
	 * 3. If yes : consider that as one substring.
	 * 4. Now, find the least valid substring.
	 * <b>Important note:</b> A least valid substring can be bac, cab etc. which can start with any of the 3 chars.
	 * 		 So, we have to consider the least index value and calculate the number of substrings formed from it. 
	 * 	<b>Example : {2,1,4} this means the least valid starts with 'b' index is 1
	 * 		   Sum = sum + 1 + (index of 'b' - l)</b>
	 * </pre>
	 * */
	private static int numberOfSubstringsContainingAll3CharachtersOptimal(String s) {
		int count = 0;
		int l = 0, r = 0;
		int[] arr = new int[] { -1, -1, -1 };

		while (r < s.length()) {
			arr[s.charAt(r) - 'a'] = r; // store the last occurrence index of a char.
			if (arr[0] != -1 && arr[1] != -1 && arr[2] != -1) {// all chars occurred.
				int minIndex = Math.min(arr[0], Math.min(arr[1], arr[2]));
				count = count + 1 + (minIndex - l);
			}
			r++;
		}
		return count;

	}
}
