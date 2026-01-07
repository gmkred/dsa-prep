package striver_dsa_sheet.graphs;

import java.util.List;
import java.util.PriorityQueue;

/**
 * <pre>
 * 
 * 
 * spanning tree:
 * Undirected weighted graph.
 * N nodes
 * M edges
 * 
 * A tree in which we have N nodes and N-1 edges and all nodes are reachable from eachother.
 * 
 *     2     3
 *  0 --- 1 --- 2
 *  |    /\     /
 * 6|  8/  \5  /7
 *  |  /    \ /
 *   3       4
 * 
 * 
 * from above graph we can create few spanning trees
 *     2     3
 *  0 --- 1 --- 2
 *  |     \     
 * 6|      \5  
 *  |       \ 
 *   3       4
 * sum == 16
 * 
 *     2     3
 *  0 --- 1 --- 2
 *       /      /
 *     8/      /7
 *     /      /
 *   3       4
 * sum = 20
 * 
 * the MST is tree with sum 16.
 * </pre>
 */
public class MinimumSpanningTree_PrimesAlgo {
	public static int spanningTree(int V, List<List<List<Integer>>> adj) {
		PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.distance - y.distance);
		int[] vis = new int[V];
		pq.add(new Pair(0, 0));
		int sum = 0;
		while (pq.size() > 0) {
			int wt = pq.peek().distance;
			int node = pq.peek().node;
			pq.poll();
			if (vis[node] == 1) {
				continue;
			}
			vis[node] = 1;
			sum += wt;
			for (int i = 0; i < adj.get(node).size(); i++) {
				int edN = adj.get(node).get(i).get(1);
				int adjNode = adj.get(node).get(i).get(0);
				if (vis[adjNode] == 0) {
					pq.add(new Pair(edN, adjNode));
				}
			}
		}
		return sum;
	}

	static class Pair {
		int node;
		int distance;

		public Pair(int distance, int node) {
			this.node = node;
			this.distance = distance;
		}
	}
}
