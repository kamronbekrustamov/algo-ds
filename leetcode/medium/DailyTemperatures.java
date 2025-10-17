import java.util.Deque;
import java.util.ArrayDeque;

/**
 * Given an array of daily temperatures, returns an array where each element
 * is the number of days you have to wait after the current day to get a
 * warmer temperature. If there is no future day with a warmer temperature,
 * the result is 0 for that day.
 */
class Solution {
    /**
     * Calculates the number of days to wait for a warmer temperature using a monotonic stack.
     * The stack stores the indices of days in strictly decreasing order of temperature.
     *
     * @param temperatures An array representing the daily temperatures.
     * @return An array where ans[i] is the number of days until a warmer temperature.
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        // The result array, initialized to 0s (default for int), which is correct for days with no warmer future.
        int[] ans = new int[n];

        // Monotonic decreasing stack. Stores the INDEX of the day.
        // The temperature for the index 'i' can be retrieved using temperatures[i].
        // Using Integer for indices simplifies the stack operations compared to int[].
        Deque<Integer> stack = new ArrayDeque<>();

        // Iterate through each day, from left to right.
        for (int i = 0; i < n; i++) {
            int currentTemperature = temperatures[i];

            // Maintain the monotonic decreasing property:
            // While the stack is not empty AND the temperature at the index on top of the stack
            // is LESS than the current day's temperature (temperatures[i]), we've found the
            // *first* warmer day for the day at the stack's top.
            while (!stack.isEmpty() && temperatures[stack.peek()] < currentTemperature) {
                // Pop the index of the day for which we've found the answer.
                int previousIndex = stack.pop();

                // The wait time is the difference between the current index (i) and the previous index.
                ans[previousIndex] = i - previousIndex;
            }

            // Push the current day's index onto the stack.
            // It potentially needs a warmer day later in the future.
            stack.push(i);
        }

        // Days remaining in the stack have no warmer day to their right, so their
        // corresponding 'ans' value remains 0 (the default value), which is correct.
        return ans;
    }
}