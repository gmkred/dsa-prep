package striver_dsa_sheet.recursions;

public class ReverseAnArrayRecursively {

	public static void main(String[] args) {
		int arr[] = { 1, 2, 3, 4, 5 };
		display(arr);
		reverseAnArray(arr, 0);
		display(arr);
	}

	private static void reverseAnArray(int arr[], int pointer) {

		if (pointer >= arr.length / 2) {
			return;
		}
		reverseAnArray(arr, pointer + 1);
		int temp = arr[pointer];
		arr[pointer] = arr[arr.length - 1 - pointer];
		arr[arr.length - 1 - pointer] = temp;
		return;
	}

	private static void display(int arr[]) {
		for (int i : arr) {
			System.out.println(i + " ");
		}
		System.out.println();
	}
}
