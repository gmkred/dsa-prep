package striver_dsa_sheet.graphs;

/**
 * <pre>
 * We can find the shortest path between any nodes int he graph.
 * Works in negative cycle.
 * Node  -> Go via every vertex / node
 * 
 * use adjacent matrix instead of adjacent list.
 * 
 * 0 -> (1, 2) 
 * 1 -> (0, 1), (2, 3)
 * 3 -> (2, 4), (0, 3), (1, 5)
 * 
 * & -> infinity
 *    0 1 2 3
 *  0[0 2 & &]
 *  1[1 0 3 &]
 *  2[& & 0 &]
 *  3[3 5 4 0]
 *  
 *  via 0
 *  matrix[1,3] = Min (matrix[1][3], matrix[1][0]+matrix[0][3])
 *                    (&, 1+3) == (&, 4) 
 *                    4 is minimum.
 *  
 *  for every 2 nodes, go via all the nodes and find the shortest path for those 2 nodes.
 *  
 *  How to detect a negative cycle?
 *  if the costing of any node to itself is < 0, then there is a negative cycle.
 * 
 * </pre>
 */
public class FloydWarshallAlgo {
	public static void main(String[] args) {

	}

	public static void shortest_distance(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// -1 means, it is not reachable so update it with 1e9, and later change it to
				// -1.
				// (optional)
				if (matrix[i][j] == -1) {
					matrix[i][j] = (int) 1e9;
				}
				if (i == j)
					matrix[i][j] = 0;
			}
		}
//via each node
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					matrix[j][k] = Math.min(matrix[j][k], matrix[j][i] + matrix[i][k]);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// -1 means, it is not reachable so update it with 1e9, and later change it to
				// -1.
				// (optional)
				if (matrix[i][j] == (int) 1e9) {
					matrix[i][j] = -1;
				}
			}
		}
	}
}
