package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * given a matrix. find number of paths 
 * that can lead from [0,0] to [n-1,n-1]
 * 
 * 
 * 
 * </pre>
 */
public class TotalUniquePaths {
	public static void main(String[] args) {
		int row = 4;
		int col = 4;
		int dp[][] = new int[row + 1][col + 1];
//		System.out.println(recursion(row, col));
//		System.out.println(recursionDP(row, col, dp));
		System.out.println(recursionDPTabulation(row, col));
	}

	private static int recursion(int row, int col) {
		if (row == 0 && col == 0) {
			return 1;
		} else if (row < 0 || col < 0) {
			return 0;
		}
		int count = 0;
		int left = recursion(row, col - 1);

		int up = recursion(row - 1, col);
		count = up + left;

		return count;

	}

	private static int recursionDP(int row, int col, int dp[][]) {
		if (row == 0 && col == 0) {
			return 1;
		} else if (row < 0 || col < 0) {
			return 0;
		}

		if (dp[row][col] > 0) {
			return dp[row][col];
		}
		int count = 0;
		int left = recursionDP(row, col - 1, dp);

		int up = recursionDP(row - 1, col, dp);
		dp[row][col] = up + left;
		count = up + left;

		return count;

	}

	private static int recursionDPTabulation(int row, int col) {
		int dp[][] = new int[row + 1][col + 1];
		dp[0][0] = 1;
		for (int i = 0; i <= row; i++) {
			for (int j = 0; j <= col; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				int up = 0;
				int left = 0;
				if (i > 0) {
					up = dp[i - 1][j];
				}
				if (j > 0) {
					left = dp[i][j - 1];
				}

				dp[i][j] = up + left;
			}
		}
		return dp[row][col];
	}
}
