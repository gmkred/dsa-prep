package striver_dsa_sheet.algos;

/**
 * <pre>
 * Dutch National Flag Algorithm
 * This algo uses 3 pointers
 * 
 * low, mid and high.
 * This is an hypothetical array
 * 
 * everything from 0 to (low-1) will be 0's
 * everything from low to (mid-1) will be 1's
 * everything from mid to high will be random
 * everything from (high+1) to (n-1) will be 2's
 * 
 * 
 * {0 0 0 0 0    1 1 1 1    2 1 0 2 1 0      2 2 2 2}
 *  |-------|    |-----|    |---------|      |-----|
 *   0to(L-1)    Lto(M-1)    Mto(H-1)      (H+1)to(n-1)
 * 
 * </pre>
 */
public class DutchNationalFlag_Algo {

	public static void main(String[] args) {
		int arr[] = { 2, 1, 2, 0, 1, 0, 2, 0, 0, 1, 2 };
//		sortZeroesOnesTwos(arr);
//		sortZeroesOnesTwos_better(arr);
		sortZeroesOnesTwos_3pointers(arr);

		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

	/**
	 * <pre>
	 * Think the 0 to low-1 is all zeroes.
	 * int low = 0;
	 * Think that from mid to high-1 is all random
	 * int mid = arr.length / 2;
	 * Think that from high+1 to n-1 is all twos
	 * int high = arr.length - 1;
	 * 
	 * Start iterating from mid, if mid is 0, swap with low and increase low++.
	 * if mid is 1, just move forward because bed beginning will be 1.
	 * if mid is 2, then swap with high, and decrease the high--
	 * 
	 * TC : O(N) each iteration takes one swap at most so it will be O(N)
	 * SC : O(1)
	 * </pre>
	 */
	private static void sortZeroesOnesTwos_3pointers(int arr[]) {
		int low = 0;
		int mid = 0;
		int high = arr.length - 1;
		// go till high, becasue high is shrinking and it may have other than 2 so going
		// till high will swap that value to its correct place
		while (mid <= high) {
			if (arr[mid] == 0) {
				int temp = arr[low];
				arr[low] = arr[mid];
				arr[mid] = temp;
				low++;
				// when we are swapping with low, it will be always 1, so increment mid++,
				mid++;
			} else if (arr[mid] == 1) {
				mid++;
			} else if (arr[mid] == 2) {
				int temp = arr[high];
				arr[high] = arr[mid];
				arr[mid] = temp;
				high--;
			}
		}
	}

}
