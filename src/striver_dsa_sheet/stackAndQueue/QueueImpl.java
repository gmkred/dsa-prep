package striver_dsa_sheet.stackAndQueue;

public class QueueImpl {

	int start = -1, end = -1, size = 4, queueSize = 0;

	int queue[] = new int[size];

	private void push(int x) {
		if (queueSize == size) {
			System.out.println("cannot insert");
			return;
		}
		if (queueSize == 0) {
			start = 0;
			end = 0;
		} else {
			end = (end + 1) % size;

		}
		queue[end] = x;
		queueSize++;
	}

	private void display() {
		for (int i : queue) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	private int top() {

		return queue[start];

	}

	private int pop() throws Exception {
		if (queueSize == 0) {
			throw new Exception("No elements in queue");
		}
		int i = queue[start];
		if (queueSize == 1) {
			start = end = -1;
		} else {
			// using modulus will not overflow the index value
			start = (start + 1) % size;
		}
		queueSize--;
		return i;

	}

	private int size() {
		return queueSize;
	}

	public static void main(String[] args) throws Exception {
		QueueImpl queue = new QueueImpl();
		queue.display();
		queue.push(1);
		queue.display();
		queue.push(2);
		queue.display();
		queue.push(4);
		queue.display();
		queue.push(3);
		queue.display();
		System.out.println(queue.pop());
		queue.display();
		System.out.println(queue.top());
		queue.display();
		System.out.println(queue.top());
		queue.display();
		System.out.println(queue.pop());
		queue.display();
		queue.push(6);
		queue.display();
		System.out.println(queue.top());
		queue.display();
		System.out.println(queue.size());
		queue.display();
	}

}
