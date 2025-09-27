from typing import List

class Solution:
    def asteroidCollision(self, asteroids: List[int]) -> List[int]:
        """
        Simulates asteroid collisions and returns the state of the asteroids after all collisions.
        Asteroids are represented by integers: positive values move right, negative values move left.
        The absolute value represents the size.
        When two asteroids collide:
        - If they are of the same size, both explode.
        - If they are of different sizes, the smaller one explodes.
        - If they move in the same direction, they never collide.
        - If they move towards each other (right-moving followed by left-moving), they might collide.

        Args:
            asteroids: A list of integers representing the asteroids.

        Returns:
            A list of integers representing the asteroids after all collisions.
        """
        
        stack = [] # Use a stack to keep track of asteroids that haven't collided yet.

        for asteroid in asteroids:
            # A flag to determine if the current asteroid has exploded.
            # It starts as True if the asteroid is moving right (no immediate collision with stack).
            # Or if the stack is empty (no collision possible).
            # It becomes False if the asteroid survives all collisions.
            asteroid_survived = True 

            # Collision condition: current asteroid moves left (negative) AND
            # there are asteroids in the stack AND the top asteroid in the stack moves right (positive).
            while stack and asteroid < 0 and stack[-1] > 0:
                # Case 1: Current asteroid is larger than the top of the stack.
                # The top of the stack explodes. Pop it and continue checking the current asteroid.
                if abs(asteroid) > stack[-1]:
                    stack.pop()
                    continue # Continue the while loop to check against the new stack top.
                
                # Case 2: Current asteroid is equal in size to the top of the stack.
                # Both explode. Pop from stack, and the current asteroid is destroyed.
                elif abs(asteroid) == stack[-1]:
                    stack.pop()
                    asteroid_survived = False # Current asteroid exploded.
                    break # Exit the while loop, as current asteroid is gone.
                
                # Case 3: Current asteroid is smaller than the top of the stack.
                # Current asteroid explodes. The top of the stack remains.
                else: # abs(asteroid) < stack[-1]
                    asteroid_survived = False # Current asteroid exploded.
                    break # Exit the while loop, as current asteroid is gone.
            
            # If the current asteroid survived all potential collisions, add it to the stack.
            if asteroid_survived:
                stack.append(asteroid)
                
        return stack


        
