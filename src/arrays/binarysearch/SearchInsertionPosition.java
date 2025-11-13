package arrays.binarysearch;

/**
 * <pre>
 * If 't' is present return index
 * if not present insert at the correct place.
 * and shift the elements.
 * And return the index.
 * Use lower bound arr[index] >= t
 * </pre>
 */
public class SearchInsertionPosition {
	public static void main(String[] args) {
		int arr[] = { 1, 2, 4, 7 };
		int t = 6;
		System.out.println(insertPosition(arr, t));
	}

	private static int insertPosition(int arr[], int x) {
		int ans = arr.length;
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] >= x) {
				ans = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return ans;
	}
}
