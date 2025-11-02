package striver_dsa_sheet.recursions;

import java.util.ArrayList;
import java.util.List;

public class CountSubSequencesWithSumK {
	public static void main(String[] args) {
		int arr[] = { 1, 2, 2, -3, 3, 1 };
		int k = 3;
		System.out.println(pritnASubSequenceWithSumK(arr, k, 0, 0));
	}

	private static int pritnASubSequenceWithSumK(int arr[], int k, int sum, int index) {

		if (index >= arr.length) {
			if (sum == k) {
				return 1;
			} else {
				return 0;
			}
		}
		int count = 0;
		sum += arr[index];
		count += pritnASubSequenceWithSumK(arr, k, sum, index + 1);

		sum -= arr[index];
		count += pritnASubSequenceWithSumK(arr, k, sum, index + 1);
		return count;
	}
}
