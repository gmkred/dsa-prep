package arrays;

public class Kadanes_bestTimeToSell {

	public static void main(String[] args) {
		int arr[] = { 7, 1, 5, 3, 6, 4 };
		int result = maxProfiBook_kadanesAlgo(arr);
		System.out.println(result);
	}

	/**
	 * in this solution kadanes algo is used to track the minimum buy value to get max profit.
	 * */
	public static int maxProfiBook_kadanesAlgo(int arr[]) {
		int profit = 0;
		int buyValue = arr[0];
		for (int i = 0; i < arr.length; i++) {
			buyValue = Math.min(buyValue, arr[i]);// take the min buy value to get more profit.
			// find the current profit.
			int curProfit = arr[i] - buyValue;
			// if current profit is higher than existing profit then update the profit
			profit = Math.max(profit, curProfit);
		}
		return profit;
	}
}
