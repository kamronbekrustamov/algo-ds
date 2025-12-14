class Solution {

    /**
     * Calculates the maximum amount of water a container can store.
     * * <p>Algorithm: Two-Pointer Approach
     * <br>Time Complexity: O(n) - We traverse the array once.
     * <br>Space Complexity: O(1) - We use constant extra space.
     * * @param height An array of non-negative integers representing the height of lines.
     * @return The maximum area of water that can be contained.
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            // Cache values to avoid repeated array access
            int hLeft = height[left];
            int hRight = height[right];

            // Calculate area: width * min_height
            // We use the shorter line because water would spill over the taller one
            int currentArea = (right - left) * Math.min(hLeft, hRight);
            
            // Update maxArea if the current one is larger
            if (currentArea > maxArea) {
                maxArea = currentArea;
            }

            // Move the pointer of the shorter line inward.
            // Logic: Moving the taller pointer would decrease the width without 
            // potentially increasing the height (bounded by the shorter line),
            // guaranteeing a smaller area. We must move the shorter line to hope for a taller one.
            if (hLeft < hRight) {
                left++;
                // Optimization: Skip lines shorter than or equal to the current left line
                // because the width is shrinking. We need a strictly taller line to beat the current area.
                while (left < right && height[left] <= hLeft) {
                    left++;
                }
            } else {
                right--;
                // Optimization: Skip lines shorter than or equal to the current right line
                while (left < right && height[right] <= hRight) {
                    right--;
                }
            }
        }
        
        return maxArea;
    }
}