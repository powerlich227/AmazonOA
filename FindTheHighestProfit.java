import java.util.*;
import java.util.Map.Entry;
/*
 * An e-commerce company imports a type of fitness band from China and sell them in the US for a higher price. 
 * The company source the product from multiple suppliers, each with their own inventory. 
 * The suppliers raise the price of their product when inventory decreases due to scarcity. 
 * More specifically, the profit that the e-commerce company makes on each product sold is equal to the number of products left from the supplier.

Given a list of integers representing the number of products each supplier has and an integer representing the number of products sold, 
find the maximum profit the company can make.

Examples
Example 1:
Input:
inventories = [6, 4] order = 4

Output: 19
Explanation:
There are two suppliers, with inventory of 4 and 6 respectively. A total of 4 items are ordered. We can make maximum profit by

selling 1 item from the first supplier for 6
selling 1 item from the first supplier for 5
selling 1 item from the first supplier for 4, which brings down the inventory of the first supplier to 3
selling 1 item from the second supplier for 4
The maximum profit is 6 + 5 + 4 + 4 = 19.

Example 2:
Input:
inventories = [10, 10]

order = 5

Output: 46
Explanation:
The maximum profit we can generate is by

selling 1 item for a profit of 10 from the first supplier
selling 1 item for a profit of 10 from the second supplier
selling 1 item for a profit of 9 from the first supplier
selling 1 item for a profit of 9 from the second supplier
selling 1 item for a profit of 8 from the first or second supplier
The maximum profit is 10 + 10 + 9 + 9 + 8 = 46.
 */
public class FindTheHighestProfit {
	public static int findProfit(List<Integer> inventory, int order) {
		int res = 0;
		Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
		pq.addAll(inventory);		
		for (int i = 0; i < order; i++) {
			int cur = pq.poll();
			res += cur;
			pq.offer(cur - 1);
		}
		return res;
	}
	
	public static int findProfit2(List<Integer> inventory, int order) {
		int res = 0;
		Map<Integer, Integer> map = new TreeMap<>((a, b) -> (b - a)); // (stock, suppliers count)
		for (int i : inventory)
			map.merge(i, 1, Integer::sum); // map.merge(i, 1, (k, v) -> k + v)
        List<Entry<Integer, Integer>> stocks = new ArrayList<>();
        for (Entry<Integer, Integer> entry : map.entrySet())
        	stocks.add(entry);
        
        int n = stocks.size(), suppliers = 0;        
        for (int i = 0; i < n; i++) {
            int stock = stocks.get(i).getKey();
            // stock of next supplier group
            int nextStock = (i < n - 1) ? stocks.get(i + 1).getKey() : 0;
            // suppliers = suppliers from before + new suppliers
            suppliers += stocks.get(i).getValue();
            // number of products that current suppliers have best prices of
            // before other suppliers can compete
            int supply = suppliers * (stock - nextStock);
            // full rows of products to purchase
            int full = Math.min(order, supply) / suppliers;
            // extras in last row
            int part = Math.min(order, supply) % suppliers;
            // profit gained from full rows and last row
            res += suppliers * (2 * stock - full + 1) * full / 2 + part * (stock - full);
            order -= supply;
        	if (order <= 0)
        		break;
        }
		return res;
	}
	public static void main(String[] args) {
		List<Integer> inventory = Arrays.asList(6, 4), inventory2 = Arrays.asList(10, 10);
		int order = 4, order2 = 5;
		System.out.println(findProfit2(inventory, order));
		System.out.println(findProfit2(inventory2, order2));
	}

}
