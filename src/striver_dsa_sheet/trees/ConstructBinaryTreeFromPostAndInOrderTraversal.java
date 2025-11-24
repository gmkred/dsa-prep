package striver_dsa_sheet.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *             0   1  2    3   4  5
 * inorder = {40, 20, 50, 10, 60, 30}; Left -->  root --> right
 *              0    1   2   3   4   5 
 * postorder = {40, 50, 20, 60, 30, 10};  Left --> Right --> root
 * 
 *                           10
 *                    20             30 
 *               40        50   60   
 * 
 * int postLastindex= 5;
 * 
 * rec(int[] inOrder, int inStart, int inEnd, int[] postOrder, int postStart, int postEnd, Map<Integer,integer> inMap);
 * 
 * The roots in post order will be at the end.
 * 
 *             0   1  2    3   4  5
 * inorder = {40, 20, 50, 10, 60, 30}; Left -->  root --> right
 *              0    1   2   3   4   5 
 * postorder = {40, 50, 20, 60, 30, 10};  Left --> Right --> root
 * 
 * </pre>
 */
public class ConstructBinaryTreeFromPostAndInOrderTraversal {
	public static TreeNode buildTree(int[] inorder, int[] postorder) {
		Map<Integer, Integer> inMap = new HashMap();
		for (int i = 0; i < inorder.length; i++) {
			inMap.put(inorder[i], i);
		}
		return rec(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, inMap);
	}

//similar to pre and in oreder constructor, just take the postorder's last elements as root and avoid the postEnd at eac recursiove.
	public static TreeNode rec(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd,
			Map<Integer, Integer> inMap) {
		if (inStart > inEnd || postStart > postEnd) {
			return null;
		}
		int rootElementIndex = inMap.get(postorder[postEnd]);
		int numsLeft = rootElementIndex - inStart;
		TreeNode root = new TreeNode(inorder[rootElementIndex]);
		root.left = rec(inorder, inStart, rootElementIndex - 1, postorder, postStart, postStart + numsLeft - 1, inMap);

		root.right = rec(inorder, rootElementIndex + 1, inEnd, postorder, postStart + numsLeft, postEnd - 1, inMap);
		return root;
	}
}
