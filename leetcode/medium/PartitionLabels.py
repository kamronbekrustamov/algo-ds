from typing import List

class Solution:
    """
    Partitions a string into the maximum number of parts such that each letter
    appears in at most one part.
    """
    def partitionLabels(self, s: str) -> List[int]:
        """
        Greedily finds the partition points by tracking the last occurrence of 
        each character.

        Args:
            s: The input string consisting of lowercase English letters.

        Returns:
            A list of integers representing the size of each partition.

        Time Complexity: O(N)
            The algorithm makes two passes over the string:
            1. O(N) to build the `last_seen` map/array.
            2. O(N) to iterate and determine the partition points.
            The total complexity is O(N).

        Space Complexity: O(1)
            Uses a fixed-size array (`last_seen`) of size 26 to store the 
            last index for each lowercase English letter, independent of the 
            input string length N.
        """
        # 1. Store the last index of every character (O(N) time)
        # Using a fixed-size array (26 for 'a' through 'z') for O(1) space.
        last_seen = [-1] * 26
        for i, char in enumerate(s):
            # Calculate the index (0 for 'a', 1 for 'b', etc.)
            last_seen[ord(char) - ord('a')] = i

        # 2. Greedy partitioning (O(N) time)
        res = []
        start_of_partition = 0
        # 'farthest_reach' tracks the maximum last index of any character encountered
        # within the current running partition.
        farthest_reach = 0

        for i, char in enumerate(s):
            # Update the farthest index the current partition must reach.
            # This is the key greedy step: the partition must extend at least 
            # to the last occurrence of the current character.
            char_index = ord(char) - ord('a')
            farthest_reach = max(farthest_reach, last_seen[char_index])
            
            # If the current index 'i' is equal to 'farthest_reach', 
            # it means all characters from 'start_of_partition' up to 'i' 
            # do not appear again later in the string.
            if i == farthest_reach:
                # We've found a valid partition boundary.
                partition_size = i - start_of_partition + 1
                res.append(partition_size)
                
                # Start the next partition immediately after the current one.
                start_of_partition = i + 1
                # Note: 'farthest_reach' will be updated by the first character 
                # of the new partition in the next iteration.
                
        return res