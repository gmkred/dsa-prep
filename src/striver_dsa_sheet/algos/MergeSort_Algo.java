package striver_dsa_sheet.algos;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Merge sort follows Divid and merge concept
 * {3,1,2,4,1,5,2,6,4}
 * the array is divided into 2 parts and those 2 parts are again
 * divided into 2 parts till there will be only one element 
 * available in the hypothetical array.
 * 
 * middle = (l+r)/2
 * 
 * {3,1,2,4,1}    {5,2,6,4}
 * {3,1,2} {4,1}   {5,2} {6,4}
 * {3,1} {2} {4} {1} {5} {2} {6} {4}
 * {3} {1}
 * 
 * Once we get 2 individual single element arrays, 
 * start merge them in a sorted way.
 * Do it recursively. 
 * 
 * we don't break the array, we play around with indexes.
 * 
 * Take low and high for hypothetically broken array.
 * 
 * 
 * TC : 
 * N= array size.
 * At every step arr is getting divided by 2, then its (log (base 2) N)
 * Number of steps = log 2 N
 * 
 * if arr size is 16
 * 
 *               16
 *        8              8        step1 merge TC N steps
 *    4      4        4     4     step2 merge TC N/2 steps
 *  2   2   2  2    2  2  2  2    step3 merge TC N/4 stpes
 *  11  11  11 11   11 11 11 11   step4 merge TC N/8 steps
 * 
 * to get individual array, it takes 4 steps of divides
 * so, 4 steps = log (base 2) 16
 * 
 * 
 * TC : 
 * 
 * O(log N)
 * 
 * And merge function
 * On each level it sorts the array so it will be of N corresponding to each level
 *  
 * O(N * log N) 
 * By this
 * </pre>
 * 
 */
public class MergeSort_Algo {

	public static void main(String[] args) {
		int arr[] = { 2, 5, 1, 4, 3, 5, 9, 5, 2, 6, 4, 8, 2, 4, 7, 3 };
		traverseArray(arr);
		divide(arr, 0, arr.length - 1);
		traverseArray(arr);
	}

	public static void divide(int arr[], int low, int high) {
		if (low >= high) {
			return;
		}
		int mid = (low + high) / 2;
		divide(arr, low, mid);
		divide(arr, mid + 1, high);
		merge(arr, low, mid, high);

	}

	/**
	 * 
	 * 
	 * */
	public static void merge(int arr[], int low, int mid, int high) {
		List<Integer> list = new ArrayList<>();
		int l = low;
		int r = mid + 1;
		while (l <= mid && r <= high) {
			if (arr[l] <= arr[r]) {
				list.add(arr[l]);
				l++;
			} else {
				list.add(arr[r]);
				r++;
			}
		}
		while (l <= mid) {
			list.add(arr[l]);
			l++;
		}
		while (r <= high) {
			list.add(arr[r]);
			r++;
		}

		for (int i = low; i <= high; i++) {
			arr[i] = list.get(i - low);
		}

	}

	public static void mergeInMemory(int arr[], int low, int mid, int high) {
		List<Integer> list = new ArrayList<>();
		int l = low;
		int r = mid + 1;
		while (l <= mid && r <= high) {
			if (arr[l] <= arr[r]) {
				list.add(arr[l]);
				l++;
			} else {
				list.add(arr[r]);
				r++;
			}
		}
		while (l <= mid) {
			list.add(arr[l]);
			l++;
		}
		while (r <= high) {
			list.add(arr[r]);
			r++;
		}

		for (int i = low; i <= high; i++) {
			arr[i] = list.get(i - low);
		}

	}

	public static void traverseArray(int arr[]) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
