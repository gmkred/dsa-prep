package striver_dsa_sheet;

/**
 * <pre>
 * 1 2 3		7 4 1
 * 4 5 6     = 	8 5 2
 * 7 8 9		9 6 3
 * </pre>
 */
public class RotateMatrixBy90Degrees {

	public static void main(String[] args) {

		int matrix[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
//		rotateMatrixBy90Degrees(matrix);
		rotateMatrixBy90Degrees_optimal(matrix);
		for (int i[] : matrix) {
			for (int j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}

	/**
	 * <pre>
	 * 1 2 3		7 4 1
	 * 4 5 6     = 	8 5 2
	 * 7 8 9		9 6 3	
	 * The observation above is. that 1st column has become the first row but in reverse order.
	 * so first transpose the array.
	 * Transpose : make columns as rows and rows and columns.
	 * Then reverse rows to get the 90 degree rotated array in-memory.
	 * 
	 * 1 2 3		1 4 7		7 4 1
	 * 4 5 6	=	2 5 8	=	8 5 2
	 * 7 8 9		3 6 9		9 6 3
	 * 
	 * To Transpose, keep the diagonal at place and only swap one side of elements to another side.
	 * Note: only half rotation is enough, if done for entire matrix it will make the matrix to its original statte
	 * TC : O(N + N/2)
	 * SC :O(1)
	 * 
	 * 
	 * Transpose and reverse the rows.
	 * </pre>
	 */
	private static void rotateMatrixBy90Degrees_optimal(int matrix[][]) {

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j <= i; j++) {
				if (i != j) {
					int temp = matrix[i][j];
					matrix[i][j] = matrix[j][i];
					matrix[j][i] = temp;
				}
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			int m = 0, n = matrix[i].length - 1;
			while (m < n) {
				int temp = matrix[i][m];
				matrix[i][m] = matrix[i][n];
				matrix[i][n] = temp;
				m++;
				n--;
			}
		}
	}

	/**
	 * <pre>
	 * 1 2 3		7 4 1
	 * 4 5 6     = 	8 5 2
	 * 7 8 9		9 6 3
	 * 
	 * 
	 * N = matrix total elements.
	 * TC : O(2N)
	 * SC : O(N)
	 * 
	 * </pre>
	 */
	private static void rotateMatrixBy90Degrees(int matrix[][]) {

		int[][] copy = new int[matrix.length][matrix.length];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				copy[j][matrix[i].length - i - 1] = matrix[i][j];
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = copy[i][j];
			}
		}

	}
}
