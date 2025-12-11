package striver_dsa_sheet.graphs;

import java.util.ArrayList;

/**
 * <pre>
 * we need visited array and self dfs
 * 
 * visited array: once marked it is marked
 * 
 * DFS visited array : when returning, unmark the node, 
 * because the only time the dfs visisted node is again visited only if there is a loop.
 * 
 * 
 * 
 * </pre>
 */
public class DetectCycleInDirectedGraphBSFS {
	public static void main(String[] args) {

	}

	public static boolean isCyclic(int N, ArrayList<ArrayList<Integer>> adj) {

		int vis[] = new int[N];
		int dfsVis[] = new int[N];
		for (int i = 0; i < N; i++) {
			if (vis[i] == 0) {
				if (checkCycle(i, adj, vis, dfsVis)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkCycle(int node, ArrayList<ArrayList<Integer>> adj, int vis[], int dfsVis[]) {
		vis[node] = 1;
		dfsVis[node] = 1;

		for (Integer i : adj.get(node)) {
			// if not visited
			if (vis[i] == 0) {
				if (checkCycle(i, adj, vis, dfsVis)) {
					return true;
				}
			}
			// if visited and dfs visited is also 1 then it is a loop
			else if (dfsVis[i] == 1) {
				return true;
			}
		}
		// while returning back, un-visit the node in dfs visited array
		dfsVis[node] = 0;
		return false;
	}
}
