package striver_dsa_sheet;

public class LargestNumberInArray {

	public static void main(String[] args) {
		int arr[] = { 2, 1, 5, 3, 9, 5, 4, 8 };
//		System.out.println(largestNumber(arr));
		System.out.println(largestNumber_better(arr));
	}

	/**
	 * TC : O(N) SC : O(1)
	 */
	private static int largestNumber_better(int arr[]) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}

		return max;

	}

	/**
	 * TC : O(N^2) SC : O(N)
	 */
	private static int largestNumber(int arr[]) {
		// insertion sort.

		for (int i = 0; i < arr.length; i++) {
			int j = i;
			while (j > 0 && arr[j - 1] > arr[j]) {
				// swap
				int temp = arr[j - 1];
				arr[j - 1] = arr[j];
				arr[j] = temp;
				j--;
			}
		}

		return arr[arr.length - 1];
	}
}
