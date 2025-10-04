import java.util.HashSet;
import java.util.Set;

class Solution {
    /**
     * Checks if an array contains any duplicate values.
     *
     * This solution uses a HashSet to achieve optimal time complexity. By leveraging
     * the properties of a hash set (which does not allow duplicate elements), we can
     * efficiently track the numbers we have already seen.
     *
     * Algorithm Breakdown:
     * 1. Initialize an empty HashSet called `seenNumbers`.
     * 2. Iterate through each number in the input array `nums`.
     * 3. For each number, attempt to add it to the `seenNumbers` set.
     * 4. The `add` method of a HashSet returns `false` if the element is already
     *    present in the set. If we encounter this, we have found a duplicate and
     *    can immediately return `true`.
     * 5. If the loop completes without the `add` method ever returning `false`,
     *    it means all elements were unique. We then return `false`.
     *
     * Time Complexity: O(n), where n is the number of elements in the array. This
     *                  is because adding and checking for existence in a HashSet
     *                  takes, on average, O(1) time.
     * Space Complexity: O(n) in the worst case, where all elements are unique and
     *                   must be stored in the HashSet.
     *
     * @param nums The input array of integers.
     * @return true if the array contains at least one duplicate, false otherwise.
     */
    public boolean containsDuplicate(int[] nums) {
        // 1. Initialize a HashSet to store the numbers we have seen so far.
        Set<Integer> seenNumbers = new HashSet<>();

        // 2. Iterate through each number in the input array.
        for (int num : nums) {
            // 3. Try to add the current number to the set.
            // 4. If set.add() returns false, it means the number is already in the set.
            if (!seenNumbers.add(num)) {
                // We have found a duplicate.
                return true;
            }
        }

        // 5. If the loop completes, no duplicates were found.
        return false;
    }
}
