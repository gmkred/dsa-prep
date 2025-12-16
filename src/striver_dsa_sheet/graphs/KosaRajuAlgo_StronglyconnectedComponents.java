package striver_dsa_sheet.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <pre>
 * 
 * Kasaraju's : strongly connected components in a directed graph.
 * 
 * 1 ---> 2 ---> 4
 * ^     /       |
 *  \   /        |
 *   \ V         V
 *    3          5
 *    
 * if we start from any of 1 or 2 or 3, it will reach to other 2 nodes.
 * 
 * 1 --> 2 --> 3
 * 2 --> 3 --> 1
 * 3 --> 1 --> 2
 * This is a strongly connected component.
 * 
 * 4 is also a SCC
 * 5 is also a SCC
 * 
 * 
 * output:
 * 1 2 3
 * 4
 * 5
 * 
 * 3 different SCC
 * 
 * take any node in the component, and it should reach to every other node in that component.
 * 
 * this algo starts from the last node.
 * 3 steps of this algo:
 * 1. sort all nodes in order of finishing time. (topo sort)
 * 2. transpose the graph:
 * 	  All the edges are reversed.
 * 3. DFS according to the finishing time().
 * 
 * </pre>
 * 
 */
public class KosaRajuAlgo_StronglyconnectedComponents {
	public static void main(String[] args) {
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i <= 5; i++) {
			adj.add(new ArrayList<>());
		}
		adj.get(1).add(2);
		adj.get(2).add(3);
		adj.get(3).add(1);
		adj.get(2).add(4);
		adj.get(4).add(5);
		System.out.println(adj);
		List<List<Integer>> res = new ArrayList<>();
		process(res, adj);
		System.out.println(res);
	}

	public static void process(List<List<Integer>> res, List<List<Integer>> adj) {
		Stack<Integer> stack = new Stack();
		boolean visited[] = new boolean[adj.size()];
		for (int i = 1; i < adj.size(); i++) {
			if (!visited[i]) {
				dfs(adj, visited, i, stack);
			}
		}
		System.out.println(stack);
		List<List<Integer>> transposeGraph = new ArrayList<>();
		for (int i = 0; i <= 5; i++) {
			transposeGraph.add(new ArrayList<>());
		}
		// reversing the direction.
		for (int i = 0; i < adj.size(); i++) {
			visited[i] = false;
			for (int num : adj.get(i)) {
				transposeGraph.get(num).add(i);
			}
		}
		System.out.println(transposeGraph);
		// find the SCC using reversed graph with the topo sorted graph.
		while (!stack.isEmpty()) {
			int temp = stack.pop();

			if (!visited[temp]) {
				List<Integer> list = new ArrayList();
				revDfs(transposeGraph, visited, list, temp);
				res.add(list);
			}
		}
	}

	public static void revDfs(List<List<Integer>> adj, boolean[] visited, List<Integer> list, int node) {
		visited[node] = true;
		list.add(node);
		for (int num : adj.get(node)) {
			if (!visited[num]) {
				revDfs(adj, visited, list, num);
			}
		}

	}

	public static void dfs(List<List<Integer>> adj, boolean visited[], int node, Stack<Integer> stack) {
		visited[node] = true;
		for (int num : adj.get(node)) {
			if (!visited[num]) {
				dfs(adj, visited, num, stack);
			}
		}
		stack.push(node);
	}
}
