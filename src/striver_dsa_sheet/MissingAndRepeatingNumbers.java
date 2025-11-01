package striver_dsa_sheet;

import java.util.HashMap;
import java.util.Map;

public class MissingAndRepeatingNumbers {
	public static void main(String[] args) {
		int[] arr = { 4, 3, 6, 2, 1, 1 };
//		int res[] = missingAndRepeatingNumbers(arr, 6);
//		int res[] = missingAndRepeatingNumbers_hash(arr, 6);
		int res[] = missingAndRepeatingNumbers_optimal(arr, 6);

		for (int i : res) {
			System.out.println(i + " ");
		}
	}

	/**
	 * <pre>
	 * { 4, 3, 6, 2, 1, 1 }
	 * 
	 * X = repeating number
	 * Y = missing number
	 * 
	 * X - Y = sum of array - S(n)
	 * 
	 * S(n) = n(n+1)/2 = 6*7/2 = 21
	 * sum of array    = 4+3+6+2+1+1 = 17
	 * X - y = 17 - 21
	 * X - y = -4
	 * 
	 * but we cannot solve the equation without knowing X or Y
	 * So, we find S(n^2) and sum of squares of given array numbers.
	 * 
	 * S(n^2) = n*(n+1)(2n+1)/6 = 6*7*13/6 = 91
	 * sum of squares of array = 4^2 + 3^2 + 6^2 + 2^2 + 1^2 + 1^2 
	 *                         = 16 + 9 + 36 + 4 + 1 + 1 
	 *                         = 67
	 * X^2 - Y^2 = 67 - 91
	 * X^2 - Y^2 = -24
	 * (X-Y)*(X+Y) = -24
	 * 
	 * X+Y = -24/(X-Y) = -24 / -4
	 * X + y = 6
	 * 
	 * X - Y = -4
	 * X + y = 6
	 * ----------
	 * 2X = 2
	 * X = 1 --> repeating
	 * 
	 * X-Y = -4
	 * 1-Y = -4
	 * Y = 1+4
	 * Y = 5 --> missing number
	 * 
	 * 
	 * TC : O(N)
	 * SC : O(1)
	 * </pre>
	 */
	private static int[] missingAndRepeatingNumbers_optimal(int arr[], int n) {
		int[] res = new int[2];
		int sumN = (n * (n + 1)) / 2;
		int sumNSquare = (n * (n + 1) * ((2 * n) + 1)) / 6;
		int arraySum = 0;
		int arraysSquareSum = 0;
		int x = 0;
		int y = 0;

		for (int i = 0; i < arr.length; i++) {
			arraySum += arr[i];
			arraysSquareSum += arr[i] * arr[i];
		}
		int xMinusy = arraySum - sumN;
		int xSqMinuYsq = arraysSquareSum - sumNSquare;
		int xPlusy = xSqMinuYsq / xMinusy;

		x = (xMinusy + xPlusy) / 2;
		y = x - xMinusy;
		res[0] = x;
		res[1] = y;
		return res;

	}

	/**
	 * <pre>
	 * TC : O(2N)
	 * SC : O(N)
	 * 
	 * </pre>
	 * 
	 */
	private static int[] missingAndRepeatingNumbers_hash(int arr[], int n) {
		int hash[] = new int[n];
		int[] res = new int[2];

		for (int i = 0; i < arr.length; i++) {
			hash[arr[i] - 1]++;
		}
		for (int i = 1; i < n; i++) {
			if (hash[i - 1] == 0) {
				res[0] = i;
			} else if (hash[i - 1] == 2) {
				res[1] = i;
			}
			if (res[0] != 0 && res[1] != 0) {
				break;
			}
		}

		return res;
	}

	/**
	 * <pre>
	 * TC : O(N^2)
	 * SC : O(2) to store the results
	 * 
	 * </pre>
	 * 
	 */
	private static int[] missingAndRepeatingNumbers(int arr[], int n) {
		int res[] = new int[2];

		for (int i = 1; i <= n; i++) {
			int count = 0;
			for (int j = 0; j < arr.length; j++) {
				if (i == arr[j]) {
					count++;
				}
			}
			if (count == 0) {
				res[0] = i;
			}
			if (count == 2) {
				res[1] = i;
			}
			if (res[0] != 0 && res[1] != 0) {
				break;
			}
		}

		return res;
	}
}
