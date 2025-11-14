/**
 * Solution for rotating an array to the right by k steps.
 * This class implements the efficient three-reversal method.
 */
class Solution {
    
    /**
     * Rotates the given array `nums` to the right by `k` steps.
     * The rotation is performed in-place using the three-reversal technique.
     *
     * Time Complexity: O(n), where n is the length of nums.
     * Space Complexity: O(1) extra space.
     *
     * @param nums The array to rotate.
     * @param k The number of steps to rotate the array to the right.
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return;
        }

        int n = nums.length;
        // Normalize k: rotation by n steps results in the original array.
        // k must be between 0 and n-1.
        k = k % n;
        
        // If k is 0 after modulo, no rotation is needed.
        if (k == 0) {
            return;
        }

        // The three-reversal algorithm:
        // Let the array be A = [1, 2, 3, 4, 5, 6] and k = 2.
        // The result should be [5, 6, 1, 2, 3, 4].
        
        // 1. Reverse the entire array.
        // [1, 2, 3, 4, 5, 6] -> [6, 5, 4, 3, 2, 1]
        // This brings the elements that should be at the front to the back,
        // but in the reverse order.
        reverseRange(nums, 0, n - 1);
        
        // 2. Reverse the first k elements.
        // [6, 5, | 4, 3, 2, 1] -> [5, 6, | 4, 3, 2, 1]
        // This corrects the order of the first k elements (which are the last k
        // elements of the original array).
        reverseRange(nums, 0, k - 1);
        
        // 3. Reverse the remaining n - k elements.
        // [5, 6, | 4, 3, 2, 1] -> [5, 6, | 1, 2, 3, 4] (Final result)
        // This corrects the order of the remaining n - k elements
        // (which are the first n - k elements of the original array).
        reverseRange(nums, k, n - 1);
    }

    /**
     * Reverses the elements of the array `nums` within the specified range [start, end] (inclusive).
     *
     * @param nums The array to modify.
     * @param start The starting index of the range (inclusive).
     * @param end The ending index of the range (inclusive).
     */
    private void reverseRange(int[] nums, int start, int end) {
        // Simple in-place two-pointer swap until the pointers meet or cross.
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}