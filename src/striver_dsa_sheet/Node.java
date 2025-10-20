package striver_dsa_sheet;

/**
 * Singly linked list
 * 
 */
class Node {
	int val;
	Node next;

	public Node() {
	}

	public Node(int val, Node next) {
		this.val = val;
		this.next = next;
	}

	public Node(int val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "Node [val=" + val + ", next=" + next + "]";
	}

	public Node generateSInglyLinkedList(int arr[]) {
		Node head = null;
		Node temp = null;
		Node mover = null;
		for (int i = 0; i < arr.length; i++) {
			if (head == null) {
				head = new Node(arr[i]);
				mover = head;
			} else {
				temp = new Node(arr[i]);
				mover.next = temp;
				mover = temp;
			}
		}
		return head;
	}

	public void traverse(Node head) {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
		System.out.println();
	}

}