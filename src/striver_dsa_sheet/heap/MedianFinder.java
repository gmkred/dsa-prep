package striver_dsa_sheet.heap;

import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {
	public static void main(String[] args) {
		MedianFinder mf = new MedianFinder();
		mf.addNum(6);
		System.out.println(mf.findMedian());
		mf.addNum(10);
		System.out.println(mf.findMedian());
		mf.addNum(2);
		System.out.println(mf.findMedian());
		mf.addNum(6);
		System.out.println(mf.findMedian());
		mf.addNum(5);
		System.out.println(mf.findMedian());
		mf.addNum(0);
		System.out.println(mf.findMedian());
		mf.addNum(6);
		System.out.println(mf.findMedian());
		mf.addNum(3);
		System.out.println(mf.findMedian());
		mf.addNum(1);
		System.out.println(mf.findMedian());
		mf.addNum(0);
		System.out.println(mf.findMedian());
		mf.addNum(0);
		System.out.println(mf.findMedian());
	}

	// store smaller values in reverse order so the top will be max value of first
	// half
	PriorityQueue<Integer> maxHeap = null;
	// store larger values. the top will be the second half min value
	PriorityQueue<Integer> minHeap = null;
	// if size is odd, take the value from the heap that size is greater than other,
	// if size is even take the avg of to heaps top values.

	public MedianFinder() {
		maxHeap = new PriorityQueue(Collections.reverseOrder());
		minHeap = new PriorityQueue();
	}

	public void addNum(int num) {
		// first add to min heap
		maxHeap.offer(num);
		// then move the largest value of max to min heap
		minHeap.offer(maxHeap.poll());
		// in case heaps are not balanced, balance them. This way we can always have the
		// middle values on top.
		if (minHeap.size() > maxHeap.size()) {
			maxHeap.offer(minHeap.poll());
		}
	}

	public double findMedian() {

		if (minHeap.size() == maxHeap.size()) {
			// if size is same then return the avg of min and max tops
			return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2;
		} else {
			// other wise return maxHeap top element as the max heap contains the middle
			// value.
			return maxHeap.peek();
		}
	}
}