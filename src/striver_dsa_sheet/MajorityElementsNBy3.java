package striver_dsa_sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Find the majority elements that are N/3
 * 
 * this means there should be only 2 maximum majority elements. because N/3 is
 * third part of N, so at max 2 elements can be > N/3
 * steps:
 * take el1, el2, elC1, elc2
 * 
 * track any 2 elements, if any new elements other than 2 elements appear, do elc1--, elc2--
 * because if we check only one element at a time like
 * el, elc at this time any element other than the current el appears, we need to do elc--, so same apply when 
 * we check for 2 elements in same loop, if there is any new element other than el1, and el2 reduce counters of both el1 and el2.
 * 
 * 
 * </pre>
 */
public class MajorityElementsNBy3 {

	public static void main(String[] args) {
		int arr[] = { 2, 3, 1, 3, 2, 1, 2, 3 };
		System.out.println(majorityElementsNBy3(arr));

	}

	private static List<Integer> majorityElementsNBy3(int arr[]) {

		List<Integer> list = new ArrayList<>();
		int el1 = 0;
		int el2 = 0;
		int elc1 = 0;
		int elc2 = 0;

		for (int i = 0; i < arr.length; i++) {
			if (elc1 == 0 && arr[i] != el2) {
				el1 = arr[i];
				elc1++;
			} else if (elc2 == 0 && arr[i] != el1) {
				el2 = arr[i];
				elc2++;
			} else if (el1 == arr[i]) {
				elc1++;
			} else if (el2 == arr[i]) {
				elc2++;
			} else {
				elc1--;
				elc2--;
			}
		}
		// If question does not mention that there will be majority elements, re-check
		// the el1 and el2 in the array once again;
		list.add(el1);
		list.add(el2);
		return list;

	}

}
