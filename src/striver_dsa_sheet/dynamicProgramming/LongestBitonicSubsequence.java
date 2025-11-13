package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * 
 * arr[] = {1,11,2,10,4,5,2,1}
 * 
 * increasing and tehn decreasing
 * 
 * 1,2,4,5,2,1
 * 
 * 1,11,10,5,2,1
 * 1,11,10,4,2,1
 * 
 * Bitonc can be just increasing or just be decreasing
 * 
 * Longes increasing or longest decreasing can also be Bitoni.
 * 
 * 1. Longest increasing
 * 2. Longest decreasing
 * 3. Both.
 * 
 * Use 2 dp tables one for increasing and one for decreasing.
 * 
 * and make the bitonic dp from dp1 and dp 2
 *           {1, 11, 2, 10, 4, 5, 2, 1}
 * dp1[]   = {1, 2,  2, 3,  3, 4, 2  1} increasing left to rigth
 * dp2[]   = {1, 5,  2, 4,  3, 3, 2, 1} increasing right to left
 * bitonic = {1, 6,  3, 6,  5, 6, 3, 1}
 * 
 * longest bitoni is 6
 * </pre>
 */
public class LongestBitonicSubsequence {
	public static void main(String[] args) {
		int arr[] = { 1, 11, 2, 10, 4, 5, 2, 1 };
		System.out.println(lbs(arr));
	}

	private static int lbs(int arr[]) {

		int dp1[] = new int[arr.length];
		int dp2[] = new int[arr.length];
		int bitonic[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			dp1[i] = 1;
			dp2[i] = 1;
		}

		// left to right increasing subsequence
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && dp1[j] + 1 > dp1[i]) {
					dp1[i] = 1 + dp1[j];
				}
			}
		}

		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j] < arr[i] && dp2[j] + 1 > dp2[i]) {
					dp2[i] = 1 + dp2[j];
				}
			}
		}
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			// -1 is for not considering the common element
			bitonic[i] = dp1[i] + dp2[i] - 1;
			max = Math.max(max, bitonic[i]);
		}
		return max;
	}
}
