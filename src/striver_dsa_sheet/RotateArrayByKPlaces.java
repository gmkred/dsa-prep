package striver_dsa_sheet;

public class RotateArrayByKPlaces {

	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 4, 5 };
//		rotateArrayByKPlaces(arr, 2);
//		rotateArrayByKPlaces_better(arr, 6);
		rotateArrayByKPlaces_optimal(arr, 2);
		for (int i : arr) {
			System.out.print(i + " ");
		}
		// {4,5,1,2,3}

	}

	/**
	 * <pre>
	 * 
	 * reverse the 2 halves first and then reverse entire array;
	 * 
	 * 
	 * TC : O(N-K) + O(K) + O(N) = O(2N);
	 * SC : O(1)
	 * </pre>
	 * 
	 */
	private static void rotateArrayByKPlaces_optimal(int arr[], int k) {
		int n = arr.length;
		k = k % n;// if k is larger than array length, then we can reduce it to less than length
					// for less iteration
		reverse(arr, 0, (n - 1) - k);
		reverse(arr, n - k, n - 1);
		reverse(arr, 0, n - 1);

	}

	private static void reverse(int arr[], int low, int high) {
		while (high > low) {
			int temp = arr[low];
			arr[low] = arr[high];
			arr[high] = temp;
			low++;
			high--;
		}

	}

	/**
	 * <pre>
	 * TC : O(K) + O(N-K) + O(K) == O(N+K)
	 * SC : O(K)
	 * 
	 * 
	 * </pre>
	 */
	private static void rotateArrayByKPlaces_better(int arr[], int k) {
		int n = arr.length;
		k = k % n;
		// SC : O(K)
		int[] newArr = new int[k];
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = arr[n - k + i];
		}

		for (int i = n - 1; i >= k; i--) {
			arr[i] = arr[i - k];
		}
		for (int i = 0; i < newArr.length; i++) {
			arr[i] = newArr[i];
		}

	}

	/**
	 * <pre>
	 * Right rotate : start updating from the end of array.
	 * Left rotate : start from the begin.
	 * 
	 * TC : O(K) + O(N)
	 * SC : O(1)
	 * </pre>
	 */
	private static void rotateArrayByKPlaces_bruteForce(int arr[], int k) {
		int n = arr.length;
		int last = -1;
		for (int i = 0; i < k; i++) {
			last = arr[n - 1];
			for (int j = n - 2; j >= 0; j--) {
				arr[j + 1] = arr[j];
			}
			arr[0] = last;
		}
	}

}
