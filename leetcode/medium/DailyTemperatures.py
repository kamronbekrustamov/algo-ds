from typing import List

class Solution:
    def dailyTemperatures(self, temperatures: List[int]) -> List[int]:
        """
        Given an array of integers `temperatures` represents the daily temperatures,
        return an array `answer` such that `answer[i]` is the number of days you
        have to wait after the i-th day to get a warmer temperature. If there is
        no future day for which this is possible, keep `answer[i] == 0` instead.

        This problem can be efficiently solved using a monotonic stack.
        The stack will store indices of temperatures in decreasing order.

        Args:
            temperatures: A list of integers representing daily temperatures.

        Returns:
            A list of integers where `answer[i]` is the number of days to wait
            for a warmer temperature, or 0 if no warmer day exists.
        """
        
        # Initialize an empty stack. The stack will store tuples of (index, temperature).
        # We maintain a decreasing order of temperatures in the stack.
        stack = []
        
        # Initialize the result array with zeros. This will store the waiting days.
        # If no warmer temperature is found, the value remains 0.
        result = [0] * len(temperatures)
        
        # Iterate through the temperatures with their indices.
        for index, temperature in enumerate(temperatures):
            # While the stack is not empty AND the current temperature is warmer
            # than the temperature at the index on top of the stack:
            while stack and stack[-1][1] < temperature:
                # Pop the (index, temperature) pair from the stack.
                # This means we found a warmer temperature for the popped day.
                prev_index, prev_temperature = stack.pop()
                
                # Calculate the number of days to wait and store it in the result array.
                result[prev_index] = index - prev_index
            
            # Push the current (index, temperature) onto the stack.
            # The stack maintains a decreasing order of temperatures.
            stack.append((index, temperature))
            
        return result