class Solution {
    /**
     * Calculates the n-th Fibonacci number.
     *
     * The Fibonacci sequence is defined as:
     * F(0) = 0
     * F(1) = 1
     * F(n) = F(n-1) + F(n-2) for n > 1.
     *
     * This solution uses a space-optimized dynamic programming approach to
     * calculate the Fibonacci number iteratively. Instead of storing the entire
     * sequence in an array, we only need to keep track of the last two numbers
     * to compute the next one.
     *
     * Time Complexity: O(n), as we iterate from 2 up to n.
     * Space Complexity: O(1), as we only use a constant number of variables.
     *
     * @param n A non-negative integer.
     * @return The n-th Fibonacci number.
     */
    public int fib(int n) {
        // Base cases for F(0) and F(1).
        if (n < 2) {
            return n;
        }

        // Initialize variables to store the state for F(i-2) and F(i-1).
        int twoStepsBack = 0; // Represents F(0)
        int oneStepBack = 1;  // Represents F(1)

        // Iterate from 2 up to n to calculate the Fibonacci numbers.
        for (int i = 2; i <= n; i++) {
            // Calculate the current Fibonacci number.
            int currentFib = twoStepsBack + oneStepBack;
            
            // Update the two state variables for the next iteration.
            twoStepsBack = oneStepBack;
            oneStepBack = currentFib;
        }

        // After the loop, oneStepBack holds the value for F(n).
        return oneStepBack;
    }
}
