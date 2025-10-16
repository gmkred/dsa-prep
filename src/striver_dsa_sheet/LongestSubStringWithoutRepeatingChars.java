package striver_dsa_sheet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//2 pointer and sliding window
public class LongestSubStringWithoutRepeatingChars {

	public static void main(String[] args) {
		String str = "abasdrehrh";
		System.out.println(bruteforce(str));
		System.out.println(better(str));
		System.out.println(striverOptimal(str));
	}

	/**
	 * <pre>
	 * TC : O(length^2) 
	 * SP : O(1) + O(length);
	 * </pre>
	 * 
	 * In brute force, store the characters into Set, each iteration check the
	 * current sub string and size of the Set, if same then uniqe sub string if not
	 * then duplicate chars are present so break the inner look
	 */
	private static String bruteforce(String str) {
		String maxUni = "";
		for (int i = 0; i < str.length(); i++) {
			String curStr = "" + str.charAt(i);
			Set<Character> temp = new HashSet();
			temp.add(str.charAt(i));
			for (int j = i + 1; j < str.length(); j++) {
				curStr = curStr.concat("" + str.charAt(j));
				temp.add(str.charAt(j));
				if (temp.size() != curStr.length()) {
					break;
				} else {
					maxUni = curStr.length() > maxUni.length() ? curStr : maxUni;
				}
			}
		}

		return maxUni;
	}

	/**
	 * <pre>
	 * "abasdrehrh"
	 * TC : O(N)
	 * SP : O(N) - extra Set
	 * 
	 * Solved this problem using 2 pointer and sliding window.
	 * 
	 * Storing each char in arr[256], if at any point of execution, there comes a same char that 
	 * is already present in the arr, that means its a duplicate char in the contiguous 
	 * sub array, so we have to slide our window to where there is no duplicate and update the flag to 0;
	 * 
	 * 
	 * </pre>
	 */

	private static String better(String str) {

		int[] all = new int[256];
		int l = 0, r = 1;
		String max = "" + str.charAt(r);
		while (r < str.length()) {
			if (all[str.charAt(r)] == 1) {
				all[str.charAt(r)] = 0;
				max = max.substring(1, max.length());
				l++;
			}
			all[str.charAt(r)] = 1;

			String cur = str.substring(l, r);
			max = max.length() < cur.length() ? cur : max;
			r++;

		}
		return max;
	}

	/**
	 * <pre>
	 * "abasdrehrh"
	 * TC : O(N)
	 * SP : O(N) - extra Set
	 * 
	 * Solved this problem using 2 pointer and sliding window.
	 * 
	 * 
	 * </pre>
	 */
	private static String optimal(String str) {

		int l = 0;// left side of window
		int r = 0;// right side of window
		String maxUnqStr = "";
		String curUnqStr = "";
		Set<Character> set = new HashSet<>();
		while (r < str.length()) {// loop till right side of window touches the right end.
			curUnqStr = curUnqStr + str.charAt(r);// concate or sum with the latest r th index.
			if (set.contains(str.charAt(r))) {// put conditions like <=, ==, >= for any type of problem.
				// if true, j=them remove the l index value and move left side of window towards
				// right.
				curUnqStr = curUnqStr.substring(1, curUnqStr.length());
				set.remove(str.charAt(r));
				l++;
			}
			// optional logic if any problem required goes here.
			set.add(str.charAt(r));
			// update max value.
			maxUnqStr = maxUnqStr.length() < curUnqStr.length() ? curUnqStr : maxUnqStr;
			r++;
		}
		return maxUnqStr;
	}

	/**
	 * <pre>
	 * using sliding window and a hashmap
	 * 
	 * <span style="color : green">l=r=max=0</span>
	 * hashmap <char,int>
	 * steps:
	 * <span style="color : green">common to all two pointer/sliding window</span>
	 * <span style="color : red">specific to this problem</span>
	 * 1. <span style=
	"color : green">loop through the string sequence (r < length).</span>
	 * 2. <span style="color : red">Find whether current char is present in the map.
	 * 3. if present check if existing char's index in map is >= window's begining 'l'
	 * 	(prevInd of cur char >= l) // this is to avoid considering the duplicate value which is outside of the window.
	 * 4. 	if yes, then move 'l' towards right side by 
	 * 		l = map.get(str.charAt(r)) +1 // i.e. moving a step ahead of existing char index.</span>
	 * 5. <span style=
	"color : green">add new cahrs to map, which will automatically updates the index of the char if there is duplicate.
	 * 6. increase r i.e. r++
	 * </span>
	 * {c,a,d,b,z,a,b,c,d}
	 * "abasdrehrh";
	 * 
	 * TC: O(N) + O(1) = O(N)
	 * 	To find an entry in hash map is O(1) to O(log N) but mostly O(1). 
	 * SC: O(256) 
	 * 	Because we will be updating the characters indexes so at max the hash map will store 256 entries
	 * </pre>
	 */
	private static int striverOptimal(String str) {
		int l = 0;// left side of window
		int r = 0;// right side of window
		int max = 0;
		int curMax = 0;
		HashMap<Character, Integer> map = new HashMap();

		while (r < str.length()) {
			if (map.containsKey(str.charAt(r))) {// if char is already present
				// take the existing chars index to check if its in the window or not.
				int preIndex = map.get(str.charAt(r));
				if (preIndex >= l) {// avoid moving l if the duplicate char index is outside of the window.
					l = map.get(str.charAt(r)) + 1;
				}
			}
			map.put(str.charAt(r), r);
			max = Math.max(max, (r - l + 1));// max of existing max length and current window length
			r++;
		}
		return max;
	}
}
