class Solution {
    /**
     * Given a sorted array of distinct integers and a target value, return the index if the target is found.
     * If not, return the index where it would be if it were inserted in order.
     *
     * This function implements a binary search algorithm to achieve O(log n) runtime complexity.
     *
     * Algorithm:
     * 1. Initialize `low` to 0 and `high` to `nums.length - 1`.
     * 2. Perform binary search to find the target or the insertion point:
     *    a. While `low` is less than or equal to `high`:
     *       i. Calculate the `middle` index: `middle = low + (high - low) / 2`.
     *       ii. If `nums[middle]` is equal to `target`, return `middle`.
     *       iii. If `nums[middle]` is less than `target`, the target must be in the right half.
     *            Set `low = middle + 1`.
     *       iv. If `nums[middle]` is greater than `target`, the target must be in the left half.
     *            Set `high = middle - 1`.
     * 3. If the loop finishes, the target was not found. The `low` pointer will be at the position
     *    where the target should be inserted.
     *
     * @param nums A sorted array of distinct integers.
     * @param target The integer to search for.
     * @return The index of the target if found, otherwise the index where it should be inserted.
     *
     * Time Complexity: O(log N), where N is the number of elements in the array.
     * Space Complexity: O(1).
     */
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int middle = low + (high - low) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return low;
    }
}
