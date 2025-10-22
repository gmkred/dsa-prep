package striver_dsa_sheet.ddl;

public class Node {

	public int val;
	public Node next;
	public Node prev;

	public Node tail;

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

	public Node generateDoublyLinkedList(int[] arr) {
		Node head = null;
		Node temp = null;
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

	public void traversedoublyLinkedlist(Node head) {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
		System.out.println();
	}

	public void traversedoublyLinkedlistReverse(Node tail) {
		Node temp = tail;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.prev;
		}
		System.out.println();
	}
}
