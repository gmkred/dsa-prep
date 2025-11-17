package striver_dsa_sheet.trees;

/**
 * 
 * <pre>
 * 
 * 
 * The longest path between any 2 nodes.
 * Does not need to pass via root
 * 
 * 
 *                   1
 *               2       3   
 *           4       5 
 *           
 *           
 * 4 2 1 3 = len 4 is diameter 
 *          
 *           			1
 *                  2        3
 *                       4        7  
 *                    5                8
 *                6                         9
 * 
 * 6 5 4 3 7 8 9 = len 7 is diameter
 * 
 * 
 * Find the left and right height from a particular node and check it can be max diameter.
 * 
 * 
 * lh + rh for all the nodes and take maximum
 * </pre>
 */
public class DiameterOfBinaryTree {

	// optimal
	// TC : O(N)
	public static int findlen(TreeNode node, int c[]) {
		if (node == null) {
			return 0;
		}
		int right = findlen(node.right, c);
		int left = findlen(node.left, c);
		c[0] = Math.max(c[0], left + right);
		return 1 + Math.max(left, right);
	}

	// brute force
	// TC : O(N^2)
	public static int diameterOfBinaryTree(TreeNode root) {
		int c[] = { 0 };
		maxDiameter(root, c);
		return c[0];
	}

	public static void maxDiameter(TreeNode node, int container[]) {
		if (node == null) {
			return;
		}
		int left = findlen(node.left);
		int right = findlen(node.right);
		container[0] = Math.max(left + right, container[0]);
		maxDiameter(node.left, container);
		maxDiameter(node.right, container);
		return;
	}

	public static int findlen(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int right = findlen(node.right);
		int left = findlen(node.left);
		return 1 + Math.max(left, right);
	}
}
