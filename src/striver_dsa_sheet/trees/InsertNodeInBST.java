package striver_dsa_sheet.trees;

/**
 * <pre>
 * 
 * Go till the end of a leaf node in search of given val and attach the new node to the leaf.
 * 
 * </pre>
 */
public class InsertNodeInBST {
	public TreeNode insertIntoBST(TreeNode root, int val) {
		// assume the given val is present in BST and find the in the BST, it will
		// always take us to a leaf node, just insert the new node at left or right of
		// that lead.
		if (root == null) {
			root = new TreeNode(val);
			return root;
		}
		TreeNode temp = root;
		while (temp != null) {
			if (temp.val < val) {
				if (temp.right != null)
					temp = temp.right;
				else {
					temp.right = new TreeNode(val);
					break;
				}
			} else {
				if (temp.left != null)
					temp = temp.left;
				else {
					temp.left = new TreeNode(val);
					break;
				}
			}
		}
		return root;
	}
}
