import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Converts a Roman numeral string to an integer.
     *
     * This solution iterates through the Roman numeral string from right to left.
     * This approach simplifies handling the subtractive cases (e.g., IV, IX, XL).
     *
     * Algorithm:
     * 1. Define a map of Roman numerals to their integer values.
     * 2. Initialize `result` to 0 and `prev_value` to 0.
     * 3. Iterate through the input string `s` in reverse order.
     * 4. For each character, get its integer `current_value` from the map.
     * 5. Compare `current_value` with the value of the character to its right (`prev_value`):
     *    - If `current_value` is less than `prev_value` (e.g., 'I' before 'V'),
     *      it's a subtractive case, so we subtract `current_value` from the result.
     *    - Otherwise, we add `current_value` to the result.
     * 6. Update `prev_value` to `current_value` for the next iteration.
     *
     * Example: "MCMXCIV"
     * - V: 5. Add 5. result=5.
     * - I: 1. 1 < 5, subtract 1. result=4.
     * - C: 100. Add 100. result=104.
     * - X: 10. 10 < 100, subtract 10. result=94.
     * - M: 1000. Add 1000. result=1094.
     * - C: 100. 100 < 1000, subtract 100. result=994.
     * - M: 1000. Add 1000. result=1994.
     *
     * @param s The Roman numeral string.
     * @return The integer representation of the Roman numeral.
     *
     * Time Complexity: O(N), where N is the length of the Roman numeral string, as we iterate through it once.
     * Space Complexity: O(1) for the map (fixed size) and a few variables.
     */
    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            int currentValue = romanMap.get(c);

            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            prevValue = currentValue;
        }

        return result;
    }
}
