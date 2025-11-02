package striver_dsa_sheet.recursions;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 
 * Problem statement : 
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 * Only numbers 1 through 9 are used.
 * Each number is used at most once.
 * Return a list of all possible valid combinations. 
 * The list must not contain the same combination twice, and 
 * the combinations may be returned in any order.
 * 
 * Example
 * Input: k = 3, n = 9
 * Output: [[1,2,6],[1,3,5],[2,3,4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 * 
 * 
 * </pre>
 * 
 */
public class CombinationSum3 {
	public static void main(String[] args) {
		int k = 3;
		int n = 9;
		System.out.println(combinationSum3(k, n));
	}

	public static List<List<Integer>> combinationSum3(int k, int n) {
		int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		List<List<Integer>> lists = new ArrayList();
		combinationRecursion(arr, k, n, lists, new ArrayList(), 0, 0);
		return lists;
	}

	private static void combinationRecursion(int arr[], int comblen, int target, List<List<Integer>> lists,
			List<Integer> combination, int index, int sum) {
		// if combination length == k, then check if the sum == target,
		// if met then dd to lists and return, if not et also return because
		// going further it will be having more than 3 digits.
		if (comblen == combination.size()) {
			if (sum == target) {
				lists.add(new ArrayList(combination));
			}
			return;
		}
		if (index == arr.length) {
			return;
		}

		// include
		int curVal = arr[index];
		// if the curVal adding to sum becomes greater than the target, tehn we dont
		// need it.
		/**
		 * <pre>
		 * target 9 but with 3 digit combination
		 * 
		 * 1 ,2, 6 = 9, after this if we increase to ,8,9 it will only results in >9 so return when we find the target and sum == 3.
		 * it goes till recursion which started from 2, after excluding the 2
		 * 1, 3 ...5 = 9, after this return.
		 * it goes till recursion which started from 1, exclude 1 and start from 2
		 * 2, 3, 4 =9, after this return.
		 * it goes till recursions started from 4,
		 * 
		 * 2, 3 since 4 is excluded, there is not sum which gives 9,
		 * it goes till recursion started from 3,
		 * after this there will be no combination which gives 9 so only 3 combinations are present.
		 * </pre>
		 */
		if (sum + curVal <= target) {
			sum += curVal;
			combination.add(curVal);
			combinationRecursion(arr, comblen, target, lists, combination, index + 1, sum);
			sum -= curVal;
			combination.remove(combination.size() - 1);
			// exclude
			combinationRecursion(arr, comblen, target, lists, combination, index + 1, sum);
		}
		return;
	}
}
