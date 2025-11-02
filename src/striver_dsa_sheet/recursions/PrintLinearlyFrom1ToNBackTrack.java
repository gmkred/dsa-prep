package striver_dsa_sheet.recursions;

public class PrintLinearlyFrom1ToNBackTrack {
	public static void main(String[] args) {
		printNumbers(5);
	}

	private static void printNumbers(int N) {
		if (N < 1) {
			return;
		}
		printNumbers(N - 1);
		System.out.println(N);
	}
}
