package striver_dsa_sheet;

/**
 * <pre>
 * patterns
 * 1.  constant window :
 * 	given an array and window size, find the max sum of a sub arrya size k.
 * 
 * 2** longest subarray or substring where condition like sum <=k
 * 	three templates:
 * 	1. generate all the subarrays using nested for loops
 * 	2. use two pointer and sliding window.
 * 		usually we have expand and shrink 
 * 		expand happens to the right side of the window 
 * 		and shrink happens at the left side of the window.
 * 	3. instead of shrinking multiple times, move the window.
 * 3. number of subarrays where some condition which is constant
 * 	we use the 2nd pattern to solve this problem.
 * 	<b>example question: find number of subarrays whose sum are exactly equal to K
 * 	sol : (find all the sub arrays sum  <=K) - (find all sub arrays sum <= (K-1)) 
 * 	= Number of subarrays whos sum is equal k  </b>
 * 4(rare). finding the shortest or the minimum window where some condition.
 * 		first find the alid window and shrink it till we find shortest valid window.
 * </pre>
 */

public class TwoPointer_SlidingWindow {

	public static void main(String[] args) {
		constantWIndow();
		longestSubarraysum();
	}

	// when there is constant window increase l and r
	public static void constantWIndow() {
		int arr[] = { 2, 5, 3, 6, 9, 8, 1, 2, 5 };
		int k = 4; // window size

		int l = 0;
		int r = 0;
		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum = sum + arr[i];
			r++;
		}
		System.out.print(sum + " ");
		while (r < arr.length) {
			sum = sum - arr[l] + arr[r];
			l++;
			r++;
			System.out.print(sum + " ");
		}
		System.out.println();
	}

	// expand and shrink
	public static void longestSubarraysum() {
		int arr[] = { 2, 5, 3, 6, 9, 8, 1, 2, 5 };
		int targetSum = 17;
		int l = 0;
		int r = 0;
		int sum = 0;
		int max = 0;
		while (r < arr.length) {
			sum = sum + arr[r];
//			while (sum > targetSum) {
//				sum = sum - arr[l];
//				l++;
//			}
			if (sum > targetSum) {
				sum = sum - arr[l];
				l++;
			}
			if (sum <= targetSum) {//instead of shrinking multiple levels, just keep the window
				//size same just move the window, because we already have the max length so why to shrink.
				max = Math.max(max, (r - l + 1));
			}

			r++;
		}
		System.out.println(max);
	}

}
