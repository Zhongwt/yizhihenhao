package com.yzhh.backstage.api;

public class Wildcard {

	public static boolean match(String string, String pattern) {
		return match(string, pattern, 0, 0);
	}

	public static boolean equalsOrMatch(String string, String pattern) {
		if (string.equals(pattern) == true) {
			return true;
		}
		return match(string, pattern, 0, 0);
	}

	private static boolean match(String string, String pattern, int stringStartNdx, int patternStartNdx) {
		int pNdx = patternStartNdx;
		int sNdx = stringStartNdx;
		int pLen = pattern.length();
		if (pLen == 1) {
			if (pattern.charAt(0) == '*') { // speed-up
				return true;
			}
		}
		int sLen = string.length();
		boolean nextIsNotWildcard = false;

		while (true) {

			if ((sNdx >= sLen) == true) { // end of string still may have pending '*' in pattern
				while ((pNdx < pLen) && (pattern.charAt(pNdx) == '*')) {
					pNdx++;
				}
				return pNdx >= pLen;
			}
			if (pNdx >= pLen) { // end of pattern, but not end of the string
				return false;
			}
			char p = pattern.charAt(pNdx); // pattern char

			// perform logic
			if (nextIsNotWildcard == false) {

				if (p == '\\') {
					pNdx++;
					nextIsNotWildcard = true;
					continue;
				}
				if (p == '?') {
					sNdx++;
					pNdx++;
					continue;
				}
				if (p == '*') {
					char pnext = 0; // next pattern char
					if (pNdx + 1 < pLen) {
						pnext = pattern.charAt(pNdx + 1);
					}
					if (pnext == '*') { // double '*' have the same effect as one '*'
						pNdx++;
						continue;
					}
					int i;
					pNdx++;

					// find recursively if there is any substring from the end of the
					// line that matches the rest of the pattern !!!
					for (i = string.length(); i >= sNdx; i--) {
						if (match(string, pattern, i, pNdx) == true) {
							return true;
						}
					}
					return false;
				}
			} else {
				nextIsNotWildcard = false;
			}

			if (p != string.charAt(sNdx)) {
				return false;
			}

			sNdx++;
			pNdx++;
		}
	}

	public static int matchOne(String src, String[] patterns) {
		for (int i = 0; i < patterns.length; i++) {
			if (match(src, patterns[i]) == true) {
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		System.out.println(Wildcard.match("/api/res/23/22/123123/123123", "/api/res/**"));
	}

}
