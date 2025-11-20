class Solution {
    /**
     * Finds the minimum element in a sorted array that has been rotated at an unknown pivot.
     * The array is guaranteed to contain unique elements.
     *
     * This method uses an iterative Binary Search approach to achieve O(log n) time complexity.
     *
     * @param nums The integer array, sorted and possibly rotated.
     * @return The minimum element in the array.
     */
    public int findMin(int[] nums) {
        // Handle edge case for an empty or null array (though problem constraints often ensure non-empty)
        if (nums == null || nums.length == 0) {
            // Depending on strict problem constraints, this could throw an exception
            // or return a sentinel value. Assuming non-empty for standard LeetCode problems.
            throw new IllegalArgumentException("Array must not be null or empty.");
        }

        int low = 0;
        int high = nums.length - 1;

        // If the array is not rotated (i.e., it's fully sorted), the minimum is at the start.
        // This is a small optimization for the non-rotated case.
        if (nums[low] <= nums[high]) {
            return nums[low];
        }

        // Standard Binary Search loop. The loop terminates when low == high,
        // and 'low' (or 'high') points to the minimum element.
        while (low < high) {
            // Calculate mid-point to avoid potential integer overflow (robust calculation)
            int mid = low + (high - low) / 2;

            // Case 1: nums[mid] is less than nums[high].
            // This means the right half (mid to high) is sorted, and the minimum element
            // must be in the left half OR at nums[mid] itself.
            // We can safely discard the elements from (mid+1) to high, and set high = mid.
            if (nums[mid] < nums[high]) {
                high = mid; // Potential minimum is nums[mid]
            }
            // Case 2: nums[mid] is greater than nums[high].
            // This means the pivot (and thus the minimum element) must be in the right half
            // (from mid + 1 to high). The left half (low to mid) is sorted and does not
            // contain the minimum.
            else { // nums[mid] > nums[high]
                low = mid + 1; // Minimum must be in the range [mid + 1, high]
            }
            // Note: If nums[mid] == nums[high], this case only occurs in arrays with duplicates,
            // which this problem typically excludes. For unique elements, we only need to compare
            // nums[mid] with one of the ends.
        }

        // When the loop terminates, low == high, and this index is the index of the minimum element.
        return nums[low];
    }
}