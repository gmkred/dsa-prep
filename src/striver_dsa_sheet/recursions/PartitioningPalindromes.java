package striver_dsa_sheet.recursions;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 
 * "aabb"
 * 
 * partitioning palindromes, are substrings which are all palindromes.
 * 
 * {a,a,b,b}
 * {a,a,bb}
 * {aa,b,b}
 * {aa,bb}
 * 
 * Not a partitioning palindrome
 * 
 * {aab,b}
 * {a,abb}
 * because these are not completely palindromes.
 * index = sent to recursion call
 * i = used in for loop inside recursion
 * 
 * The approach is to use recursion, and loop through all the indexes of the string
 * but each recursive call will be happening from the current i+1, 
 * so it will start from the next index of the current on going valid partitioning palindromes,
 * At any given point, there is not planidrome then dont go further instead return back, while returning back remove the 
 * added substrings, when the call returned to previous recursion, it will start with next index in the recursion call,
 * and checks substring from index -> i is palindrome, if yes, again call recursion with i+1;
 * 
 * </pre>
 */
public class PartitioningPalindromes {
	public static void main(String[] args) {

		String s = "aabb";
		System.out.println(partition(s));
	}

	public static List<List<String>> partition(String s) {
		List<List<String>> ans = new ArrayList();
		List<String> partitions = new ArrayList();
		int len = s.length();
		recursion(s, 0, len, partitions, ans);
		return ans;
	}

	private static void recursion(String s, int index, int len, List<String> partitions, List<List<String>> ans) {
		if (index == len) {
			ans.add(new ArrayList(partitions));
			return;
		}
		for (int i = index; i < len; i++) {
			if (isStringPalindrome(s, index, i)) {
				partitions.add(s.substring(index, i + 1));
				recursion(s, i + 1, len, partitions, ans);
				partitions.remove(partitions.size() - 1);
			}
		}
	}

	private static boolean isStringPalindrome(String s, int start, int end) {
		while (end > start) {
			if (s.charAt(start) != s.charAt(end)) {
				return false;
			}
			end--;
			start++;
		}
		return true;
	}
}
