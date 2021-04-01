import java.util.Map;
import java.util.TreeMap;
/*
 * A customer wants to buy a pair of jeans, a pair of shoes, a skirt, and a top but has a limited budget in dollars. 
 * Given different pricing options for each product, determine how many options our customer has to buy 1 of each product. 
 * You cannot spend more money than the budgeted amount.

Example
priceOfJeans = [2, 3]
priceOfShoes = [4]
priceOfSkirts = [2, 3]
priceOfTops = [1, 2]
budgeted = 10

The customer must buy shoes for 4 dollars since there is only one option. This leaves 6 dollars to spend on the other 3 items. 
Combinations of prices paid for jeans, skirts, and tops respectively that add up to 6 dollars or less are [2, 2, 2], [2, 2, 1], [3, 2, 1], [2, 3, 1]. 
There are 4 ways the customer can purchase all 4 items.

Function Description

Complete the getNumberOfOptions function in the editor below. 
The function must return an integer which represents the number of options present to buy the four items.

getNumberOfOptions has 5 parameters:
int[] priceOfJeans: An integer array, which contains the prices of the pairs of jeans available.
int[] priceOfShoes: An integer array, which contains the prices of the pairs of shoes available.
int[] priceOfSkirts: An integer array, which contains the prices of the skirts available.
int[] priceOfTops: An integer array, which contains the prices of the tops available.
int dollars: the total number of dollars available to shop with.

Constraints

1 ≤ a, b, c, d ≤ 103
1 ≤ dollars ≤ 109
1 ≤ price of each item ≤ 109
Note: a, b, c and d are the sizes of the four price arrays
 */
public class ShopOptions {
	public static int getNumberOfOptions(int[] priceOfJeans, int[] priceOfShoes, int[] priceOfSkirts, int[] priceOfTops, int dollars) {
		int res = 0;
		Map<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < priceOfJeans.length; i++) {
			for (int j = 0; j < priceOfShoes.length; j++) {
				int part1 = priceOfJeans[i] + priceOfShoes[j];
				map.put(part1, map.getOrDefault(part1, 0) + 1);
			}
		}
		int accumulated = 0;
    	TreeMap<Integer, Integer> dp = new TreeMap<>();
    	for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
	    	accumulated += entry.getValue();
	    	dp.put(entry.getKey(), accumulated);
	    }
		for (int i = 0; i < priceOfSkirts.length; i++) {
			for (int j = 0; j < priceOfTops.length; j++) {
				int part2 = priceOfSkirts[i] + priceOfTops[j];
				int part1 = dollars - part2;
				Integer floor = dp.floorKey(part1);
				if (floor != null)
					res += dp.get(floor);
			}
		}		
		return res;
	}
	public static void main(String[] args) {
		int[] priceOfJeans = {2, 3}, priceOfShoes = {4}, priceOfSkirts = {2, 3}, priceOfTops = {1, 2};
		int dollars = 10;
		System.out.println(getNumberOfOptions(priceOfJeans, priceOfShoes, priceOfSkirts, priceOfTops, dollars));
	}
}
