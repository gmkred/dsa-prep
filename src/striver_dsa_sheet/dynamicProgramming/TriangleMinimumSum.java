package striver_dsa_sheet.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 
 * ***Important***
 * This problem has variable base conditions, 
 * 
 * when we reach to bottom, there are 4 variables that neeeds to e considered.
 * 
 * in this type of problems for DP or Tabulation put base conditions from the variables instead of dp[0][0]
 * 
 * pointer can move only bottom and bottom-right (adjacent)
 * 
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * 
 * min sum = 2 + 3 + 5 + 1 = 11
 * 
 * 
 * 
 * 
 * </pre>
 */
public class TriangleMinimumSum {
	public static void main(String[] args) {
		List<List<Integer>> lists = new ArrayList<>();
		List<Integer> l1 = new ArrayList<>();
		l1.add(2);
		List<Integer> l2 = new ArrayList<>();
		l2.add(3);
		l2.add(4);
		List<Integer> l3 = new ArrayList<>();
		l3.add(6);
		l3.add(5);
		l3.add(7);

		List<Integer> l4 = new ArrayList<>();
		l4.add(4);
		l4.add(1);
		l4.add(8);
		l4.add(3);
		lists.add(l1);
		lists.add(l2);
		lists.add(l3);
		lists.add(l4);
		System.out.println(minimumTotal(lists));
	}

	public static int minimumTotal(List<List<Integer>> triangle) {
		int dp[][] = new int[triangle.size()][];
		int min = -(10 * 10 * 10 * 10 + 1);
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new int[triangle.get(i).size()];
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = Integer.MIN_VALUE;
			}
		}
//		return recMin(triangle, 0, 0, dp, min);
		return recTabulation(triangle, triangle.size() - 1);
	}

	/**
	 * [2] [3,4] [6,5,7] [4,1,8,3]
	 */
	private static int recMin(List<List<Integer>> triangle, int row, int col, int dp[][], int min) {
		// base
		if (row == triangle.size() - 1) {
			return triangle.get(row).get(col);
		}
		if (dp[row][col] > min) {
			return dp[row][col];
		}
		int bottom = triangle.get(row).get(col) + recMin(triangle, row + 1, col, dp, min);

		int adjacent = triangle.get(row).get(col) + recMin(triangle, row + 1, col + 1, dp, min);
		dp[row][col] = Math.min(bottom, adjacent);
		return dp[row][col];
	}

	private static int recTabulation(List<List<Integer>> triangle, int row) {
		// there are n-1 base cases, because at the last list, we have n-1 values so, we
		// have to store n value in dp[n-1][0] to dp[n][n-1]
		int dp[][] = new int[triangle.size()][];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = new int[triangle.get(i).size()];
		}
		// base conditions
		for (int c = 0; c < triangle.get(triangle.size() - 1).size(); c++) {
			dp[row][c] = triangle.get(row).get(c);
		}
		// start from row-1, if 4 rows are there, 4th row will be for base conditions
		// and start from 3rd row.
		for (int i = row - 1; i >= 0; i--) {
			for (int j = 0; j < triangle.get(i).size(); j++) {
				int bottom = dp[i + 1][j] + triangle.get(i).get(j);
				int bottomRight = dp[i + 1][j + 1] + triangle.get(i).get(j);
				dp[i][j] = Math.min(bottom, bottomRight);
			}
		}
		return dp[0][0];
	}

}
