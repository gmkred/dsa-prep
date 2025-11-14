package striver_dsa_sheet.bitManipulation;

public class SetithBit {
	public static void main(String[] args) {
		int N = 9;
		int i = 2;
		/**
		 * <pre>
		 * Left shift
		 * 1001 = 9
		 * 1 << 2
		 * 0100
		 * ----OR
		 * 1101
		 * 
		 * </pre>
		 */
		System.out.println((N | (1 << i)));
	}
}
