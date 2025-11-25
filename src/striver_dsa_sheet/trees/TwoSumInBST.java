package striver_dsa_sheet.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>
 * 
 * Given
 * Target = 9
 * 
 * find 2 nodes whose value is target, else return false;
 * 
 * 
 * In-order traversal gives sorted order, and store them into an array and then use 2 pointer approach to find the 2 elements from the array.
 * TC : O(N) + O(N)
 * SC : O(N)
 * 
 * 
 * Optimized way.
 * 
 * 
 * </pre>
 */
public class TwoSumInBST {
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

		int target = 8;
		System.out.println(findTarget(node10, target));
		System.out.println(findTargetOptimal(node10, target));

	}

	public static boolean findTargetOptimal(TreeNode node, int k) {
		BSTIterator left = new BSTIterator(node, false);
		BSTIterator right = new BSTIterator(node, true);

		int i = left.next();
		int j = right.next();
		while (i < j) {
			if (i + j == k)
				return true;
			else if (i + j < k)
				i = left.next();
			else
				j = right.next();
		}
		return false;
	}

//TC : O(N) + O(N)
//SC : O(N)	
	public static boolean findTarget(TreeNode root, int k) {
		List<Integer> list = new ArrayList();
		recInOrder(root, list);
		int arr[] = new int[list.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i);
		}
		int i = 0;
		int j = arr.length - 1;

		while (i < j) {
			if (arr[i] + arr[j] == k) {
				return true;
			} else if (arr[i] + arr[j] > k) {
				j--;
			} else {
				i++;
			}
		}
		return false;

	}

	public static void recInOrder(TreeNode node, List<Integer> list) {
		if (node == null) {
			return;
		}
		recInOrder(node.left, list);
		list.add(node.val);
		recInOrder(node.right, list);
	}
}
