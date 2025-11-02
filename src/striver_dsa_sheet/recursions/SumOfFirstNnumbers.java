package striver_dsa_sheet.recursions;

public class SumOfFirstNnumbers {

	public static void main(String[] args) {
		System.out.println(parameterizedWay(5, 0));
		System.out.println(functional(5));
	}

	private static int parameterizedWay(int N, int sum) {
		if (N < 1) {
			return sum;
		}
		sum = parameterizedWay(N - 1, sum + N);

		return sum;

	}

	private static int functional(int N) {

		if (N < 1) {
			return 0;
		}
		return N + functional(N - 1);
		/**
		 * <pre>
		 * 5 + f(4) waits for f(4)
		 * 5 + 4 + f(3) waits for f(3)
		 * 5 + 4 + 3 + f(2) waits for f(2)
		 * 5 + 4 + 3 + 2 + f(1) waits for f(1)
		 * 5 + 4 + 3 + 2 + 1 + f(0) waits for f(0)
		 * f(0) returns 0, now rest of all the recursive calls will be executed.
		 * so addition will be 15
		 * 
		 * </pre>
		 */
	}
}
