import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The Solution class provides a method to group anagrams from a given array of strings.
 * Two strings are anagrams if one can be formed from the other by rearranging its letters.
 * The core idea is to use the sorted version of each string as a unique key for its anagram group.
 */
class Solution {
    /**
     * Groups strings from the input array into lists of anagrams.
     *
     * @param strs An array of strings to be grouped.
     * @return A list of lists, where each inner list contains strings that are anagrams of each other.
     *
     * @complexity
     * - Time Complexity: O(N * K log K)
     * Where N is the number of strings in `strs`, and K is the maximum length of a string.
     * The dominating factor is sorting each string, which takes O(K log K).
     * The HashMap operations (put/get) are O(K) on average (due to string hashing/comparison) but sorting dominates.
     * - Space Complexity: O(N * K)
     * To store all the characters across all the strings in the HashMap.
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // Handle null or empty input gracefully.
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        // Use a HashMap where:
        // Key: The sorted string (e.g., "aet" for "eat", "tea", "ate")
        // Value: A list of strings that share that sorted key (e.g., ["eat", "tea", "ate"])
        Map<String, List<String>> anagramMap = new HashMap<>();

        for (String word : strs) {
            // 1. Convert the string to a character array.
            char[] characters = word.toCharArray();
            
            // 2. Sort the character array. This generates the canonical key for the anagram group.
            Arrays.sort(characters);
            
            // 3. Convert the sorted character array back to a String.
            String sortedKey = new String(characters);

            // 4. Optimization: Use computeIfAbsent for cleaner and often more efficient map manipulation.
            // This ensures a new ArrayList is created and associated with the key only if the key is absent.
            List<String> group = anagramMap.computeIfAbsent(sortedKey, k -> new ArrayList<>());

            // 5. Add the original word to its corresponding anagram group.
            group.add(word);
        }

        // Return all the values (the lists of anagrams) from the map as a new list.
        return new ArrayList<>(anagramMap.values());
    }
}