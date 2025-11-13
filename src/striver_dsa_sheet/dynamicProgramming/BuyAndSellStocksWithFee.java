package striver_dsa_sheet.dynamicProgramming;

/**
 * There will a fee for a transaction. prices = {1,3,2,8,4,9}, fee = 2
 * 
 */
public class BuyAndSellStocksWithFee {
	public static void main(String[] args) {
		int[] prices = { 1, 3, 2, 8, 4, 9 };
		int fee = 2;
		int dp[][] = new int[prices.length + 1][2];
		for (int i = 0; i <= prices.length; i++) {
			for (int j = 0; j < 2; j++) {
				dp[i][j] = -1;
			}
		}

	}

	private static int rec(int index, int buy, int prices[], int fee) {
		if (index >= prices.length) {
			return 0;
		}
		if (buy == 1) {
			int bought = -prices[index] + rec(index + 1, 0, prices, fee);
			int notBought = 0 + rec(index + 1, 1, prices, fee);
			return Math.max(bought, notBought);
		} else {
			int sold = prices[index] - fee + rec(index + 1, 1, prices, fee);
			int notSold = 0 + rec(index + 1, 0, prices, fee);
			return Math.max(sold, notSold);
		}
	}

	private static int recDp(int index, int buy, int prices[], int fee, int dp[][]) {
		if (index >= prices.length) {
			return 0;
		}
		if (dp[index][buy] != -1) {
			return dp[index][buy];
		}
		if (buy == 1) {
			int bought = -prices[index] + recDp(index + 1, 0, prices, fee, dp);
			int notBought = 0 + recDp(index + 1, 1, prices, fee, dp);
			dp[index][buy] = Math.max(bought, notBought);
			return dp[index][buy];
		} else {
			int sold = prices[index] - fee + recDp(index + 1, 1, prices, fee, dp);
			int notSold = 0 + recDp(index + 1, 0, prices, fee, dp);
			dp[index][buy] = Math.max(sold, notSold);
			return dp[index][buy];
		}
	}

	private static int tabulation(int prices[], int fee) {
		int dp[][] = new int[prices.length + 1][2];
		for (int index = prices.length - 1; index >= 0; index--) {
			int bought = -prices[index] + dp[index + 1][0];
			int notBought = 0 + dp[index + 1][1];
			dp[index][1] = Math.max(bought, notBought);
			int sold = prices[index] - fee + dp[index + 1][1];
			int notSold = 0 + dp[index + 1][0];
			dp[index][0] = Math.max(sold, notSold);
		}
		return dp[0][1];
	}
}
