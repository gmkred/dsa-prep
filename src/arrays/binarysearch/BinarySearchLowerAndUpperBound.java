package arrays.binarysearch;

public class BinarySearchLowerAndUpperBound {
	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 3, 7, 8, 9, 9, 9, 11 };
		int x = 1;
		System.out.println(lowerBound(arr, x));
		System.out.println(upperBound(arr, x));
		x = 10;
		System.out.println(lowerBound(arr, x));
		System.out.println(upperBound(arr, x));
	}

	/**
	 * <pre>
	 * 
	 * Lower bound -> smallest index such that 
	 * arr[index] >= x
	 *  0  1  2   3  4    5    6
	  * {3, 5, 8, 15, 19, 19, 19}
	 * x = 8
	 * LB index = 2
	 * x = 9
	 * LB idex = 3
	 * 
	 * x = 16
	 * LB index = 4
	 * 
	 * x = 20
	 * LB = 7, no one is >= 20 so return size
	 * 
	 * TC : O(Log N)
	 * </pre>
	 */
	private static int lowerBound(int arr[], int x) {
		int ans = arr.length;
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] >= x) {
				ans = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return ans;
	}

	/**
	 * <pre>
	 * 
	 * Lower bound -> smallest index such that 
	 * arr[index] > x
	 *  0  1  2   3  4    5    6
	  * {3, 5, 8, 15, 19, 19, 19}
	 * x = 8
	 * LB index = 3 15 > 8
	 * x = 9
	 * LB idex = 3
	 * 
	 * x = 16
	 * LB index = 4
	 * 
	 * x = 20
	 * LB = 7, no one is >= 20 so return size
	 * 
	 * TC : O(Log N)
	 * </pre>
	 */
	private static int upperBound(int arr[], int x) {
		int ans = arr.length;
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] > x) {
				ans = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return ans;
	}

}
