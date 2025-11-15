package striver_dsa_sheet.trees;

public class MorrisPreOrder {
	public static void main(String[] args) {
		TreeNode node = TreeTraversal.get();
		morrisPreOrder(node);
	}

//TC : ~O(2N) SP : O(1)
	public static void morrisPreOrder(TreeNode node) {
		TreeNode cur = node;
		while (cur != null) {
			if (cur.left == null) {
				System.out.print(cur.val + " ");
				cur = cur.right;
			} else {
				TreeNode prev = cur.left;
				// find he right most node from this root node.
				while (prev.right != null && prev.right != cur) {
					prev = prev.right;
				}

				if (prev.right == null) {
					prev.right = cur;
					// move cur to left
					// for pre order the root or sub root will the beginning so when we are at root
					// take the value.
					System.out.print(cur.val + " ");
					cur = cur.left;
				} else {
					prev.right = null;
					// cur will be sub root, at each level the nood is a sub root.
					cur = cur.right;
				}
			}
		}
	}

}
