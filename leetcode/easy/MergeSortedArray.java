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
        int pMerged = m + n - 1;

        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[pMerged] = nums1[p1];
                p1--;
            } else {
                nums1[pMerged] = nums2[p2];
                p2--;
            }
            pMerged--;
        }

        while (p2 >= 0) {
            nums1[pMerged] = nums2[p2];
            p2--;
            pMerged--;
        }
    }
}
