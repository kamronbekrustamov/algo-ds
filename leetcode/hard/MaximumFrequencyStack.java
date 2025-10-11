import java.util.HashMap;
import java.util.Map;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 * Implements a Frequency Stack, a data structure that pops the most frequent element.
 * If there is a tie in frequency, the element pushed most recently is popped.
 *
 * This implementation achieves O(1) time complexity for both push and pop operations.
 */
class FreqStack {

    // Map to store the frequency of each element.
    // Key: element, Value: frequency
    private final Map<Integer, Integer> freq;

    // Map to group elements by their frequency. Each frequency maps to a stack
    // of elements that have that frequency. The stack ensures that for a given
    // frequency, the most recently pushed element is at the top.
    // Key: frequency, Value: Stack of elements with this frequency
    private final Map<Integer, Deque<Integer>> freqToStack;

    // Tracks the current maximum frequency of any element in the stack.
    private int maxFreq;

    /**
     * Constructor to initialize the data structures.
     */
    public FreqStack() {
        freq = new HashMap<>();
        freqToStack = new HashMap<>();
        maxFreq = 0;
    }

    /**
     * Pushes an integer value onto the stack.
     *
     * @param val The integer value to push.
     */
    public void push(int val) {
        // 1. Get the new frequency of the element.
        int newFreq = freq.getOrDefault(val, 0) + 1;
        // 2. Update the frequency map.
        freq.put(val, newFreq);

        // 3. Update the maximum frequency seen so far.
        if (newFreq > maxFreq) {
            maxFreq = newFreq;
        }

        // 4. Push the element onto the stack corresponding to its new frequency.
        //    computeIfAbsent ensures the stack is created if it's the first time
        //    an element reaches this frequency.
        freqToStack.computeIfAbsent(newFreq, k -> new ArrayDeque<>()).push(val);
    }

    /**
     * Pops and returns the most frequent element from the stack.
     * If there's a tie, it returns the element that was pushed most recently.
     *
     * @return The most frequent element.
     */
    public int pop() {
        // 1. Get the stack of elements with the highest frequency.
        Deque<Integer> topStack = freqToStack.get(maxFreq);
        // 2. Pop the top element. This is the most frequent and most recent element.
        int val = topStack.pop();

        // 3. Decrement the frequency of the popped element.
        freq.put(val, freq.get(val) - 1);

        // 4. If the stack for the max frequency is now empty, it means the
        //    maximum frequency in the entire FreqStack has decreased by one.
        if (topStack.isEmpty()) {
            maxFreq--;
        }

        return val;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */