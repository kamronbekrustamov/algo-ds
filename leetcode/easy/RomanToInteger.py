
class Solution:
    def romanToInt(self, s: str) -> int:
        """
        Converts a Roman numeral string to an integer.

        This solution iterates through the Roman numeral string from right to left.
        This approach simplifies handling the subtractive cases (e.g., IV, IX, XL).

        Algorithm:
        1. Define a map of Roman numerals to their integer values.
        2. Initialize `result` to 0 and `prev_value` to 0.
        3. Iterate through the input string `s` in reverse order.
        4. For each character, get its integer `current_value`.
        5. Compare `current_value` with the value of the character to its right (`prev_value`):
           - If `current_value` is less than `prev_value` (e.g., 'I' before 'V'),
             it's a subtractive case, so we subtract `current_value` from the result.
           - Otherwise, we add `current_value` to the result.
        6. Update `prev_value` to `current_value` for the next iteration.

        Example: "MCMXCIV"
        - V: 5. Add 5. result=5.
        - I: 1. 1 < 5, subtract 1. result=4.
        - C: 100. Add 100. result=104.
        - X: 10. 10 < 100, subtract 10. result=94.
        - M: 1000. Add 1000. result=1094.
        - C: 100. 100 < 1000, subtract 100. result=994.
        - M: 1000. Add 1000. result=1994.
        """
        roman_map = {"I": 1, "V": 5, "X": 10, "L": 50, "C": 100, "D": 500, "M": 1000}
        result = 0
        prev_value = 0
        for char in reversed(s):
            current_value = roman_map[char]
            result += current_value if current_value >= prev_value else -current_value
            prev_value = current_value
        return result
