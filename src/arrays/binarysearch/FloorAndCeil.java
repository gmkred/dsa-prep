package arrays.binarysearch;

/**
 * <pre>
 * 
 * floor -> largest number in the array <= x
 * Ceil -> smallest number in the array >= x
 * 
 * arr[] = {10,20,30,40,50}
 * 
 * x = 25
 * 
 * floor = 20 - reverse lower bound
 * ceil = 30 - lower bound
 * 
 * 
 * arr[] = {10,20,30,40,50}
 * 
 * X = 20
 * floor = 20
 * ceil = 20
 * </pre>
 */
public class FloorAndCeil {
	public static void main(String[] args) {
		int arr[] = { 10, 20, 30, 40, 50 };
		int x = 25;
		System.out.println(floor(arr, x));
		System.out.println(ceil(arr, x));
		x = 20;
		System.out.println(floor(arr, x));
		System.out.println(ceil(arr, x));
	}

	private static int floor(int arr[], int x) {
		int ans = -1;
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] <= x) {
				ans = arr[mid];
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return ans;
	}

	private static int ceil(int arr[], int x) {
		int ans = -1;
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] >= x) {
				ans = arr[mid];
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return ans;
	}

}
