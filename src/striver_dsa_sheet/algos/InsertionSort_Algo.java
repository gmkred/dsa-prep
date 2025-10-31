package striver_dsa_sheet.algos;

/**
 * <pre>
 * 
 * 
 * Takes an element and place it in it's correct order
 * 
 * Move the elements until its greater then next element, when it become less than
 * next element, that means it is in its correct order in that iteration.
 * 
 * </pre>
 * 
 */
public class InsertionSort_Algo {

	public static void main(String[] args) {
		int arr[] = { 14, 9, 15, 12, 6, 8, 13 };
		insertionSort(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

	/**
	 * 
	 * TC : N(N+1)/2 = ~= O(N^2)
	 * 
	 * SC : O(1)
	 * 
	 */
	public static void insertionSort(int arr[]) {
		// {9,14,15,12,6,8,13}
		int n = arr.length;
		for (int i = 0; i <= (n - 1); i++) {
			int j = i;
			while (j > 0 && arr[j - 1] > arr[j]) {
				int temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
				j--;
			}

		}
	}
}
