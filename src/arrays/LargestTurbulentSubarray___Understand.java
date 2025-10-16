package arrays;

/**
 * A turbulent array is an array where
 * 
 * 
 * Still did not understood
 * 
 */

public class LargestTurbulentSubarray___Understand {

	public static void main(String[] args) {

		int arr[] = { 9, 4, 2, 10, 7, 8, 8, 1, 9 };
		int result = maxTurbulenceSize(arr);
		System.out.println(result);
	}

	public static int maxTurbulenceSize(int[] arr) {
		int p = 0, q = 0;
		int turSize = 0;
		if (arr.length == 1) {
			return 1;
		}
		while (q < arr.length - 1) {
			if (q % 2 == 0) {
				if (arr[q] < arr[q + 1]) {
					turSize++;
				} else {
					p = q;
					turSize = 0;
				}
			} else {
				if (arr[q] > arr[q + 1]) {
					turSize++;
				} else {
					p = q;
					turSize = 0;
				}
			}
			q++;
		}
		return turSize;
	}
}
