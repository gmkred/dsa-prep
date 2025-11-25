package striver_dsa_sheet.trees;

/**
 * <pre>
 * 
 * 
 *                          13
 *                 10                15
 *            7          12     14          17
 *                 9                   16
 *            8         
 * 
 * for a node to be in a BST, it should be in the range of its parents parent and parents value.
 * example : to check if 16 is in a valid BST, it should be in the range of [15 , 17]
 * to check 9, it should be in the range of [7, 10]
 * 
 * Initially for root node, consider [MIN_VALUE, MAX_VALUE] as range.
 * 
 * 
 * 
 * </pre>
 */

public class ValidateBST {
	public static void main(String[] args) {
		TreeNode node10 = new TreeNode(10);
		TreeNode node5 = new TreeNode(5);
		TreeNode node13 = new TreeNode(13);
		TreeNode node3 = new TreeNode(3);
		TreeNode node6 = new TreeNode(6);
		TreeNode node2 = new TreeNode(2);
		TreeNode node4 = new TreeNode(4);
		TreeNode node9 = new TreeNode(9);
		TreeNode node11 = new TreeNode(11);
		TreeNode node14 = new TreeNode(14);

		node10.left = node5;
		node10.right = node13;
		node5.left = node3;
		node5.right = node6;
		node3.left = node2;
		node3.right = node4;
		node6.right = node9;
		node13.left = node11;
		node13.right = node14;
		System.out.println(isValidBST(node10));
	}

	public static boolean isValidBST(TreeNode root) {
		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public static boolean isValidBST(TreeNode node, long left, long right) {

		if (node == null) {
			// if it reaches null, it means that part of BT is a part of BST.
			return true;
		}
		return (node.val > left && node.val < right) && isValidBST(node.left, left, node.val)
				&& isValidBST(node.right, node.val, right);

	}

}
