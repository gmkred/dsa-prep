package striver_dsa_sheet;

public class FindNumberAppearOnce {

	public static void main(String[] args) {
		int arr[] = { 1, 1, 2, 2, 3, 4, 4, 5, 5 };
		System.out.println(findNumberAppearOnce(arr));
		System.out.println(findNumberAppearOnce_better(arr));
		System.out.println(findNumberAppearOnce_Optimal_XOR(arr));
	}

	private static int findNumberAppearOnce_Optimal_XOR(int arr[]) {

		int xor = 0;
		for (int i = 0; i < arr.length; i++) {
			xor = xor ^ arr[i];
		}

		return xor;
	}

	/**
	 * <pre>
	 * 
	 * TC : O(N) + O(M)
	 * SC : O(M)
	 * 
	 * </pre>
	 */
	private static int findNumberAppearOnce_better(int arr[]) {
		int hash[] = new int[arr[arr.length - 1] + 1];

		for (int i = 0; i < arr.length; i++) {
			if (hash[arr[i]] == 0) {
				hash[arr[i]]++;
			} else {
				hash[arr[i]]--;
			}
		}
		for (int i = 0; i < hash.length; i++) {
			if (hash[i] == 1) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * <pre>
	 * TC : O(N^2)
	 * SC : O(1)
	 * </pre>
	 * 
	 */
	private static int findNumberAppearOnce(int arr[]) {

		int counter = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					counter++;
				}
			}
			if (counter == 1) {
				return arr[i];
			}
			counter = 0;
		}
		return -1;

	}
}
