package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * 
 * given a n*n matrix, find the minimum sun starting from any of the first row columns.
 * 
 * [2, 1, 3]
 * [6, 5, 4]
 * [7, 8, 9]
 * 
 * the min sum can be 
 * 1+5+7 = 13
 * or 
 * 1+4+8 = 13 both paths sum is 13 and that is the min path sum in this matrix.
 * 
 * </pre>
 */
public class MinimumFallingPathSum {
	public static void main(String[] args) {
		int matrix[][] = { { 2, 1, 3 }, { 6, 5, 4 }, { 7, 8, 9 } };
		System.out.println(minFallingPathSum(matrix));
	}

	public static int minFallingPathSum(int[][] matrix) {
//		return loop(matrix);
		return tabulation(matrix);
	}

	private static int loop(int[][] matrix) {
		int min = Integer.MAX_VALUE;
		for (int col = 0; col < matrix[0].length; col++) {
			// looping through first row all columns. because problem states find min sum
			// from starting at any col of 0th row
			int curMin = rec(matrix, 0, col);
			min = Math.min(min, curMin);
		}
		return min;
	}

	private static int rec(int[][] matrix, int currentRow, int currentCol) {
		if (currentRow == matrix.length - 1) {
			return matrix[currentRow][currentCol];
		}
		int min = Integer.MAX_VALUE;
		// bottom
		int bottomLeft = Integer.MAX_VALUE;
		int bottomRight = Integer.MAX_VALUE;
		int bottom = rec(matrix, currentRow + 1, currentCol) + matrix[currentRow][currentCol];
		// bottom-left
		if (currentCol - 1 >= 0) {
			bottomLeft = rec(matrix, currentRow + 1, currentCol - 1) + matrix[currentRow][currentCol];
		}
		// bottom-right
		if (currentCol + 1 < matrix[currentRow].length) {
			bottomRight = rec(matrix, currentRow + 1, currentCol + 1) + matrix[currentRow][currentCol];
		}
		// min of all 3 side when added to current matrix[row][col]
		min = Math.min(bottom, Math.min(bottomLeft, bottomRight));
		return min;
	}

	// dp
	private int loopDP(int[][] matrix) {
		int dp[][] = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = -200;
			}
		}
		int min = Integer.MAX_VALUE;
		for (int col = 0; col < matrix[0].length; col++) {
			int curMin = recDP(matrix, 0, col, dp);
			min = Math.min(min, curMin);
		}
		return min;
	}

	private static int recDP(int[][] matrix, int currentRow, int currentCol, int[][] dp) {
		if (currentRow == matrix.length - 1) {
			dp[currentRow][currentCol] = matrix[currentRow][currentCol];
			return dp[currentRow][currentCol];
		}
		if (dp[currentRow][currentCol] != -200) {
			return dp[currentRow][currentCol];
		}
		// bottom
		int bottomLeft = Integer.MAX_VALUE;
		int bottomRight = Integer.MAX_VALUE;
		int bottom = recDP(matrix, currentRow + 1, currentCol, dp) + matrix[currentRow][currentCol];

		// bottom-left
		if (currentCol - 1 >= 0) {
			bottomLeft = recDP(matrix, currentRow + 1, currentCol - 1, dp) + matrix[currentRow][currentCol];
		}
		// bottom-right
		if (currentCol + 1 < matrix[currentRow].length) {
			bottomRight = recDP(matrix, currentRow + 1, currentCol + 1, dp) + matrix[currentRow][currentCol];
		}
		dp[currentRow][currentCol] = Math.min(bottom, Math.min(bottomLeft, bottomRight));
		return dp[currentRow][currentCol];
	}

	// tabulation
	private static int tabulation(int[][] matrix) {
		/**
		 * <pre>
		 * In this type of problems have extra index at the end of each row,
		 * because we need minimum from all columns of first row. at the end of the row we try to 
		 * store the minimum sum at that particular row last index.
		 * so at last we can jsut return dp[0][dp[0].length-1]
		 * </pre>
		 */
		int dp[][] = new int[matrix.length][matrix[0].length + 1];
		int min = Integer.MAX_VALUE;
		// base conditions. last four values in this case becasue we are falling down.
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
				// ---
				// bottom
				int bottomLeftRev = Integer.MAX_VALUE;
				int bottomRightRev = Integer.MAX_VALUE;
				int bottomRev = matrix[currentRow][currentCol] + dp[currentRow + 1][currentCol];

				// bottom-left
				if (currentCol - 1 >= 0) {
					bottomLeftRev = matrix[currentRow][currentCol] + dp[currentRow + 1][currentCol - 1];
				}
				// bottom-right
				if (currentCol + 1 < matrix[currentRow].length) {
					bottomRightRev = matrix[currentRow][currentCol] + dp[currentRow + 1][currentCol + 1];
				}
				dp[currentRow][currentCol] = Math.min(bottomRev, Math.min(bottomLeftRev, bottomRightRev));
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
