package striver_dsa_sheet.trees;

public class TreeNode {

	int val;
	TreeNode left;
	TreeNode right;

	public TreeNode() {
	}

	public TreeNode(int val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";
	}

}
