package striver_dsa_sheet.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 * 
 *     2------5
 *    /        \
 *   /          \
 *  1            7
 *   \          /
 *    \        /
 *     3------6 
 *     |
 *     |
 *     4
 *     
 * 1 = {2,3}
 * 2 = {1,5}
 * 3 = {1,6,4}
 * 4 = {3}
 * 5 = {2,7}
 * 6 = {3,7}
 * 7 = {5,6}
 * 
 * if we start from 1, the pairs will look like this
 * 
 * when we go through the neighbors of 5, it checks if 2 is marked as visited, if yes, it goes to 7 
 * since 7 is not marked, it will mark.
 * when we go through the neighbors of 6, it checks if 3 is visisted, if yes, then it checks it it is the parent, it is so it moves to 7
 * now,  is already marked by 5, so it checks if it is 7 is 6's parent, it is not,
 * this means that 7 is visited by other node that means there is a cycle.
 * 
 * 
 *  
 * 
 * for each visited number in the adj, store the parent along with itself in the q, so next time 
 * we do not visit the parent again, 
 * And when an element is visited twice from different elements then it is a cyclic graph.
 * 
 * </pre>
 */
public class DetectCycleInUndirectedGraphUsingBFS {
	public static void main(String[] args) {

	}

	// source is given
	public static boolean isCyclincGraph(ArrayList<ArrayList<Integer>> adj, int n, int src, boolean[] visited) {

		Queue<Pair> q = new LinkedList();
		q.offer(new Pair(src, -1));
		while (!q.isEmpty()) {

			Pair temp = q.poll();
			for (int adjNode : adj.get(temp.num)) {
				if (!visited[adjNode]) {
					visited[adjNode] = true;
					q.add(new Pair(adjNode, temp.num));
					// if current adjNode is visited but not the parent of the current temp node
					// then it must be visited by other node so it a cycle.
				} else if (temp.parentNumber != adjNode) {
					return true;
				}
			}
		}
		return false;
	}

	// broken graph
	// TC : O(N) + O(2*E) degrees
	public static boolean isCyclincGraph_COnnectedComponenets(ArrayList<ArrayList<Integer>> adj, int n, int src) {

		boolean visisted[] = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (!visisted[i]) {
				if (isCyclincGraph(adj, i, src, visisted)) {
					return true;
				}
			}
		}
		return false;
	}

	static class Pair {
		int num;
		int parentNumber;

		Pair(int num, int parentNumber) {
			this.num = num;
			this.parentNumber = parentNumber;
		}
	}
}
