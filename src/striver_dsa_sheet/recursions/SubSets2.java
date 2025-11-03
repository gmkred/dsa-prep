package striver_dsa_sheet.recursions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * 
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 
 * 
 * Since at every level the code tries to create unique subsets, adding them to lists will give the answer.
 * 
 * </pre>
 */
public class SubSets2 {
	public static void main(String[] args) {
		int arr[] = { 1, 2, 2 };
		System.out.println(subsetsWithDup(arr));
	}

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> lists = new ArrayList();
		recursion(nums, 0, lists, new ArrayList());
		return lists;
	}

	private static void recursion(int arr[], int index, List<List<Integer>> lists, List<Integer> combination) {
		// add each combination, as each combiantion will be unique and distinct.
		// because the code avoid creating duplicate combinations.
		lists.add(new ArrayList(combination));
		for (int i = index; i < arr.length; i++) {
			int curVal = arr[i];
			if (i > index && arr[i - 1] == arr[i]) {
				continue;
			}
			combination.add(curVal);
			recursion(arr, i + 1, lists, combination);
			combination.remove(combination.size() - 1);
		}
		return;
	}
}
