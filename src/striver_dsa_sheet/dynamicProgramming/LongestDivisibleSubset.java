package striver_dsa_sheet.dynamicProgramming;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * 
 * { 1, 16, 7, 8, 4 }
 * 
 * No need to follow an order.
 * 
 * 
 * pick any 2 elements and if they are divisible then its a count.
 * 
 * 
 * 16%1 = 0
 * 16%8 = 0
 * 
 * find the largest divisible pair.
 * 
 * {1,16,8,4}
 * 
 * Ans can be in any order.
 * 
 * Since its given to to it any order, so we use LIS
 * Sort the array and perform divisibility.
 * {1,4,7,8,16}
 * 
 * {1,4,8,16}
 * 
 * if 8 is divisible by 4 then it is also divisible by 1
 * if 16 is divisible by 8, then it can be divisible by 4 and 1.
 * 
 * 
 * </pre>
 */
public class LongestDivisibleSubset {
	public static void main(String[] args) {
		int arr[] = { 1, 16, 7, 8, 4 };
		Arrays.sort(arr);
		System.out.println(rec(0, -1, arr));
	}

	// 1,4,7,8,16
	private static int rec(int index, int prev, int arr[]) {
		if (index == arr.length) {
			return 0;
		}
		if (prev == -1 || arr[index] % arr[prev] == 0) {
			return 1 + rec(index + 1, index, arr);
		}
		return 0 + rec(index + 1, prev, arr);

	}

	private static List<Integer> rec(int arr[], List<Integer> list) {
		int dp[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			dp[i] = 1;
		}
		int lis = 0;
		int hash[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			for (int prev = 0; prev < i; prev++) {
				// 1 + dp[prev] , here 1 is dp[i] value which should be always 1 untill a max
				// value is found, if we use dp[i] + dp[prev] it will not work becasue we are
				// updating dp[i] value so it will result in wrong ans.
				if (arr[i] % arr[prev] == 0 && 1 + dp[prev] > dp[i]) {
					dp[i] = 1 + dp[prev];
					hash[i] = prev;
				}
			}
			if (lis < dp[i]) {
				lis = dp[i];
			}
		}
		int lisIndex = 0;
		// find the LIS index
		for (int i = 0; i < dp.length; i++) {
			if (dp[lisIndex] < dp[i]) {
				lisIndex = i;
			}
		}
		// go through all the LIS elements using backtracking hash.
		while (lisIndex >= 0 && lis > 0) {
			list.add(arr[lisIndex]);
			lisIndex = hash[lisIndex];
			lis--;
		}
		return list;
	}

}
