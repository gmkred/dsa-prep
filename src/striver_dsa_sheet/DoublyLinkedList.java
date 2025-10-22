package striver_dsa_sheet;

public class DoublyLinkedList {

	static Node head = null;
	static Node tail = head;

	public static void main(String[] args) {
		int[] arr = { 101, 202, 303, 404, 505, 606 };
		generateDoublyLinkedList(arr);
		traversedoublyLinkedlist();
//		traversedoublyLinkedlistReverse();
//		deleteHead();
//		traversedoublyLinkedlist();
//		deleteTail();
//		traversedoublyLinkedlist();
//		deleteKthNode(2);
//		traversedoublyLinkedlist();
//		Node kthNode = getNode(2);
//		System.out.println(kthNode.val);
//		deleteNode(kthNode);
		traversedoublyLinkedlist();
		insertAtHead(99);
		traversedoublyLinkedlist();
		insertAtTail(100);
		traversedoublyLinkedlist();
		insertAtK(50, 8);
		traversedoublyLinkedlist();
		Node kthNode = getNode(2);
		insertNodeAtK(kthNode, 125);
		traversedoublyLinkedlist();
	}

	// insert new node before the given node.
	private static void insertNodeAtK(Node node, int val) {
		Node newNode = new Node(val);
		Node prev = node.prev;
		Node temp = node;
		if (temp.next != null) {
			newNode.next = temp;
			temp.prev = newNode;
			newNode.prev = prev;
			prev.next = newNode;
		}
	}

	private static void insertAtK(int val, int k) {
		if (k == 1) {
			insertAtHead(val);
			return;
		}
		Node newNode = new Node(val);
		int count = 0;
		Node temp = head;
		Node prev = null;
		while (temp != null) {
			count++;
			if (count == k) {
				newNode.next = temp;
				temp.prev = newNode;
				newNode.prev = prev;
				prev.next = newNode;
				return;
			}
			prev = temp;
			temp = temp.next;
		}
		if (count + 1 == k) {
			tail.next = newNode;
			newNode.prev = tail.prev;
			tail = newNode;
		}

	}

	private static void insertAtTail(int val) {
		Node newNode = new Node(val);
		if (head == null) {
			head = newNode;
		}
		tail.next = newNode;
		tail = newNode;
	}

	private static void insertAtHead(int val) {
		Node newNode = new Node(val);
		if (head == null) {
			head = newNode;
		}
		newNode.next = head;
		head.prev = newNode;
		head = newNode;
	}

	/**
	 * <pre>
	 * node != head never
	 * The given node is present somewhere in the doubly linked list.
	 * we need to delete that node from the doubly linked list.
	 * It may or may not contain next so check properly.
	 * </pre>
	 */
	private static void deleteNode(Node node) {
		Node prev = node.prev;
		if (node.next == null) {
			prev.next = null;
			tail = prev;
			prev.next = node.next;
		} else {
			prev.next = node.next;
			node = node.next;
			node.prev = prev;
		}

	}

	private static Node getNode(int k) {

		if (head == null) {
			return null;
		}
		if (k == 1) {
			return head;
		}
		Node temp = head;
		int count = 0;
		while (temp != null) {
			count++;
			if (count == k) {
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}

	private static void deleteKthNode(int k) {
		if (head == null) {
			return;
		}
		if (k == 1) {
			deleteHead();
			return;
		}
		int c = 0;
		Node prev = null;
		Node temp = head;
		while (temp != null) {
			c++;
			if (c == k) {
				if (temp.next == null) {
					tail = tail.prev;
					tail.next = null;
					return;
				}
				prev.next = temp.next;
				temp = temp.next;
				temp.prev = prev;
				return;
			}
			prev = temp;
			temp = temp.next;

		}

	}

	private static void deleteTail() {
		if (head == null) {
			return;
		}
		if (head.next == null) {
			head = null;
			tail = null;
		}
		tail = tail.prev;
		tail.next = null;

	}

	private static void deleteHead() {
		if (head == null) {
			return;
		}
		if (head.next == null) {
			head = null;
			tail = null;
		}
		head = head.next;
		head.prev = null;
	}

	public static Node generateDoublyLinkedList(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			Node newNode = new Node(arr[i]);
			if (head == null) {
				head = newNode;
				tail = head;
			} else {
				newNode.prev = tail;
				tail.next = newNode;
				tail = newNode;
			}
		}
		return head;
	}

	public static void traversedoublyLinkedlist() {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
		System.out.println();
	}

	public static void traversedoublyLinkedlistReverse() {
		Node temp = tail;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.prev;
		}
		System.out.println();
	}

	static class Node {

		int val;
		Node next;
		Node prev;

		public Node() {

		}

		public Node(int val) {
			this.val = val;

		}

		public Node(int val, Node next) {
			this.val = val;
			this.next = next;
		}

		public Node(Node next, int val, Node prev) {
			this.val = val;
			this.next = next;
			this.prev = prev;
		}

		@Override
		public String toString() {
			return "Node [val=" + val + ", next=" + next + ", prev=" + prev + "]";
		}

		public Node(Node prev, int val) {
			this.val = val;
			this.prev = prev;
		}
	}
}
