package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * This is a 3D problem, where we will be moving 2 pointers simultaneously.
 * 
 * Since the row is common at each operation, we have have single reference to row.
 * col1 and col2 for 2 pointers becasue they start from different fixed points.
 * 
 * Given a grid with cherries.
 * 
 * 2 robots from top-left and top-right started to pick the cherries.
 * 
 * return the Max cherries they can pick when they reach to the bottom of the grid.
 * 
 * Constraints : 
 * 
 * 1. If they are at same cell only one can get the amount of cherries from that cell.
 * 2. each robot can move down, down-left or down-right
 * 
 * 
 * 
 * There are 3 possibilities for each robot
 * down, down-left and down-right.
 * 
 * Since we have 2 robots.
 * 
 *  It is possible that if 
 *  robot1 moves down,       robot2 moves down or down-left or down-right
 *  robot1 moves down-left,  robot2 moves down or down-left or down-right
 *  robot1 moves down-right, robot2 moves down or down-left or down-right
 * 
 * this means for each robot1 direction movement, robot2 should move all 3 directions and calculate the max pick.
 * so totally 3*3 for each robot1 cell.
 * 
 * </pre>
 */
public class CherryPick3DGrid {
	public static void main(String[] args) {
		int[][][] dp = new int[2][2][3];
		int grid[][] = { { 3, 1, 1 }, { 2, 5, 1 }, { 1, 5, 5 }, { 2, 1, 1 } };
		System.out.println(cherryPickupDP(grid));

	}

	public static int cherryPickup(int[][] grid) {
		return rec(grid, 0, 0, grid[0].length - 1);
	}

	// fixed points
	/**
	 * <pre>
	 * TC : O(3 ^ n * 3 ^ n)  for each robot there is 3 options 
	 * SC : O(N) auxiliary stack space;
	 * </pre>
	 */
	private static int rec(int[][] grid, int r, int c1, int c2) {

		if (c1 < 0 || c1 >= grid[r].length || c2 < 0 || c2 >= grid[r].length) {
			return 0;
		}
		if (r == grid.length - 1) {
			if (c1 == c2) {
				return grid[r][c1];
			}
			return grid[r][c1] + grid[r][c2];
		}
		int maxi = (int) -1e8;
		/**
		 * At each cell of robot1, there are 3 possibilities c1-1,c1 and c1+1 to next
		 * row ANd for each robot1 movement, there are 3 possibilities for robot2
		 * c2-1,c2 and c2+1 to next row. with below for loops, for each cell movement of
		 * robot1, there will be 3 movements for robot2
		 * 
		 * ANd we need to find out the max cherries that both robots can pick on each
		 * row, if they are at same cell, then only one can pick the cherries.
		 */
		for (int robot1 = -1; robot1 <= 1; robot1++) {
			for (int robot2 = -1; robot2 <= 1; robot2++) {
				int maxj = 0;
				if (c1 == c2) {
					maxj += grid[r][c1];
				} else {
					maxj += grid[r][c1] + grid[r][c2];
				}
				maxj += rec(grid, r + 1, c1 + robot1, c2 + robot2);
				maxi = Math.max(maxi, maxj);
			}
		}
		return maxi;
	}

	public static int cherryPickupDP(int[][] grid) {
		// since we have 2 points on each row, we need to maintain 3D array
		// dp[row][col1][col2]
		int dp[][][] = new int[grid.length][grid[0].length][grid[0].length];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				for (int k = 0; k < dp[i][j].length; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		return recDP(grid, 0, 0, grid[0].length - 1, dp);
	}

	// fixed points
	private static int recDP(int[][] grid, int r, int c1, int c2, int[][][] dp) {

		if (c1 < 0 || c1 >= grid[r].length || c2 < 0 || c2 >= grid[r].length) {
			return 0;
		}
		if (r == grid.length - 1) {
			if (c1 == c2) {
				dp[r][c1][c2] = grid[r][c1];
				return grid[r][c1];
			}
			dp[r][c1][c2] = grid[r][c1] + grid[r][c2];
			return dp[r][c1][c2];
		}
		if (dp[r][c1][c2] != -1) {
			return dp[r][c1][c2];
		}
		int maxi = (int) -1e8;
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int maxj = 0;
				if (c1 == c2) {
					maxj += grid[r][c1];
				} else {
					maxj += grid[r][c1] + grid[r][c2];
				}
				maxj += recDP(grid, r + 1, c1 + i, c2 + j, dp);
				maxi = Math.max(maxi, maxj);
			}
		}
		dp[r][c1][c2] = maxi;
		return dp[r][c1][c2];
	}
}
