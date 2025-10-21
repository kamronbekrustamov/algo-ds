from typing import List

class Solution:
    """
    Checks if a list of words is lexicographically sorted according to a specific
    alien alphabet order.
    """

    def isAlienSorted(self, words: List[str], order: str) -> bool:
        """
        Determines if the 'words' list is sorted in the 'order' specified.

        The comparison is done between every adjacent pair of words.

        Args:
            words: A list of strings to check for sortedness.
            order: A string representing the alien lexicographical order.

        Returns:
            True if the words are sorted, False otherwise.
        """
        # 1. Map each character in the alien order to its precedence index.
        # This allows for O(1) lookup of a character's rank.
        order_map = {char: idx for idx, char in enumerate(order)}

        # 2. Iterate through all adjacent pairs of words (word1, word2).
        for i in range(len(words) - 1):
            w1, w2 = words[i], words[i + 1]
            
            # 3. Compare characters of w1 and w2 from left to right.
            for j in range(len(w1)):
                # Case 1: w1 is a prefix of w2 (e.g., "apple" and "app").
                # If w1 is longer than w2 and they match up to len(w2), 
                # then w1 comes after w2 in any dictionary, meaning they are NOT sorted.
                if j == len(w2):
                    return False
                
                # Case 2: Mismatch found. Compare their ranks in the alien order.
                if w1[j] != w2[j]:
                    rank1 = order_map[w1[j]]
                    rank2 = order_map[w2[j]]
                    
                    # If the character in w2 comes before the character in w1 
                    # (i.e., w2's rank is less than w1's rank), 
                    # they are NOT sorted.
                    if rank2 < rank1:
                        return False
                    
                    # If w1[j] comes before w2[j] (rank1 < rank2), the pair is sorted 
                    # up to this point, so we can move to the next pair of words.
                    break
            
            # Case 3: Both words are equal (e.g., "word", "word"). 
            # If the inner loop completes without a 'break' or 'return False', 
            # it means the words are identical or w1 is a prefix of w2 
            # (handled by Case 1's return False). If they are identical, they are sorted,
            # and the outer loop continues to the next pair.

        # 4. If all adjacent pairs are correctly sorted, the entire list is sorted.
        return True