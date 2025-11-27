package striver_dsa_sheet.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <pre>
 * Traversal technique: Breadth First Search or level wise.
 * N = 8
 * 
 *                      1                         level 0
 *                    /   \ 
 *                  /       \
 *                /           \
 *              2               6                 level 1
 *             / \            /   \
 *           /    \         /       \ 
 *         3       4       7         8            level 2
 *                  \     /
 *                   \   /
 *                     5                          level 3
 * given a starting node.
 * starting node is 1:
 * level 0 = {1}
 * level 1 = {2,6}
 * level 2 = {3,4,7,8}
 * level 3 = {5}
 * {1, 2, 6, 3, 4, 7, 8, 5}
 * 
 * what if the starting node is 6:
 * now the level 0 will be 6, level 0 always has one node and the level 1 nodes will be the nodes that are equally edged neighbors.
 * level 0 = {6}
 * level 1 = {1, 7, 8}
 * level 2 = {2, 5}
 * level 3 = {3, 4}
 * 
 * depending on the starting node, we always traverse breadth wise.
 * 
 * Use Queue DS, and put starting node.
 * and we need to also need a visited Array.
 * mark the node as marked that is added to queue in the array.
 * 
 * keep taking out till the queue is not empty.
 * when we took out a node from queue, we store the adjacent node value which are not visited in the adjacencyList.
 * 
 * 
 * NOTE: we need to see if it is a 0 based or 1 based graph, based on it we need to create the visited array and adjacencyList array.
 * SC : O(N) visited array + O(N) adjacencyListArray.
 * TC : O(N) total numbers + O(2*E) total de grees
 * 
 * </pre>
 */
public class BFSTraversal {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

	}

	public static ArrayList<Integer> bfs(int n, ArrayList<ArrayList<Integer>> adj) {
		// for 0 based it will be n, for 1 based it will be n+1;
		boolean visited[] = new boolean[n];
		ArrayList<Integer> bfs = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		q.offer(0);
		visited[0] = true;
		while (!q.isEmpty()) {
			int val = q.poll();
			bfs.add(val);
			for (int i : adj.get(val)) {
				if (!visited[i]) {
					visited[i] = true;
					q.offer(i);
				}
			}
		}
		return bfs;
	}
}
