import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * Finds the starting and ending positions of "large groups" in a string.
     * A large group is defined as a consecutive sequence of the same character
     * with a length of 3 or more.
     *
     * This solution uses a single pass with a `start_index` to track the beginning
     * of the current group. When a group ends (either due to a character change
     * or reaching the end of the string), it checks if the group is large enough
     * and adds its positions to the result list.
     *
     * Algorithm:
     * 1. Initialize an empty list `result` to store the positions of large groups.
     * 2. Initialize `start_index` to 0, marking the beginning of the current group.
     * 3. Iterate through the string from the second character up to and including
     *    an imaginary character after the last character (by iterating `i` from 1 to `s.length()`).
     *    This allows for proper handling of the last group.
     * 4. Inside the loop, check if the current character `s.charAt(i)` is different
     *    from the previous character `s.charAt(i-1)`, or if `i` has reached the end of the string.
     *    This condition signifies the end of a group.
     * 5. If a group has ended, calculate its length (`i - start_index`).
     * 6. If the length is 3 or more, add `[start_index, i - 1]` to the `result` list.
     * 7. Update `start_index` to `i` to mark the beginning of the next group.
     * 8. Return the `result` list.
     *
     * @param s The input string.
     * @return A list of lists, where each inner list contains the start and end
     *         indices of a large group.
     *
     * Time Complexity: O(N), where N is the length of the string, as we iterate through it once.
     * Space Complexity: O(N) in the worst case, if all characters form large groups.
     */
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList<>();
        if (s == null || s.length() < 3) {
            return result;
        }

        int startIndex = 0;
        for (int i = 1; i <= s.length(); i++) {
            if (i == s.length() || s.charAt(i) != s.charAt(i - 1)) {
                if (i - startIndex >= 3) {
                    List<Integer> group = new ArrayList<>();
                    group.add(startIndex);
                    group.add(i - 1);
                    result.add(group);
                }
                startIndex = i;
            }
        }
        return result;
    }
}
