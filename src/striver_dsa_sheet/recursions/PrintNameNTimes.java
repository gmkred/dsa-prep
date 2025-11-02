package striver_dsa_sheet.recursions;

public class PrintNameNTimes {
	public static void main(String[] args) {
		printName("Malli", 5);
	}

	/**
	 * <pre>
	 * 
	 * TC : O(N)
	 * SC : O(N) for stack space
	 * 
	 * </prE>
	 */
	private static void printName(String name, int N) {

		if (N < 1) {
			return;
		}
		printName(name, N - 1);
		System.out.println(name);
	}
}
