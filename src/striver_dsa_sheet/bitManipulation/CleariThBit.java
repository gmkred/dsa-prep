package striver_dsa_sheet.bitManipulation;

public class CleariThBit {
	public static void main(String[] args) {
		int N = 13;
		int i = 2;
		// turn 1 or 0 to 0
		/**
		 * <pre>
		 * 
		 * 1101 = 13
		 * 1 << 2
		 * 0100
		 * -----XOR
		 * 1001 = 9
		 * 
		 * 
		 * or
		 * 
		 * 0000000.....1101
		 * ~(1 << 2)
		 * 1111111 ....1011
		 * -----------------AND
		 * 0000000000001001 = 9
		 * </pre>
		 */
		System.out.println(N & ~(1 << i));
		System.out.println(N ^ (1 << i));
	}
}
