class Solution {
    /**
     * Calculates the amount of rainwater that can be trapped between bars of varying heights.
     *
     * This solution uses a two-pointer approach to efficiently calculate the trapped water.
     * It maintains two pointers, `left` and `right`, starting from the ends of the array,
     * and two variables, `max_left` and `max_right`, to keep track of the maximum height
     * encountered from the left and right respectively.
     *
     * Algorithm:
     * 1. Initialize `left` to 0 and `right` to `height.length - 1`.
     * 2. Initialize `max_left` to `height[left]` and `max_right` to `height[right]`.
     * 3. Initialize `total_water` to 0.
     * 4. Loop while `left` is less than `right`:
     *    a. If `max_left` is less than `max_right`:
     *       i. Move `left` pointer one step to the right (`left++`).
     *       ii. Update `max_left = Math.max(max_left, height[left])`.
     *       iii. Add `max_left - height[left]` to `total_water`. This is the water trapped
     *            above the current bar, limited by `max_left`.
     *    b. Else (`max_right` is less than or equal to `max_left`):
     *       i. Move `right` pointer one step to the left (`right--`).
     *       ii. Update `max_right = Math.max(max_right, height[right])`.
     *       iii. Add `max_right - height[right]` to `total_water`. This is the water trapped
     *            above the current bar, limited by `max_right`.
     * 5. Return `total_water`.
     *
     * @param height An array of non-negative integers representing the height of each bar.
     * @return The total amount of rainwater that can be trapped.
     *
     * Time Complexity: O(N), where N is the length of the `height` array, as we traverse the array once.
     * Space Complexity: O(1).
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int maxLeft = height[left];
        int maxRight = height[right];
        int totalWater = 0;

        while (left < right) {
            if (maxLeft < maxRight) {
                left++;
                maxLeft = Math.max(maxLeft, height[left]);
                totalWater += maxLeft - height[left];
            } else {
                right--;
                maxRight = Math.max(maxRight, height[right]);
                totalWater += maxRight - height[right];
            }
        }

        return totalWater;
    }
}
