/**
 * The Solution class provides a method to convert a string into a ZigZag pattern
 * based on a given number of rows, as seen in the "ZigZag Conversion" LeetCode problem.
 * The pattern resembles a 'Z' or a wave.
 */
class Solution {
    /**
     * Converts the given string 's' into a ZigZag pattern across 'numRows' rows.
     * * @param s The input string to be converted.
     * @param numRows The number of rows for the ZigZag pattern.
     * @return The converted string, read row by row.
     */
    public String convert(String s, int numRows) {
        // Base case: If there is only one row, the pattern is just the original string.
        // This also handles numRows >= s.length() implicitly.
        if (numRows == 1) {
            return s;
        }

        // 1. Initialization
        // Create an array of StringBuilder objects, one for each row.
        // This allows characters to be grouped by the row they belong to.
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currentRow = 0;      // Tracks the current row index (0 to numRows - 1).
        boolean goingDown = false; // Controls the direction of movement (down or up).

        // 2. Pattern Simulation: Iterate through each character of the input string
        for (char c : s.toCharArray()) {
            // Append the character to the StringBuilder corresponding to the currentRow.
            rows[currentRow].append(c);

            // Check if the traversal has hit the boundary (top or bottom row).
            // When a boundary is hit, the direction must be reversed.
            // The top row is index 0. The bottom row is index numRows - 1.
            if (currentRow == 0 || currentRow == numRows - 1) {
                // Reverse the direction: true -> false (up), false -> true (down).
                goingDown = !goingDown;
            }

            // Move to the next row based on the current direction.
            // If goingDown is true (moving down), increment currentRow.
            // If goingDown is false (moving up), decrement currentRow.
            // NOTE: The direction only flips *after* the character is placed
            // in the boundary row (0 or numRows - 1), but before the next character's placement.
            // The character placement loop starts with the direction flipped to DOWN (row 0 to 1).
            currentRow += goingDown ? 1 : -1;
        }

        // 3. Final Result Construction
        // Concatenate all the row StringBuilders in order (row 0, row 1, ...).
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        // Return the final converted string.
        return result.toString();
    }
}