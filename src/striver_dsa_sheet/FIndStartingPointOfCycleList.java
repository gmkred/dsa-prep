package striver_dsa_sheet;

/**
 * <a href=
 * "https://www.youtube.com/watch?v=2Kd0KKmmHFc&list=PLgUwDviBIf0rAuz8tVcM0AymmhTRsfaLU&index=18">Youtube
 * link<a>
 * 
 * <pre style ="color:red; font-size:14px;font-weight:bold">
 * Assume that sp moved 3 steps (points to the beginning of the loop, just assume)
 * Then fp will move sp*2 steps => 3*2 =>6
 * Think that sp distance from head is L1
 * Then fp will be 2 * L1. But from the loop it will be L1
 * Once sp enters the loop:
 * The distance between fp  sp is D
 * And every loop fp is nearing to sp by 2 steps, and sp is running away by 1 step.
 * So the distance reduced between them is 
 * (D – 2 +1) i.e fp is trying to reach sp by 2 so reducing the distance by 2 and sp is running away by 1 so +1 (D-1)
 * The net distance covered by fp is D ,D-1, D-2,D-3 ….0 so D 
 * And each tiem 2 steps so D*2  2D steps
 * Sp would have taken D steps
 * After sp enters the loop it has covered D steps and fp covered 2D steps.
 * The length of the loop is L1 + D
 * Now, The distance between starting of the loop to Collison point is D.
 * So from Collison point to starting point of the loop is L1
 * So to find exact cycle starting point
 * Start sp from head again
 * Fp from there itsel but only one step move.
 * When they meet at one point that will be the start of the cycle
 * 
 * </pre>
 * 
 */
public class FIndStartingPointOfCycleList {
	static Node head = null;
	static int arr[];

	public static void main(String[] args) {
		arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		Node node = new Node();
		head = node.generateSInglyLinkedList(arr);
		head.next.next.next.next.next.next.next = head.next.next;
		System.out.println(startingPointOfCcle_FastAndSlowPointer().val);
	}

	/**
	 * <pre>
	 * 
	 * Use fp and sp to find the cycle.
	 * after that sp = head and fp should move one step instead of 2 steps by this 
	 * they meet at the starting of the cycle.
	 * 
	 * </pre>
	 */
	private static Node startingPointOfCcle_FastAndSlowPointer() {

		Node sp = head;
		Node fp = head;
		while (fp != null && fp.next != null) {
			sp = sp.next;
			fp = fp.next.next;
			if (sp == fp) {
				break;
			}
		}
		// sp and fp ar at same position

		sp = head;
		while (sp != fp) {
			sp = sp.next;
			fp = fp.next;
		}

		return sp;
	}

	/**
	 * <pre>
	 * Brute force is storign the nodes in hash and as soon as a node is already present in
	 * the hash, that is where the loop started
	 * 
	 * TC : O(N) + hash operation of storing, finding etc..
	 * SP O(N) : for hash
	 * </pre>
	 */
	private static Node startingPointOfCcle_bruteForce() {

		return null;
	}
}
