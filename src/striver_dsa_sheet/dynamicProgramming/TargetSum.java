package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * 
 * {1,2,3,1}
 * T = 3
 * 
 * Assign signs -ve or +ve to the numbers in array.
 * Count how many ways we can assign signs to get the given target.
 * 
 * -1,+2,+3,-1 = 3
 * +1,-2,+3,+1 = 3
 * 
 * 
 * It is exactly same as finding the number of subsets with difference Target.
 * 
 * -1,+2,+3,-1 = 3
 * {2+3} - {1+1} = 3
 * 
 * +1,-2,+3,+1 = 3
 * {1+3+1} -{2}= 3
 * 
 * 
 * Find total = 7
 * 
 * D = 3
 * 
 * S1+S2 = total
 * 
 * S1-S2 = D
 * 
 * S1+S2-S2 = D+S2
 * Total - S2 = D+S2
 * Total - D = 2 S2
 * S2 = (Total-D)/2
 * 
 * Now the target = (Total-D/)2
 * target = (7-3)/2 = 4/2 = 2
 * Find a subset with sum 2, then count
 * </pre>
 */
public class TargetSum {
	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 1 };
		int T = 3;
		int index = arr.length - 1;

		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[i];
		}
		if (total < 0 || (total - T) % 2 != 0) {
			return;
		}
		int target = (total + T) / 2;
		System.out.println(targetSumRec(index, target, arr));

		int dp[][] = new int[arr.length][total + 1];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j <= total; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(targetSumRecDP(index, target, arr, dp));

	}

	private static int targetSumRec(int index, int target, int arr[]) {
		if (index == 0) {
			if (target == 0 && arr[0] == 0) {
				return 2;
			}
			if (target == 0 || target == arr[0]) {
				return 1;
			} else {
				return 0;
			}
		}
		int include = 0;
		int exclude = 0;
		if (arr[index] <= target) {
			include = targetSumRec(index - 1, target - arr[index], arr);
		}
		exclude = targetSumRec(index - 1, target, arr);
		return include + exclude;
	}

	private static int targetSumRecDP(int index, int target, int arr[], int dp[][]) {
		if (index == 0) {
			if (target == 0 && arr[0] == 0) {
				dp[index][target] = 2;
			}
			if (target == 0 || target == arr[0]) {
				dp[index][target] = 1;
			} else {
				dp[index][target] = 0;
			}
			return dp[index][target];
		}
		if (dp[index][target] != -1) {
			return dp[index][target];
		}
		int in = 0;
		int ex = 0;
		if (arr[index] <= target) {
			in = targetSumRecDP(index - 1, target - arr[index], arr, dp);
		}
		ex = targetSumRecDP(index - 1, target, arr, dp);
		dp[index][target] = in + ex;
		return dp[index][target];
	}
}
