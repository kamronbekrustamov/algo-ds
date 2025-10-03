class Solution {
    /**
     * Searches for a target value in a sorted array of integers using the
     * Binary Search algorithm.
     *
     * Algorithm Breakdown:
     * 1. Initialize two pointers, `low` and `high`, to represent the boundaries
     *    of the search space. Initially, this is the entire array.
     * 2. While the search space is valid (`low <= high`):
     *    a. Calculate the middle index, `mid`.
     *    b. Compare the element at `mid` with the `target`.
     *    c. If they are equal, the target is found, and we return its index.
     *    d. If the middle element is greater than the target, it means the target
     *       must be in the left half of the current search space. We shrink the
     *       space by moving the `high` pointer to `mid - 1`.
     *    e. If the middle element is less than the target, the target must be in
     *       the right half. We shrink the space by moving the `low` pointer to
     *       `mid + 1`.
     * 3. If the loop finishes without finding the target, it means the target is
     *    not in the array, so we return -1.
     *
     * Time Complexity: O(log n), where n is the number of elements in the array,
     *                  because we halve the search space in each iteration.
     * Space Complexity: O(1), as we only use a few variables to store pointers.
     *
     * @param nums   A sorted array of integers.
     * @param target The integer value to search for.
     * @return The index of the target if it is found, otherwise -1.
     */
    public int search(int[] nums, int target) {
        // Handle null or empty array case.
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 1. Initialize pointers for the boundaries of the search space.
        int low = 0;
        int high = nums.length - 1;

        // 2. Loop as long as the search space is valid.
        while (low <= high) {
            // a. Calculate the middle index.
            // This formula is used to prevent potential integer overflow
            // compared to the simpler `(low + high) / 2`.
            int mid = low + (high - low) / 2;

            // b. Compare the middle element with the target.
            if (nums[mid] == target) {
                // c. Target found.
                return mid;
            } else if (nums[mid] > target) {
                // d. Target must be in the left half.
                // Discard the right half by moving the high pointer.
                high = mid - 1;
            } else {
                // e. Target must be in the right half.
                // Discard the left half by moving the low pointer.
                low = mid + 1;
            }
        }

        // 3. If the loop finishes, the target was not found in the array.
        return -1;
    }
}
