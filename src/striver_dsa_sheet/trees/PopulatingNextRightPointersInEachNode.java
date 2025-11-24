package striver_dsa_sheet.trees;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersInEachNode {

	public static Node connectUsingDepthFirstSearchAlgo(Node root) {
		return root;
	}

	public static Node connect(Node root) {
		if (root == null) {
			return root;
		}
		Queue<Node> q = new LinkedList();
		q.offer(root);
		while (!q.isEmpty()) {
			int levelSize = q.size();
			Node prev = q.poll();
			if (prev.left != null) {
				q.offer(prev.left);
			}
			if (prev.right != null) {
				q.offer(prev.right);
			}
			for (int i = 1; i < levelSize; i++) {
				Node cur = q.poll();
				if (prev != null) {
					prev.next = cur;
				}
				if (cur.left != null) {
					q.offer(cur.left);
				}
				if (cur.right != null) {
					q.offer(cur.right);
				}
				prev = cur;
			}
		}
		return root;
	}

	static class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;

		public Node() {
		}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, Node _left, Node _right, Node _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}
	};
}
