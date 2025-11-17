package striver_dsa_sheet.trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class TopAndBottomView {
	public static void main(String[] args) {
		TreeNode node = TreeTraversal.get();
		System.out.println(bottomView(node));
		System.out.println(topView(node));
	}

	public static List<Integer> bottomView(TreeNode node) {
		/**
		 * <pre>
		 * 
		 * we know if we divide tree vertically, it will look
		 * somthing like
		 * -2 -1 0 1 2 columns
		 * So we need to take the latest value of each column to get bottom view
		 * 
		 * </pre>
		 * 
		 */
		// column,value
		Map<Integer, Integer> map = new HashMap<>();
		Queue<Tuple> q = new LinkedList<>();
		q.offer(new Tuple(node, 0, 0));
		while (!q.isEmpty()) {
			Tuple tu = q.poll();
			TreeNode temp = tu.node;
			// x axis -2 -1 0 1 2
			int x = tu.x;
			// levels
			int y = tu.y;
			map.put(x, temp.val);

			if (temp.left != null) {
				q.offer(new Tuple(temp.left, x - 1, y + 1));
			}
			if (temp.right != null) {
				q.offer(new Tuple(temp.right, x + 1, y + 1));
			}
		}

		return map.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
	}

	public static List<Integer> topView(TreeNode node) {
		/**
		 * <pre>
		 * 
		 * we know if we divide tree vertically, it will look
		 * somthing like
		 * -2 -1 0 1 2 columns
		 * So we need to take the latest value of each column to get bottom view
		 * 
		 * </pre>
		 * 
		 */
		// column,value
		Map<Integer, Integer> map = new HashMap<>();
		Queue<Tuple> q = new LinkedList<>();
		q.offer(new Tuple(node, 0, 0));
		while (!q.isEmpty()) {
			Tuple tu = q.poll();
			TreeNode temp = tu.node;
			// x axis -2 -1 0 1 2
			int x = tu.x;
			// levels
			int y = tu.y;
			if (!map.containsKey(x)) {
				map.put(x, temp.val);
			}
			if (temp.left != null) {
				q.offer(new Tuple(temp.left, x - 1, y + 1));
			}
			if (temp.right != null) {
				q.offer(new Tuple(temp.right, x + 1, y + 1));
			}
		}

		return map.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
	}
}