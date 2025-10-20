package striver_dsa_sheet;

public class AddNumbersInTwoLinkedLists {

	public static void main(String[] args) {
		int arr[] = { 2, 4, 6 };
		int arr1[] = { 3, 8, 7 };
		Node node = new Node();
		Node head1 = node.generateSInglyLinkedList(arr);
		Node head2 = node.generateSInglyLinkedList(arr1);
		node.traverse(head1);
		node.traverse(head2);
		Node sumNode = addTwoLinkedLists(head1, head2);
		node.traverse(sumNode);
	}

	public static Node addTwoLinkedLists(Node head1, Node head2) {

		int carry = 0;
		Node temp1 = head1;
		Node temp2 = head2;
		Node dummyNode = new Node(-1);
		Node temp = dummyNode;
		int sum = 0;
		while (temp1 != null && temp2 != null) {
			sum = (carry + temp1.val + temp2.val);
			carry = sum / 10;
			temp.next = new Node(sum % 10);
			temp = temp.next;
			temp1 = temp1.next;
			temp2 = temp2.next;
		}

		while (temp1 != null) {
			sum = (carry + temp1.val) % 10;
			carry = sum / 10;
			temp.next = new Node(sum);
			temp = temp.next;
			temp1 = temp1.next;
		}

		while (temp2 != null) {
			sum = (carry + temp2.val) % 10;
			carry = sum / 10;
			temp.next = new Node(sum);
			temp = temp.next;
			temp2 = temp2.next;
		}
		if (carry > 0) {
			temp.next = new Node(carry);
			temp = temp.next;
		}
		return dummyNode.next;

	}

}
