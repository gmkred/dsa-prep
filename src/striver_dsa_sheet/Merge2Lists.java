package striver_dsa_sheet;

import java.util.LinkedList;
import java.util.List;

public class Merge2Lists {
	static public Node head;

	public static void main(String[] args) {
		Node node = new Node();
		Node one = node.generateSInglyLinkedList(new int[] { 1, 1, 2, 4 });
		Node two = node.generateSInglyLinkedList(new int[] { 1, 3, 5, 6, 7 });
		node.traverse(one);
		node.traverse(two);
//		merger2lists(one, two);
//		node.traverse(head);
//		merge2Lists_better(one, two);
//		node.traverse(head);
		merge2Lists_optimal(one, two);
		node.traverse(head);
	}

	/**
	 * <pre>
	 * TC : O(N1+N2)
	 * SC : O(1)
	 * </pre>
	 */
	public static void merge2Lists_optimal(Node one, Node two) {
		Node temp1 = one;
		Node temp2 = two;
		Node dummyNode = new Node(-1);
		Node temp = dummyNode;
		while (temp1 != null && temp2 != null) {
			if (temp1.val <= temp2.val) {
				temp.next = temp1;
				temp1 = temp1.next;
			} else {
				temp.next = temp2;
				temp2 = temp2.next;
			}
			temp = temp.next;
		}
		while (temp1 != null) {
			temp.next = temp1;
			temp = temp.next;
			temp1 = temp1.next;
		}
		while (temp2 != null) {
			temp.next = temp2;
			temp = temp.next;
			temp2 = temp2.next;
		}
		head = dummyNode.next;
	}

	/**
	 * <pre>
	 * TC : O(N1 + N2)
	 *  SC: O(N)
	 * </pre>
	 */
	public static void merge2Lists_better(Node one, Node two) {
		Node temp1 = one;
		Node temp2 = two;
		Node dummyNode = new Node(-1);
		Node temp = dummyNode;
		while (temp1 != null && temp2 != null) {
			if (temp1.val <= temp2.val) {
				temp.next = new Node(temp1.val);
				temp1 = temp1.next;
			} else {
				temp.next = new Node(temp2.val);
				temp2 = temp2.next;
			}
			temp = temp.next;
		}
		while (temp1 != null) {
			temp.next = new Node(temp1.val);
			temp = temp.next;
			temp1 = temp1.next;
		}
		while (temp2 != null) {
			temp.next = new Node(temp2.val);
			temp = temp.next;
			temp2 = temp2.next;
		}
		head = dummyNode.next;

	}

	/**
	 * <pre>
	 * N = N1+N2
	 * 
	 * TC : O(2N)
	 * 
	 * SC : O(2N)
	 * </pre>
	 */
	public static void merger2lists(Node one, Node two) {

		List<Integer> list = new LinkedList<>();
		Node temp1 = one;
		Node temp2 = two;
		while (temp1 != null) {
			list.add(temp1.val);
			temp1 = temp1.next;
		}
		while (temp2 != null) {
			list.add(temp2.val);
			temp2 = temp2.next;
		}
		list.sort(Integer::compare);
		temp1 = head;
		for (int i : list) {
			if (temp1 == null) {
				head = new Node(i);
				temp1 = head;
			} else {
				temp1.next = new Node(i);
				temp1 = temp1.next;

			}
		}
	}

}
