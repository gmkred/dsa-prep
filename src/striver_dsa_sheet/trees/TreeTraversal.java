package striver_dsa_sheet.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <pre>
 * Depth First Search:
 *              1
 *          2       3
 *        4   5   6   7   
 * 
 * 
 * 1.In order Traversal (left Root right) 
 * 
 * Go to the extream left and apply logic
 * and then goo to the extreme right and apply the logic.
 * 
 * 4 2 5 1 6 3 7 
 * 
 * 2. Pre order Traversal (Root Left Right)
 * 
 * 1 2 4 5 3 6 7
 * 
 * 
 * 3. Post order traversal (left right root)
 * 
 * 4 5 2 6 7 3 1
 * 
 * Breath First Search : 
 * Goes level wise.
 * 1 2 3 4 5 6 7
 * 
 * 
 * </pre>
 */
public class TreeTraversal {
	public static void main(String[] args) {
		TreeNode node = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);

		node.left = node2;
		node.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		preOrderTravarsal(node);
		System.out.println();
		inOrderTraversal(node);
		System.out.println();
		postOrderTraversal(node);
		System.out.println();
		System.out.println(breathOrLevelFirstSearch(node));

	}

	public static TreeNode get() {
		TreeNode node = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		TreeNode node6 = new TreeNode(6);
		TreeNode node7 = new TreeNode(7);

		node.left = node2;
		node.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		return node;
	}

//TC : O(N) SC : O(N) auxiliary space
	public static void preOrderTravarsal(TreeNode node) {

		if (node == null) {
			return;
		}
		System.out.print(node.val + " ");
		// first goes left end and to right.
		preOrderTravarsal(node.left);
		preOrderTravarsal(node.right);
	}

	public static void inOrderTraversal(TreeNode node) {

		if (node == null) {
			return;
		}
		inOrderTraversal(node.left);
		System.out.print(node.val + " ");
		inOrderTraversal(node.right);

	}

	public static void postOrderTraversal(TreeNode node) {

		if (node == null) {
			return;
		}
		postOrderTraversal(node.left);
		postOrderTraversal(node.right);
		System.out.print(node.val + " ");

	}

	/**
	 * <pre>
	 * 
	 * For this type of traversal we need queue ds
	 * initially having the root
	 * 
	 * Take List<List<>> ds
	 * 
	 * Q = Root{1, left, right}
	 * 
	 * First take the root from queue and put the value in list
	 * list {1} 
	 * Now if left exists put it into queue and also check if right exists then put that into queue
	 * 
	 * Q = {2,3}
	 *
	 * Since there is only 1 in first level put that list in list<list>,
	 * 
	 * Next iteration, what ever is lined up in the queue, just take them.
	 * 
	 * list {2,3} and store left and right of 2 and 3
	 * 
	 * add the new level list to lists
	 * Q = {4,5,6,7}
	 * 
	 * Next iteration
	 * list {4,5,6,7} and there is no left and right to each eleement so it ends here.
	 * 
	 * Q = {}
	 * 
	 * 
	 * TC : O(N)
	 * SC : O(N)
	 * </pre>
	 */
	public static List<List<Integer>> breathOrLevelFirstSearch(TreeNode node) {
		List<List<Integer>> levels = new ArrayList<>();
		List<Integer> level = new ArrayList();

		Queue<TreeNode> queue = new LinkedList();
		queue.offer(node);
		while (!queue.isEmpty()) {
			// Maintaining the size is important because queue is keep on updating.
			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {
				if (queue.peek().left != null) {
					queue.offer(queue.peek().left);
				}
				if (queue.peek().right != null) {
					queue.offer(queue.peek().right);
				}
				level.add(queue.poll().val);
			}
			levels.add(new ArrayList(level));
			level.clear();
		}
		return levels;
	}
}
