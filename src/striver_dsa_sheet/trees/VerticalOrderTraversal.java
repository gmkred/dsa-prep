package striver_dsa_sheet.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class VerticalOrderTraversal {

	public static List<List<Integer>> verticalTraversal(TreeNode root) {
		TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap();
		Queue<Tuple> q = new LinkedList();
		q.offer(new Tuple(root, 0, 0));
		while (!q.isEmpty()) {
			Tuple tu = q.poll();
			TreeNode temp = tu.node;
			int x = tu.x;
			int y = tu.y;
			if (!map.containsKey(x)) {
				map.put(x, new TreeMap());
			}
			if (!map.get(x).containsKey(y)) {
				map.get(x).put(y, new PriorityQueue<Integer>());
			}
			map.get(x).get(y).offer(temp.val);
			if (temp.left != null) {
				q.offer(new Tuple(temp.left, x - 1, y + 1));
			}
			if (temp.right != null) {
				q.offer(new Tuple(temp.right, x + 1, y + 1));
			}

		}
		List<List<Integer>> lists = new ArrayList();
		for (TreeMap<Integer, PriorityQueue<Integer>> c : map.values()) {
			List<Integer> l = new ArrayList();
			for (PriorityQueue<Integer> pq : c.values()) {
				while (!pq.isEmpty()) {
					l.add(pq.poll());
				}
			}
			lists.add(l);
		}
		return lists;
	}

	

	/**
	 * <pre>
	 * 
	 *             1
	 *         2             3
	 *      4      5      6      7
	 *                 8 
	 *                    9
	 *                 10  
	 *             11
	 * 
	 * If we draw vertical lines in the tree.
	 * 
	 * 4
	 * 2
	 * 1 5 11
	 * 8 10
	 * 6 9
	 * 3
	 * 7
	 * 
	 *      -2  -1    0    1   2  3    4   X AXIS
	 *    |   |   |   1 |    |  |   |    |   0th level
	 *    |   |  2|     |    |  |  3|    |   1st level 
	 *    |  4|   |   5 |    | 6|   |   7|   2nd level
	 *    |   |   |     |  8 |  |   |    |   3rd level
	 *    |   |   |     |    | 9|   |    |   4th level 
	 *    |   |   |     |  10|  |   |    |   5th level     
	 *    |   |   |   11|    |  |   |    |   6th level
	 * 
	 * 
	 * (0,0) = 1
	 * (-1,1) = 2
	 * (-2,2) = 4
	 * 
	 * Iterate ascending order on x axis for every level
	 * 
	 * We can use any type of traversal.
	 * </pre>
	 */
}
