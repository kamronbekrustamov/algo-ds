import java.util.HashMap;

class Solution {
    /**
     * Finds two numbers in an array that sum up to a specific target.
     *
     * This method uses a one-pass HashMap to solve the problem efficiently.
     * A HashMap is used to store the numbers we've seen so far and their indices.
     *
     * Algorithm:
     * 1. Create a HashMap to store numbers and their indices: {number: index}.
     * 2. Iterate through the input array.
     * 3. For each number, calculate its complement (target - number).
     * 4. Check if the complement exists in the HashMap. If it does, we have found the two numbers
     *    that sum up to the target. Return their indices.
     * 5. If the complement is not in the HashMap, add the current number and its index to the HashMap.
     *
     * @param nums An array of integers.
     * @param target The target sum.
     * @return An array of two integers representing the indices of the two numbers, or an empty array if no solution is found.
     *
     * Time Complexity: O(N), where N is the number of elements in the array, as we iterate through the array once.
     * Space Complexity: O(N) in the worst case, where we store all numbers in the HashMap.
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> seenMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seenMap.containsKey(complement)) {
                return new int[] { seenMap.get(complement), i };
            }
            seenMap.put(nums[i], i);
        }
        return new int[0];
    }
}
