package striver_dsa_sheet.ddl;

public class DesignBrowserHistory {
	Node head;
	Node currentUrl;

	public DesignBrowserHistory(String homepage) {
		head = new Node(homepage);
		currentUrl = head;
	}

	public void visit(String url) {
		currentUrl.next = null;
		Node newUrl = new Node(url);
		currentUrl.next = newUrl;
		newUrl.prev = currentUrl;
		currentUrl = newUrl;
	}

	public String back(int steps) {
		while (currentUrl.prev != null && steps > 0) {
			currentUrl = currentUrl.prev;
			steps--;
		}
		return currentUrl.url;
	}

	public String forward(int steps) {
		while (currentUrl.next != null && steps > 0) {
			currentUrl = currentUrl.next;
			steps--;
		}
		return currentUrl.url;
	}

	class Node {
		String url;
		Node next;
		Node prev;

		public Node(String url) {
			this.url = url;
		}
	}
}
