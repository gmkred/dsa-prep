package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * 
 * A subsequence can be a contiguous or non contiguous but follows order.
 * A Subset : might not follow the order.
 * 
 * 
 * 
 * {2,3,5,4,1}
 * 
 * sub sequences :
 * some examples : {2}{2,5} {3,4,1} must be in order.
 * 
 * sub sets : 
 * some examples : {4,5,3} {3} might not be in order.
 * 
 * </pre>
 * 
 */
public class SubSequenceOrSubSetEqualsTarget {
	public static void main(String[] args) {
		int arr[] = { 3, 2, 1, 5, 3, 2 };
		int k = 6;
//		System.out.println(subSequenceWithSumK(arr, k, arr.length - 1));
		// index , target
		// because both changes
		// -1 initial, 1 false
		int dp[][] = new int[arr.length][k + 1];
		for (int i = 0; i < dp.length; i++) {
			// always take j as highest as compared to any of the element in the array, so
			// it can fit target from 0 to k at any index.
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(subSequenceWithSumK(arr, k, arr.length - 1));

		System.out.println(subSequenceWithSumKDP(arr, k, arr.length - 1, dp));
		System.out.println(subSequenceWithSumKDPTabulation(arr, k));
		System.out.println(subSequenceWithSumKNoDPTabulation(arr, k));

	}

	private static boolean subSequenceWithSumK(int arr[], int t, int index) {

		if (t == 0) {
			return true;
		}
		if (index == 0) {
			return t == arr[0];
		}
		boolean flag = false;

		// include
		if (arr[index] <= t) {
			if (subSequenceWithSumK(arr, t - arr[index], index - 1)) {
				return true;
			}
		}
		// exclude
		if (subSequenceWithSumK(arr, t, index - 1)) {
			return true;
		}
		return flag;
	}

	// int arr[] = { 8, 3, 4, 2, 1 };
	// int k = 5;
	private static boolean subSequenceWithSumKDP(int arr[], int t, int index, int dp[][]) {
		if (t == 0) {
			return true;
		}
		if (index == 0) {
			if (t == arr[0]) {
				return true;
			}
			dp[index][t] = 1;
		}
		boolean flag = false;
		if (dp[index][t] != -1) {
			return false;
		}
		// include
		if (arr[index] <= t) {
			if (subSequenceWithSumKDP(arr, t - arr[index], index - 1, dp)) {
				return true;
			}
		}
		// exclude
		if (subSequenceWithSumKDP(arr, t, index - 1, dp)) {
			return true;
		}
		dp[index][t] = 1;
		return flag;
	}

	/**
	 * <pre>
	 *   0  1  2  3  4  5 
	 * { 3, 2, 1, 5, 3, 2 };
	 * target = 6
	 * target's     0     1       2     3      4      5      6
	 * arr[0] = 3 [true, false, false, true, false, false, false], 
	 * arr[1] = 2 [true, false, true,  true, false, true,  false], 
	 * arr[2] = 1 [true, true,  true,  true, true,  true,  true], 
	 * arr[3] = 5 [true, true,  true,  true, true,  true,  true], 
	 * arr[4] = 3 [true, true,  true,  true, true,  true,  true], 
	 * arr[5] = 2 [true, true,  true,  true, true,  true,  true]
	 * 
	 * Why this works : 
	 * It works because we are trying to find the subsets with sum 0 to 6.
	 * Lets see how :
	 * 
	 * Note: Either include or exclude is true then entire target at a particular index is true.
	 * 
	 * we have {3} as 0th index value since its the 0th index mark t0 and t3 as possibilities at this index.
	 *  
	 * we have {2} as 1st index value:
	 * 
	 * How many targets can be true with {3,2} : 
	 * target t2, if we remove 2 from t2, (t2-2) =0 (marked in0th index),  if we exclude it, then it will 0 and 0 is marked in 0th index.
	 * target t3, if we not remove from t3, it will be as 3 (marked in 0th index).
	 * target t5, if we remove 2 from t5, {t5-2) = 3 (marked in 0th index):
	 *
	 * 
	 * This also tells for a given array, if arr[5] has truw for any target, that means that target can be achieved.
	 * 
	 * Note : We can solve a question where it asks to find the targets from 0 to K, that can be found as sum of sub sets from the given array.
	 * 
	 * Since tabulation starts from the beginning, we need to assume our bases cases answers.
	 * 
	 * So base cases are:
	 * consider the DP[arr.length][max number in the array if it is greater than target + 1]
	 * ** +1 is to have 1 based index.
	 * 
	 * 1. if at any index target is 0 then its true.
	 * 		target == 0 dp[anyIndex][0] = true so make all the indexes 0th index as true
	 * 2. At 0th index, if we get a target that is equivalent to the arr[0] then it is true.
	 *    Assume that dp[0][arr[0]] = true;
	 *    
	 * Now steps:
	 * Think that, we get a target at an index, we need to check if that (target-arr[i]) is present in the previous index
	 * if present, that means if we send (target-arr[i]) to previous index it will result in 0 then we can update
	 * dp[i][target] = true.
	 * 
	 * or 
	 * 
	 * we can check not using the current arr[i] like not reducing it from target and send the target as it is at the current index.
	 * If, the value is present in the previous index, then it can also be resulted in 0, and we can mark
	 * 
	 * dp[i][target] = true;
	 * 
	 * since we dont know which target will be at a particular index, we need to try out all the 
	 * value from [0 to target].
	 * 
	 * Each target at a particular index should be included and excluded and this way we can find if there is a sub set sum = target.
	 * 
	 * TC : O((N-1)*(K+1))
	 * SC : O(N*(K+1))
	 * 
	 * </pre>
	 */
	public static boolean subSequenceWithSumKDPTabulation(int arr[], int t) {
		boolean dp[][] = new boolean[arr.length][t + 1];
		// base cases.
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = true;
		}
		dp[0][arr[0]] = true;
		for (int index = 1; index < arr.length; index++) {
			for (int target = 1; target <= t; target++) {
				if (arr[index] <= target) {
					if (dp[index - 1][target - arr[index]]) {
						dp[index][target] = true;
					}
				}
				// think that each target is a target that got to "index" and we we do not want
				// take current index value, just ignore it.
				// if want to take remove the current index value from target, that how we can
				// move towards 0.
				if (dp[index - 1][target]) {
					dp[index][target] = true;
				}
			}
		}
		return dp[arr.length - 1][t];
	}

	// TC : O(N-1 * K+1) SC : O(2 (K+1))
	public static boolean subSequenceWithSumKNoDPTabulation(int arr[], int t) {
		// we need 2 1D arrays original and temp;
		boolean original[] = new boolean[t + 1];
		// base cases.

		original[0] = true;
		original[arr[0]] = true;
		for (int index = 1; index < arr.length; index++) {
			boolean temp[] = new boolean[t + 1];
			temp[0] = true;
			for (int target = 1; target <= t; target++) {
				boolean flagRemove = false;
				if (arr[index] <= target) {
					if (original[target - arr[index]]) {
						flagRemove = true;
					}
				}
				boolean flagNoRemove = original[target];
				temp[target] = flagRemove || flagNoRemove;
			}
			original = temp;
		}
		return original[t];
	}
}
