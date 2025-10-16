package arrays;

public class InvertedKadanesAlgo {

	public static void main(String[] args) {
		int arr[] = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		int result = invertedKadane(arr);
		System.out.println(result);
	}

	/**
	 * same as Kadane's algo, but for minimum subarray sum.
	 * */
	public static int invertedKadane(int arr[]) {
		int curMin = arr[0];
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			curMin = curMin + arr[i];
			curMin = Math.min(curMin, arr[i]);
			min = Math.min(curMin, min);
		}
		return min;
	}
}
