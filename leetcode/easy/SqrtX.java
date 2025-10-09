class Solution {
    /**
     * Computes the integer square root of a non-negative integer x.
     *
     * The function returns the largest integer `i` such that `i*i <= x`.
     *
     * This implementation uses binary search to find the square root,
     * which is much more efficient than a linear search. The time
     * complexity is O(log x).
     *
     * @param x A non-negative integer.
     * @return The integer square root of x.
     */
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        long low = 1, high = x;
        int ans = 0;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            // To avoid potential overflow with mid*mid, we can check if mid > x / mid.
            if (mid > x / mid) {
                high = mid - 1;
            } else {
                ans = (int) mid;
                low = mid + 1;
            }
        }
        return ans;
    }
}
