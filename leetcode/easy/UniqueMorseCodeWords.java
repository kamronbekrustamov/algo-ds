import java.util.HashSet;
import java.util.Set;

class Solution {
    /**
     * Calculates the number of unique Morse code transformations for a list of words.
     *
     * This method uses a HashSet to store the unique Morse code transformations.
     * A String array is used to store the Morse code for each letter.
     *
     * Algorithm:
     * 1. Create a String array to store the Morse code for each letter from 'a' to 'z'.
     * 2. Create a HashSet to store the unique Morse code transformations.
     * 3. Iterate through each word in the input array.
     * 4. For each word, build the Morse code representation by looking up each character in the Morse code array.
     * 5. Add the generated Morse code string to the HashSet.
     * 6. The size of the HashSet is the number of unique transformations.
     *
     * @param words An array of strings.
     * @return The number of unique Morse code transformations.
     *
     * Time Complexity: O(S), where S is the total number of characters in all words.
     * Space Complexity: O(S) in the worst case, where all transformations are unique.
     */
    public int uniqueMorseRepresentations(String[] words) {
        String[] morse = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> transformations = new HashSet<>();
        for (String word : words) {
            StringBuilder transformation = new StringBuilder();
            for (char c : word.toCharArray()) {
                transformation.append(morse[c - 'a']);
            }
            transformations.add(transformation.toString());
        }
        return transformations.size();
    }
}
