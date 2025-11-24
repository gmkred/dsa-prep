package striver_dsa_sheet.trees;

public class SearchInBST {
	public static void main(String[] args) {
		TreeNode node8 = new TreeNode(8);
		TreeNode node5 = new TreeNode(5);
		TreeNode node12 = new TreeNode(12);
		TreeNode node4 = new TreeNode(4);
		TreeNode node7 = new TreeNode(7);
		TreeNode node6 = new TreeNode(6);
		TreeNode node10 = new TreeNode(10);
		TreeNode node14 = new TreeNode(14);
		TreeNode node13 = new TreeNode(13);

		node8.left = node5;
		node8.right = node12;
		node5.left = node4;
		node5.right = node7;
		node7.left = node6;
		node12.left = node10;
		node12.right = node14;
		node14.left = node13;
		System.out.println(findNumInBSTRec(node8, 10));
		System.out.println(findNumInBST(node8, 10));
	}

	/**
	 * TC : O(Log N) SC : O(1)
	 */
	public static boolean findNumInBSTRec(TreeNode node, int num) {
		if (node == null) {
			return false;
		}
		if (num < node.val) {
			if (findNumInBSTRec(node.left, num)) {
				return true;
			}
		} else if (num > node.val) {
			if (findNumInBSTRec(node.right, num)) {
				return true;
			}
		} else {
			return true;
		}
		return false;
	}

	public static boolean findNumInBST(TreeNode node, int num) {
		while (node != null) {
			if (num < node.val) {
				node = node.left;
			} else if (num > node.val) {
				node = node.right;
			} else {
				return true;
			}
		}
		return false;
	}

}
