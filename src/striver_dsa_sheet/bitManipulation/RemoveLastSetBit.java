package striver_dsa_sheet.bitManipulation;

public class RemoveLastSetBit {
	public static void main(String[] args) {
		int N = 12;
		/**
		 * <pre>
		 * 1100 = 12 
		 * 
		 * remove last set bit
		 * 
		 * 1000 = 8
		 * 
		 * examples
		 *  
		 * 10000 = 16
		 * 01111 = 15
		 * 16 = 2^4
		 * 15 = 8+4+2+1
		 * 
		 * 101000 = 40
		 * 100111 = 39
		 * 
		 * 40 = 32 + 8 = 2^5 + 2^3
		 * 39 = 32 + 4+2+1
		 * 
		 * 1010100 =84
		 * 1010011 =83
		 * 
		 * to remove last bit
		 * 
		 * N & N-1
		 * 
		 * 101000
		 * 100111
		 * ------AND
		 * 100000
		 * 
		 * 1010100
		 * 1010011
		 * -------AND
		 * 1010000
		 * 
		 * </pre>
		 */

		System.out.println(N & (N - 1));
	}
}
