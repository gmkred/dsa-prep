package striver_dsa_sheet.recursions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 
 * 
 * Given N, place a N queens in a N*N chess.
 * 
 * Constraints:
 * 1. each row and col should contain a Q.
 * 2. Queens cannot be killed
 * 	Queen can travel, 8 directions, so having a queen to any of the other queens 8 directions will kill the queen.
 * 
 * for N = 4
 * 
 * |- Q - -|       |- - Q -|
 * |- - - Q|  and  |Q - - -|
 * |Q - - -|       |- - - Q|
 * |- - Q -|       |- Q - -|
 * 
 * 
 * These are the possible chess boards.
 * 
 * Intuition is to follow the combination sum problem, in which we need to avoid duplicate combinations,
 * similarly, try looping for N*N and check if Q can be places at any given row and col.
 * 
 * </pre>
 * 
 */
public class NQueens {

	public static void main(String[] args) {
		int N = 4;
		List<List<String>> boards = new ArrayList<>();
		String s = "";
		for (int i = 0; i < N; i++) {
			s += ".";
		}
		List<String> board = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			board.add(s);
		}
//		nQueensRecursion(N, 0, boards, board);
//		System.out.println(boards);
		// 3 - always 3 directions.
		boolean[][] hashs = new boolean[3][];
		hashs[0] = new boolean[N];
		hashs[1] = new boolean[2 * N - 1];
		hashs[2] = new boolean[2 * N - 1];
		nQueensRecursion2(N, 0, boards, board, hashs);
		System.out.println(boards);
	}

	/**
	 * <Pre>
	 * optimal solution.
	 * Instead of looping through all the indexes to check if it is a safe place, 
	 * we maintain a hash for each direction, so checking for a safe place will be O(1) for all three directions.
	 * 
	 * Observations.
	 * 
	 * 1. To check left direction.
	 * when we want to place Q at any column, first we need to check that row has Q anywhere.
	 * |- Q - -|
	 * |- - - Q|
	 * |Q - - -|
	 * |- - Q -|
	 * 
	 * { 
	 * 0 |
	 * 1 |
	 * 2 |
	 * 3 |
	 *   } maintain a hash for N rows and mark true if any rows col is set Q.
	 * 
	 * 2. To check left-bottom direction.
	 *   0 1 2 3 4
	 * 0[0 1 2 3]4
	 * 1[1 2 3 4]5
	 * 2[2 3 4 5]6  
	 * 3[3 4 5 6]7      
	 * 4[4 5 6 7 8
	 * if we observe, we can add the row + col indexes which gives the left-bottom diagonals.
	 * hashmap should look : {_ _ _ _ _ _ _}
	 *                        0 1 2 3 4 5 6
	 * For example, if Q is set at [1,2] 1 row, 2 col. update hash[1+3] = true.
	 * 
	 * for the next col check if hash[row+col] is already set true, then its not a safe position.
	 * 
	 * 3. To check left-top direction
	 *    0 1 2 3
	 *  0[3 4 5 6]
	 *  1[2 3 4 5]
	 *  2[1 2 3 4]  
	 *  3[0 1 2 3]
	 * 
	 * 
	 * <span style="color:red;">(n-1) + col - row </span>
	 * so it can be 
	 * 
	 * row 2
	 * col 1
	 * 
	 * (4-1) + 1 -2
	 * 3 + 1 -2 = 2
	 * 
	 * row 3
	 * col 1
	 * 
	 * 3 + 1 - 3 = 1 
	 * 
	 * so maintain a hash map {_ _ _ _ _ _ _}
	 *                         0 1 2 3 4 5 6
	 * </pre>
	 */
	private static void nQueensRecursion2(int N, int col, List<List<String>> boards, List<String> board,
			boolean[][] hashs) {

		if (col == N) {
			boards.add(new ArrayList(board));
			return;
		}
		for (int row = 0; row < N; row++) {

			if (hashs[0][row] == false && hashs[1][row + col] == false && hashs[2][(N - 1) - row + col] == false) {

				hashs[0][row] = true;
				hashs[1][row + col] = true;
				hashs[2][(N - 1) - row + col] = true;
				String s = board.get(row);
				char[] chars = s.toCharArray();
				chars[col] = 'Q';
				String s2 = new String(chars);
				board.set(row, s2);
				nQueensRecursion2(N, col + 1, boards, board, hashs);
				board.set(row, s);
				hashs[0][row] = false;
				hashs[1][row + col] = false;
				hashs[2][(N - 1) - row + col] = false;
			}

		}
	}

	/**
	 * <pre>
	 * 
	 * 
	 * One method
	 * </pre>
	 */
	private static void nQueensRecursion(int N, int col, List<List<String>> boards, List<String> board) {
		// if col == N, that means it is a valid board.
		if (col == N) {
			boards.add(new ArrayList<>(board));
			return;
		}

		// on each col, traverse all the rows to find the safe place.
		// col will be == N, only if there is a valid board, if not it will return as
		// for loop fro rows will be executed without placing a Q.
		for (int row = 0; row < N; row++) {
			if (isSafePlace(col, row, board)) {
				// get current row and check for a safe col.
				String s = board.get(row);
				char[] chars = s.toCharArray();
				chars[col] = 'Q';
				String s2 = new String(chars);
				board.set(row, s2);
				nQueensRecursion(N, col + 1, boards, board);
				board.set(row, s);
			}
		}
		return;
	}

	/**
	 * <pre>
	 * 
	 * Remember, we do not have to check all the 8 directions, because when we start checking for a col and a row,
	 * only the left side, left-top-diagonal and left-bottom-diagonal is what we need to check.
	 * 
	 * TC : O(3* Col) = each time 3 loops runs from given col to 0, so it ill be 3 col.
	 * 
	 * We can optimaize it using hashMaps for 3 directions.
	 * </pre>
	 */
	private static boolean isSafePlace(int col, int row, List<String> board) {
		int c = col;
		int r = row;
		// left-top-diagonal
		while (c >= 0 && r >= 0) {
			if (board.get(r).charAt(c) == 'Q') {
				return false;
			}
			c--;
			r--;
		}
		c = col;
		r = row;
		// left side
		while (c >= 0) {
			if (board.get(r).charAt(c) == 'Q') {
				return false;
			}
			c--;
		}
		c = col;
		r = row;
		// left-bottom-diagonal
		while (c >= 0 && r < board.size()) {
			if (board.get(r).charAt(c) == 'Q') {
				return false;
			}
			c--;
			r++;
		}
		return true;
	}
}
