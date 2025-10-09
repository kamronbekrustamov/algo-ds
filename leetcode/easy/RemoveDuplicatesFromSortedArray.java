class Solution {
    /**
     * Removes duplicates from a sorted array in-place.
     * The function modifies the input array `nums` such that each unique element
     * appears only once. The relative order of the elements should be kept the same.
     * The method returns the new length of the array after removing duplicates.
     *
     * This solution uses a two-pointer approach:
     * - A `slow` pointer (`slow`) keeps track of the position where the next unique
     *   element should be placed.
     * - A `fast` pointer (implicit in the `for` loop, `num`) iterates through the array.
     *
     * Algorithm:
     * 1. Initialize `slow` pointer to 0.
     * 2. Iterate through each `num` in the `nums` array.
     * 3. If `slow` is 0 (first element) or the current `num` is different from the
     *    element at `nums[slow - 1]` (the last unique element found):
     *    a. Place `num` at `nums[slow]`.
     *    b. Increment `slow`.
     * 4. The final value of `slow` will be the count of unique elements, which is
     *    also the new length of the array.
     *
     * @param nums The input array of integers, sorted in non-decreasing order.
     * @return The new length of the array after removing duplicates.
     *
     * Time Complexity: O(N), where N is the length of the array, as we iterate through it once.
     * Space Complexity: O(1), as the modification is done in-place.
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int num : nums) {
            if (slow < 1 || num != nums[slow - 1]) {
                nums[slow] = num;
                slow++;
            }
        }
        return slow;
    }
}
