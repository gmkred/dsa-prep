package striver_dsa_sheet.trees;

/**
 * <pre>
 * 
 * Morri's IN-order.
 * TC : O(N) and no extra space.
 * 
 * It uses the Threaded Binary Tree.
 * In Order
 *                   1
 *               2       3
 *            4    5   
 *                    6
 * 
 * 
 * we can have 2 thread
 * 
 * 4 --> 2
 * 6 --> 1
 * 
 * 4 2 5 6 1 3
 * 
 * 1st case
 * From the last node we go back to the sub root node.
 * 2nd case
 * The right most node of left subtree should connect t the  root.
 * 
 * If we again visit the same right most node of left side, then remove the thread and move to right.
 * 
 * </pre>
 */
public class MorrisPreTraversal {
	public static void main(String[] args) {
		TreeNode node = TreeTraversal.get();
		morrisINOrder(node);
	}

	public static void morrisINOrder(TreeNode node) {
		TreeNode cur = node;
		while (cur != null) {
			if (cur.left == null) {
				System.out.print(cur.val + " ");
				cur = cur.right;
			} else {
				TreeNode prev = cur.left;
				// find the right most node from this root node.
				while (prev.right != null && prev.right != cur) {
					prev = prev.right;
				}

				if (prev.right == null) {
					prev.right = cur;
					// move cur to left
					cur = cur.left;
				} else {
					prev.right = null;
					System.out.print(cur.val + " ");
					cur = cur.right;
				}
			}
		}
	}

}
