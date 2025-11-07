package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * { 5, 2, 6, 4 }
 * 
 * divide into 2 subsets which should have the diff D
 * 
 * 
 * D = 3
 * 
 * {5+2} - {6+4} = 3
 * 
 * 
 * assume 
 * S1 and S2
 * Total = 5+2+6+4 = 17
 * S1 = 10
 * S2 = 7
 * 
 * to find the difference we need to know either S1 or S2
 * S1 > S2
 * 
 * Total = 17
 * S1 = 10
 * 
 * D = (S1-s2) 
 * 
 * 	S1 + S2 = Total
 * 
 * 	s2 = Total - S1
 * D = S1 - Total + S1
 * D = 2S1 - Total
 * 
 * D + Total = 2S1
 * 
 * S1 = (Total + D)/2
 * 
 * 
 * </pre>
 */
public class CountPartitionsWithGivenKDifference {
	public static void main(String[] args) {
		int arr[] = { 5, 2, 6, 4 };
		int D = 3;
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			total += arr[i];
		}
		int dp[] = new int[arr.length];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = -1;
		}
		int target = (total + D) / 2;
		System.out.println(rec(arr, D, arr.length - 1, target));
		System.out.println(recDP(arr, D, arr.length - 1, target, 0, dp));

	}

//TC : O(2^N) SC : O(N) auxiliary
	private static int rec(int arr[], int D, int index, int target) {
		if (index == 0) {
			if (target == arr[index] || target == 0) {
				return 1;
			} else {

				return 0;
			}
		}
		if (target == 0) {
			return 1;
		}
		int include = 0;
		include += rec(arr, D, index - 1, target - arr[index]);

		int exclude = 0;
		exclude += rec(arr, D, index - 1, target);

		return include + exclude;
	}

	private static int recDP(int arr[], int D, int index, int target, int dp[]) {
		if (index == 0) {
			if (target == 0 || target == arr[index]) {
				return 1;
			} else {
				return 0;
			}
		}
		if (target == 0) {

			return 1;
		}
		int include = 0;
		include += rec(arr, D, index - 1, target - arr[index]);
		int exclude = 0;
		exclude += rec(arr, D, index - 1, target);

		return include + exclude;
	}
}
