package arrays.binarysearch;

/**
 * <pre>
 * Binary search 
 * Searching in a limited search space.
 * 
 * Real life examples
 *  Dictionary ABCD...Z
 *  FInd out letter R,just open some page and see the letter, if
 *  letter is before R then turn right side or turn left side pages until R is found.
 *  This is how binary search works.
 *  Search space must be sorted to apply binary search
 * 
 * 
 * 
 * 
 * </pre>
 */
public class BinarySearch {
	public static void main(String[] args) {
		int arr[] = { 3, 4, 6, 7, 9, 12, 16, 17 };
		int t = 17;
		System.out.println(binarySearch(arr, t));
		System.out.println(binarySearchRec(arr, t, 0, arr.length - 1));
	}

//TC : O(log n)
	private static int binarySearch(int arr[], int t) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] == t) {
				return mid;
			} else if (t > arr[mid]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}

	// TC : O(log n)
	// sc : O(N) auxiliary space.
	private static int binarySearchRec(int arr[], int t, int low, int high) {
		if (low > high) {
			return -1;
		}
		int mid = low + (high - low) / 2;
		if (arr[mid] == t) {
			return mid;
		} else if (t > arr[mid]) {
			return binarySearchRec(arr, t, mid + 1, high);
		} else {
			return binarySearchRec(arr, t, low, mid - 1);
		}
	}
	/**
	 * <pre>
	 * 
	 * imagine the search space is 0 to MAX_VALUE
	 * 
	 * mid = (MAX_VALUE+MAX_VALUE)/2
	 * now it overflows the max integer value
	 * so always do [low + (high-low)/2]
	 * 
	 * 3 to 7
	 * 
	 * (3+7)/2 = 5
	 * or
	 * 3 + (7-3)/2 = 3 + 4/2 = 3+2 = 5
	 * 
	 * </pre>
	 */
}
