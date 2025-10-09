class Solution {
    /**
     * Calculates the median of two sorted arrays.
     *
     * This function implements the efficient O(log(min(m, n))) algorithm, which uses
     * binary search to find the correct partition in the smaller of the two arrays.
     * The goal is to partition the combined conceptual array into two equal halves
     * such that all elements in the left half are less than or equal to all elements
     * in the right half.
     *
     * Algorithm:
     * 1. Ensure `nums1` is the smaller array to optimize the binary search range.
     * 2. Calculate `m` (length of `nums1`), `n` (length of `nums2`), and `total_len` (`m + n`).
     * 3. `half_len` is the size of the combined left partition: `total_len / 2`.
     * 4. Perform binary search for the correct partition in `nums1` (the smaller array):
     *    a. Initialize `low = 0` and `high = m`.
     *    b. While `low <= high`:
     *       i. Calculate `partition1` (split index for `nums1`): `(low + high) / 2`.
     *       ii. Calculate `partition2` (split index for `nums2`): `half_len - partition1`.
     *       iii. Get the four key elements that border the partitions:
     *            - `max_left1`: largest element in the left part of `nums1`.
     *            - `min_right1`: smallest element in the right part of `nums1`.
     *            - `max_left2`: largest element in the left part of `nums2`.
     *            - `min_right2`: smallest element in the right part of `nums2`.
     *            Handle edge cases where `partition1` or `partition2` is 0 or `m`/`n`
     *            by using `Double.NEGATIVE_INFINITY` or `Double.POSITIVE_INFINITY`.
     *       iv. Check if the partition is correct: `max_left1 <= min_right2` AND `max_left2 <= min_right1`.
     *           - If correct:
     *             - If `total_len` is odd, the median is `Math.min(min_right1, min_right2)`.
     *             - If `total_len` is even, the median is `(Math.max(max_left1, max_left2) + Math.min(min_right1, min_right2)) / 2.0`.
     *           - Return the median.
     *       v. If `max_left1 > min_right2`, `partition1` is too large. Adjust `high = partition1 - 1`.
     *       vi. Else (`max_left2 > min_right1`), `partition1` is too small. Adjust `low = partition1 + 1`.
     *
     * @param nums1 The first sorted array.
     * @param nums2 The second sorted array.
     * @return The median of the two combined arrays.
     *
     * Time Complexity: O(log(min(m, n))), where m and n are the lengths of the two arrays.
     * Space Complexity: O(1).
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to optimize the binary search range.
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int total_len = m + n;
        int half_len = total_len / 2;

        int low = 0;
        int high = m;

        while (low <= high) {
            int partition1 = low + (high - low) / 2;
            int partition2 = half_len - partition1;

            double max_left1 = (partition1 == 0) ? Double.NEGATIVE_INFINITY : nums1[partition1 - 1];
            double min_right1 = (partition1 == m) ? Double.POSITIVE_INFINITY : nums1[partition1];

            double max_left2 = (partition2 == 0) ? Double.NEGATIVE_INFINITY : nums2[partition2 - 1];
            double min_right2 = (partition2 == n) ? Double.POSITIVE_INFINITY : nums2[partition2];

            if (max_left1 <= min_right2 && max_left2 <= min_right1) {
                if (total_len % 2 == 1) {
                    return Math.min(min_right1, min_right2);
                } else {
                    return (Math.max(max_left1, max_left2) + Math.min(min_right1, min_right2)) / 2.0;
                }
            } else if (max_left1 > min_right2) {
                high = partition1 - 1;
            } else {
                low = partition1 + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays are not sorted or invalid.");
    }
}
