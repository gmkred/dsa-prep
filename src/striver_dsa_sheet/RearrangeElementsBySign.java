package striver_dsa_sheet;

/**
 * <pre>
 *  Given an array with even length, contains N/2 of positives and
 * negatives. Rearrange them in a way that pos neg pos neg pos neg
 * 
 * 
 * {1, 2, -2, 6, -3, -9}
 * 
 * res 
 * {1, -2, 2, -3, 6, -9}
 * 
 * </pre>
 */
public class RearrangeElementsBySign {
	public static void main(String[] args) {
		int arr[] = { 1, 2, -2, 6, -3, -9 };
		rearrangeElementsBySign(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

	/**
	 * <pre>
	 * TC : O(2N)
	 * SC : O(N)
	 * 
	 * </pre>
	 */
	private static void rearrangeElementsBySign(int arr[]) {

		int pos = 0;
		int neg = 1;
		int arr2[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {

			if (arr[i] >= 0) {
				arr2[pos] = arr[i];
				pos += 2;
			} else {
				arr2[neg] = arr[i];
				neg += 2;
			}

		}
		for (int i = 0; i < arr2.length; i++) {
			arr[i] = arr2[i];
		}
	}
}
