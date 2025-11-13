package striver_dsa_sheet.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 
 * { 1, 7, 8, 4, 5, 6, -1, 9 }
 * 
 * {1,7,8,9} =4
 * 
 * {1,4,5,6,9} =5
 *  
 * {-1,9} = 2
 * 
 * Instead of generating all sub sequences. try to override existing sub sequence
 * 
 * Initially the sub sequence looks like
 * {1,7,8
 * when we get 4 replace it at correct plave by replacing 7
 * 
 * {1,4,8
 * next we get 5, so replace it at 8
 * {1,4,5
 * next we get 6, just add it becasue its >5
 * {1,4,5,6
 * 
 * next we get -1 replace it with 1
 * {-1,4,5,6
 * 
 * next we get 9, add it at the end
 * 
 * {-1,4,5,6,9}
 * 
 * this is not the correct sub sequence but, it gives the length of LIS.
 * 
 * We replace the values using binary search.
 * </pre>
 */
public class LongestIncreasingSubsequenceUsingBinarySearch {
	public static void main(String[] args) {
		int arr[] = { 1, 7, 8, 4, 5, 6, -1, 9 };
	}

	private static int bs(int arr[]) {
		int max = Integer.MIN_VALUE;
		List<Integer> list = new ArrayList<>();
		list.add(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > arr[i - 1]) {
				list.add(arr[i]);
			} else {
				replace(list, arr[i], 0, list.size() - 1);
			}
		}
		return max;
	}

	private static void replace(List<Integer> list, int value, int low, int high) {
		if (high <= low) {
			return;
		}

		int mid = (low + high) / 2;
		if (list.get(mid) == value) {
			return;
		} else if (list.get(mid) > value) {

		}

	}
}
