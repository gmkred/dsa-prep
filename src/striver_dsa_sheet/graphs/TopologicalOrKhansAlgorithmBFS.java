package striver_dsa_sheet.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <pre>
 * TOpological sorting (Khans algo) (BFS)
 * 
 * Linear ordering of vertices such that if there is 
 * an edge between u and v, u always appears before v in that ordering.
 * 
 *    5             4
 *    |\           /|
 *    |  \       /  | 
 *    |    \   /    |
 *    |     VV      |
 *    |     0       |
 *    V             V
 *    2 ---->3 ---->1
 * 5 4 0 2 3 1
 * 4 5 2 3 1 0
 * are linear orders as long as it satisfies the condition.
 * edges
 * 5 0
 * 4 0
 * 5 2
 * 2 3
 * 3 1
 * 4 1
 * 
 * Topology sort is only valid in DAG (Directed Acyclic Graph)
 * 
 * For DFS we use Stack, but for BFS we need to use Queue 
 * And inDegree[] array --> number of incoming edges to a node
 *  0 1 2 3 4 5
 * {2 2 1 1 0 0}
 * 
 * the sorting will be, which ever has lowest in degrees will be in the beginning and highest
 * in degrees will be at the end of the list.
 * 
 * Insert all nodes that have 0 in degrees.
 * 
 * 
 * </pre>
 */
public class TopologicalOrKhansAlgorithmBFS {
	public static List<Integer> topoBFS(int N, ArrayList<ArrayList<Integer>> adj) {
		int[] indegrees = new int[N];
		List<Integer> list = new ArrayList();
		for (int i = 0; i < N; i++) {
			for (int node : adj.get(i)) {
				indegrees[node]++;
			}
		}
		Queue<Integer> q = new LinkedList();
		for (int i = 0; i < N; i++) {
			if (indegrees[i] == 0) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			int tempNode = q.poll();
			// add the popped node to list, because the beginning nodes of the list should
			// contain
			// the less number of indegrees.
			list.add(tempNode);
			// go through then neighbors of current popped node and reduce the indegees
			// if it becomes zero, then add that node to the queue.
			for (int i : adj.get(tempNode)) {
				indegrees[i]--;
				if (indegrees[i] == 0) {
					q.offer(i);
				}
			}
		}
		return list;

	}
}
