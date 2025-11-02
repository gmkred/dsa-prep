package striver_dsa_sheet.recursions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * 
 * Problem statement : find the combinations whose sum == k, but smae combination are not allowed if there are any.
 * and all the combinations should be in sorted order.
 * 
 * Example :
 * arr={1,1,2,2} target = 4
 * 
 * combiantion accordingly.
 *  0 1 2
 * {1,1,2}
 *  2 3
 * {2,2}
 *                                         0 1 3
 * there can be one more combination with {1,1,2}, but it will be duplicate combination.
 * 
 * 
 * </pre>
 */
public class CombinationSum2 {
	public static void main(String[] args) {
		int arr[] = { 10, 1, 2, 7, 6, 1, 5 };
		int target = 8;
//		System.out.println(combinationSum2(arr, target));
		System.out.println(combinationSum2(arr, target));
	}

	/**
	 * <pre>
	 * This is a version where we dont use include and exclude, instead we use loops for recursion calls.
	 *  0  1  2  3  4
	 * {2, 5, 2, 1, 2}
	 * 
	 * {1, 2, 2, 2, 5}  target =5
	 * 
	 * firstly sort the array, because ans should be sorted.
	 * on 0th index, we start from from 0 to n-1, and we pick only unique
	 *               first unique element is 1 so pick it, {1} <= 5 so call the recursion form i+1
	 *                  recursion call loop starts from 1st to n-1.
	 *                  first unique element is 2 so pick it {1+2} <= 5, so call the recursion from i+1
	 *                    recursion call loop starts from 2nd to n-1
	 *                    first unique element is 2 so pick it, {1+2+2} <= 5, so call the recursion from i+1
	 *                      recursion call loop starts from 3 to n-1,
	 *                      since sum == target, add the combination to the list and return, which will go to previous recursion call of 2 to n-1. 
	 *                    in 2 to n-1, when the 2nd is already completed so remove 2nd value {1+2}
	 *                    now move to next index (3rd), before considering the number check if it is already there in the previous index, if yes do nto consider,
	 *                    because it will only result in duplicate combination, so skip the loop unti a unique element i found.
	 *                    at 4th index we have 5, which is unque compared to previous element so take 5, but if we take 5, the sum will be > 5
	 *                    since sum becomes 1+2+5 > 5, this recursion loop should be broken as it will not give any combination further.
	 *                    return the recursion to 1st to n-1.
	 *                  move to 2nd element, check if it is same as previous, if as skip until there is a new unique element.
	 *                  in this recursion loop, we have 5, whihc is new unique element, but picking 5 will result sum > 5,
	 *                  so break the current recursion loop.
	 *                  return to 0th to n-1 recursion loop.
	 *               before moving to next element, remove the current element from sum and combination {1} to {}
	 *               now pick the 1st index element and call the recursion from 2nd to n-1 and perform same steps until sum == target is met or sum exceeds target.
	 * Example 2 
	 *  0  1  2  3
	 * {1, 1, 1, 3}
	 * 
	 * target 5:
	 *  0  1  3       1  2  3
	 * {1, 1, 3} and {1, 1, 3}
	 * 
	 * To avoid duplicate combination.
	 * We need to start picking unique's at each level
	 * 
	 * first level
	 * 
	 * {1}                                                 and {3} false
	 * 
	 * second level
	 * 
	 * {1,1}                                                and {1,3} false 
	 * 
	 * third level                                         
	 *                                        
	 * {1,1,1} false   and  {1,1,3} == 5 true              
	 * 
	 * fourth leave
	 *  
	 * {1,1,1,3] > 5 false
	 * 
	 * 
	 * only one unique combination {1,1,3}
	 * 
	 * TC : O(n log n) sort
	 * O(2^n * k) if array contains n unique elements and k is average length of combination that needs to be added into lists
	 * 
	 * 
	 * SC : O(K*X) k is average length * x is combination count
	 * </pre>
	 */
	private static void recursionWithoutSet(int arr[], int target, int sum, int index, List<List<Integer>> lists,
			List<Integer> combination) {
//we dont have to take care of lenght, because for loop only executes till leangth
		if (sum == target) {
			lists.add(new ArrayList<>(combination));
			return;
		}

		for (int i = index; i < arr.length; i++) {

			// on each level consider only unique values. so skip if current index elements
			// is same as previous one.
			if (i > index && arr[i - 1] == arr[i]) {
				continue;
			}

			int curVal = arr[i];
			// if sum is <= target then only go for next recursion else break current loop,
			// because next elements in the sorted array will be greater than the current
			// elements which will be anyway greater than target. so just break.
			if (sum + curVal <= target) {
				sum += curVal;
				combination.add(curVal);
				recursionWithoutSet(arr, target, sum, i + 1, lists, combination);
				sum -= curVal;
				combination.remove(combination.size() - 1);
			} else {
				break;
			}
		}
		return;

	}

	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Set<List<Integer>> set = new HashSet();
		Arrays.sort(candidates);
//		recursion(candidates, target, 0, new ArrayList(), 0, set);
		List<List<Integer>> lists = new ArrayList<>();
		recursionWithoutSet(candidates, target, 0, 0, lists, new ArrayList<>());
//		return new ArrayList(set);
		return lists;
	}

	/**
	 * <pre>
	 * TC : O(2^N) * K log K 
	 * k log K, we put in a hashset so it takes logarithmic of K.
	 * This is not optimal for this solution.
	 * </pre>
	 */
	private static void recursion(int arr[], int target, int sum, List<Integer> combination, int index,
			Set<List<Integer>> set) {
		if (index == arr.length) {
			if (sum == target) {
				List<Integer> l = new ArrayList(combination);
				set.add(l);
			}
			return;
		}
		// since duplicates are not allowed, its a include once and exlcude once
		// problem.
		int curVal = arr[index];
		// include if sum+curVal is <= target, if exceeded then we do nto need it so
		// exclude it now its self.
		if (sum + curVal <= target) {
			sum += curVal;
			combination.add(curVal);
			recursion(arr, target, sum, combination, index + 1, set);
			sum -= curVal;
			combination.remove(combination.size() - 1);
		}
		recursion(arr, target, sum, combination, index + 1, set);
		return;
	}
}
