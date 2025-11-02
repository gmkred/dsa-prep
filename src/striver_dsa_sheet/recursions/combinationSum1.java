package striver_dsa_sheet.recursions;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 
 * Problem statement : find the sub sequence combination wose sum == target, duplicates are allowed.
 * 
 * {2,3,6,7} target = 7
 * duplicates allowed
 * sub sequences with sum 7
 * 
 * {2,2,3} - 2 is taken twice.
 * {7}
 * 
 * are the possible sub sequence with sum 7
 * 
 * To achieve this with recursion.
 * We start taking the 0th index multiple times as duplicates are allowed.
 * But before considering a value, we need to check if that value added to the sum makes the
 * sum > target, if yes we does not consider it and exclude it and move to next index value.
 * we do the same for all the indexes until the the index == length.
 * then we compare the sum == target, 
 * if yes add it to list.
 * 
 * Thsi way we can consider the duplicates and find the sub sequences of sum K.
 * 
 * Explanation :
 * <a href=
"https://github.com/gmkred/preparation-files/blob/master/sub%20sequence%20sum%20%3D%20target%20duplicates%20allowed.whiteboard">white board flow explanation</a>
 *  0  1  2  3
 * {2, 3, 6, 7} target = 7
 * 
 * include 0th index {2}  2 <= 7 true
 * 0th
 * before including 0th again, check if sum will be <=target, sum =4 <= target so include
 *     0th
 *     include 0th index {2, 2}  4 <= true.
 *        0th
 *        include 0th index {2, 2, 2} sum = 6.
 *        now, if we try including 0th again {2,2,2,2} sum = 8 which is > 7  we should not include this,
 *        so exclude current 0th index. {2,2,2} <= 7 true.
 *        when we exclude, we do (index+1)
 *        1st
 *        now next recursion starts from 1st index.{2,2,2}
 *        before including 1st index value check sum {2,2,2,3} <= 7 false
 *        so exclude this and move to next index = 2nd {2,2,2}
 *        2nd
 *        before including 2nd index value check sum {2,2,2,6} <= 7 false,
 *        so exclude this and move to next index = 3rd {2,2,2}
 *        3rd
 *        before including 3rd index value, check sum {2,2,2,7} <= 7 false.
 *        so exclude this and move to next index = 4th {2,2,2}
 *        4th
 *        since 4 is >= length, its the end so chec the sum == target, 6 == 7 false so its not a combination.
 *        4th return {2,2,2}
 *        3rd return {2,2,2}
 *        2nd return {2,2,2}
 *        1st return {2,2,2}
 *        0th exclude {2,2}
 *          1st include {2,2,3} <= 7 true
 *           1st include {2,2,3,3} <= 7 false, so exclude this 1st index.
 *           1st exclude {2,2,3} <=7 true.
 *             2nd include {2,2,3,6} <= false, so exclude this 2nd index.
 *             2nd exclude {2,2,3} <= 7 true
 *              3rd include {2,2,3,7} <= false, so exclude this 3rd index.
 *              3rd exclude {2,2,3} <= 7 true.
 *                4th >= length so check 2+2+3 == 7 true so this is a combination. {2,2,3}
 *                4th return {2,2,3}
 *              3rd return {2,2,3}
 *             2nd return {2,2,3}
 *           1st return {2,2,3}
 *          1st exclude {2,2}
 *           2nd include {2,2,6} <= 7 false so exclude
 *           2nd exclude {2,2}
 *            3rd include {2,2,7} <= 7 false so exclude
 *            3rd exclude {2,2} <= 7 true
 *             4th >= leangth check 2+2 == 7 false, its not a combination {2,2}
 *             4th return {2,2}
 *            3rd return {2,2}
 *           2nd return {2,2}
 *          1st return {2,2}
 *         0th exclude {2} <=7
 *          1st include {2,3} <= 7 true
 *           1st include {2,3,3} <= 7 false so exclude
 *           1st exclude {2,3} <=7 true
 *             2nd include {2,3,6} <= 7 fasle so exclude
 *             2nd exclude {2,3} <= true
 *              3rd include { 2,3,7} <= 7 fasle so exclude
 *              3rd exclude {2,3}
 *               4th  2+3 == 7 false so its not a combination
 *              3rd return {2,3}
 *             2nd return {2,3}
 *            1st return {2,3}
 *           1st exclude {2}
 *   This is how it continues.
 * 
 * TC : O(2^t * K)
 * Its exponential.
 * assume t = 10;
 * and {1,2,3}
 * 
 * so 1 is included for 10 times and excluded for 10 times;
 * so for every element the wrost cas would be
 * 2 ^ 10
 * 2 ^ t
 * for k is average length.
 * 
 * SC : K(average length) * X(combination) but its impossible to predict.
 * </pre>
 */
public class combinationSum1 {
	public static void main(String[] args) {
		int arr[] = { 2, 3, 6, 7 };
		int target = 7;
		List<List<Integer>> lists = new ArrayList<>();
		findSubSequneces_Sum_Equals_Target(arr, target, 0, 0, new ArrayList(), lists);
		System.out.println(lists);

	}

	private static void findSubSequneces_Sum_Equals_Target(int arr[], int target, int index, int sum,
			List<Integer> combination, List<List<Integer>> lists) {

		// base condition
		if (index == arr.length) {
			if (sum == target) {
				lists.add(new ArrayList(combination));
			}
			return;
		}

		/**
		 * first we try to include same value until it is > target, them we move to next
		 * index to check if that value makes the sum == target.
		 * 
		 */

		// step 2 : but before addign a value check if the sum <= target, so we can
		// ignore adding the values which results in sum > target

		int curValue = arr[index];
		if (sum + curValue <= target) {
			// step 1 : add the same value
			sum += curValue;
			combination.add(curValue);
			findSubSequneces_Sum_Equals_Target(arr, target, index, sum, combination, lists);
			// on returning from recursion, remove the current value form sum and
			// combination.
			sum -= curValue;
			combination.remove(combination.size() - 1);
		}

		// at any point sum > target, we should exclude the curValue and move to next
		// index,
		// do same steps until the end and find the combination. there can be or can not
		// be a combination.

		findSubSequneces_Sum_Equals_Target(arr, target, index + 1, sum, combination, lists);
		return;

	}
}
