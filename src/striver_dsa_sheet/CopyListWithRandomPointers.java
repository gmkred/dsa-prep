package striver_dsa_sheet;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Problem statement:
 * A list that contains a random node reference to each node.
 * Write a solution to create exact copy of the List with random references.
 * 
 * <a href=
"https://www.youtube.com/watch?v=q570bKdrnlw&list=PLgUwDviBIf0rAuz8tVcM0AymmhTRsfaLU&index=28"> Youtube link</a>
 * 
 * </pre>
 */
public class CopyListWithRandomPointers {

	public static void main(String[] args) {
		NodeR head = generateRandomLinkedlist_static();
		travaerseRandomlinkedlist(head);
//		NodeR brandNew = copyList_HashMap(head);
		NodeR brandNew = copyList_storeInBetweenOriginalList(head);
		travaerseRandomlinkedlist(head);
		travaerseRandomlinkedlist(brandNew);

	}

	/**
	 * <pre>
	 * 
	 * original  
	 * <span style="color:red"> 7 -> 13 -> 11 -> 10 -> 1</span> 
	 * Initial Copy each node, place them between original list
	 * <span style="color:red"> 7 ->  <span style=
	"color:green">7</span> -> 13 ->  <span style=
	"color:green">13</span> -> 11 ->  <span style=
	"color:green"> 11</span> -> 10 ->  <span style=
	"color:green">10</span> -> 1</span> -> <span style="color:green">1</span>
	 * 
	 * By this, new list is present in original list and after this we need to map the
	 * random pointers.
	 * Now again start from the beginning.
	 * if original node has a random pointer, 
	 * make the copy node of the original node point to the original random node's next node
	 * which will be the copy node.
	 * 
	 * After this separate the copy nodes and original nodes
	 * 
	 * Make sure that original list should not have copy nodes after the process.
	 * 
	 * TC : O(3N) ~= O(N)
	 * SC : O(N) == Question itself demands to create new list.
	 * 
	 * </pre>
	 */
	private static NodeR copyList_storeInBetweenOriginalList(NodeR head) {

		NodeR temp = head;
		NodeR tempNext = head;
		NodeR dummy = new NodeR(-1);
		NodeR dp = dummy;
		while (temp != null) {
			tempNext = temp.next;
			NodeR newNode = new NodeR(temp.val);
			temp.next = newNode;
			newNode.next = tempNext;
			temp = tempNext;
		}
		temp = head;
		while (temp != null) {
			if (temp.random != null) {
				temp.next.random = temp.random.next;
			}
			temp = temp.next.next;

		}
		temp = head;
		while (temp != null) {
			dp.next = temp.next;
			dp = dp.next;
			temp.next = dp.next;
			temp = temp.next;
		}
		return dummy.next;
	}

	/**
	 * <pre>
	 * TC : 
	 * O(2N) for 2 while loops 
	 * O(2N) for insertion and retrieval
	 * 
	 * O(4N) ~= O(N)
	 * SC :"
	 * O(N) hashmap
	 * 
	 * </pre>
	 */
	static private NodeR copyList_HashMap(NodeR head) {
		Map<NodeR, NodeR> map = new HashMap<>();
		NodeR temp = head;
		NodeR dummy = new NodeR(-1);
		NodeR dumP = dummy;
		while (temp != null) {
			dumP.next = new NodeR(temp.val);
			dumP = dumP.next;
			// store the original node and equivalent copy node in map, so we can get random
			// nodes easily.
			map.put(temp, dumP);
			temp = temp.next;
		}
		temp = head;
		dumP = dummy.next;
		while (temp != null) {
			if (temp.random != null) {
				// We get the random node using original node's random node.
				// because that random node has an equivalent node in copy node.
				dumP.random = map.get(temp.random);
			}
			temp = temp.next;
			dumP = dumP.next;
		}

		return dummy.next;

	}

	static private NodeR generateRandomLinkedlist_static() {

		NodeR n1 = new NodeR(7);
		NodeR n2 = new NodeR(13);
		NodeR n3 = new NodeR(11);
		NodeR n4 = new NodeR(10);
		NodeR n5 = new NodeR(1);
		n1.random = null;
		n2.random = n1;
		n3.random = n5;
		n4.random = n3;
		n5.random = n1;
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		return n1;

	}

	private static void travaerseRandomlinkedlist(NodeR head) {
		NodeR temp = head;
		while (temp != null) {
			System.out.print(temp + " ");
			temp = temp.next;
		}
		System.out.println();
	}

	static class NodeR {
		int val;
		NodeR next;
		NodeR random;

		@Override
		public String toString() {
			return "NodeR [val=" + val + ", next=" + (next != null ? next.val : null) + ", random="
					+ (random != null ? random.val : null) + "]";
		}

		public NodeR(int val) {
			this.val = val;
		}

		public NodeR(int val, NodeR next, NodeR random) {
			this.val = val;
			this.next = next;
			this.random = random;
		}

		public NodeR() {
		}

	}

}
