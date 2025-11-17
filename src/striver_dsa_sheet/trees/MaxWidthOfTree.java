package striver_dsa_sheet.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <pre>
 * Width -> max number of nodes between 2 nodes on same level
 * E - empty or null node
 * 
 *                         1
 *                    3          2
 *                5        3E         9
 *               
 *                max width = 4 -> last level between 5 to 9, consider the empty node as well.
 *                
 *                          1
 *                   3             2
 *             5          E    E       4
 *         7       E   E    EE    E  E    6
 *         
 *         Max width = 8
 * We should also consider the imaginary between the extreme node at a level.
 * 
 * Width is dependent on level.
 * If we index each node?
 * 
 * (last Index - first index + 1 )
 * 
 * 0 based indexing
 * if root node index is 0
 * i = 0
 * for left index = 2*i+1
 * for right index = 2*i+2
 *                    0
 *         2*0+1               2*0+2    
 * i=1       1                   2           i=2
 *    2*1+1      2*1+2      2*2+1     2*2+2
 *     3          4          4          6
 * 
 * This will overflow if the tree length > 10^5
 *                         0
 *                     1
 *                 2
 *             3
 *          4
 *          
 * if we keep on multiplying by 2 it overflow
 * So modify it to prevent overflow
 * 
 * 
 * So instead of 2*i+1 or 2*i+2
 *  
 *  now the i for index level indexing will be = (current node index - min);
 *  i = (current ndoe index - min)
 *  for next node indexing do 2*i+1, 2*i+2
 *  With this we can get proper indexing for each level. starting from 1 and considering imaginary nodes.
 * 
 * 
 * </pre>
 */
public class MaxWidthOfTree {
	public static void main(String[] args) {
		TreeNode node = TreeTraversal.get();
		System.out.println(widthOfBinaryTree(node));
	}

	public static int widthOfBinaryTree(TreeNode root) {
		Queue<Pair> q = new LinkedList();
		// setting 0 index for root
		q.offer(new Pair(root, 0));
		int max = Integer.MIN_VALUE;
		while (!q.isEmpty()) {
			int currentLevelSize = q.size();
			// first node of the level index.
			int currentLevelMinIndex = q.peek().value;
			int lastIndex = 0;
			// we need to traverse only current level nodes, becasue we add next level nodes
			// and if we do not restrict using for loop, it will consider all the nodes as
			// current level.
			for (int i = 0; i < currentLevelSize; i++) {
				Pair tempP = q.poll();
				TreeNode temp = tempP.key;
				int currentNodeIndex = tempP.value;
				lastIndex = currentNodeIndex;
				if (temp.left != null) {
					q.offer(new Pair(temp.left, 2 * currentNodeIndex + 1));
				}
				if (temp.right != null) {
					q.offer(new Pair(temp.right, 2 * currentNodeIndex + 2));
				}
			}
			max = Math.max(max, lastIndex - currentLevelMinIndex + 1);
		}
		return max;
	}
}
