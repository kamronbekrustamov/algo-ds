class Solution {
    /**
     * Removes all instances of a given value in-place from an array and returns the new length.
     *
     * This solution uses a two-pointer approach (a slow and a fast pointer) to
     * achieve this in a single pass with O(1) extra space.
     *
     * Algorithm:
     * 1. Initialize a `slow` pointer (`k`) to 0. This pointer will track the position
     *    where the next element that is NOT equal to `val` should be placed.
     * 2. Iterate through the array with a `fast` pointer (the `num` in the loop).
     * 3. If the current element (`num`) is NOT equal to `val`, it means this
     *    element should be kept.
     * 4. We copy the `num` to the `slow` pointer's position (`nums[k]`) and then
     *    increment the `slow` pointer.
     * 5. The final value of `k` is the new length of the modified array.
     *
     * @param nums The input array of integers.
     * @param val The value to be removed from the array.
     * @return The new length of the array after removing all occurrences of `val`.
     *
     * Time Complexity: O(n), as we iterate through the array once.
     * Space Complexity: O(1), as we modify the array in-place.
     */
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int num : nums) {
            if (num != val) {
                nums[k] = num;
                k++;
            }
        }
        return k;
    }
}
