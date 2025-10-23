package striver_dsa_sheet;

public class RotateLinkedListKTimes {

	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 2, 3, 4, 5 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
//		rotateListKTimes(2);
//		node.traverse(head);
		rotateListKTimes_circularList(2);
		node.traverse(head);

	}

	/**
	 * TC : O(N) + O(K%Length) SP : O(1)
	 */
	private static void rotateListKTimes_circularList(int k) {

		Node temp = head;

		int length = 1;
		while (temp.next != null) {
			temp = temp.next;
			length++;
		}
		temp.next = head;
		temp = temp.next;
		// we need to find the reminder for large number given for K
		int n = k % length;
		while (n != 0) {
			temp = temp.next;
			n--;
		}
		head = temp.next;
		temp.next = null;

	}

	/**
	 * TC : O(N)+O(K%N) SP : (1)
	 * 
	 * 
	 * Can we make it even better.
	 * 
	 */
	private static void rotateListKTimes(int k) {

		if (head == null || head.next == null) {
			return;
		}
		int len = 0;// for every len rotates it becomes an original list.
		Node temp = head;
		while (temp != null) {
			len++;
			temp = temp.next;
		}
		k = k % len;
		for (int i = 0; i < k; i++) {
			Node pre = null;
			Node cur = head;
			while (cur.next != null) {
				pre = cur;
				cur = cur.next;
			}
			cur.next = head;
			pre.next = null;

			head = cur;
		}
	}

}
