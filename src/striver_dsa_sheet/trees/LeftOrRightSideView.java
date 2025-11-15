package striver_dsa_sheet.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href=
 * "https://leetcode.com/problems/find-bottom-left-tree-value/submissions/1830369443/">leed
 * code</a>
 */
public class LeftOrRightSideView {
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
		List<Integer> ds = new ArrayList();
		rightView(node, 0, ds);
		System.out.println(ds);
		ds.clear();
		leftView(node, 0, ds);
		System.out.println(ds);
	}

	/**
	 * <pre>
	 * 
	 *             1
	 *         2             3
	 *     4      5       6      7
	 *                  8 
	 *                     9
	 *                  10  
	 *               11
	 *  Right view = 1 3 7 8 9 10 11
	 *  Left view =  1 2 4 8 9 10 11
	 *  
	 *  we can use Level order traversal 
	 *  but TC : O(n) and SC : O(n)
	 *  
	 *  Recursive traversal
	 *  
	 *  pre order : Root Left Right
	 *  reverse Pre order
	 *  Root Right Left
	 *  
	 *  we need node and level
	 *  
	 *  maintain a DS to store value on each level when recursion enters into that level
	 *  since we are going right first and left second.
	 *  If right value is present for that level it is taken and if not present it take left which will be visisble from right view.
	 * 
	 * </pre>
	 */
	public static void rightView(TreeNode node, int level, List<Integer> ds) {

		if (node == null) {
			return;
		}
		if (ds.size() == level) {
			ds.add(node.val);
		}

		rightView(node.right, level + 1, ds);
		rightView(node.left, level + 1, ds);
	}

	public static void leftView(TreeNode node, int level, List<Integer> ds) {

		if (node == null) {
			return;
		}
		if (ds.size() == level) {
			ds.add(node.val);
		}

		rightView(node.left, level + 1, ds);
		rightView(node.right, level + 1, ds);
	}
}
