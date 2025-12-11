package striver_dsa_sheet.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

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
 * Means no bidirectional and no cycle, because these will not follow the condition.
 * 
 * Use Stack.
 * add the current node to stack only after adding the adjacent nodes of the current node.
 * 
 * 
 * SC: O(N) + O(N)
 * TC : O(V + E) ==> vertices + edges
 * 
 * Intuition:
 * 1 --> 2 --> 3--> 4 --> 5
 *                   \
 *                     \
 *                       V
 *                         6
 * we have to add a node to stack, only if its neighbors are in stack so it maintains the order.
 * </pre>
 */
public class TopologicalOrKhansAlgorithmDFS {
	public static void main(String[] args) {
	}

	public static List topoDFS(int N, ArrayList<ArrayList<Integer>> adj) {
		Stack<Integer> stack = new Stack();
		int visited[] = new int[N];

		for (int i = 0; i < N; i++) {
			if (visited[i] == 0) {
				topoRec(N, adj, visited, stack);
			}
		}
		List<Integer> list = stack.stream().collect(Collectors.toList());
		return list;
	}

	public static void topoRec(int node, ArrayList<ArrayList<Integer>> adj, int visited[], Stack<Integer> stack) {
		visited[node] = 1;

		for (int neighbourNode : adj.get(node)) {
			if (visited[neighbourNode] == 0) {
				topoRec(neighbourNode, adj, visited, stack);
			}
		}
		stack.push(node);
	}
}
