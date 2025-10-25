package striver_dsa_sheet;

/**
 * <pre>
 * 
 * 1 -> 5 -> 9
 * if we add one 
 * new linked list  1-> 6 -> 0
 * 
 * </pre>
 * 
 */
public class Add1ToLinkedList {

	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 9, 9, 9 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
//		add_1_ToLinkedList_mysolution(1);
//		node.traverse(head);
//		//
//		arr = new int[] { 9, 9, 9 };
//		node = new Node();
//		head = node.generateSInglyLinkedList(arr);
//		add_1_ToLinkedList_mysolution(1);
//		node.traverse(head);
//		node.traverse(head);
//		Node resultHead = add_1_ToLinkedList_Optimal(head);
//		if (resultHead.val / 10 > 0) {
//			Node newNode = new Node(resultHead.val / 10);
//			resultHead.val = resultHead.val % 10;
//			newNode.next = resultHead;
//			head = newNode;
//		}
//		node.traverse(head);

		Node result = recursiveByStriver(head);
		node.traverse(result);
	}

	/**
	 * striver's recursive by returning the carry.
	 */
	private static Node recursiveByStriver(Node head) {

		int carry = helper(head);
		if (carry > 0) {
			Node newNode = new Node(carry);
			newNode.next = head;
			head = newNode;
		}
		return head;

	}

	private static int helper(Node head) {

		if (head == null) {
			return 1;
		}

		int carry = helper(head.next);
		int sum = head.val + carry;
		head.val = (sum) % 10;
		return sum / 10;
	}

	/**
	 * Use recursive approach
	 * 
	 * T.C = O(N) SP : O(N) --> recursive stack trace
	 * 
	 */
	private static Node add_1_ToLinkedList_Optimal(Node head) {

		if (head.next == null) {
			head.val = head.val + 1;
			return head;
		}
		Node returnedNode = add_1_ToLinkedList_Optimal(head.next);
		int val = returnedNode.val;
		int carry = val / 10;
		returnedNode.val = val % 10;
		head.val = carry + head.val;
		return head;
	}

	/**
	 * Reverse the list and add the one to the first node, if any carry the add that
	 * to next node. At the end again reverse the list.
	 * 
	 * TC : reversing O(2N) + O(N) = O(3N)
	 * 
	 * SC : O(1)
	 */
	private static void add_1_ToLinkedList_mysolution(int k) {

		reverse();

		Node cur = head;
		Node pre = null;
		int carry = 0;
		int sum = 0;
		head.val = head.val + k;
		while (cur != null) {
			sum = carry + cur.val;
			carry = (sum) / 10;
			cur.val = sum % 10;
			pre = cur;
			cur = cur.next;
		}
		if (carry > 0) {
			pre.next = new Node(carry);
		}

		reverse();

	}

	private static void reverse() {
		Node newNode = null;
		Node cur = head;
		Node next = null;

		while (cur != null) {
			next = cur.next;
			cur.next = newNode;
			newNode = cur;
			cur = next;

		}
		head = newNode;
	}
}
