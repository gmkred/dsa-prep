package striver_dsa_sheet.trees;

public class LowestCommonAncestor {
	/**
	 * <pre>
	 * 
	 * Generate all the paths and find the path that is common where the p and q are present
	 *                           3
	 *                    5              1
	 *               6        2      0       8
	 *                    7       4
	 *                 
	 * p = 6, q = 4
	 * Firstly get both the paths that are having 6 and 4.
	 * 
	 * {3 5 6}
	 * {3 5 2 4}
	 * 
	 * 5 is the last ancestor that is common in both the paths
	 * 
	 * </pre>
	 */
	public static TreeNode lcaBrute(TreeNode root, TreeNode p, TreeNode q) {

		return null;
	}

	/**
	 * <pre>
	 * 
	 * 
	 * This is optimal solution instead of generating paths, we can check on each node if it is either p or q.
	 * If the node is any of p or q, return that node with out proceeding.
	 * If it reaches null, return null.
	 * 
	 * And at any node, the p and q are returned as not null, that is the ancestor node.
	 * If only either of p or q is not null, only return p or q instead of that particular node.
	 * 
	 * </pre>
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		if (root == p) {
			return p;
		}
		if (root == q) {
			return q;
		}
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		// if both p and q are found then return current node as ancestor.
		if (left != null && right != null) {
			return root;
			// it is possible that p or q can be the ancestor, then we cannot go further so
			// left or right will be null, so return which is not null.
		} else if (left == null) {
			return right;
		} else {
			return left;
		}
	}
}
