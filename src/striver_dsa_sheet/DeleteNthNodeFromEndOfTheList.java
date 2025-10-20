package striver_dsa_sheet;

public class DeleteNthNodeFromEndOfTheList {

	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 2, 3, 4, 5, 6 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
//		deleteNthNodeFromEnd(1);
//		node.traverse(head);
		deleteNthNodeFromEnd_Optimal(7);
		node.traverse(head);
	}

	/**
	 * <pre>
	 * Use slow and fast pointers.
	 * Fast should be ahead of N nodes compare to slow.
	 * { 1, 2, 3, 4, 5, 6 } N=2
	 * 
	 * 
	 * </pre>
	 */
	private static void deleteNthNodeFromEnd_Optimal(int N) {
		if (N == 0) {
			return;
		}
		Node dummyNode = new Node(-1);
		dummyNode.next = head;
		Node slow = head;
		Node fast = head;
		for (int i = 0; i < N; i++) {
			if (fast == null) {
				return;
			}
			fast = fast.next;

		}
		if (fast == null) {
			head = head.next;
			return;
		}
		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}
		slow.next = slow.next.next;
	}

	/**
	 * 
	 * TC : O(Length) + O(length - N) ==> O(2 length) SP : O(1)
	 * 
	 */
	private static void deleteNthNodeFromEnd(int N) {
		if (N == 0) {
			return;
		}
		Node dummyNode = new Node(-1);
		dummyNode.next = head;
		Node temp = head;

		int count = 0;
		while (temp != null) {
			count++;
			temp = temp.next;
		}
		int n = (count - N);// before the deleting node
		if (n == 0) {
			dummyNode.next = head.next;
			head = dummyNode.next;
		}
		temp = head;
		while (temp != null) {
			n--;
			if (n == 0) {
				break;
			}
			temp = temp.next;
		}
		temp.next = temp.next.next;
	}

}
