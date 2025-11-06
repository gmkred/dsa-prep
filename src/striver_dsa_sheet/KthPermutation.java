package striver_dsa_sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * <a href=
"https://leetcode.com/problems/permutation-sequence/">Permutation sequence</a>
 * Given n and k
 * n = 4 {1,2,3,4}
 * k = 18, find 18th permutation or 17th index permutation.
 * list {1, 2, 3, 4}
 * 
 * 0th  [1, 2, 3, 4] ^
 * 1st  [1, 2, 4, 3] |
 * 2nd  [1, 3, 2, 4] | 0th part
 * 3rd  [1, 3, 4, 2] |
 * 4th  [1, 4, 2, 3] |
 * 5th  [1, 4, 3, 2] v
 * 6th  [2, 1, 3, 4] ^
 * 7th  [2, 1, 4, 3] |
 * 8th  [2, 3, 1, 4] | 1st part
 * 9th  [2, 3, 4, 1] |
 * 10th [2, 4, 1, 3] |
 * 11th [2, 4, 3, 1] v
 * 12th [3, 1, 2, 4] ^
 * 13th [3, 1, 4, 2] |
 * 14th [3, 2, 1, 4] | 2nd part
 * 15th [3, 2, 4, 1] |
 * 16th [3, 4, 1, 2] |
 * 17th [3, 4, 2, 1] v
 * 18th [4, 1, 2, 3] ^
 * 19th [4, 1, 3, 2] |
 * 20th [4, 2, 1, 3] | 3rd part
 * 21st [4, 2, 3, 1] |
 * 22nd [4, 3, 1, 2] |
 * 23rd [4, 3, 2, 1] v
 * 
 * 
 * n = 4 = 4! = 24.
 * 
 * 24 / n = 24 / 4 = 6, 
 * each number with beginning has 6 permutations 
 * so there are 4 parts with 6 permutations having same number in the beginning for each part.
 * 
 * 0 to 5 starts with 1
 * 6 to 11 starts with 2
 * 12 to 17 starts with 3
 * 18 to 23 starts with 4
 * 
 * since we need to find 18th perm or 17th index, 
 * 17 / 6 = 2, this means, the 17th perm lies in 2nd part, which begins with 3.
 * so 17th perm starts with 2nd index {3}
 * 
 * 17th per = {3,_,_,_}
 * list = {1, 2, 4}
 * Now, we need to find the next value, but each 2nd part has 6 permutations that starts with 3.
 * so we need to find exact permutation we want, for that  do 
 * 17 % 6 = 5.
 * 5 is the reminder, which is the last permutation of 2nd part. 
 * ------------------------------------------------------ 
 * 
 *     0th part
 * 0 {1,2,4}
 * 1 {1,4,2}
 *     1st part
 * 2 {2,1,4}
 * 3 {2,4,1}
 *     2nd part
 * 4 {4,1,2}
 * 5 {4,2,1}
 * Assume 
 * n = 3
 * k = 6th permutation or 5th index permutation.
 * 
 * now, we need to perform same operation on {1,2,4}, 3 is already taken.
 * n! = 3! 
 * 6/n = 6/3 = 2,
 * each number with beginning has 2 permutations.
 * k/2 = 5/2 = 2nd part of the permutations
 * 
 * that means the 17th index perm {3,4,_,_}
 * list = {1,2}
 * ------------------------------------------------------ 
 * next k = 5 % 2 = 1st index or 2nd permutation.
 * 
 * n = 2!
 * 2/n = 2/2 = 1, 
 * each number with beginning has 1 permutation.
 * k = 1
 * k/n = 1/1 = 1, 1st index or 2nd permutation.
 * 0th part
 * {1,2}
 * 1st part
 * {2,1}
 * 
 * 17th index permutation {3,4,2,}
 * list {1}
 * ------------------------------------------------------
 * k = 1 % 1 
 * k = 0, 0th index or 1st permutation
 * n = 1! = 1
 * 1/n = 1/1 = 1, only one permutation 
 * k/n = 0/1 = 0, 0th index or 1 st permutation.
 * {1}
 * 
 * 17th index permutation {3,4,2,1}
 * list {}
 * 
 * 
 * </pre>
 * 
 */
public class KthPermutation {

	public static void main(String[] args) {
		int k = 18;
		int n = 4;
		// 0 based indexing of each permutation
		k = k - 1;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(i + 1);
		}
		int fac = 1;
		for (int i = 2; i <= n; i++) {
			fac *= i;
		}
		System.out.println(kthPermutation(n, k, list, "", fac));
	}

	private static String kthPermutation(int n, int k, List<Integer> list, String ans, int fac) {

		if (n == 0) {
			return ans;
		}

		int eachPartLength = fac / n;
		// 0 based indexing. because we need to take value from list where 1st value is
		// in 0th index.
		int PartsFirstValue = k / eachPartLength;
		// since the 1st value will be same in this entire part so we can take it.
		ans += list.get(PartsFirstValue);
		// remove
		list.remove(PartsFirstValue);
		// once we take the 1st value, we need to find the given Kth sencond value.
		// to find 2nd vaue, we need to find which part of the (n-1) permutations does
		// it belongs to
		// by doing K % current part length
		return kthPermutation(n - 1, k % eachPartLength, list, ans, fac / n);
	}
}
