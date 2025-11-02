package striver_dsa_sheet.recursions;

public class IsStringPalindrome {

	public static void main(String[] args) {
		String s = "madamm";
		System.out.println(isPalindrome(s, 0));
	}

	private static boolean isPalindrome(String s, int pointer) {
		if (pointer >= s.length() / 2) {
			return true;
		}
		if (s.charAt(pointer) != s.charAt(s.length() - 1 - pointer)) {
			return false;
		}
		return isPalindrome(s, pointer + 1);
	}
}
