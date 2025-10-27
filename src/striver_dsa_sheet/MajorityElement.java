package striver_dsa_sheet;

import java.util.HashMap;
import java.util.Map;

import striver_dsa_sheet.algos.MooresVoting_Algo;

/**
 * element appears more the N/2 times;
 * 
 * Moore's Voting algo
 * 
 */
public class MajorityElement {

	public static void main(String[] args) {
		int arr[] = { 2, 2, 1, 3, 3, 2, 2 };
		System.out.println(majorityElement(arr));
		System.out.println(majorityElement_MooresVoting(arr));
	}

	/**
	 * @see {@link MooresVoting_Algo}
	 * 
	 */
	private static int majorityElement_MooresVoting(int arr[]) {
		int el = 0;
		int count = 0;

		for (int i = 0; i < arr.length; i++) {
			if (count == 0) {
				el = arr[i];
				count++;
			} else if (el == arr[i]) {
				count++;
			} else {
				count--;
			}
		}
		count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == el) {
				count++;
			}
		}
		return count > arr.length / 2 ? el : -1;
	}

	/**
	 * <pre>
	 * 
	 * TC : O(N log N)
	 * SC : O(N) at most.
	 * 
	 * </pre>
	 */
	private static int majorityElement(int arr[]) {
		int e = 0;
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				e = map.get(arr[i]);
				map.put(arr[i], e + 1);
				e++;
				if (e > arr.length / 2) {
					return arr[i];
				}

			} else {
				map.put(arr[i], 1);
			}
		}

		return e;
	}
}
