package striver_dsa_sheet;

import java.util.Arrays;

/**
 * 1 1 1 1 0 1 1 0 1 ==> 0 0 0 1 1 1 1 0 1
 * 
 * The goal it to make entire row and column if there is a zero.
 * 
 * 
 */

public class SetMatrixZeros {

	public static void main(String[] args) {
		int[][] arr = { { 0, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
		int[][] arr2 = { { 1, 1, 1 }, { 0, 1, 0 }, { 1, 1, 1 } };
		int[][] arr3 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 0 } };
//		setMatrixZeroBrute(arr);
//		setMatrixZeroBrute(arr2);
//		setMatrixZeroBrute(arr3);
		setMatrixZeroOptimal(arr);
		setMatrixZeroOptimal(arr2);
		setMatrixZeroOptimal(arr3);
	}

	/**
	 * TC : O(N2) SP : N Brute force
	 */
	public static void setMatrixZeroBrute(int[][] arr) {
		displayArray(arr);
		int[] row = new int[arr.length];
		int[] col = new int[arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 0) {
					if (i == 0 & j == 0)
						row[i] = -1;
					col[j] = -1;
				}
			}
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (row[i] == -1 || col[j] == -1) {
					arr[i][j] = 0;
				}
			}
		}
		displayArray(arr);
	}
	/**
	 * 
	 * <pre>
	 * steps:
	 * 1. maintain a variable for 0th column as col0, since we are changing the inplace array
	 *    it is possible that 0th row can become 0's and 0th column cannot, for this edge case 
	 *    maintaining col0 helps to determine whther 0th column is all 0's or not.
	 * 2. loop through entire array and update 0th row and column if 0 found in arr[i][j]
	 *   <b>Edge case</b> : set col0 if arr[i][j] ==0 and j!=0 i.e. column 0
	 * 3. loop through array again from st row and col and set arr[i][j=0 if any 0th row or col to that 
	 *    corresponding cell is 0
	 * 4. if col0 is 0 then loop through all the rows 0th column and make them 0
	 * 
	 * </pre>
	 * 
	 * */
	public static void setMatrixZeroOptimal(int[][] arr) {
		int col0 = 1;
		displayArray(arr);

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 0) {
					arr[i][0] = 0;
					if (j != 0) {
						arr[0][j] = 0;
					} else {
						col0 = 0;
					}
				}
			}
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[0].length; j++) {
				if (arr[i][0] == 0 || arr[0][j] == 0) {
					arr[i][j] = 0;
				}
			}
		}
		if (col0 == 0) {
			for (int i = 0; i < arr.length; i++) {
				arr[i][0] = 0;
			}
		}
		displayArray(arr);

	}

	private static void displayArray(int[][] arr) {

		for (int[] i : arr) {
			for (int j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
		System.out.println();

	}
}
