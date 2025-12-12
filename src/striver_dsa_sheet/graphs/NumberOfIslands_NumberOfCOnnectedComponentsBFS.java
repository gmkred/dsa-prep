package striver_dsa_sheet.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 * 
 * no of connected components
 * 
 *  0 1 1 0
 *  0 1 1 0
 *  0 0 1 0
 *  0 0 0 0
 *  1 1 0 1
 *  
 * we have 3 islands which are surrounded by water.
 * 1 1
 * 1 1
 *   1
 *   
 * 1 1
 * 
 * 1
 * 
 * 
 * find the number of islands if given  matrix.
 * 
 * assume all the cells are nodes/ vertices.
 * 
 * if we start a traversal (BFS/DFS) at any node and go 8 directions.
 * if we have 3 starting nodes then we have 3 islands.
 * 
 * node can be represented as {row, col}
 * use a 2D array  to mark a node is visited.
 * 
 * when queue becomes empty then there is no neighbor land.
 * 
 * now start from another land in the matrix.
 * Why it works?
 * It uses connected components, which works in this case.
 * </pre>
 */
public class NumberOfIslands_NumberOfCOnnectedComponentsBFS {
	public static void main(String[] args) {
		int islands[][] = { { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 1, 1, 0, 1 } };
		System.out.println(numberOfIslands(islands));
	}

	public static int numberOfIslands(int islands[][]) {
		boolean visited[][] = new boolean[islands.length][islands[0].length];
		int count = 0;
		for (int i = 0; i < islands.length; i++) {
			for (int j = 0; j < islands[i].length; j++) {
				if (!visited[i][j] && islands[i][j] == 1) {
					count++;
					bfs(islands, visited, i, j);
				}
			}
		}
		return count;
	}

	private static void bfs(int[][] islands, boolean[][] visited, int row, int col) {
		visited[row][col] = true;
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(row, col));
		while (!q.isEmpty()) {
			Pair temp = q.poll();
			// 8-directions/neighbors.
			// everything varies from -1 to +1
			// for 4 directions, create a 2D array
			// int fourDirs [][] = {{1,0},{-1,0},{0,-1},{0,1}};
			int _row = temp.row;
			int _col = temp.col;
			for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
				for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {
					if (_col + deltaCol >= 0 && _col + deltaCol < islands[_row].length && _row + deltaRow >= 0
							&& _row + deltaRow < islands.length) {
						// they have to be a land and non visited as well
						if (islands[_row + deltaRow][_col + deltaCol] == 1
								&& !visited[_row + deltaRow][_col + deltaCol]) {
							visited[_row + deltaRow][_col + deltaCol] = true;
							q.offer(new Pair(_row + deltaRow, _col + deltaCol));
						}
					}
				}
			}
		}
	}

	static class Pair {
		int row;
		int col;

		Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
