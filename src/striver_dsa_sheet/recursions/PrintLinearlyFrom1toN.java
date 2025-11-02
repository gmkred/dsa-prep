package striver_dsa_sheet.recursions;

public class PrintLinearlyFrom1toN {
	public static void main(String[] args) {
		printNumbers(1, 5);
	}

	private static void printNumbers(int start, int N) {
		if (start > N) {
			return;
		}
		System.out.println(start);
		printNumbers(start + 1, N);

	}
}
