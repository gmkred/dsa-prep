package striver_dsa_sheet;

import java.util.ArrayList;
import java.util.List;

import striver_dsa_sheet.DoublyLinkedList.Node;

public class FindPairsOfSumInDLL {

	static Node head;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 2, 3, 4, 5, 9 };
		head = DoublyLinkedList.generateDoublyLinkedList(arr);
		DoublyLinkedList.traversedoublyLinkedlist();
		List<List<Integer>> list = findPairswithsumK(5);
		System.out.println(list);
		System.out.println(findPairsWIthSumK_Optimal(5));
	}

	/**
	 * <pre>
	 * 
	 * This works when DLL is sorted. 
	 * 
	 * 1, 2, 3, 4, 5, 9
	 * 
	 * 
	 * TC = O(N) + ~O(N) ==> O(2N)
	 * 
	 * SP : O(1)
	 * </pre>
	 */
	private static int findPairsWIthSumK_Optimal(int k) {
		int pairs = 0;
		Node temp1 = head;
		Node temp2 = head;
		// point to the last node.
		while (temp2.next != null) {
			temp2 = temp2.next;
		}
		// once right crossed left that means we need to break the loop
		while (temp2.val > temp1.val) {
			if (temp1.val + temp2.val == k) {
				pairs++;
				temp1 = temp1.next;
				temp2 = temp2.prev;
			} else if (temp1.val + temp2.val > k) {// when sum > k that means we need to reducse the sum so move
													// right towards left
				temp2 = temp2.prev;
			} else {// when sum < k then we need to increase the sum, so move left to gight
				temp1 = temp1.next;
			}
		}
		return pairs;
	}

	/**
	 * <pre>
	 * TC : nearly O(N^2)
	 * 
	 * SP : O(N)
	 * or 
	 * SP : O(1) if only store the count of the pairs
	 * 
	 * </pre>
	 */
	private static List<List<Integer>> findPairswithsumK(int k) {
		Node temp1 = head;
		Node temp2 = head;
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		while (temp1 != null) {
			temp2 = temp1.next;
			while (temp2 != null && (temp1.val + temp2.val) <= k) {
				List<Integer> l = new ArrayList<>();
				if (temp1.val + temp2.val == k) {
					l.add(temp1.val);
					l.add(temp2.val);
					list.add(l);
				}
				temp2 = temp2.next;
			}
			temp1 = temp1.next;
		}
		return list;
	}
}
