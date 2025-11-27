package striver_dsa_sheet.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <pre>
 * 
 * given 2 arrays find the max sum paris from these 2 arrays.
 * pair should contain one element from one array and other from another array.
 * 
 * nums1 = {7,3}
 * nums2 = {1,6}
 * 
 * k=2
 * 
 * max k sum combinations are (7,6) and (3,6)
 * 
 * 
 * 
 * </pre>
 */
public class MaximumSumCombination {
	public static void main(String[] args) {
		int arr1[] = { 3, 4, 5 };
		int arr2[] = { 2, 6, 3 };
		int k = 2;
		System.out.println(maxPairSumOptimal(arr1, arr2, k));
	}

	/**
	 * <pre>
	 * 
	 * sort the arrays in descending order
	 * 
	 * arr1[] = { 3, 4, 5 };
	 * arr2[] = { 2, 6, 3 };
	 * 
	 * Descending order
	 * 
	 * {3, 4, 5}
	 * {2, 3, 6}
	 * 
	 * now sum the last elements of the 2 arrays.
	 * 
	 * 5+6 = 11, push it into PQ.
	 * 
	 * next time take that sum and add it to list and then again sum the
	 * elements
	 * 
	 * add sums of 5+3 and 6+4 to PQ, it will sort the sums and next time,
	 * when we poll, it gives the max sum.
	 * 
	 * We need to tract the indexes, so for next sum combination we use the current index value.
	 * 
	 * 
	 * </pre>
	 */
	public static List<Integer> maxPairSumOptimal(int arr1[], int arr2[], int k) {
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		// for max heap,if Object is a generic, then the t2 value should be greater than
		// t1.
		// Or if, we use Integer as Generic, use Collections.reverseOrder or i2 - i1;
		PriorityQueue<Tuple> pq = new PriorityQueue<>((t1, t2) -> t2.sum - t1.sum);
		int l1 = arr1.length;
		int l2 = arr2.length;
		pq.offer(new Tuple(arr1[l1 - 1] + arr2[l2 - 1], l1 - 1, l2 - 1));
		List<Integer> list = new ArrayList<>();
		while (!pq.isEmpty()) {
			Tuple t = pq.poll();
			list.add(t.sum);
			if (list.size() == k) {
				break;
			}
			int i1 = t.arr1Index;
			int i2 = t.arr2Index;
			if (i2 > 0) {
				pq.offer(new Tuple(arr1[i1] + arr2[i2 - 1], i1, i2 - 1));
			}
			if (i1 > 0) {
				pq.offer(new Tuple(arr1[i1 - 1] + arr2[i2], i1 - 1, i2));
			}
		}
		return list;
	}

	// take one element from first array and sum with all the elements in second
	// array and store all the sums in a list, at the end sort the list in reverse
	// order and get the first k sums.
	// TC : O(N^M) loop + O(N+M log N+M) sorting
	// SC : O(N+M)
	public static int[] maxPairSum(int arr1[], int arr2[], int k) {
		return null;

	}

	static class Tuple {
		public Tuple(int sum, int arr1Index, int arr2Index) {
			this.sum = sum;
			this.arr1Index = arr1Index;
			this.arr2Index = arr2Index;
		}

		int sum;
		int arr1Index;
		int arr2Index;
	}

}
