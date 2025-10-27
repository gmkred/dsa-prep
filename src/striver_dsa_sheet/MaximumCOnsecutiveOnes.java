package striver_dsa_sheet;

public class MaximumCOnsecutiveOnes {
	public static void main(String[] args) {
		int arr[] = { 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1 };
		System.out.println(maxConsecutiveOnes(arr));
		System.out.println(maxConsecutiveOnes_optimal(arr));
		System.out.println(maxConsecutiveOnes_striver_Optimal(arr));
	}

	private static int maxConsecutiveOnes_striver_Optimal(int arr[]) {
		int max = 0;
		int counter = 0;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				max = Math.max(max, counter);
				counter = 0;
			} else {
				counter++;
			}
		}

		return max;

	}

	/**
	 * <pre>
	 * Two pointer solution
	 * 
	 * start i and j at 1, move only j and find the max on each iteration.
	 * when arr[j] = 0, then reset the i and j from next 1.
	 * 
	 * TC : O(N)
	 * SC : O(1)
	 * 
	 * </pre>
	 */
	private static int maxConsecutiveOnes_optimal(int arr[]) {

		int max = Integer.MIN_VALUE;
		int i = 0;
		int j = 0;

		while (j < arr.length) {
			if (arr[j] != 1) {
				j = i;
				i++;
				j++;
			} else {
				max = Math.max(max, j - i + 1);
				j++;
			}
		}
		return max;

	}

	/**
	 * <pre>
	 * TC : ~O(N^2)
	 * SC : O(1)
	 * </pre>
	 * 
	 */
	private static int maxConsecutiveOnes(int arr[]) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 1) {
				continue;
			}
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] == 1) {
					max = Math.max(max, j - i + 1);
				} else {
					break;
				}
			}
		}
		return max;
	}
}
