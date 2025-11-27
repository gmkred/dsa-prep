package striver_dsa_sheet.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * <pre>
 * Graph : nodes and edges. even a tree can be graph.
 * 
 * Directed graphs
 * bidirectional graphs.
 * 
 * 1 ---------- 4
 * 2 edges, we can go from 1 to 4 and 4 to 1
 * 
 * 1 ----------- 4
 * |             |
 * |             |
 * |             |
 * 2 ------------5
 *  \           /
 *    \       /
 *      \ 3 /
 * 
 * undirected graphs
 * unidirectional graphs.
 * 
 * 1 --------> 4
 * 
 * 
 * 1 ----------> 4
 * |             |
 * |             |
 * |             |
 * V             V
 * 2 ----------> 5
 *  \           /
 *    \       /
 *      \   /
 *       V V
 *        3 
 *        
 * 
 * 1 edge, only 1 to 4
 * 
 * we can also have
 * 1 <--------- 4
 * 1 edge, from 4 to 1
 * 
 * Cycles in a graph:
 * start from a node and end at that node.
 * Bidirectional graph is a undirected cyclic graph.
 * 
 * in directed graph, if start is not the end, then it is a directed Acyclic graph DAG.
 * in directed graph, if start is end, it is called as Directed Cyclic Graph.
 * 
 * 
 * path:
 * can contain a lot of nodes and each of them are reachable.
 * 
 *  1 ------- 2
 *            |
 *            |
 *            |
 *  4 ------- 3
 *           /
 *          /
 *         /
 *        5
 * 1 2 3 5 is a path
 * 1 2 3 2 1 is not a path, a node cannot appear twice in a path.
 * 
 * 
 * Degrees in a path in Undirected graph:
 * A degree of undirected graph, is the number of edges that inside or go outside a node.
 * 1 ----------- 4
 * |             |
 * |             |
 * |             |
 * 2 ------------5
 *  \           /
 *    \       /
 *      \ 3 /
 *      
 * degree(3) = 2
 * degree(5) = 3
 * degree(1) = 2
 * 
 * Property :
 * it states the the total degree of a graph is equal to 2*number of edges.
 * Total degrees = 2 * number of edges ( because each edge connect to 2 nodes)
 * 
 * 
 * Degrees in a directed graph:
 * indegree(node)
 * 
 * the number of incoming edges.
 * 
 * outdegree(node)
 * 
 * the number of out going edges.
 * 
 * Edge weight:
 * there will be weights assigned to each edge, in case of not present assume it as 1.
 * 
 * 
 * 
 * How the questioning will be ?
 * 
 * Input of a graph:
 * 	first line given n and m and also states directed or undirected graphs.
 * n - nodes = 5
 * m - edges = 6
 * next m lines represent edges.
 * 
 * if input is 2 1, the edge between 2 and 1.
 * 
 * 5 6 - number of nodes and edges
 * 
 * 2 1
 * 1 3
 * 2 4
 * 3 4
 * 2 5
 * 4 5
 *  0  1  2  3  4  5
 *0 [0, 0, 0, 0, 0, 0]
 *1 [0, 0, 1, 1, 0, 0]
 *2 [0, 1, 0, 0, 1, 1]
 *3 [0, 1, 0, 0, 1, 0]
 *4 [0, 0, 1, 1, 0, 1]
 *5 [0, 0, 1, 0, 1, 0]
 * 
 * how to store?
 * 2 ways:
 * 
 * MATRIX, called ad adjacency matrix
 * 
 * its 1 based indexing so define adj[n+1][n+1]
 * 
 * if there is an edge between 1 and 2, mark [1,2] and [2,1] in the matrix as 1.
 * this matrix tell if there is  an edge between 1 and 2.
 * 
 * 
 * SC : (N*N) and this is a costly thing.
 * it is taking so much space.
 * 
 * so, we maintain 1D array : int[N+1]
 * and this array contains a list. every index contains an empty list.
 * 
 * What is the motive of the list?
 * 
 * we consider index as node, so we store the adjacent neighbors of that node in a list and that list is referred in that index.
 * 
 * 0
 * 1 = {2,3}
 * 2 = {1,4,5}
 * 3 = {1,4}
 * 4 = {3,2,5}
 * 5 = {2,4}
 * 
 * SC : O(2*E) because, here we store who matters.
 * 
 * 
 * in Directed graph:
 * SC : O(E) because we do not store other way.
 * 
 * In case of weighted graphs:
 * in matrix instead of 1, store the weight
 * 
 * </pre>
 * 
 */
public class Graph {
	public static void main(String[] args) {
//		adjacentMatrix();
		adjacentListDirected();
	}

