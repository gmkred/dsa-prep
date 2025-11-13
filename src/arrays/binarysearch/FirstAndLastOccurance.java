package arrays.binarysearch;

/**
 * <pre>
 *          0 1 2 3 4 5  6 7 
 * arr[] = {2,4,6,8,8,8,11,13}
 * x = 8;
 * first = 3
 * last = 5
 * 
 * </pre>
 * 
 */
public class FirstAndLastOccurance {
	public static void main(String[] args) {

		int arr[] = { 2, 4, 6, 8, 8, 8, 11, 13 };
		int x = 8;
		int ans[] = firstAndLast(arr, x);
		for (int i : ans) {
			System.out.print(i + " ");
		}
		System.out.println();
		// O(2 * log N)
		System.out.println(firstOccurenclowerBound(arr, x) + " " + (LastOccurencUpperBound(arr, x) - 1));
		// edge cases if x is not present in the array so it returns an index which has
		// >x value, if LB itself out of index then return -1
		// another edge case check (upperbound -1) index contains x or else x is not
		// present in array.
		ans = binarySearch(arr, x);
		if (ans[0] != -1 && ans[1] != -1) {
			for (int i : ans) {
				System.out.print(i + " ");
			}
		}
	}

	// TC : O(N)
	private static int[] firstAndLast(int arr[], int x) {
		int ans[] = { -1, -1 };
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == x) {
				if (ans[0] == -1) {
					ans[0] = i;
				} else {
					ans[1] = i;
				}
			}
		}
		return ans;
	}

	private static int firstOccurenclowerBound(int arr[], int x) {
		int ind = arr.length;

		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] >= x) {
				ind = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return ind;
	}

	private static int LastOccurencUpperBound(int arr[], int x) {
		int ind = arr.length;
		int low = 0;
		int high = arr.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] > x) {
				ind = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return ind;
	}

	private static int[] binarySearch(int arr[], int x) {
		int first = -1;
		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] == x) {
				first = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		int second = -1;
		if (first != -1) {
			for (int i = first + 1; i < arr.length; i++) {
				if (arr[i] == x) {
					second = i;
				} else {
					break;
				}
			}
		}
		return new int[] { first, second };

	}

}
