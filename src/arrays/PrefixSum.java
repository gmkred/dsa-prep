package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * In prefix sum we create an Hashtable of index and sum of previous elements to
 * the current element.
 * 
 * <pre>
 * {1,5,4,8,9,2}
 * In prefix hashtable, the first value to 0th index should be 0
 * prefix[0,0] 				0
 * prefix[1, 0+1]				1
 * prefix[2, 0+1+5]			6
 * prefix[3, 0+1+5+4]			10
 * prefix[4, 0+1+5+4+8]			18
 * prefix[5, 0+1+5+4+8+9]			27
 * prefix[6, 0+1+5+4+8+9+2]		29
 * 
 * Now to find any <b>range</b> in the prefix sum 
 * For example : sum(2,5) ==> sum from 2nd index to 5th index 
 * i.e. {1,5,<b>4,8,9,2</b>}
 * 4+8+9+2 = 23
 * 
 * sum(l,r) => prefix[r+1] - prefix[l]
 * sum(2,5) => prefix[5+1] - prefix[2]
 * 
 * 			   29 - 6	=> 23
 * we are removing the value till 'l' which is '2' i.e. in prefix sum 2nd index contains
 * the sum of 0th and 1st index values so when we use 2nd we are actually removing 
 * 0th and 1st index values. its because of a 0 values stored in 0th index of prefixsum.
 * 
 * prefix[i] = arr[0]+arr[1]+...+arr[i-1];
 * prefix[2] = arr[0]+arr[1]
 * 
 * 
 * </pre>
 */
public class PrefixSum {

	public static void main(String[] args) {
				//	  0	  1  2  3  4  5
		int arr[] = { 23, 2, 4, 6, 7, 15 };
				//    0   1   2   3   4   5   6
				//	{ 0, 23, 25, 29, 35, 42, 57}
				//   range 2 to 5 means after 2 till 6. so we have to subtract 6th index value with 2nd index value 
		Map<Integer, Integer> prefixSum = prefixSum(arr);
		int range2to5 = rangeQuery(2, 5, prefixSum);
		System.out.println();

	}
	/**
	 * <pre>
	 * Example : 
	 * Find the profit from 2nd day to 4th day
	 * Given an array of profits, each element indicates the profit for that day.
	 *  0   1    2   3   4  5 
	 * {12, 15, 18, 21, 26, 23}
	 * 2nd to 4th day profit ==> 15+18+21 = 54
	 * To solve this :
	 * 	first perform a prefix sum on the elements
	 * 	0  1   2   3   4   5    6
	 * {0, 12, 27, 45, 66, 92, 115}
	 * 
	 * to find profit from 2nd day to 4th.
	 * find the range between 4nd to 4th
	 * rane(l,r) ==> (r+1) - l
	 * range(2,4) ==> 1st index to 3th index==>  arr[3+1] - arr[1} => 66 - 12 = 54 
	 * 
	 * </pre>
	 * */
	public static Map prefixSum(int arr[]) {
		Map<Integer, Integer> prefixSum = new HashMap<>();
		prefixSum.put(0, 0);
		IntStream.range(0, arr.length).forEach(ind -> prefixSum.put(ind + 1, prefixSum.get(ind) + arr[ind]));
		System.out.println(prefixSum);
		return prefixSum;
	}
	/**	
	 * <pre>
	 *    0  1  2  3  4  5
	 *	{ 23, 2, 4, 6, 7, 15 };
	 *	  0   1   2   3   4   5   6
	 *	{ 0, 23, 25, 29, 35, 42, 57}
	 *	range 2 to 5 means after 2 till 6. so we have to subtract 6th index value with 2nd index value 
	 * <span style= "color:red"> 
	 *                          range
	 *                 |------------------|
	 * |----------------------------------|
	 * |---------------|                  Pb
	 *                 Pa	
	 *	</span>
	 *	Pb - Pa = range
	 * </pre>
	 */
	private static int rangeQuery(int l, int r, Map<Integer, Integer> prefixSum) {
		int right = prefixSum.get(r + 1);// Because in prefixSum map, the index is +1 to original array index
		int left = prefixSum.get(l); // in original array l is -1 so its fine to have l
		return right - left;
	}
	
}
