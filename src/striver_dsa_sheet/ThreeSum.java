package striver_dsa_sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 * 
 * {-1, 0, 1, 2, -1, -4}
 * find three unique indexed elements whose sum = 0;
 * i != j != k
 * {-1, 2, 1}
 * {0, 1, -1}
 * are two different sets.
 * </pre>
 */
public class ThreeSum {

	public static void main(String[] args) {
		int arr[] = { -1, 0, 1, 2, -1, -4 };
		System.out.println(threeSumCOunt(arr));
		System.out.println(threesum_better(arr));
		System.out.println(threeSum_Optimal(arr));
	}

	/**
	 * <pre>
	 * sort the given array.
	 * {-2, -2, -2, -1, -1, -1, 0, 0, 0, 2, 2, 2, 2}
	 * 
	 * use 3 pointers
	 * i,j,k
	 * and also track the prev values of each pointers
	 * iPrev, jPrev, and kPrev
	 * initially put 
	 * i at 0
	 * j = i+1
	 * k = n-1
	 * 
	 * first check the sum of arr[i]+arr[j]+arr[k] 
	 * if sum is equal to 0 add it to the list.
	 * if sum < 0, that means we need to increase j
	 * 	But increasing j just by 1 is not enough,
	 * 	because, the j+1 can be same as j, whihc will be a duplicate.
	 * 	so increment the j till it is not same as jPrev value.
	 * 	update jPrev.
	 * if sum > 0, that means we need to decrease the k
	 * 	But decreasing k just by 1 is not enough,
	 * 	because, the k-1 can be same as k, which will be a duplicate.
	 * 	so decrement the k till it is not same as kPrev value.
	 * After this do it from i+1, 
	 * 	But increasing i just by 1 is not enough,
	 * 	because i+1 cab be same as iPrev.
	 * 	so increase i till it is not same as iPrev.
	 * 
	 * This way we can avoid duplicates.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * </pre>
	 */
	private static List<List<Integer>> threeSum_Optimal(int arr[]) {
		List<List<Integer>> list = new ArrayList<>();

		int iPrev = Integer.MIN_VALUE;
		Arrays.sort(arr);
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < arr.length) {
			if (arr[i] == iPrev) {
				i++;
			}
			iPrev = arr[i];
			k = arr.length - 1;
			if (i == k) {
				break;
			}
			j = i + 1;
			int jPrev = arr[j];
			int kPrev = arr[k];
			while (j < k) {
				jPrev = arr[j];
				kPrev = arr[k];
				if (arr[i] + arr[j] + arr[k] == 0) {
					List<Integer> l = new ArrayList<>();
					l.add(arr[i]);
					l.add(arr[j]);
					l.add(arr[k]);
					list.add(l);
					j++;
					k--;
				} else if (arr[i] + arr[j] + arr[k] <= 0) {
					while (arr[j] == jPrev) {
						j++;
					}
				} else {
					while (arr[k] == kPrev) {
						k--;
					}
				}
			}
			i++;

		}
		return list;
	}

	/**
	 * <pre>
	 * arr[i]+arr[j]+ arr[k] = 0 arr[k] == -(arr[i] + arr[j])
	 * 
	 * store all the elements in hash and find if -(arr[i]+arr[j]) present if yes
	 * that a triplet.
	 * 
	 * 
	 * Note: Dont store all the elemetns into map in the beginning,
	 * Because, -(arr[i]+arr[j]) might give a value which is same as arr[i] or arr[j]
	 * Example : 
	 * { -1, 0, 1, 2, -1, -4}
	 * 
	 *  -(arr[3]+arr[5]) = -(2-4) = 2 but 2 is already is arr[3].
	 *  So, add elements into the map which are between i -- j, so the unique indexed elmenet will 
	 *  not be i or j
	 *  
	 *  TC : O(N^2) 
	 *  + 
	 *  O(log of M) for each i it will be n-i;
	 *  
	 *  SC : O(number of triplets) + O(N) each time we are clearing so at max it will be N
	 * </pre>
	 */
	private static int threesum_better(int arr[]) {

		Set<Integer> ele = new HashSet();
		Set<List<Integer>> set = new HashSet();

		for (int i = 0; i < arr.length; i++) {
			ele.clear();
			for (int j = i + 1; j < arr.length; j++) {
				if (ele.contains(-(arr[i] + arr[j]))) {
					List<Integer> list = new ArrayList<>();
					list.add(arr[i]);
					list.add(arr[j]);
					list.add(-(arr[i] + arr[j]));
					list.sort(Integer::compare);
					set.add(list);
				}
				ele.add(arr[j]);
			}
		}
		System.out.println(set);
		return set.size();

	}

	/**
	 * <pre>
	 * 
	 * TC : ~O(N^3) + O(number of unique triplets)
	 * SC : O(Number of triplets) + O(number of triplets) if we need to return it as a list.
	 * </pre>
	 */
	private static int threeSumCOunt(int arr[]) {
		Set<List<Integer>> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				for (int k = j + 1; k < arr.length; k++) {
					if (arr[i] + arr[j] + arr[k] == 0) {
						List<Integer> list = new ArrayList<>();
						list.add(arr[i]);
						list.add(arr[j]);
						list.add(arr[k]);
						list.sort(Integer::compare);
						set.add(list);
					}
				}
			}
		}
		System.out.println(set);
		return set.size();
	}
}
