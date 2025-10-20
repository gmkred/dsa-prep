package striver_dsa_sheet;

public class ReverseLinkedList {
	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 2, 3, 4, 5, 6 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
//		reverseLinkedList_optimal();
//		node.traverse(head);
//		reverseLinkedList_optimal_recursive(null, head, null);
//		node.traverse(head);
		Node returnedNode = reverseRecursively_striverway(head);
		node.traverse(returnedNode);
	}

	/**
	 * <pre>
	 * On each level we pass the head.next, until head is the last node.
	 * After that we return that node. from the last recursion call, 
	 * it is treated as newNode.
	 * 
	 * when it started return the nodes back, we perform reversing the links
	 * 
	 * if the recursion returns the last node, then current recursion is last but one node.
	 * 
	 * 
	 * so we need to make last's next point to current node.
	 * current.next which points to last node should now points to null.
	 * <span style="color : red">
	 * ******Remember to use temp node while swapping the links, 
	 * if returned node is used to manipulate the links, then we loose the list.
	 * </span>
	 * 
	 * 
	 * </pre>
	 */
	private static Node reverseRecursively_striverway(Node head) {

		if (head.next == null) {
			return head;
		}
		Node newNode = reverseRecursively_striverway(head.next);
		Node temp = head.next;
		temp.next = head;
		head.next = null;
		return newNode;
	}

	/**
	 * 
	 * Node prev = null; Node cur = head; Node next = null;
	 * 
	 */
	private static void reverseLinkedList_optimal_recursive(Node prev, Node cur, Node next) {
		if (cur == null) {
			head = prev;
			return;
		}
		next = cur.next;
		cur.next = prev;
		prev = cur;
		cur = next;

		reverseLinkedList_optimal_recursive(prev, cur, next);

	}

	/**
	 * <pre>
	 * { 1, 2, 3, 4, 5, 6 }
	 * 
	 * 
	 * without extra space we have to use 3 references
	 * 
	 * prev, cur and next
	 * 
	 * **prev actually hold the beginning of the reverse list.
	 * 
	 * firstly prev = null // because there is null in the end of the list. cur =
	 * head next = cur.next // pointer for the current linked list
	 * 
	 * 
	 * on each iteration we have to point the cur.next = prev, but if we directly do
	 * that we will loose the connection to forward list.
	 * 
	 * so, first assign next = cur.next thats how we point the original list. now we
	 * can assign cur.next = prev. once cur will be the beginning of the reverse
	 * list, so make prev = cur and cur = next // we should to same to next node
	 * till cur == null.
	 * 
	 * after this prev will be the beginning of the reverse list, so just assign
	 * head = prev
	 *
	 * </pre>
	 */
	private static void reverseLinkedList_optimal() {

		Node prev = null;
		Node cur = head;
		Node next = null;

		while (cur != null) {
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		System.out.println("hcf");
		head = prev;

	}

	/**
	 * use Stack to store the elements and later rewrite the List from last inserted
	 * value.
	 * 
	 */
	private static void reverseLinkedList() {

	}
}
