package striver_dsa_sheet.recursions;

import java.util.ArrayList;
import java.util.List;

public class PrintSubSequencyWithSumK {

	public static void main(String[] args) {
		int arr[] = { 1, 2, 2, -3, 3, 1 };
		int k = 3;
		printSubSequenceWithSUmK(arr, 0, k, new ArrayList<Integer>(), 0);
	}

	/**
	 * <pre>
	 * If we don't want to print the sub sequence we can ignore list, and just maintain a sum variable.
	 * additions and subtractions can be done on the sum, when sum == k, increase the count.
	 * 
	 * 
	 * </pre>
	 */
	private static void printSubSequenceWithSUmK(int arr[], int index, int k, List<Integer> list, int sum) {
		if (index >= arr.length) {
			if (sum == k) {
				System.out.println(list);
			}
			return;
		}
		// include
		list.add(arr[index]);
		sum += arr[index];
		printSubSequenceWithSUmK(arr, index + 1, k, list, sum);
		// exclude
		list.remove(list.size() - 1);
		sum -= arr[index];
		printSubSequenceWithSUmK(arr, index + 1, k, list, sum);
		return;
	}
}
