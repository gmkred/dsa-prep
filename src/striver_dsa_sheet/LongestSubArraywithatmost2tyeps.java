package striver_dsa_sheet;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * {3,3,3,1,2,1,1,2,3,3,4} out put : 5
 * 
 * 
 * </pre>
 * 
 */
public class LongestSubArraywithatmost2tyeps {

	public static void main(String[] args) {
		int arr[] = { 3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4 };
		System.out.println(longestSubarrayatmost2types(arr));
		arr = new int[] { 3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4 };
		System.out.println(longestSubarrayatmost2typesWITH_HASH_MAP(arr, 3));
	}

	/**
	 * <pre>
	* Intuition : track at most 2 number types with a dedicated variables.
	* 	when there is a new number type occurs try to shrink the window till any one of the number type
	* 	is completely removed from the window.
	* 
	* steps:
	* 1. initialize l,r max =0 which is general for all two pointers and sliding window.
	* 2. initialize cm1=0, cm2=0, n1=-1, n2=-1 to track the occurrences and number type.
	* 3. start a loop r < arr.length
	* 4. on each iteration
	* <span style = "color : red">
	* 	1. check if current arr[i] is equal to n1 or n2.
	* 		if yes, increase the respective cm.
	* 		if no, then check whether n1 or n2 are yet to start tracking a number type.
	* 			if yes, then assign new number type to n1 or n2 and increase respective cm.
	* 		if stll no, that means arr[i] is completely a new number type apart from existing one.
	* 		so, we need to shrink the window till it contains only at most 2 number types.
	* 	To shrink the window, loop l < r, on each iteration check if cm1 or cm2 has become 0.
	* 		assign new number type to n1 or n2 based on cm1 or cm2 (which ever becomes 0 first) 
	* 		and break the loop. 
	* remember : 
	* 	while shrinking, decrement the cm1 or cm2 based on arr[l] until cm1 or cm2 becomes '0'
	* 	That is how we know that the current window while shrinking only has at most 2 number types.
	* </span>
	* 
	* find the max
	* r++
	 * 
	 * 
	 * </pre>
	 * 
	 */
	// This culd be a bad idea because, in future if we want to have 3 different
	// number types then
	// we need to additional n3 to tract 3rd unique number.
	private static int longestSubarrayatmost2types(int arr[]) {

		int l = 0, r = 0, cm1 = 0, cm2 = 0, max = 0;
		int n1 = -1, n2 = -1;// arr[i] >=0 so having -1 for initialization.
		while (r < arr.length) {// constant
			if (arr[r] == n1) {
				cm1++;
			} else if (arr[r] == n2) {
				cm2++;
			} else if (n1 == -1) {
				n1 = arr[r];
				cm1++;
			} else if (n2 == -1) {
				n2 = arr[r];
				cm2++;
			} else {// if there comes a new number type then shrink the window
				while (l < r) {
					if (cm1 == 0) {
						n1 = arr[r];
						cm1++;
						break;
					} else if (cm2 == 0) {
						n2 = arr[r];
						cm2++;
						break;
					} else if (arr[l] == n1) {
						cm1--;
					} else if (arr[l] == n2) {
						cm2--;
					}
					l++;
				}
			}
			max = Math.max(max, (cm1 + cm2));// constant
			r++;// constant
		}

		return max;
	}

	/**
	 * <pre>
	 * Store the unique numbers and its frequencies in a hashmap, when ever there is
	 * more than k entries in map that means the window has more than required
	 * unique numbers so move the left side window and remove the frequenecy until
	 * any one of the entries frequency becomes 0 and remove that entry and find the
	 * max length using max and window length when there are only k unique elements
	 * in that subarray.
	 * 
	 * 
	 * { 3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4 } out put : 5 </span>
	 */
	private static int longestSubarrayatmost2typesWITH_HASH_MAP(int arr[], int numberOfUniqeNumbers) {
		int l = 0, r = 0, max = 0;
		Map<Integer, Integer> map = new HashMap<>();
		while (r < arr.length) {
			int _r = arr[r];
			if (map.containsKey(_r)) {
				map.put(_r, map.get(_r) + 1);
			} else {
				map.put(arr[r], 1);
			}
			if (map.size() > numberOfUniqeNumbers) {
				int _l = arr[l];
				map.put(_l, map.get(_l) - 1);
				if (map.get(_l) == 0) {
					map.remove(_l);
				}
				l++;
			}
			max = Math.max(max, (r - l + 1));
			r++;
		}
		return max;
	}
}
