import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    /**
     * Checks if a string has valid parentheses.
     *
     * This method uses a stack (implemented with ArrayDeque) to check for valid parentheses.
     * When an opening bracket is encountered, the corresponding closing bracket is pushed onto the stack.
     * When a closing bracket is encountered, the stack is popped and checked for a match.
     * If the stack is empty when a closing bracket is encountered, or the popped bracket doesn't match,
     * the string is invalid.
     * Finally, if the stack is empty at the end, the string is valid.
     *
     * Time Complexity: O(N), where N is the length of the string, because we iterate through the string once.
     * Space Complexity: O(N) in the worst case, where the string consists of only opening brackets.
     *
     * @param s The input string.
     * @return True if the string has valid parentheses, false otherwise.
     */
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}
