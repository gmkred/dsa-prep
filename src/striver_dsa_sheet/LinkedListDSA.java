package striver_dsa_sheet;

/**
 * <pre>
 * What is linkedlist?
 * A dynamic array which which can grow as required.
 * But linkedlist unlike array is not contiguous.
 * It stores the Nodes in the heap where it finds a space to save.
 * 
 * How do we know the memory location if they are not saving in sequence?
 * Each Node contains the next Node's address.
 * 
 * N1[1, nextNode = N2] N2[3, nextNode = null]
 * 
 * first Node = head
 * last node = tail
 * 
 * Where it is used?
 * stack, queue
 * 
 * Real life :
 * we use in a browser. <-- and --> 
 * 
 * 1D LL ==> remembering only forward.
 * 
 * 2D LL or doubly linked list ==> remembering backward and forward nodes.
 * 
 * what is the data structure we use?
 * array.
 * 
 * Note: On linked list the question will ask to return the head or starting point.
 * Imp : 
 * 1. never tamper the head of the linkedlist, if done we will loose the pointer to whole list.
 * 
 * </pre>
 */
public class LinkedListDSA {

	static Node head = null;

	public static void main(String[] args) {
		Node node = new Node(3);
		node.next = new Node(4);
		System.out.println(node);
		createLinkedListUsingArray();
		traverse();
		lengthOfll();
		System.out.println(search(2));
//		deleteHead();
//		traverse();
//		deletionByValue(3);
//		traverse();
//		deleteTail();
//		traverse();
//		deletionByPosition(3);
//		traverse();
//		insertHead(12);
//		traverse();
//		insertTail(33);
//		traverse();
//		insert(22, 10);
//		traverse();
		insertBeforValue(44, 4);
		traverse();
	}

	private static void insertBeforValue(int val, int beforeVal) {
		Node newNode = new Node(val);
		int c = 0;
		if (head == null) {
			head = newNode;
			return;
		}
		if (head.val == beforeVal) {
			newNode.next = head;
			head = newNode;
			return;
		}

		Node prev = null, temp = head;
		while (temp != null) {
			if (temp.val == beforeVal) {
				newNode.next = prev.next;
				prev.next = newNode;
				return;
			}
			prev = temp;
			temp = temp.next;
		}
		try {
			throw new Exception("gefore value is not present");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void insert(int val, int k) {
		Node newNode = new Node(val);
		int c = 0;
		if (k == 1) {
			if (head == null) {
				head = newNode;
			} else {
				newNode.next = head;
				head = newNode;
			}
			return;
		}
		Node prev = null, temp = head;
		while (temp != null) {
			c++;
			if (c == k) {
				newNode.next = prev.next;
				prev.next = newNode;
				return;
			}
			prev = temp;
			temp = temp.next;
		}
		if (k > c) {
			try {
				throw new Exception("K is exceeded");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void insertTail(int k) {
		Node newNode = new Node(k);
		if (head == null) {
			head = newNode;
			return;
		}
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = newNode;
	}

	private static void insertHead(int k) {
		Node newNode = new Node(k);
		if (head == null) {
			head = newNode;
			return;
		}
		newNode.next = head;
		head = newNode;
	}

	/**
	 * <pre>
	 * Maintain 3 pointer 
	 * head, mover and temp
	 * Head will alwys points to the starting node
	 * temp is to point the current node
	 * mover is used to connect newly added node to the existing list, 
	 * after that point the mover to newly added node.
	 * </pre>
	 */
	private static void createLinkedListUsingArray() {
		int[] arr = { 1, 2, 3, 4 };
		head = null;
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
		System.out.println(head);
	}

	private static void traverse() {
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
		System.out.println();
	}

	private static boolean search(int k) {
		Node temp = head;
		while (temp != null) {
			if (temp.val == k) {
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	private static void lengthOfll() {
		Node temp = head;
		int length = 0;
		while (temp != null) {
			length++;
			temp = temp.next;
		}
		System.out.println(length);
	}

	private static void deleteHead() {
		if (head == null) {
			return;
		}
		head = head.next;

	}

	private static void deleteTail() {
		Node temp = head;
		Node mover = temp;
		if (head == null || head.next == null) {
			return;
		}
		// using 2 reference to find the last Node
//		while (temp.next != null) {
//			mover = temp;
//			temp = temp.next;
//		}
//		mover.next = null;
		// using temp.next.next == null
		// find the next to next element, if it null, then then the next element is last
		// element.
		// so reference the current node.next as null.
		while (temp.next.next != null) {
			temp = temp.next;
		}
		temp.next = null;

	}

	private static void deletionByPosition(int k) {
		Node temp = head;
		Node prev = null;// befor the head.
		int c = 0;
		if (head == null) {
			return;
		}
		if (k == 1) {
			head = head.next;
			return;
		}
//		while (temp != null) {// still in the linked list
//			c++;// increase the counter for current Node
//			if (c + 1 == k) {// if next node is Kth node
//				if (temp.next != null) {// check if kth node is not null
//					temp.next = temp.next.next;// if not null we can directly assign kth node next to k+1 th next to kth
//												// next.
//				} else {
//					temp = temp.next;// if kth next value is null node, then just assign that to k-1 next.
//				}
//				return;
//			}
//		}
		// or we can us an extra reference which is prev
		while (temp != null) {
			c++;
			// when counter == k, that means temp is pointing to exact element.
			// we just need to point prev.next to temp.next whihc will derefer the kth node
			// in the list.
			if (c == k) {
				prev.next = temp.next;
				return;
			}

			prev = temp;
			temp = temp.next;
		}

	}

	private static void deletionByValue(int k) {
		Node temp = head;
		Node prev = temp;
		if (head == null) {
			return;
		}
		while (temp != null) {
			if (head.val == k) {
				head = head.next;
				return;
			}
			if (temp.val == k) {
				prev.next = temp.next;
				return;
			}
			prev = temp;
			temp = temp.next;
		}
	}

	/**
	 * A LinkedList contanins an object or a value and an address to next list.
	 * 
	 */
	static class Node {
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

	}

}
