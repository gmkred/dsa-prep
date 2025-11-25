package striver_dsa_sheet.trees;

/**
 * This returns the size of the largest BST in the BT.
 * 
 */
public class LargestBSTinBT {
	public int maxSumBST(TreeNode root) {
		return rec(root).maxSize;
	}

	public static NodeValue rec(TreeNode root) {
		if (root == null) {
			return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
		}
		NodeValue left = rec(root.left);
		NodeValue right = rec(root.right);

		if (left.maxVal < root.val && root.val < right.minVal) {
			return new NodeValue(Math.min(left.minVal, root.val), Math.max(right.maxVal, root.val),
					left.maxSize + right.maxSize + 1);
		} else {
			// if BT is not BST at current node, send invalid MAX and MIN which will be
			// resolved in previous recursion stack.
			return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSize, right.maxSize));
		}
	}

	static class NodeValue {
		int minVal;
		int maxSize;
		int maxVal;

		NodeValue(int minVal, int maxVal, int maxSize) {
			this.maxSize = maxSize;
			this.minVal = minVal;
			this.maxVal = maxVal;
		}
	}
}
