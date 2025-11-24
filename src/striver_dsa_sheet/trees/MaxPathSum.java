package striver_dsa_sheet.trees;

/**
 * <pre>
 * 
 * 
 * Find the max sum path without repeating the nodes.
 * 
 * We can solve this using Max height and max width code.
 *                        -10
 *                 9               20
 *                         15              7
 *                         
 * Max path sum is 20+15+7 = 42
 * 
 * </pre>
 * 
 */
public class MaxPathSum {
	public static int maxPathSum(TreeNode root) {
		int arr[] = { -1001 };
		maxPathSum(root, arr);
		return arr[0];
	}

	public static int maxPathSum(TreeNode root, int arr[]) {
		if (root == null) {
			return 0;
		}
		// Do not consider if we get any negative from either left or right.
		// if we avoid -ve, then the max path either from left to root or right to root,
		// if we do not avoid it, then we do not get max path sum
		int left = Math.max(0, maxPathSum(root.left, arr));
		int right = Math.max(0, maxPathSum(root.right, arr));
		// get the max path value adding with the current sub root
		arr[0] = Math.max(arr[0], root.val + left + right);
		// return only the max value of left and right with the current sub root node
		// value. this because, a max path will only have 2 leafs with a root, if we
		// return nood.val+left+right, we are considering 2 lelaves from on side which
		// is
		// not correct.
		// this is used to make another max with the ancestor node.
		return root.val + Math.max(left, right);
	}
}
