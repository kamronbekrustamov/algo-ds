class Solution {

    /**
     * Calculates the number of ways to make up a specific amount using a given set of coins.
     * This solves the "Coin Change 2" problem using 1D Dynamic Programming.
     *
     * @param amount The total target amount.
     * @param coins  An array of distinct coin denominations.
     * @return The number of combinations that make up the amount.
     */
    public int change(int amount, int[] coins) {
        // Edge case: There is exactly one way to make amount 0 (choose no coins).
        // This is implicitly handled by dp[0] = 1, but stated here for clarity.
        
        // dp[i] represents the number of ways to make amount 'i'
        int[] dp = new int[amount + 1];
        
        // Base case: There is 1 way to make amount 0 (by taking nothing)
        dp[0] = 1;

        // Iterate through each coin denomination
        for (int coin : coins) {
            // Iterate through all amounts from the coin value up to the target amount.
            // NOTE: We iterate FORWARD (i++) because we can use the same coin multiple times.
            // (If we could only use each coin once, we would iterate backward).
            for (int i = coin; i <= amount; i++) {
                // The number of ways to make amount 'i' includes:
                // 1. Existing ways to make 'i' without this coin.
                // 2. Ways to make 'i - coin' (then adding this coin).
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }
}