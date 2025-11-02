package striver_dsa_sheet.recursions;

public class DistinctSubSequenceString {
	public static void main(String[] args) {
		System.out.println(numDistinct("rabbbit", "rabbit"));
	}

	public static int numDistinct(String s, String t) {
		return countDistinctSubSequences(s, t, new StringBuilder(), 0);
	}

	private static int countDistinctSubSequences(String s, String t, StringBuilder sub, int index) {
		if (t.contains(sub)) {
			return 0;
		}
		if (index >= s.length() || sub.length() >= t.length()) {
			if (sub.toString().equals(t)) {
				return 1;
			} else {
				return 0;
			}
		}
		int count = 0;
		// include
		sub.append(s.charAt(index));
		count += countDistinctSubSequences(s, t, sub, index + 1);
		// exclude
		sub.deleteCharAt(sub.length() - 1);
		count += countDistinctSubSequences(s, t, sub, index + 1);
		return count;
	}
}
