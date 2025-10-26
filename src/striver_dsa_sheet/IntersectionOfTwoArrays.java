package striver_dsa_sheet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * <pre>
 * a = 1 2 3 4 5 5 6 6 7 
 * b = 1 3 4 5 6 6 7 8 
 * intersection 
 * 1,3,4,5,6,6,7
 * 
 * </pre>
 * 
 */
public class IntersectionOfTwoArrays {
	public static void main(String[] args) {
		int arr1[] = { 1, 2, 3, 4, 5, 5, 6, 6, 7 };
		int arr2[] = { 1, 1, 4, 5, 6, 6, 7, 8 };
//		List<Integer> result = intersection(arr1, arr2);
		List<Integer> result = intersection_optimal(arr1, arr2);
		System.out.println(result);

	}

	/**
	 * <pre>
	 * TC : O(N1+N2) worst case if no match found.
	 * SC : O(N1 or N2)
	 * 
	 * </pre>
	 */
	private static List<Integer> intersection_optimal(int arr1[], int arr2[]) {
		List<Integer> intrsec = new ArrayList<>();
		int i = 0;
		int j = 0;
		while (i < arr1.length && j < arr2.length) {

			if (arr1[i] == arr2[j]) {
				intrsec.add(arr1[i]);
				i++;
				j++;
			} else if (arr1[i] < arr2[j]) {
				i++;
			} else {
				j++;
			}
		}
		return intrsec;
	}

	/**
	 * <pre>
	 * TC : O(N1 * N2)
	 * SC : O(N2) + O(N1 or N2)
	 * 
	 * </pre>
	 */
	private static List<Integer> intersection(int arr1[], int arr2[]) {
		List<Integer> intersection = new ArrayList<>();
		int visited2[] = new int[arr2.length];

		for (int i = 0; i < arr1.length; i++) {
			int a = arr1[i];
			for (int j = 0; j < arr2.length; j++) {
				if (a == arr2[j] && visited2[j] == 0) {
					intersection.add(a);
					visited2[j]++;
					// once a match is found break it. else arr1[i] can find another match then it
					// will add to list, which is wrong. there should be duplicates if both arrays
					// contains duplicates
					break;
				}
				// if j crosses i, that means we cannot find same elements in both array so
				// break it
				if (arr2[j] > a) {
					break;
				}
			}
		}
		return intersection;
	}
}
