package striver_dsa_sheet.trees;

public class SearchInABinarySearchTree {

	public static TreeNode searchBST(TreeNode root, int val) {
		if (root == null) {
			return null;
		}
		if (root.val == val) {
			return root;
		}
		TreeNode left = searchBST(root.left, val);
		TreeNode right = searchBST(root.right, val);
		TreeNode res = null;
		if (left != null) {
			res = left;
		} else if (right != null) {
			res = right;
		}
		return res;
	}
}
