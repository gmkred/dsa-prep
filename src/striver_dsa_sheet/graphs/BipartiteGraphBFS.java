package striver_dsa_sheet.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <pre>
 * 
 * 
 * None of the adjacent nodes should have same color.
 * 
 * given a graph, we try to color all the nodes, 
 * and none of the adjacent should have same color,
 * then it is bipartite graph.
 * 
 * Linear graphs with no cycle are always bipartite graphs.
 * 
 * 
 * Any graph with odd length cycle then it is a non-bipartite graph.
 * </pre>
 */
public class BipartiteGraphBFS {
	public static void main(String[] args) {
		List<List<Integer>> adj = new ArrayList();
		int arr2d[][] = { { 0 }, { 2 }, { 1, 3, 6 }, { 2, 4 }, { 3, 5, 7 }, { 4, 6 }, { 2, 5 }, { 4, 8 }, { 7 } };
		for (int[] arr : arr2d) {
			List<Integer> list = new ArrayList();
			for (int num : arr) {
				list.add(num);
			}
			adj.add(list);
		}
		System.out.println(isBipartite(adj, 1, 8));
	}

	public static boolean isBipartite(List<List<Integer>> adj, int node, int N) {
		int color[] = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			color[i] = -1;
		}
		// -1, not colored
		// 0, green
		// 1, blue
		Queue<Integer> q = new LinkedList();
		q.offer(node);
		while (!q.isEmpty()) {
			int _node = q.poll();
			for (int num : adj.get(_node)) {
				if (color[num] == -1) {
					// 1-0 = 1
					// 1-1 = 0
					color[num] = 1 - color[_node];
					q.offer(num);
				} else if (color[num] == color[_node]) {
					return false;
				}
			}
		}
		return true;
	}
}