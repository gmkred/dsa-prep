package striver_dsa_sheet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array, find the longest consecutive numbers in array.
 * 
 */
public class LongestConsecutiveArray_Unsorted {

	public static void main(String[] args) {
		int arr[] = { 100, 4, 200, 1, 3, 2, 203, 205, 204, 202, 201 };
		int arr2[] = { 100, 4, 200, 1, 3, 2 };
		System.out.println(longestConsecutiveArray(arr));
		System.out.println(longestConsecutiveArray(arr2));

		int arr3[] = { 100, 102, 100, 101, 101, 4, 3, 3, 2, 2, 3, 1, 1, 2, 1, 2, 1, 5 };
		System.out.println(longestCOnsecutiveArray_better(arr3));
		System.out.println(longestConsecutiveArray(arr3));

	}

	/**
	 * <pre>
	 * Only under some constraints.
	 * Use Set, and at any element check if it has ele-1 present in set, if yes, then dont start looking for next element.
	 * this will save some time instead look for next elements only if the current element can be the beginning of the sequence.
	 * 
	 * TC : O(N) + O(2N)
	 * SC: O(N)
	 * </pre>
	 */
	private static int longestConsecutiveArray_optimal(int arr[]) {
		int max = 1;
		int count = 1;
		Set<Integer> set = new HashSet<>();
		for (int i : arr) {
			set.add(i);
		}
		for (int i = 0; i < arr.length; i++) {
			// if there is arr[i]-1, they skip this as we again have to start from there and
			// cover those consecutive's.
			if (!set.contains(arr[i] - 1)) {
				int x = arr[i];
				while (set.contains(x + 1)) {
					count++;
					x = x + 1;
				}
				max = Math.max(max, count);
				count = 1;
			}
		}

		return max;
	}

	/**
	 * <pre>
	 * Sort the array and find the consecutive numbers like arr[i]+1 == arr[i+1] If
	 * it breaks start counting from that particular element.
	 * 
	 * merge sort.
	 * TC : O(N log N) + O(N)
	 * SC : O(N) recursive auxiliary memory when sorting.
	 * 
	 * 
	 * 
	 * </pre>
	 */
	private static int longestCOnsecutiveArray_better(int arr[]) {

		// sort the array
		Arrays.sort(arr);
		int count = 0;
		int lastEle = 0;
		int max = 1;

		for (int i = 0; i < arr.length; i++) {
			if (lastEle + 1 == arr[i]) {
				lastEle = arr[i];
				count++;
			} else if (arr[i] == lastEle) {
				continue;
			} else {
				lastEle = arr[i];
				count = 1;
			}
			max = Math.max(max, count);
		}
		return max;
	}

	/**
	 * <pre>
	 * 
	 * TC : O(N^2)
	 * SC :O(1)
	 * 
	 * </pre>
	 */
	private static int longestConsecutiveArray(int arr[]) {
		int max = 1;
		int count = 1;
		int n = arr[0];
		for (int i = 0; i < arr.length; i++) {
			n = arr[i];
			count = 1;
			while (findElement(arr, n + 1) == true) {
				count++;
				n += 1;
			}
			max = Math.max(max, count);
		}
		return max;
	}

	private static boolean findElement(int arr[], int ele) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ele) {
				return true;
			}
		}
		return false;
	}
}
