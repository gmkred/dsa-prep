package striver_dsa_sheet;

/**
 * <pre>
 * 
 * LeetCode solved problems
 * <a href=
"https://leetcode.com/problems/spiral-matrix-iv/submissions/1814373259/">spiral-matrix-iv</a>
 * <a href=
"https://leetcode.com/problems/spiral-matrix-ii/description//">spiral-matrix-ii</a>
 * <a href="https://leetcode.com/problems/spiral-matrix/">spiral-matrix</a>
 * 
 * 1  2  3  4  5  6 
 * 20 21 22 23 24 7
 * 19 32 33 34 25 8
 * 18 31 36 35 26 9
 * 17 30 29 28 27 10
 * 16 15 14 13 12 11
 * 
 * traverse in spiral manner that means.
 * 
 * 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,36,35,36
 * 
 * 4 pointers
 * left
 * right
 * top
 * bottom
 * 
 * 
 * Start from left -> right ( top constant)
 * top++    because when we start reading from top -> bottom we should not include the last value [0][n-1]
 * read from top -> bottom (right constant)
 * right--
 * read from right -> left (bottom constant)
 * bottom--
 * read from bottom -> top (left constant)
 * left--
 * 
 * Make sure either top > bottom or left > right that means matrix is ended so break the loop.
 * Also check after traversing left -> right whether top has 
 * crossed bottom because we are doing top++
 * 
 * Also check after traversing top -> bottom has left > right because we are doing right--
 * with this, it can solve any type of matrix N*M.
 * </pre>
 * 
 */
public class SpiralTraversalMatrix {

	public static void main(String[] args) {
		int matrix[][] = { { 1, 2, 3, 4, 5, 6 }, { 20, 21, 22, 23, 24, 7 }, { 19, 32, 33, 34, 25, 8 },
				{ 18, 31, 36, 35, 26, 9 }, { 17, 30, 29, 28, 27, 10 }, { 16, 15, 14, 13, 12, 11 } };
		spiralTraversal(matrix);
		for (int i[] : matrix) {
			for (int j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}

	private static void spiralTraversal(int matrix[][]) {
		int left = 0;
		int right = matrix[0].length - 1;
		int top = 0;
		int bottom = matrix.length - 1;
		int k = 3;
		while (left <= right && top <= bottom) {
			for (int i = left; i <= right; i++) {
				System.out.print(matrix[top][i] + " ");
			}
			top++;
			for (int i = top; i <= bottom; i++) {
				System.out.print(matrix[i][right] + " ");
			}
			right--;
			// what if there is only one row, then reducing right and increasing top is not
			// needed
			// check if top is still <= bottom
			if (top <= bottom) {
				for (int i = right; i >= left; i--) {
					System.out.print(matrix[bottom][i] + " ");
				}
				bottom--;
			}
			// check if left is still <=right
			if (left <= right) {
				for (int i = bottom; i >= top; i--) {
					System.out.print(matrix[i][left] + " ");
				}
				left++;
			}
		}
		System.out.println();
	}

}
