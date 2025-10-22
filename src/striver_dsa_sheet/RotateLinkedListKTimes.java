package striver_dsa_sheet;

public class RotateLinkedListKTimes {

	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 2, 3, 4, 5 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
		rotateListKTimes(2);
		node.traverse(head);

	}

	private static void rotateListKTimes_circularList(int k) {

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
