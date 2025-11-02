package striver_dsa_sheet.recursions;

public class PrintFromNto1BackTrack {

	public static void main(String[] args) {
		printNumbers(1, 5);
	}

	private static void printNumbers(int start, int N) {
		if (start > N) {
			return;
		}

		printNumbers(start + 1, N);
		System.out.println(start);
	}
}
