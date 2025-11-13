package arrays.binarysearch;

/**
 * <pre>
 * Unique elements.
 * arr[] = {7, 8, 9, 1, 2, 3, 4, 5, 6}
 * 
 * int t =1;
 * 
 * </pre>
 */
public class SearchElementInRotatedSortedArray {
	public static void main(String[] args) {
		int arr[] = { 7, 8, 9, 1, 2, 3, 4, 5, 6 };
		int t = 1;
		System.out.println(binarySearch(arr, t));
	}

	// TC : O(N)
	private static int linearSearch(int arr[]) {
		return 0;

	}

	private static int binarySearch(int arr[], int target) {
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] == target) {
				return mid;
			}
			// left side if sorted
			if (arr[low] <= arr[mid]) {
				// is target present in left side
				if (arr[low] <= target && target <= arr[mid]) {
					// right is eliminated.
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			} else {
				// is target present in right side
				if (target >= arr[mid] && arr[high] >= target) {
					// left is eliminated.
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
		}
		return -1;

	}
}
