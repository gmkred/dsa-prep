package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * given the lengths or costs or heights array
 * {10, 20, 30, 10}
 * 
 * A frog needs to reach from bottom of the stairs to top
 * It can jump only 1 or 2 steps at a time.
 * when ever it jumps from one place to another,
 * it either covers some distance or costs some points.
 * 
 * if it jumped from 1st step to 2nd step then the length it covered will be Absolute of (2nd index - 1st index)
 * 
 * 
 * So find the min or max length, or height or cost to reach from bottom to top or start to end or top to bottom
 * 
 * </pre>
 */
public class FrogJump {
	public static void main(String[] args) {

		int heights[] = { 10, 20, 30, 10 };
		System.out.println(forgJumpRec(heights, heights.length - 1));
		int dp[] = new int[heights.length + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = -1;
		}
//		System.out.println(frogJumpMemoization(heights, heights.length - 1, dp));
		System.out.println(frogJumpTabulationFromMemoization(heights, heights.length - 1, dp));
		System.out.println(frogJumpTabulationFromMemoization_optimal(heights, heights.length - 1));
	}

	private static int forgJumpRec(int heights[], int index) {
		if (index == 0) {
			return 0;
		}
		int oneStep = 0;
		// we need to use MAX value, because if array length is only 2, so
		// two steps cannot happen, and if its initialized with 0, then that can be
		// taken as min value which will be not correct.
		int twoSteps = Integer.MAX_VALUE;

		/**
		 * Firstly, it goes till 1st index, from there the computations will happen form
		 * the 1st index, from there it will add the min value for the next computation,
		 * it continues it the n-1 index
		 */
		oneStep = forgJumpRec(heights, index - 1) + Math.abs(heights[index] - heights[index - 1]);

		// for step to only if index > 1 we need to preceed
		if (index > 1) {
			twoSteps = forgJumpRec(heights, index - 2) + Math.abs(heights[index] - heights[index - 2]);
		}
		// on each index find the min height.
		return Math.min(oneStep, twoSteps);
	}

	private static int frogJumpMemoization(int heights[], int index, int dp[]) {
		if (index == 0) {
			return 0;
		}

		if (dp[index] != -1) {
			return dp[index];
		}

		int oneStep = 0;
		int twoSteps = Integer.MAX_VALUE;

		oneStep = frogJumpMemoization(heights, index - 1, dp) + Math.abs(heights[index] - heights[index - 1]);

		if (index > 1) {
			twoSteps = frogJumpMemoization(heights, index - 2, dp) + Math.abs(heights[index] - heights[index - 2]);
		}

		dp[index] = Math.min(oneStep, twoSteps);
		return dp[index];

	}

	private static int frogJumpTabulationFromMemoization(int[] heights, int index, int dp[]) {
		// base condition is converted to dp[0]
		dp[0] = 0;
		int oneStep = 0;
		int twoSteps = Integer.MAX_VALUE;
		for (int i = 1; i <= index; i++) {
			oneStep = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
			if (i > 2) {
				twoSteps = dp[i - 2] + Math.abs(heights[i] - heights[i - 2]);
			}
			dp[i] = Math.min(oneStep, twoSteps);
		}
		return dp[index];

	}

	private static int frogJumpTabulationFromMemoization_optimal(int[] heights, int index) {
		int prev1 = 0;
		int prev2 = 0;
		int curMin = 0;
		int oneStep = 0;
		int twoSteps = Integer.MAX_VALUE;
		for (int i = 1; i <= index; i++) {
			oneStep = prev1 + Math.abs(heights[i] - heights[i - 1]);
			if (i > 2) {
				twoSteps = prev2 + Math.abs(heights[i] - heights[i - 2]);
			}
			curMin = Math.min(twoSteps, oneStep);
			prev2 = prev1;
			prev1 = curMin;
		}
		return curMin;
	}
}
