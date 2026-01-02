/**
 * Calculates the number of structurally unique Binary Search Trees (BSTs) 
 * that can be formed with n unique nodes (values 1 to n).
 * * <p>This implementation uses Dynamic Programming (bottom-up) to calculate
 * the nth Catalan number.
 * * <p><b>Complexity:</b>
 * <ul>
 * <li>Time: O(n^2) - Nested loops to calculate each state based on previous states.</li>
 * <li>Space: O(n) - Array to store intermediate results.</li>
 * </ul>
 */
class Solution {
    public int numTrees(int n) {
        // dp[i] stores the number of unique BSTs with i nodes
        int[] dp = new int[n + 1];

        // Base case: An empty tree (0 nodes) is structurally unique (1 way)
        dp[0] = 1;

        // Base case: A tree with 1 node is structurally unique (1 way)
        // (This is implicitly handled by the loop logic but good to know)
        
        // Calculate the number of unique BSTs for sizes 1 up to n
        for (int i = 1; i <= n; i++) {
            // Consider every number j from 1 to i as the root node.
            // (Note: inner loop variable 'j' here represents the number of nodes in the LEFT subtree)
            for (int j = 0; j < i; j++) {
                // dp[j]: Number of ways to form the left subtree (j nodes)
                // dp[i - 1 - j]: Number of ways to form the right subtree (remaining nodes)
                // Multiply them (Cartesian product) and add to the total for size i
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }
        
        return dp[n];
    }
}