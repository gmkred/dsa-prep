package striver_dsa_sheet.ddl;

import java.util.ArrayList;
import java.util.List;

public class DeleteAllOccurrencesInDDL {
	static Node head;
	static int arr[];

	public static void main(String[] args) {
		Node node = new Node();
		arr = new int[] { 10, 1, 2, 3, 10, 4, 5, 6, 10 };
		int k = 1;
		head = node.generateDoublyLinkedList(arr);
		node.traversedoublyLinkedlist(head);
//		deleteAllOccurrencesOfK(k);
//		node.traversedoublyLinkedlist(head);
		deleteAllOccurancesOfK_Optimal(10);
		node.traversedoublyLinkedlist(head);
	}

	/**
	 * <pre>
	 * 10, 1, 2, 3, 10, 4, 5, 6, 10
	 * 
	 * TC : O(N)
	 * SP : O(1)
	 * 
	 * </pre>
	 */
	public static void deleteAllOccurancesOfK_Optimal(int k) {
		if (head == null) {
			return;
		}
		if (head.next == null && head.val == k) {
			head = null;
			return;
		}
		Node before = null;
		Node temp = head;
		Node after = null;
		while (temp != null) {
			if (temp.val == k) {
				if (temp == head) {
					head = head.next;
				}
				after = temp.next;
				before = temp.prev;
				if (after != null) {
					after.prev = before;
				}
				if (before != null) {
					before.next = after;
				}
				if (temp != null) {
					temp = after;
				}
			} else {
				temp = temp.next;
			}
		}
	}

	/**
	 * Brute force.
	 * 
	 * store the values in an arr first then update the DLL
	 * 
	 * TC : O(N) + O(N) => !O(2N)
	 * 
	 * SP : O(N)
	 * 
	 */
	public static void deleteAllOccurrencesOfK(int k) {
		if (head == null) {
			return;
		}
		if (head.next == null && head.val == k) {
			head = null;
			return;
		}

		List<Integer> list = new ArrayList<>();

		Node temp = head;
		while (temp != null) {
			if (temp.val != k) {
				list.add(temp.val);
			}
			temp = temp.next;
		}
		temp = head;
		for (int i = 0; i < list.size(); i++) {
			temp.val = list.get(i);
			temp = temp.next;
		}
		Node before = temp.prev;
		if (before != null) {// to avoid if there is only one node and its not k
			temp.prev = null;
			before.next = null;
		}
	}
}
