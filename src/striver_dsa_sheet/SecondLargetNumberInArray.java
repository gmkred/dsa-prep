package striver_dsa_sheet;

public class SecondLargetNumberInArray {

	public static void main(String[] args) {
		int arr[] = { 5, 3, 1, 4, 7, 8, 2, 3, 6, 9 };
		System.out.println(secondLargestNumber(arr));
		System.out.println(secondLargestNumber_better(arr));
		System.out.println(secondLargestNumber_sort(arr));
	}

	/**
	 * <pre>
	 * TC : O(N) 
	 * 
	 * SC : O(1)
	 * </pre>
	 */
	private static int secondLargestNumber_better(int arr[]) {
		int max = Integer.MIN_VALUE;
		int secMax = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				secMax = max;// update secMax when there is new max.
				max = arr[i];
			} else if (arr[i] < max && arr[i] > secMax) {
				secMax = arr[i];
			}
		}

		return secMax;

	}

	/**
	 * <pre>
	 * Find the first max, and then find the second max
	 * TC : o(2N)
	 * SC : O(1)
	 * </pre>
	 */
	private static int secondLargestNumber(int arr[]) {
		int max = Integer.MIN_VALUE;
		int secMax = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < max && arr[i] > secMax) {
				secMax = arr[i];
			}
		}
		return secMax;
	}

	/**
	 * <pre>
	 * TC : O(N^2)
	 * SC : O(1)
	 * </pre>
	 */
	private static int secondLargestNumber_sort(int arr[]) {

		// bubble sort
		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {// we are doing j+1 so going till i-1 or < i will be correct.
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}

		return arr[arr.length - 2];
	}
}
