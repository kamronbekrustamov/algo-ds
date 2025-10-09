class Solution {
    /**
     * Determines if a string can be a palindrome by deleting at most one character.
     *
     * This solution uses a two-pointer approach. We scan the string from both
     * ends inward. If we find a pair of characters that do not match, we are
     * faced with two possibilities to make the string a palindrome:
     * 1. Delete the character at the left pointer.
     * 2. Delete the character at the right pointer.
     *
     * We then check if the remaining substring is a palindrome in either of these
     * two cases. If at least one of them is a palindrome, the original string
     * can be considered a valid palindrome II. If the initial scan completes
     * without any mismatches, the string is already a palindrome.
     *
     * @param s The input string.
     * @return True if the string can be a palindrome after at most one deletion,
     *         False otherwise.
     *
     * Time Complexity: O(N), where N is the length of the string.
     * Space Complexity: O(1).
     */
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindromeRange(s, left + 1, right) || isPalindromeRange(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean isPalindromeRange(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
