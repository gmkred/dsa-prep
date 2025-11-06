package striver_dsa_sheet.dynamicProgramming;

public class MinPathSum {
	public static void main(String[] args) {
		int grid[][] = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
		int dp[][] = new int[grid.length][grid[0].length];
		System.out.println(minPathSum(grid));
	}

	public static int minPathSum(int[][] grid) {
		return recursion(grid, grid.length - 1, grid[0].length - 1);
	}

	private static int recursion(int[][] grid, int row, int col) {
		if (row == 0 && col == 0) {
			return grid[0][0];
		}
		if (row < 0 || col < 0) {
			return Integer.MAX_VALUE - 10000;
		}
		int up = grid[row][col] + recursion(grid, row - 1, col);

		int left = grid[row][col] + recursion(grid, row, col - 1);

		return Math.min(up, left);

	}

	private static int recursionDP(int[][] grid, int row, int col, int dp[][]) {
		if (row == 0 && col == 0) {
			dp[0][0] = grid[0][0];
			return dp[0][0];
		}
		if (row < 0 || col < 0) {
			return 2010000;
		}
		if (dp[row][col] > 0) {
			return dp[row][col];
		} else {
			int up = grid[row][col] + recursionDP(grid, row - 1, col, dp);
			int left = grid[row][col] + recursionDP(grid, row, col - 1, dp);
			dp[row][col] = Math.min(up, left);
		}
		return dp[row][col];
	}
}
