package striver_dsa_sheet;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>
 *      3->1-> 
 *             4-> 5-> 6-> null
 * 1->2->4->5->
 * 
 * 
 * we have 2 heads.
 * head1 = 3->1-><span style="color:reD">4</span>->5->6->null
 * head2 = 1->2->4->5-><span style="color:reD"> 4</span>->5->6->null
 * 
 * Note: we need to find the Node not the value;
 * 
 * </pre>
 */

public class FindYIntersection {

	static Node head1 = null;
	static Node head2 = null;
	static int arr[];

	public static void main(String[] args) {
		Node node = new Node();
		arr = new int[] { 3, 1 };
		head1 = node.generateSInglyLinkedList(arr);
		arr = new int[] { 1, 2, 4, 5, 4, 5, 6 };
		head2 = node.generateSInglyLinkedList(arr);
		head1.next.next = head2.next.next.next.next;
//		System.out.println(findYIntersection());
		System.out.println(NodefindYIntersection_striver_optimal());

	}

	/**
	 * <pre>
	 *       3->1-> 
	 *             4-> 5-> 6-> null
	 * 1->2->4->5->
	 * 
	 * This solution will assign 
	 * temp1 = head2 if temp1 reaches end.
	 * temp2 = head1 if teamp2 reaches end.
	 * 
	 * This will work because.
	 * look at the given lists.
	 * 
	 * list1 reaches end first 
	 * 
	 * list2 reaches end after (L2-L1) steps becasue list2 has 2 more nodes so it reaches
	 * 2 steps slower than list1
	 * 
	 * when temp2 points to head1, temp1 points (L2-L1) steps further in head2
	 * Now both temp1 and temp2 will be in sync from there each points moves by one and find 
	 * intersection without using extra space;
	 * 
	 * In Simple terms : 
	 * To make sure the smallest to match the longest list, just reverse the lists pointer
	 * so they will be in sync at one point.
	 * <span style="color:red">
	 * its like temp1 covers 4 steps first
	 * temp2 covers 6 stes first
	 * Now, temp1 should cover 6 steps and temp2 covers 4 steps to reach at same point.
	 * </span>
	 * N1 < N2
	 * TC: O()
	 * SP : O(1)
	 * 
	 * 
	 * </pre>
	 */

	private static Node NodefindYIntersection_striver_optimal() {
		Node intersection = null;
		Node temp1 = head1;
		Node temp2 = head2;
		intersection = head1;
		while (temp1 != temp2) {
			if (temp1 == temp2) {
				return temp1;
			}
			if (temp1 == null) {
				temp1 = head2;
			}
			if (temp2 == null) {
				temp2 = head1;
			}

			temp1 = temp1.next;
			temp2 = temp2.next;
		}

		return intersection;
	}

	/**
	 * <pre>
	 * find the length of each list
	 *      3->1-> 
	 *    			4-> 5-> 6-> null
	 * 1->2->4->5->
	 * 
	 * l1 = 5
	 * l2 = 7
	 * 
	 *  diff = 7-5 = 2
	 *  Put a pointer after 2 nodes in the longest list.
	 *  From there simultaneously compare current nodes of each lists.
	 *  If any intersection occurs return that Node
	 *  
	 *  TC : 
	 *  if N1 < N2
	 *  finding lengths O(N1) + O(N2)
	 *  setting pointer after  l2-l1 will be O(N2-N1)
	 *  parallel movement of each list's temp variable O(N1);
	 *  O(N1+N2+ (N2-N1) + N1) = O(2N2+N1) 
	 *  
	 *  SP: O(1)
	 * 
	 * </pre>
	 */
	private static Node findYIntersection_striver_better() {

		return null;
	}

	/**
	 * <pre>
	 * TC : 
	 * N1 < N2
	 * O(N1 + (N2-N1))
	 * 
	 * SP : O(N1+N2) ||
	 * 
	 * we can use Map in place of Set.
	 * </pre>
	 */
	private static Node findYIntersection() {
		Set<Node> set = new HashSet();
		Node temp1 = head1;
		Node temp2 = head2;
		while (temp1 != null && temp2 != null) {
			if (!set.add(temp1)) {
				return temp1;
			}
			if (!set.add(temp2)) {
				return temp2;
			}
			temp1 = temp1.next;
			temp2 = temp2.next;
		}
		while (temp1 != null) {
			if (!set.add(temp1)) {
				return temp1;
			}
			temp1 = temp1.next;
		}
		while (temp2 != null) {
			if (!set.add(temp2)) {
				return temp2;
			}
			temp2 = temp2.next;
		}
		return null;
	}

	/**
	 * compare head1's first will all the nodes of head2 if any node is == then
	 * return that node.
	 * 
	 * TC : O(N2) : for nested loop.
	 * 
	 */
	private static Node bruteForce() {
		return null;

	}
}
