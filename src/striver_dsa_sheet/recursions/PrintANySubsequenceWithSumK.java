package striver_dsa_sheet.recursions;

import java.util.ArrayList;
import java.util.List;

public class PrintANySubsequenceWithSumK {

	public static void main(String[] args) {
		int arr[] = { 1, 2, 2, -3, 3, 1 };
		int k = 3;
		pritnASubSequenceWithSumK(arr, k, 0, 0, new ArrayList());
	}

	private static boolean pritnASubSequenceWithSumK(int arr[], int k, int sum, int index, List<Integer> list) {

		if (index >= arr.length) {
			if (sum == k) {
				System.out.println(list);
				System.out.println(sum);
				return true;
			} else {
				return false;
			}
		}
		boolean flag = false;

		sum += arr[index];
		list.add(arr[index]);
		flag = pritnASubSequenceWithSumK(arr, k, sum, index + 1, list);
		if (flag) {
			return flag;
		}

		sum -= arr[index];
		list.remove(list.size() - 1);
		flag = pritnASubSequenceWithSumK(arr, k, sum, index + 1, list);
		if (flag) {
			return flag;
		}
		return flag;
	}

}
