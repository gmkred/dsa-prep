package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * How is it different from 0/1 knapsack.
 * 
 * 
 * There is it only one supply for an item, but here its infinite supply of items.
 * 
 * wt {2,4,6}
 * val {5,11,13}
 * 
 * bag capacity = 10
 * 
 * 6+4 = 10
 * val = 11+13 = 24
 * 2+2+2+2+2 =10
 * val = 5+5+5+5 = 20
 * 2+4+4 = 10
 * val = 5+11+11 =27
 * 
 * 
 * Find the maximum value thief can steal.
 * 
 * </pre>
 */
public class UnboundedKnapSack {
	public static void main(String[] args) {
		// item weight - value
		int items[][] = { { 2, 5 }, { 4, 11 }, { 6, 13 } };
		int bagCapacity = 10;
		int index = items.length - 1;
		System.out.println(knapsackRecRepeated(index, bagCapacity, items));
		int dp[][] = new int[items.length][bagCapacity + 1];
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j <= bagCapacity; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(knapsackRecRepeatedDP(index, bagCapacity, items, dp));
		System.out.println(knapsackRecRepeatedDPTabulation(bagCapacity, items));
		System.out.println(knapsackRecRepeatedNoDPTabulation(bagCapacity, items));
	}

	// TC : O(2^N)
	// SC : O(N) auxiliary
	private static int knapsackRecRepeated(int index, int bagCapacity, int items[][]) {
		if (index == 0) {
			if (items[index][0] <= bagCapacity) {
				return items[index][1] * (bagCapacity / items[index][0]);
			} else {
				return 0;
			}
		}
		int in = Integer.MIN_VALUE;
		int ex = 0;
		if (items[index][0] <= bagCapacity) {
			in = items[index][1] + knapsackRecRepeated(index, bagCapacity - items[index][0], items);
		}
		ex = 0 + knapsackRecRepeated(index - 1, bagCapacity, items);
		return Math.max(in, ex);
	}

	// TC : O(N) SC : (N*T) + O(N) auxiliary space
	private static int knapsackRecRepeatedDP(int index, int bagCapacity, int items[][], int dp[][]) {

		if (index == 0) {
			if (items[index][0] <= bagCapacity) {
				dp[index][bagCapacity] = items[index][1] * (bagCapacity / items[index][0]);
				return dp[index][bagCapacity];
			} else {
				dp[index][bagCapacity] = 0;
				return dp[index][bagCapacity];
			}
		}
		if (dp[index][bagCapacity] != -1) {
			return dp[index][bagCapacity];
		}
		int in = Integer.MIN_VALUE;
		int ex = 0;
		if (items[index][0] <= bagCapacity) {
			in = items[index][1] + knapsackRecRepeatedDP(index, bagCapacity - items[index][0], items, dp);
		}
		ex = 0 + knapsackRecRepeatedDP(index - 1, bagCapacity, items, dp);
		dp[index][bagCapacity] = Math.max(in, ex);
		return dp[index][bagCapacity];
	}

	// TC : O(N*T) SC : O(N*T)
	private static int knapsackRecRepeatedDPTabulation(int bagCapacity, int items[][]) {
		int dp[][] = new int[items.length][bagCapacity + 1];
		for (int t = 0; t <= bagCapacity; t++) {
			if (items[0][0] <= t) {
				dp[0][t] = items[0][1] * (t / items[0][0]);
			}
		}
		for (int index = 1; index < items.length; index++) {
			for (int t = 0; t <= bagCapacity; t++) {
				int in = Integer.MIN_VALUE;
				int ex = 0;
				if (items[index][0] <= t) {
					in = items[index][1] + dp[index][t - items[index][0]];
				}
				ex = 0 + dp[index - 1][t];
				dp[index][t] = Math.max(in, ex);
			}
		}
		return dp[items.length - 1][bagCapacity];
	}

//TC : O(N*T) SC : (2*T)
	private static int knapsackRecRepeatedNoDPTabulation(int bagCapacity, int items[][]) {
		int previous[] = new int[bagCapacity + 1];
		for (int t = 0; t <= bagCapacity; t++) {
			if (items[0][0] <= t) {
				previous[t] = items[0][1] * (t / items[0][0]);
			}
		}
		for (int index = 1; index < items.length; index++) {
			int cur[] = new int[bagCapacity + 1];
			for (int t = 0; t <= bagCapacity; t++) {
				int in = Integer.MIN_VALUE;
				int ex = 0;
				if (items[index][0] <= t) {
					in = items[index][1] + cur[t - items[index][0]];
				}
				ex = 0 + previous[t];
				cur[t] = Math.max(in, ex);
			}
			previous = cur;
		}
		return previous[bagCapacity];
	}
}
