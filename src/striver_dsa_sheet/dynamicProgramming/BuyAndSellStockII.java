package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * prices[] = {7, 1, 5, 3, 6, 4}
 * Buy as many times and also sell as many times
 * 
 * buy at 1 and sell at 5, pro = 4
 * but at 3 and sell at 6, pro = 3
 * 
 * Before performing any buy, previous holding stocks should be sold.
 * </pre>
 */
public class BuyAndSellStockII {
	public static void main(String[] args) {
		int prices[] = { 7, 1, 5, 3, 6, 4 };
		System.out.println(rec(prices, 0, true));
		int dp[][] = new int[prices.length][2];
		for (int i = 0; i <= prices.length - 1; i++) {
			for (int j = 0; j < 2; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(recDp(prices, 0, 1, dp));
		System.out.println(recDpTabulation(prices));
		System.out.println(recNoDpTabulation(prices));
	}

//TC : O(2^N) SC : O(N) auxiliary
	// for eary index we can sell or not sell or buy or not buy.
	private static int rec(int prices[], int index, boolean canBuy) {
		if (index == prices.length) {
			return 0;
		}
		int include = Integer.MIN_VALUE;
		int exclude = 0;
		int buyMax = 0;
		int sellMax = 0;
		if (canBuy) {
			// If option is to buy, we can buy or move to next day to buy.
			// interested to buy
			include = -prices[index] + rec(prices, index + 1, false);
			// not interested to buy
			exclude = 0 + rec(prices, index + 1, true);
			buyMax = Math.max(include, exclude);
		} else {
			// if option is to sell, then we sell or move to next day to sell.
			// interested to sell
			include = prices[index] + rec(prices, index + 1, true);
			// not interested to sell.
			exclude = 0 + rec(prices, index + 1, false);
			sellMax = Math.max(exclude, include);
		}
		return Math.max(sellMax, buyMax);
	}

	// TC : O(N) SC : O(N*2)
	private static int recDp(int prices[], int index, int canBuy, int dp[][]) {
		if (index == prices.length) {
			return 0;
		}
		int include = Integer.MIN_VALUE;
		int exclude = 0;
		int buyMax = 0;
		int sellMax = 0;

		if (dp[index][canBuy] != -1) {
			return dp[index][canBuy];
		}
		if (canBuy == 1) {
			// If option is to buy, we can buy or move to next day to buy.
			// interested to buy
			include = -prices[index] + recDp(prices, index + 1, 0, dp);
			// not interested to buy
			exclude = 0 + recDp(prices, index + 1, 1, dp);
			buyMax = Math.max(include, exclude);

		} else {
			// if option is to sell, then we sell or move to next day to sell.
			// interested to sell
			include = prices[index] + recDp(prices, index + 1, 1, dp);
			// not interested to sell.
			exclude = 0 + recDp(prices, index + 1, 0, dp);
			sellMax = Math.max(exclude, include);
		}
		dp[index][canBuy] = Math.max(sellMax, buyMax);
		return dp[index][canBuy];
	}

	private static int recDpTabulation(int prices[]) {
		int dp[][] = new int[prices.length + 1][2];
		// on first day if sell there is no profit so set 0
		dp[prices.length][0] = 0;
//		// if bought on first day then it can be sold next.
		dp[prices.length][1] = 0;

		for (int index = prices.length - 1; index >= 0; index--) {
			for (int buy = 0; buy < 2; buy++) {
				int buyMax = Integer.MIN_VALUE;
				int sellMax = Integer.MIN_VALUE;
				if (buy == 1) {
					// If option is to buy, we can buy or move to next day to buy.
					// interested to buy
					int bought = -prices[index] + dp[index + 1][0];
					// not interested to buy
					int notBought = 0 + dp[index + 1][1];
					buyMax = Math.max(bought, notBought);
				} else {
					// if option is to sell, then we sell or move to next day to sell.
					// interested to sell
					int sold = prices[index] + dp[index + 1][1];
					// not interested to sell.
					int notSold = 0 + dp[index + 1][0];
					sellMax = Math.max(sold, notSold);
				}
				dp[index][buy] = Math.max(sellMax, buyMax);
			}
		}
		return dp[0][1];
	}

	// TC : O(N*2) SC : O(2*2)
	private static int recNoDpTabulation(int prices[]) {
		int next[] = new int[2];
		for (int index = prices.length - 1; index >= 0; index--) {
			int cur[] = new int[2];
			for (int buy = 0; buy < 2; buy++) {
				int buyMax = Integer.MIN_VALUE;
				int sellMax = Integer.MIN_VALUE;
				if (buy == 1) {
					// If option is to buy, we can buy or move to next day to buy.
					// interested to buy
					int bought = -prices[index] + next[0];
					// not interested to buy
					int notBought = 0 + next[1];
					buyMax = Math.max(bought, notBought);
				} else {
					// if option is to sell, then we sell or move to next day to sell.
					// interested to sell
					int sold = prices[index] + next[1];
					// not interested to sell.
					int notSold = 0 + next[0];
					sellMax = Math.max(sold, notSold);
				}
				cur[buy] = Math.max(sellMax, buyMax);
			}
			next = cur;
		}
		return next[1];
	}


}
