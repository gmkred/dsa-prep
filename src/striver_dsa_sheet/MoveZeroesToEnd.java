package striver_dsa_sheet;

public class MoveZeroesToEnd {
	public static void main(String[] args) {
		int arr[] = { 0, 1, 0, 3, 12 };
//		moveZeroesTOEnd(arr);
		moveZerosToEnd_Optimal(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

	/**
	 * <pre>
	 * TC :
	 *  O(x) - finding first zero
	 *  O(N-X) - for the rest of the array.
	 *  so TC : O(N)
	 *  SC : O(1)
	 * </pre>
	 */
	private static void moveZeroesToEnd_Striver(int nums[]) {
		int k = -1;// for zero
		// find first zero index
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				k = i;
				break;
			}
		}
		// in no zero in array then return.
		if (k == -1) {
			return;
		}
		/**
		 * find the non zero an swap with k index.
		 * 
		 * Once swapped, move k by one place. K should be be fixed until i is non-zero,
		 * so we can swap all the zeroes with non zeroes
		 */
		for (int i = k + 1; i < nums.length; i++) {
			int temp = -1;
			if (nums[i] != 0) {
				temp = nums[i];
				nums[i] = nums[k];
				nums[k] = temp;
				k++;
			}
		}
	}

	/**
	 * <pre>
	 * TC : O(N)
	 * SC: O(1)
	 * 
	 * 
	 * </pre>
	 */
	private static void moveZerosToEnd_Optimal(int nums[]) {
		int i = 0;// pointer for zero
		int j = i + 1;// pointer for non-zero
		while (j < nums.length) {// loop till i finds Zero
			while (i < nums.length && nums[i] != 0) {
				i++;
				j++;
			}
			// loop till j finds Non-zero
			while (j < nums.length && nums[j] == 0) {
				j++;
			}
			// swap zero with non - zero
			/**
			 * if {1,2,0,0,0,4,5,6} if condition will swap one zero with non zero. And next
			 * main loop checks if i is at zero and j is at non-zero. so, the above loops
			 * will not execute until i is at non-zero.
			 * 
			 */
			if (j < nums.length) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			}
		}
	}

	/**
	 * <pre>
	 * TC : O(2N)
	 * SC : O(N)
	 * </pre>
	 */
	private static void moveZeroesTOEnd(int nums[]) {
		int i = 0;
		int j = nums.length - 1;
		int arr[] = new int[nums.length];
		for (int k = 0; k < nums.length; k++) {
			if (j < i) {
				break;
			}
			if (nums[k] == 0) {
				j--;
			} else {
				arr[i] = nums[k];
				i++;
			}
		}
		for (int k = 0; k < arr.length; k++) {
			nums[k] = arr[k];
		}
	}
}
