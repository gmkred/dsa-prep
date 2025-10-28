package striver_dsa_sheet.algos;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Use recursion to generate all the permutations.
 * Use a data structure like list to store the permutation.
 * And also a frequnecy map to know what is left at each level of the recursion.
 * if list.size == arr.length then retrun.
 * While returning remove the element from the list and also remove in map.
 * 
 * 
 * TC : O(N*N!) at minimum
 * 
 * SC : O(N) + O(N), ignore O(N!) because its result.
 * </pre>
 */
public class AllPermutations {
	public static void main(String[] args) {
		int arr[] = { 1, 2, 3 };
		System.out.println(printAllPermutations(arr));

	}

	private static List<List<Integer>> printAllPermutations(int arr[]) {
		return generatePermutations(arr);
	}

	private static List<List<Integer>> generatePermutations(int arr[]) {

		List<List<Integer>> permuataions = new ArrayList<>();
		List<Integer> perm = new ArrayList<>();
		boolean[] freq = new boolean[arr.length];
		recursivePermutation(permuataions, perm, arr, freq);
		return permuataions;

	}

	/**
	 * <pre>
	 * Explanation:
	 * 
	 * Requirements:
	 * List<List<integer>> : to store each permutation list.
	 * List<Integer> : to create the permutations. 
	 * 	Used for all he permutations and created a new instance when there is one permutation and add it the permutations list.
	 * Boolean frequency : On each recursive, to know which index value is still available to be taken to create a permutation.
	 * Array : which is an input.
	 * 
	 * Process:
	 *  0 1 2
	 * {1,2,3}
	 * 
	 * Start with a loop.
	 * 
	 * L10=0		0th available {1}
	 * 				rec --> L100
	 * L100=0		0th taken{1}{1}
	 * L100=1 		1st available {1,2}
	 * 				rec -->L1000
	 * L1000=0		0th taken{1,2}
	 * L1000=1		1st is taken{1,2}
	 * L1000=2		2nd available {1,2,3}
	 * 				Got one permutation = add it to permutations.{1,2,3}
	 * 				Remove 2nd form the perm {1,2}
	 * 				L100=1<-- rec
	 * L100=1		Remove 1st from perm {1}
	 * L100=2		2nd is available {1,3}
	 * 				rec --> L1001
	 * L1001=0		0th is taken{1,3}
	 * L1001=1		1st is available {1,3,2}
	 * 				Got one permutation = add it to permutations. {1,2,3},{1,3,2}
	 * 				Remove 1st from perm {1,3}
	 * L1001=2		2nd is already taken
	 * 				L100=2<-- rec
	 * L100=2		Remove 2nd from perm {1}
	 * 				L10=0<-- rec
	 * L10=0		Remove 0th from perm {}
	 * ------------------
	 * L10=1		1st availabale {2}
	 * 				rec --> L200=0
	 * L200=0		0th is available {2,1}
	 * 				rec --> L2000=0
	 * L2000=0		0th is taken{2,1}
	 * L2000=1		1st is taken{2,1}
	 * L2000=2		2nd is available {2,1,3}
	 * 				Got one permutation = add it to permutations.
	 * 				remove 2nd from perm {2,1}
	 * 				L200=0<-- rec
	 * L200=0		Remove 0th {2}
	 * L200=1		1st is taken{2}
	 * L200=2		2nd is available {2,3}
	 * 				rec --> L2001=0
	 * L2001=0		0th is avaible {2,3,1}
	 * 				Got One permutation = add t=it to permutations.
	 * 				Remove 0th from prem {2,3}
	 * 				L200=2<-- rec 
	 * L200=2		remove 2nd fron perm {2}
	 * 				L10=1 <-- rec
	 * L10=1		remove 1st from prem {}
	 * -------------------
	 * L10=2		2nd availabale {3}
	 * 				rec --> L300=0
	 * L300=0		0th is available {3,1}
	 * 				rec --> L3000=0
	 * L3000=0		0th is taken{3,1}
	 * L3000=1		1st is available {3,1,2}
	 * 				Got one permutation = add it to permutations.
	 * 				Remove 1st from perm {3,1}
	 * 				L300=0 <-- rec
	 * L300=0		remove 0th from perm {3}
	 * L300=1		1st is available {3,2}
	 * 				rec --> L3001=0
	 * L3001=0		0th is available {3,2,1}
	 * 				Got one permutation = add it to permutations.
	 * 				remove 0th from perm {3,2}
	 * 				L300=1 <-- rec
	 * L300=1		remove 1st from perm {3}
	 * L300=2		2nd is taken.
	 * 				L10=2 <-- rec
	 * L10=2		remove 2nd from perm {}
	 * 
	 * Permutations.
	 * {1,2,3},{1,3,2},{2,1,3},{2,3,1},{3,1,2},{3,2,1}
	 * 
	 * 
	 * 
	 * </pre>
	 */
	private static void recursivePermutation(List<List<Integer>> permutations, List<Integer> perm, int arr[],
			boolean[] freq) {
		if (perm.size() == arr.length) {
			permutations.add(new ArrayList<>(perm));
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (!freq[i]) {
				freq[i] = true;
				perm.add(arr[i]);
				recursivePermutation(permutations, perm, arr, freq);
				perm.remove(perm.size() - 1);
				freq[i] = false;
			}
		}
	}
}
