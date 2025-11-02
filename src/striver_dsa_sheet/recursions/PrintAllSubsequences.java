package striver_dsa_sheet.recursions;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * <a href=
"https://github.com/gmkred/preparation-files/blob/master/sub%20sequences%20using%20recursive.whiteboard">white board flow explanation </a>
 * A contiguous or non contiguous which  follows the order.
 * 
 * {3,1,2}
 * {} empty is also considered as sub sequence.
 * 3
 * 1
 * 2
 * 3,1
 * 1,2
 * 3,2
 * 3,1,2
 * 
 * the above are the subsequences because all follows the order
 * 
 * if we take 3,2. 3 comes before 2
 * but if we take 1,3. its wrong because in array, 1 is after 3.
 * 
 * 
 * {3,1,2,5}
 * sequence in which it prints
 *	[3, 1, 2, 5]
 * 	[3, 1, 2]
 *	[3, 1, 5]
 *	[3, 1]
 *	[3, 2, 5]
 *	[3, 2]
 *	[3, 5]
 *	[3]
 *	[1, 2, 5]
 *	[1, 2]
 *	[1, 5]
 * 	[1]
 *	[2, 5]
 *	[2]
 *	[5]
 *	[]
 * 
 * 
 * At each index we will include that index value and exclude that index value,
 * suppose we at 2nd index, first include it, next recursive will be for 3rd index,
 * 3rd index is included, and recursion starts for 4th index, which will be the base condition,
 * it prints the sequence and return to 3rd index, now 3rd is excluded and again it call recursion for 4th,
 * it will print the sequence and return to 3rd, now 3rd include and excluded is done, it returns to 
 * 2nd, now 2nd index is excluded, it calls recursion for 3rd, again 3rd includes itself and calls 4th,
 * 4th, prints the sequence, and returns to 3rd, not 3rd exclude it self, again call recursion for 4th,
 * it will print the sequence, and returns to 3rd, which returns 2nd, now 2nd is also executed for include and exclude, it returns to 1st. 
 * 
 * TC : O(2^N) for every element there are 2 options.
 * O(N) for every subsequence to print
 * O(N) stack space at any given time.
 * SC : O(N^2)
 * </pre>
 */
public class PrintAllSubsequences {

	public static void main(String[] args) {
		int arr[] = { 3, 1, 2, 5 };
//		printAllSubsequences(arr, new ArrayList<Integer>(), 0, arr.length);
		printAllSubsequencesWIthForLoop(arr);
	}

	private static void printAllSubsequences(int arr[], List<Integer> list, int currentIndex, int n) {
		if (currentIndex >= n) {
			System.out.println(list);
			return;
		}
		/**
		 * 
		 * <pre>
		 * we have 2 things here one is pick and another one is non pick 
		 * It means, we will current element and we skip the current element elements 
		 * to get the subsequences with and with out current element.
		 * Example : 
		 * {3,2,1}
		 * if curent element is 2,
		 * if we pick it 
		 * the subsequence will be {3,2,1}
		 * If we do not pick it the subsequence will be {3,1}
		 * 
		 * we will do it for each element so we get all the subsequences with and without this element
		 * </pre>
		 */

		// pick current element
		list.add(arr[currentIndex]);
		printAllSubsequences(arr, list, currentIndex + 1, n);
		// do not pick current element
		list.remove(list.size() - 1);
		printAllSubsequences(arr, list, currentIndex + 1, n);

	}

	// 3 1 2 5
	private static void printAllSubsequencesWIthForLoop(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				for (int k = i; k <= j; k++) {
					System.out.print(arr[k] + " ");
				}
				System.out.println();
			}
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (j == i) {
					continue;
				}
				System.out.print(arr[j] + " ");
			}
			System.out.println();
		}

	}
}
