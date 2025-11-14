package arrays.binarysearch;

import java.util.Arrays;

/**
 * <pre>
 * 
 * BS on min of max or max of min
 * 
 * Aggresive cows : (Min dist between cows) is max
 * arr[] = {0, 3, 4, 7, 10, 9}
 * N = stalls
 * cows = 4
 * 
 * 
 * place 4 cows in such a way that the minimum distance between any of the 2 cows is the maximum possible.
 * {0,  3,  4,  7,  9, 10}
 *  c1  c2  c3  c4
 *  
 *  c1 -> c2 = 3
 *  c2 -> c3 = 1
 *  c3 -> c4 = 3
 *  
 *  Min dist = 1
 *  
 * {0,  3,  4,  7,  9,  10}
 *  c1      c2      c3  c4
 *  
 *  c1 -> c2 = 4
 *  c2 -> c3 = 5
 *  c3 -> c4 = 1
 * 
 * min dist = 1
 * 
 * {0,  3,  4,  7,  9,  10}
 *  c1      c2  c3      c4
 *  
 *  c1 -> c2 = 4
 *  c2 -> c3 = 3
 *  c3 -> c4 = 3
 * 
 * min dist = 3
 * 
 * max of min = 3
 * 
 * conditions:
 * all cows must be placed
 * 
 * 
 * Lets apply binary search
 * 
 * {0,  3,  4,  7,  9,  10}
 * 
 * [0................33]
 * low = 0
 * high = 33
 * mid = 0+33 = 33/2 = 16
 * 
 * all arr[i] > 16 so shrink high
 * 
 * [0........15]
 * 
 * low =0
 * high = 15
 * mid = 15/2 = 7
 * 
 * 
 * 
 * 
 * </pre>
 */
public class AggresiveCows {
	public static void main(String[] args) {
		int arr[] = { 0, 3, 4, 7, 10, 9 };
		Arrays.sort(arr);
		int cows = 4;
		System.out.println(bruteForce(arr, cows));
		System.out.println(binarySearcApproach(arr, cows));
	}

	/**
	 * <pre>
	 * Try taking a min variable
	 * starting with 1
	 * min =1,
	 * find to place all cows with minimum of min distance, if yes,
	 * try increasing min to 2 and see again if all cows can be placed
	 * if yes again increase min to 3 and see if all cows can be place with min distance 3
	 * do it until, if one cow cannot be places.
	 * 
	 * The last min value will be the minumn distance.
	 * 
	 * TC : O(max-min)*O(N)
	 * SC : O
	 * 
	 * </pre>
	 */
	private static int bruteForce(int arr[], int cows) {
		int min = 0;
		boolean flag = true;
		while (flag) {
			int placedCows = 1;
			int lastCowPlace = 0;
			for (int i = 1; i < arr.length; i++) {
				if (arr[i] - arr[lastCowPlace] >= (min + 1)) {
					placedCows++;
					min++;
					lastCowPlace = i;
				}
			}
			flag = !(placedCows == cows);
		}
		return min;
	}

	/**
	 * <pre>
	 * if cows = 2;
	 * 
	 * min possible can be 1
	 * max possible can be arr[len-1]-arr[0]
	 * max(1,arr[len-1]-arr[0])
	 * 
	 * {0,  3,  4,  7,  9,  10}
	 * 
	 * [0..............10]
	 * l=0
	 * h=10
	 * m=10/2 =5
	 * 
	 * 
	 * TC : N log N (sorting) + O(log len)*O(N)
	 * SC : O(1)
	 * </pre>
	 */
	private static int binarySearcApproach(int arr[], int cows) {
		int l = arr[0];
		int h = arr[arr.length - 1];
		while (l <= h) {
			int m = (l + h) / 2;
			if (!isAllCowsPlaced(arr, cows, m)) {
				h = m - 1;
			} else {
				l = m + 1;
			}
		}
		return l;
	}

	private static boolean isAllCowsPlaced(int arr[], int cows, int dis) {
		int placedCows = 1;
		int lastCowPlace = 0;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] - arr[lastCowPlace] >= (dis)) {
				placedCows++;
				lastCowPlace = i;
			}
			if (lastCowPlace == cows) {
				return true;
			}
		}
		return false;
	}
}
