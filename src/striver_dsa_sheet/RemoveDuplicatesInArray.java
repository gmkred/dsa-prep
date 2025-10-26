package striver_dsa_sheet;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesInArray {
	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 3, 4, 5, 5, 6, 7, 7 };
//		remeoveDuplicates(arr);
		removeDuplicates_optimal(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

	/**
	 * <pre>
	 * Use 2 pointer.
	 * 
	 * Each iteration check if arr[i] == arr[j] and move the j pointer till arr[i] != arr[j]
	 * and put arr[j] value in arr[i+1]
	 * </pre>
	 */
	private static void removeDuplicates_optimal(int arr[]) {

		int i = 0;
		int j = i + 1;

		while (j < arr.length) {
			if (arr[i] == arr[j]) {
				j++;
			} else {
				i++;
				arr[i] = arr[j];
			}
		}

	}

	/**
	 * In memory manipulation Using Set
	 */
	private static void remeoveDuplicates(int arr[]) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			set.add(arr[i]);
		}
		int indx = 0;
		for (int i : set) {
			arr[indx] = i;
			indx++;
		}
	}
}
