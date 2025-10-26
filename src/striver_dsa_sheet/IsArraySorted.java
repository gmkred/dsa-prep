package striver_dsa_sheet;

public class IsArraySorted {

	public static void main(String[] args) {

		int arr[] = { 1, 2, 2, 3, 4, 5, 6 };
		int arr2[] = { 3, 2, 5, 4 };
		System.out.println(isArraySorted(arr));
		System.out.println(isArraySorted(arr2));
	}

	private static boolean isArraySorted(int arr[]) {

		for (int i = 1; i < arr.length; i++) {
			if (arr[i - 1] > arr[i]) {
				return false;
			}
		}
		return true;
	}
}
