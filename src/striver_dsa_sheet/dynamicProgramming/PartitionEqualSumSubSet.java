package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * Find if subsets can have equal sum when partitioned into 2.
 * 
 * {2,3,3,3,4,5}
 * 
 * {2,3,5} = 10 == {3,3,4} =10
 * </pre>
 * 
 */
public class PartitionEqualSumSubSet {
	public static void main(String[] args) {
		int arr[] = new int[] { 2, 3, 3, 3, 4, 5 };
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		System.out.println(isPartitonEqual(arr, arr.length - 1, sum, 0));
	}

	private static boolean isPartitonEqual(int arr[], int index, int totalSum, int sum) {
		if (index < 0) {
			if ((double) totalSum / 2 == sum) {
				return true;
			} else {
				return false;
			}
		}
		// include
		sum += arr[index];
		if (isPartitonEqual(arr, index - 1, totalSum, sum))
			return true;
		// exclude
		sum -= arr[index];
		if (isPartitonEqual(arr, index - 1, totalSum, sum)) {
			return true;
		}
		return false;
	}
}
