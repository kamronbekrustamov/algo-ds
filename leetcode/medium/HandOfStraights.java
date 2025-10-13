import java.util.TreeMap;

/**
 * Solution for the Hand of Straights problem.
 */
class Solution {
    /**
     * Checks if a hand of cards can be rearranged into groups of `groupSize`
     * consecutive cards.
     *
     * The approach is greedy. We first count the occurrences of each card using a
     * sorted map (TreeMap). A sorted map is crucial because it allows us to always
     * start a new potential straight with the smallest card currently available.
     *
     * The algorithm proceeds as follows:
     * 1. Check if the total number of cards is divisible by `groupSize`. If not,
     *    it's impossible, so return false.
     * 2. Count all cards and store them in a TreeMap to keep them sorted by card value.
     * 3. As long as there are cards left in the map:
     *    a. Get the smallest card number available (the first key in the TreeMap).
     *       This will be the start of a new straight.
     *    b. Iterate from this start card up to `startCard + groupSize - 1`.
     *    c. For each card in this required sequence, decrement its count in the map.
     *    d. If a required card is not found in the map, then a straight cannot be
     *       formed, and we return false.
     * 4. If we successfully form all groups, return true.
     *
     * @param hand An array of integers representing the cards.
     * @param groupSize The size of each group of consecutive cards.
     * @return True if the hand can be rearranged as required, false otherwise.
     */
    public boolean isNStraightHand(int[] hand, int groupSize) {
        // 1. Initial check for divisibility.
        if (hand.length % groupSize != 0) {
            return false;
        }

        // 2. Count card occurrences in a sorted map.
        TreeMap<Integer, Integer> cardCounts = new TreeMap<>();
        for (int card : hand) {
            cardCounts.put(card, cardCounts.getOrDefault(card, 0) + 1);
        }

        // 3. Greedily form straights until all cards are used.
        while (!cardCounts.isEmpty()) {
            // a. Get the smallest card to start a new straight.
            int startCard = cardCounts.firstKey();

            // b. Try to form a straight of `groupSize` cards.
            for (int i = 0; i < groupSize; i++) {
                int currentCard = startCard + i;

                // c. Check if the required card is available.
                if (!cardCounts.containsKey(currentCard)) {
                    return false;
                }

                // d. Decrement the card's count.
                int count = cardCounts.get(currentCard);
                if (count == 1) {
                    // Remove the card from the map if it's the last one.
                    cardCounts.remove(currentCard);
                } else {
                    cardCounts.put(currentCard, count - 1);
                }
            }
        }

        // 4. If the loop completes, all cards have been formed into valid straights.
        return true;
    }
}