package striver_dsa_sheet.trees;

import java.util.Stack;

public class IterativePostOrder {
	public static void main(String[] args) {

		TreeNode node = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);

		node.left = node2;
		node.right = node3;
		node2.left = node4;
		node2.right = node5;
		node5.left = node6;
		node5.right = node7;
		traversal(node);
	}

	/**
	 * <pre>
	 * 
	 * 
	 *                  1
	 *            2           3   
	 *        4       5 
	 *             6     7
	 *              
	 *  Use stack
	 *  left right root
	 *  
	 *  4
	 *  6
	 *  7
	 *  5
	 *  2
	 *  3
	 *  1
	 *  
	 *  
	 *                   1
	 *             2          7
	 *         3           8     9  
	 *            4                 10
	 *               5 
	 *                  6
	 *                  
	 *  6 5 4 3 2 8 10 9 7 1
	 * 
	 * 
	 * Use stack 
	 * Go as left as possible.
	 * TC : O(2N) because we go once and we return
	 * 
	 * </pre>
	 * 
	 */
	public static void traversal(TreeNode node) {

		Stack<TreeNode> stack = new Stack<>();
		TreeNode cur = node;
		while (cur != null || !stack.isEmpty()) {

			// if cur is not null add to stack
			if (cur != null) {
				stack.push(cur);
				// now move to extreme left to print from there
				cur = cur.left;
			} else {
				TreeNode temp = stack.peek().right;
				// if right is null that means it last child or leaf move back and print the
				// numbers
				if (temp == null) {
					temp = stack.pop();
					System.out.println(temp.val);
					// if temp is the right of its root node then we have to print that root node,
					// as this is LEFT -> RIGHT -> ROOT
					while (!stack.isEmpty() && temp == stack.peek().right) {
						temp = stack.pop();
						System.out.println(temp.val);
					}
				} else {
					// if temp is not null, that means there are some nodes under the right node so
					// again apply same logic to them.
					cur = temp;
				}
			}
		}
	}

}
