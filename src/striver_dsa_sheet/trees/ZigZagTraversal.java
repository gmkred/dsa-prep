package striver_dsa_sheet.trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZagTraversal {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		// level traversal zig zag
		List<List<Integer>> ans = new ArrayList();
		if (root == null) {
			return ans;
		}
		List<Integer> ds = new ArrayList();
		// 0 for left to right
		// 1 for right to left
		int flag = 0;
		Queue<TreeNode> q = new LinkedList();
		q.offer(root);
		// for odd level do it reversely
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode temp = q.poll();
				ds.add(temp.val);
				if (temp.left != null) {
					q.offer(temp.left);
				}
				if (temp.right != null) {
					q.offer(temp.right);
				}
			}
			if (flag == 1) {
				Collections.reverse(ds);
				ans.add(new ArrayList(ds));
				flag = 0;
			} else {
				flag = 1;
				ans.add(new ArrayList(ds));
			}
			ds.clear();
		}
		return ans;
	}
}
