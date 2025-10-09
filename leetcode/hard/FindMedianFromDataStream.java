import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {
    /**
     * A data structure that finds the median from a data stream of integers.
     *
     * This implementation uses two heaps to find the median:
     * 1. A max-heap (`maxHeap`) to store the smaller half of the numbers.
     * 2. A min-heap (`minHeap`) to store the larger half of the numbers.
     *
     * The heaps are intended to be balanced such that their sizes differ by at most 1.
     *
     * Time Complexity:
     * - `addNum`: O(log N), where N is the total number of elements, due to heap insertions/deletions.
     * - `findMedian`: O(1).
     * Space Complexity: O(N) to store all numbers in the heaps.
     */

    private PriorityQueue<Integer> maxHeap; // Stores the smaller half, top is largest of small half
    private PriorityQueue<Integer> minHeap; // Stores the larger half, top is smallest of large half

    /**
     * Initializes the MedianFinder object.
     */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Max-heap
        minHeap = new PriorityQueue<>(); // Min-heap
    }

    /**
     * Adds an integer to the data structure, attempting to keep the two heaps balanced.
     *
     * Algorithm:
     * 1. Add the new number `num` to the `maxHeap` (smaller half).
     * 2. Move the largest element from `maxHeap` to `minHeap` to maintain the property
     *    that all elements in `maxHeap` are less than or equal to elements in `minHeap`.
     * 3. If `maxHeap` becomes smaller than `minHeap` (meaning `minHeap` has more than one
     *    extra element), move the smallest element from `minHeap` back to `maxHeap` to rebalance.
     *
     * @param num The integer to add.
     */
    public void addNum(int num) {
        maxHeap.offer(num); // Add to max-heap first
        minHeap.offer(maxHeap.poll()); // Move largest from max-heap to min-heap

        // Rebalance if maxHeap becomes too small
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * Returns the median of all elements added so far.
     *
     * Algorithm:
     * 1. If the total number of elements is even (heaps have equal size), the median is
     *    the average of the top elements of `maxHeap` and `minHeap`.
     * 2. If the total number of elements is odd (`maxHeap` will have one more element),
     *    the median is the top element of `maxHeap`.
     *
     * @return The median of the numbers in the data stream.
     */
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
