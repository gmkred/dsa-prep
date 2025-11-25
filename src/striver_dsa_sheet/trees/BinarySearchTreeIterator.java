package striver_dsa_sheet.trees;

import java.util.Stack;

/**
 * <pre>
 * 
 * hasNext -> is there anything next
 * next -> get the next node
 * 
 * 
 * 
 * 
 * TC : ~O(1)
 * SC : O(H) height of the tree.
 * 
 * constructor 
 * BSTIterator(TreeNode node)
 * 
 * when we initialize an object, store all the lefts into a stack
 * And when we get next node, store the left nodes of right node of the popped node,
 * this way it will maintain the sorting order.
 * </pre>
 */
public class BinarySearchTreeIterator {
	private Stack<TreeNode> stack = new Stack<TreeNode>();

//initially store all the lefts of the root into stack.
	public BinarySearchTreeIterator(TreeNode root) {
		pushAll(root);
	}

//when getting a node from stack, check if there is any right to it and store that right and lefts of that right node. This way the sorting order is maintained and it follows in-order traversal.
	public int next() {
		TreeNode temp = stack.pop();
		pushAll(temp.right);
		return temp.val;
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}

//Store the root or sub root and its lefts.
	public void pushAll(TreeNode node) {
		while (node != null) {
			stack.push(node);
			node = node.left;
		}
	}
}
