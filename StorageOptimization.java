/*
The local library is considering making their bookshelves more efficient by implementing a new flexible shelf system. 
The new shelves consist of rearrangeable dividers (both vertically and horizontally), 
with the smallest blocks being a cube of 1 foot by 1 foot by 1 foot.

Given a shelf configuration, calculate the volume of the largest space in the shelf.
n = number of horizontal dividers
m = number of vertical dividers
h = a list of numbers representing the horizontal dividers that are missing
v = a list of numbers representing the vertical dividers that are missing

Example 1:
Input:
n = 6 m = 6 h = [4] v = [2]
Output: 4

Explanation:
Consider the diagram below. The left image depicts the initial storage unit with all the dividers. 
The right image depicts the unit after h = [4] and v = [2] dividers are removed. 
The maximum storage volume for that shelf is therefore 2 x 2 x 1 = 4 cubic feet. 
The last dimension is always 1, since all shelves are 1 foot deep.
 */

import java.util.*;

public class StorageOptimization {
	public static int maxArea(int n, int m, List<Integer> h, List<Integer> v) {
		int[] h1 = new int[n + 1], v1 = new int[m + 1];
		for (int i : h) 
			h1[i] = 1;
		for (int j : v)
			v1[j] = 1;
		
		int x = 0, y = 0, max_X = 0, max_Y = 0;
		for (int i : h1) {
			if (i == 0)
				x = 0;
			else 
				max_X = Math.max(max_X, ++x);
		}
		for (int j : v1) {
			if (j == 0)
				y = 0;
			else
				max_Y = Math.max(max_Y, ++y);
		}
		return (max_X + 1) * (max_Y + 1);
	}
	public static void main(String[] args) {
		int n = 3, m = 2;
		List<Integer> h = Arrays.asList(1, 2, 3);
		List<Integer> v = Arrays.asList(1, 2);
		System.out.println(maxArea(n, m, h, v));
	}
}
