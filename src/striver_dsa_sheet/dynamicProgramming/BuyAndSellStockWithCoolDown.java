package striver_dsa_sheet.dynamicProgramming;

/**
 * Infinite transactions but when a transaction made, the next day is cooling
 * period no buy should happen.
 * 
 */
public class BuyAndSellStockWithCoolDown {
	public static void main(String[] args) {
		int prices[] = { 1, 2, 3, 0, 2 };
		System.out.println(rec(prices, 0, 1));
		int dp[][] = new int[prices.length][2];
		for (int i = 0; i < prices.length; i++) {
			for (int j = 0; j < 2; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(recDp(prices, 0, 1, dp));
	}

	private static int rec(int prices[], int index, int buy) {
		if (index >= prices.length) {
			return 0;
		}
		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;

		if (buy == 1) {
			int bought = -prices[index] + rec(prices, index + 1, 0);
			int notBought = 0 + rec(prices, index + 1, 1);
			max1 = Math.max(bought, notBought);
		} else {
			int sold = prices[index] + rec(prices, index + 2, 1);
			int notSold = 0 + rec(prices, index + 1, 0);
			max2 = Math.max(sold, notSold);
		}
		return Math.max(max1, max2);
	}

	private static int recDp(int prices[], int index, int buy, int dp[][]) {
		if (index >= prices.length) {
			return 0;
		}
		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		if (dp[index][buy] != -1) {
			return dp[index][buy];
		}
		if (buy == 1) {
			int bought = -prices[index] + recDp(prices, index + 1, 0, dp);
			int notBought = 0 + recDp(prices, index + 1, 1, dp);
			max1 = Math.max(bought, notBought);
		} else {
			int sold = prices[index] + recDp(prices, index + 2, 1, dp);
			int notSold = 0 + recDp(prices, index + 1, 0, dp);
			max2 = Math.max(sold, notSold);
		}
		dp[index][buy] = Math.max(max1, max2);
		return dp[index][buy];
	}

	private int recDpTabulation(int prices[]) {
		// since we can go out of index due to index+2 after selling so have dp declared
		// with length+2
		int dp[][] = new int[prices.length + 2][2];
		for (int index = prices.length - 1; index >= 0; index--) {
			for (int buy = 0; buy < 2; buy++) {
				int max1 = Integer.MIN_VALUE;
				int max2 = Integer.MIN_VALUE;
				if (buy == 1) {
					int bought = -prices[index] + dp[index + 1][0];
					int notBought = 0 + dp[index + 1][1];
					max1 = Math.max(bought, notBought);
					dp[index][buy] = max1;
				} else {
					int sold = Integer.MIN_VALUE;
					sold = prices[index] + dp[index + 2][1];

					int notSold = 0 + dp[index + 1][0];
					max2 = Math.max(sold, notSold);
					dp[index][buy] = max2;
				}
				dp[index][buy] = Math.max(max1, max2);
			}
		}
		return dp[0][1];
	}

	private int recDpTabulationOptimal(int prices[]) {
		int dp[][] = new int[prices.length + 2][2];
		for (int index = prices.length - 1; index >= 0; index--) {
			// omit the loop since it has only 2 value 0 and 1
			int bought = -prices[index] + dp[index + 1][0];
			int notBought = 0 + dp[index + 1][1];
			// directly use 1 for buying
			dp[index][1] = Math.max(bought, notBought);
			int sold = Integer.MIN_VALUE;
			sold = prices[index] + dp[index + 2][1];
			int notSold = 0 + dp[index + 1][0];
			// use 0 for selling
			dp[index][0] = Math.max(sold, notSold);
		}
		return dp[0][1];
	}

}
