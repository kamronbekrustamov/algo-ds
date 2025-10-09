class NumArray {
    /**
     * This class allows for fast calculation of the sum of elements in a given range of an array.
     * It pre-computes a prefix sum array, which allows for constant-time range sum queries.
     *
     * Time Complexity: O(n) for constructor, O(1) for sumRange.
     * Space Complexity: O(n) for the prefix sum array.
     */
    private int[] prefix;

    /**
     * Initializes the data structure by creating a prefix sum array.
     * The prefix sum array `this.prefix[i]` stores the cumulative sum of the elements of `nums`
     * up to index `i-1`.
     *
     * @param nums A list of integers.
     */
    public NumArray(int[] nums) {
        prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
    }

    /**
     * Calculates the sum of the elements from index `left` to `right` (inclusive).
     * This is done by subtracting the cumulative sum up to `left` from the cumulative sum up to `right + 1`.
     * For example, sum(left, right) = prefix[right + 1] - prefix[left].
     *
     * @param left The starting index of the range.
     * @param right The ending index of the range.
     * @return The sum of the elements in the specified range.
     */
    public int sumRange(int left, int right) {
        return prefix[right + 1] - prefix[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
