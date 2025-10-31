package striver_dsa_sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * {{1,3},{2,6},{8,9},{9,11},{8,10},{2,4},{15,18},{16,17}}
 * 
 * 
 * Find the minimum number of intervals can be formed from the above intervals
 * 
 * 
 * {{1,6},{8,11},{15,18}}
 * 
 * {1,3},{2,4},{2,6}   = {1,6}
 * {8,9},{8,10},{9,11} = {8,11}
 * {15,18},{16,17}     = {15,18}
 * </pre>
 */
public class MergingOverlappingIntervalsInArrays {
	public static void main(String[] args) {
		int arr[][] = { { 1, 3 }, { 2, 6 }, { 8, 9 }, { 9, 11 }, { 8, 10 }, { 2, 4 }, { 15, 18 }, { 16, 17 } };
//		System.out.println(mergeIntervals(arr));
		System.out.println(mergeIntervals_sorted(arr));

	}

	/**
	 * <pre>
	 * TC : O(N*log N) for sorting + O(N) for linear traversal
	 * SC :  O(N) worst case for result.
	 * </pre>
	 */
	private static List<List<Integer>> mergeIntervals_sorted(int arr[][]) {
		List<List<Integer>> list = new ArrayList<>();
		mergeSort(arr, 0, arr.length - 1);
		List<Integer> l = null;
		for (int j = 0; j < arr.length; j++) {
			if (j > 0 && arr[j - 1][1] >= arr[j][0]) {
				l.set(1, Math.max(l.get(1), arr[j][1]));
			} else {
				l = new ArrayList<>();
				l.add(arr[j][0]);
				l.add(arr[j][1]);
				list.add(l);
			}
		}
		return list;

	}

	private static void mergeSort(int arr[][], int low, int high) {

		if (low >= high) {
			return;
		}
		int mid = (low + high) / 2;

		mergeSort(arr, low, mid);
		mergeSort(arr, mid + 1, high);
		merge(arr, low, mid, high);

	}

	private static void merge(int arr[][], int low, int mid, int high) {
		int left = low;
		int right = mid + 1;
		List<int[]> list = new ArrayList<>();
		while (left <= mid && right <= high) {
			if (arr[left][0] == arr[right][0]) {
				if (arr[left][1] <= arr[right][1]) {
					list.add(arr[left]);
					left++;
				} else {
					list.add(arr[right]);
					right++;
				}
			} else if (arr[left][0] <= arr[right][0]) {
				list.add(arr[left]);
				left++;
			} else {
				list.add(arr[right]);
				right++;
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

	/**
	 * <pre>
	 * 
	 * TC : O(N*(N+1)/2) ~= O(N^2)
	 * SC : O(N) in worst case where there is no merging intervals.
	 * 
	 * 
	 * </pre>
	 */
	private static List<List<Integer>> mergeIntervals(int arr[][]) {
		List<List<Integer>> list = new ArrayList<>();

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) {
				continue;
			}
			int max = arr[i][1];
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] != null && arr[j][0] <= arr[i][1]) {
					if (arr[j][1] > max) {
						max = arr[j][1];
					}
					arr[i][1] = max;
					arr[j] = null;
				}
			}
		}
		for (

		int[] i : arr) {
			if (i != null) {
				List<Integer> l = new ArrayList<>();
				l.add(i[0]);
				l.add(i[1]);
				list.add(l);
			}
		}
		return list;
	}
}
