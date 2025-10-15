import java.util.Deque;
import java.util.ArrayDeque;

/**
 * Implements a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time, O(1).
 * * This is achieved by storing a Pair object for each element, which contains
 * the element's value and the minimum value of the stack up to that point.
 */
class MinStack {

    /**
     * A simple class to hold an element's value and the minimum value of the
     * stack when this element was pushed.
     */
    private static class Pair {
        private final int val; // The value of the element being pushed.
        private final int min; // The minimum value of the stack *including* this element.

        /**
         * Constructs a new Pair.
         * @param val The value to store.
         * @param min The current minimum of the stack up to this point.
         */
        public Pair(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    // The main stack storing elements and their corresponding minimums.
    private final Deque<Pair> stack;

    /**
     * Initializes the MinStack object.
     */
    public MinStack() {
        stack = new ArrayDeque<>();
    }

    /**
     * Pushes the element 'val' onto the stack.
     * The associated minimum value is calculated and stored with 'val'.
     * Time Complexity: O(1).
     * @param val The value to push onto the stack.
     */
    public void push(int val) {
        int currentMin = val;
        // If the stack is not empty, the new minimum is the lesser of the new value
        // and the current overall minimum (which is stored at the top of the stack).
        if (!stack.isEmpty()) {
            currentMin = Math.min(val, stack.peek().min);
        }
        stack.push(new Pair(val, currentMin));
    }

    /**
     * Removes the element on top of the stack.
     * Throws an exception if the stack is empty (optional, but good practice).
     * Time Complexity: O(1).
     */
    public void pop() {
        if (stack.isEmpty()) {
            throw new java.util.NoSuchElementException("Cannot pop from an empty stack.");
        }
        stack.pop();
    }

    /**
     * Gets the element on top of the stack.
     * Throws an exception if the stack is empty.
     * Time Complexity: O(1).
     * @return The top element's value.
     */
    public int top() {
        if (stack.isEmpty()) {
            throw new java.util.NoSuchElementException("Cannot get top from an empty stack.");
        }
        return stack.peek().val;
    }

    /**
     * Retrieves the minimum element in the stack.
     * Throws an exception if the stack is empty.
     * Time Complexity: O(1).
     * @return The minimum element's value.
     */
    public int getMin() {
        if (stack.isEmpty()) {
            throw new java.util.NoSuchElementException("Cannot get min from an empty stack.");
        }
        // The minimum element up to this point is stored in the Pair at the top.
        return stack.peek().min;
    }
}