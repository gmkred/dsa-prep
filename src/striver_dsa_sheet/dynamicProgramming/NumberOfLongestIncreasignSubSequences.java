package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * arr[] = {1,3,5,4,7}
 * 
 * {1,3,5,7}
 * {1,3,4,7}
 * 2 LIS. 
 *         0  1  2  3  4
 *        {1, 3, 5, 4, 7}
 * dp[] = {1, 2, 3, 3, 4}
 * ct[] = {1, 1, 1, 1, 2}
 * 
 * 
 * at 4th index value 7, j at 2nd index,
 * comapre 1+dp[2] > dp[4] if yes, then updated the dp[4] value with 1+dp[2]
 * 
 *  dp[4] = 1 + dp[2] = 1+3 = 4
 *  ct[4] = 1, still the LIS is 1 at this position
 *  now j at 3rd index.
 *  1 + dp[3] == dp[4]
 *     4 == 4
 *     
 * this means now both are same so dp[4] can make another LIS with dp[3] so
 * add ct[3] to ct[4]
 * 
 * </pre>
 */
public class NumberOfLongestIncreasignSubSequences {
	public static void main(String[] args) {
		int arr[] = { 1, 3, 5, 4, 7 };
		System.out.println(numberofLis(arr));

	}

	private static int numberofLis(int arr[]) {
		int dp[] = new int[arr.length];
		int ct[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			dp[i] = 1;
			ct[i] = 1;
		}
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && 1 + dp[j] > dp[i]) {
					dp[i] = 1 + dp[j];
					ct[i] = ct[j];
					// if prev +! == dp[i] then add ct[j] to ct[i]
				} else if (arr[j] < arr[i] && 1 + dp[j] == dp[i]) {
					ct[i] += ct[j];
				}
			}
			max = Math.max(ct[i], max);
		}
		return max;
	}
}
