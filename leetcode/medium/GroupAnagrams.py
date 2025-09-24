from collections import defaultdict
from typing import List

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        # The core idea is that all anagrams, when their letters are sorted,
        # will result in the exact same string. This sorted string can act as a
        # unique key for each group of anagrams.
        
        # We use a defaultdict(list) to automatically create a new list for any
        # new key we encounter. This simplifies the logic inside the loop.
        anagram_map = defaultdict(list)
        
        for word in strs:
            # Sort the characters of the word to create the canonical key.
            # For example, "eat", "tea", and "ate" all become "aet".
            sorted_key = "".join(sorted(word))
            
            # Append the original word to the list associated with its sorted key.
            anagram_map[sorted_key].append(word)
            
        # The values of the dictionary are the lists of grouped anagrams.
        return list(anagram_map.values())
