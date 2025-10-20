package striver_dsa_sheet;

/**
 * <pre>
 * Given head of linked list, group all the odd and even nodes.
 * 
 * 
 * 1->2->3->4->5->6
 * 
 * 1->3->5->2->4->6
 * 
 * </pre>
 */
public class GroupOddAndEvenNodes {
	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 2, 3, 4, 5, 6 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
//		groupOddAndEvenNodeValue();
//		node.traverse(head);
		groupOddAndEvenNodeLinks();
		node.traverse(head);
	}

//grouing odd and even by values.
	/**
	 * TC = O(2N) SP = O(N) - extra array
	 */
	private static void groupOddAndEvenNodeValue() {
		int[] oddAndEven = new int[arr.length];
		Node temp = head;
		int c = 0;
		if (head == null || head.next == null) {
			return;
		}
		// we need to check current value and current's next. if next is null then next
		// -> next will also be null
		while (temp != null && temp.next != null) {
			oddAndEven[c++] = temp.val;
			temp = temp.next.next;
		}
		// remember after while loop exits chaeck if temp is pointing to last node.
		// it is possible because in the while loop condition we are checking temp.next
		// == null
		// which will break the loop even if temp != null
		if (temp != null) {
			oddAndEven[c++] = temp.val;
		}
		temp = head.next;
		while (temp != null && temp.next != null) {
			oddAndEven[c++] = temp.val;
			temp = temp.next.next;
		}
		// remember after while loop exits chaeck if temp is pointing to last node.
		// it is possible because in the while loop condition we are checking temp.next
		// == null
		// which will break the loop even if temp != null
		if (temp != null) {
			oddAndEven[c++] = temp.val;
		}
		temp = head;
		for (int i = 0; i < oddAndEven.length; i++) {
			temp.val = oddAndEven[i];
			temp = temp.next;
		}
	}

	// use O(1) --> That means change the links between list

	/**
	 * <pre>
	 * 1->2->3->4->5
	 * 
	 * 
	 * 1->2->3->4->5->6 
	 * 
	 * 1->3->5->2->4->6
	 * </pre>
	 */
	private static void groupOddAndEvenNodeLinks() {
		if (head == null || head.next == null) {
			return;
		}
		Node odd = head;
		Node even = head.next;
		Node _even = even;// need to attach even beginning to odd next.
		// Even is always ahead of odd so check even and even.next
		while (even != null && even.next != null) {
			odd.next = odd.next.next;
			even.next = even.next.next;
			odd = odd.next;
			even = even.next;
		}
		odd.next = _even;
	}

}
