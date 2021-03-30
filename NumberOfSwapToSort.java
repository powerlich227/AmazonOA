/* 
 * Given an array and a sorting algorithm, the sorting algorithm will do a selection swap. 
 * Find the number of swaps to sort the array.

Example 1:
Input: [5, 4, 1, 2]
Output: 5
Explanation:
Swap index 0 with 1 to form the sorted array [4, 5, 1, 2].
Swap index 0 with 2 to form the sorted array [1, 5, 4, 2].
Swap index 1 with 2 to form the sorted array [1, 4, 5, 2].
Swap index 1 with 3 to form the sorted array [1, 2, 5, 4].
Swap index 2 with 3 to form the sorted array [1, 2, 4, 5].

 */
import java.util.*;
public class NumberOfSwapToSort {
//	public static int countSwaps(int[] nums) {
//		int n = nums.length;
//		int[] temp = new int[n];
//		return mergeSort(nums, temp, 0, n - 1);
//	}
//	// mergeSort
//	public static int mergeSort(int[] nums, int[] temp, int left, int right) {
//		int res = 0;
//		if (nums.length <= 1)
//			return 0;
//		int mid = 0;
//		if (left < right) {
//			mid = (left + right)/ 2;
//			
//			res += mergeSort(nums, temp, left, mid);
//			res += mergeSort(nums, temp, mid + 1, right);
//			res += merge(nums, temp, left, mid + 1, right);
//		}
//		return res;
//	}
//	// merge
//	public static int merge(int[] nums, int[] temp, int left, int mid, int right) {
//		int res = 0, i = left, j = mid, k = left;
//		while (i < mid && j <= right) {
//			if (nums[i] < nums[j])
//				temp[k++] = nums[i++];
//			else {
//				temp[k++] = nums[j++];
//				res += (mid - i);
//			}
//		}
//		while (i < mid)
//			temp[k++] = nums[i++];
//		while (j <= right)
//			temp[k++] = nums[j++];
//		for (i = left; i <= right; i++)
//			nums[i] = temp[i];
//		return res;
//	}
	
	// mergeSort
	public static int countSwaps(int[] nums) {
		int res = 0;
		if (nums.length <= 1)
			return 0;
		int	n = nums.length, mid = n / 2;
			
		int[] nums1 = Arrays.copyOfRange(nums, 0, mid);
		int[] nums2 = Arrays.copyOfRange(nums, mid, n);
		res += countSwaps(nums1);
		res += countSwaps(nums2);
		res += merge(nums, nums1, nums2, mid);
		return res;
	}
	// merge
	public static int merge(int[] nums, int[] nums1, int[] nums2, int mid) {
		int res = 0, i = 0, j = 0, k = 0;
		int m = nums1.length, n = nums2.length;
		while (i < m && j < n) {
			if (nums1[i] < nums2[j])
				nums[k++] = nums1[i++];
			else {
				nums[k++] = nums2[j++];
				res += (mid - i);
			}
		}
		while (i < m)
			nums[k++] = nums1[i++];
		while (j < n)
			nums[k++] = nums2[j++];
		return res;
	}
	public static void main(String[] args) {
		int[] nums = {5, 2, 4, 1}, nums2 = {5, 2, 6, 1}, nums3 = {1, 9, 7, 8, 5}, nums4 = {6, 3, 7, 1, 5, 8, 2, 4};
		System.out.println(countSwaps(nums));
		System.out.println(countSwaps(nums2));
		System.out.println(countSwaps(nums3));
		System.out.println(countSwaps(nums4));
	}
}
