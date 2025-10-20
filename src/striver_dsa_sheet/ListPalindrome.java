package striver_dsa_sheet;

public class ListPalindrome {

	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 2, 3, 2, 1 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
		
	}
}
