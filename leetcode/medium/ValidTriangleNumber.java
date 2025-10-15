import java.util.Arrays;

/**
 * Finds the number of triplets (a, b, c) in an array that can form a valid triangle.
 * A triplet (a, b, c) forms a valid triangle if the sum of any two sides is greater than the third side:
 * a + b > c, a + c > b, and b + c > a.
 * * After sorting, if we fix the largest side c (nums[i]), the conditions simplify to:
 * nums[left] + nums[right] > nums[i], where left < right < i.
 * The other two conditions (nums[left] + nums[i] > nums[right] and nums[right] + nums[i] > nums[left])
 * are automatically satisfied since nums[i] is the largest side.
 */
class Solution {
    /**
     * Counts the number of triplets in the array that can form a valid triangle.
     * * @param nums The array of side lengths.
     * @return The total count of valid triangle triplets.
     */
    public int triangleNumber(int[] nums) {
        // Sort the array. This is crucial for the two-pointer approach and 
        // simplifies the triangle inequality checks. Time: O(n log n).
        Arrays.sort(nums); 

        int count = 0;
        int n = nums.length;
        
        // Iterate through the array from the end, fixing the longest side 'c'.
        // We only need to check up to index 2 because a triangle needs at least 3 sides.
        // This loop fixes nums[i] as the largest side.
        // Time Complexity: O(n^2) due to the nested loops.
        for (int i = n - 1; i > 1; i--) {
            int left = 0;      // Pointer for the smallest side 'a'
            int right = i - 1; // Pointer for the middle side 'b'

            // Use the two-pointer technique to find valid pairs (a, b) for the fixed 'c'.
            while (left < right) {
                // Check the primary triangle inequality: a + b > c
                if (nums[left] + nums[right] > nums[i]) {
                    // Since the array is sorted, if nums[left] + nums[right] > nums[i],
                    // then for all elements 'k' such that left <= k < right,
                    // nums[k] + nums[right] will also be greater than nums[i].
                    // The number of such valid pairs is (right - left).
                    count += right - left;
                    
                    // Move 'right' inward to try a smaller 'b' with the current 'a',
                    // as we've already counted all possible 'a' values for the current 'b'.
                    right--;
                }
                else {
                    // If nums[left] + nums[right] <= nums[i], the sum is too small.
                    // We need a larger 'a', so move 'left' inward.
                    left++;
                }
            }
        }
        return count;
    }
}