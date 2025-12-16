package striver_dsa_sheet.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <pre>
 * 
 * find the shortest path from a given source/starting node.
 * 
 * Note : In this algo we will find the shortest distance from source node to all the other nodes.
 * in this problem, we also get weights for edges.
 * 
 * weight is calculated as distance in this problem.
 * 
 * we can use PQ or Set or Queue(which takes more time)
 * 
 * Time taken : Q > PQ > Set.
 * 
 * adjacency list looks like {node, weight}
 * 
 * 0 --> {1,4}
 * 
 * 0th index is node.
 * 1st index is weight.
 * 
 * if we use PQ(min-heap) by Weight, always minimum weight will be on top.
 * 
 * Dijkstra's does not work in negative weight or negative cycle.
 * 
 * </pre>
 * 
 */
public class DijkstrasAlgo {
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
		int disArray[] = new int[adj.size()];
		System.out.println(Arrays.toString(dijkstras(adj, pq, disArray, 0)));

	}

	public static int[] dijkstras(List<List<WeightedNode>> adj, PriorityQueue<WeightedNode> pq, int[] disArray,
			int srcNode) {
		for (int i = 0; i < disArray.length; i++) {
			if (i != srcNode) {
				// initialize with max distance
				disArray[i] = (int) 1e9;
			}
		}
		pq.offer(new WeightedNode(srcNode, 0));

		while (!pq.isEmpty()) {
			WeightedNode temp = pq.poll();

			for (WeightedNode next : adj.get(temp.node)) {
				// check if the next node dist + current node dist is shorter than the next
				// nodes distance in distance array.
				// This is needed because from source to next node, there can be multiple paths,
				// we need to update our dis array with shortest distance from source to a node.
				if (next.weight + temp.weight < disArray[next.node]) {
					disArray[next.node] = next.weight + temp.weight;
					// add new WeightedNode to PQ with updated distance from source to next node.
					pq.offer(new WeightedNode(next.node, disArray[next.node]));
				}
			}
		}

		return disArray;
	}

	static class WeightedNode {
		int node;
		int weight;

		WeightedNode(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "[" + node + "=" + weight + "]";
		}
	}
}
