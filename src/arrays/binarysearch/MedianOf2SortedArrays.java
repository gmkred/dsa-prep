package arrays.binarysearch;

/**
 * <pre>
 * 
 * arr[] = {1, 3, 4, 7, 10, 13}
 * arr2[]= {2, 3, 6, 15}
 * 
 * {1, 2, 3, 3, 4, 6, 7, 10, 13, 15}
 *  M = (4+6)/2 = 10/2 = 5
 * 
 * arr[] = {2, 3, 4} 
 * arr2[]= {1, 3}
 * {1, 2, 3, 3, 4}
 *       M=3
 * 
 * arr[] = {1, 3, 4, 7, 10, 13}
 * arr2[]= {1, 2, 3, 5, 6, 15}
 * 
 * {1, 1, 2, 3, 3, 4, 5, 6, 7, 10, 13, 15} even
 * M = (4+5)/2 = 9/2 = 4.5
 * 
 * </pre>
 */
public class MedianOf2SortedArrays {
	public static void main(String[] args) {

		int arr[] = { 1, 3, 4, 7, 10, 13 };
		int arr2[] = { 2, 3, 6, 15 };
	}

//use merge sort and then find the median
	// TC : O(M+N)
	// SC : O(M+M)
	private static void bruteForce(int arr[], int arr2[]) {

	}

	// TC : O(M+N) SC : O(1)
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		// arr[] = {1, 3, 4, 7, 10, 13}
		// arr2[]= {1, 2, 3, 5, 6, 15}

		int mid = (nums1.length + nums2.length);

		int i = 0;
		int j = 0;
		int count = 0;
		int sec = mid / 2;
		int fir = sec - 1;
		int el1 = 0;
		int el2 = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] <= nums2[j]) {
				if (count == fir) {
					el1 = nums1[i];
				} else if (count == sec) {
					el2 = nums1[i];
				}
				i++;
			} else {
				if (count == fir) {
					el1 = nums2[j];
				} else if (count == sec) {
					el2 = nums2[j];
				}
				j++;
			}
			count++;
		}

		while (i < nums1.length) {
			if (count == fir) {
				el1 = nums1[i];
			} else if (count == sec) {
				el2 = nums1[i];
			}
			i++;
			count++;
		}

		while (j < nums2.length) {
			if (count == fir) {
				el1 = nums2[j];
			} else if (count == sec) {
				el2 = nums2[j];
			}
			j++;
			count++;
		}

		if (mid % 2 == 0) {
			return (double) (el1 + el2) / 2.0;
		} else {
			return el2;
		}

	}
}
