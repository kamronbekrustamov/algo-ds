import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    /**
     * Decodes a string encoded using the rule k[encoded_string], where k is a positive integer 
     * and encoded_string is the string to be repeated k times.
     * * This method uses two stacks:
     * 1. numStack: Stores the repetition counts (k).
     * 2. resStack: Stores the string components accumulated before a '[' is encountered.
     * * Time Complexity: O(Max(k) * n), where n is the length of the string and Max(k) is the 
     * maximum repetition number. This is because each character is processed once, but string 
     * concatenation inside the ']' branch can happen k times.
     * * Space Complexity: O(n), where n is the length of the string, due to the space used by the stacks.
     *
     * @param s The encoded string.
     * @return The fully decoded string.
     */
    public String decodeString(String s) {
        // Stack to store the repetition counts (k)
        Deque<Integer> numStack = new ArrayDeque<>();
        
        // Stack to store the partial decoded strings accumulated before the current '['
        Deque<StringBuilder> resStack = new ArrayDeque<>();
        
        // Current repetition number being parsed
        int num = 0;
        
        // Current string segment being built
        StringBuilder currentString = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // 1. Build the number 'k' (can be multiple digits)
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                // 2. Encountered a new segment:
                
                // Push the current repetition count onto the num stack.
                numStack.push(num);
                
                // Push the currently built string (which is complete for the previous level)
                // onto the result stack, starting a new segment.
                resStack.push(currentString);
                
                // Reset the number and the string builder for the new segment.
                num = 0;
                currentString = new StringBuilder();
            } else if (c == ']') {
                // 3. Encountered end of segment, time to decode:
                
                // Pop the repetition count k
                int k = numStack.pop();
                
                // Pop the string from the previous level (to append the decoded sequence to)
                StringBuilder prevString = resStack.pop();
                
                // Append the current decoded string (currentString) k times to prevString
                prevString.append(currentString.toString().repeat(k));
                
                // The newly decoded string segment becomes the current string for the next iteration/level
                currentString = prevString;

            } else {
                // 4. Regular characters: just append to the current string segment
                currentString.append(c);
            }
        }

        // The final decoded string is what remains in the currentString builder.
        return currentString.toString();
    }
}