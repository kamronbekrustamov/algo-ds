class Solution {
    /**
     * Checks if s2 contains a permutation of s1.
     * Uses a Sliding Window approach with an optimized frequency match counter.
     *
     * @param s1 The substring to search for (permutation).
     * @param s2 The main string to search within.
     * @return true if a permutation of s1 exists in s2, false otherwise.
     */
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        if (len1 > len2) {
            return false;
        }

        // Frequency array stores the count difference between s1 and the current window in s2.
        // Positive value: s1 has more of this char.
        // Negative value: current window has more of this char.
        // Zero: counts match perfectly.
        int[] countDiff = new int[26];

        // Initialize the window with the first len1 characters
        for (int i = 0; i < len1; i++) {
            countDiff[s1.charAt(i) - 'a']++;
            countDiff[s2.charAt(i) - 'a']--;
        }

        // Count how many characters currently have a perfect match (diff is 0)
        int matches = 0;
        for (int i = 0; i < 26; i++) {
            if (countDiff[i] == 0) {
                matches++;
            }
        }
        
        // If all 26 characters match immediately, return true
        if (matches == 26) {
            return true;
        }

        // Slide the window across s2
        // i represents the index of the character ENTERING the window
        for (int i = len1; i < len2; i++) {
            int rightCharIdx = s2.charAt(i) - 'a';        // New char entering window
            int leftCharIdx = s2.charAt(i - len1) - 'a';  // Old char leaving window

            // Optimization: If entering and leaving chars are the same, the balance doesn't change.
            if (rightCharIdx == leftCharIdx) {
                continue;
            }

            // Update for the entering character (s2 adds to the window, so we decrement the balance)
            if (countDiff[rightCharIdx] == 0) {
                matches--; // It was balanced, now it's not
            }
            countDiff[rightCharIdx]--;
            if (countDiff[rightCharIdx] == 0) {
                matches++; // It is now balanced
            }

            // Update for the leaving character (s2 removes from window, so we increment the balance)
            if (countDiff[leftCharIdx] == 0) {
                matches--; // It was balanced, now it's not
            }
            countDiff[leftCharIdx]++;
            if (countDiff[leftCharIdx] == 0) {
                matches++; // It is now balanced
            }

            if (matches == 26) {
                return true;
            }
        }

        return false;
    }
}