package striver_dsa_sheet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {
	static Node head = null;

	public static void main(String[] args) {
		int[] arr1 = new int[] { 1, 4, 5 };
		int[] arr2 = new int[] { 1, 3, 4 };
		int[] arr3 = new int[] { 2, 6 };
		Node node = new Node();
		Node list1 = node.generateSInglyLinkedList(arr1);
		Node list2 = node.generateSInglyLinkedList(arr2);
		Node list3 = node.generateSInglyLinkedList(arr3);
		Node[] lists = new Node[3];
		lists[0] = list1;
		lists[1] = list2;
		lists[2] = list3;
//		head = mergeKLists_mySol(lists);
//		head = mergeKLists_WithExtraArray(lists);
		head = mergeKLists_optimal(lists);
		node.traverse(head);

	}

	/**
	 * <pre>
	 * Use min-heap, in java it Priorityy Queue implements min-heap
	 * min-heap returns the min value from the stored elements.
	 * 
	 * Priority queue interms of min value
	 * 
	 * store all the heads of each list into the priority queue;
	 * 
	 * Priority Queue <int, Node>
	 * 
	 * int = val of the head node
	 * Node = head of the node
	 * 
	 * { 1, 4, 5 };
	 * { 1, 3, 4 };
	 * { 2, 6 };
		
	 * <1, node1>
	 * <1, node2>
	 * <2, node3>
	 * 
	 * PQ returns one of the min valu node.
	 * take that node as temp.
	 * add that node to dummy node,
	 * after that move temp to tmep.next and again store it in the PQ.
	 *
	 * dummyNode ={-1,1}
	 * 
	 * <5, node1>
	 * <1, node2>
	 * <2, node3>
	 * 
	 * dummyNode ={-1,1,1}
	 * 
	 * <5, node1>
	 * <3, node2>
	 * <2, node3>
	 * and so on, till PQ is empty.
	 * 
	 * K = number of lists
	 * N = assume each list is size of N
	 * 
	 * TC:
	 * O(log K) :PQ --> pq.add() takes log K to add, because it sorts and adds.
	 * O(K) : to loop K lists
	 * While loop:
	 * O(K*N) : to read each node from PQ
	 * log K to poll
	 * log K to again add.
	 * 
	 * Total : O((3 log K) +  K*N*log K)
	 * 
	 * SC : O(K) - PQ stores only the head reference of K lists.
	 * </pre>
	 */
	private static Node mergeKLists_optimal(Node[] lists) {

		Node dummy = new Node(-1);
		Node dummyPointer = dummy;
		Queue<Node> pq = new PriorityQueue(new NodeComparator());
		for (Node n : lists) {
			pq.add(n);
		}
		Node temp = null;
		while (!pq.isEmpty()) {
			temp = pq.poll();
			dummyPointer.next = temp;
			temp = temp.next;
			if (temp != null) {
				pq.add(temp);
			}
			dummyPointer = dummyPointer.next;
		}
		return dummy.next;
	}

	/**
	 * <pre>
	 * Use an extra array to store all elements, and sort that array and convert
	 * into new List
	 * 
	 * TC : O(K) + O(2(K*N)) + O(k*N log k*N)
	 * O(K) for k lists
	 * O(K*N) storing into list
	 * O(k*N log k*N) sorting the list;
	 * O(K*N) to create new list with sorted array.
	 * 
	 *  
	 *  SP : O(2N) 
	 *  N = to store elements before sorting
	 *  N = for new list
	 * 
	 * </pre>
	 */
	public static Node mergeKLists_WithExtraArray(Node[] lists) {
		if (lists.length == 0) {
			return null;
		}
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < lists.length; i++) {
			Node local = lists[i];
			while (local != null) {
				list.add(local.val);
				local = local.next;
			}
		}
		list.sort(Integer::compare);
		Node dummy = new Node(-1);
		Node temp = dummy;
		for (int i : list) {
			temp.next = new Node(i);
			temp = temp.next;
		}
		return dummy.next;
	}

	/**
	 * <pre>
	 * TC 
	 * 
	 * Assume each list has N elements.
	 * 
	 * 2N+3N+4N+...+KN
	 * 
	 * N(2+3+4+...+K) = N * (K(K+1)/2) like N(N+1)/2
	 * 
	 * TC : O(N(N+1)/2) = O(N^2) + O(K)
	 * 
	 * SC : O(1)
	 * 
	 * </pre>
	 */
	public static Node mergeKLists_better(Node[] lists) {
		if (lists.length == 0) {
			return null;
		}
		Node global = null;
		if (lists.length > 0) {
			global = lists[0];
		}
		Node globalPointer = global;

		Node dummy = new Node(-1);
		Node temp = dummy;
		for (int i = 1; i < lists.length; i++) {
			Node local = null;
			local = lists[i];
			while (globalPointer != null & local != null) {
				if (globalPointer.val <= local.val) {
					temp.next = globalPointer;
					temp = temp.next;
					globalPointer = temp.next;
				} else {
					temp.next = local;
					temp = temp.next;
					local = local.next;
				}
			}
			while (globalPointer != null) {
				temp.next = globalPointer;
				temp = temp.next;
				globalPointer = globalPointer.next;
			}

			while (local != null) {
				temp.next = local;
				temp = temp.next;
				local = local.next;
			}
			global = dummy.next;
			globalPointer = global;
			dummy.next = null;
			temp = dummy;
		}
		return global;
	}

}
