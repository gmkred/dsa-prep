package striver_dsa_sheet.recursions;

/**
 * <pre>
 * 
 * Sudoku is a 9*9 matrix, on each [][] it contains a 3*3
 *   0 1 2  3 4 5  6 7 8
 *0 |* * *| * * *| * * *|
 *1 |* * *| * * *| * * *|
 *2 |* * *| * * *| * * *|
 *  ------ ------ ------
 *3 |* * *| * * *| * * *|
 *4 |* * *| * * *| * * *|
 *5 |* * *| * * *| * * *|
 *  ------ ------ ------
 *6 |* * *| * * *| * * *|
 *7 |* * *| * * *| * * *|
 *8 |* * *| * * *| * * *|
 *  ------ ------ ------
 * 
 * conditions :
 * 1. each row must contains 1-9 digits
 * 	check entire row at each empty cell, to find which is valid at that empty cell.
 * 	If not valid, then return false as going forward with empty cell will not give result.
 * 	To check board[i][col] fix col and vary row because we need to check every of that column.
 * 2. each column must contains 1-9 digits
 * 	check entire column at each empty cell, to find which is a valid at that empty cell.
 * 	If not valid, the return false.
 * To check board[row][i] fix row and vary col.
 * 3. each 3*3 matrix must contain 1-9 digits.
 *  Important.
 *  We have sub matrix, which is 3*3. we need to check if selected value is present in the sub matrix as well.
 *  To find this first observe the matrix.
 * 
 *    0 1 2  3 4 5  6 7 8
 * 0 |* * *| * * *| * * *| ^
 * 1 |* * *| * * *| * * *| | 0th part
 * 2 |* * *| * * *| * * *| v
 *   ------ ------ ------
 * 3 |* * *| * * *| * * *| ^
 * 4 |* * *| * * *| * * *| | 1st part
 * 5 |* * *| * * *| * * *| v
 *   ------ ------ ------
 * 6 |* * *| * * *| * * *| ^
 * 7 |* * *| * * *| * * *| | 2nd part
 * 8 |* * *| * * *| * * *| v
 *   ------ ------ ------
 *   <----> <----> <---->
 *     0th    1st    2nd    parts
 * suppose the current empty cell is at board[7][4]
 * row = 7
 * col = 4
 * 
 * with this value we need to find the beginning of the sub matrix.
 * 
 * to find the beginning row of sub matrix
 * firstly
 * divide row / 3 = 7/3 = 2nd part.
 * divide col / 3 = 4/3 = 1st part
 * 
 * so the sub matrix is 2nd part in row and 1st part in col so it should start from [6,3]
 * 
 * but to get row as 6 and col 3 we need to perform multiplication wit 3.
 * 
 * sub matrix row beginning 2*3 = 6th row
 * sub matrix col beginning 1*3 = 3rd col
 * 
 * [6,3] [6,4] [6,5]
 * [7,3] [7,4] [7,5]
 * [8,3] [8,4] [8,5]
 * 
 * Now, to traverse through the sub matrix observe that at each row, row is fixed and col movies.
 * 
 * so to get sub matrix indexes as 0 to 8, perform below operation.
 *                        | 0th col        1st col        2nd col
 * 0th row = 6 + 0/3 = 6  | 3 + 0%3 = 3    3 + 1%3 = 4    3 + 2%3 = 5
 * 1st row = 6 + 4/3 = 7  | 3 + 0%3 = 3    3 + 1%3 = 4    3 + 2%3 = 5
 * 2nd row = 6 + 7/3 = 8  | 3 + 0%3 = 3    3 + 1%3 = 4    3 + 2%3 = 5
 * 
 * to fix row, we use / and to move col use %
 * 
 * because / gives same value for 0/3,1/3,3/3 which is 0th row
 * because % gives different value on same row 0%3 = 0, 1%3 = 1, 2%3 = 2
 * 
 * 
 * 
 * </pre>
 */
public class SudokuSolver {
	public static void main(String[] args) {
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		display(board);

		recursionSudoku(board);
		display(board);
	}

	private static boolean recursionSudoku(char[][] board) {

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				// find the empty cell, and start finding the valid value from 1 to 9.
				if (board[row][col] == '.') {
					// if this loop ends that means there is no valid number that can be set so
					// return fasle.
					for (char val = '1'; val <= '9'; val++) {
						if (isValid(row, col, val, board)) {
							// set the val.
							board[row][col] = val;
							// now move on to the next empty cell.
							// if it returns false, that means we need not consider the current val and
							// remove it from the borad.
							// this will be only true when there is a valid sudoko, if if with current value
							// there cannot be a valid sudoko at current cell, then it returns false and we
							// need to try with next value at current cell.
							if (recursionSudoku(board)) {
								// when there will be no empty cell, code returns true as all the valid val are
								// set.
								return true;
							} else {
								// if not valid, it return recursion call returns false, so remove the val
								board[row][col] = '.';
							}
							// if setting val, is resulting in invalid Sudoko, remove it upon returning.
						}
					}
					return false;
				}
			}
		}
		// if row loop completed, that means there is no empty cell left so its a valid
		// Sudoko.
		return true;
	}

	private static boolean isValid(int row, int col, char val, char[][] board) {

		// check if val present in given row, col and sub matrix.
		for (int index = 0; index < 9; index++) {
			if (board[row][index] == val) {
				return false;
			}

			if (board[index][col] == val) {
				return false;
			}

			if (board[3 * (row / 3) + index / 3][3 * (col / 3) + index % 3] == val) {
				return false;
			}
		}
		// if val is not present at any place in the row, col and sub matrix retur true.
		return true;
	}

	private static void display(char[][] board) {

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
