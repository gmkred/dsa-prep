package striver_dsa_sheet.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 
 * preorder = [3,9,20,15,7], 
 * inorder = [9,3,15,20,7]
 * 
 * preorder starts with Root, left and right
 * inorder starts with left, root, post
 * 
 * so which ever first element in the pre order will be the root of node element of the tree.
 * </pre>
 */
public class ConstructBinaryTreeFromPreAndInOrderTraversal {
	public static void main(String[] args) {
		int[] preorder = { 3, 9, 20, 15, 7 };
		int[] inorder = { 9, 3, 15, 20, 7 };
		System.out.println(buildTree(preorder, inorder));
	}

	public static TreeNode buildTree(int[] preorder, int[] inorder) {
		// element, index
		Map<Integer, Integer> inMap = new HashMap();
		for (int i = 0; i < inorder.length; i++) {
			inMap.put(inorder[i], i);
		}
		return rec(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, inMap);
	}

	public static TreeNode rec(int[] pre, int[] in, int preStart, int preEnd, int inStart, int inEnd,
			Map<Integer, Integer> inMap) {
		if (preEnd < preStart || inEnd < inStart) {
			return null;
		}
		int preOrderElement = pre[preStart];
		int inorderIndex = inMap.get(preOrderElement);
		int numsLeft = inorderIndex - inStart;
		TreeNode root = new TreeNode(in[inorderIndex]);
		/**
		 * <pre>
		 * Number of left elements from root = find the root element index in inorder
		 * array, from this we can get the left side elements count. 
		 * preStart for left tree starts from preStart + 1 
		 * preStart for right tree starts from preStart + number of left elements + 1 
		 * preOrder frst elment is the root node of the inOrder. 
		 * preEnd for left tree will end after the number of left elements present from the root element in the inorder. 
		 * preEnd for right tree will end at preEnd itself. 
		 * inStart for left tree will be inStart itself. 
		 * inStart for right tree will be starting at inorderIndex+1 
		 * inEnd for left tree will end at inorderIndex-1; inENd for right tree will end at inEnd itself.
		 * </pre>
		 */
		root.left = rec(pre, in, preStart + 1, (preStart + numsLeft), inStart, inorderIndex - 1, inMap);
		root.right = rec(pre, in, (preStart + numsLeft + 1), preEnd, inorderIndex + 1, inEnd, inMap);
		return root;
	}
}
