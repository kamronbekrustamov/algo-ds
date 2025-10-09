import java.util.Deque;
import java.util.ArrayDeque;

/**
 * Implements a First-In, First-Out (FIFO) queue using two stacks.
 *
 * This implementation uses an input stack and an output stack to achieve
 * queue functionality. The `push` operation is O(1), while `pop` and `peek`
 * have an amortized time complexity of O(1).
 */
class MyQueue {

    // stack_in is used for push operations.
    private Deque<Integer> stack_in;
    // stack_out is used for pop and peek operations.
    private Deque<Integer> stack_out;

    /**
     * Initializes the queue with two empty stacks.
     */
    public MyQueue() {
        stack_in = new ArrayDeque<>();
        stack_out = new ArrayDeque<>();
    }

    /**
     * Pushes an element x to the back of the queue.
     *
     * This operation simply pushes the element onto the `stack_in`.
     * Time complexity: O(1).
     * @param x The element to push.
     */
    public void push(int x) {
        stack_in.push(x);
    }

    /**
     * Removes and returns the element from the front of the queue.
     *
     * If `stack_out` is empty, it first moves all elements from `stack_in`
     * to `stack_out`. This reversal of elements ensures FIFO order.
     * Amortized time complexity: O(1).
     * @return The element at the front of the queue.
     */
    public int pop() {
        move();
        return stack_out.pop();
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * Similar to `pop`, it ensures `stack_out` has the front element.
     * Amortized time complexity: O(1).
     * @return The element at the front of the queue.
     */
    public int peek() {
        move();
        return stack_out.peek();
    }

    /**
     * Returns whether the queue is empty.
     *
     * The queue is empty if and only if both stacks are empty.
     * Time complexity: O(1).
     * @return true if the queue is empty, false otherwise.
     */
    public boolean empty() {
        return stack_in.isEmpty() && stack_out.isEmpty();
    }

    /**
     * Moves elements from `stack_in` to `stack_out` if `stack_out` is empty.
     *
     * This operation is the core of the queue implementation. It reverses the
     * order of elements from `stack_in` and places them in `stack_out`,
     * so the first element pushed into `stack_in` becomes the top of `stack_out`.
     */
    private void move() {
        if (stack_out.isEmpty()) {
            while (!stack_in.isEmpty()) {
                stack_out.push(stack_in.pop());
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
