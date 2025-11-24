package striver_dsa_sheet.trees;

public class IsSymmetricTree {

	public static boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		return isSymmetricRec(root.left, root.right);
	}

	/**
	 * <pre>
	 * 
	 * compare  tree1's right values with right tree2's left values
	 * compare  tree1's left values with right tree2's right values;
	 * 
	 * if any of them does not match, it will make it as false.
	 * </pre>
	 */
	public static boolean isSymmetricRec(TreeNode root1, TreeNode root2) {
		if (root1 == null || root2 == null) {
			return root1 == root2;
		}
		return (root1.val == root2.val) && isSymmetricRec(root1.left, root2.right)
				&& isSymmetricRec(root1.right, root2.left);
	}
}
