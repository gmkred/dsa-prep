package striver_dsa_sheet;

public class LongestOnesInASubArray {

	public static void main(String[] args) {
		int[] arr = { 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 };
		System.out.println(longestOnesInASubarray(arr));
		arr = new int[] { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
		int k = 2;
		System.out.println(longestOnesInASubarrayIfKZerosCanFlip(arr, k));
		arr = new int[] { 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0 };
		k = 3;
		System.out.println(longestOnesInASubarrayIfKZerosCanFlip(arr, k));

		System.out.println("optimal");
		arr = new int[] { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
		k = 2;
		System.out.println(longestOnesInASubarrayIfKZerosCanFlipOptimal(arr, k));
		arr = new int[] { 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0 };
		k = 3;
		System.out.println(longestOnesInASubarrayIfKZerosCanFlipOptimal(arr, k));

	}

	/**
	 * <pre>
	 * Solved using Sliding window.
	 * { 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1 }
	 * ANS : 4 : { 1, 1, 1, 0, 0,  <span style=
	 "color : red">1, 1, 1, 1,</span> 0, 0, 1 }
	 *
	 * steps:
	 * 1. init l, r, curMax, max = 0
	 * 2. loop till arr.length.
	 * 3. On each iteration.
	 * <span style="color:red">
	 * 4. check if the current value is != 1
	 * 	if yes:
	 * 		move 'l' to 'r' to start a fresh subarray.
	 * 		increament l ++ until arr[i] = 1
	 * 		make curMax = 0 to start a fresh curMax
	 * </span>
	 * 5. find the curMax = r-l+1 (window size)
	 * 6. find the new max by comparing the max and curMax.
	 * 7. increment r++
	 * 
	 * 
	 * </pre>
	 * 
	 */
	private static int longestOnesInASubarray(int[] arr) {
		int l = 0, r = 0, curMax = 0, max = 0;

		while (r < arr.length) {
			if (arr[r] != 1) {
				l = r; // if arr[i] = 0 then move l to r and increase l till arr[i] = 1
				l++;
				curMax = 0; // make curMax is 0 to start a new max leangth
			}
			curMax = r - l + 1; // curent window length
			max = Math.max(curMax, max);// find max of max, curMax
			r++;
		}
		return max;
	}

	/**
	 * <pre>
	 * {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0} k= 2 
	 *   0  1  2  3  4  5  6  7  8  9  10  11 12  13
	 * { 1, 1, 1, 0, 0, 0, 0, 0, 1, 1,  1,  1, 1,  0 } k = 3
	 * 
	 * 
	 * 
	 * 
	 * TC : O(2N)
	 * </pre>
	 */
	private static int longestOnesInASubarrayIfKZerosCanFlip(int arr[], int k) {
		int l = 0, r = 0, curMax = 0, max = 0, zeroes = 0;

		while (r < arr.length) {
			if (arr[r] == 0) {
				zeroes++;
			}
			while (zeroes > k) {// shrink window
				if (arr[l] == 0) {
					zeroes--;
				}
				l++;
				continue;
			}

			curMax = r - l + 1;
			max = Math.max(max, curMax);
			r++;
		}

		return max;
	}

	/**
	 * <pre>
	 * {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0} k= 2 
	 *   0  1  2  3  4  5  6  7  8  9  10  11 12  13
	 * { 1, 1, 1, 0, 0, 0, 0, 0, 1, 1,  1,  1, 1,  0 } k = 3
	 * 
	 * 
	 * Steps:
	 * 1. initialize l,r,cur,max,zeroes =0;
	 * 2. loop through array r < arr.length
	 * 3. on each iteration<span style="color:red">
	 * 	1. check if the current  value is 0, if yes increment zeroes++
	 * 	2. check if zeroes > k
	 * 		1. if yes i.e. the window has exceeded the flippable '0's. 
	 * 		   so start sliding the window by checking arr[l] if it is '0' then zeroes-- and
	 * 		   l++ to slide along with 'r' and r++ (which is normally happens);
	 * 		   This way we can slide through invalid window until zeroes <= k that means 
	 * 		   it a valid subarray and only increase r++ to check if that sub array can be increased
	 * 		   until zeroes> k or r == arr.length
	 * 	3. if zeroes <= k then calcualte the max length, because while sliding the size of subarray 
	 * 	   will be same, so no comparison while sliding.</span>
	 * 	4. r++;
	 * 
	 * 
	 * TC: O(N)
	 * SP : O(1)
	 * </pre>
	 */
	private static int longestOnesInASubarrayIfKZerosCanFlipOptimal(int arr[], int k) {
		int l = 0, r = 0, curMax = 0, max = 0, zeroes = 0;

		while (r < arr.length) {
			if (arr[r] == 0) {
				zeroes++;
			}
			// slide window instead of shrinking to make it O(N) from O(2N)
			if (zeroes > k) {
				if (arr[l] == 0) {
					zeroes--;
				}
				l++;
			} else {
				curMax = r - l + 1;
				max = Math.max(max, curMax);
			}
			r++;
		}

		return max;
	}
}
