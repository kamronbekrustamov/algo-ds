import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Solution for LeetCode problem "Last Stone Weight".
 * This class provides a method to simulate the smashing of stones until at most one stone remains.
 */
class Solution {

    /**
     * Simulates the smashing of stones and returns the weight of the last remaining stone,
     * or 0 if no stones are left.
     *
     * The problem describes a process where, in each turn, the two heaviest stones are chosen.
     * If their weights are x and y (where x <= y):
     * - If x == y, both stones are destroyed.
     * - If x != y, the stone of weight x is destroyed, and the stone of weight y has its new weight y - x.
     *
     * A max-heap (PriorityQueue in Java with reverseOrder) is an ideal data structure for this problem
     * because it allows efficient retrieval of the two largest elements (stones) at each step.
     *
     * @param stones An array of integers representing the weights of the stones.
     * @return The weight of the last remaining stone, or 0 if no stones are left.
     */
    public int lastStoneWeight(int[] stones) {
        // Create a max-heap to store stone weights. Collections.reverseOrder() makes it a max-heap.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Add all initial stone weights to the max-heap.
        for (int stone : stones) {
            maxHeap.add(stone);
        }

        // Continue smashing stones as long as there are at least two stones in the heap.
        while (maxHeap.size() > 1) {
            // Get the two heaviest stones.
            int first = maxHeap.poll();  // Heaviest stone
            int second = maxHeap.poll(); // Second heaviest stone

            // If their weights are different, a new stone with weight (first - second) is formed.
            // Since first >= second, the result (first - second) will be non-negative.
            if (first != second) {
                maxHeap.add(first - second);
            }
            // If first == second, both stones are destroyed, and nothing is added back.
        }

        // After the loop, if the heap is empty, all stones were destroyed (result is 0).
        // Otherwise, the single remaining stone's weight is the answer.
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
}
