package striver_dsa_sheet.bitManipulation;

public class CheckIfNumberIsPowerOf2 {
	public static void main(String[] args) {
		int N = 16;
		/**
		 * <pre>
		 * 
		 * 10000 = 16
		 * 
		 * every power of 2 can have one set bit.
		 * 
		 * N & N-1
		 * 10000 =16
		 * 01111 =15
		 * -----AND
		 * 00000 = 0 then its power of 2
		 * 
		 * N = 19
		 * 
		 * 10011 = 19
		 * 01111 = 18
		 * ------AND
		 * 10011 = > 0 so not power of 2
		 * 
		 * </pre>
		 */
		System.out.println((N & (N - 1)) == 0);
	}
}
