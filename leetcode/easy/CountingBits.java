class Solution {
    /**
     * Counts the number of 1's in the binary representation of each number from 0 to n.
     * 
     * This solution uses a dynamic programming approach based on the observation that
     * the count of bits for a number i can be derived from the count of bits for i >> 1
     * (i divided by 2, discarding the remainder) plus the least significant bit of i.
     * 
     * For example:
     * - For i = 5 (binary 101), i >> 1 = 2 (binary 10), and the least significant bit is 1.
     *   So, ans[5] = ans[2] + 1 = 1 + 1 = 2.
     * - For i = 6 (binary 110), i >> 1 = 3 (binary 11), and the least significant bit is 0.
     *   So, ans[6] = ans[3] + 0 = 2 + 0 = 2.
     * 
     * Time Complexity: O(n) - We iterate through each number from 1 to n once.
     * Space Complexity: O(n) - We store the result for each number from 0 to n.
     * 
     * @param n The upper bound of the range (inclusive).
     * @return An array where ans[i] is the number of 1's in the binary representation of i.
     */
    public int[] countBits(int n) {
        // Initialize the result array with size n+1 to store counts for numbers 0 to n
        int[] result = new int[n + 1];
        
        // Iterate through each number from 1 to n
        for (int i = 1; i <= n; i++) {
            // The count of bits for i is the count of bits for i >> 1 (i divided by 2)
            // plus the least significant bit of i (i & 1)
            // This works because shifting right by 1 discards the least significant bit
            result[i] = result[i >> 1] + (i & 1);
        }
        
        return result;
    }
}