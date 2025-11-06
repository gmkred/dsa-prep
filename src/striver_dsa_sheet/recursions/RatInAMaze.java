package striver_dsa_sheet.recursions;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.Characters;

/**
 * <pre>
 * Given a matrix. find number of paths or directions like 
 * DURL 
 * D=down
 * U=up
 * R=right
 * L=left
 * 
 * that a  rat can from [0,0] to [n-1][n-1]
 * 
 *   0 1 2 3
 * 0{1 0 0 0}
 * 1{1 1 0 1}
 * 2{1 1 0 0}
 * 3{0 1 1 1}
 * 
 * 
 * there are 2 paths which rat can take to reach end.
 * 
 *   0 1 2 3
 * 0{1      }
 * 1{1      }
 * 2{1 1    }
 * 3{  1 1 1}
 * 
 * DDRDRR
 *   0 1 2 3
 * 0{1      }
 * 1{1 1    }
 * 2{  1    }
 * 3{  1 1 1}
 * DRDDRR
 * 
 * </pre>
 * 
 */
public class RatInAMaze {
	public static void main(String[] args) {
		int[][] matrix = { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 1, 1, 0, 0 }, { 0, 1, 1, 1 } };
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		List<Character> path = new ArrayList<>();
		List<List<Character>> paths = new ArrayList<>();
//		findPaths(matrix, 0, 0, path, paths, new boolean[4]);
//		System.out.println(paths);
		findPaths_striver(matrix, 0, 0, path, paths, visited);
		System.out.println(paths);

	}

	/**
	 * <pre>
	 * 
	 * TC : if all are 1's it will take O(4 ^ (n*m)) 
	 * for each cell, trying out 4 directions.
	 * 
	 * SC : auxiliary space if all are 1's O(m*n)
	 * 
	 * </pre>
	 */
	private static void findPaths_striver(int[][] matrix, int row, int col, List<Character> path,
			List<List<Character>> paths, boolean[][] visited) {

		if (row == matrix.length - 1 && col == matrix[row].length - 1) {
			paths.add(new ArrayList<>(path));
			return;
		}
//coming from which direction, we should avoid that direction again going back.
		// For 4 directions 1 for top, 2 for right, 3 for down, 4 for.
		// down
		// when moving down, block up.
		if (row + 1 < matrix.length) {
			if (matrix[row + 1][col] == 1 && !visited[row + 1][col]) {
				path.add('D');
				visited[row][col] = true;
				findPaths_striver(matrix, row + 1, col, path, paths, visited);
				path.remove(path.size() - 1);
				visited[row][col] = true;
			}
		}
		// left
		// when moving left, block right.
		if (col - 1 >= 0) {
			if (matrix[row][col - 1] == 1 && !visited[row][col - 1]) {
				path.add('L');
				visited[row][col] = true;
				findPaths_striver(matrix, row, col - 1, path, paths, visited);
				path.remove(path.size() - 1);
				visited[row][col] = false;
			}
		}
		// up
		// when moving up, block down.
		if (row - 1 >= 0) {
			if (matrix[row - 1][col] == 1 && !visited[row - 1][col]) {
				path.add('U');
				visited[row][col] = true;
				findPaths_striver(matrix, row - 1, col, path, paths, visited);
				path.remove(path.size() - 1);
				visited[row][col] = false;
			}
		}
		// right
		// when moving right, block left.
		if (col + 1 < matrix[row].length && !visited[row][col + 1]) {
			if (matrix[row][col + 1] == 1) {
				path.add('R');
				visited[row][col] = true;
				findPaths_striver(matrix, row, col + 1, path, paths, visited);
				path.remove(path.size() - 1);
				visited[row][col] = false;
			}
		}
		return;
	}

//my sol
	private static void findPaths(int[][] matrix, int row, int col, List<Character> path, List<List<Character>> paths,
			boolean[] prevDir) {

		if (row == matrix.length - 1 && col == matrix[row].length - 1) {
			paths.add(new ArrayList<>(path));
			return;
		}
//coming from which direction, we should avoid that direction again going back.
		// For 4 directions 1 for top, 2 for right, 3 for down, 4 for.
		// down
		// when moving down, block up.
		if (row + 1 < matrix.length) {
			if (matrix[row + 1][col] == 1 && !prevDir[2]) {
				path.add('D');
				prevDir[0] = true;
				findPaths(matrix, row + 1, col, path, paths, prevDir);
				path.remove(path.size() - 1);
				prevDir[0] = true;
			}
		}
		// left
		// when moving left, block right.
		if (col - 1 >= 0) {
			if (matrix[row][col - 1] == 1 && !prevDir[3]) {
				path.add('L');
				prevDir[1] = true;
				findPaths(matrix, row, col - 1, path, paths, prevDir);
				path.remove(path.size() - 1);
				prevDir[1] = false;
			}
		}
		// up
		// when moving up, block down.
		if (row - 1 >= 0) {
			if (matrix[row - 1][col] == 1 && !prevDir[0]) {
				path.add('U');
				prevDir[2] = true;
				findPaths(matrix, row - 1, col, path, paths, prevDir);
				path.remove(path.size() - 1);
				prevDir[2] = false;
			}
		}
		// right
		// when moving right, block left.
		if (col + 1 < matrix[row].length && !prevDir[1]) {
			if (matrix[row][col + 1] == 1) {
				path.add('R');
				prevDir[3] = true;
				findPaths(matrix, row, col + 1, path, paths, prevDir);
				path.remove(path.size() - 1);
				prevDir[3] = false;
			}
		}
		return;
	}
}
