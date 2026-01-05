class Solution {
    /**
     * Reorganizes the string such that no two adjacent characters are the same.
     * This is possible only if the frequency of the most frequent character 
     * is not greater than (length + 1) / 2.
     *
     * @param s The input string consisting of lowercase English letters.
     * @return A reorganized string, or an empty string if not possible.
     */
    public String reorganizeString(String s) {
        int length = s.length();
        // 1. Count frequencies and find the most frequent character.
        // Array to store frequency of each character ('a' to 'z').
        int[] frequencyMap = new int[26];

        int highestFrequencyCount = 0;
        char highestFrequencyChar = 'a'; // Initialize to a default character

        for (char c : s.toCharArray()) {
            int index = c - 'a';
            frequencyMap[index]++;

            if (frequencyMap[index] > highestFrequencyCount) {
                highestFrequencyCount = frequencyMap[index];
                highestFrequencyChar = c;
            }
        }

        // 2. Check for the impossible case.
        // If the max frequency is greater than the allowed limit (ceil(length / 2)),
        // it's impossible to reorganize, so return "".
        if (highestFrequencyCount > (length + 1) / 2) {
            return "";
        }

        // 3. Place the most frequent character first (Greedy approach).
        char[] result = new char[length];
        
        // Start filling at index 0, then 2, 4, ... (even indices).
        int index = 0; 
        
        // Place the most frequent character into the even positions.
        while (highestFrequencyCount > 0) {
            result[index] = highestFrequencyChar;
            index += 2;
            
            // Wrap around to odd positions (1, 3, 5, ...) if even positions are exhausted.
            if (index >= length) {
                index = 1;
            }
            highestFrequencyCount--;
        }

        // 4. Place the remaining characters.
        // Iterate through all character frequencies.
        for (int i = 0; i < 26; i++) {
            int currentFrequency = frequencyMap[i];
            char currentChar = (char) (i + 'a');

            // The highestFrequencyChar has already been placed, so we skip it 
            // if we didn't track the remaining frequency in the map.
            // A cleaner approach is to use the actual remaining frequency:
            if (currentChar == highestFrequencyChar) {
                // We've already placed the required number of these characters. 
                // Any remaining frequency in the map should be 0 now 
                // (which is true if the loop condition `currentFrequency > 0` is checked 
                // against the *original* frequency after the high-freq char is placed,
                // or simply by skipping it). We skip it here to avoid re-placing.
                continue; 
            }
            
            // Place the remaining characters into the next available slot (which will be odd,
            // or even if index wrapped around)
            while (currentFrequency > 0) {
                result[index] = currentChar;
                index += 2;
                
                // Wrap around to the start of the odd positions if needed.
                if (index >= length) {
                    index = 1;
                }
                currentFrequency--;
            }
        }

        return String.valueOf(result);
    }
}