package striver_dsa_sheet.stackAndQueue;

import java.util.Stack;

/**
 * <pre>
 * 
 * every opening bracket should have closing bracket
 * ()[]{}
 * 
 * they should be following order
 * ()[(){}]
 * 
 * </pre>
 */
public class Balancedarantheses {
	public static void main(String[] args) {
		String s = "()[{}()]";
		String s2 = "()[{}(])";
		System.out.println(isBalancedParantheses(s));
		System.out.println(isBalancedParantheses(s2));
	}

	public static boolean isBalancedParantheses(String s) {

		Stack<Character> stack = new Stack();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
				stack.push(s.charAt(i));
			} else {
				if (s.charAt(i) == ')' && stack.peek() == '(') {
					stack.pop();
				} else if (s.charAt(i) == ']' && stack.peek() == '[') {

					stack.pop();
				} else if (s.charAt(i) == '}' && stack.peek() == '{') {

					stack.pop();
				}
			}
		}
		return stack.isEmpty();
	}
}
