package striver_dsa_sheet.recursions;

public class FactorialOfN {
	public static void main(String[] args) {
		System.out.println(factorial(4));
		System.out.println(parameterized(4, 1));
	}

	private static int parameterized(int N, int factorial) {
		if (N == 1) {
			return factorial;
		}
		return parameterized(N - 1, factorial * N);
		/**
		 * <pre>
		 * 
		 * f(3, 1*4)
		 *          f(2, 1*4*3)
		 *                    f(1, 1*4*3*2)
		 *                    return factorial (1*4*3*2) = 24
		 *           24
		 *  24
		 * </pre>
		 */

	}

	private static int factorial(int N) {

		if (N == 1) {
			return 1;
		}
		return factorial(N - 1) * N;
		/**
		 * <pre>
		 * 
		 * f(3) * 4
		 * f(2) * 3 * 4
		 * f(1) * 2 * 3 * 4
		 * f(1) starts returning
		 * 
		 * 1 * 2 * 3 * 4 = 24
		 * 
		 * </pre>
		 */
	}
}
