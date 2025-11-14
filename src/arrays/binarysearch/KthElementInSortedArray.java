package arrays.binarysearch;

/**
 * <pre>
 * 
 * arr[] = {2,3,6,7,9}
 * arr2[] = {1,4,8,10}
 * 
 * Find Kth element
 * 
 * k =5
 * {1,2,3,4,6,7,8,9,10}
 * kth element is 6
 * 
 * 1 based indexing
 * 
 * TC : O(N+M)
 * SC : O(1)
 * </pre>
 */
public class KthElementInSortedArray {

	public static void main(String[] args) {
		int large[] = { 2, 3, 6, 7, 9 };
		int small[] = { 1, 4, 8, 10 };
		int k = 5;
		System.out.println(kthElementMerging(large, small, k));
		large = new int[] { 1, 2, 3, 4, 9, 11 };
		small = new int[] { 7, 12, 14, 15 };
		System.out.println(kthElementBinarySearch(large, small, k));
	}

	// create a M+N array by merging the 2 arrays and find the element
	private static void kthElementBrute(int arr[], int arr2[], int k) {

	}

	private static int kthElementMerging(int nums1[], int nums2[], int k) {
		int kth = 0;
		int i = 0;
		int j = 0;
		int count = 0;
		while (i < nums1.length && j < nums2.length) {
			count++;
			if (nums1[i] <= nums2[j]) {
				if (count == k) {
					kth = nums1[i];
				}
				i++;
			} else {
				if (count == k) {
					kth = nums2[j];
				}
				j++;
			}

		}
		while (i < nums1.length) {
			count++;
			if (count == k) {
				kth = nums1[i];
			}
			i++;
		}

		while (j < nums2.length) {
			count++;
			if (count == k) {
				kth = nums2[j];
			}
			j++;
		}
		return kth;
	}

	/**
	 * <pre>
	 * 
	 * 
	 * Use binary search.
	 * 
	 * when we merge 2 sorted arrays and want to find kth element,
	 * we know that some elements of left side to kth element are some taken from arr1 and some taken from arr2
	 * k =5
	 * {1, 2, 3, 4, 6, 7, 8, 9, 10}
	 * If we can figure out how many are taken from first arr and how many are taken from second arr.
	 * 
	 * arr1  1 3 4
	 *           l1
	 * arr2  2 3
	 *         l2
	 * 
	 * max(l1,l2) will be the answer.
	 * 
	 * 
	 * 1 3  4  7  10 12
	 *     l1  r1
	 *   2  3  6 15
	 *     l2  r2  
	 *     
	 *     
	 * if l1 <= r2 means the entire left half is smaller than right half.
	 * if l2 <= r1 means the entire left half is smaller than right half.
	 * 
	 * 
	 * Take first array is smaller array.
	 * 
	 * 7 12 14 15
	 * 1 2 3 4 9 11
	 * 
	 * l1 <= r2
	 * l2 <= r1
	 * low1 = 0
	 * high1= 4
	 * cut1 = (0+4)/2 = 2
	 * rest k-2 = 3 from second array
	 * cut2 = 3
	 * 
	 * 
	 * 
	 * lets pick up 2 elements from first array and rest from second array
	 * 
	 * and check if thats a valid left half.
	 * 
	 * If left half's l1 > r2, shrink from high.
	 * low1 = 0;
	 * high1= cut1-1 = 1;
	 * cut1 = 1/2 = 0; so zero elements
	 * 
	 * cut2 = k-0 =  5
	 * 
	 * If left half's l2 > r1, shrink from left
	 * 
	 * low = cut1+1 = 1;
	 * high = 1
	 * 
	 * cut1 = (1+1)/2 = 1
	 * 
	 * 1 element from first arr
	 * cut2 = k-1 = 4 from second arr
	 * 
	 * 7       | 12 14 15
	 * 1 2 3 4 | 9 11
	 * 
	 * now l1 <= r2 and l2 <= r1
	 * return the max(l1,l2)
	 * 
	 * Edge cases.
	 * k=3 smaller the smaller size array then
	 * low = 0 in minimum from 1st array and high =3 at max from 1st array
	 * k=7, larger than the largest array,
	 * low = 1 in minimum from 1st arr, high = 4 at max 4 from 1st  array
	 * 
	 * l1 = arr[cut1-1], r1 = arr[cut1]
	 * l2 = arr[cut2-1], r2 = arr[cut2]
	 * 
	 * if cut happens at 0 or extreme right
	 * so have l1 and l2 = MIN_VALUE
	 * r1 and r2 = MAX_VALUE
	 * 
	 * 
	 * 
	 * </pre>
	 */
	private static int kthElementBinarySearch(int large[], int small[], int k) {
		// take the smallest array as first array
		int low = Math.max(0, k - small.length - 1);
		int high = Math.min(large.length - 1, k);
		// elements from first array

		// elements from second array

		while (low <= high) {
			int cut1 = (low + high) / 2;
			int cut2 = k - cut1;
			int l1 = 0;
			int l2 = 0;
			int r1 = 0;
			int r2 = 0;
			if (cut1 > 0) {
				l1 = small[cut1 - 1];
			} else {
				l1 = Integer.MIN_VALUE;
			}
			if (r1 < small.length) {
				r1 = small[cut1];
			} else {
				r1 = Integer.MAX_VALUE;
			}
			cut2 = k - cut1;
			if (cut2 > 0) {
				l2 = large[cut2 - 1];
			} else {
				l2 = Integer.MIN_VALUE;
			}
			if (r2 < large.length) {
				r2 = large[cut2];
			} else {
				r1 = Integer.MAX_VALUE;
			}

			if (l1 <= r2 && l2 <= r1) {
				return Math.max(l1, l2);
			}
			if (l1 > r2) {
				high = cut1 - 1;

			} else if (l2 > r1) {
				low = cut1 + 1;
			} else {
				break;
			}
		}
		return 1;
	}
}
