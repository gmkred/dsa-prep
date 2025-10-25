package striver_dsa_sheet;

/**
 * <pre>
 * 
 * problem statement 
 * 
 * Head = 1 -> 2 -> 3 -> 4 -> 5 -> 6
 * left =2
 * right =4
 * 
 * Head = 1 -> 4 -> 3 -> 2 -> 5 -> 6
 * 
 * </pre>
 */
public class ReverseBetweenSinglyLinkedList {

	public static void main(String[] args) {
		Node node = new Node();
		Node head = node.generateSInglyLinkedList(new int[] { 1, 2, 3, 4, 5, 6 });
		node.traverse(head);
		Node resultHead = reverseBetween(head, 2, 4);
		node.traverse(resultHead);
	}

	public static Node reverseBetween(Node head, int left, int right) {
		if (right - left == 0) {
			return head;
		}
		Node temp = head;
		Node befRev = null;
		int _l = left;
		/**
		 * <pre>
		 * first get to the point from where we need to reverse.
		 * </pre>
		 */
		while (temp != null && _l > 1) {
			befRev = temp;
			temp = temp.next;
			_l--;
		}
		/**
		 * <pre>
		 * we need to point reverse end, after reversing the list,
		 * we need to continue the remaining node pointing as reverse end next.
		 * </pre>
		 */
		Node revEnd = temp;
		Node cur = temp;

		/**
		 * No of nodes need to be reverse. From 2 to 4 we have 3 nodes so +1
		 */
		int noOfNodes = right - left + 1;
		Node next = null;
		Node prev = null;
		/**
		 * <pre>
		 * loop through the node which needs to be reversed. change the pointers for cur
		 * to point prev.
		 * 
		 * <pre>
		 */
		while (cur != null && noOfNodes > 0) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
			noOfNodes--;
		}
		/**
		 * <pre>
		 *  After iteration prev will be the reversed list head and 
		 *  cur point's to next which will be the continuation of normal list.
		 *
		 *  If before reverse is null that means, reverse is started from the beginning.
		 *  So, point the head to new head (reverse head).
		 * </pre>
		 */
		if (befRev != null) {
			befRev.next = prev;
		} else {
			head = prev;
		}
		/**
		 * <pre>
		 * reverse end's next to point the cur. becasue cur will be pointing to 
		 * the node just after the range of reverse list.
		 * </pre>
		 */
		revEnd.next = cur;

		return head;
	}
}