	public static void adjacentMatrixUndirected() {
		Scanner sc = new Scanner(System.in);
		String nadnm = sc.nextLine();
		String[] arr = nadnm.trim().split(" ");
		int adj[][] = new int[Integer.parseInt(arr[0]) + 1][Integer.parseInt(arr[0]) + 1];
		for (int i = 0; i < Integer.parseInt(arr[1]); i++) {
			String nodes = sc.nextLine();
			String[] nodesArr = nodes.trim().split(" ");
			adj[Integer.parseInt(nodesArr[0])][Integer.parseInt(nodesArr[1])] = 1;
			adj[Integer.parseInt(nodesArr[1])][Integer.parseInt(nodesArr[0])] = 1;
		}
		for (int i[] : adj) {
			System.out.println(Arrays.toString(i));
		}
	}

	public static void adjacentListUndirected() {
		Scanner sc = new Scanner(System.in);
		String nadnm = sc.nextLine();
		String[] arr = nadnm.trim().split(" ");
		List<Integer> adjList[] = new List[Integer.parseInt(arr[0]) + 1];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 0; i < Integer.parseInt(arr[1]); i++) {
			String nodes = sc.nextLine();
			String[] nodesArr = nodes.trim().split(" ");
			adjList[Integer.parseInt(nodesArr[0])].add(Integer.parseInt(nodesArr[1]));
			adjList[Integer.parseInt(nodesArr[1])].add(Integer.parseInt(nodesArr[0]));
		}
		for (List<Integer> i : adjList) {
			System.out.println((i));
		}
	}

	public static void adjacentMatrixDirected() {
		Scanner sc = new Scanner(System.in);
		String nadnm = sc.nextLine();
		String[] arr = nadnm.trim().split(" ");
		int adj[][] = new int[Integer.parseInt(arr[0]) + 1][Integer.parseInt(arr[0]) + 1];
		for (int i = 0; i < Integer.parseInt(arr[1]); i++) {
			String nodes = sc.nextLine();
			String[] nodesArr = nodes.trim().split(" ");
			adj[Integer.parseInt(nodesArr[0])][Integer.parseInt(nodesArr[1])] = 1;
		}
		for (int i[] : adj) {
			System.out.println(Arrays.toString(i));
		}
	}

	public static void adjacentListDirected() {
		Scanner sc = new Scanner(System.in);
		String nadnm = sc.nextLine();
		String[] arr = nadnm.trim().split(" ");
		List<Integer> adjList[] = new List[Integer.parseInt(arr[0]) + 1];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 0; i < Integer.parseInt(arr[1]); i++) {
			String nodes = sc.nextLine();
			String[] nodesArr = nodes.trim().split(" ");
			// only one direction
			adjList[Integer.parseInt(nodesArr[0])].add(Integer.parseInt(nodesArr[1]));
		}
		for (List<Integer> i : adjList) {
			System.out.println((i));
		}
	}

	public static void adjacentMatrixWeighted() {
		Scanner sc = new Scanner(System.in);
		String nadnm = sc.nextLine();
		String[] arr = nadnm.trim().split(" ");
		int adj[][] = new int[Integer.parseInt(arr[0]) + 1][Integer.parseInt(arr[0]) + 1];
		for (int i = 0; i < Integer.parseInt(arr[1]); i++) {
			String nodes = sc.nextLine();
			String[] nodesArr = nodes.trim().split(" ");
			// store weight
			adj[Integer.parseInt(nodesArr[0])][Integer.parseInt(nodesArr[1])] = Integer.parseInt(nodesArr[2]);
			adj[Integer.parseInt(nodesArr[1])][Integer.parseInt(nodesArr[0])] = Integer.parseInt(nodesArr[2]);
		}
		for (int i[] : adj) {
			System.out.println(Arrays.toString(i));
		}
	}

	public static void adjacentListWeighted() {
		Scanner sc = new Scanner(System.in);
		String nadnm = sc.nextLine();
		String[] arr = nadnm.trim().split(" ");
		List<Integer[]> adjList[] = new List[Integer.parseInt(arr[0]) + 1];
		for (int i = 0; i < adjList.length; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 0; i < Integer.parseInt(arr[1]); i++) {
			String nodes = sc.nextLine();
			String[] nodesArr = nodes.trim().split(" ");
			adjList[Integer.parseInt(nodesArr[0])]
					.add(new Integer[] { Integer.parseInt(nodesArr[1]), Integer.parseInt(nodesArr[2]) });
			adjList[Integer.parseInt(nodesArr[1])]
					.add(new Integer[] { Integer.parseInt(nodesArr[0]), Integer.parseInt(nodesArr[2]) });
		}
		for (List<Integer[]> i : adjList) {
			for (Integer[] j : i) {
				System.out.println((Arrays.toString(j)));
			}
		}
	}

}
