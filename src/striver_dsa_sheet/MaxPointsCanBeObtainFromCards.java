package striver_dsa_sheet;

/**
 * <pre>
 * given an array and k
 * find the max number of points that can be obtained only from the edges and not from the middle
 * 
 * {6,2,3,4,7,2,1,7,1} k =4
 * we can sum 
 * first 4 + last 0
 * first 3 + last 1
 * first 2 + last 2
 * first 1 + last 3
 * first 0 + last 4
 * and find which one is max
 * 
 * </pre>
 */
public class MaxPointsCanBeObtainFromCards {

	public static void main(String[] args) {
		int arr[] = { 6, 2, 3, 4, 7, 2, 1, 7, 1 };
		System.out.println(bruteForce(arr, 4));
		System.out.println(betteroroptimal(arr, 4));

	}

	// TC : O(K^2);
	private static int bruteForce(int[] arr, int k) {
		int max = 0;
		int l = k;
		int r = arr.length - 1;
		while (l > 0) {
			int sum = 0;
			for (int i = 0; i < l; i++) {
				sum = sum + arr[i];
			}
			for (int i = arr.length - 1; i > r; i--) {
				sum = sum + arr[i];
			}
			max = Math.max(max, sum);
			l--;
			r--;
		}

		return max;
	}

	
	/**
	 * <pre>
	 * instead of going for multiple sub loops we can maintain
	 * Lsum and Rsum to store either ends sum.
	 * Example:
	 *  { 6, 2, 3, 4, 7, 2, 1, 7, 1 }
	 *  initially
	 *  Lsum = sum of first k(6+2+3+4), Rsum = sum of last 0 elements ();
	 *  on each iteration, subtract one element value from Lsum and add right end element to Rsum
	 *  Lsum = (6+2+3)		, Rsum = (1)
	 *  Lsum = (6+2)		,Rsum =(1+7)
	 *  Lsum = (6)			,Rsum = (1+7+1)
	 *  Lsum = ()			,Rsum =(1+7+1+2)
	 *  
	 *  each iteration find the max
	 *  
	 * </pre>
	 * */
	//TC : O(K) + O(K) = O(2K)
	private static int betteroroptimal(int[] arr, int k) {
		int max = 0;
		int l = 0, r = arr.length - 1;
		int lsum = 0;
		int rsum = 0;
		for (int i = 0; i < k; i++) {
			lsum = lsum + arr[i];
			max = Math.max(lsum, max);
		}
		l = k - 1;
		while (l >= 0) {
			lsum = lsum - arr[l];
			rsum = rsum + arr[r];
			max = Math.max(max, rsum + lsum);
			l--;
			r--;
		}
		return max;

	}
}
