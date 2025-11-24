package striver_dsa_sheet.trees;

import java.util.Stack;

public class FlattenABinaryTree {
	public void flattenBinaryTreeWithStack(TreeNode root) {
		Stack<TreeNode> stack = new Stack();
		TreeNode temp = root;
		while (!stack.isEmpty() || temp != null) {
			if (temp.left != null) {
				if (temp.right != null) {
					stack.push(temp.right);
				}
				temp.right = temp.left;
				temp.left = null;
			} else if (temp.right == null) {
				if (!stack.isEmpty()) {
					TreeNode temp2 = stack.pop();
					temp.right = temp2;
				}
			}
			temp = temp.right;
		}
	}
}
