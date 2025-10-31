package striver_dsa_sheet;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 
 * </pre>
 */
public class NumberOfSubArraysWithXOR_K {

	public static void main(String[] args) {
		int arr[] = { 4, 2, 2, 6, 4 };
		// prefixSum {0, 4, 6, 4, 2, 6 }
		int k = 6;
		System.out.println(findNumberOfSubarraysWithXorK(arr, k));
		System.out.println(findNumberOfSubarraysWithXorK_optimal(arr, k));
	}

	/**
	 * <pre>
	 * 
	 * use prefix xor.
	 * XOR ^ K = X
	 * If X present that means, after that till current index, its a XOR of k.
	 * 6 ^ 2 = 4
	 * check current prefix (XOR ^ K) is present in the map, if yes that means after that till 
	 * current index its a XOR of K.
	 * 
	 * map at the end looks like
	 * prefix XOR and frequency
	 *    6,            2
	 *    4,            2
	 *    2,            1
	 *    0,            1
	 * 
	 * TC : O(N) if maps does not have collisions.
	 * TC : O(N log N) if map has collisions and searches for an element in the bucket. 
	 * SC : O(N)
	 * </pre>
	 */
	private static int findNumberOfSubarraysWithXorK_optimal(int arr[], int k) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		int xor = 0;
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			xor ^= arr[i];
			if (map.containsKey(xor ^ k)) {
				count += map.get(xor ^ k);
			}
			if (map.containsKey(xor)) {
				map.put(xor, map.get(xor) + 1);
			} else {
				map.put(xor, 1);
			}
		}
		return count;

	}

	/**
	 * <pre>
	 * 
	 * TC : O(N^2)
	 * SC : O(1)
	 * 
	 * </pre>
	 */
	private static int findNumberOfSubarraysWithXorK(int arr[], int k) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			int xor = arr[i];
			if (xor == k) {
				count++;
			}
			for (int j = i + 1; j < arr.length; j++) {
				xor ^= arr[j];
				if (xor == k) {
					count++;
				}
			}

		}
		return count;

	}
}
