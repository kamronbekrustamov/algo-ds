import java.util.PriorityQueue;

/**
 * Design a class to find the k-th largest element in a stream of numbers.
 *
 * This class uses a min-heap (PriorityQueue in Java) to efficiently keep track
 * of the k largest elements seen so far. The root of the min-heap is always
 * the k-th largest element.
 */
class KthLargest {
    private final PriorityQueue<Integer> minHeap;
    private final int k;

    /**
     * Initializes the KthLargest object with the value of k and an initial
     * stream of numbers.
     *
     * @param k    The k-th largest element to find.
     * @param nums An initial array of numbers in the stream.
     */
    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();
        
        // Add initial numbers from the stream to establish the initial heap state.
        for (int num : nums) {
            add(num);
        }
    }

    /**
     * Adds a new number to the stream and returns the current k-th largest element.
     *
     * @param val The new number to add to the stream.
     * @return The k-th largest element in the stream after adding the new value.
     */
    public int add(int val) {
        if (minHeap.size() < k) {
            // If the heap is not yet full, just add the new element.
            minHeap.offer(val);
        } else if (val > minHeap.peek()) {
            // If the new value is larger than the smallest element in the heap (the k-th largest),
            // remove the smallest and add the new value.
            minHeap.poll();
            minHeap.offer(val);
        }
        // The root of the min-heap is the k-th largest element.
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
