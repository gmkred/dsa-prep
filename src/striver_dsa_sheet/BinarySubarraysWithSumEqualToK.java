package striver_dsa_sheet;

import java.util.HashMap;
import java.util.Map;

public class BinarySubarraysWithSumEqualToK {

	public static void main(String[] args) {
		int arr[] = new int[] { 1, 0, 1, 0, 1 };
		int k = 2;
		System.out.println(binarySubarraysWithSumEqualToK(arr, k));

		System.out.println(binarySubarraysWithSumEqualToK_SpaceComplexity_Oof1(arr, k)
				- binarySubarraysWithSumEqualToK_SpaceComplexity_Oof1(arr, k - 1));
	}

	/**
	 * <pre>
	 * Same as
	 * {@link striver_dsa_sheet.NumberOdSubArraysWithSumK#numberOfSubArraysWithSumK(int[], int)}
	 * TC :O(N) SP :O(N)
	 * 
	 * <span style = "color : red; font-weight:bold;">But to optimize memory, then we need to go for </span>
	 * <span style = "color : green; font-weight:bold;font-size:12px;";>fun(nums,k) - fun(nums, k-1) ==> all possible sub arrays <= k and <= k-1 which gives == k</span>
	 * </pre>
	 */
	public static int binarySubarraysWithSumEqualToK(int nums[], int goal) {
		int count = 0, l = 0, r = 0, sum = 0;
		Map<Integer, Integer> map = new HashMap();
		map.put(0, 1);
		while (r < nums.length) {
			sum = sum + nums[r];
			if (map.containsKey(sum - goal)) {
				count += map.get(sum - goal);
			}

			if (map.containsKey(sum)) {
				map.put(sum, map.get(sum) + 1);
			} else {
				map.put(sum, 1);
			}
			r++;
		}
		return count;
	}

	/**
	 * <pre>
	 * 
	 * <span style = "color : green; font-weight:bold;font-size:13px;";><u>Memory optimization fun(nums,k) - fun(nums, k-1) </u></span>
	 * When we have binary array which means O's and 1's. for this maintaining hashmap can consume 
	 * more memory so to optimize memory we can try sliding window. but there is catch
	 * for example {1,0,1,0,1,0,1} k =2;
	 * we need to find number of subarrays whose sum is equal to 2
	 * possible sub arrays
	 * {1,0,1,0,1,0,1} ==> original array
	 * {1,0,1}           //1
	 * {1,0,1,0}         //2
	 *   {0,1,0,1,0}     //3
	 *     {1,0,1,0}     //4
	 *       {0,1,0,1}   //5
	 *         {1,0,1}   //6
	 * the problem with sliding window is, when we find the sub array sum is > k then we try to shrink the
	 * window by moving 'l'.
	 * if we observe 3rd subarray,{0,1,0,1} which is valid but 4th subarray {1,0,1} is also valid.
	 * But the condition to move 'l' will only check if the sum is <= k then breaks out the loop, which will
	 * miss the 4th subarray ass soon as it find 3rd subarray.
	 * 
	 * 
	 * So, instead of finding subarray sum = k
	 * we can do sub array (sum < = k) and subarrays (sum < = k-1)
	 * 
	 * (sum < =k) - (sum < =k-1) = sum == k // this will give sub arrays whose sum is == k
	 * 
	 * TC : O(2N)
	 * SP : O(1)
	 * </pre>
	 */
	public static int binarySubarraysWithSumEqualToK_SpaceComplexity_Oof1(int nums[], int goal) {
		int count = 0, l = 0, r = 0, sum = 0;
		if (goal < 0) {
			return 0;
		}
		while (r < nums.length) {
			sum = sum + nums[r];
			while (sum > goal) {
				sum = sum - nums[l];
				l++;
			}
			count += r - l + 1;
			r++;
		}
		return count;
	}
}
