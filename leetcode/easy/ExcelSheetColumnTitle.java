class Solution {
    /**
     * Converts a given positive integer (column number) to its corresponding
     * Excel column title.
     *
     * This problem is essentially a base-26 conversion, but with a crucial
     * difference: it's a 1-indexed system (A=1, B=2, ..., Z=26) rather than
     * a 0-indexed system (where 0 would typically map to 'A').
     *
     * Algorithm Breakdown:
     * 1. Initialize a `StringBuilder` to efficiently build the result string.
     * 2. Loop as long as `columnNumber` is greater than 0.
     * 3. In each iteration:
     *    a. Adjust `columnNumber` by subtracting 1. This transforms the 1-indexed
     *       system (1-26) into a 0-indexed system (0-25), which directly maps
     *       to character ASCII values ('A' to 'Z').
     *    b. Calculate the `index` of the current character by taking the modulo 26
     *       of the adjusted `columnNumber`.
     *    c. Convert this `index` back to its corresponding character ('A' + index)
     *       and append it to the `StringBuilder`.
     *    d. Update `columnNumber` by dividing the adjusted `columnNumber` by 26
     *       for the next iteration.
     * 4. After the loop, the `StringBuilder` contains the characters in reverse
     *    order (from least significant to most significant). Reverse it and
     *    convert it to a `String` to get the final Excel column title.
     *
     * Time Complexity: O(log_26 n), where n is the `columnNumber`. This is because
     *                  the `columnNumber` is divided by 26 in each iteration.
     * Space Complexity: O(log_26 n) for the `StringBuilder` to store the result.
     *
     * @param columnNumber The positive integer representing the Excel column number.
     * @return The corresponding Excel column title as a String.
     */
    public String convertToTitle(int columnNumber) {
        // Use StringBuilder for efficient string construction.
        StringBuilder resultBuilder = new StringBuilder();
        
        // Loop until the columnNumber becomes 0.
        while (columnNumber > 0) {
            // Step 3a: Adjust columnNumber by subtracting 1 to convert from 1-indexed (1-26)
            // to 0-indexed (0-25) for modulo and character mapping.
            columnNumber--;
            
            // Step 3b: Calculate the index of the current character (0-25).
            int index = columnNumber % 26;
            
            // Step 3c: Convert the index to its corresponding character ('A' + index)
            // and append it to the result.
            resultBuilder.append((char)('A' + index));
            
            // Step 3d: Update columnNumber for the next iteration by dividing by 26.
            columnNumber /= 26;
        }
        
        // Step 4: The characters were appended in reverse order, so reverse the StringBuilder
        // and convert it to a String.
        return resultBuilder.reverse().toString();
    }
}
