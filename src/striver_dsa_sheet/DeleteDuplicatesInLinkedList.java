package striver_dsa_sheet;

public class DeleteDuplicatesInLinkedList {
	static Node head;
	static int arr[];

	public static void main(String[] args) {
		Node node = new Node();
		arr = new int[] { 1, 2, 2, 3, 4, 4, 5, 6, 7 };
		head = node.generateSInglyLinkedList(arr);
		node.traverse(head);
		deleteDuplicatesAndHAveUniques();
		node.traverse(head);
		System.out.println("-------------");
		arr = new int[] { 1, 2, 2, 3, 4, 4, 5, 6, 7 };
		head = node.generateSInglyLinkedList(arr);
		node.traverse(head);
		deleteDuplicateWithItsOriginalAsWell();
		node.traverse(head);
	}

	/**
	 * <pre>
	 * <a href=
	 * "https://leetcode.com/problems/remove-duplicates-from-sorted-list/">
	 * leetcode link solved by me
	 * </a>
	 * 
	 * 
	 * 1 2 2 3 4 4 5 6 7 
	 * 1 2 3 4 5 6 7
	 * </pre>
	 */
	private static void deleteDuplicatesAndHAveUniques() {
		if (head == null) {
			return;
		}
		if (head.next == null) {
			return;
		}
		Node temp = head;
		while (temp != null) {
			if (temp.next != null && temp.val == temp.next.val) {
				temp.next = temp.next.next;
			} else {
				temp = temp.next;
			}
		}
		return;
	}

	/**
	 * <pre>
	 * <a href=
	 * "https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/">
	 * leetcode link solved by me
	 * </a>
	 * 
	 * 
	 * 1 2 2 3 4 4 5 6 7 
	 * 1 3 5 6 7
	 * </pre>
	 */
	private static void deleteDuplicateWithItsOriginalAsWell() {
		if (head == null) {
			return;
		}
		if (head.next == null) {
			return;
		}
		Node prev = null;
		Node temp = head;

		while (temp != null) {
			if (temp.next != null && temp.val == temp.next.val) {
				while (temp.next != null && temp.val == temp.next.val) {
					temp = temp.next;
				}
				if (prev == null) {
					head = temp.next;
				} else {
					prev.next = temp.next;
				}
			} else {
				prev = temp;
			}
			temp = temp.next;
		}
		return;
	}
}
