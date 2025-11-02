package striver_dsa_sheet.recursions;

public class PrintFromNto1 {

	public static void main(String[] args) {
		printNumbers(5);
	}

	private static void printNumbers(int N) {
		if (N < 1) {
			return;
		}
		System.out.println(N);
		printNumbers(N - 1);
	}
}
