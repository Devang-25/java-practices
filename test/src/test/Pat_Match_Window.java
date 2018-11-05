package test;

import java.util.HashMap;
import java.util.Map;

// Find the smallest window in a string containing all characters of another string

/*
 Input :  string = "this is a test string"
         pattern = "tist"
Output :  Minimum window is "t stri"
Explanation: "t stri" contains all the characters
              of pattern.

Input :  string = "geeksforgeeks"
         pattern = "ork" 
Output :  Minimum window is "ksfor"	
 */

public class Pat_Match_Window {
	static final int no_of_chars = 256;
	int[] str_chars = new int[no_of_chars];
	int[] pat_chars = new int[no_of_chars];

	String findPatMatchMinWindow(String str, String pat) {
		int len1 = str.length();
		int len2 = pat.length();

		if (len1 < len2) {
			System.out.println("No such window exists");
			return "";
		}
		for (int i = 0; i < len2; i++) {
			pat_chars[pat.charAt(i)]++;
		}
		int count = 0;
		int start = 0, start_index = -1, min_len = Integer.MAX_VALUE;

		for (int j = 0; j < len1; j++) {
			str_chars[str.charAt(j)]++;

			if (pat_chars[str.charAt(j)] != 0 && pat_chars[str.charAt(j)] >= str_chars[str.charAt(j)]) {
				count++;
			}
			if (count == len2) {
				while (str_chars[str.charAt(start)] > pat_chars[str.charAt(start)]
						|| pat_chars[str.charAt(start)] == 0) {

					if (str_chars[str.charAt(start)] > pat_chars[str.charAt(start)])
						str_chars[str.charAt(start)]--;
					start++;
				}
				// update window size
				int len_window = j - start + 1;
				if (min_len > len_window) {
					min_len = len_window;
					start_index = start;
				}
			}
		}
		// If no window found
		if (start_index == -1) {
			System.out.println("No such window exists");
			return "";
		}

		return str.substring(start_index, start_index + min_len);
	}

	String findAllKeywordsMatchMinSnippet(String str, String[] keywords) {
		int len1 = str.length();
		int len2 = keywords.length;
		Map<String, Integer> keymaps = new HashMap<String, Integer>();
		for (int i = 0; i < len2; i++) {
			int val = keymaps.get(keywords[i]) == null ? 0 : keymaps.get(keywords[i]);
			keymaps.put(keywords[i], val + 1);
		}
		int start = Integer.MAX_VALUE;
		int end = 0;
		int start_index = 1;
		for (int i = 0; i < len2; i++) {
			int ind = new KMP_String_Matching().KMPSearch(keywords[i], str);
			if (ind >= 0) {
				if (start > ind) {
					start = ind;
				} else if (ind + keywords[i].length() > end) {
					end = ind + keywords[i].length();
				}
			}
		}
		return str.substring(start, end);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "aaabcda";
		String pat = "acd";
		Pat_Match_Window win = new Pat_Match_Window();
		// System.out.println(win.findPatMatchMinWindow(str, pat));
		String[] keys = new String[] { "aa", "bc" };
		String st= win.findAllKeywordsMatchMinSnippet(str, keys);
		System.out.println(st);
	}

}
