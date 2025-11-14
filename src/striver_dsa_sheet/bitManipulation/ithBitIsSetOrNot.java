package striver_dsa_sheet.bitManipulation;

public class ithBitIsSetOrNot {

	public static void main(String[] args) {

		int N = 13;
		int M = 14;
		int i = 2;
		// is the 2nd bit set i.e. 1
		// brute force - convert to binary and check
		// left shift operator
		/**
		 * <pre>
		 * i = 2
		 * using 1 << i
		 * 
		 * 1 << 0 means
		 * 0001
		 * 
		 * 1 << 1 means
		 * 0010
		 * 
		 * 1 << 2 means
		 * 0100
		 * 
		 * 1101 = 13
		 * 0100 = 1 << 2
		 * ----- AND
		 * 0100
		 * if value > 0 then set or not set
		 * </pre>
		 */

		System.out.println((N & (1 << i)) != 0);
		System.out.println((M & (1 << i)) != 0);

		/**
		 * <pre>
		 * Use right shift
		 * 
		 * take the ith bit to extreme right
		 * 
		 * N = 13
		 * 1101 >> i
		 * 1101 >> 2
		 * 
		 * 0011
		 * 0001
		 * ----AND
		 * 0001 if 1 its set or not set
		 * 
		 * </pre>
		 */
		System.out.println(((N >> i) & 1) != 0);
		System.out.println(((M >> i) & 1) != 0);

	}

}
