class Solution {
    /**
     * Finds the first missing positive integer in an unsorted array.
     *
     * This solution uses a cycle sort approach to place positive integers in their
     * correct positions. The number `k` should ideally be at index `k-1`.
     * For example, `1` at index `0`, `2` at index `1`, etc.
     *
     * Algorithm:
     * 1. Iterate through the array using index `i` from 0 to `n-1`.
     * 2. For each `nums[i]`:
     *    a. Calculate its `correct_idx` which should be `nums[i] - 1`.
     *    b. While `nums[i]` is a positive number within the range `[1, n]`
     *       AND `nums[i]` is not already in its `correct_idx` position
     *       (i.e., `nums[i] != nums[correct_idx]` to avoid infinite loops with duplicates):
     *       i. Swap `nums[i]` with `nums[correct_idx]`.
     *       ii. Recalculate `correct_idx` for the new `nums[i]` (which was the old `nums[correct_idx]`).
     *    c. If the conditions for swapping are not met, move to the next element (`i++`).
     * 3. After the cycle sort phase, iterate through the array again from `i = 0` to `n-1`.
     * 4. The first index `i` where `nums[i]` is not equal to `i + 1` indicates that `i + 1` is the
     *    first missing positive integer. Return `i + 1`.
     * 5. If the loop completes, it means all numbers from 1 to `n` are present and in their
     *    correct positions. In this case, the first missing positive is `n + 1`.
     *
     * @param nums The input array of integers.
     * @return The first missing positive integer.
     *
     * Time Complexity: O(N), where N is the length of the array. Although there's a nested loop,
     *                  each number is swapped at most once to its correct position.
     * Space Complexity: O(1), as the modification is done in-place.
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        int i = 0;
        while (i < n) {
            int correct_idx = nums[i] - 1;
            // Check if the number is in the valid range [1, n] and is not in its correct place,
            // and also check `nums[i] != nums[correct_idx]` to avoid infinite loops
            // when there are duplicate numbers.
            if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[correct_idx]) {
                // Swap nums[i] and nums[correct_idx]
                int temp = nums[i];
                nums[i] = nums[correct_idx];
                nums[correct_idx] = temp;
            } else {
                // If the number is out of range, negative, or already in the correct place,
                // move to the next element.
                i++;
            }
        }

        // After sorting, find the first position where the index `i` does not hold `i+1`.
        for (int j = 0; j < n; j++) {
            if (nums[j] != j + 1) {
                return j + 1;
            }
        }

        // If all numbers from 1 to n are present and in their correct positions,
        // the first missing positive is n + 1.
        return n + 1;
    }
}
