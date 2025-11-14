package striver_dsa_sheet.bitManipulation;

public class NumberToBinaryAndViseVersa {
	public static void main(String[] args) {
		int n = 13;
		String res = binary(n);
		System.out.println(res);
		System.out.println(binaryToNumber((res)));
	}

	private static String binary(int n) {
		String res = "";

		while (n > 0) {
			int rem = n % 2;
			res = rem + res;
			n = n / 2;
		}
		return res;
	}

	private static int binaryToNumber(String binary) {
		int num = 0;
		int p = 0;
		int index = binary.length() - 1;
		while (index >= 0) {
			int n = (binary.charAt(index) - 48);
			// if its 0, then computation is 0 so no need to add.
			if (n == 1) {
				num += n * Math.pow(2, p);
			}
			p++;
			index--;
		}
		return num;
	}
}