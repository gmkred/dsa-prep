package striver_dsa_sheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FourSum {
	public static void main(String[] args) {
		int arr[] = { 1, 0, -1, 0, -2, 2 };
//		System.out.println(fourSum(arr));
		System.out.println(fourSum_better(arr));
		int arr2[] = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5 };
		System.out.println(fourSum_Optimal(arr2, 8));
		System.out.println(fourSum_Optimal_striver(arr2, 8));
		System.out.println(fourSum_Optimal_consider_duplicates(arr2, 8).size());

	}

	/**
	 * <pre>
	 * @see {@link ThreeSum#threeSum_Optimal(int[])}
	 * It is similar to Three sum with one extra pointer.
	 * Where 2 pointers are fixed in Four sum and 2 pointer moves around.
	 * </pre>
	 */
	private static List<List<Integer>> fourSum_Optimal(int arr[], int target) {
		List<List<Integer>> lists = new ArrayList();
		// N log N
		Arrays.sort(arr);

		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int iPrev = Integer.MIN_VALUE;
		int jPrev = Integer.MIN_VALUE;
		int kPrev = Integer.MIN_VALUE;
		int lPrev = 0;
		while (i < arr.length) {
			if (arr[i] == iPrev) {
				i++;
				continue;
			}
			iPrev = arr[i];
			j = i + 1;
			while (j < arr.length) {
				if (arr[j] == jPrev) {
					j++;
					continue;
				}
				jPrev = arr[j];
				k = j + 1;
				l = arr.length - 1;
				while (k < l && k < arr.length) {
					kPrev = arr[k];
					lPrev = arr[l];
					int sum = arr[i] + arr[j] + arr[k] + arr[l];
					if (sum == 8) {
						List<Integer> list = new ArrayList();
						list.add(arr[i]);
						list.add(arr[j]);
						list.add(arr[k]);
						list.add(arr[l]);
						lists.add(list);
						while (arr[k] == kPrev) {
							k++;
						}
						kPrev = arr[k];
						while (arr[l] == lPrev) {
							l--;
						}
						lPrev = arr[l];
					} else if (sum < target) {
						while (arr[k] == kPrev) {
							k++;
						}
						kPrev = arr[k];
					} else {
						while (arr[l] == lPrev) {
							l--;
						}
						lPrev = arr[l];
					}
				}
			}
		}

		return lists;
	}

	private static List<List<Integer>> fourSum_Optimal_striver(int arr[], int target) {
		List<List<Integer>> lists = new ArrayList();
		// N log N
		Arrays.sort(arr);

		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		while (i < arr.length) {
			if (i > 0 && arr[i - 1] == arr[i]) {
				i++;
				continue;
			}
			j = i + 1;
			while (j < arr.length) {
				if (j > (i + 1) && arr[j - 1] == arr[j]) {
					j++;
					continue;
				}
				k = j + 1;
				l = arr.length - 1;
				while (k < l && k < arr.length) {
					int sum = arr[i] + arr[j] + arr[k] + arr[l];
					if (sum == target) {
						List<Integer> list = new ArrayList();
						list.add(arr[i]);
						list.add(arr[j]);
						list.add(arr[k]);
						list.add(arr[l]);
						lists.add(list);
						k++;
						l--;
						while (k < l && arr[k - 1] == arr[k]) {
							k++;
						}
						while (k < l && arr[l] == arr[l + 1]) {
							l--;
						}
					} else if (sum < target) {
						k++;

					} else {
						l--;
					}
				}
				j++;
			}
			i++;
		}

		return lists;

	}

	private static List<List<Integer>> fourSum_Optimal_consider_duplicates(int arr[], int target) {
		List<List<Integer>> lists = new ArrayList();
		// N log N
		Arrays.sort(arr);

		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		while (i < arr.length) {
			j = i + 1;
			while (j < arr.length) {
				k = j + 1;
				l = arr.length - 1;
				while (k < l && k < arr.length) {
					int sum = arr[i] + arr[j] + arr[k] + arr[l];
					if (sum == 8) {
						List<Integer> list = new ArrayList();
						list.add(arr[i]);
						list.add(arr[j]);
						list.add(arr[k]);
						list.add(arr[l]);
						lists.add(list);
						k++;
						l--;
					} else if (sum < target) {
						k++;
					} else {
						l--;
					}
				}
				j++;
			}
			i++;
		}

		return lists;
	}

	/**
	 * <pre>
	 * TC : O(N* Log m) + O(N^3)
	 * SC : O(N) + O(quads)
	 * 
	 * </pre>
	 */
	private static Set<List<Integer>> fourSum_better(int arr[]) {
		Set<List<Integer>> lists = new HashSet<>();
		Set<Integer> set = new HashSet();
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				set.clear();
				for (int l = j + 1; l < arr.length; l++) {
					if (set.contains(0 - (arr[i] + arr[j] + arr[l]))) {
						List<Integer> list = new ArrayList<>();
						list.add(arr[i]);
						list.add(arr[j]);
						list.add(arr[l]);
						list.add(-(arr[i] + arr[j] + arr[l]));
						list.sort(Integer::compare);
						lists.add(list);
					}
					set.add(arr[l]);
				}
			}
		}
		return lists;

	}

	/**
	 * <pre>
	 * 
	 * 
	 * TC : O(N^4) + O(4 * number of lists that have sum 0) + O(log N) to add to Set.
	 * 
	 * SC : O(2 * (4 * number of unique four sum elements found))
	 * </pre>
	 */
	private static List<List<Integer>> fourSum(int arr[]) {
		List<List<Integer>> lists = new ArrayList<>();
		Set<List<Integer>> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				for (int k = j + 1; k < arr.length; k++) {
					for (int l = k + 1; l < arr.length; l++) {
						if (arr[i] + arr[j] + arr[k] + arr[l] == 0) {
							List<Integer> list = new ArrayList<>();
							list.add(arr[i]);
							list.add(arr[j]);
							list.add(arr[k]);
							list.add(arr[l]);
							list.sort(Integer::compare);
							set.add(list);
						}
					}
				}
			}
		}
		lists.addAll(set);
		return lists;
	}
}
