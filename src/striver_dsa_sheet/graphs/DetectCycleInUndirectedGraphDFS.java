package striver_dsa_sheet.graphs;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import striver_dsa_sheet.graphs.DetectCycleInUndirectedGraphUsingBFS.Pair;

/**
 * <pre>
 * 
 * 
 * 
 * </pre>
 */
public class DetectCycleInUndirectedGraphDFS {

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		int arr[][] = { { 0 }, { 2, 3 }, { 1, 5 }, { 1, 4, 6 }, { 3 }, { 2, 7 }, { 3, 7 }, { 5, 6 } };
		for (int i = 0; i < arr.length; i++) {
			adj.add(new ArrayList<>());
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				adj.get(i).add(arr[i][j]);
			}
		}
		boolean[] visisted = new boolean[arr.length];
		System.out.println(dfs(1, -1, visisted, adj));
	}

	public static boolean dfs(int node, int parent, boolean visited[], ArrayList<ArrayList<Integer>> adj) {
		visited[node] = true;

		for (int i : adj.get(node)) {
			if (!visited[i]) {
				if (dfs(i, node, visited, adj)) {
					return true;
				}
				// if it is not a parent and i is visited then it is visited by other node so
				// its a cycle.
			} else if (i != parent) {
				return true;
			}
		}
		return false;
	}

	public static boolean dfsConnectedComponents() {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		int arr[][] = { { 0 }, { 2, 3 }, { 1, 5 }, { 1, 4, 6 }, { 3 }, { 2, 7 }, { 3, 7 }, { 5, 6 } };
		for (int i = 0; i < arr.length; i++) {
			adj.add(new ArrayList<>());
		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				adj.get(i).add(arr[i][j]);
			}
		}
		boolean[] visisted = new boolean[arr.length];
		// start from 1 for 1 based indexing
		for (int i = 1; i < arr.length; i++) {
			if (!visisted[i]) {
				if (dfs(i, -1, visisted, adj)) {
					return true;
				}
			}
		}
		return false;
	}
}