import java.util.Queue;
import java.util.LinkedList;

/**
 * Implements a Last-In, First-Out (LIFO) stack using only one queue.
 * The most recently added element is always kept at the front of the queue.
 */
class MyStack {

    private Queue<Integer> queue;

    /**
     * Initializes the stack with an empty queue.
     */
    public MyStack() {
        queue = new LinkedList<>();
    }

    /**
     * Pushes an element x onto the top of the stack.
     *
     * To maintain the LIFO order with a FIFO queue, we add the new element
     * to the queue and then rotate the queue so that the new element moves
     * from the back to the front.
     *
     * Time complexity: O(n), where n is the number of elements in the stack.
     * @param x The element to push.
     */
    public void push(int x) {
        // Add the new element to the back of the queue.
        queue.add(x);
        // Rotate the queue to move the new element to the front.
        // The number of rotations is one less than the size of the queue.
        int size = queue.size();
        for (int i = 0; i < size - 1; i++) {
            queue.add(queue.remove());
        }
    }

    /**
     * Removes and returns the element on top of the stack.
     *
     * Since the most recently pushed element is at the front of the queue,
     * we can simply remove it from the front.
     *
     * Time complexity: O(1).
     * @return The element on top of the stack.
     */
    public int pop() {
        return queue.remove();
    }

    /**
     * Returns the element on top of the stack without removing it.
     *
     * The top element is at the front of the queue.
     *
     * Time complexity: O(1).
     * @return The element on top of the stack.
     */
    public int top() {
        return queue.peek();
    }

    /**
     * Returns whether the stack is empty.
     *
     * Time complexity: O(1).
     * @return true if the stack is empty, false otherwise.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
