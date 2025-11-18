package striver_dsa_sheet.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <pre>
 * 
 * Boundary traversal of a binary tree.
 * 
 *                       1
 *                 2           7
 *             3                   8
 *                 4           9
 *             5       6    10    11   
 *             
 * 1 2 3 4 5 6 10 11 9 8 7 1
 * 
 * Anti clock wise.
 * 
 * Firstly take roots Left boundary excluding leaf
 * LeafNodes
 * roots Right Boundary in the reverse excluding the leafs.
 * 
 * roots
 * left : 1 2 3 4
 * leafs :  5 6 10 11
 * right : 9 8 7 1
 * 
 * left + leafs + right
 * 
 * 1 2 3 4 5 6 7 10 11 9 8 7 1
 * </pre>
 * 
 */
public class BoundaryTraversal {

	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
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
		TreeNode node12 = new TreeNode(12);
		TreeNode node13 = new TreeNode(13);

		node1.left = node2;
		node2.left = node3;
		node3.right = node4;
		node4.left = node5;
		node4.right = node6;
		node1.right = node7;
		node7.right = node8;
		node8.left = node9;
		node9.left = node10;
		node9.right = node11;
		System.out.println(boundaryTraversalAnticlockwise(node1));
		System.out.println(boundaryTraversalclockwise(node1));
	}

	public static List<Integer> boundaryTraversalAnticlockwise(TreeNode node) {
		List<Integer> leftBoundary = new ArrayList();
		List<Integer> leaves = new ArrayList<>();
		List<Integer> rightBoundary = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		// left --> preorder iteration
		leftBoundary.add(node.val);
		q.offer(node.left);
		while (!q.isEmpty()) {
			TreeNode temp = q.poll();
			if (temp.left == null && temp.right == null) {
				leaves.add(temp.val);
			} else {
				leftBoundary.add(temp.val);
			}

			if (temp.left != null) {
				q.offer(temp.left);
			}
			if (temp.right != null) {
				q.offer(temp.right);
			}
		}

		q.offer(node.right);
		while (!q.isEmpty()) {
			TreeNode temp = q.poll();
			if (temp.left == null && temp.right == null) {
				leaves.add(temp.val);
			} else {
				rightBoundary.add(temp.val);
			}

			if (temp.left != null) {
				q.offer(temp.left);
			}
			if (temp.right != null) {
				q.offer(temp.right);
			}
		}
		List<Integer> ans = new ArrayList<>();
		ans.addAll(leftBoundary);
		ans.addAll(leaves);
		Collections.reverse(rightBoundary);
		ans.addAll(rightBoundary);
		return ans;
	}

	public static List<Integer> boundaryTraversalclockwise(TreeNode node) {
		List<Integer> leftBoundary = new ArrayList();
		List<Integer> leaves = new ArrayList<>();
		List<Integer> rightBoundary = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		// left --> preorder iteration
		q.offer(node.left);
		while (!q.isEmpty()) {
			TreeNode temp = q.poll();
			if (temp.left == null && temp.right == null) {
				leaves.add(temp.val);
			} else {
				leftBoundary.add(temp.val);
			}

			if (temp.left != null) {
				q.offer(temp.left);
			}
			if (temp.right != null) {
				q.offer(temp.right);
			}
		}
		rightBoundary.add(node.val);
		q.offer(node.right);
		while (!q.isEmpty()) {
			TreeNode temp = q.poll();
			if (temp.left == null && temp.right == null) {
				leaves.add(temp.val);
			} else {
				rightBoundary.add(temp.val);
			}

			if (temp.left != null) {
				q.offer(temp.left);
			}
			if (temp.right != null) {
				q.offer(temp.right);
			}
		}
		List<Integer> ans = new ArrayList<>();
		ans.addAll(rightBoundary);
		Collections.reverse(leaves);
		ans.addAll(leaves);
		Collections.reverse(leftBoundary);
		ans.addAll(leftBoundary);
		return ans;
	}
}
