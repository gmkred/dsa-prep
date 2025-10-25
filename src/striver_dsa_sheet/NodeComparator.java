package striver_dsa_sheet;

import java.util.Comparator;

import striver_dsa_sheet.ddl.Node;

public class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {

		if (o1.val < o2.val) {
			return -1;
		} else if (o1.val > o2.val) {
			return 1;
		}
		return 0;
	}
}
