package striver_dsa_sheet;

import java.util.Stack;

public class ListPalindrome {

	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 2, 3, 2, 1 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
		System.out.println(isListPalindrome_extreme());

	}

	/**
	 * TC : O(2N)
	 * SP : O(N)
	 * 
	 * */
	private static boolean isListPalindrome_extreme() {
		Stack<Integer> stack = new Stack();
		Node temp = head;
		while (temp != null) {
			stack.push(temp.val);

			temp = temp.next;
		}
		temp = head;
		while (temp != null) {
			if (temp.val != stack.pop()) {
				return false;
			}
			temp = temp.next;
		}
		return true;
	}
}
