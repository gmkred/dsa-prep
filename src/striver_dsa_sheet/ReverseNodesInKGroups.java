package striver_dsa_sheet;

public class ReverseNodesInKGroups {
	static public Node head;

	public static void main(String[] args) {
		Node node = new Node();
		head = node.generateSInglyLinkedList(new int[] { 1, 2, 3, 4, 5 });
		node.traverse(head);
		reverseKGroup(2);
		node.traverse(head);
		System.out.println("-----------------");
		head = node.generateSInglyLinkedList(new int[] { 1, 2, 3, 4, 5 });
		node.traverse(head);
		reverseKGroup(3);
		node.traverse(head);
	}

	/**
	 * <pre>
	 * Reverse the groups individually by separating each group from the entire list.
	 * 
	 * Before separating, make sure we ave pointer that points to previousNode of group
	 * And nextNode of group.
	 * So, after reversing,
	 * previousNode should point to the -> beginning of reversed list i.e(ktNode),
	 * end of the reversed list (i.e. temp) -> should point to the nextNode.
	 * 
	 * applay logic only to the group that has length K, if less then avoid reversing.
	 * 
	 * 
	 * TC : O(N) for reversing  + O(N) for finding kthNode = O(2N)
	 * SP : O(1)
	 * </pre>
	 */
	public static Node reverseKGroup(int k) {
		if (head == null || head.next == null) {
			return head;
		}
		Node temp = head;
		Node next = null;
		Node prev = null;
		Node kthNode = null;
		while (temp != null) {
			kthNode = kthNode(temp, k);
			if (kthNode != null) {
				next = kthNode.next;
				kthNode.next = null;
				reverseList(temp);
				if (temp == head) {
					head = kthNode;
				}
				if (prev != null) {
					prev.next = kthNode;
				}
				temp.next = next;
			}
			prev = temp;
			temp = temp.next;
		}
		return head;
	}

	public static Node kthNode(Node head, int k) {
		if (head == null) {
			return head;
		}
		Node temp = head;
		int counter = 1;
		while (temp != null) {
			temp = temp.next;
			counter++;
			if (counter == k) {
				return temp;
			}
		}
		return null;
	}

	public static void reverseList(Node head) {
		Node temp = head;
		Node prev = null;
		Node next = null;
		while (temp != null) {
			next = temp.next;
			temp.next = prev;
			prev = temp;
			temp = next;
		}
	}

	/**
	 * recursive way of reversing
	 */
	public static void recursiveReverse(Node head) {
		if (head.next == null) {
			return;
		}
		recursiveReverse(head.next);
		Node nextNode = head.next;
		nextNode.next = head;
		head.next = null;
	}
}
