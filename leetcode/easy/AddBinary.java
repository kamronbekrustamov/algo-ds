class Solution {
    /**
     * Adds two binary strings and returns their sum as a binary string.
     *
     * This solution simulates the process of manual binary addition, starting
     * from the least significant bit (the rightmost characters of the strings).
     *
     * Algorithm Breakdown:
     * 1. Initialize a StringBuilder to store the result and a `carry` variable to 0.
     * 2. Use two pointers, `i` and `j`, to traverse the strings `a` and `b` from
     *    right to left.
     * 3. Loop as long as there are digits in either string to process, or if there
     *    is a remaining carry.
     * 4. In each iteration, calculate the `sum` of the digits at the current
     *    pointers (if they are valid) and the `carry`.
     * 5. The next digit of the result is `sum % 2`. This is appended to our
     *    StringBuilder.
     * 6. The new `carry` for the next iteration is `sum / 2`.
     * 7. Since we built the result string from right to left, it's in reverse
     *    order. Finally, reverse the StringBuilder and convert it to a string.
     *
     * Time Complexity: O(max(N, M)), where N and M are the lengths of the two strings.
     * Space Complexity: O(max(N, M)) for the StringBuilder.
     *
     * @param a The first binary string.
     * @param b The second binary string.
     * @return The sum of a and b as a binary string.
     */
    public String addBinary(String a, String b) {
        // StringBuilder is efficient for string concatenation in a loop.
        StringBuilder resultBuilder = new StringBuilder();
        
        // Pointers for traversing the strings from right to left.
        int i = a.length() - 1;
        int j = b.length() - 1;
        
        // Carry for the addition.
        int carry = 0;
        
        // Loop until both strings are fully processed and there's no carry left.
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            
            // Add the digit from string 'a' if the pointer is valid.
            if (i >= 0) {
                // Convert char '0' or '1' to integer 0 or 1.
                sum += a.charAt(i) - '0';
                i--;
            }
            
            // Add the digit from string 'b' if the pointer is valid.
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            
            // The binary digit to append is the remainder of the sum divided by 2.
            resultBuilder.append(sum % 2);
            
            // The new carry is the quotient of the sum divided by 2.
            carry = sum / 2;
        }
        
        // The result was built in reverse order, so we reverse it and return as a string.
        return resultBuilder.reverse().toString();
    }
}