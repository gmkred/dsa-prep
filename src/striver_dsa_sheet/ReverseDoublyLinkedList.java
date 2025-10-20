package striver_dsa_sheet;

import java.util.Stack;

import striver_dsa_sheet.DoublyLinkedList.Node;

public class ReverseDoublyLinkedList {
	static Node head = null;
	static Node tail = head;

	public static void main(String[] args) {
		int[] arr = { 101, 202, 303, 404, 505, 606 };
		generateDoublyLinkedList(arr);
		traversedoublyLinkedlist();
//		reverseOnlyValues();
//		traversedoublyLinkedlist();
		reverseNodes();
		traversedoublyLinkedlist();
	}

	/**
	 * <pre>
	 * swapping a Node in DLS.
	 * --> represents next 
	 * <-- represents prev
	 * 
	 * 1 --> 2 --> 3 
	 *   <--    <--
	 *  
	 *  1 <-- 2 <-- 3 
	 *    -->    -->
	 * 
	 * how to swap 2 variables a and b
	 * a =5, b=6
	 * temp = a;
	 * a = b;
	 * b = temp
	 * 
	 * </pre>
	 * 
	 */
	private static void reverseNodes() {
		if (head == null || head.next == null) {
			return;
		}

		Node temp = head;
		Node prev = null;
		while (temp != null) {

			// use prev as placeholder when swapping 2 points.
			prev = temp.prev;
			temp.prev = temp.next;
			temp.next = prev;
			temp = temp.prev;
		}
		/**
		 * **********Important After all the iterations temp points to null, prev points
		 * to temp prev so
		 */
		head = prev.prev;

	}

	private static void reverseOnlyValues() {
		Node temp = head;

		Stack<Integer> stack = new Stack<Integer>();
		while (temp != null) {
			stack.push(temp.val);
			temp = temp.next;
		}
		temp = head;
		while (temp != null) {
			temp.val = stack.pop();
			temp = temp.next;
		}

	}

	public static void traversedoublyLinkedlist() {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
		System.out.println();
	}

	public static void traversedoublyLinkedlistReverse() {
		Node temp = tail;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.prev;
		}
		System.out.println();
	}

	public static void generateDoublyLinkedList(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			Node newNode = new Node(arr[i]);
			if (head == null) {
				head = newNode;
				tail = head;
			} else {
				newNode.prev = tail;
				tail.next = newNode;
				tail = newNode;
			}
		}
	}

	static class Node {

		int val;
		Node next;
		Node prev;

		public Node() {

		}

		public Node(int val) {
			this.val = val;

		}

		public Node(int val, Node next) {
			this.val = val;
			this.next = next;
		}

		public Node(Node next, int val, Node prev) {
			this.val = val;
			this.next = next;
			this.prev = prev;
		}

		@Override
		public String toString() {
			return "Node [val=" + val + ", next=" + next + ", prev=" + prev + "]";
		}

		public Node(Node prev, int val) {
			this.val = val;
			this.prev = prev;
		}
	}
}
