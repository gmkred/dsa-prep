package striver_dsa_sheet;

import java.util.ArrayList;
import java.util.List;

public class SortLinkedList {
	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 3, 4, 1, 2, 5, 1, 8, 2 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
//		node.traverse(head);
//		sortList();
		node.traverse(head);
		head = listmergeSort(head);
		node.traverse(head);
	}

	private static Node listmergeSort(Node head) {
		if (head.next == null) {// return if there is only single element in the list
			return head;
		}
		// break down the lists into 2 lists
		Node mid = midNode(head);
		Node leftHead = head;
		Node rightHead = mid.next;
		mid.next = null;

		Node left = listmergeSort(leftHead);
		Node right = listmergeSort(rightHead);
		// merge the broken lists
		return merge(left, right);

	}

	private static Node merge(Node head, Node head2) {
		Node l = head;
		Node r = head2;
		Node dum = new Node(-1);
		Node dumPointer = dum;

		while (l != null && r != null) {
			if (l.val <= r.val) {
				dumPointer.next = l;
				dumPointer = dumPointer.next;
				l = l.next;
			} else {
				dumPointer.next = r;
				dumPointer = dumPointer.next;
				r = r.next;
			}
		}
		while (l != null) {
			dumPointer.next = l;
			dumPointer = dumPointer.next;
			l = l.next;
		}
		while (r != null) {
			dumPointer.next = r;
			dumPointer = dumPointer.next;
			r = r.next;
		}
		return dum.next;

	}

	private static Node midNode(Node head) {
		Node fp = head;
		Node mid = head;

		while (fp != null && fp.next != null) {
			fp = fp.next.next;
			// breaking here, because in even linked list, mid points to m2, but for merge
			// sort we need m1
			if (fp == null) {
				break;
			}
			mid = mid.next;
		}
		return mid;
	}

	/**
	 * <pre>
	 * TC : O(N) + O(N) + O(N log N)
	 * SC : O(N)
	 * </pre>
	 */
	public static void sortList() {
		Node temp = head;
		// O(N)
		List<Integer> list = new ArrayList<>();
		// O(N)
		while (temp != null) {
			list.add(temp.val);
			temp = temp.next;
		}
		// N * Log N
		list.sort(Integer::compare);
		temp = head;
		// O(N)
		for (int i : list) {
			temp.val = i;
			temp = temp.next;
		}

	}
}
