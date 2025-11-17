package striver_dsa_sheet.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 
 * Node 7 
 * 
 * from root to 7 we need to find the path.
 * 
 *               1
 *           2         3
 *      4        5 
 *           6       7
 *           
 *           
 *  Use in order which is best in this case.
 *  
 *  Go extreme left, if 7 not found them return false, and go to right
 *  do it until 7 is found, once found return true.
 * 
 * </pre>
 */
public class RootNodeOrLeafPath {
	public static void main(String[] args) {
		TreeNode node = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);
		TreeNode node8 = new TreeNode(8);
		TreeNode node9 = new TreeNode(9);
		TreeNode node10 = new TreeNode(10);
		TreeNode node11 = new TreeNode(11);

		node.left = node2;
		node.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		node7.left = node8;
		node8.right = node9;
		node9.left = node10;
		node10.left = node11;
		List<Integer> list = new ArrayList<>();
		rootToN(node, 11, list);
		System.out.println(list);
	}

	public static boolean rootToN(TreeNode node, int n, List<Integer> list) {
		if (node == null) {
			return false;
		}
		list.add(node.val);
		if (node.val == n) {
			return true;
		}
		boolean l = rootToN(node.left, n, list);

		boolean r = rootToN(node.right, n, list);
		// if n is found on left or right return true
		if (l || r) {
			return true;
		}
		// else remove this node value which is not in the path to n
		list.remove(list.size() - 1);
		return false;

	}
}
