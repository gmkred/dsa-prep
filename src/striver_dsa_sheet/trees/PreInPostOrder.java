package striver_dsa_sheet.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * <pre>
 * 
 * Pre, in and post order in single traversal
 * Use stack
 * 
 * <Node, num>
 * initially push root node
 * 
 * <rootNode ,1>
 * 
 * RUELS : 
 * 1. If a node is taken from stack that num==1 go to pre order.
 * 	After that push that number using num++
 * 	if there exist a left push it to stack
 * 2. If a node is taken from stack with num==2, in order increment num++F 
 * 	and push right value if there exist a right.
 * 
 * 3. If a node is taken from stack with num==3, post order.
 * 
 * 
 * Preorde = num ==1 increment the num only if there is left
 * inorder = num ==2 increment the num only if there is right.
 * postorder num ==3 no increment required, because a node at max is visited 3 times.
 * 
 * Why it works.
 *                  1
 *            2           3   
 *        4       5 
 *             6     7
 * Pre : when we first visit a node, we just take it and increase the value of it so next time when coming back it will be 2 and also push left with value 1.
 * In : the nodes that are having 2, this will happen when we are visiting a node that has value ==2, take it and increment it and take right into stack wit hvalue 1.
 * post : This will start taking when coming back from the null by then the nodes are marked as 3 so it will pick.
 * </pre>
 */
public class PreInPostOrder {
	public static void main(String[] args) {
		TreeNode node = TreeTraversal.get();
		System.out.println(allOrdersInSingleTraversal(node));
	}

	public static List<List<Integer>> allOrdersInSingleTraversal(TreeNode node) {
		//
		List<Integer> pre = new ArrayList<>();
		List<Integer> in = new ArrayList<>();
		List<Integer> post = new ArrayList<>();
		List<List<Integer>> lists = new ArrayList<>();
		Stack<Pair> stack = new Stack<>();
		stack.push(new Pair(node, 1));
		while (!stack.isEmpty()) {
			Pair pair = stack.pop();
			if (pair.value == 1) {
				pre.add(pair.key.val);
				pair.value++;
				stack.push(pair);
				if (pair.key.left != null) {
					stack.push(new Pair(pair.key.left, 1));
				}
			} else if (pair.value == 2) {
				in.add(pair.key.val);
				pair.value++;
				stack.push(pair);
				if (pair.key.right != null) {
					stack.push(new Pair(pair.key.right, 1));
				}
			} else if (pair.value == 3) {
				post.add(pair.key.val);
			}

		}
		lists.add(pre);
		lists.add(in);
		lists.add(post);
		return lists;

	}
}
