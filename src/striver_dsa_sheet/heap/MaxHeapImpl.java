package striver_dsa_sheet.heap;

import java.util.Arrays;

/**
 * MaxHeapImpl
 *
 * A heap is an array representation of a complete binary tree. In a Max-Heap
 * every parent node is >= its children.
 *
 * Why use a heap instead of maintaining a fully sorted array? - Sorted array:
 * insert : O(n) (must shift elements) delete : O(n) (must shift elements) get
 * max : O(1) - Max-Heap: insert : O(log n) extract : O(log n) get max : O(1)
 *
 * Index relationships (0-based array): parent(i) = (i - 1) / 2 left(i) = 2*i +
 * 1 right(i) = 2*i + 2
 *
 * Insertion: place new element at end, then shiftUp (sift-up) while it is
 * larger than parent. Extraction: replace root with last element, reduce size,
 * then shiftDown (sift-down) swapping with the larger child until heap property
 * restored.
 */
public class MaxHeapImpl {
	private int[] heap;
	private int size;
	private static final int DEFAULT_CAPACITY = 16;

	public MaxHeapImpl() {
		this.heap = new int[DEFAULT_CAPACITY];
		this.size = 0;
	}

	// Optional constructor to start from an existing array (build-heap O(n))
	public MaxHeapImpl(int[] arr) {
		this.heap = Arrays.copyOf(arr, Math.max(DEFAULT_CAPACITY, arr.length));
		this.size = arr.length;
		buildHeap();
	}

	private void buildHeap() {
		// Heapify in O(n): siftDown from last non-leaf down to root
		for (int i = parent(size - 1); i >= 0; i--) {
			shiftDown(i);
		}
	}

	public void initializeHeap() {
		heap = new int[DEFAULT_CAPACITY];
		size = 0;
	}

	public void insert(int key) {
		if (heap == null)
			initializeHeap();
		if (size == heap.length) {
			heap = Arrays.copyOf(heap, heap.length * 2);
		}
		heap[size] = key;
		shiftUp(size);
		size++;
	}

	/**
	 * changeKey: change a value at index to newVal and restore heap property. index
	 * must be 0-based and in range [0, size-1].
	 */
	public void changeKey(int index, int newVal) {
		checkIndex(index);
		int old = heap[index];
		heap[index] = newVal;
		if (newVal > old) {
			shiftUp(index);
		} else if (newVal < old) {
			shiftDown(index);
		}
		// if equal, nothing to do
	}

	/**
	 * extractMax: remove the maximum element (root). Note: signature is void to
	 * match original; if you want the value returned, change to `public int
	 * extractMax()`.
	 */
	public void extractMax() {
		if (isEmpty()) {
			throw new IllegalStateException("Heap is empty");
		}
		// Move last element to root, reduce size, then sift-down to restore heap
		heap[0] = heap[size - 1];
		heap[size - 1] = 0; // optional: clear last slot
		size--;
		if (size > 0) {
			shiftDown(0);
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getMax() {
		if (isEmpty()) {
			throw new IllegalStateException("Heap is empty");
		}
		return heap[0];
	}

	public int heapSize() {
		return size;
	}

	// ----------------- Helper methods -----------------

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return 2 * i + 2;
	}

	private void shiftUp(int index) {
		int cur = index;
		while (cur > 0) {
			int p = parent(cur);
			if (heap[cur] > heap[p]) {
				swap(cur, p);
				cur = p;
			} else {
				break;
			}
		}
	}

	private void shiftDown(int index) {
		int cur = index;
		while (true) {
			int l = left(cur);
			int r = right(cur);
			int largest = cur;

			if (l < size && heap[l] > heap[largest]) {
				largest = l;
			}
			if (r < size && heap[r] > heap[largest]) {
				largest = r;
			}

			if (largest == cur) {
				break;
			} else {
				swap(cur, largest);
				cur = largest;
			}
		}
	}

	private void swap(int i, int j) {
		int tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
	}

	private void checkIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of range: " + index);
		}
	}

	// ----------------- Demo / quick test -----------------
	public static void main(String[] args) {
		MaxHeapImpl impl = new MaxHeapImpl();
		impl.insert(50);
		impl.insert(30);
		impl.insert(40);
		impl.insert(10);
		impl.insert(20);
		impl.insert(45);

		System.out.println("Heap array (partial): " + Arrays.toString(Arrays.copyOf(impl.heap, impl.heapSize())));
		System.out.println("Max = " + impl.getMax());

		impl.extractMax();
		System.out.println("After extractMax: " + Arrays.toString(Arrays.copyOf(impl.heap, impl.heapSize())));
		System.out.println("Max now = " + impl.getMax());

		// change key example
		impl.changeKey(1, 100); // make index 1 very large
		System.out.println("After changeKey(1,100): " + Arrays.toString(Arrays.copyOf(impl.heap, impl.heapSize())));
		System.out.println("Max now = " + impl.getMax());
	}
}
