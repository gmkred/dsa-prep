package striver_dsa_sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 
 * 
 * AN inversion in as array means i < j, arr[i] > arr[j]
 * 
 * {5, 3, 2, 1, 4}
 * 
 * {5,3}, {5,2}, {5,1}, {5,4}
 * {3,2}, {3,1}
 * {2,1}
 * 
 * 
 * </pre>
 */
public class CountInversionsInAnArray {
//	static int count = 0;

	public static void main(String[] args) {
		int arr[] = { 5, 3, 2, 1, 4 };
		System.out.println(countInversionsInAnArray_optimal(arr));
		int arr2[] = { 40, 25, 19, 12, 9, 6, 2 };
		System.out.println(countInversionsInAnArray_optimal(arr2));
//		System.out.println(count);
	}

	/**
	 * <pre>
	 * Observation:
	 * {5, 3, 2, 1, 4}
	 * 
	 * if we see first 2 indexes of array and compare them to find a count.
	 * 
	 * 5 , 3, this is a count 5 > 3
	 * count =1
	 * next compare {5,3} and {2}
	 * 
	 * 5 > 2,  +1
	 * 3 > 2,  +1
	 * count = 3
	 * 
	 * next compare {5,3,2} and {1}
	 * 5 > 1, +1
	 * 3 > 1, +1
	 * 2 > 1, +1
	 * count = 6
	 * 
	 * next compare {5,3,2,1} and {4}
	 *  5 > 4, +1
	 *  count = 7
	 * 
	 * when we divide array into two parts and compare left side with right side and
	 * count if left[i] > right[j]
	 * 
	 * 
	 * The observation tells that we can perform a merge sort and at each
	 * level check if left[i] > right[j] then we can easily find count of
	 * inversions, i.e. when left[i] > right[j], all the numbers after i in left array
	 * are greater than right[j] so the count at this level would be
	 * 
	 * (mid - i)+1;
	 * mid is the end of the left array.
	 * {5, 3, 2, 1, 4}
	 *     {5,3,2}                  {1,4}
	 *   {5,3}   {2}               {1} {4}
	 * {5} {3}
	 * 5 > 3
	 * count = (mid-i)+1 = 1
	 * 
	 * {3,5}      {2}
	 * 3 > 2
	 * count = (mid - i)+1 = 2 
	 * 
	 * {2,3,5}                    
	 *                             1 < 4 so not valid
	 *                             sort and merge
	 *                             {1,4}
	 * {2,3,5}                     {1,4}
	 * 
	 * 2 > 1
	 * count = mid-i+1 = 3
	 * move j, j++
	 * 2 > 4
	 * i++
	 * 3 > 4
	 * i++
	 * 5 > 4 
	 * count = mid - i +1 = 1
	 * 
	 * end
	 * total count = 1+2+3+1
	 * 
	 * 
	 * TC : O(N log N)
	 * SC : O(N) Auxiliary space of recursive stacks.
	 * 
	 * </pre>
	 */
	private static int countInversionsInAnArray_optimal(int arr[]) {
		int count = mergeSort(arr, 0, arr.length - 1);
		return count;
	}

	private static int mergeSort(int arr[], int low, int high) {
		if (low >= high) {
			return 0;
		}
		int count = 0;
		int mid = (low + high) / 2;
		count += mergeSort(arr, low, mid);
		count += mergeSort(arr, mid + 1, high);
		count += merge(arr, low, mid, high);
		return count;
	}

	private static int merge(int arr[], int low, int mid, int high) {
		int count = 0;
		List<Integer> list = new ArrayList();
		int left = low;
		int right = mid + 1;

		while (left <= mid && right <= high) {
			if (arr[left] > arr[right]) {
				count += (mid - left + 1);
				list.add(arr[right]);
				right++;
			} else {
				list.add(arr[left]);
				left++;
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
		return count;
	}

	/**
	 * <pre>
	 * do extreme for loop to find count inversions
	 * </pre>
	 */
	private static int countInversionsInAnArray_brute(int arr[]) {

		return 0;
	}
}
