import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a solution to the "Partition Labels" problem (LeetCode 763).
 * The goal is to partition a string into as many parts as possible
 * such that each letter appears in at most one part.
 */
class Solution {

    /**
     * Partitions the given string into the maximum number of parts
     * such that no letter appears in more than one part.
     *
     * <h3>Algorithm Explanation</h3>
     * The algorithm works in two passes using a greedy approach:
     * <ol>
     * <li>
     * <b>First Pass (Preprocessing):</b>
     * Iterate through the string once to find the <b>last occurrence index</b>
     * for every character. We store this in an array.
     * This tells us the furthest "reach" of each character.
     * </li>
     * <li>
     * <b>Second Pass (Partitioning):</b>
     * Iterate through the string again, maintaining a "window."
     * We use two pointers:
     * <ul>
     * <li>`windowStart`: Marks the beginning of the current partition.</li>
     * <li>`windowEnd`: Marks the furthest "last seen" index of any
     * character we have encountered <em>in the current partition</em>.</li>
     * </ul>
     * As we iterate (with index `i`), we update `windowEnd` to be the
     * maximum of its current value and the `lastSeen` index of the
     * character at `i`.
     * <br><br>
     * When our iteration index `i` <b>reaches</b> `windowEnd`, it means
     * every character we've seen from `windowStart` to `i` has its
     * last occurrence <em>within</em> this window. We have found a
     * complete partition. We record its length (`windowEnd - windowStart + 1`)
     * and set `windowStart` to `i + 1` to begin searching for the next partition.
     * </li>
     * </ol>
     *
     * @param s The input string. This solution assumes `s` contains
     * only lowercase English letters.
     * @return A list of integers representing the lengths of the partitions.
     */
    public List<Integer> partitionLabels(String s) {
        
        // --- First Pass: Record the last seen index for each char ---
        
        // We use an int[26] array to store the last seen index for 'a'-'z'.
        // This provides O(1) access and is efficient for a fixed lowercase alphabet.
        int[] lastSeen = new int[26];
        for (int i = 0; i < s.length(); i++) {
            // 's.charAt(i) - 'a'' maps 'a'->0, 'b'->1, ... 'z'->25
            lastSeen[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int windowStart = 0; // The start index of the current partition
        int windowEnd = 0;   // The "furthest reach" of the current partition

        // --- Second Pass: Find partitions greedily ---
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            // Expand the current window to include the last
            // occurrence of the current character.
            windowEnd = Math.max(windowEnd, lastSeen[currentChar - 'a']);

            // If the current index 'i' has reached the 'windowEnd',
            // it means all characters within this window
            // (from windowStart to windowEnd) appear *only* in this window.
            // We have found a complete partition.
            if (i == windowEnd) {
                // Add the size of the partition to our result.
                int partitionLength = (windowEnd - windowStart) + 1;
                result.add(partitionLength);
                
                // Set the start of the *next* potential partition.
                windowStart = i + 1;
            }
        }
        
        return result;
    }
}