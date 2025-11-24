package striver_dsa_sheet.trees;

/**
 * <pre>
 * 
 * Find the Kth smallest element.
 * Approach 1:
 * we can use DFS / pre or in or post order traversal and store them in a List and sort them and find the kth element,
 * TC : O(N) + O(N log N)
 * SC : O(N)
 * 
 * Approach 2:
 * write down the In-order : Left, Root and Right
 * 
 *                       5
 *                 3           7
 *             1       4    6     8
 *                2
 * In order : 1 2 3 4 5 6 7 8       
 * the in-order of any given binary search tree is always in sorted order.
 * we can eliminate theO(N log N) for sorting;
 * Use a counter when we are visiting a node, and when it becomes == k, then return that node value.
 * 
 * 
 * 
 * 
 * </pre>
 */
public class KthSmallestElementInBST {
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

		int key = 3;
		System.out.println(kthSmallest(node10, key));

	}

	public static int kthSmallest(TreeNode root, int k) {
		int counter[] = { 0, -1 };
		kthSmallestRec(root, k, counter);
		return counter[1];
	}

	public static void kthSmallestRec(TreeNode node, int k, int[] counter) {

		if (node == null) {
			return;
		}
		kthSmallestRec(node.left, k, counter);
		counter[0]++;
		if (counter[0] == k) {
			counter[1] = node.val;
			return;
		}
		kthSmallestRec(node.right, k, counter);
	}
}
