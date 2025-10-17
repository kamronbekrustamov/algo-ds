from typing import List

class Solution:
    """
    Finds the town judge in a town of N people.
    
    The town judge is defined by two conditions:
    1. The town judge trusts nobody.
    2. Everybody (except the town judge) trusts the town judge.
    """
    def findJudge(self, n: int, trust: List[List[int]]) -> int:
        """
        Calculates the trust score (in-degree minus out-degree) for each person.
        
        Args:
            n: The number of people in the town (labeled from 1 to n).
            trust: A list of trust relationships, where trust[i] = [a, b] 
                   represents that person 'a' trusts person 'b'.
                   
        Returns:
            The label of the town judge if they exist, otherwise -1.
        """
        
        # Base case: If there is only one person, they are trivially the judge.
        if n == 1:
            # If trust is not empty, it implies self-trust, which is technically
            # not allowed by the 'trusts nobody' condition, but for N=1 and 
            # an empty trust list, 1 is the judge. The problem constraints usually
            # guarantee the input respects the N people. 
            # We return 1 only if there are no explicit self-trust relationships
            # when N=1, but the simple N=1 check is sufficient.
            return 1 if not trust else -1 

        # We use an array to store the "trust score" for each person from 1 to n.
        # Index 0 is unused. The size is n + 1.
        # Score = (Number of people who trust this person) - (Number of people this person trusts)
        # This is equivalent to (In-degree) - (Out-degree).
        # People are 1-indexed, so we use an array of size n + 1.
        trust_scores = [0] * (n + 1)
        
        # O(T) time complexity, where T is the length of the trust list
        for a, b in trust:
            # Person 'a' trusts someone (out-degree increases by 1)
            # This reduces 'a's trust score by 1. (Condition 1: Judge trusts nobody)
            trust_scores[a] -= 1
            
            # Person 'b' is trusted by someone (in-degree increases by 1)
            # This increases 'b's trust score by 1. (Condition 2: Everyone trusts the Judge)
            trust_scores[b] += 1
            
        # O(N) time complexity to check all people
        # The judge must satisfy two conditions, which translate to a single score:
        # 1. The judge trusts nobody: Out-degree = 0.
        # 2. Everybody (n-1 others) trusts the judge: In-degree = n - 1.
        # Score = In-degree - Out-degree = (n - 1) - 0 = n - 1.
        for i in range(1, n + 1):
            if trust_scores[i] == n - 1:
                return i
                
        # If no one satisfies the trust score of n - 1, no judge exists.
        return -1