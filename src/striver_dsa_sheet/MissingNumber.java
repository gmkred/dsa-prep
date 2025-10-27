package striver_dsa_sheet;

public class MissingNumber {

	public static void main(String[] args) {
		int arr[] = { 3, 0, 1 };
		int missingNumber = findMissingNumber(arr);
		System.out.println(missingNumber);
		System.out.println(findMissingNumber_optimal(arr));
		System.out.println(findMissingNumber_optimal(arr));
		System.out.println(findMissingNumber_optimal_XOR(arr));
	}

	/**
	 * <pre>
	 * 1^1 = 0
	 * 2^2 = 0
	 * 2^2^2 = 2
	 * 1^1^2^2^3^3 = 0
	 * 
	 * same number will give 0, if any extra number, it just returns that number.
	 * 
	 * 2 ^ 2 ^ 3 ^ 4 ^ 1   =  6
	 * 2	0010
	 * 2	0010
	 * 3	0011
	 * 4	0100
	 * 1	0001
	 * res	0110         =  6
	 * 
	 * TC : O(N)
	 * SC : (1) but the sum will not exceed 10^6 if XOR is used. whihc is better than optimal 1 solution
	 * 
	 * </pre>
	 */
	private static int findMissingNumber_optimal_XOR(int arr[]) {
		int xor1 = 0;
		int xor2 = 0;

		for (int i = 0; i < arr.length; i++) {
			xor1 = xor1 ^ arr[i];
			xor2 = xor2 ^ i;
		}
		xor2 = xor2 ^ arr.length;

		return xor1 ^ xor2;

	}

	/**
	 * <pre>
	 * 
	 * TC : O(N)
	 * SC : O(1)
	 * 
	 * 
	 * </pre>
	 */
	private static int findMissingNumber_optimal(int[] arr) {
		int sum = 0;
		int n = arr.length;
		for (int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];
		}

		return n * (n + 1) / 2 - sum;

	}

	/**
	 * <pre>
	 * TC : O(2N)
	 * SC : O(N)
	 * 
	 * </pre>
	 */
	private static int fidingMissingNumber_better(int arr[]) {
		int[] hash = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			hash[arr[i]]++;
		}
		int missingNUmber = -1;
		for (int i = 0; i < hash.length; i++) {
			if (hash[i] == 0) {
				missingNUmber = i;
				break;
			}
		}
		return missingNUmber;
	}

	/**
	 * <pre>
	 * 
	 * TC : O(N^2)
	 * SC: O(1)
	 * </pre>
	 * 
	 */
	private static int findMissingNumber(int[] arr) {
		int missingNumber = -1;
		// arr contains one less number which is missing. so check each number from 0 to
		// arr.length (included)
		for (int i = 0; i <= arr.length; i++) {
			int count = 0;
			for (int j = 0; j < arr.length; j++) {
				if (i == arr[j]) {
					count++;
				}
			}
			if (count == 0) {
				missingNumber = i;
				break;
			}
		}

		return missingNumber;
	}
}
