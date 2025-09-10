class Solution:
    def intToRoman(self, num: int) -> str:
        # This list maps integer values to their Roman numeral symbols.
        # It's ordered from largest to smallest, including subtractive forms (e.g., 900 is "CM").
        # This ordering is crucial for the greedy approach to work correctly.
        val_sym = [
            (1000, "M"), (900, "CM"), (500, "D"), (400, "CD"), (100, "C"),
            (90, "XC"), (50, "L"), (40, "XL"), (10, "X"),
            (9, "IX"), (5, "V"), (4, "IV"), (1, "I")
        ]

        result = []

        # Greedily build the Roman numeral string.
        # For each value, subtract it from the number as many times as possible.
        for val, sym in val_sym:
            # Check how many times the current value fits into the remaining number.
            count = num // val
            if count > 0:
                # Append the corresponding symbol 'count' times.
                result.append(sym * count)
                # Update the number.
                num %= val

            # Early exit if the number has been fully converted.
            if num == 0:
                break

        return "".join(result)

        