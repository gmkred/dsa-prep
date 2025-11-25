package striver_dsa_sheet.trees;

import java.util.Stack;

/**
 * <pre>
 * BST iterator implementation
 * 
 * 
 * </pre>
 */
public class BSTIterator {
	private Stack<TreeNode> stack = new Stack();
	// reverse -> true -> before
	// reverse -> false -> next
	boolean reverse = true;

	public BSTIterator(TreeNode root, boolean isReverse) {
		reverse = isReverse;
		pushAll(root);

	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}

	public int next() {
		TreeNode temp = stack.pop();
		if (reverse == false) {
			pushAll(temp.right);
		} else {
			pushAll(temp.left);
		}
		return temp.val;
	}

	private void pushAll(TreeNode node) {
		while (node != null) {
			stack.push(node);
			if (reverse == true) {
				node = node.right;
			} else {
				node = node.left;
			}
		}
	}
}
