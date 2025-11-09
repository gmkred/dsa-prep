package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * 
 * Given N = 5,
 * N is the rod length.
 * For every length given price
 *  1 2 3 4 5
 * {2,5,7,8,10}
 * 
 * cut the rod in to pieces.
 * for length 1 the price in the market is 2
 * for 2 its 5
 * for 2 its 7
 * 
 * if we cut 5 length rod into 5 pieces of 1 length
 * the price we get is 10
 * 
 * if we cut rod into 1 piece of 1 length and 2 pieces of 2 length
 * the price we will get is 2+5+5 = 12
 * 
 * To get the maximum price how many pieces with what lengths?
 * 
 * 
 * </pre>
 * 
 */
public class RodCuttingProblem {
	public static void main(String[] args) {
		int prices[] = { 2, 5, 7, 8, 10 };
		// length
		int N = 5;
		int index = prices.length - 1;
		System.out.println(rodCuttingRec(index, N, prices));
		int dp[][] = new int[prices.length][N + 1];
		for (int i = 0; i < prices.length; i++) {
			for (int j = 0; j <= 5; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(rodCuttingRecDP(index, N, prices, dp));
		System.out.println(rodCuttingDPTbulation(N, prices));
		System.out.println(rodCuttingNoDPTbulation(N, prices));
	}

	// assume 1 based indexing
	// TC : > O(2^N) exponential because we are staying at same index.
	// SC : O(N) auxiliary
	private static int rodCuttingRec(int index, int len, int prices[]) {
		if (index == 0) {
			return len * prices[0];
		}
		int in = Integer.MIN_VALUE;
		int ex = 0;
		int rodLen = index + 1;
		if (rodLen <= len) {
			in = prices[index] + rodCuttingRec(index, len - rodLen, prices);
		}
		ex = 0 + rodCuttingRec(index - 1, len, prices);
		return Math.max(in, ex);
	}

	private static int rodCuttingRecDP(int index, int len, int prices[], int dp[][]) {
		if (index == 0) {
			dp[0][len] = len * prices[0];
			return dp[0][len];
		}
		if (dp[index][len] != -1) {
			return dp[index][len];
		}
		int in = Integer.MIN_VALUE;
		int ex = 0;
		int rodLen = index + 1;
		if (rodLen <= len) {
			in = prices[index] + rodCuttingRecDP(index, len - rodLen, prices, dp);
		}
		ex = 0 + rodCuttingRecDP(index - 1, len, prices, dp);
		dp[index][len] = Math.max(in, ex);
		return dp[index][len];
	}

	private static int rodCuttingDPTbulation(int len, int prices[]) {
		int dp[][] = new int[prices.length][len + 1];
		for (int l = 0; l <= len; l++) {
			dp[0][l] = l * prices[0];
		}
		for (int index = 1; index < prices.length; index++) {
			for (int l = 0; l <= len; l++) {
				int in = Integer.MIN_VALUE;
				int ex = 0;
				int rodLen = index + 1;
				if (rodLen <= l) {
					in = prices[index] + dp[index][l - rodLen];
				}
				ex = 0 + dp[index - 1][l];
				dp[index][l] = Math.max(in, ex);
			}
		}
		return dp[prices.length - 1][len];
	}

	private static int rodCuttingNoDPTbulation(int len, int prices[]) {
		int previous[] = new int[len + 1];
		for (int l = 0; l <= len; l++) {
			previous[l] = l * prices[0];
		}
		for (int index = 1; index < prices.length; index++) {
			int cur[] = new int[len + 1];
			for (int l = 0; l <= len; l++) {
				int in = Integer.MIN_VALUE;
				int ex = 0;
				int rodLen = index + 1;
				if (rodLen <= l) {
					in = prices[index] + cur[l - rodLen];
				}
				ex = 0 + previous[l];
				cur[l] = Math.max(in, ex);
			}
			previous = cur;
		}
		return previous[len];
	}
}
