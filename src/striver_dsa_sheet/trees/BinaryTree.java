package striver_dsa_sheet.trees;

/**
 * <pre>
 * 
 * 
 * In our system we have directories and inside that we have another directory and it can go on.
 * 
 * It is represented as Tree.
 * 
 * Starting point is root.
 * It has children
 * Leaf node -> a node that does not have children
 * Sub tree -> from given node the beneath children are sub trees.
 * 
 * Ancestors -> for a node, all the node in its hierarchy are ancestors.
 * 
 * Types:
 * 
 * 1. Full binary tree 
 * every node either have 2 children or 0 children
 * 			T
 *       T     T
 *     T   T   
 * 2. Complete BT
 * 	All levels are completely filled and except the last level.
 * 	The last level has all node in left as possible.
 * 	        T                         T     
 *      T       T       or         T     T   
 *   T    T  T    T             T     T 
 * 
 * 3. Perfect BT
 * 	All leaf nodes are at same level
 * 	             T
 *           T       T
 *        T    T  T     T
 *        
 * 4. Balanced BT
 * 	Height of tree at max log (N) nodes
 *  n=8
 *  log n = ~3 cannot have a height of >3
 * 
 * 5. Degenerate tree : every node has single child like lindex list.
 * 	n=4
 *            T
 *          T
 *        T
 *      T
 * 
 * 
 * 
 * 
 * Binary Tree representation
 * </pre>
 */
public class BinaryTree {

}
