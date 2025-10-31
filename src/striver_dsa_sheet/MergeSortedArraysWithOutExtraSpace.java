package striver_dsa_sheet;

import java.util.Arrays;
import java.util.Iterator;

public class MergeSortedArraysWithOutExtraSpace {

	/**
	 * <pre>
	 * 
	 * {1,3,5,7}	{0,2,4,6}
	 * 
	 * to
	 * {0,1,2,3}	{4,5,6,7}
	 * Without extra space
	 * 
	 * </pre>
	 */
	public static void main(String[] args) {
		int arr1[] = { 1, 3, 5, 7 };
		int arr2[] = { 0, 2, 4, 6, 8 };
//		mergeTwoSortedArraysWIthoutExtraSpace(arr1, arr2);
		mergeTwoSortedListsUsingGapTheorem(arr1, arr2);
		for (int i : arr1) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i : arr2) {
			System.out.print(i + " ");
		}

	}

	private static void swap(int arr1[], int arr2[], int i1, int i2) {
		if (arr1[i1] > arr2[i2]) {
			int temp = arr1[i1];
			arr1[i1] = arr2[i2];
			arr2[i2] = temp;
		}
	}

	/**
	 * <pre>
	 * TC : O(log (M+N)) * O(M+N)
	 * SC : O(1)
	 * 
	 * Use Gap method of shell sorting where it starts from. it is similar to Insertion
	 * sorting but with Longest gap first and decrease the gaps.
	 * <span style = "color:red;">
	 * Shell Sort improves Insertion Sort by reducing the number of shifts required 
	 * for distant elements. Instead of comparing adjacent elements, 
	 * it sorts elements at larger gaps first, progressively reducing the gap.
	 * </span>
	 * Gap = total length / 2
	 * Gap = Gap / 2
	 * 
	 * until Gap >= 1
	 * 
	 * Once gap reaches 1, that should be the last iteration for sorting.
	 * 
	 * On each iteration swap the elements if arr[i] > arr[j] in both array's
	 * With this, the sorting will happen with a larger Gap first and
	 * 
	 * </pre>
	 */
	private static void mergeTwoSortedListsUsingGapTheorem(int arr1[], int arr2[]) {
		int total = arr1.length + arr2.length;
		int gap = (total / 2) + (total % 2);
		int i = 0;
		int j = 0;
		while (gap > 0) {
			i = 0;
			j = i + gap;
			while (j < total) {
				// there are 3 conditions
				// i is in arr1 and j is in arr2
				if (i < arr1.length && j >= arr1.length) {
					swap(arr1, arr2, i, j - arr1.length);
				}
				// i and j are in array2
				else if (i >= arr1.length) {
					swap(arr2, arr2, i - arr1.length, j - arr1.length);
				}
				// i and j are in array1
				else {
					swap(arr1, arr1, i, j);
				}
				i++;
				j++;
			}
			if (gap == 1) {
				break;
			}
			gap = (gap / 2) + (gap % 2);
		}
	}

	/**
	 * <pre>
	 * 
	 * Start comparing last element of first array and first of the second array.
	 * swap it till first array elements are > second elements.
	 *        i      j
	 * {1,3,5,7}	{0,2,4,6} 
	 * 
	 * {1,3,2,0}	{7,5,4,6}
	 * 
	 * Now sort the 2 arrays individually.
	 * 
	 * Why this works:
	 * It works because, we are segregating all greater values on right side and all lower values on left side
	 * 
	 * if M < N
	 * TC : O(M) +O(M Log M) + O(N log N)
	 * SC : O(1)
	 * 
	 * </pre>
	 */
	private static void mergeTwoSortedArraysWIthoutExtraSpace(int arr1[], int arr2[]) {

		int i = arr1.length - 1;
		int j = 0;
		while (arr1[i] >= arr2[j] && i < arr1.length && j >= 0) {
			int temp = arr1[i];
			arr1[i] = arr2[j];
			arr2[j] = temp;
			i--;
			j++;
		}
		Arrays.sort(arr1);
		Arrays.sort(arr2);
	}
}
