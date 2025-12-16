package striver_dsa_sheet.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import striver_dsa_sheet.graphs.DijkstrasAlgo.WeightedNode;

/**
 * <pre>
 * 
 * we need to store the nodes of the shortest path.
 * It means, we have to store and update the nodes in a linked list or something similar
 * 
 * Try to remember where am i coming from.
 * 
 * PQ
 * ParentArray : where am i coming from
 * dist[]
 * 
 * </pre>
 */
public class PrintShortestPath_DijkstrasAlgo {

	public static void main(String[] args) {

		List<List<WeightedNode>> adj = new ArrayList();
		for (int i = 0; i < 6; i++) {
			adj.add(new ArrayList<>());
		}

		adj.get(0).add(new WeightedNode(1, 4));
		adj.get(0).add(new WeightedNode(2, 4));
		adj.get(1).add(new WeightedNode(0, 4));
		adj.get(1).add(new WeightedNode(2, 2));
		adj.get(2).add(new WeightedNode(0, 4));
		adj.get(2).add(new WeightedNode(1, 2));
		adj.get(2).add(new WeightedNode(3, 3));
		adj.get(2).add(new WeightedNode(4, 1));
		adj.get(2).add(new WeightedNode(5, 6));
		adj.get(3).add(new WeightedNode(2, 3));
		adj.get(3).add(new WeightedNode(5, 2));
		adj.get(4).add(new WeightedNode(2, 1));
		adj.get(4).add(new WeightedNode(5, 3));
		adj.get(5).add(new WeightedNode(2, 6));
		adj.get(5).add(new WeightedNode(3, 2));
		adj.get(5).add(new WeightedNode(4, 3));
		System.out.println(adj);

		Comparator<WeightedNode> comp = (x, y) -> {
			if (x.weight != y.weight) {
				return x.weight - y.weight;
			} else {
				return x.node - y.node;
			}
		};
		PriorityQueue<WeightedNode> pq = new PriorityQueue<>(comp);
		int parent[] = new int[adj.size()];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
		int dist[] = new int[adj.size()];
		System.out.println(shortestPath(adj, pq, 0, parent, dist));
		System.out.println(Arrays.toString(dist));

		System.out.println(Arrays.toString(parent));
	}

	public static List<Integer> shortestPath(List<List<WeightedNode>> adj, PriorityQueue<WeightedNode> pq, int src,
			int[] parent, int[] dist) {

		for (int i = 0; i < dist.length; i++) {
			if (i != src) {
				// initialize with max distance
				dist[i] = (int) 1e9;
			}
		}
		pq.offer(new WeightedNode(src, 0));

		while (!pq.isEmpty()) {
			WeightedNode temp = pq.poll();
			for (WeightedNode next : adj.get(temp.node)) {
				if (temp.weight + next.weight < dist[next.node]) {
					parent[next.node] = temp.node;
					dist[next.node] = temp.weight + next.weight;
					pq.offer(new WeightedNode(next.node, dist[next.node]));
				}
			}
		}

		List<Integer> path = new ArrayList<>();
		int dest = adj.size() - 1;
		if (dist[dest] == (int) 1e9) {
			return Collections.emptyList();
		}
		while (src != dest) {
			path.add(dest);
			dest = parent[dest];
		}

		path.add(src);
		Collections.reverse(path);
		return path;
	}
}
