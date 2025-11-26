package striver_dsa_sheet.trees;

/**
 * <pre>
 * preOrder = {8, 5, 1, 7, 10, 12};
 * 
 *                          8
 *                   5             10
 *                1      7               12
 *                
 *                
 * Naive solution : insert all the numbers greater than 8 on right side and all of the numbers less than left side.
 * 
 * TC : O(N^2)
 * SC : O(1)
 * Better solution : 
 * get the in-order traversal : which is actaully a BST as Inorder will be sorted.
 * inorder = {1,5,7,8,10,12}
 * now we have in order and pre order, we can built the BST with this.
 * 
 * TC : O(N log N) + O(N) sorting
 * SC : O(N) to store in Map
 * 
 * Optimal solution :
 * check if BT is BST. instead of carrying upper and lower bound, only carry upper bound.
 * 
 * 
 * TC : O(3N) at worst case which is as good as O(N)
 * SC : O(1)
 * </pre>
 */
public class ConstructBSTfromPreOrderTraversal {
	public static void main(String[] args) {
		int preOrder[] = { 8, 5, 1, 7, 10, 12 };
		int singleIndex[] = { 0 };
		int bound = Integer.MAX_VALUE;
		System.out.println(constructBSTFromPreOrder(preOrder, bound, singleIndex));
	}

	public static TreeNode constructBSTFromPreOrder(int[] preOrder, int bound, int singleIndex[]) {
		// if the index goes out of bound return null or if the value present in the
		// current index is greater than the bound return null, because it should be
		// right side of the node.
		if (singleIndex[0] == preOrder.length || preOrder[singleIndex[0]] > bound) {
			return null;
		}
		TreeNode node = new TreeNode(preOrder[singleIndex[0]++]);
		// for left side tree, the upper bound will be the current node, because it is
		// always greater than its left child nodes.
		node.left = constructBSTFromPreOrder(preOrder, node.val, singleIndex);
		// for right side tree, the upper bound will be the upper bound of current node
		// because the value of right side should be the lesser than the upper bound of
		// the current node.
		// for any given node the left side of tree will be lesser than its val, here
		// the upper bound acts at the given node.
		node.right = constructBSTFromPreOrder(preOrder, bound, singleIndex);
		return node;
	}

}
