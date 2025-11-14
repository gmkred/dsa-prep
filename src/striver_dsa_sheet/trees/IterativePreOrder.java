package striver_dsa_sheet.trees;

import java.util.Stack;

public class IterativePreOrder {
	public static void main(String[] args) {
		TreeNode node = TreeTraversal.get();
		iterativePreOrder(node);
	}

	/**
	 * <pre>
	 * 
	 * With out recursion.
	 * Use Stack - FILO
	 * First put root to stack.
	 * 
	 * take top and print and first take right and then left to put into stack.
	 * 
	 * 
	 * 
	 * 
	 * </pre>
	 */
	public static void iterativePreOrder(TreeNode node) {
		Stack<TreeNode> stack = new Stack<>();
		stack.push(node);

		while (!stack.isEmpty()) {
			TreeNode root = stack.pop();
			// we want left first so pushing right first gives after polling left.
			System.out.print(root.val + " ");
			if (root.right != null) {
				stack.push(root.right);
			}
			if (root.left != null) {
				stack.push(root.left);
			}
		}
		System.out.println();
	}
}
