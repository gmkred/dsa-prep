package striver_dsa_sheet.graphs;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * <pre>
 * Depth First Search:
 *                 1
 *                / \
 *               /   \
 *              /     \
 *             2       3------4
 *            / \      |      |
 *           /   \     |      |
 *          /     \    |      |
 *         5       6   7------8
 * 
 * start from 1 --> 2 --> 5 there is no next ,so go back to 2 and then go to 6
 * after 6 there is nothing, 
 * so go back to 1 and then go to 3 --> 7 -> 8 --> 4 --> 3 ( 3 is already visited so go back)
 * 
 * starting node = 3;
 * form 3 we can start from 1 or 7 or 4, if we start from 1, next time we go back 
 * to 3, next time we go to 7 and it continues.
 * 
 * This kind of algo uses Recursion, because there are multiple nodes or directions (edges) from one node.
 * 
 * Adjacency lists
 * 1 = {2,3}
 * 2 = {5,6}
 * 3 = {1,4,7}
 * 4 = {3,8}
 * 5 = {2}
 * 6 = {2}
 * 7 = {3,8}
 * 8 = {4,7}
 * 
 * DFS = {1, 2, 5, 6, 3, 4, 8, 7}
 * 
 * </pre>
 */
public class DFSTraversal {
	public static void main(String[] args) {
		ArrayList<Integer> dfs = new ArrayList<>();
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
		int n = 0;
		boolean visited[] = new boolean[n];
		visited[0] = true;
		dfs(adj, 0, visited, dfs);
		System.out.println(dfs);
	}

	public static void dfs(ArrayList<ArrayList<Integer>> adj, int node, boolean[] visisted, ArrayList<Integer> dfs) {

		visisted[node] = true;
		dfs.add(node);

		for (Integer i : adj.get(node)) {
			// if already visisted then dont visist again.
			if (!visisted[i]) {
				dfs(adj, i, visisted, dfs);
			}
		}

	}
}