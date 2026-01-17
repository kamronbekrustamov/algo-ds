import java.util.ArrayList;
import java.util.List;

/**
 * Problem: Restore IP Addresses
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * A valid IP address consists of exactly four octets (0-255) separated by dots, with no leading zeros 
 * (unless the segment is exactly "0").
 */
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        int n = s.length();
        
        // An IPv4 address has between 4 and 12 digits.
        if (n < 4 || n > 12) return result;
        
        backtrack(s, 0, 0, new StringBuilder(), result);
        return result;
    }

    /**
     * @param s          The original input string.
     * @param start      The current index in the input string.
     * @param octetCount The number of octets already placed (0 to 4).
     * @param current    The current IP string being built.
     * @param result     The list to store valid IP addresses.
     */
    private void backtrack(String s, int start, int octetCount, StringBuilder current, List<String> result) {
        int remainingChars = s.length() - start;
        int remainingOctets = 4 - octetCount;

        // Pruning: Check if the remaining characters can fit into the remaining octets.
        // Each octet needs 1-3 characters.
        if (remainingChars < remainingOctets || remainingChars > remainingOctets * 3) {
            return;
        }

        // Base case: If we have 4 octets and have used all characters.
        if (octetCount == 4) {
            if (start == s.length()) {
                // Remove the trailing dot before adding to results.
                result.add(current.substring(0, current.length() - 1));
            }
            return;
        }

        int prevLen = current.length();

        // Try segments of length 1, 2, and 3.
        for (int len = 1; len <= 3 && start + len <= s.length(); len++) {
            String segment = s.substring(start, start + len);
            
            if (isValid(segment)) {
                current.append(segment).append('.');
                backtrack(s, start + len, octetCount + 1, current, result);
                current.setLength(prevLen); // Backtrack: reset StringBuilder length
            }
        }
    }

    /**
     * Helper to validate an IP segment (0-255).
     * Rules: No leading zeros unless segment is "0", and value <= 255.
     */
    private boolean isValid(String segment) {
        if (segment.length() > 1 && segment.startsWith("0")) return false;
        int val = Integer.parseInt(segment);
        return val <= 255;
    }
}