package striver_dsa_sheet.algos;

/**
 * <pre>
 * Sorting ascending and descending order
 * 
 * Why Quick Sort over Merge sort?
 * 
 *  Quick sort uses O(1) memory, it uses stack memory.
 * 
 * Algorithm steps
 * Given a array:
 * 1. Pick a pivot. any element in the array.
 * 	1st
 * 	last
 * 	median
 * 	random
 *  * Once we pick the pivot.
 *  * pick that element and 
 *  * place it in it's correct place in the unsorted array.
 *  * Do same till the array is sorted.
 * 2.Make all smaller elements on the left of pivot and larger elements on the right of pivot.
 * 
 * How to code?
 * 
 * pointers 
 * 'low' at starting of array
 * 'high' at ending of array.
 * 'i' at low
 * 'j' at high
 * 
 * Take a pivot, may be 'low'th element.
 * 
 * start comparing pivot with 'i'th element
 * If 'i'th < pivot element. then its in the correct position.
 *     so move 'i' towards right.
 * If 'i'th > pivot, compare 'j'th element with pivot.
 * If  'j'th > pivot, its in correct position,
 *     so move 'j' towards left.
 * If 'j'th  < pivot then swap the 'i'th element with 'j'th
 * 
 * Do same steps until, 'j'th < 'i'th
 * when j < i, that means j is < pivot.
 * Swap the 'pivot with 'j'th element
 * 
 * Now, we will have an array where left to pivot element are smaller
 * and right to pivot elements are larger elements.
 * 
 * Partition = 'j'th which now has pivot element.
 * 
 * so, now we get to partitions
 * next apply same steps on
 * 
 * (low -> partition-1) ==> partition-1 will be high of this array
 * point 'i' to low and 'j' to partition-1
 * (partition+1 -> high) ==> partition+1 will be low of this array
 * point 'i' to partition+1 and 'j' to high
 * 
 *                                                         {4, 6, 2, 5, 7, 9, 1, 3 }
 *  first sort p=arr[0]=4,low=0,high=7  
 *  partition is 'j'                                       {1, 3, 2, 4, 7, 9, 5, 6 }
 *  now divide into two partitions
 *  pivot = 1                                           {1,3,2,4}       {7,9,5,6}        
 *                                                      {1,2,3,4}       {7,6,5,9}
 *                                                    {1,2}
 *                                                   {1} {2}
 * </pre>
 */
public class QuickSort_Algo {

	public static void main(String[] args) {
		int arr[] = { 4, 6, 2, 5, 7, 9, 1, 3, 2 };
//		int arr[] = { 110, 100, 0 };
		quickSort(arr, 0, arr.length - 1);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

	/**
	 * TC : O(N2) = one each stack it traverse through all the elements.
	 * 
	 * SP : O(1) = no extra memory is used.
	 * 
	 */
	public static void quickSort(int arr[], int low, int high) {
		if (low >= high) {
			return;
		}
		int partition = partitonaIndex(arr, low, high);
		quickSort(arr, low, partition - 1);
		quickSort(arr, partition + 1, high);
	}

	private static int partitonaIndex(int arr[], int low, int high) {
		int i = low;
		int j = high;
		int pivot;
		pivot = arr[low];
		int temp = 0;
		while (i < j) {
			// <= pivot will be on left side of pivot
			while (arr[i] <= pivot && i <= high - 1) {// if we go till <=high, the i++ will results
				// out of bound exception
				i++;// find first element which is greater than pivot
			}
			// > pivot will be on right side of pivot
			while (arr[j] > pivot && j >= low + 1) {// if we go till >=low, the j-- will results
				// out of bound exception
				j--;// find first elements which is lesser than pivot.
			}
			if (i < j) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		arr[low] = arr[j];
		arr[j] = pivot;
		return j;
	}
}