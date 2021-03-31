/*
This problem is a variant of closest pair sum. You'll be given two arrays
arr1 = { {1, 2000}, {2, 3000}, {3, 4000} }
arr2 = { { 1, 5000 }, {2, 3000} }
the first element of every pair represents id and the second value represents the value.
and a target x = 5000
Find the pairs from both the arrays whose value add up to a sum which is less than given target and should be close to the target.

Output for the above example:
{ {1, 2} } // Note that the output should be in id's
 */

import java.util.*;
public class AmazonPrimeAirRoute {
	public static List<int[]> getClosestPair(int[][] arr1, int[][] arr2, int target) {
		List<int[]> res = new ArrayList<>();
		TreeMap<Integer, List<Integer>> valueIDs = new TreeMap<>();
		
		int tempTarget = 0;
		for (int[] a : arr1) {
			int id1 = a[0], value1 = a[1];
			valueIDs.putIfAbsent(value1, new ArrayList<>());
			valueIDs.get(value1).add(id1);
		}
		
		for (int[] a : arr2) {
			int id2 = a[0], value2 = a[1];
			int remained = target - value2; // remaining value in arr1
			if (remained < 0)
				continue;
			if (valueIDs.containsKey(remained)) {
				if (target > tempTarget) {
					tempTarget = target;
					res = new ArrayList<>();
				}		
				for (int id : valueIDs.get(remained))
					res.add(new int[] {id, id2});
			}
			else {
				Integer floorRemained = valueIDs.floorKey(remained);
				if (floorRemained == null)
					continue;
				int newTarget = floorRemained + value2;
				if (newTarget >= tempTarget) {
					if (newTarget > tempTarget)  {
						tempTarget = newTarget;
						res = new ArrayList<>();
					}
					for (int id : valueIDs.get(floorRemained))
						res.add(new int[] {id, id2});
				}
			}
		}
		return res;
	}
	public static void main(String[] args) {
		int[][] arr1 = {{1, 2000}, {2, 3000}, {3, 4000}}, arr2 = { { 1, 5000 }, {2, 3000} };
		int target = 5000;
		for (int[] pair : getClosestPair(arr1, arr2, target)) {
				System.out.println(pair[0] + " " + pair[1]);
		}
	}
}
