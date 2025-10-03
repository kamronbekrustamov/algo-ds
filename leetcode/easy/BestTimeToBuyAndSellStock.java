class Solution {
    /**
     * Calculates the maximum profit that can be achieved by buying a stock on one
     * day and selling it on a future day.
     *
     * This solution uses a single-pass greedy approach. It iterates through the
     * prices while keeping track of the lowest price found so far (`minPrice`)
     * and the maximum profit that could have been achieved (`maxProfit`).
     *
     * Time Complexity: O(N), where N is the number of prices, as we only
     *                  iterate through the array once.
     * Space Complexity: O(1), as we only use a few variables.
     *
     * @param prices An array of integers where prices[i] is the price of the stock on day i.
     * @return The maximum profit that can be achieved.
     */
    public int maxProfit(int[] prices) {
        // Handle edge case where no profit can be made.
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // `minPrice` tracks the lowest stock price encountered so far.
        // Initialize to a very large value to ensure the first price becomes the minimum.
        int minPrice = Integer.MAX_VALUE;
        
        // `maxProfit` tracks the maximum profit found so far.
        int maxProfit = 0;

        for (int price : prices) {
            // Update the minimum price seen so far (the best day to buy).
            minPrice = Math.min(minPrice, price);
            
            // Calculate the potential profit if we were to sell on the current day
            // and update maxProfit if it's a new maximum.
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        
        return maxProfit;
    }
}
