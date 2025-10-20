package striver_dsa_sheet;

import striver_dsa_sheet.DoublyLinkedList.Node;
/**
 * yet to solve
 * */
public class ReverseBetweenSinglyLinkedList {

	static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

		@Override
		public String toString() {
			return "ListNode [val=" + val + ", next=" + next + "]";
		}
	}

	static ListNode head = null;

	public static void main(String[] args) {
		head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
		reverseBetween(head, 2, 4);
		traversedoublyLinkedlist();

	}

	public static void traversedoublyLinkedlist() {
		ListNode temp = head;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
		System.out.println();
	}

	public static ListNode reverseBetween(ListNode head, int left, int right) {

		ListNode l = head;
		ListNode prev = null;
		ListNode r = null;
		int len = right - left;
		int c = 0;
		if (right - left == 0) {
			return head;
		}
		while (l != null) {
			c++;
			if (c >= left && c <= right) {
				prev.next = r;
				l.next = r.next;
				r.next = l;
			} else {
				prev = l;
			}
			l = l.next;
			if (l != null) {
				r = l.next;
			}
		}
		return head;
	}
}
