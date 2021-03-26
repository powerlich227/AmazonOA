/*
Given a string s made up of lowercase alphabet characters, find all unique substrings containing distinct characters of length k.

Examples
Example 1:
Input: s = xabxcd, k = 4
Output: ["abxc", "bxcd"]
Explanation:
Substrings of size 4 in s are xabx, abxc, and bxcd. However x repeats in the xabx, so it is not a valid substring made up of distinct characters.

Example 2:
Input: s = aabcdbcd, k = 3
Output: ["abc", "bcd", "cdb", "dbc"]
Explanation:
The substrings with distinct characters are abc, bcd, cdb, dbc, and again bcd. However, we are only looking for unique substrings, so we discard the last one.

Constraints:
k is a positive number less than or equal to 26.
 */

import java.util.*;

public class SubstringOfSizeKWithDistinctCharacters {
	public static List<String> substrings (String s, int k) {
		List<String> res = new ArrayList<>();
		Map<Character, Integer> map = new HashMap<>();
		Set<String> found = new HashSet<>();
		
		int l = 0, r = 0;
		while (r < s.length()) {
			char c = s.charAt(r);
			int start = map.getOrDefault(c, r - k);
			while (l <= start)
				map.remove(s.charAt(l++));
			map.put(c, r);
			r++;
			
			if (r - l < k)
				continue;
			String substring = s.substring(l, r);
			if (!found.contains(substring)) {
				found.add(substring);
				res.add(substring);
			}
		}
		return res;
	}
	public static void main(String[] args) {
		String s1 = "xabxcd";
		String s2 = "aabcdbcd";
		int k1 = 4;
		int k2 = 3;
		System.out.println(substrings(s1, k1));
		System.out.println(substrings(s2, k2));		
	}
}
