package striver_dsa_sheet;

/***
 * <pre>
 * 
 * First 6 rows of Pascal triangle
 *  0 1 2 3 4 5 6 7 8
 *0         1
 *1       1   1
 *2     1   2   1
 *3   1   3   3   1
 *4 1   4   6   4   1  
 * 
 * Print N rows of Pascal triangle.
 * 3 types of Questions:
 * 1. Given row and col, give the element at that place. 
 * 	Note : Index start from 1
 * 	R=5 and C=3 5th row 3rd element ans = 6
 * 2. Print Nth row pascal triangle
 * 	5 = 1 4 6 4 1
 * 3. Print entire triangle.
 * 
 * </pre>
 */
public class PascalTriangle {

	public static void main(String[] args) {
//		printPascalTriangle(5);
		System.out.println(getPascalTriangleElement(5, 3));
//		printNthRowOfPascalTriangle(6);
		generateAllRowsWithOutMatrix(5);
	}

	private static void generateAllRowsWithOutMatrix(int N) {
		// rows are 1 based in pascal triangle
		for (int row = 1; row <= N; row++) {
			System.out.print("1 ");
			int combination = 1;
			for (int colIndex = 1; colIndex < row; colIndex++) {
				combination = combination * (row - colIndex);
				combination = combination / colIndex;
				System.out.print(combination + " ");
			}
			System.out.println();
		}
	}

	/**
	 * <pre>
	 * This is for indexes starting from 1
	 *  0 1 2 3 4 5 6 7 8
	 *1         1
	 *2       1   1
	 *3     1   2   1
	 *4   1   3   3   1
	 *5 1   4   6   4   1
	 *
	 *5th row  1    4       6           4                1
	 *         1   4/1   4*3/1*2   4*3*2/1*2*3    4*3*2*1/1*2*3*4
	 * 
	 * (previous * (row - col)) / col
	 * 
	 * Note : consider colomn's are 0 based and rows are 1 based.
	 * And start from col 1 as col 0 is always value 1
	 * </pre>
	 */
	private static void printNthRowOfPascalTriangle(int N) {
		int rowNum = N;
		int combination = 1;
		System.out.print(combination + " ");

		for (int colIndex = 1; colIndex < rowNum; colIndex++) {
			combination = combination * (rowNum - colIndex);
			combination = combination / colIndex;
			System.out.print(combination + " ");
		}
	}

	/**
	 * <pre>
	 * Use combination formula.
	 * 
	 * n C r = n!/r!*(n-r)!
	 * 
	 * (row-1) C (col-1) = (row-1)! / (row-1)-(col-1)! * (col-1)!
	 * 
	 * This is for indexes starting from 1
	 *  1 2 3 4 5 6 7 8 9
	 *1         1
	 *2       1   1
	 *3     1   2   1
	 *4   1   3   3   1
	 *5 1   4   6   4   1    
	 * 5 C 3 = 4 C 2 = 4*3*2*1 / 2*1 * 2*1 = 4*3/1*2 = 6
	 * 
	 * TC : (ROW - COL);
	 * 
	 * </pre>
	 */
	private static int getPascalTriangleElement(int row, int col) {
		int n = row - 1;
		int r = col - 1;

		int combination = 1;
		// loop till i > (n - r), because after that (n-r) in the denominator will be
		// canceled. so no need to go till or behind (n-r)
		for (int i = n; i > (n - r); i--) {
			combination = (combination * i) / (n - i + 1);
		}
		return combination;
	}

	/**
	 * <pre>
	 * TC : O(N*N)
	 * SC : O(N*N)
	 * 
	 * </pre>
	 */
	private static void printPascalTriangle(int N) {
		int[][] matrix = new int[N][N * 2 - 1];
		matrix[0][N - 1] = 1;
		for (int i = 1; i < N; i++) {
			for (int j = (N - 1) - i; j <= (N - 1) + i; j += 2) {
				if (j == N - 1 - i || j == N - 1 + i) {
					matrix[i][j] = 1;
				} else {
					matrix[i][j] = matrix[i - 1][j - 1] + matrix[i - 1][j + 1];
				}
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
