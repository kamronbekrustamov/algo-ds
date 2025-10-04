class Solution {
    /**
     * Creates a new array that is the concatenation of the input array with itself.
     *
     * Given an input array `nums` of length `n`, this method creates and returns
     * a new array `ans` of length `2n`. The first half of `ans` is a copy of
     * `nums`, and the second half is also a copy of `nums`.
     *
     * Algorithm Breakdown:
     * 1. Determine the length `n` of the input array `nums`.
     * 2. Create a new integer array `ans` with a size of `2 * n`.
     * 3. Iterate from `i = 0` to `n - 1`. In each iteration:
     *    a. Copy the element `nums[i]` to `ans[i]`.
     *    b. Copy the same element `nums[i]` to `ans[i + n]`.
     * 4. Return the resulting array `ans`.
     *
     * Time Complexity: O(n), where n is the length of the input array, because
     *                  we iterate through the array once.
     * Space Complexity: O(n), as we create a new array of size 2n. (The space for
     *                   the output array is typically not counted, in which case
     *                   it would be O(1)).
     *
     * @param nums The input integer array.
     * @return A new array representing the concatenation of `nums` with itself.
     */
    public int[] getConcatenation(int[] nums) {
        // 1. Get the length of the original array.
        int n = nums.length;

        // 2. Create a new array with twice the length.
        int[] ans = new int[2 * n];

        // 3. Iterate through the original array to populate the new array.
        for (int i = 0; i < n; i++) {
            // a. Copy the element to the first half of the new array.
            ans[i] = nums[i];
            // b. Copy the same element to the second half of the new array.
            ans[i + n] = nums[i];
        }

        // 4. Return the concatenated array.
        return ans;
    }
}
