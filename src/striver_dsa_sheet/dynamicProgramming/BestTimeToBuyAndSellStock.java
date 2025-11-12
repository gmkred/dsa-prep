package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * BEst tiem to buy and sell stock
 *          1 2 3 4 5 6
 * arr[] = {7,1,5,3,6,4}
 * 
 * to get max profit buy on 2nd day and sell it on sth day
 * max profit is 5.
 * 
 * 
 * Do it reversely, sell it on the high price and buy it on less price.
 * selling on ith day our buying will be on from 1st day till i-1 day
 * </pre>
 */
public class BestTimeToBuyAndSellStock {
	public static void main(String[] args) {
		int arr[] = { 7, 1, 5, 3, 6, 4 };
		System.out.println(maxProfit(arr));
		System.out.println(maxProfitTwoPointer(arr));
	}

	private static int maxProfit(int arr[]) {
		int maxProfit = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int curProfit = 0;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] > arr[i]) {
					curProfit = Math.max(curProfit, arr[j] - arr[i]);
				}
			}
			maxProfit = Math.max(maxProfit, curProfit);
		}
		return maxProfit;
	}

	private static int maxProfitTwoPointer(int arr[]) {
		int maxProfit = Integer.MIN_VALUE;
		int i = 0;
		int j = 1;
		while (j < arr.length) {
			if (arr[j] > arr[i]) {
				maxProfit = Math.max(maxProfit, arr[j] - arr[i]);
				j++;
			} else {
				i = j;
				j++;
			}
		}
		return maxProfit;
	}
}
