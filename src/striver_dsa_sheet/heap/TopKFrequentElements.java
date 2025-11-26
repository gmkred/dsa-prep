package striver_dsa_sheet.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import striver_dsa_sheet.heap.TopKFrequentElements.Tuple;

public class TopKFrequentElements {
	public int[] topKFrequent(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap();
		// store the elements and its frequencies.
		for (int i = 0; i < nums.length; i++) {
			int val = nums[i];
			if (map.containsKey(val)) {
				map.put(val, map.get(val) + 1);
			} else {
				map.put(val, 1);
			}
		}
		Comparator<Tuple> c = (t1, t2) -> t1.count - t2.count;
		PriorityQueue<Tuple> minHeap = new PriorityQueue<Tuple>(c);
		map.forEach((key, v) -> {
			minHeap.offer(new Tuple(key, v));
			// Only store k Tuples in ascending order, so at the end only max frequency
			// only store the k tuples, if it crosses k, then remove top tuple as it is not
			// the max frequency among the existing tuples in the min heap, so we do not
			// need it.
			if (minHeap.size() > k) {
				minHeap.poll();
			}
		});
		int res[] = new int[k];
		int i = 0;
		while (!minHeap.isEmpty()) {
			res[i] = minHeap.poll().val;
			i++;
		}
		return res;
	}

	static class Tuple {
		int val;
		int count;

		public Tuple(int val, int count) {
			this.val = val;
			this.count = count;
		}
	}
}
