package striver_dsa_sheet.trees;

/**
 * <pre>
 *                              8
 *                  5                      12
 *           2             7        10             13
 *       1      3       6      8
 *                 4    
 * delete node 5,
 * 
 * we know that to a node all its left nodes are smaller than all its right nodes.
 * 
 * left side max node < right side min node.
 * 
 * By this we can find the node and attach all of its right side nodes to the max node of its left node.
 * 
 * 
 * 
 * </pre>
 */
public class DeleteNodeInBST {
	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		if (root.val == key) {
			return makeConnection(root);
		}
		TreeNode temp = root;
		while (temp != null) {
			if (temp.val > key) {
				if (temp.left != null && temp.left.val == key) {
					temp.left = makeConnection(temp.left);
					break;
				} else {
					temp = temp.left;
				}
			} else {
				if (temp.right != null && temp.right.val == key) {
					temp.right = makeConnection(temp.right);
					break;
				} else {
					temp = temp.right;
				}
			}
		}
		return root;
	}

	public TreeNode makeConnection(TreeNode node) {
		// if no left, then return right
		if (node.left == null) {
			return node.right;
			// if no right then return left.
		} else if (node.right == null) {
			return node.left;
		} else {
			// if both present make the connection and return left.
			TreeNode rightChild = node.right;
			TreeNode lastNode = lastNode(node.left);
			lastNode.right = rightChild;
			return node.left;
		}
	}

	public TreeNode lastNode(TreeNode node) {
		if (node.right == null) {
			return node;
		}
		return lastNode(node.right);
	}
}
