package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * Given an array. divide them into subsets and 
 * find the min absolute difference between the sum of the subsets
 * 
 * {1,2,3,4}
 * 
 * |{1+2} - {3+4}| = 4 is absolute diff
 * 
 * |{2+3} - {1+4}| = 0 is absolute diff which is minimum.
 * 
 * {@link SubSequenceOrSubSetEqualsTarget#subSequenceWithSumKDPTabulation(int[], int)}
 * </pre>
 */
public class PartitionASetIntoTwoSubSetsWIthMinAbsSumDifference {

	public static void main(String[] args) {

		int arr[] = { 2, 2, 3, 4 };
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		System.out.println(tabulation(arr, sum));
	}

	private static int tabulation(int arr[], int totalSum) {

		boolean dp[][] = new boolean[arr.length][totalSum + 1];
		// base cases
		for (int i = 0; i < arr.length; i++) {
			dp[i][0] = true;
		}
		// if we reach 0 inex and arr[0] is the target then it is also a consideration.
		dp[0][arr[0]] = true;

		for (int index = 1; index < arr.length; index++) {
			for (int sum = 1; sum <= totalSum; sum++) {
				if (arr[index] <= sum) {

					if (dp[index - 1][sum - arr[index]]) {
						dp[index][sum] = true;
					}

					if (dp[index - 1][sum]) {
						dp[index][sum] = true;
					}
				}
			}
		}
		/**
		 * <pre> 
		 * {@link SubSequenceOrSubSetEqualsTarget#subSequenceWithSumKDPTabulation(int[], int)}
		 * 
		 * To find the minimum absolute difference.
		 * Think each target of the given index as Ti
		 * we need to find (totalSum - Tx) = Ty
		 * 
		 *  minimum diff Math.min(Math.abs((totalSum - Tx),Tx))
		 * </pre>
		 */
		int min = Integer.MAX_VALUE;
		for (int col = 0; col < totalSum + 1; col++) {
			if (dp[dp.length - 1][col]) {
				min = Math.min(Math.abs((totalSum - col) - col), min);
			}
		}
		return min;
	}
}
