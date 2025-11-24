class Solution {
    /**
     * Searches for a target value in a rotated sorted array.
     * * @param nums   The rotated sorted integer array.
     * @param target The integer value to search for.
     * @return       The index of the target if found, otherwise -1.
     */
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            // Calculate mid to avoid potential integer overflow
            int midPos = low + (high - low) / 2;
            int midVal = nums[midPos];

            // Case 1: Target found
            if (midVal == target) {
                return midPos;
            }

            // Case 2: The RIGHT half is sorted
            // (e.g., [7, 0, 1, 2, 3, 4, 5, 6], mid is 2, high is 6)
            if (midVal < nums[high]) {
                // Check if target is strictly within the right sorted range
                if (midVal < target && target <= nums[high]) {
                    low = midPos + 1; // Go Right
                } else {
                    high = midPos - 1; // Go Left
                }
            }
            // Case 3: The LEFT half is sorted
            // (e.g., [4, 5, 6, 7, 0, 1, 2], mid is 7, high is 2)
            else {
                // Check if target is strictly within the left sorted range
                if (nums[low] <= target && target < midVal) {
                    high = midPos - 1; // Go Left
                } else {
                    low = midPos + 1; // Go Right
                }
            }
        }

        return -1;
    }
}