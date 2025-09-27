from typing import List

class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        """
        Generates all permutations of a list without using the itertools module.
        
        :param data: The list of elements to permute.
        :return: A list of lists, where each inner list is a permutation.
        """
        # Base case: If the list has 1 or 0 elements, it is its own only permutation.
        if len(nums) <= 1:
            return [nums]

        # Initialize a list to store all the resulting permutations
        all_perms = []

        # Loop through each element in the list
        for i in range(len(nums)):
            # 1. Pick the current element (the 'head')
            head = nums[i]
            
            # 2. Get the remaining elements (the 'tail')
            # This is everything EXCEPT the element at index i
            tail = nums[:i] + nums[i+1:]
            
            # 3. Recursively find all permutations of the remaining elements
            for perm in permute(tail):
                # 4. Prepend the 'head' element to each permutation of the 'tail'
                # and add the new full permutation to the results list
                all_perms.append([head] + perm)

        return all_perms
