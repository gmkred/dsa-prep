package striver_dsa_sheet.dynamicProgramming;

/**
 * <pre>
 * Tough
 * 
 * Partition DP.
 * 
 * First understand MCM 
 * Matrix Chain Multiplication.
 * 
 * 
 * ABC
 * 
 * A = 10*30
 * B = 30*5
 * C = 5*60
 * 
 * To multiple 2 matrixes the A(COl) == B(ROW)
 * 
 * Number of multiplications required 10*30*5
 * 
 * total operations required
 * 
 * The resultant of the matrix is 10*5
 * AB(10*30*5) + AB(C(10*5*60))
 * 1500 + 3000 = 4500 operations
 * 
 * other way
 * 
 * BC (30*5*60) + A(BC(10*30*60))
 * 9000 + 18000 = 27000 operations
 * 
 * 4500 < 27000
 * 
 * 2 ways 
 * A(BC)
 * B(CA)
 * 
 * 2*2 2*1
 * [1 2] [2]
 *      *
 * [3 1] [3]
 * 
 * [1*2 + 2*3]
 * [3*2 + 1*3]
 * 
 * 2*1 matrix
 * 
 * 4 multiplications
 * 
 *           0  1  2  3  4
 * arr[] = {10,20,30,40,50}
 * 
 * A = 10*20  1st index = arr[0] * arr[1]
 * B = 20*30  2nd index = arr[1] * arr[2]
 * C = 30*40  3rd index = arr[2] * arr[3]
 * D = 40*50  4th index = arr[3] * arr[4]
 * 
 * 
 * 
 * Rules for partition DP :
 * 1. Start with entire block/array(i,j)
 * 	 i is start point and j is end point
 *   ij  ij       ij i j       i j  ij
 * 	(AB)(CD) and (A)(BCD) and (ABC)(D)
 *   and break down and find the minimum operations
 *   
 *   f(1,4)
 *   base case i==j return
 * 2. Try all partitions : run a loop to try all partitions
 * 		i -> j-1
 * 		(A)(BCD) (AB)(CD) (ABC)(D)
 * (A)      (BCD)
 * (10*20)  (20*50)
 * 10*20*50 = 1000
 *     k
 *     i     j
 *  {0 1 2 3 4}
 *     A B C D
 *    (A) (BCD)
 *     i k   j
 *  {0 1 2 3 4} 
 * 	   A B C D
 * 	  (AB)(CD)
 *     i   k j
 *  {0 1 2 3 4} 
 * 	   A B C D
 * 	  (ABc)(D)
 * 
 *  k is the partition
 * 3. return the best possible 2 partitions.
 * 
 * </pre>
 * 
 */
public class MatrixChainMultiplication {
	public static void main(String[] args) {
		int arr[] = { 10, 20, 30, 40, 50 };
		System.out.println(mcm(arr, 1, arr.length - 1));
		int dp[][] = new int[arr.length][arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(mcmDp(arr, 1, arr.length - 1, dp));
	}

	private static int mcm(int arr[], int i, int j) {
		if (i == j)
			return 0;
		int min = Integer.MAX_VALUE;
		// partition loop
		for (int k = i; k < j; k++) {
			int mul = arr[i - 1] * (arr[k] * arr[j]) + mcm(arr, i, k) + mcm(arr, k + 1, j);
			min = Math.min(min, mul);
		}
		return min;
	}

	private static int mcmDp(int arr[], int i, int j, int dp[][]) {
		if (i == j) {
			dp[i][j] = 0;
			return dp[i][j];
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		int min = Integer.MAX_VALUE;
		// partition loop
		for (int k = i; k < j; k++) {
			int mul = arr[i - 1] * (arr[k] * arr[j]) + mcm(arr, i, k) + mcm(arr, k + 1, j);
			min = Math.min(min, mul);
		}
		dp[i][j] = min;
		return dp[i][j];
	}

}
