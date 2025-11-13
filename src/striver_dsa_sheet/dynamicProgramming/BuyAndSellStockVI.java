package striver_dsa_sheet.dynamicProgramming;

/**
 * similar to III where max transactions can be 2 but K or the number of
 * transaction can vary
 * 
 * {@link BuyAndSellStockIII}
 */
public class BuyAndSellStockVI {

	public static void main(String[] args) {
		int k = 2;
		int prices[] = { 3, 2, 6, 5, 0, 3 };
		System.out.println(rec(prices, 0, true, k));
		int dp[][][] = new int[prices.length][2][3];
		for (int i = 0; i < prices.length; i++) {
			for (int j = 0; j < 2; j++) {
				for (int cap = 0; cap < k + 1; cap++) {
					dp[i][j][cap] = -1;
				}
			}
		}
		System.out.println(recDP(prices, 0, 1, k, dp));
		System.out.println(tabulation(prices, k));
		System.out.println(noDptabulation(prices, k));
	}

	private static int rec(int prices[], int index, boolean canBuy, int cap) {
		if (cap == 0) {// 2 transactions completed so return back.
			return 0;
		}
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
			include = -prices[index] + rec(prices, index + 1, false, cap);
			// not interested to buy
			exclude = 0 + rec(prices, index + 1, true, cap);
			buyMax = Math.max(include, exclude);
		} else {
			// if option is to sell, then we sell or move to next day to sell.
			// interested to sell
			// A transaction end only after selling the stock, so reduce cap when stock is
			// sold.
			include = prices[index] + rec(prices, index + 1, true, cap - 1);
			// not interested to sell.
			exclude = 0 + rec(prices, index + 1, false, cap);
			sellMax = Math.max(exclude, include);
		}
		return Math.max(sellMax, buyMax);
	}

	private static int recDP(int prices[], int index, int canBuy, int cap, int dp[][][]) {

		if (cap == 0) {// 2 transactions completed so return back.
			return 0;
		}

		if (index == prices.length) {
			return 0;
		}
		int buyMax = 0;
		int sellMax = 0;
		if (dp[index][canBuy][cap] != -1) {
			return dp[index][canBuy][cap];
		}
		if (canBuy == 1) {
			// If option is to buy, we can buy or move to next day to buy.
			// interested to buy
			int bought = -prices[index] + recDP(prices, index + 1, 0, cap, dp);
			// not interested to buy
			int notBought = 0 + recDP(prices, index + 1, 1, cap, dp);
			buyMax = Math.max(bought, notBought);
		} else {
			// if option is to sell, then we sell or move to next day to sell.
			// interested to sell
			int sold = prices[index] + recDP(prices, index + 1, 1, cap - 1, dp);
			// not interested to sell.
			int notSold = 0 + recDP(prices, index + 1, 0, cap, dp);
			sellMax = Math.max(sold, notSold);
		}
		dp[index][canBuy][cap] = Math.max(sellMax, buyMax);
		return dp[index][canBuy][cap];
	}

//TC : O(N*2*3) SC : O(N*2*3)
	private static int tabulation(int prices[], int k) {
		int dp[][][] = new int[prices.length + 1][2][k + 1];
		// if(cap == 0){//2 transactions completed so return back.
		// return 0;
		// }
		// if (index == prices.length) {
		// return 0;
		// }

		for (int index = prices.length - 1; index >= 0; index--) {
			for (int buy = 0; buy < 2; buy++) {
				for (int cap = 1; cap <= k; cap++) {
					int buyMax = 0;
					int sellMax = 0;
					if (buy == 1) {
						// If option is to buy, we can buy or move to next day to buy.
						// interested to buy
						int bought = -prices[index] + dp[index + 1][0][cap];
						// not interested to buy
						int notBought = 0 + dp[index + 1][1][cap];
						buyMax = Math.max(bought, notBought);
					} else {
						// if option is to sell, then we sell or move to next day to sell.
						// interested to sell
						int sold = prices[index] + dp[index + 1][1][cap - 1];
						// not interested to sell.
						int notSold = 0 + dp[index + 1][0][cap];
						sellMax = Math.max(sold, notSold);
					}
					dp[index][buy][cap] = Math.max(sellMax, buyMax);
				}
			}
		}
		return dp[0][1][k];
	}

//TC : O(N*2*3) SC: O(2*(2*3))
	private static int noDptabulation(int prices[], int k) {
		int next[][] = new int[2][k + 1];
		// if(cap == 0){//2 transactions completed so return back.
		// return 0;
		// }
		// if (index == prices.length) {
		// return 0;
		// }

		for (int index = prices.length - 1; index >= 0; index--) {
			int cur[][] = new int[2][3];
			for (int buy = 0; buy < 2; buy++) {
				for (int cap = 1; cap <= k; cap++) {
					int buyMax = 0;
					int sellMax = 0;
					if (buy == 1) {
						// If option is to buy, we can buy or move to next day to buy.
						// interested to buy
						int bought = -prices[index] + next[0][cap];
						// not interested to buy
						int notBought = 0 + next[1][cap];
						buyMax = Math.max(bought, notBought);
					} else {
						// if option is to sell, then we sell or move to next day to sell.
						// interested to sell
						int sold = prices[index] + next[1][cap - 1];
						// not interested to sell.
						int notSold = 0 + next[0][cap];
						sellMax = Math.max(sold, notSold);
					}
					cur[buy][cap] = Math.max(sellMax, buyMax);
				}
			}
			next = cur;
		}
		return next[1][2];
	}

}
