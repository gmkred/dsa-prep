package striver_dsa_sheet;

/**
 * find the length of the loop find the length of the list
 */
public class FindLengthOfLoopInLinkedList {

	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
		head.next.next.next.next.next.next.next = head.next.next;
		System.out.println(findLengthOfLoopInList());
		System.out.println(findLengthOfActualList());
	}

	/**
	 * 
	 * only loops length
	 */
	private static int findLengthOfLoopInList() {
		int length = 0;
		Node sp = head;
		Node fp = head;

		while (fp != null & fp != null) {
			sp = sp.next;
			fp = fp.next.next;
			if (sp == fp) {
				break;
			}
		}
		fp = fp.next;
		length++;
		while (sp != fp) {
			fp = fp.next;
			length++;

		}
		return length;
	}

	/**
	 * Actual list length
	 */
	private static int findLengthOfActualList() {
		int length = 0;
		Node sp = head;
		Node fp = head;

		while (fp != null & fp != null) {
			sp = sp.next;
			fp = fp.next.next;
			length++;
			if (sp == fp) {
				break;
			}
		}
		sp = head;
		while (fp != sp) {
			fp = fp.next;
			sp = sp.next;
			length++;
		}
		return length;
	}
}