class Solution {
    /**
     * Converts an integer to its Roman numeral representation.
     * The method uses a greedy approach with pre-defined arrays for
     * values and symbols, covering all necessary Roman numeral combinations
     * including subtractive notation (e.g., 4=IV, 9=IX, 40=XL, etc.).
     *
     * Constraints: 1 <= num <= 3999
     *
     * @param num The integer to be converted (e.g., 3, 58, 1994).
     * @return The Roman numeral string (e.g., "III", "LVIII", "MCMXCIV").
     */
    public String intToRoman(int num) {
        // Pre-defined arrays for values and corresponding Roman symbols,
        // ordered from largest to smallest. This ordering is crucial for the greedy approach.
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        // Use a StringBuilder for efficient string concatenation.
        StringBuilder result = new StringBuilder();

        // Iterate through the values and symbols arrays.
        for (int i = 0; i < values.length && num > 0; i++) {
            int value = values[i];
            String symbol = symbols[i];

            // Apply the greedy strategy:
            // Repeatedly append the symbol and subtract the value
            // as long as the current number is greater than or equal to the value.
            while (num >= value) {
                result.append(symbol);
                num -= value;
            }
        }

        return result.toString();
    }
}