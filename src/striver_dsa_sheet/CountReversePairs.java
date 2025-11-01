package striver_dsa_sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Reverse pairs are
 * Similar to Count inversion but the condition here is
 * i < j and arr[i] > 2 arr[j]
 * 
 * 
 * We use the merge sort to divide and while merging we will count the reverse pairs
 * if we take 2 sorted arrays
 * 
 * {12, 19, 25, 40}		{2,6,9}
 * 
 * 12 > 2*2 true and > 2*6 false
 * 19 > 2*2 true and > 2*6 true  and > 2*9 true
 * 25 > 2*2 true and > 2*6 true  and > 2*9 true
 * 40 > 2*2 true and > 2*6 true  and > 2*9 true
 * 
 * 
 * the observation here is,
 * 
 * if 12 > 2 then all the values after 12 in left array are greater than 2*2
 * so, we do not have to loop from the beginning of right array for every left array element.
 * 
 * So, firstly take 12, and loop through right array till 12 > 2*arr[right]. right++
 * When 2*arr[right] > 12, then perform (right - (mid+1) + 1) this will give the count
 * of element which are 12 > 2 * arr[right]
 * 
 * 12 > 2*2  count = 1
 * 
 * for the next element in left, 19, loop till 19 > 2*arr[right]. right++
 * since right is not started again from the beginning, there will be less number of loops.
 * 
 * 19 > 2*6 and > 2*9 count = 2
 * 
 * Now, right is > high, that means, right array is completed.
 * But we still have 25 and 40 left in the left array.
 * This means 25 and 40 are > 2*arr[lastRightElement]
 * 
 * so for each 25 and 40, the count is calculated a (right -(mid+1)+1) which will give the
 * number of elelments on right array.
 * 25, count = 3
 * 40, count = 3
 * 
 * total count = 1+2+3+3 = 9
 * 
 * </pre>
 * 
 */
public class CountReversePairs {

	public static void main(String[] args) {
		int arr2[] = { 40, 25, 19, 12, 9, 6, 2 };
		System.out.println(countReversePairsInAnArray_optimal(arr2));

	}

	private static int countReversePairsInAnArray_optimal(int arr[]) {
		int count = mergeSort(arr, 0, arr.length - 1);
		return count;
	}

	private static int mergeSort(int arr[], int low, int high) {
		if (low >= high) {
			return 0;
		}
		int count = 0;
		int mid = (low + high) / 2;
		count += mergeSort(arr, low, mid);
		count += mergeSort(arr, mid + 1, high);
		count += countReversePairs(arr, low, mid, high);
		merge(arr, low, mid, high);
		return count;
	}

	private static int countReversePairs(int[] arr, int low, int mid, int high) {
		int count = 0;
		int right = mid + 1;
		for (int left = low; left <= mid; left++) {
			while (right <= high && arr[left] > 2 * arr[right]) {
				right++;
			}
			count += (right - (mid + 1));
		}
		return count;
	}

	private static void merge(int arr[], int low, int mid, int high) {
		List<Integer> list = new ArrayList();
		int left = low;
		int right = mid + 1;

		while (left <= mid && right <= high) {
			if (arr[left] > arr[right]) {
				list.add(arr[right]);
				right++;
			} else {
				list.add(arr[left]);
				left++;
			}
		}
		while (left <= mid) {
			list.add(arr[left]);
			left++;

		}
		while (right <= high) {
			list.add(arr[right]);
			right++;
		}
		for (int i = low; i <= high; i++) {
			arr[i] = list.get(i - low);
		}
	}
}
