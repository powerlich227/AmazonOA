/*
 * Given a multiset (set that allows for multiple instances of same value), 
 * partition it into two multisets A and B such that the sum of A is greater than that of B. Return A.
 * If more than one such "A"s exists, return the one with minimal size.

Examples
Example 1:
Input:
nums = [4, 5, 2, 3, 1, 2]

Output:
[4, 5]

Explanation:
We can divide the numbers into two subsets A = [4, 5] and B = [1, 2, 2, 3]. 
The sum of A is 9 which is greater than the sum of B which is 8. 
There are other ways to divide but A = [4, 5] is of minimal size of 2.
 */
import java.util.*;

public class OptimizingBoxWeights {
	public static List<Integer> optimize(int[] nums) {
		Arrays.sort(nums);
		int n = nums.length;
		LinkedList<Integer> res = new LinkedList<>();
		int sumA = 0, sumB = 0, l = 0, r = n - 1;
		while (l < r) {
			if (nums[l] + sumB < sumA)
				sumB += nums[l++];
			else {
				sumA += nums[r];
				res.addFirst(nums[r--]);
			}
		}
		return res;
	}
	public static void main(String[] args) {
		int[] test1 = {4, 5, 2, 3, 1, 2};
		int[] test2 = {10, 5, 3, 1, 20};
		int[] test3 = {1, 2, 5, 8, 3};
		
		System.out.println(optimize(test1));
		System.out.println(optimize(test2));
		System.out.println(optimize(test3));
	}
}
