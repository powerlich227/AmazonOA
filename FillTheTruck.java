/*
 * A warehouse manager needs to create a shipment to fill a truck. 
 * All products loaded onto the truck should be packaged in boxes of the same size. 
 * One box can hold different number of units of product for each product type.

Given the number of boxes the truck can hold, write an algorithm to determine the maximum number of units of any mix of products that can be shipped.

Input
The input consists of five arguments:

num: an integer representing the number of product types in the warehouse

boxes: a list of integers representing the number of available boxes for each product type

unitSize: an integer representing size of the unitsPerBox list

unitsPerBox: a list of integers representing the number of units packed in a box for each product type

truckSize: an integer representing the number of boxes the truck can carry

Output
Return an integer representing the maximum units that can be carried by truck.

Constraints
1 <= |boxes| <= 10^5

|boxes| == |unitsPerBox|

1 <= boxes[i] <= 10^7

1 <= i <= |boxes|

1 <= unitsParBox[i] <= 10^5

1 <= j <= |unitsPerBox|

1 <= truckSize <= 10 ^ 8

Examples
Example 1:
Input:
num = 3

boxes = [1, 2, 3]

unitSize = 3

unitsPerBox = [3, 2, 1]

truckSize = 3

Output: 7
Explanation:
Product 0: because boxes[0] = 1, we know there is 1 box of product 0. And because unitsPerBox[0] = 3, we know there is 1 box with 3 units of product 0.

Product 1: 2 boxes with 2 units each

Product 2: 3 boxes with 1 unit each

Finally, we have packed products like the list: [3, 2, 2, 1, 1, 1]

The truckSize is 3, so we pick the top 3 boxes from the above list, which is [3, 2, 2], and return the sum 7.

The maximum number of units that can be shipped = 3 + 2 + 2 = 7 units.
 */
import java.util.*;
public class FillTheTruck {
	public static int maxUnits(int num, List<Integer> boxes, int unitSize, List<Integer> unitsPerBox, int truckSize) {
        int res = 0;    
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < unitSize; i++)
        	map.put(unitsPerBox.get(i), boxes.get(i));
        
        ArrayList<Map.Entry<Integer, Integer>> unitsBoxes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : map.entrySet())
            unitsBoxes.add(e);
        unitsBoxes.sort(Comparator.comparing(e -> -e.getKey()));
        
        int truckLeft = truckSize;
        for (Map.Entry<Integer, Integer> e : unitsBoxes) {
            int units = e.getKey();
            int box = Math.min(truckLeft, e.getValue());
            res += units * box;
            truckLeft -= box;
            if (truckLeft == 0)
                break;
        }
        return res;
    }
	public static void main(String[] args) {
		int num = 3, unitSize = 3, truckSize = 3;
		int num2 = 4, unitSize2 = 4, truckSize2 = 2;
		List<Integer> boxes = Arrays.asList(1, 2, 3), boxes2 = Arrays.asList(1, 7, 5, 1);
		List<Integer> unitsPerBox = Arrays.asList(3, 2, 1), unitsPerBox2 = Arrays.asList(4, 3, 5, 9);
		System.out.println(maxUnits(num, boxes, unitSize, unitsPerBox, truckSize));
		System.out.println(maxUnits(num2, boxes2, unitSize2, unitsPerBox2, truckSize2));
	}
}
