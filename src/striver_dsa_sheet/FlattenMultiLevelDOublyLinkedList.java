package striver_dsa_sheet;

import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

//Definition for a Node.
/**
 * @see <a href=
 *      "https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/"
 *      >leetcode problem</a>
 * 
 */

public class FlattenMultiLevelDOublyLinkedList {
	public static void main(String[] args) {

	}

	public Node flatten(Node head) {
		if (head == null) {
			return null;
		}
		Stack<Node> stack = new Stack<Node>();
		Node temp = head;
		Node prev = null;
		while (temp != null) {
			if (temp.child != null) {
				if (temp.next != null) {
					stack.push(temp.next);
				}
				prev = temp;
				temp = temp.child;
				if (prev != null) {
					prev.next = temp;
					temp.prev = prev;
				}
				prev.child = null;
			} else if (temp.next == null && stack.size() > 0) {
				Node _temp = stack.pop();
				temp.next = _temp;
				_temp.prev = temp;
			} else {
				prev = temp;
				temp = temp.next;
			}
		}
		return head;
	}

	static class Node {
		public int val;
		public Node prev;
		public Node next;
		public Node child;

		public Node(Node prev, int val, Node next, Node child) {
			this.val = val;
			this.prev = prev;
			this.next = next;
			this.child = child;
		}

		public Node(int val) {
			this.val = val;
		}

		public Node() {

		}

	}
}