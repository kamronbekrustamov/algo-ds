import java.util.Arrays;

/**
 * Solution for the "Boats to Save People" problem.
 *
 * Problem: The i-th person has weight people[i], and each boat can carry a maximum weight of limit.
 * Each boat can carry at most two people at the same time, provided the sum of the
 * weight of those people is at most limit.
 *
 * Goal: Return the minimum number of boats to carry every given person.
 *
 * This class provides an efficient greedy algorithm to solve this problem.
 */
class Solution {

    /**
     * Calculates the minimum number of rescue boats required.
     *
     * The strategy is to use a two-pointer greedy approach after sorting the array.
     * We always try to pair the heaviest remaining person with the lightest remaining person.
     * This is optimal because if the heaviest person cannot be paired with the lightest,
     * they cannot be paired with anyone else.
     *
     * Time Complexity: O(N log N), dominated by the sorting step. The two-pointer scan is O(N).
     * Space Complexity: O(log N) on average for the sorting algorithm's stack space in Java.
     *
     * @param people An array of integers representing the weights of the people.
     * @param limit  An integer representing the maximum weight capacity of a single boat.
     * @return The minimum number of boats needed to rescue all people.
     */
    public int numRescueBoats(int[] people, int limit) {
        // Step 1: Sort the array of people's weights.
        // This allows us to efficiently pair the lightest and heaviest people using two pointers.
        Arrays.sort(people);

        // Initialize the boat count.
        int count = 0;

        // Step 2: Initialize two pointers.
        // 'left' points to the lightest person (start of the array).
        // 'right' points to the heaviest person (end of the array).
        int left = 0;
        int right = people.length - 1;

        // Step 3: Use a greedy approach to assign people to boats.
        // The loop continues as long as there are people left to assign (left pointer hasn't crossed right).
        while (left <= right) {
            // We will always need a boat for the heaviest remaining person (at 'right').
            count++;

            // Step 4: Try to pair the heaviest person with the lightest person.
            // Check if the lightest person (at 'left') can share the boat with the heaviest person (at 'right')
            // without exceeding the weight limit.
            if (people[left] + people[right] <= limit) {
                // If they can share a boat, both are now assigned.
                // Move the 'left' pointer to the next lightest person.
                left++;
            }

            // The heaviest person (at 'right') is always assigned to a boat (either alone or paired).
            // So, we always move the 'right' pointer to the next heaviest person.
            right--;
        }

        // Return the total number of boats used.
        return count;
    }
}