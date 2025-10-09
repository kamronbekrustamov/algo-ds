import java.util.Arrays;

class Solution {
    /**
     * Calculates the shortest distance from each character in a string `s` to a given character `c`.
     *
     * This solution uses a two-pass approach:
     * 1. First pass (left to right): Calculates the distance to the nearest `c` on the left for each character.
     * 2. Second pass (right to left): Updates the distances with the distance to the nearest `c` on the right,
     *    taking the minimum of the two calculated distances.
     *
     * Algorithm:
     * 1. Initialize an `ans` array of the same length as `s`, filled with a large value (e.g., `n`).
     * 2. **Left to right pass:**
     *    a. Initialize `pos` to a very small value (e.g., `-n`) to represent that no `c` has been seen yet.
     *    b. Iterate from `i = 0` to `n - 1`:
     *       i. If `s.charAt(i)` is `c`, update `pos = i`.
     *       ii. Set `ans[i] = i - pos` (distance to the nearest `c` on the left).
     * 3. **Right to left pass:**
     *    a. Initialize `pos` to a very large value (e.g., `2 * n`) to represent that no `c` has been seen yet from the right.
     *    b. Iterate from `i = n - 1` down to `0`:
     *       i. If `s.charAt(i)` is `c`, update `pos = i`.
     *       ii. Update `ans[i] = Math.min(ans[i], pos - i)` (take the minimum of the left distance and right distance).
     * 4. Return the `ans` array.
     *
     * @param s The input string.
     * @param c The character to find the shortest distance to.
     * @return An array of integers representing the shortest distance from each character in `s` to `c`.
     *
     * Time Complexity: O(N), where N is the length of the string, due to two passes.
     * Space Complexity: O(N) for the `ans` array.
     */
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];
        Arrays.fill(ans, n);

        int pos = -n;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                pos = i;
            }
            ans[i] = i - pos;
        }

        pos = 2 * n;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                pos = i;
            }
            ans[i] = Math.min(ans[i], pos - i);
        }

        return ans;
    }
}
