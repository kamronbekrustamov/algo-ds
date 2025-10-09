import java.util.Set;
import java.util.HashSet;

/**
 * Solution class for the Jewels and Stones problem.
 */
class Solution {
    /**
     * Counts how many of the stones you have are also jewels.
     *
     * The letters in `jewels` are distinct jewel types.
     * The letters in `stones` are the stones you have.
     *
     * @param jewels A string representing the types of stones that are jewels.
     * @param stones A string representing the stones you have.
     * @return The number of stones you have that are also jewels.
     */
    public int numJewelsInStones(String jewels, String stones) {
        // Create a HashSet for efficient O(1) lookup of jewel types.
        Set<Character> jewelSet = new HashSet<>();
        
        // Populate the set with each type of jewel.
        for (char jewel : jewels.toCharArray()) {
            jewelSet.add(jewel);
        }

        int jewelCount = 0;
        // Iterate through each stone you have.
        for (char stone : stones.toCharArray()) {
            // If the stone is in the jewelSet, increment the count.
            if (jewelSet.contains(stone)) {
                jewelCount++;
            }
        }

        return jewelCount;
    }
}
