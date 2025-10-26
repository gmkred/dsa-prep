package striver_dsa_sheet;

import java.util.Iterator;
import java.util.TreeSet;

public class UnionAndIntersectionArrays {
	public static void main(String[] args) {
		int arr1[] = { 1, 2, 3, 3, 4, 5, 6, 7, 8 };
		int arr2[] = { 1, 2, 4, 5, 6, 7, 7 };
//		int res[] = unionOfTwoArrays(arr1, arr2);
		int res[] = unionOfTwoArrays_optimal(arr1, arr2);
		for (int i : res) {
			System.out.print(i + " ");
		}
	}

	/**
	 * <pre>
	 * 
	 * TC : O(2N)
	 * SC : O(N)
	 * 
	 * 
	 * 
	 * </pre>
	 */
	private static int[] unionOfTwoArrays_optimal(int arr1[], int arr2[]) {
		int i = 0;
		int j = 0;
		int[] union = new int[(arr1.length + arr2.length)];
		int k = 0;
		while (i < arr1.length && j < arr2.length) {
			// if i <= j lets take i
			if (arr1[i] <= arr2[j]) {
				// before taking i, check if previous union is not equal to arr1[i]
				if (k == 0 || union[k - 1] != arr1[i]) {
					union[k] = arr1[i];
					k++;
				}
				i++;
			} else {
				/**
				 * if j < i then take arr2[j], but beofre taking j, check if arr2[j] is not
				 * equal to previous union value
				 */
				if (k == 0 || union[k - 1] != arr2[j]) {
					union[k] = arr2[j];
					k++;
				}
				j++;
			}
		}
		while (i < arr1.length) {
			if (arr1[i] != union[k - 1]) {
				union[k] = arr1[i];
				k++;
			}
			i++;
		}
		while (j < arr2.length) {
			if (arr2[j] != union[k - 1]) {
				union[k] = arr1[j];
				k++;
			}
			j++;
		}
		return union;

	}

	/**
	 * <pre>
	 * TC : O(N1*log N1 +N2*log N2 + (N1+N2))
	 * SC :
	 * O(N1+N2) size of set
	 * O(N1+N2) to return the array which is required as per problem statement.
	 * 
	 * </pre>
	 */
	private static int[] unionOfTwoArrays(int arr1[], int arr2[]) {
		int res[] = null;
		TreeSet<Integer> set = new TreeSet();
		for (int i : arr1) {
			set.add(i);
		}
		for (int i : arr2) {
			set.add(i);
		}
		Iterator<Integer> itr = set.iterator();
		res = new int[set.size()];
		int i = 0;
		while (itr.hasNext()) {
			res[i] = itr.next();
			i++;
		}

		return res;
	}
}
