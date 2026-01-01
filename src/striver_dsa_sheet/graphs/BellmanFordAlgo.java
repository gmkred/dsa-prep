package striver_dsa_sheet.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * It finds the shortest path (similar to DijkstrasAlgo) to all the other nodes.
 * 
 * 2 points to be remembered about Dijkstra Algo:
 * 1. It fails when graph has negative edges 
 * 2. It throws TLE when there negative cycle, because it keeps on minimizing the distance.
 * ***If a path weight is <0 then the graph has negative cycle.
 * So we use Bellman ford to detect negative cycles as well.
 * And this is only applicable for Directed Graph.
 * 
 * If UDG is given we need to change it to directed graph where both nodes are directing to each other with same weight.
 * 
 * UDG
 *      5
 * 1 ------- 2
 * TO DG
 *      5
 * 1 <------ 2
 *   ------> 2
 *      5
 * 
 * **edges can be in any order.
 * relax all the edges in n-1 times sequentially.
 * 
 * Relax : if we have a distance array, (dist[toReachU] + edgeWeight) < v
 * if(dist[u] +wg] < v){
 * 	dist[v] = dist[u]+wg;
 * do it for N-1 times.
 * (u, v, wt)
 * (3, 2, 6)
 * (5, 3, 1)
 * (0, 1, 5)
 * (1, 5, -3)
 * (1, 2, -2)
 * (3, 4, -2)
 * (2, 4, 3)
 * 
 * Why N-1 ?
 * Its because, this type of questions gives unordered edges,
 * what if the src node present in the end of the list, in this case we need N-1 iterations.
 * So we should always go with N-1 to get the paths right. 
 * Example:
 * (u, v , wt)
 * (3, 4, 1)
 * (2, 3, 1)
 * (1, 2, 1)
 * (0, 1, 1)
 * distArr [0, MAX, MAX, MAX, MAX]
 * 
 * 1st iteration
 * distArr [0, 1, MAX, MAX, MAX]
 * 
 * 2nd iteration
 * distArr [0, 1, 2, MAX, MAX]
 * 
 * 3rd iteration
 * distArr [0, 1, 2, 3, MAX]
 * 
 * 4th iteration
 * distArr [0, 1, 2, 3, 4]
 * 
 * so to get the shortest paths to all nodes from src, we need to do N-1 relaxations.
 * 
 * How to detect Negative cycles?
 * On Nth iteration, the relaxation is done and if the distArr gets updated then the Graph has a negative cycle.
 * 
 * </pre>
 * 
 */
public class BellmanFordAlgo {
	public static void main(String[] args) {

		int N = 6;
		List<List<Integer>> edges = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			edges.add(new ArrayList());
		}
		int arrEdges[][] = { { 3, 2, 6 }, { 5, 3, 1 }, { 0, 1, 5 }, { 1, 5, -3 }, { 1, 2, -2 }, { 3, 4, -2 },
				{ 2, 4, 3 } };
		for (int i = 0; i <= N; i++) {
			for (int num : arrEdges[i]) {
				edges.get(i).add(num);
			}
		}
		System.out.println(Arrays.toString(bellmanFordAlgo(N, edges)));
	}

	public static int[] bellmanFordAlgo(int N, List<List<Integer>> edges) {

		int[] distArr = new int[N];
		Arrays.fill(distArr, (int) 10e6);
		distArr[0] = 0;

		// relaxation for N-1 times
		for (int i = 0; i < N - 1; i++) {
			for (List<Integer> it : edges) {
				int u = it.get(0);
				int v = it.get(1);
				int wt = it.get(2);
				if (distArr[u] + wt < distArr[v]) {
					distArr[v] = distArr[u] + wt;
				}
			}
		}
		// N th relaxation to check negative cycle.
		for (List<Integer> it : edges) {
			int u = it.get(0);
			int v = it.get(1);
			int wt = it.get(2);
			if (distArr[u] + wt < distArr[v]) {
				return new int[] { -1 };
			}
		}

		return distArr;

	}

}
