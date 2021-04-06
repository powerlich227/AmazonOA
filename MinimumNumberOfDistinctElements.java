import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
 * Minimum number of distinct elements after removing m items

Given an array of items, an i-th index element denotes the item id’s, and given a number m.
The task is to remove m elements such that there should be minimum distinct id’s left. Print the number of distinct id’s.

Examples: 
 
Input : arr[] = { 2, 2, 1, 3, 3, 3} 
            m = 3
Output : 1
Remove 1 and both 2's.So, only 3 will be left that's why distinct id is 1.

Input : arr[] = { 2, 4, 1, 5, 3, 5, 1, 3} 
            m = 2
Output : 3
Remove 2 and 4 completely. So, remaining ids are 1, 3 and 5 i.e. 3
 */
public class MinimumNumberOfDistinctElements {
	public static int distinctIds(int arr[], int n, int m) {
		int res = 0;		
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) 
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		
		res = map.size();
		List<Map.Entry<Integer, Integer>> mapList = new ArrayList<>(map.entrySet());
		mapList.sort((a, b) -> (a.getValue() - b.getValue()));
		for (Map.Entry<Integer, Integer> e : mapList) {
			int count = e.getValue();
			if (count <= m) {
				m -= count;
				res--;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] arr = {2, 2, 1, 3, 3, 3}, arr2 = {2, 4, 1, 5, 3, 5, 1, 3};
		int m = 3, m2 = 2;
		System.out.println(distinctIds(arr, arr.length, m));
		System.out.println(distinctIds(arr2, arr2.length, m2));
	}

}
