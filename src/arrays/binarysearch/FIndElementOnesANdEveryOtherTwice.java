package arrays.binarysearch;

public class FIndElementOnesANdEveryOtherTwice {
	public static void main(String[] args) {
		int arr[] = { 1, 1, 2, 3, 3, 4, 4, 8, 8 };
		System.out.println(binarySearch(arr));
		arr = new int[] { 1, 1, 2, 2, 4, 4, 8, 8, 9 };
		System.out.println(binarySearch(arr));
	}

	// do a XOR TC : O(N)
	private static void bruteForce(int arr) {

	}

	/**
	 * <pre>
	 * every twice element is at odd and even index
	 * L = 0
	 * H = last but one which will be a odd number
	 *   0  1  2  3  4  5  6  7  8
	 * { 1, 1, 2, 3, 3, 4, 4, 8, 8 }
	 *   L        M           H   
	 *   L  M  H
	 *         L
	 *         H
	 *         M
	 *      H when H corsses L, then return L that will be the single element.
	 *   If M is at odd, check right element that should be same to shrink left
	 *   else shrink right
	 *   If M is at even, check left element should be same to shrink left
	 *   else shrink right
	 *   0  1  2  3  4  5  6  7  8
	 * { 1, 1, 2, 2, 4, 4, 8, 8, 9 }  
	 *   L        M           H
	 *               L  M     H
	 *                     L  H
	 *                     M
	 *                        L
	 *                        H
	 *                        M 
	 *                           L  now L crosses M and that is the single element.
	 *               
	 * initially place high at last but one index.
	 * 
	 * 
	 * 
	 * </pre>
	 */
	private static int binarySearch(int arr[]) {

		int low = 0;
		int high = arr.length - 2;

		while (low <= high) {
			int mid = (low + high) / 2;
			/**
			 * <pre>
			 * mid^1
			 * 
			 * if mid is odd, it gives previous even,
			 * if mid is even, it gives next odd,
			 * 
			 * mid = 4
			 * 0100
			 * 0001
			 * ----
			 * 0101 = 5
			 * 
			 * mid = 3
			 * 
			 * 0011
			 * 0001
			 * ----
			 * 0010 = 2
			 * 
			 * </pre>
			 * 
			 */
			if (arr[mid] == arr[mid ^ 1]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return arr[low];
	}

}
