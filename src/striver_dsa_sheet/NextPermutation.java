package striver_dsa_sheet;

import java.util.Arrays;

import striver_dsa_sheet.algos.AllPermuataion_Optimized;
import striver_dsa_sheet.algos.AllPermutations;

/**
 * <pre>
 * @see
 * 
 *{@link AllPermutations}
 *{@link AllPermuataion_Optimized}
 * </pre>
 */
public class NextPermutation {
	public static void main(String[] args) {
		int arr[] = { 2, 1, 5, 4, 3, 0, 0 };
		int res[] = nextPermutation_optimal(arr);
		for (int i : res) {
			System.out.print(i + " ");
		}
	}

	/**
	 * <pre>
	 * {2,1,5,4,3,0,0}
	 * Observation:
	 * 
	 * Dictionary order
	 * raj
	 * ram
	 * rbx
	 * 
	 * 1. Longer prefix match. Try to get longest prefix possible.
	 *    Find the break point from end.
	 * 2. Find smallest but greater value than the break point and swap break point with that value.
	 * 3. Sort the remaining numbers after the original break point index.
	 * 
	 * 
	 * Dry run:arr = { 1, 4, 5, 1, 3, 0, 0 }
	 * 
	 * 
	 * First find the break point from end. This will make sure the longest prefix still be there with out disturbing it.
	 * Break point : A first smaller number found from right.
	 * arr[3] = 1 
	 * Is the first smallest number found from the right;
	 * Why it works: 
	 * Assume we have 
	 * {1 4 3 2 1}
	 * And we try to randomly swap an element.
	 * {1,4,2,3,1} = this has become smaller value than the given value, but need next permutation.
	 * so if we swap decreasing toward right values, it will alwasy result in smaller values.
	 * 
	 * 	Because, when we have increasing numbers from right and there found a smallest number when compared adjacently.
	 * 	That means, the breakpoint if swapped with the slightly larger number in the right side of breakpoint
	 * 	and reverse the array after the break point will actually results in next permutation.
	 * 
	 * arr[4] = 3 which is slightly larger than 1, so swap breakpoint with it.
	 * {1,4,5,3,1,0,0}
	 * 
	 * After swapping, reverse the array from after breakpoint, because it is in dcreasing order from left
	 * so just make it increasing order which gives the next permutation.
	 * 
	 * 
	 * 
	 * If there is no breakpoint found, that means it is teh last permutation of the arr, so reverse the array to get the next(first permutation).
	 * 
	 * 
	 * TC : O(3N)
	 * SC : O(1)
	 * </pre>
	 */
	private static int[] nextPermutation_optimal(int arr[]) {

		int breakPointIndex = -1;
		int larger = arr.length - 1;

		for (int i = arr.length - 1; i > 0; i--) {
			if (arr[i - 1] < arr[i]) {
				breakPointIndex = i - 1;
				break;
			}
		}
		if (breakPointIndex == -1) {
			reverse(arr, 0, arr.length - 1);
		} else {
			for (int i = arr.length - 1; i > breakPointIndex; i--) {
				// take which ever is first that is greater than breakpoint from right is the
				// slightly larger.
				if (arr[i] > arr[breakPointIndex]) {
					larger = i;
					break;
				}
			}
		}
		int temp = arr[larger];
		arr[larger] = arr[breakPointIndex];
		arr[breakPointIndex] = temp;
		// but instead of sorting, we can reverse because the numbers after break point
		// index are in descending order.
//		Arrays.sort(arr, breakPointIndex + 1, arr.length);
		reverse(arr, breakPointIndex + 1, arr.length - 1);

		return arr;
	}

	private static void reverse(int[] arr, int low, int high) {
		int i = low;
		int j = high;
		while (i < j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
	}
}
