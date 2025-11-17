package striver_dsa_sheet.trees;

/**
 * <pre>
 * 
 * 
 * Balanced tree : left and right subtrees 
 * height should not have difference >1
 * 
 * at any the height of left and right diff should not be >1 then it is a balance tree.
 * 
 * </pre>
 */
public class CheckForBalancedBinaryTree {
	public static boolean isBalanced(TreeNode root) {
		return isTrue(root) == -1 ? false : true;
	}

	public static int isTrue(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int left = isTrue(node.left);
		int right = isTrue(node.right);
		// when the diff is > 1 then return -1 or retur nheight
		if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
			return -1;
		} else {
			return 1 + Math.max(left, right);
		}
	}
}
