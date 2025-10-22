package striver_dsa_sheet;

public class DeleteMiddleNodeOfList {

	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
		deleteMiddleNode();
		node.traverse(head);
		arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		node = new Node();
		head = node.generateSInglyLinkedList(arr);
		deleteMiddleNode();
		node.traverse(head);

	}

	/**
	 * <pre>
	 * reach node previous to middle node so we can easily delete the middle
	 * ODD
	 * { 1, 2, 3, 4, 5, 6, 7 }
	 * node before middle will be N/2 = 7/2 = 3.5 => 3
	 * EVEN
	 * { 1, 2, 3, 4, 5, 6, 7, 8 }
	 * node before middle node will be N/2 = 8/2 = 4
	 * 
	 * so, for ODD and EVEN the node before middle node will be N/2
	 * 
	 * OUTPUT
	 * 1 2 3 5 6 7 
	 * 1 2 3 4 6 7 8
	 * 
	 * </pre>
	 */
	private static void deleteMiddleNode() {
		Node tepm = head;
		Node sp = head;
		Node fp = head;
		while (fp != null && fp.next != null) {
			fp = fp.next.next;
			// we want out sp to point node before middle node so moving sp only if it wont
			// point to middle node
			// skip sp
			if (fp == null || fp.next == null) {
				break;
			}
			sp = sp.next;
		}
		sp.next = sp.next.next;
	}
}
