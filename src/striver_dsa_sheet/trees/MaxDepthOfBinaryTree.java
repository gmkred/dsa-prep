package striver_dsa_sheet.trees;

/**
 * Number of levels of binary tree;
 * 
 */
public class MaxDepthOfBinaryTree {
	public static void main(String[] args) {
		TreeNode node = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		TreeNode node10 = new TreeNode(10);
		TreeNode node11 = new TreeNode(11);

		node.left = node2;
		node.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		node7.left = node8;
		node8.right = node9;
		node9.left = node10;
		node10.left = node11;
		System.out.println(maxDepth(node));
	}

	/**
	 * <pre>
	 * 
	 * We can use Level order as count the levels
	 * Or recursive solution and find the max depth.
	 * 
	 * </pre>
	 */
	public static int maxDepth(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int left = maxDepth(node.left);
		int right = maxDepth(node.right);
		// 1 for current node consideration
		return 1 + Math.max(left, right);
	}

}
