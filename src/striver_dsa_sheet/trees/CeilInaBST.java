package striver_dsa_sheet.trees;

/**
 * <pre>
 * 
 * 
 * Ceil in a BST
 * 
 * k = 8
 * 
 * find a lowest value >= k,
 * 
 *                           10
 *                   5                     13
 *            3            6          11          14      
 *      2         4              9
 * 
 * </pre>
 */
public class CeilInaBST {
	public static void main(String[] args) {
		TreeNode node10 = new TreeNode(10);
		TreeNode node5 = new TreeNode(5);
		TreeNode node13 = new TreeNode(13);
		TreeNode node3 = new TreeNode(3);
		TreeNode node6 = new TreeNode(6);
		TreeNode node2 = new TreeNode(2);
		TreeNode node4 = new TreeNode(4);
		TreeNode node9 = new TreeNode(9);
		TreeNode node11 = new TreeNode(11);
		TreeNode node14 = new TreeNode(14);

		node10.left = node5;
		node10.right = node13;
		node5.left = node3;
		node5.right = node6;
		node3.left = node2;
		node3.right = node4;
		node6.right = node9;
		node13.left = node11;
		node13.right = node14;

		int key = 8;
		System.out.println(ceilInBST(node10, key));
	}

	public static int ceilInBST(TreeNode node, int key) {

		int ceil = -1;
		while (node != null) {
			// if exact key found, then return it.
			if (node.val == key) {
				ceil = node.val;
				return ceil;
			}
			if (key > node.val) {
				node = node.right;
				// if
			} else {
				ceil = node.val;
				node = node.left;
			}
		}
		return ceil;
	}
}
