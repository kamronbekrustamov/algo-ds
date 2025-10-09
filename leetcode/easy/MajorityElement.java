/**
 * Solution for LeetCode problem "Majority Element".
 * This class provides a method to find the majority element in an array.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * It is assumed that the array is non-empty and the majority element always exists in the array.
 */
class Solution {

    /**
     * Finds the majority element in the given array using Boyer-Moore Voting Algorithm.
     *
     * Boyer-Moore Voting Algorithm works on the principle that if an element is the majority element,
     * its count will remain positive even after canceling out with all other non-majority elements.
     *
     * The algorithm maintains a `candidate` for the majority element and a `count`.
     * - Initialize `count = 0` and `candidate = null`.
     * - Iterate through the array `nums`:
     *   - If `count` is 0, set the current element as the new `candidate`.
     *   - If the current element is the same as `candidate`, increment `count`.
     *   - If the current element is different from `candidate`, decrement `count`.
     * - After iterating through the entire array, the `candidate` will be the majority element.
     *
     * @param nums The array of integers.
     * @return The majority element in the array.
     */
    public int majorityElement(int[] nums) {
        // Initialize candidate and count for Boyer-Moore Voting Algorithm.
        Integer candidate = null;
        int count = 0;

        // Iterate through each number in the array.
        for (int num : nums) {
            // If count is 0, set the current number as the new candidate.
            // This means the previous candidate's votes have been cancelled out.
            if (count == 0) {
                candidate = num;
            }

            // If the current number is the same as the candidate, increment count.
            // Otherwise, decrement count.
            count += (num == candidate) ? 1 : -1;
        }

        // The candidate at the end of the iteration will be the majority element.
        // The problem statement guarantees that a majority element always exists.
        return candidate;
    }
}
