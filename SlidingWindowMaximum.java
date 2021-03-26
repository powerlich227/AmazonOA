/*
 * Leetcode 239
 * 
A user wants to store a file in a data center, but requests it to be replicated across each machine in a block. 
A block is defined as a continuous set of machines, starting from the first machine, with each block being next to one another and fixed in size. 
For example, if the block size is defined as 3, the first block is composed of machines 1 to 3, the second block is composed of machines 2 to 4, and so on.

Find the largest possible file the user can store in a data center, given a block size.

Input
freeSpace: a list of numbers representing the free space available in each machine of the data center

blockSize: a number representing the size of each block

Output
A number representing the amount of free space that the emptiest block in the data center has. 
The free space within a given block is the minimum free space of all the machines in it.

Constraints
The size of the block is always smaller than the number of machines in the freeSpace list. freeSpace values are never zero.

Examples
Example 1:
Input:
freeSpace = [8,2,4,5]

blockSize = 2

Output: 4
Explanation:
In this data center, the subarrays representing the free space of each block of size 2 are [8,2], [2,4], and [4,5]. 
The minimum available space of each blocks is 2, 2, and 4. The maximum of these values is 4. Therefore, the answer is 4.

Complexity
Both time complexity and space complexity must be around O(n).
 */
import java.util.*;

public class SlidingWindowMaximum {
	/*
	 * Deque
	 */
	public static int maxDiskSpace(int[] nums, int k) {
		int n = nums.length;
		int res = 0;
		
		Deque<Integer> dq = new ArrayDeque<>(); // store the index of minimum in each block.
		for (int i = 0; i < n; i++) {
			while (!dq.isEmpty() && dq.peekFirst() <= i - k) // check the left bound of the block
				dq.pollFirst();
			while (!dq.isEmpty() && nums[dq.peekLast()] > nums[i]) // remove the bigger number of nums[i-1] and nums[i].
				dq.pollLast();
			dq.offerLast(i);
			if (i > k - 2)
				res = Math.max(nums[dq.peekFirst()], res);
		}
		return res;
	}
	public static void main(String[] args) {
		int[] nums = {8, 2, 4, 5}, nums2 = {1, 6, 7, 4, 8, 11, 9}, nums3 = {3, 18, 40, 5, 5, 5, 7, 2};
		int k = 2, k2 = 3, k3 = 4;
		System.out.println(maxDiskSpace(nums, k));
		System.out.println(maxDiskSpace(nums2, k2));
		System.out.println(maxDiskSpace(nums3, k3));
	}
}
