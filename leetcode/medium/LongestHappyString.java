import java.util.PriorityQueue;

/**
 * Solution for the Longest Happy String problem.
 */
class Solution {

    /**
     * Helper class to store a character and its remaining count.
     * This improves readability over using an array like `int[]`.
     */
    private static class CharCount {
        char character;
        int count;

        CharCount(char character, int count) {
            this.character = character;
            this.count = count;
        }
    }

    /**
     * Constructs the longest possible "happy" string given the counts of 'a', 'b', and 'c'.
     *
     * A "happy" string is one that does not contain "aaa", "bbb", or "ccc" as a substring.
     *
     * This solution uses a greedy approach with a max-heap (PriorityQueue) to prioritize
     * the character with the highest remaining count at each step, while ensuring the
     * "happy" string condition is not violated.
     *
     * @param a The number of 'a' characters available.
     * @param b The number of 'b' characters available.
     * @param c The number of 'c' characters available.
     * @return The longest possible happy string.
     */
    public String longestDiverseString(int a, int b, int c) {
        // A PriorityQueue configured as a max-heap based on character counts.
        PriorityQueue<CharCount> maxHeap = new PriorityQueue<>((p1, p2) -> p2.count - p1.count);

        // Add characters to the heap only if their counts are greater than 0.
        if (a > 0) maxHeap.offer(new CharCount('a', a));
        if (b > 0) maxHeap.offer(new CharCount('b', b));
        if (c > 0) maxHeap.offer(new CharCount('c', c));

        StringBuilder resultBuilder = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            // Get the character with the highest remaining count.
            CharCount mostFrequent = maxHeap.poll();
            int len = resultBuilder.length();

            // --- Greedy Choice ---
            // Check if adding this character would violate the happy string rule
            // (i.e., create a sequence of three identical characters).
            if (len >= 2 && resultBuilder.charAt(len - 1) == mostFrequent.character && resultBuilder.charAt(len - 2) == mostFrequent.character) {
                // If we can't use the most frequent character, we must use the second most frequent.
                // If there's no second most frequent, we can't add any more characters.
                if (maxHeap.isEmpty()) {
                    break;
                }

                // Get the second most frequent character.
                CharCount secondMostFrequent = maxHeap.poll();
                
                // Add the second most frequent character to the result.
                resultBuilder.append(secondMostFrequent.character);
                secondMostFrequent.count--;

                // If the second most frequent character can still be used, add it back to the heap.
                if (secondMostFrequent.count > 0) {
                    maxHeap.offer(secondMostFrequent);
                }
                
                // Crucially, add the most frequent character (that we couldn't use)
                // back onto the heap so it can be used in the next iteration.
                maxHeap.offer(mostFrequent);

            } else {
                // It's safe to add the most frequent character.
                resultBuilder.append(mostFrequent.character);
                mostFrequent.count--;

                // If the most frequent character can still be used, add it back to the heap.
                if (mostFrequent.count > 0) {
                    maxHeap.offer(mostFrequent);
                }
            }
        }

        return resultBuilder.toString();
    }
}