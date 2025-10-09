class Solution {
    /**
     * Checks if two strings are anagrams of each other.
     *
     * An anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
     * typically using all the original letters exactly once.
     *
     * This method uses a character frequency map (an array of 26 integers for the English alphabet)
     * to determine if two strings are anagrams.
     *
     * Algorithm:
     * 1. Check if the lengths of the two strings are equal. If not, they cannot be anagrams.
     * 2. Create an integer array of size 26 to store the frequency of each character.
     * 3. Iterate through the first string and increment the count for each character in the frequency map.
     * 4. Iterate through the second string and decrement the count for each character.
     * 5. Finally, iterate through the frequency map. If any count is not zero, the strings are not anagrams.
     * 6. If all counts are zero, the strings are anagrams.
     *
     * @param s The first string.
     * @param t The second string.
     * @return True if the strings are anagrams, false otherwise.
     *
     * Time Complexity: O(N), where N is the length of the strings, as we iterate through them once.
     * Space Complexity: O(1), as the size of the character map is constant (26).
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] charMap = new int[26];
        for (char c : s.toCharArray()) {
            charMap[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            charMap[c - 'a']--;
        }
        for (int count : charMap) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
