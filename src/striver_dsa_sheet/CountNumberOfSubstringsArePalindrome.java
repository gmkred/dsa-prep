package striver_dsa_sheet;

public class CountNumberOfSubstringsArePalindrome {
	public static void main(String[] args) {
		String s = "sbabacabc";
		System.out.println(palindromeSubStringsCount(s, 0));
	}

	private static int palindromeSubStringsCount(String s, int index) {
		if (index >= s.length()) {
			return 0;
		}
		int count = 0;
		// odd
		count += isPalindrome(s, index, index);
		// even
		count += isPalindrome(s, index, index + 1);
		count += palindromeSubStringsCount(s, index + 1);
		return count;
	}

	private static int isPalindrome(String s, int mid, int midNext) {
		int count = 0;
		while (midNext < s.length() && mid >= 0 && s.charAt(mid) == s.charAt(midNext)) {
			count++;
			mid--;
			midNext++;
		}
		return count;
	}
}
