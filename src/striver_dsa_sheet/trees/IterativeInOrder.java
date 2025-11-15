package striver_dsa_sheet.trees;

import java.util.Stack;

public class IterativeInOrder {
	public static void main(String[] args) {

		TreeNode node = TreeTraversal.get();
		traversal(node);

	}

	/**
	 * <pre>
	 * 
	 * 
	 *                  1
	 *              2       3   
	 *          4      5 
	 *              6     7
	 *              
	 *  Use stack
	 *  
	 *  3
	 *  7
	 *  6
	 *  5
	 *  4
	 *  2
	 *  1
	 *  
	 *  We will be removing the eleemnts once it reach to null and move to other side if there are nodes left.
	 *  Try to store all left side nodes until there is a null.
	 *  If there is null, then pick the last node from the stack and print.
	 *  this time go to the right and again check if there is left to that right node.
	 *  
	 *   This way when we reach end of left we start taking the nodes in reverse order and move to right which is inorder traversal.
	 * 
	 * 
	 * 
	 * </pre>
	 * 
	 */
	public static void traversal(TreeNode node) {

		Stack<TreeNode> stack = new Stack<>();
		while (true) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				if (stack.isEmpty()) {
					break;
				}
				TreeNode temp = stack.pop();
				System.out.println(temp.val);
				node = temp.right;
			}
		}
	}

}
