package striver_dsa_sheet.trees;

public class SameTrees {

	public static boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q != null) {
			return false;
		} else if (p != null && q == null) {
			return false;
		} else if (p == null && q == null) {
			return true;
		}

		if (p.val != q.val) {
			return false;
		}
		boolean left = isSameTree(p.left, q.left);
		boolean right = isSameTree(p.right, q.right);
		return left && right;
	}
}
