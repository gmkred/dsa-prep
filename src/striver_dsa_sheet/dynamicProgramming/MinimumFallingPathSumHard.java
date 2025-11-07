package striver_dsa_sheet.dynamicProgramming;

/**
 * 
 * Use : last colomn in previous row, because we should not take in the current
 * row.
 * 
 */
public class MinimumFallingPathSumHard {
	public static void main(String[] args) {
		int grid[][] = { { -73, 61, 43, -48, -36 }, { 3, 30, 27, 57, 10 }, { 96, -76, 84, 59, -15 },
				{ 5, -49, 76, 31, -7 }, { 97, 91, 61, -46, 67 } };
		System.out.println(tabulation(grid));
		// ans -192
	}

	private static int tabulation(int[][] matrix) {
		int dp[][] = new int[matrix.length][matrix[0].length + 1];
		for (int base = 0; base < matrix[0].length; base++) {
			dp[dp.length - 1][base] = matrix[matrix.length - 1][base];
			if (base == 0) {
				dp[dp.length - 1][dp[0].length - 1] = dp[dp.length - 1][base];
			} else {
				dp[dp.length - 1][dp[0].length - 1] = Math.min(dp[dp.length - 1][dp[0].length - 1],
						dp[dp.length - 1][base]);
			}
		}
		for (int currentRow = matrix.length - 2; currentRow >= 0; currentRow--) {
			for (int currentCol = 0; currentCol < matrix[0].length; currentCol++) {
				int bottomLeftRev = Integer.MAX_VALUE;
				int bottomRightRev = Integer.MAX_VALUE;
				if (currentCol - 1 >= 0) {
					bottomLeftRev = matrix[currentRow][currentCol] + dp[currentRow + 1][currentCol - 1];
				}
				if (currentCol + 1 < matrix[currentRow].length) {
					bottomRightRev = matrix[currentRow][currentCol] + dp[currentRow + 1][currentCol + 1];
				}
				dp[currentRow][currentCol] = Math.min(bottomLeftRev, bottomRightRev);
				if (currentCol == 0) {
					dp[currentRow][dp[currentRow].length - 1] = dp[currentRow][currentCol];
				} else {
					dp[currentRow][dp[currentRow].length - 1] = Math.min(dp[currentRow][dp[currentRow].length - 1],
							dp[currentRow][currentCol]);
				}
			}
		}
		return dp[0][dp[0].length - 1];
	}
}
