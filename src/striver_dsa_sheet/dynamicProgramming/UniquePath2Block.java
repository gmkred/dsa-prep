package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * 
 * Given a matrix, and -1 as blocker.
 * find the number of paths from the [0,0] to [n-1][m-1]
 * 
 * 
 * </pre>
 */
public class UniquePath2Block {
	public static void main(String[] args) {
		int m[][] = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };

	}

	private static int rec(int[][] matrix, int row, int col, int[][] dp) {
		if (row == 0 && col == 0) {
			if (matrix[0][0] == 1) {
				return 0;
			}
			return 1;
		} else if (row < 0 || col < 0) {
			return 0;
		} else if (matrix[row][col] == 1) {
			return 0;
		}
		if (dp[row][col] > 0) {
			return dp[row][col];
		}
		int count = 0;
		int right = rec(matrix, row, col - 1, dp);
		count += right;
		int down = rec(matrix, row - 1, col, dp);
		dp[row][col] = down + right;
		count += down;
		return count;
	}

	private static int tabulation(int matrix[][], int row, int col) {
		int dp[][] = new int[matrix.length][matrix[0].length];
		if (matrix[row][col] == 1) {
			return 0;
		}
		for (int i = 0; i <= row; i++) {
			for (int j = 0; j <= col; j++) {
				int up = 0;
				int left = 0;
				if (matrix[i][j] == 1) {
					// so next time if any 
					dp[i][j] = 0;
					continue;
				} else if (i == 0 && j == 0) {
					dp[i][j] = 1;
				} else {
					if (i > 0) {
						up = dp[i - 1][j];
					}
					if (j > 0) {
						left = dp[i][j - 1];
					}
					dp[i][j] = left + up;
				}
			}
		}
		return dp[row][col];
	}
}
