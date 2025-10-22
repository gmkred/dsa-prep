package striver_dsa_sheet;

/**
 * <pre>
 * Why and how tortoise and hare (slow pointer and fast pointer works perfectly)
 * 
 * once slow pointer enters the loop, the fast pointer tries to reach slow
 * pointer.
 * 
 * Here comes the magic why fast pointers exactly meet slow pointer without
 * cross over.
 * 
 * think the distance between fast to slow as D. Not slow to fast.
 * 
 * fast is moving 2 steps at a time slow step moving 1 step at a time.
 * 
 * by this faster is always only one step closer each time to slow pointer. and
 * soon they will intersect at a node.
 * 
 * that why 2 steps work greatly but not 3 and others.
 * </pre>
 */
public class DetectCircularLoop {

	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
		// end of the list points to 3
		head.next.next.next.next.next.next.next = head.next.next;
		System.out.println(detectLoopInList_slowAndFastpointers().val);

	}

	private static Node detectLoopInList_slowAndFastpointers() {

		Node sp = head;
		Node fp = head;
		// this will tell only cycle is there or not.

		while (fp != null && fp.next != null) {
			sp = sp.next;
			fp = fp.next.next;
			if (sp == fp) {
				break;
			}
		}
		// now we found that there is a cycle
		// to find exactly where it is we need to point sp to the beginning
		// fp should be at its current node, but this time move one step instead of 2
		// steps.
		sp = head;
		while (sp != fp) {

			sp = sp.next;
			fp = fp.next;
		}

		return sp;
	}

	/**
	 * <pre>
	 * use hashmap to store the current nodes and check if its already present and 
	 * when ever we find any node that is already existing that is where the loop started. 
	 * because the node is already exists.
	 * 
	 * TC : ~O(N)
	 * SP : O(N)
	 * 
	 * </pre>
	 */
	private static void detectLoopInList_bruteforce()

	{

	}

}
