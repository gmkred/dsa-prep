package striver_dsa_sheet;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 
 * 2 Varieties fo two sums.
 * 1. Is array has 2 elements whose sum is target.
 * 2. Return the elements whose sum is target.
 * 
 * 
 * Better approach is optimal for 2nd variety.
 * Optimal approach is only for 1st variety.
 * 
 * 
 * </pre>
 */
public class TwoSum {

	public static void main(String[] args) {
		int arr[] = { 2, 6, 5, 8, 11 };
		int target = 14;
		System.out.println(twoSum(arr, target));
		System.out.println(twoSum_better(arr, target));
		System.out.println(twoSum_Optimal(arr, target));
	}

	/**
	 * <pre>
	 * Sort the array and put 2 points at extreme ends, and check if arr[i]+arr[j] == target
	 * If sum < target, move i to right,
	 * If sum > target, move j to left ,until j<=i
	 * 
	 * Tc : O(N^2) for quick sort
	 * or use Merge sort for O(N log N)
	 * and O(N) for traversing
	 * SC : O(1)
	 * 
	 * </pre>
	 */
	private static boolean twoSum_Optimal(int arr[], int target) {
		quickSort(arr, 0, arr.length - 1);
		int i = 0;
		int j = arr.length - 1;

		while (i < j) {
			if (arr[i] + arr[j] < target) {
				i++;
			} else if (arr[i] + arr[j] > target) {
				j--;
			} else {
				return true;
			}

		}
		return false;
	}

	private static void quickSort(int arr[], int low, int high) {
		if (low >= high) {
			return;
		}
		int partition = findPartition(arr, low, high);
		quickSort(arr, low, partition - 1);
		quickSort(arr, partition + 1, high);
	}

	private static int findPartition(int arr[], int low, int high) {
		if (low == high) {
			return arr[low];
		}
		int partition = 0;
		int pivot = arr[low];
		int i = 0;
		int j = high;
		int temp = -1;
		while (i < j) {
			while (arr[i] <= pivot && i < arr.length) {
				i++;
			}
			while (arr[j] > pivot && j < arr.length) {
				j--;
			}
			if (i < j) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		arr[low] = arr[j];
		arr[j] = pivot;
		partition = j;

		return partition;
	}

	/**
	 * <pre>
	 * TC : O(N*log N)
	 * SC : O(N)
	 * </pre>
	 */
	private static boolean twoSum_better(int arr[], int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(target - arr[i])) {
				return true;
			}
			map.put(arr[i], i);
		}
		return false;
	}

	/**
	 * <pre>
	 * find the 2 numbers which sum == target
	 * TC : ~O(N^2)
	 * SC : O(N)
	 * </pre>
	 */
	private static boolean twoSum(int arr[], int target) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] + arr[j] == target) {
					return true;
				}
			}
		}
		return false;
	}
}
