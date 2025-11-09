package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * What is knapsack ?
 * N=3, each item has particular weight and a value.
 * weight	3	4	5
 * value	30	50	60
 * Thief bag can only carry weight 8.
 * Maximum value with given weight thief can steal.
 * {4+5} = 9 X weight is not in bags range
 * {50+60} = 110 
 * {3+4} = 7 <=8 Possible
 * {30+50} = 80 this is the maximum value thief can steal.
 * Greedy wont work because it has no uniformity.
 * so we sue recursion:
 * Steps :
 * 1. Express the problem in terms of array. [index, Weight Value]
 * 2. Explore all possibilities. Pick / Not pick.
 * 3. Max(all possibilities)
 * 
 * </pre>
 */
public class KnapSack0and1 {
	public static void main(String[] args) {
		int items[][] = { { 3, 50 }, { 3, 40 }, { 4, 60 } };
		int index = items.length - 1;
		int bagCapacity = 8;
		System.out.println(knapSack(items, bagCapacity, index));
		int dp[][] = new int[items.length][bagCapacity + 1];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(knapsackDP(index, bagCapacity, items, dp));
		System.out.println(knapsackDPTabulation(bagCapacity, items));
		System.out.println(knapsackNoDPTabulation(bagCapacity, items));
	}

	// TC : O(2^N)
	// SC : O(N) auxiliary space
	private static int knapSack(int items[][], int weight, int index) {
		if (index == 0) {
			if (items[index][0] <= weight) {
				return items[0][1];
			} else {
				return 0;
			}
		}
		int include = Integer.MAX_VALUE;
		if (items[index][0] <= weight) {
			// include
			include = items[index][1] + knapSack(items, weight - items[index][0], index - 1);
		}
		// exclude
		int exclude = 0 + knapSack(items, weight, index - 1);

		return Math.max(include, exclude);
	}

	// TC : O(N)
	// SC : O(N*(weight+1)) + Auxiliary space
	private static int knapsackDP(int index, int weight, int items[][], int dp[][]) {
		if (index == 0) {
			if (items[0][0] <= weight) {
				dp[0][weight] = items[0][1];
				return items[0][1];
			} else {
				dp[0][0] = 0;
				return dp[0][weight];
			}
		}
		if (dp[index][weight] != -1) {
			return dp[index][weight];
		}
		int include = Integer.MIN_VALUE;
		if (items[index][0] <= weight) {
			include = items[index][1] + knapsackDP(index - 1, weight - items[index][0], items, dp);
		}

		int exclude = 0 + knapsackDP(index - 1, weight, items, dp);

		dp[index][weight] = Math.max(include, exclude);

		return dp[index][weight];
	}

//TC : O(N*W)
	// SC : O(N*W)
	private static int knapsackDPTabulation(int weight, int items[][]) {
		int dp[][] = new int[items.length][weight + 1];
		// base cases
		for (int w = items[0][0]; w < dp[0].length; w++) {
			dp[0][w] = items[0][1];
		}
		for (int index = 1; index < items.length; index++) {
			for (int w = 0; w <= weight; w++) {
				int include = Integer.MIN_VALUE;
				if (items[index][0] <= w) {
					include = items[index][1] + dp[index - 1][w - items[index][0]];
				}
				int exclude = 0 + dp[index - 1][w];
				dp[index][w] = Math.max(include, exclude);
			}
		}
		return dp[items.length - 1][weight];
	}

	// O(N*W)
	// SC : O(2*W)
	private static int knapsackNoDPTabulation(int weight, int items[][]) {
		int previous[] = new int[weight + 1];
		// base cases
		for (int w = items[0][0]; w < previous.length; w++) {
			previous[w] = items[0][1];
		}
		for (int index = 1; index < items.length; index++) {
			int temp[] = new int[weight + 1];
			for (int w = 0; w <= weight; w++) {
				int include = Integer.MIN_VALUE;
				if (items[index][0] <= w) {
					include = items[index][1] + previous[w - items[index][0]];
				}
				int exclude = 0 + previous[w];
				temp[w] = Math.max(include, exclude);
			}
			previous = temp;
		}
		return previous[weight];
	}
}
