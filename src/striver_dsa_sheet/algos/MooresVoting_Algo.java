package striver_dsa_sheet.algos;

/**
 * <pre>
 * Given array
 * { 2, 2, 1, 3, 1, 1, 3, 1, 1 }
 * 
 * Moore's voting steps:
 * have 2 variables, one for element and another for count.
 * take first element which is 2 
 * el = 2
 * count = 1
 * {2,2,1,3}
 * go through each element, if 2 is found count++ or else count--.
 * when count become zero that means there are equal number of other elements as 2 count.
 * so fro mthe next element again take that element and start doing same process.
 * el =1
 * count =1
 * {1,1,3,1}
 * again 
 * el = 1
 * count = 1
 * {1}
 * At the end we have 1 with count 1 which might be the majority if there is a majority element in the array. 
 * 
 * Note: Re-check if the moore's voted element is the majority element if question does not say that there will
 * a majority element
 * 
 * 
 * TC : O(2N)
 * SC : O(1)
 * 
 * </pre>
 */
public class MooresVoting_Algo {

	public static void main(String[] args) {
		int arr[] = { 2, 2, 1, 3, 1, 1, 3, 1, 1 };
		System.out.println(majorityElement(arr));
	}

	private static int majorityElement(int arr[]) {
		int el = 0;
		int count = 0;

		for (int i = 0; i < arr.length; i++) {
			if (count == 0) {
				el = arr[i];
				count++;
			} else if (el == arr[i]) {
				count++;
			} else {
				count--;
			}
		}
		// check only if the question does not say that there is a majority element.
		count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == el) {
				count++;
			}
		}
		return count > arr.length / 2 ? el : -1;
	}
}
