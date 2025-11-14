package striver_dsa_sheet.bitManipulation;

public class COuntNumberOfSetBits {
	public static void main(String[] args) {
		int N = 16;
		/**
		 * <pre>
		 * 
		 * brute force just count while dividing the number by 2 
		 * and if reminder is 1 then increase the count
		 * 
		 * </pre>
		 */
		int count = 0;
		while (N > 0) {
			// use N & 1 the last bit of odd number is always one
			count += N & 1;
			// N = N / 2^1;
			N = N >> 1;
		}
		System.out.println(count);
		/**
		 * <pre>
		 * Another way
		 * 
		 * N = 84
		 * 1010100
		 * N-1
		 * 1010011
		 * -------AND
		 * 1010000 = 80
		 * 
		 * 80-1 of this number
		 * 1010000
		 * 1001111
		 * -------AND
		 * 1000000 = 64
		 * 
		 *  64-1
		 * 1000000
		 * 0111111
		 * -------AND
		 * 0000000 = 0
		 * 
		 * </pre>
		 */
		N = 84;
		count = 0;
		while (N != 0) {
			N = N & (N - 1);
			count++;
		}
		System.out.println(count);
	}
}
