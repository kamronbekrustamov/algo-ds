from collections import deque

class Solution:
    def canReach(self, s: str, minJump: int, maxJump: int) -> bool:
        n = len(s)
        if s[-1] == '1':
            return False
        
        reachable = [False] * n
        reachable[0] = True
        
        # Keep track of reachable positions in our current range
        from collections import deque
        queue = deque([0])  # positions we can jump from
        
        for i in range(1, n):
            # Remove positions that are too far back to reach i
            while queue and queue[0] < i - maxJump:
                queue.popleft()
            
            # Check if we can reach position i
            # We can reach i if:
            # 1. s[i] == '0' (not blocked)
            # 2. There exists a position in queue that can jump to i
            #    (i.e., queue is not empty AND the front of queue <= i - minJump)
            if s[i] == '0' and queue and queue[0] <= i - minJump:
                reachable[i] = True
                queue.append(i)
        
        return reachable[n - 1]