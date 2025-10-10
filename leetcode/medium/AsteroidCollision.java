import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Solution for the Asteroid Collision problem.
 */
class Solution {
    /**
     * Simulates asteroid collisions and returns the state of the asteroids after all collisions.
     *
     * @param asteroids An array of integers representing the asteroids.
     *                  Positive integers represent asteroids moving to the right.
     *                  Negative integers represent asteroids moving to the left.
     *                  The absolute value of the integer represents the size of the asteroid.
     * @return An array representing the asteroids remaining after all collisions.
     */
    public int[] asteroidCollision(int[] asteroids) {
        // Use a Deque as a stack to keep track of the asteroids.
        Deque<Integer> stack = new ArrayDeque<>();

        // Iterate through each asteroid in the input array.
        for (int currentAsteroid : asteroids) {
            // A flag to determine if the current asteroid should be added to the stack.
            // It survives if it doesn't collide or wins all its collisions.
            boolean shouldAdd = true;

            // A collision can only happen if the stack is not empty,
            // the asteroid at the top of the stack is moving right (> 0),
            // and the current asteroid is moving left (< 0).
            while (!stack.isEmpty() && stack.peekLast() > 0 && currentAsteroid < 0) {
                int lastAsteroid = stack.peekLast();

                // If the right-moving asteroid (lastAsteroid) is smaller than the
                // left-moving one (currentAsteroid) in absolute value.
                if (lastAsteroid < -currentAsteroid) {
                    // The right-moving asteroid is destroyed. Pop it from the stack.
                    stack.pollLast();
                    // The current asteroid continues to the next potential collision.
                    continue;
                }
                // If the right-moving asteroid is larger or equal in size.
                else {
                    // The current left-moving asteroid is destroyed.
                    shouldAdd = false;
                    // If they are equal in size, the right-moving one is also destroyed.
                    if (lastAsteroid == -currentAsteroid) {
                        stack.pollLast();
                    }
                    // Break the loop as the current asteroid's journey ends here.
                    break;
                }
            }

            // If the current asteroid survived all collisions (or there were no collisions).
            if (shouldAdd) {
                stack.addLast(currentAsteroid);
            }
        }

        // Convert the stack to an integer array to return the result.
        int[] result = new int[stack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.pollFirst();
        }
        return result;
    }
}
