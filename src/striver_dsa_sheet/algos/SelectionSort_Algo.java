package striver_dsa_sheet.algos;

/**
 * <pre>
 * What do we select?
 * we select minimums
 * 
 * FInd the minimum and swap with the first element.
 * and do for all the consecutive elements from the beginning.
 * 
 * Get the minimum and swap it.
 * 
 * TC : O(N(N+1)/2) = O((N^2)/2) ~= O(N^2) 
 * SC : O(1)
 * 
 * </pre>
 * 
 */
public class SelectionSort_Algo {

	public static void main(String[] args) {
		int arr[] = { 13, 46, 24, 52, 20, 20, 9 };
		selectionSort(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

	public static void selectionSort(int arr[]) {
		int temp = 0;
		for (int i = 0; i < arr.length; i++) {
			int minIndex = i;
			for (int j = i; j < arr.length; j++) {
				if (arr[minIndex] > arr[j]) {
					minIndex = j;
				}
			}
			temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}
}
