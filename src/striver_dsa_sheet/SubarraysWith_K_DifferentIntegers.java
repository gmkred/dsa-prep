package striver_dsa_sheet;

import java.util.HashMap;
import java.util.Map;

/**
 * Same as
	 * {@link striver_dsa_sheet.NumberOdSubArraysWithSumK#numberOfSubArraysWithSumK(int[], int)}*/
public class SubarraysWith_K_DifferentIntegers {

	public static void main(String[] args) {
		int arr1[] = { 1, 2, 1, 3, 4 };
		int k = 3;
		System.out.println((subarraysWithKDistinct(arr1, k) - subarraysWithKDistinct(arr1, k - 1)));
	}

	/**
	 * <pre>
	 * Whenever we see a problem to find the number of subarrays with condition like == k
	 * And while solving it we are unsure when and until which point we have to move 'l' pointer.
	 * Then go for <span style=
	"color:green;font-weight:bold;font-size:13px">(subarrays <= k) - (subarrays <= k-1) = (subarrays == k)</sapn>
	 * </pre>
	 * 
	 * 
	 */
	private static int subarraysWithKDistinct(int[] nums, int k) {
		if (k < 0) {
			return 0;
		}
		int count = 0, l = 0, r = 0;
		Map<Integer, Integer> map = new HashMap<>();
		while (r < nums.length) {
			int _r = nums[r];
			if (map.containsKey(_r)) {
				map.put(_r, map.get(_r) + 1);
			} else {
				map.put(_r, 1);
			}
			// after the new value is update or set in map.
			while (map.size() > k) {
				int _l = nums[l];
				map.put(_l, map.get(_l) - 1);
				if (map.get(_l) == 0) {
					map.remove(_l);
				}
				l++;
			}
			if (map.size() <= k) {
				count += (r - l + 1);// we need to calculate how many sub arrays are possible from the newly
				// added element till 'l' the element.
			}
			r++;
		}
		return count;

	}
}
