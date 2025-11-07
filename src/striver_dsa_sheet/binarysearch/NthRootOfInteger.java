package striver_dsa_sheet.binarysearch;

/**
 * <pre>
 * Find Nth root of integer
 * 
 * N =2, M=4
 * 
 * 2 (square root) of 4
 * 
 * N=3, M=27
 * 
 * 3 (cube root) of 27
 * 
 * To solve Nth root of any index we are going to use Binary search.
 * 
 * Binary search is not only to find the element in n array. But it can also be
 * implemented for problems where the values are linearly increasing. Like
 * Monotonically increasing function.
 * 1*1*1 = 1
 * 2*2*2 = 8
 * 3*3*3 = 27
 * 4*4*4 = 64
 * So when we see something monotonically increasing we can apply binary search.
 * 
 * Search space : where can be answer lie?
 * 
 * N=3, M = 27
 * Ans lies somewhere between [1.......27] this is search space.
 *  low           high
 * [1.............27]
 * middle = (1+27)/2 = 14
 * 
 * now do 14*14*14 which is > 27 so the ans lies
 * low             high
 * [1..............14]
 * 
 * middle = (1+14)/2 = 7.5
 * 
 * 7.5*7.5*7.5 > 27
 * low      high
 * [1........7.5]
 * 
 * 
 * middle = (1+7.5)/2 = 8.5/2 = 4.25
 * 
 * 4.25*4.25*4.25 > 27
 * low    high
 * [1.....4.25]
 * 
 * middle = (1+4.25)/2 = 5.25/2 =  2.625
 * 
 * 2.625*2.265*2.265 < 27
 * low   high
 * [2.625 ...  4.25]
 * 
 * middle = (2.625+4.25)/2 = 6.875/2 = 3.4375
 * 
 * 3.4375*3.4375*3.4375 > 27
 * 
 * Till how many times?
 * If question say 5 decimal places
 * remember the difference of high-low > 1e^-6
 * 
 * 
 * [2.625 ..... 3.4375]
 * 
 * middle = 2.625+3.4375 = 6.0625/2 = 3.03125
 * 
 * 3.03125*3.03125*3.03125  >27
 * 
 * [2.625 ........ 3.03125]
 * middle = (2.625+3.03125)/2 = 2.828125
 * 
 * 
 * 2.828125*2.828125*2.828125 <27
 * 
 * [2.828125 ..... 3.03125]
 * 
 * middle = (2.828125+3.03125)/2 = 2.9296875
 * 
 * <27
 * 
 * [2.9296875 ...... 3.03125]
 * 
 * middle = (2.9296875+3.03125)/2 = 2.98046875
 * <27
 * 
 * [2.98046875 .... 3.03125] 
 * 
 * middle = (2.98046875+3.03125)/2 = 3.005859375
 * 
 * >27
 * 
 * 
 * 
 * 
 * </pre>
 */
public class NthRootOfInteger {
	public static void main(String[] args) {

	}

	private static double NthRoot(int N, int M) {
		return M;

	}
}
