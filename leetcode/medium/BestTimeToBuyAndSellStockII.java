/**
 * Solution for the Best Time to Buy and Sell Stock II problem.
 */
class Solution {
    /**
     * Calculates the maximum profit that can be obtained by buying and selling a stock multiple times.
     *
     * The problem allows for completing as many transactions as you like (i.e., buy one and sell one share
     * of the stock multiple times). However, you may not engage in multiple transactions simultaneously
     * (i.e., you must sell the stock before you buy again).
     *
     * The strategy used here is a greedy approach. We iterate through the prices and accumulate
     * profit whenever the price on the current day is higher than the price on the previous day.
     * This is equivalent to buying on the low days and selling on the high days. For example,
     * if prices are [1, 2, 3], adding (2-1) + (3-2) = 2 is the same as buying at 1 and selling at 3.
     *
     * @param prices An array of integers where prices[i] is the price of the stock on day i.
     * @return The maximum profit that can be achieved.
     */
    public int maxProfit(int[] prices) {
        // If the prices array is null or has fewer than 2 elements, no transaction is possible.
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int totalProfit = 0;

        // Iterate through the prices starting from the second day.
        for (int i = 1; i < prices.length; i++) {
            // If the price on the current day is greater than the previous day,
            // we can make a profit.
            if (prices[i] > prices[i - 1]) {
                // Add the difference to the total profit. This simulates buying on day i-1
                // and selling on day i.
                totalProfit += prices[i] - prices[i - 1];
            }
        }

        // Return the accumulated profit.
        return totalProfit;
    }
}
