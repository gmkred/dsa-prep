package arrays.binarysearch;

/**
 * <pre>
 * 
 * Median of row wise sorted matrix
 * 
 * {2, 3,  4,  5,  10}
 * {1, 5,  7,  9,  11}
 * {9, 10, 12, 14, 16}
 * 
 * 2D M*N 
 * M = Odd
 * N = Odd 
 * odd*odd = odd.
 * 
 * 
 * Median = middle value in a sorted set of numbers.
 * sorted 1d arr[] = {1,2,3,4,5,7,9,9,10,10,11,12,14,16}
 * </pre>
 */
public class MatrixMedian {
	public static void main(String[] args) {

	}

	/**
	 * <pre>
	 * 
	 * Put 2d into 1D and sort it and get the middle value
	 * TC : O(M*N) + O(N*M Log M*N)
	 * SC : O(M*N)
	 * 
	 * </pre>
	 */
	private static int bruteForce(int arr[][]) {
		return 0;
	}

	/**
	 * <pre>
	 * 
	 * Search space [1 to 16]
	 * {1, 2, 3, 4, 5, 7, 9, 9, 10, 10, 11, 12, 14, 16}
	 *                    M
	 *                    
	 * we cannot say everything left is < or right is > becasue of matrix
	 * We know that on left side there are 7 elements, and on right side there will be 7
	 * {2, 3,  4,  5,  10}
	 * {1, 5,  7,  9,  11}
	 * {9, 10, 12, 14, 16}
	 * {1, 2, 3, 4, 5, 7, 9, 10, 10, 10, 11, 12, 14, 16}
	 *                       M
	 *  There will be always ((m*n)/2) elements on left. 
	 *  so the median will be some where > ((m*n)/2)	 *
	 * </pre>
	 */
	private static int better(int arr[][]) {
		int median = 0;

		return median;

	}
}
