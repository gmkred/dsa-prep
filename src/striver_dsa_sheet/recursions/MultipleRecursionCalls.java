package striver_dsa_sheet.recursions;

public class MultipleRecursionCalls {

	public static void main(String[] args) {
		System.out.println(fibonacciSum(5));
	}

	/**
	 * <pre>
	 * 
	 * Fibonacci number is a number whihc is sum of previous 2 numbers
	 * 
	 * it starts from 0, 1
	 * fibonacci of 6
	 * 0+1 = 1
	 * 1+1 = 2
	 * 1+2 = 3
	 * 2+3 = 5
	 * 3+5 = 8
	 * 5+8 = 13
	 * 
	 * so for any given N fibonacci will be (N-1) + (N-2)
	 * 
	 * the recursive goest till it find 0, 1 which are the 
	 * starting 2 numbers of fibonacci, from there it keeps on adding the 
	 * value in the stack.
	 * 
	 * TC : ~O(2^N)
	 * because each calls twice, so its nearly exponential to 2^N
	 * </pre>
	 */
	private static int fibonacciSum(int N) {
		if (N <= 1) {
			// N either can be 0 or 1
			return N;
		}

		int last = fibonacciSum(N - 1);
		int secLast = fibonacciSum(N - 2);
		return last + secLast;

	}
}
