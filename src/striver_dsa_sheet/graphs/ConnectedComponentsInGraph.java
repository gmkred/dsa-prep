package striver_dsa_sheet.graphs;

/**
 * <pre>
 * 
 * N =10, M=8
 * nodes, edges
 * 
 * 1 2
 * 1 3
 * 2 4
 * 3 4
 * 5 6
 * 5 7
 * 8 9
 * 10
 * 
 * These are 4 components of single graph.
 * 1 ----- 2        5           8      
 * |       |       / \         /       10
 * |       |      /   \       /
 * 3 ----- 4     6-----7     9
 * 
 * Visited array:
 * when we traverse this kind of graphs, we should use visited array.
 * 
 * create an array of 11 size.
 * run a loop from 1 to 10, if the node is not visited we run the traversal code from that node.
 * Do this for any kind of graph.
 * 
 * </pre>
 * 
 */
public class ConnectedComponentsInGraph {

}
