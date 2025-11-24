package striver_dsa_sheet.trees;

public class FloorInBST {
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

		int key = 15;
		System.out.println(floorInBST(node10, key));
	}

	private static int floorInBST(TreeNode node, int key) {
		int floor = -1;
		while (node != null) {
			if (node.val == key) {
				return key;
			}

			if (key < node.val) {
				node = node.left;
			} else {
				floor = node.val;
				node = node.right;
			}
		}
		return floor;
	}
}
