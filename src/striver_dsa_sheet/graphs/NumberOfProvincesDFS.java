package striver_dsa_sheet.graphs;

import java.util.ArrayList;

//number of connected components using DFS
public class NumberOfProvincesDFS {
	public static int numberOfProvincesDFS(ArrayList<ArrayList<Integer>> adjMatrix, int n) {
		int count = 0;
		boolean visited[] = new boolean[n];
		ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adjList.add(new ArrayList());
		}
		for (int i = 0; i < adjMatrix.size(); i++) {
			for (int j = 0; j < adjMatrix.size(); j++) {
				if (adjMatrix.get(i).get(j) == 1 && i != j) {
					adjList.get(i).add(j);
					adjList.get(j).add(i);
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				count++;
				dfs(adjList, visited, i);
			}
		}
		return count;
	}

	public static void dfs(ArrayList<ArrayList<Integer>> adj, boolean visited[], int node) {
		visited[node] = true;
		for (int num : adj.get(node)) {
			if (!visited[num]) {
				dfs(adj, visited, num);
			}
		}
	}
}
