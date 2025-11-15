package striver_dsa_sheet.trees;

public class Pair {
	public TreeNode key;
	public int value;

	public TreeNode getKey() {
		return key;
	}

	public void setKey(TreeNode key) {
		this.key = key;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Pair(TreeNode key, int value) {
		this.key = key;
		this.value = value;
	}

	public Pair() {
	}

}
