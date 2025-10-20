package striver_dsa_sheet;

public class SortLinkedListWith0s1s2s {
	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 0, 1, 2, 0, 2, 1 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
		node.traverse(head);
//		sortListOnlyValues();
//		node.traverse(head);
		sortListNodeLinks();
		node.traverse(head);
	}

	/**
	 * <pre>
	 * Maintain 3 lists for 0's,1's and 2's
	 * 
	 * after that link them in a way that head --> list0 --> list1 --> list2
	 * 
	 * TC : O(N)
	 * SP : O(1) only extra references.
	 * </pre>
	 */
	private static void sortListNodeLinks() {
		if (head == null || head.next == null) {
			return;
		}
		Node temp = head;
		// dummy nodes before starting to point the actual nodes.
		Node temp0 = new Node(-1);
		Node temp1 = new Node(-1);
		Node temp2 = new Node(-1);
		// Extra pointer to point the beginning of each type of Lists (0's, 1's and 2's)
		Node _temp0_begin = temp0;
		Node _temp1_begin = temp1;
		Node _temp2_begin = temp2;
		// on each iteration get the value, and refer the node accordingly.
		while (temp != null) {
			if (temp.val == 0) {
				temp0.next = temp;
				temp0 = temp;
			} else if (temp.val == 1) {
				temp1.next = temp;
				temp1 = temp;
			} else if (temp.val == 2) {
				temp2.next = temp;
				temp2 = temp;

			}
			temp = temp.next;
		}
		/**
		 * <pre>
		 * Now, link all 3 lists.
		 * 
		 * Remember to remove extra links after temp2, it's possible to have 0's and 1's
		 * next to temp2
		 * 
		 * After that link temp1 to temp2 Note : Avoid dummy node.
		 * 
		 * i.e end of temp1 to beginning of temp2 (temp1.next = _temp2_begin.next)
		 * 
		 * end of temp0 to beginning of temp1 (temp0.next = _temp1begin.next)
		 * 
		 * Point the head to _temp0_begin
		 * 
		 * return head;
		 * 
		 * <pre>
		 * 
		 */
		temp2.next = null;
		temp1.next = _temp2_begin.next;
		temp0.next = _temp1_begin.next;
		head = _temp0_begin.next;
		temp0 = temp1 = temp2 = _temp0_begin = _temp1_begin = _temp2_begin = null;

	}
/**
 * TC : O(2N)
 * SP : O(N)
 * 
 * */
	private static void sortListOnlyValues() {
		int count0 = 0, count1 = 0, count2 = 0;
		Node temp = head;
		while (temp != null) {
			if (temp.val == 0)
				count0++;
			if (temp.val == 1)
				count1++;
			if (temp.val == 2)
				count2++;
			temp = temp.next;
		}
		temp = head;
		while ((count0 + count1 + count2) != 0) {
			if (count0 > 0) {
				temp.val = 0;
				count0--;
			} else if (count1 > 0) {
				temp.val = 1;
				count1--;
			} else if (count2 > 0) {
				temp.val = 2;
				count2--;
			}
			temp = temp.next;
		}

	}

}
