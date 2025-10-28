package striver_dsa_sheet;

public class BestTimeToBuyAndSell {

	public static void main(String[] args) {
		int arr[] = { 7, 2, 5, 3, 10, 4 };
		System.out.println(bestTimeToBuyAndSell(arr));
		System.out.println(bestTimeToBuyAndSell_optimal(arr));
	}

	private static int bestTimeToBuyAndSell_optimal(int arr[]) {
		int maxProfit = Integer.MIN_VALUE;
		int buyIndex = 0;

		for (int i = 1; i < arr.length; i++) {
			// update buy index when there is low value.
			if (arr[i] < arr[buyIndex]) {
				buyIndex = i;
				continue;
			}
			maxProfit = Math.max(maxProfit, arr[i] - arr[buyIndex]);
		}
		return maxProfit;

	}

	/**
	 * <pre>
	 * 
	 * TC : ~O(N^2)
	 * SC : O(N)
	 * </pre>
	 */
	private static int bestTimeToBuyAndSell(int arr[]) {
		int maxProfit = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int profit = 0;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] > arr[i]) {
					profit = arr[j] - arr[i];
					maxProfit = Math.max(maxProfit, profit);
				}
			}
		}
		return maxProfit;
	}
}
