package striver_dsa_sheet;

/**
 * Everything right side of an element should be smaller.
 * 
 * {10,22,12,3,0,6}
 * 
 * 22,12 are leaders
 * 
 */
public class LeadersInArray {

	public static void main(String[] args) {
		int arr[] = { 10, 22, 12, 3, 0, 6 };
		leadersofArray(arr);
		leadersOfArray_optimal(arr);

	}

	/**
	 * TC : O(N) SC : O(1)
	 */
	private static void leadersOfArray_optimal(int arr[]) {
		int max = Integer.MIN_VALUE;
		for (int i = arr.length - 1; i > 0; i--) {
			if (arr[i] > max) {
				max = arr[i];
				System.out.print(arr[i] + " ");
			}
		}
		// reverse if requirement is to give in order.
		System.out.println();
	}

	/**
	 * <pre>
	 * TC : O(N*(N+1/2))
	 * SC : O(1)
	 * 
	 * </pre>
	 */
	private static void leadersofArray(int arr[]) {

		for (int i = 0; i < arr.length; i++) {
			boolean isLeader = true;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] < arr[j]) {
					isLeader = false;
				}
			}
			if (isLeader)
				System.out.print(arr[i] + " ");
		}
		System.out.println();

	}
}
