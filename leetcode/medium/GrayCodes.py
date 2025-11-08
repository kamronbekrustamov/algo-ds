from typing import List

class Solution:
    """
    Generates the n-bit Gray code sequence.

    The n-bit Gray code sequence is a sequence of 2^n unique binary numbers
    where successive codes differ in only one bit (a Hamming distance of 1).
    """
    def grayCode(self, n: int) -> List[int]:
        """
        Generates the Gray code sequence of length 2^n.

        This implementation uses the reflective property of Gray codes:
        The (n)-bit Gray code is the (n-1)-bit Gray code list (L),
        followed by the reversed (n-1)-bit Gray code list (L')
        with the most significant bit (MSB) set to 1.
        The MSB value is equal to the current list length (2^(k-1)).

        For example:
        n=1: [0, 1]
        n=2: [0, 1] + [1+2, 0+2] = [0, 1, 3, 2]
        n=3: [0, 1, 3, 2] + [2+4, 3+4, 1+4, 0+4] = [0, 1, 3, 2, 6, 7, 5, 4]

        Args:
            n: The number of bits for the Gray code (n >= 0).

        Returns:
            A list of integers representing the Gray code sequence.
        """
        # Base case for n=0: Gray code is just [0] (2^0 = 1 element)
        if n == 0:
            return [0]

        # Initialize with the 1-bit Gray code sequence: [0, 1]
        result: List[int] = [0, 1]
        
        # 'm' represents the value to add (1 << i), which is the most significant bit 
        # for the current reflection step, and also the length of the list 
        # before the current reflection. It starts as 2^(1-1) = 1, then 2, 4, 8, ...
        m = 2
        
        # Loop from i=2 up to n. The loop runs n-1 times.
        # It generates the 2-bit, 3-bit, ..., n-bit Gray code from the previous one.
        for _ in range(2, n + 1):
            # The previous sequence's length is len(result).
            # The new element to add as MSB is 'm', which is 2^(i-1).
            # The next length for the list will be len(result) * 2.
            
            # The previous loop used 'count' for the list length, 
            # which is equivalent to the current 'm'.
            # The iteration is over the previous list in reverse.
            # result[m - i - 1] in the original is now result[m - 1 - i]
            # where i is from 0 to m-1.
            
            # Use a list comprehension for a slightly cleaner append operation
            # The loop is equivalent to: result.extend([x + m for x in result[::-1]])
            for i in range(m - 1, -1, -1):
                result.append(result[i] + m)
                
            m *= 2  # Update m for the next iteration (m = 2^i)
            
        return result