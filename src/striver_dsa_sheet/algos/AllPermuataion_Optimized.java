package striver_dsa_sheet.algos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * No extra data structure.
 * 
 * Instead we swap
 * Intuition : We try to place every number at a particular index and swap other to find permutations.
 * It is only possible if we swap with the right elements with current index
 * 
 * TC : O(N! * N)
 * SC : O(1) only the auxiliary recursive space.
 *      O(N!) for result
 * </pre>
 */
public class AllPermuataion_Optimized {

	public static void main(String[] args) {
		int arr[] = { 1, 2, 3 };
		List<List<Integer>> ans = new ArrayList<>();
		recursivePermutation(arr, 0, ans);
		System.out.println(ans);

	}

	/**
	 * <pre>
	 *  
	 *  
	 * CurrentIndex at each recursive level will be constant.
	 * top level currentIndex will always be 0, and iwill be moving and swapping with currentIndex.
	 * It happens on each level.
	 * If current index is at 1, i starts from 1 to arr.length-1.
	 * if currentIndex is at 2, i starts from 2 to arr.length-1
	 * 
	 * {1,2,3}
	 * 
	 * currentIndex =0
	 * i=0 < len
	 * swap(0,0)
	 * {1,2,3}
	 * rec
	 * currentIndex = 1;
	 * i = 1 < len
	 * swap(1,1)
	 * {1,2,3}
	 * rec
	 * currentIndex = 2;
	 * i = 2 < len
	 * swap(2,2)
	 * {1,2,3}
	 * rec
	 * currentIndex = 3
	 * i =3 == len
	 * {1,2,3} add to list and return.
	 * 
	 * re swap(2,2)
	 * currentIndex = 1,
	 * i = 2
	 * swap(1,2)
	 * {1,3,2}
	 * rec
	 * currenIndex = 3
	 * i = 3 == len
	 * {1,3,2} add to lsit and return
	 * re swap(1,2)
	 * currentIndex=0;
	 * i = 1;
	 * swap(0,1)
	 * {2,1,3}
	 * rec
	 * currentIndex = 1;
	 * i = 1;
	 * swap(1,1)
	 * {2,1,3}
	 * rec
	 * currentIndex = 2;
	 * i=2;
	 * swap(2,2)
	 * {2,1,3}
	 * rec
	 * currentIndex = 3;
	 * i = 3 == len
	 * {2,1,3} add to list and return.
	 * re swap(2,2)
	 * current index=1;
	 * i = 2;
	 * swap(1,2)
	 * {2,3,1}
	 * currentIndex=2;
	 * i=2
	 * swap(2,2)
	 * {2,3,1}
	 * currentIndex = 3;
	 * {2,3,1} add to list and return
	 * re swap(2,2)
	 * currentIndex = 0;
	 * i=2;
	 * swap(0,2)
	 * {3,1,2}
	 * currentIndex = 1,
	 * i =1;
	 * swap(i,1)
	 * {3,1,2}
	 * rec
	 * currentIndex = 2;
	 * i =2;
	 * swap(2,2)
	 * {3,1,2}
	 * currentIndex = 3
	 * {3,1,2} add to list and return.
	 * re swap(2,2)
	 * currnetIndex = 1;
	 * i=2
	 * swap(1,2)
	 * {3,2,1}
	 * currentIndex = 2
	 * i=2
	 * swap(2,2}
	 * {3,2,1}
	 * currentIndex = 3;
	 * {3,2,1 add to list and return.
	 * re swap(2,2)
	 * {3,2,1}
	 * currentIndex=0;
	 * i=3
	 * recursion ends.
	 * 
	 * </pre>
	 * 
	 */
	private static void recursivePermutation(int arr[], int curIndex, List<List<Integer>> ans) {

		if (curIndex == arr.length) {
			ans.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
			return;
		}
		for (int i = curIndex; i < arr.length; i++) {
			swap(arr, curIndex, i);
			recursivePermutation(arr, curIndex + 1, ans);
			/**
			 * <pre>
			 * Do not try to add to list at this line, because, we will get duplicate
			 * permutations as same permutation will be present on different recursive
			 * level.
			 * Example:
			 * when currentiIndex and i at 1, the perm is {1,2,3} but perm will
			 * still be {1,2,3} at currentIndex and i 2, so adding on return of recursive call
			 * will add duplicate permutations
			 * </pre>
			 */
			swap(arr, curIndex, i);
		}
		return;
	}

	private static void swap(int arr[], int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
