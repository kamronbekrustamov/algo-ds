from collections import Counter
import heapq
from typing import List

class Solution:
    """
    Determines if a hand of cards can be partitioned into consecutive groups of a specified size.
    This is commonly known as the "Hand of Straights" problem (or "Divide Array in Sets of K Consecutive Numbers").
    """
    def isNStraightHand(self, hand: List[int], groupSize: int) -> bool:
        """
        :param hand: A list of card integer values.
        :param groupSize: The required size of each consecutive group (straight).
        :return: True if the partitioning is possible, False otherwise.
        """
        n = len(hand)
        
        # 1. Initial Check: The total number of cards must be divisible by the group size.
        if n % groupSize != 0:
            return False
            
        # 2. Count Card Frequencies: Store the frequency of each card value. O(N)
        # Using a hash map (Counter) for O(1) average time lookups.
        counts = Counter(hand)
        
        # 3. Min-Heap Initialization: Store the unique card values in a min-heap. O(U log U)
        # U is the number of unique cards. This allows efficient access to the smallest available card.
        min_heap = list(counts.keys())
        heapq.heapify(min_heap)
        
        # 4. Greedy Processing
        # The greedy strategy dictates that the smallest available card must start a new group.
        while min_heap:
            # Get the smallest available card value to start a new straight.
            start_card = min_heap[0]
            
            # If the smallest card in the heap has already been fully used (its count is 0), 
            # pop it and move to the next smallest card. This ensures min_heap[0] is always relevant.
            if counts[start_card] == 0:
                heapq.heappop(min_heap)
                continue # Skip to the next iteration
            
            # If the start_card count is > 0, try to form a straight of size `groupSize`.
            for card in range(start_card, start_card + groupSize):
                # a. Check if the required consecutive card is available. O(1) average.
                if counts[card] == 0:
                    # If any card in the required straight is missing, the partition is impossible.
                    return False
                
                # b. Use one instance of the current card.
                counts[card] -= 1
                
            # OPTIMIZATION/CORRECTION:
            # We ONLY check and pop the `start_card` from the heap after successfully forming 
            # the straight. The only card guaranteed to be removed from the heap (if its count hits 0) 
            # *must* be the current smallest element (`start_card`). The loop's starting check 
            # handles the removal for all other consumed cards.
            # No explicit `heappop` is needed inside the inner loop for `card` != `start_card`.
            # If the count for `start_card` is now 0, it means the group that consumed its last 
            # instance was formed, so we can now safely pop it.
            if counts[start_card] == 0:
                 heapq.heappop(min_heap)

        # 5. Result
        # If the loop completes, all cards have been successfully grouped.
        return True