from collections import defaultdict

class FreqStack:
    """
    Implements a Frequency Stack, a data structure that pops the most frequent element.
    If there is a tie in frequency, the element pushed most recently is popped.

    This implementation achieves O(1) time complexity for both push and pop operations.
    """

    def __init__(self):
        """
        Initializes the data structures for the frequency stack.
        """
        # Tracks the current maximum frequency of any element in the stack.
        self.max_freq = 0
        # Dictionary to store the frequency of each element.
        # Key: element, Value: frequency
        self.freq = {}
        # Dictionary to group elements by their frequency. Each frequency maps
        # to a list (acting as a stack) of elements that have that frequency.
        # Key: frequency, Value: list of elements
        self.freq_to_stack = defaultdict(list)


    def push(self, val: int) -> None:
        """
        Pushes an integer value onto the stack.

        Args:
            val: The integer value to push.
        """
        # 1. Get the new frequency of the element.
        count = self.freq.get(val, 0) + 1
        # 2. Update the frequency dictionary.
        self.freq[val] = count

        # 3. Update the maximum frequency seen so far.
        self.max_freq = max(self.max_freq, count)

        # 4. Append the element to the list corresponding to its new frequency.
        self.freq_to_stack[count].append(val)


    def pop(self) -> int:
        """
        Pops and returns the most frequent element from the stack.
        If there's a tie, it returns the element that was pushed most recently.

        Returns:
            The most frequent element.
        """
        # 1. Get the list of elements with the highest frequency.
        stack = self.freq_to_stack[self.max_freq]
        # 2. Pop the last element. This is the most frequent and most recent one.
        val = stack.pop()

        # 3. Decrement the frequency of the popped element.
        self.freq[val] -= 1

        # 4. If the list for the max frequency is now empty, it means the
        #    maximum frequency in the entire FreqStack has decreased by one.
        if not stack:
            self.max_freq -= 1
        
        return val

# Your FreqStack object will be instantiated and called as such:
# obj = FreqStack()
# obj.push(val)
# param_2 = obj.pop()
