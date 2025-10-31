package striver_dsa_sheet.algos;

/**
 * <pre>
 * pushes the max to the last by adjacent swapping.
 * 
 * TC : N(N+1)/2 ~= O(N^2) worst case
 * TC : O(N) = best case if there are no swaps in inner loop. Linear TC.
 * SC : O(1)
 * </pre>
 */
public class BubbleSort_ALgo {

	public static void main(String[] args) {
		int arr[] = { 13, 46, 24, 52, 20, 9 };
		bubbleSOrt(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

	/**
	 * <pre>
	 * Adjacent swapping
	 * 
	 * { 13, 46, 24, 52, 20, 9 }
	 * 
	 * First outer iteration : multiple inner iteration
	 * { 13, 46, 24, 52, 9, 20 }
	 * continue as 13 < 46
	 * { 13, 24, 46, 52, 20, 9 }
	 * continue as 24 < 46 
	 * { 13, 24, 46, 20, 52, 9 }
	 * { 13, 24, 46, 20, 52, 9 }
	 * { 13, 24, 46, 20, 9, 52 }
	 * after first iteration the largest values has reached at end, so next loop should run
	 * till 4th index.
	 * </pre>
	 */
	public static void bubbleSOrt(int arr[]) {
		int n = arr.length;
		// if on the first inner loop,
		// if i don't find any swaps then it already in sorted order.
		int swapCount = 0;
		for (int i = (n - 1); i > 0; i--) {
			for (int j = 0; j <= i - 1; j++) {
				if (arr[j] >= arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapCount++;
				}
			}
			if (swapCount == 0) {
				break;
			}
		}
	}
}
