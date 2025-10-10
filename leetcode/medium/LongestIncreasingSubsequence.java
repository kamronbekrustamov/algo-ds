import java.util.Arrays;

/**
 * Contains solutions for the Longest Increasing Subsequence (LIS) problem.
 * The primary solution is an efficient O(n log n) approach.
 * A secondary O(n^2) dynamic programming solution is also provided for reference.
 */
class Solution {

    /**
     * Finds the length of the longest increasing subsequence using an efficient
     * O(n log n) approach with dynamic programming and binary search.
     *
     * This algorithm maintains an array, `tails`, where `tails[i]` is the smallest
     * tail of all increasing subsequences with length `i+1`. This `tails` array
     * is guaranteed to be sorted, which allows for the use of binary search.
     *
     * @param nums The input array of numbers.
     * @return The length of the longest increasing subsequence.
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // `tails` is an array storing the smallest tail of all increasing subsequences.
        int[] tails = new int[nums.length];
        int size = 0; // Represents the current length of the LIS found so far.

        for (int num : nums) {
            // Perform a binary search to find the position for the current number `num`
            // in the `tails` array.
            // `Arrays.binarySearch` returns the index if found, or `(-(insertion point) - 1)`
            // if not found.
            int index = Arrays.binarySearch(tails, 0, size, num);

            // If `num` is not found, `index` will be negative. We calculate the
            // insertion point from it.
            if (index < 0) {
                index = -(index + 1);
            }

            // Place the current number at the correct position in the `tails` array.
            // If `num` is smaller than other tails of subsequences of the same length,
            // it replaces them, creating a potential for longer subsequences later on.
            tails[index] = num;

            // If the insertion point is at the current end of the `tails` array,
            // it means we have found a new, longer increasing subsequence.
            if (index == size) {
                size++;
            }
        }
        return size;
    }

    /**
     * (Secondary Solution)
     * Finds the length of the longest increasing subsequence using a classic
     * dynamic programming approach with O(n^2) time complexity.
     *
     * This method is kept for educational purposes to demonstrate the DP approach.
     *
     * @param nums The input array of numbers.
     * @return The length of the longest increasing subsequence.
     */
    private int lengthOfLIS_On2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // dp[i] will store the length of the LIS ending at index i.
        int[] dp = new int[nums.length];
        // Every element is an LIS of length 1 by itself.
        Arrays.fill(dp, 1);

        int maxLength = 1;

        // Iterate from the second element to the end.
        for (int i = 1; i < nums.length; i++) {
            // Check all previous elements.
            for (int j = 0; j < i; j++) {
                // If the current number is greater than a previous number,
                // it can potentially extend the increasing subsequence ending at `j`.
                if (nums[i] > nums[j]) {
                    // Update the LIS length for the current element.
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Keep track of the maximum length found so far.
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }
}