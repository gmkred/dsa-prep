package striver_dsa_sheet.trees;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeANdDeserializeTree {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		Queue<TreeNode> q = new LinkedList();
		StringBuilder sb = new StringBuilder();
		q.offer(root);
		// level oreder traversal or Breadth First Traversal
		while (!q.isEmpty()) {
			TreeNode temp = q.poll();
			// if a node is null store it as 'n'.
			// it is needed to deseralize the String to Tree
			if (temp == null) {
				sb.append("n ");
				continue;
			}
			sb.append(temp.val + " ");
			q.offer(temp.left);
			q.offer(temp.right);
		}
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		String[] values = data.trim().split(" ");
		Queue<TreeNode> q = new LinkedList();
		if (values[0].equals("n")) {
			return null;
		}
		TreeNode root = new TreeNode(Integer.parseInt(values[0]));
		q.offer(root);
		for (int i = 1; i < values.length; i++) {
			TreeNode temp = q.poll();
			// if 'n', leave it and not 'n' append to the current node.
			if (!values[i].equals("n")) {
				temp.left = new TreeNode(Integer.parseInt(values[i]));
				q.offer(temp.left);
			}
			if (!values[++i].equals("n")) {
				temp.right = new TreeNode(Integer.parseInt(values[i]));
				q.offer(temp.right);
			}
		}
		return root;
	}
}
