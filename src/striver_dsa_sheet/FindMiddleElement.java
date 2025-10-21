package striver_dsa_sheet;

public class FindMiddleElement {

	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 5, 6, 8, 9, 7, 4, 3 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
		System.out.println(findMiddleNode());

	}

	/**
	 * <pre>
	 * In odd list
	 * fp could reach to last node
	 * 
	 * In even list
	 * fp could reach till null
	 * 
	 * so have checks to break the loop if fp(for even) or fp.next(for odd) is null
	 * 
	 * after that sp will be exactly at the middle
	 * </pre>
	 */
	private static Node findMiddleNode() {
		Node sp = head;
		Node fp = head;

		while (fp != null && fp.next != null) {
			sp = sp.next;
			fp = fp.next.next;
		}
		return sp;

	}
}
