package striver_dsa_sheet.trees;

public class LargestBSTSuminBT {
	public static void main(String[] args) {

	}

	// optimal
	public int maxSumBSTOptimal(TreeNode root) {
		return (int) Math.max(rec(root).maxSum, 0); // Return 0 if no valid BST is found
	}

	public static NodeValue rec(TreeNode root) {
		if (root == null) {
			// when its null, assume it as valid BST and set MAX for min and MIN for max
			// value and 0 as null's node value, which represents a valid BST.
			return new NodeValue(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
		}
		NodeValue left = rec(root.left);
		NodeValue right = rec(root.right);
		// Check if current node can form a valid BST
		// max of left tree should be less than the current node and min of right tree
		// should be greater than the current node value to be a valid BST
		if (left.maxVal < root.val && root.val < right.minVal) {
			// Return new NodeValue with updated minVal, maxVal and sum
			return new NodeValue(Math.min(left.minVal, root.val), Math.max(right.maxVal, root.val),
					left.maxSum + right.maxSum + root.val);
		} else {
			// If it's not a valid BST, return invalid values, it will propagate to and the
			// Max value that is found
			return new NodeValue(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSum, right.maxSum));
		}
	}

	static class NodeValue {
		long minVal; // Minimum value in this subtree
		long maxVal; // Maximum value in this subtree
		long maxSum; // Maximum sum of a valid BST in this subtree

		NodeValue(long minVal, long maxVal, long maxSum) {
			this.minVal = minVal;
			this.maxVal = maxVal;
			this.maxSum = maxSum;
		}
	}

//brute force
	public static int maxSumBST(TreeNode root) {
		int max[] = { Integer.MIN_VALUE };
		int container[] = { 0 };
		max(root, container, max);
		return max[0] < 0 ? 0 : max[0];
	}

	public static void max(TreeNode node, int[] container, int[] max) {
		if (node == null) {
			return;
		}
		max(node.left, container, max);
		if (isValidBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE, container)) {
			max[0] = Math.max(container[0], max[0]);
		}
		container[0] = 0;
		max(node.right, container, max);
	}

	public static boolean isValidBST(TreeNode node, int left, int right, int[] container) {
		if (node == null) {
			// if it reaches null, it means that part of BT is a part of BST.
			return true;
		}
		container[0] += node.val;
		return (node.val > left && node.val < right) && isValidBST(node.left, left, node.val, container)
				&& isValidBST(node.right, node.val, right, container);
	}
}
