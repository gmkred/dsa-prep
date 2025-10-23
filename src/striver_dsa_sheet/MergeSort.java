package striver_dsa_sheet;

public class MergeSort {

	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 4, 2, 5, 3, 1, 6, 5, 4, 2, 4, 2, 1, 7 };
		mergeSort(arr);
	}

	public static void mergeSort(int arr[]) {

		int left = 0;
		int right = arr.length - 1;

		mergeSort(arr, left, right);

	}

	public static void mergeSort(int arr[], int left, int right) {

		if (right <= left) {
			return;
		}

		int mid = (left + (right - left) / 2);

		mergeSort(arr, left, mid);
		mergeSort(arr, mid + 1, right);

	}

	public static void merge(int left[], int right[], int l, int r) {
		System.out.println();
	}

}
