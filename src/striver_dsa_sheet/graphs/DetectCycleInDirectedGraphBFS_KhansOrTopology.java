package striver_dsa_sheet.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <pre>
 * 
 * Use topological sorting.
 * Since topological cannot be applied on Directed Cyclic Graph, we can still
 * apply the topo algo.
 * 
 *  when any in degree has a value of -1, then it is 
 *  cyclic, i only happens if a node is visited more than the in degrees.
 * 
 * 1 --> 2 -->  3 --> 5
 *       ^     /
 *        \   /
 *         \ V
 *          4
 *                1 2 3 4 5
 * indergees[] = {0 2 1 1 1}
 * 
 * add 1 to queue,
 * next, take 1 out and check if 1's neighbor's in degrees can be reduces to 0,
 * in this case, it cannot be reduced to 0 and there will not be any node in queue,
 * so the number of nodes and topo sort count is not same, that means there is a cycle,
 * because, there is another dependent on 2.
 * 
 * 
 * 
 * </pre>
 */
public class DetectCycleInDirectedGraphBFS_KhansOrTopology {
	public static boolean topoBFS(int N, ArrayList<ArrayList<Integer>> adj) {
		int[] indegrees = new int[N];
		for (int i = 0; i < N; i++) {
			for (int node : adj.get(i)) {
				indegrees[node]++;
			}
		}
		int count = 0;
		Queue<Integer> q = new LinkedList();
		for (int i = 0; i < N; i++) {
			if (indegrees[i] == 0) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			int tempNode = q.poll();
			count++;
			// go through then neighbors of current popped node and reduce the indegees
			// if it becomes zero, then add that node to the queue.
			for (int i : adj.get(tempNode)) {
				indegrees[i]--;
				if (indegrees[i] == 0) {
					q.offer(i);
				}
			}
		}
		// less than N, then it has a cycle.
		if (count == N) {
			return true;
		}
		return false;

	}
}
