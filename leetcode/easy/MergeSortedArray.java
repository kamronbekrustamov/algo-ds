/**
 * Solution for LeetCode problem "Merge Sorted Array".
 * This class provides a method to merge two sorted integer arrays into a single sorted array.
 */
class Solution {

    /**
     * Merges two sorted integer arrays, `nums1` and `nums2`, into `nums1`.
     * The array `nums1` has a length of `m + n`, where the first `m` elements are the ones to be merged,
     * and the last `n` elements are 0s (or any placeholder) and should be overwritten.
     * The array `nums2` has a length of `n`.
     *
     * This method uses a three-pointer approach, merging from the end of the arrays to avoid overwriting
     * elements in `nums1` that have not yet been moved.
     *
     * @param nums1 The first array, which has enough space to hold elements from both arrays.
     *              The first `m` elements are the ones to be merged.
     * @param m The number of elements in `nums1` that are to be merged.
     * @param nums2 The second array, containing `n` elements.
     * @param n The number of elements in `nums2`.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Initialize pointers:
        // p1 points to the last element of the initial part of nums1 (elements to be merged).
        int p1 = m - 1;
        // p2 points to the last element of nums2.
        int p2 = n - 1;
        // p_merged points to the last position in nums1 where a merged element will be placed.
        int p_merged = m + n - 1;

        // Iterate while there are elements to compare in both nums1 (initial part) and nums2.
        while (p1 >= 0 && p2 >= 0) {
            // Compare the elements at p1 and p2.
            if (nums1[p1] > nums2[p2]) {
                // If nums1[p1] is greater, place it at the current merged position.
                nums1[p_merged] = nums1[p1];
                p1--; // Move p1 to the left.
            } else {
                // If nums2[p2] is greater or equal, place it at the current merged position.
                nums1[p_merged] = nums2[p2];
                p2--; // Move p2 to the left.
            }
            p_merged--; // Move merged pointer to the left.
        }

        // If there are remaining elements in nums2 (meaning nums1's initial part was exhausted first),
        // copy them directly to the beginning of nums1.
        // No need to handle remaining elements in nums1 because they are already in their correct sorted positions.
        while (p2 >= 0) {
            nums1[p_merged] = nums2[p2];
            p2--;
            p_merged--;
        }
    }
}
