package striver_dsa_sheet.bitManipulation;

public class Swap2Numbers {
	public static void main(String[] args) {
		int a = 5;
		int b = 6;
		// without using the 3rd variable
		a = a ^ b;
		/**
		 * <pre>
		 * 0101 = 5
		 * 0110 = 6
		 * -----
		 * 0011 = 3
		 * </pre>
		 */
		b = a ^ b;
		/**
		 * <pre>
		 * 0011 = 3
		 * 0110 = 6
		 * -----
		 * 0101 = 5
		 * </pre>
		 */

		a = a ^ b;
		/**
		 * <pre>
		 * 0011 = 3
		 * 0101 = 5
		 * -----
		 * 0110 = 6
		 * </pre>
		 */
		System.out.println("a" + a + " b" + b);
	}
}
