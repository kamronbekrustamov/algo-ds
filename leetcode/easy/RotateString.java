class Solution {
    /**
     * Checks if string `goal` is a rotation of string `s`.
     *
     * A string `goal` is considered a rotation of `s` if and only if:
     * 1. Both strings `s` and `goal` have the same length.
     * 2. `goal` is a substring of `s` concatenated with itself (`s + s`).
     *
     * This approach leverages the property that if `goal` is a rotation of `s`,
     * then `goal` must appear exactly once within the string `s + s`.
     *
     * Algorithm:
     * 1. First, check if the lengths of `s` and `goal` are equal. If they are not,
     *    `goal` cannot be a rotation of `s`, so return `false`.
     * 2. Concatenate `s` with itself to form `s + s`.
     * 3. Check if `goal` is a substring of `s + s`. If it is, return `true`; otherwise, return `false`.
     *
     * @param s The original string.
     * @param goal The string to check if it's a rotation of `s`.
     * @return True if `goal` is a rotation of `s`, false otherwise.
     *
     * Time Complexity: O(N^2) in the worst case, where N is the length of the string `s`.
     *                  This is because `s + s` creates a string of length 2N, and `contains`
     *                  (which typically uses a naive substring search) can take O(N*M) time,
     *                  where M is the length of the substring (`goal`). In this case, M is also N.
     * Space Complexity: O(N) to store the concatenated string `s + s`.
     */
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        return (s + s).contains(goal);
    }
}
