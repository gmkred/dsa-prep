package striver_dsa_sheet.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * No adjacent nodes should have same color
 * No odd number of nodes in a cycle graph.
 * Any linear length graph is also a bipartite.
 * 
 * </pre>
 */
public class BipartiteGraphDFS {
	public static void main(String[] args) {
		System.out.println(process());
	}

	public static boolean process() {
		List<List<Integer>> adj = new ArrayList();
		int arr2d[][] = { { 0 }, { 2 }, { 1, 3, 6 }, { 2, 4 }, { 3, 5, 7 }, { 4, 6 }, { 2, 5 }, { 4, 8 }, { 7 } };
		int N = 8;
		int colors[] = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			colors[i] = -1;
		}
		for (int[] arr : arr2d) {
			List<Integer> list = new ArrayList();
			for (int num : arr) {
				list.add(num);
			}
			adj.add(list);
		}
		for (int i = 0; i < N; i++) {
			if (colors[i] == -1) {
				if (!isBipartiteDFS(adj, 1, N, colors, 0))
					return false;
			}
		}
		return true;

	}

	private static boolean isBipartiteDFS(List<List<Integer>> adj, int node, int N, int[] colors, int color) {
		colors[node] = color;
		for (int num : adj.get(node)) {
			if (colors[num] == -1) {
				if (!isBipartiteDFS(adj, num, N, colors, 1 - colors[node])) {
					return false;
				}
			} else {
				if (colors[num] == color) {
					return false;
				}
			}
		}
		return true;
	}
}
